/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.person;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.address.AddressControl;
import com.lades.sihv.model.People;
import com.lades.sihv.model.Cpf;
import com.lades.sihv.model.FederationUnity;
import com.lades.sihv.model.Owners;
import com.lades.sihv.model.PhysicalPerson;
import com.lades.sihv.model.Rg;
import com.lades.sihv.model.Users;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author thiberius
 */
public class VerifyPersonDocument extends AbstractBean {

    private List<?> listSearchPerson = new ArrayList<>();
    private boolean statusPerson = false;
    private boolean checkCPF = false;
    private boolean checkRG = false;
    private boolean checkCNPJ = false;

    private String returnHqlCPF(Cpf objCpf) {
        return " and \n"
                + "( f.id.peoplePkPerson=c.id.physicalPersonPeoplePkPerson and \n"
                + "f.id.pkPhysicalPerson=c.id.physicalPersonPkPhysicalPerson and \n"
                + "c.cpf='" + objCpf.getCpf() + "' )";
    }

    private String returnHqlRG(Rg objRg, FederationUnity selectUF) {
        return " and \n"
                + "( f.id.peoplePkPerson=r.id.physicalPersonPeoplePkPerson and \n"
                + "f.id.pkPhysicalPerson=r.id.physicalPersonPkPhysicalPerson and \n"
                + "r.rg='" + objRg.getRg() + "' ) and \n"
                + "(uf.pkFederationUnity='" + selectUF.getPkFederationUnity() + "' and \n"
                + "uf.pkFederationUnity=cy.federationUnity.pkFederationUnity and \n"
                + "cy.pkCity=n.id.cityPkCity and \n"
                + "n.id.pkNeighborhood=a.neighborhood.id.pkNeighborhood and \n"
                + "a.pkAddress=h.address.pkAddress and \n"
                + "p.pkPerson=h.people.pkPerson) ";
    }

    private People checkForCPF(Cpf objCpf) {
        List<People> list = getDaoGenerico().list("select p from People p, PhysicalPerson f, Cpf c \n"
                + "where \n"
                + "p.pkPerson=f.id.peoplePkPerson and \n"
                + "f.id.pkPhysicalPerson=c.id.physicalPersonPkPhysicalPerson and \n"
                + "f.id.peoplePkPerson=c.id.physicalPersonPeoplePkPerson and \n"
                + "c.cpf='" + objCpf.getCpf() + "' ");
        if (list.isEmpty()) {
            return new People();
        } else {
            return list.get(0);
        }
    }

    private People checkForRG(Rg objRg) {
        List<People> list = getDaoGenerico().list("select p from People p, PhysicalPerson f, Rg r \n"
                + "where \n"
                + "p.pkPerson=f.id.peoplePkPerson and \n"
                + "f.id.pkPhysicalPerson=r.id.physicalPersonPkPhysicalPerson and \n"
                + "f.id.peoplePkPerson=r.id.physicalPersonPeoplePkPerson and \n"
                + "r.rg='" + objRg.getRg() + "' ");
        if (list.isEmpty()) {
            return new People();
        } else {
            return list.get(0);
        }
    }

    private String buildHQL(boolean checkCPF, boolean checkRG,
            VariablesPerson varPerson, AddressControl addressControl) {
        String hql = "";
        String entitys = "";
        String hqlCPF, hqlRG, objReturn;

        if (checkCPF && checkRG) {
            objReturn = ", c, r";
            entitys += ", Cpf c, Rg r, FederationUnity uf, City cy, Neighborhood n, Address a, Houses h \n";
            hqlCPF = returnHqlCPF(varPerson.getObjCpf());
            hqlRG = returnHqlRG(varPerson.getObjRg(), addressControl.getVar().getSelectUF());
            hql = "select p, f" + objReturn + " from People p, PhysicalPerson f " + entitys
                    + "where \n"
                    + "p.pkPerson=f.id.peoplePkPerson" + hqlCPF + hqlRG;
        } else if (checkCPF) {
            objReturn = ", c";
            entitys += ", Cpf c \n";
            hqlCPF = returnHqlCPF(varPerson.getObjCpf());
            hql = "select p, f" + objReturn + " from People p, PhysicalPerson f " + entitys
                    + "where \n"
                    + "p.pkPerson=f.id.peoplePkPerson" + hqlCPF;
        } else if (checkRG) {
            objReturn = ", r";
            entitys += ", Rg r, FederationUnity uf, City cy, Neighborhood n, Address a, Houses h \n";
            hqlRG = returnHqlRG(varPerson.getObjRg(), addressControl.getVar().getSelectUF());
            hql = "select p, f" + objReturn + " from People p, PhysicalPerson f " + entitys
                    + "where \n"
                    + "p.pkPerson=f.id.peoplePkPerson" + hqlRG;
        }
        return hql;
    }

