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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author thiberius
 */
public class VerifyPersonDocument extends AbstractBean {

    private List<?> listSearchPerson = new ArrayList<>();
    private boolean newPerson = false;
    private boolean newRG = false;
    private boolean newCPF = false;
    private boolean checkCPF = false;
    private boolean checkRG = false;
    private boolean checkCNPJ = false;

    private String returnHqlCPF(Cpf objCpf) {
        return " and \n"
                + "( f.id.peoplePkPerson=c.physicalPerson.id.peoplePkPerson and \n" //OK----------------
                + "f.id.pkPhysicalPerson=c.physicalPerson.id.pkPhysicalPerson and \n" //OK----------------
                + "c.cpf='" + objCpf.getCpf() + "' )";
    }

    private String returnHqlRG(Rg objRg, FederationUnity selectUF) {
        return " and \n"
                + "( f.id.peoplePkPerson=r.physicalPerson.id.peoplePkPerson and \n" //OK----------------
                + "f.id.pkPhysicalPerson=r.physicalPerson.id.pkPhysicalPerson and \n" //OK----------------
                + "r.rg='" + objRg.getRg() + "' ) and \n" //OK----------------
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
                + "p.pkPerson=f.id.peoplePkPerson and \n" //OK----------------
                + "f.id.pkPhysicalPerson=c.physicalPerson.id.pkPhysicalPerson and \n" //OK----------------
                + "f.id.peoplePkPerson=c.physicalPerson.id.peoplePkPerson and \n" //OK----------------
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
                + "p.pkPerson=f.id.peoplePkPerson and \n" //OK----------------
                + "f.id.pkPhysicalPerson=r.physicalPerson.id.pkPhysicalPerson and \n" //OK----------------
                + "f.id.peoplePkPerson=r.physicalPerson.id.peoplePkPerson and \n" //OK----------------
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
                    + "p.pkPerson=f.id.peoplePkPerson" + hqlCPF + hqlRG; //OK----------------
        } else if (checkCPF) {
            objReturn = ", c";
            entitys += ", Cpf c \n";
            hqlCPF = returnHqlCPF(varPerson.getObjCpf());
            hql = "select p, f" + objReturn + " from People p, PhysicalPerson f " + entitys
                    + "where \n"
                    + "p.pkPerson=f.id.peoplePkPerson" + hqlCPF; //OK----------------
        } else if (checkRG) {
            objReturn = ", r";
            entitys += ", Rg r, FederationUnity uf, City cy, Neighborhood n, Address a, Houses h \n";
            hqlRG = returnHqlRG(varPerson.getObjRg(), addressControl.getVar().getSelectUF());
            hql = "select p, f" + objReturn + " from People p, PhysicalPerson f " + entitys
                    + "where \n"
                    + "p.pkPerson=f.id.peoplePkPerson" + hqlRG; //OK----------------
        }
        return hql;
    }

    private List<?> searchRgOrCpfUsingPerson(People person, String CpfOrRg) {
        return getDaoGenerico().list("select x from People p, PhysicalPerson f, " + CpfOrRg + " x \n"
                + "where \n"
                + "p.pkPerson=f.id.peoplePkPerson and \n" //OK----------------
                + "f.id.pkPhysicalPerson=x.physicalPerson.id.pkPhysicalPerson and \n" //OK----------------
                + "f.id.peoplePkPerson=x.physicalPerson.id.peoplePkPerson and \n" //OK----------------
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
//                varPerson.setObjRg(new Rg());
            }
        } catch (Exception e) {
            getObjMessage().error("BACK-END WARNING: ERRO private void completeRgPossible():", e.getMessage());
            System.out.println("►►►►►►►►►►►►► ERRO private void completeRgPossible(): " + e);
        }
    }

    private void completeCpfPossible(boolean checkPerson, VariablesPerson varPerson) {
        try {
            if (checkPerson) {
                List<Cpf> list = (List<Cpf>) searchRgOrCpfUsingPerson(varPerson.getPerson(), "Cpf");
                if (!list.isEmpty()) {
                    varPerson.setObjCpf(list.get(0));
                    getObjMessage().info("Registro encontrado", varPerson.getPerson().getNamePerson());
                } else {
                    getObjMessage().warn("Registro incompleto identificado!",
                            varPerson.getPerson().getNamePerson() + " está sem CPF no sistema.");
                }
            } else {
//                varPerson.setObjCpf(new Cpf());
            }
        } catch (Exception e) {
            getObjMessage().error("BACK-END WARNING: ERRO private void completeCpfPossible():", e.getMessage());
            System.out.println("►►►►►►►►►►►►► ERRO private void completeCpfPossible(): " + e);
        }
    }

    private void searchForOwner(VariablesPerson varPerson) {
        List<Owners> listOwner = getDaoGenerico().list("select o from People p, Owners o \n"
                + "where \n"
                + "p.pkPerson=o.people.pkPerson and \n"
                + "p.logicalExclusion='0' and\n"
                + "o.people.pkPerson='" + varPerson.getPerson().getPkPerson() + "'");
        varPerson.setOwner(listOwner.get(0));
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
                    searchForOwner(varPerson);
                    newPerson = false;
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
                    newPerson = false;
                    newCPF = false;
                    newRG = false;
                    if (!Objects.equals(personCPF.getPkPerson(), personRG.getPkPerson())) {
                        getObjMessage().warn("Individuos diferentes!", "Utilize documentos que pertensa a somente um indivíduo");
                        getObjMessage().warn("O CPF " + varPerson.getObjCpf().getCpf(), "Pertence a " + personCPF.getNamePerson());
                        getObjMessage().warn("O RG " + varPerson.getObjRg().getRg(), "Pertence a " + personRG.getNamePerson());
                    } else {
                        getObjMessage().warn("Novo evento identificado! %",
                                "Contacte o analista de sistemas para informar o ocorrido.");
                    }
                } else if (personCPF.getPkPerson() != null) {
                    newPerson = false;
                    List<Rg> listRg = (List<Rg>) searchRgOrCpfUsingPerson(personCPF, "Rg");
                    varPerson.setPerson(personCPF);
                    if (!listRg.isEmpty()) {
                        getObjMessage().warn("Dados incorretos!",
                                personCPF.getNamePerson() + " com nº CPF "
                                + varPerson.getObjCpf().getCpf()
                                + ", já possui o RG " + listRg.get(0).getRg() + " registrado.");
                        varPerson.setObjRg(listRg.get(0));
                    } else {
                        getObjMessage().info("Novo documento identificado!",
                                "O RG " + varPerson.getObjRg().getRg()
                                + " será adicionado ao registro de "
                                + varPerson.getPerson().getNamePerson());
                        searchForOwner(varPerson);
                        newRG = true;
                    }
                } else if (personRG.getPkPerson() != null) {
                    newPerson = false;
                    List<Cpf> listCpf = (List<Cpf>) searchRgOrCpfUsingPerson(personRG, "Cpf");
                    varPerson.setPerson(personRG);
                    if (!listCpf.isEmpty()) {
                        getObjMessage().warn("Dados incorretos!",
                                personRG.getNamePerson() + " com nº RG "
                                + varPerson.getObjRg().getRg()
                                + ", já possui o CPF " + listCpf.get(0).getCpf() + " registrado.");
                        varPerson.setObjCpf(listCpf.get(0));
                    } else {
                        getObjMessage().info("Novo documento identificado!",
                                "O CPF " + varPerson.getObjCpf().getCpf()
                                + " será adicionado ao registro de "
                                + varPerson.getPerson().getNamePerson());
                        searchForOwner(varPerson);
                        newCPF = true;
                    }
                } else {
                    getObjMessage().info("Novo cadastro identificado! #", "");
                    newPerson = true;
                    newCPF = true;
                    newRG = true;
                }
            } else {
                getObjMessage().info("Novo cadastro identificado! @", "");
                newPerson = true;
                if (checkCPF) {
                    newCPF = true;
                } else if (checkRG) {
                    newRG = true;
                }

            }
        } catch (Exception e) {
            getObjMessage().error("BACK-END WARNING: ERRO private void collectListValues():", e.getMessage());
            System.out.println("►►►►►►►►►►►►► ERRO private void collectListValues(): " + e);
        }
        return list;
    }

    public boolean checkDocumentPhysicalPerson(VariablesPerson varPerson, AddressControl addressControl) {
        listSearchPerson.clear();
        String hql = "";
        newPerson = false;
        newCPF = false;
        newRG = false;
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
            addressControl.startAddressControl(newPerson, varPerson.getPerson());
        } else if (checkCPF) {
            listSearchPerson = collectListValues(varPerson, checkCPF, checkRG, hql);
            completeRgPossible(!listSearchPerson.isEmpty(), varPerson);
            addressControl.startAddressControl(newPerson, varPerson.getPerson());
        } else if (checkRG && lengthCPF == 0) {
            listSearchPerson = collectListValues(varPerson, checkCPF, checkRG, hql);
            completeCpfPossible(!newPerson, varPerson);
            addressControl.startAddressControl(newPerson, varPerson.getPerson());
        }
        return newPerson;
    }

    //GETs & SETs---------------------------------------------------------------
    public boolean isNewPerson() {
        return newPerson;
    }

    public boolean isCheckCPF() {
        return checkCPF;
    }

    public boolean isCheckRG() {
        return checkRG;
    }

    public boolean isNewRG() {
        return newRG;
    }

    public boolean isNewCPF() {
        return newCPF;
    }

}
