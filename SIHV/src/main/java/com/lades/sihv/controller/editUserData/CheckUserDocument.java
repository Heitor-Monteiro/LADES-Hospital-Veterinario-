/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.editUserData;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.ModuleToCollectError;
import com.lades.sihv.controller.address.AddressControl;
import com.lades.sihv.controller.person.PessoaCheckCPF;
import com.lades.sihv.controller.person.VariablesPerson;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class CheckUserDocument extends AbstractBean {

    public void checkDocumentPhysicalPersonCPF(VariablesPerson varPerson, AddressControl addressControl) {
        System.out.println("►►►►►►►►►►►►► "
                + "CheckUserDocument > public void checkDocumentPhysicalPersonCPF()");
        try {
            List<?> list;
            if (new PessoaCheckCPF().checkCPF(varPerson.getObjCpf())) {
                list = getDaoGenerico().list("select c from Cpf c \n"
                        + "where \n"
                        + "c.cpf='" + varPerson.getObjCpf().getCpf() + "'");
                if (list != null && !list.isEmpty()) {
                    getObjMessage().warn("O número CPF já está sendo utilizado por outro registro!", varPerson.getObjCpf().getCpf());
                    varPerson.getObjCpf().setCpf("");
                    list.clear();
                }
            }
        } catch (Exception e) {
            System.err.println("►►►►►►►►►►►►► ERRO public void checkDocumentPhysicalPersonCPF(): " + e.toString());
            new ModuleToCollectError().erroPage500("CheckUserDocument > checkDocumentPhysicalPersonCPF", e.toString());
        }
    }

    public void checkDocumentPhysicalPersonRG(VariablesPerson varPerson, AddressControl addressControl) {
        System.out.println("►►►►►►►►►►►►► "
                + "CheckUserDocument > public void checkDocumentPhysicalPersonRG()");
        try {
            List<?> list;
            if (!varPerson.getObjRg().getRg().isEmpty()) {
                list = getDaoGenerico().
                        list("select r from Rg r, PhysicalPerson f, People p, "
                                + "Houses h, Address a, Neighborhood n, City c \n"
                                + "where \n"
                                + "r.rg='" + varPerson.getObjRg().getRg() + "' and \n"
                                + "r.physicalPerson.id.pkPhysicalPerson=f.id.pkPhysicalPerson and \n"
                                + "f.people.pkPerson=p.pkPerson and \n"
                                + "p.pkPerson=h.people.pkPerson and \n"
                                + "h.address.pkAddress=a.pkAddress and \n"
                                + "a.neighborhood.id.pkNeighborhood=n.id.pkNeighborhood and \n"
                                + "n.city.pkCity=c.pkCity and \n"
                                + "c.federationUnity.pkFederationUnity='"
                                + addressControl.getVar().getSelectUF().getPkFederationUnity() + "'");
                if (list != null && !list.isEmpty()) {
                    getObjMessage().warn("O número RG já está sendo utilizado por outro registro!", varPerson.getObjRg().getRg());
                    varPerson.getObjRg().setRg("");
                    list.clear();
                }
            }
        } catch (Exception e) {
            System.err.println("►►►►►►►►►►►►► ERRO public void checkDocumentPhysicalPersonRG(): " + e.toString());
            new ModuleToCollectError().erroPage500("CheckUserDocument > checkDocumentPhysicalPersonRG", e.toString());
        }
    }
}