    private List<?> searchRgOrCpfUsingPerson(People person, String CpfOrRg) {
        return getDaoGenerico().list("select x from People p, PhysicalPerson f, " + CpfOrRg + " x \n"
                + "where \n"
                + "p.pkPerson=f.id.peoplePkPerson and \n"
                + "f.id.pkPhysicalPerson=x.id.physicalPersonPkPhysicalPerson and \n"
                + "f.id.peoplePkPerson=x.id.physicalPersonPeoplePkPerson and \n"
                + "p.pkPerson='" + person.getPkPerson() + "' ");
    }

    private void completeRgPossible(boolean checkList, VariablesPerson varPerson) {
        try {
            if (checkList) {
                List<Rg> list = (List<Rg>) searchRgOrCpfUsingPerson(varPerson.getPerson(), "Rg");
                if (!list.isEmpty()) {
                    varPerson.setObjRg(list.get(0));
                    getObjMessage().info("Registro encontrado", varPerson.getPerson().getNamePerson());
                } else {
                    getObjMessage().warn("Registro incompleto identificado!",
                            varPerson.getPerson().getNamePerson() + " está sem RG no sistema.");
                }
            } else {
                varPerson.setObjRg(new Rg());
            }
        } catch (Exception e) {
            getObjMessage().error("BACK-END WARNING: ERRO private void completeRgPossible():", e.getMessage());
            System.out.println("BACK-END WARNING: ERRO private void completeRgPossible(): " + e);
        }
    }

    private void completeCpfPossible(boolean checkList, VariablesPerson varPerson) {
        try {
            if (checkList) {
                List<Cpf> list = (List<Cpf>) searchRgOrCpfUsingPerson(varPerson.getPerson(), "Cpf");
                if (!list.isEmpty()) {
                    varPerson.setObjCpf(list.get(0));
                    getObjMessage().info("Registro encontrado", varPerson.getPerson().getNamePerson());
                } else {
                    getObjMessage().warn("Registro incompleto identificado!",
                            varPerson.getPerson().getNamePerson() + " está sem CPF no sistema.");
                }
            } else {
                varPerson.setObjCpf(new Cpf());
            }
        } catch (Exception e) {
            getObjMessage().error("BACK-END WARNING: ERRO private void completeCpfPossible():", e.getMessage());
            System.out.println("BACK-END WARNING: ERRO private void completeCpfPossible(): " + e);
        }
    }

    private List<?> collectListValues(VariablesPerson varPerson,
            boolean checkCPF,
            boolean checkRG,
            String hql) {
        List<?> list = new ArrayList<>();
        try {
            list = getDaoGenerico().list(hql);
            if (!list.isEmpty()) {
                if (list.size() == 1) {
                    System.out.println("------------------ listSearchPerson:" + list.size());
                    for (Object[] object : (List<Object[]>) list) {
                        varPerson.setPerson((People) object[0]);
                        varPerson.setPhysicalPerson((PhysicalPerson) object[1]);
                        if (checkCPF && checkRG) {
                            varPerson.setObjCpf((Cpf) object[2]);
                            varPerson.setObjRg((Rg) object[3]);
                        } else if (checkCPF) {
                            varPerson.setObjCpf((Cpf) object[2]);
                        } else {
                            varPerson.setObjRg((Rg) object[2]);
                        }
                    }
                    if (checkCPF && checkRG) {
                        getObjMessage().info("Registro encontrado", varPerson.getPerson().getNamePerson());
                    }
                } else {
                    getObjMessage().error("Erro interno no sistema!", "Listagem diferente de 1: " + list.size());
                }
            } else if (checkCPF && checkRG) {

                People personCPF = checkForCPF(varPerson.getObjCpf());
                People personRG = checkForRG(varPerson.getObjRg());

                if (personCPF.getPkPerson() != null
                        && personRG.getPkPerson() != null) {

                    if (!Objects.equals(personCPF.getPkPerson(), personRG.getPkPerson())) {
                        getObjMessage().warn("Individuos diferentes!", "Utilize documentos que pertensa a somente um indivíduo");
                        getObjMessage().warn("O CPF " + varPerson.getObjCpf().getCpf(), "Pertence a " + personCPF.getNamePerson());
                        getObjMessage().warn("O RG " + varPerson.getObjRg().getRg(), "Pertence a " + personRG.getNamePerson());

                    } else {
                        getObjMessage().warn("Novo evento identificado! %",
                                "Contacte o analista de sistemas para informar o ocorrido.");
                    }
                } else if (personCPF.getPkPerson() != null) {
                    List<Rg> listRg = (List<Rg>) searchRgOrCpfUsingPerson(personCPF, "Rg");
                    if (!listRg.isEmpty()) {
                        getObjMessage().warn("Dados incorretos!",
                                personCPF.getNamePerson() + " com nº CPF "
                                + varPerson.getObjCpf().getCpf()
                                + ", já possui o RG " + listRg.get(0).getRg() + " registrado.");
                    } else {
                        getObjMessage().info("Novo documento identificado!",
                                "O RG " + varPerson.getObjRg().getRg()
                                + " será adicionado ao registro de "
                                + varPerson.getPerson().getNamePerson());
                    }
                } else if (personRG.getPkPerson() != null) {
                    List<Cpf> listCpf = (List<Cpf>) searchRgOrCpfUsingPerson(personRG, "Cpf");
                    if (!listCpf.isEmpty()) {
                        getObjMessage().warn("Dados incorretos!",
                                personRG.getNamePerson() + " com nº RG "
                                + varPerson.getObjRg().getRg()
                                + ", já possui o CPF " + listCpf.get(0).getCpf() + " registrado.");
                    } else {
                        getObjMessage().info("Novo cadastro identificado! %", "");
                    }
                } else {
                    getObjMessage().info("Novo cadastro identificado! #", "");
                }
            } else {
                getObjMessage().info("Novo cadastro identificado! @", "");
            }
        } catch (Exception e) {
            getObjMessage().error("BACK-END WARNING: ERRO private void collectListValues():", e.getMessage());
            System.out.println("BACK-END WARNING: ERRO private void collectListValues(): " + e);
        }
        return list;
    }

    public boolean checkDocumentPhysicalPerson(VariablesPerson varPerson, AddressControl addressControl) {
        listSearchPerson.clear();
        String hql = "";
        checkCPF = false;
        int lengthCPF = 0;
        lengthCPF = varPerson.getObjCpf().getCpf().length();
        if (lengthCPF > 0) {
            checkCPF = new PessoaCheckCPF().checkCPF(varPerson.getObjCpf());
            if (!checkCPF) {
                varPerson.getObjCpf().setCpf("");
                varPerson.getObjRg().setRg("");
                addressControl.getVar().setSelectUF(new FederationUnity());
            } 
        }
        checkRG = !varPerson.getObjRg().getRg().isEmpty();
        hql = buildHQL(checkCPF, checkRG, varPerson, addressControl);
        if (checkCPF && checkRG) {
            listSearchPerson = collectListValues(varPerson, checkCPF, checkRG, hql);
            addressControl.startAddressControl(listSearchPerson.isEmpty(), varPerson.getPerson());
        } else if (checkCPF) {
            listSearchPerson = collectListValues(varPerson, checkCPF, checkRG, hql);
            completeRgPossible(!listSearchPerson.isEmpty(), varPerson);
            addressControl.startAddressControl(listSearchPerson.isEmpty(), varPerson.getPerson());
        } else if (checkRG && lengthCPF == 0) {
            listSearchPerson = collectListValues(varPerson, checkCPF, checkRG, hql);
            completeCpfPossible(!listSearchPerson.isEmpty(), varPerson);
            addressControl.startAddressControl(listSearchPerson.isEmpty(), varPerson.getPerson());
        }
        return statusPerson;
    }

    public boolean checkDocumentLegalPerson() {
        return statusPerson;
    }

    public boolean isCheckCPF() {
        return checkCPF;
    }

    public boolean isCheckRG() {
        return checkRG;
    }

}
