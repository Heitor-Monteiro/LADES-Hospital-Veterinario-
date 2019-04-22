/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.searchForAnimalSmall;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.ModuleToCollectError;
import com.lades.sihv.controller.address.AddressControl;
import com.lades.sihv.controller.person.PessoaCheckCPF;
import com.lades.sihv.controller.person.VariablesPerson;
import com.lades.sihv.model.Cpf;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class CheckOwnerDocument extends AbstractBean {

    public void checkDocumentPhysicalPersonCPF(OwnerDataGroup ownerDataGroup) {
        System.out.println("►►►►►►►►►►►►► "
                + "CheckOwnerDocument > public void checkDocumentPhysicalPersonCPF()");
        try {
            List<?> list;
            Cpf tempObj = new Cpf();
            tempObj.setCpf(ownerDataGroup.getCpfCnpj());
            boolean var = new PessoaCheckCPF().checkCPF(tempObj);
            if (var) {
                list = getDaoGenerico().list("select c from Cpf c \n"
                        + "where \n"
                        + "c.cpf='" + ownerDataGroup.getCpfCnpj() + "'");
                if (list != null && !list.isEmpty()) {
                    getObjMessage().warn("O número CPF já está sendo utilizado "
                            + "por outro registro!", ownerDataGroup.getCpf().getCpf());
                    ownerDataGroup.setCpfCnpj(ownerDataGroup.getCpf().getCpf());
                    list.clear();
                }
            } else {
                ownerDataGroup.setCpfCnpj(ownerDataGroup.getCpf().getCpf());
            }
        } catch (Exception e) {
            System.err.println("►►►►►►►►►►►►► ERRO public void checkDocumentPhysicalPersonCPF(): " + e.toString());
            new ModuleToCollectError().erroPage500("CheckOwnerDocument > checkDocumentPhysicalPersonCPF", e.toString());
        }
    }

    public void checkDocumentPhysicalPersonRG(OwnerDataGroup ownerDataGroup) {
        System.out.println("►►►►►►►►►►►►► "
                + "CheckUserDocument > public void checkDocumentPhysicalPersonRG()");
//        try {
//            List<?> list;
//            if (!ownerDataGroup.getRg().getRg().isEmpty()) {
//                list = getDaoGenerico().
//                        list("select r from Rg r, PhysicalPerson f, People p, "
//                                + "Houses h, Address a, Neighborhood n, City c \n"
//                                + "where \n"
//                                + "r.rg='" + ownerDataGroup.getRg().getRg() + "' and \n"
//                                + "r.physicalPerson.id.pkPhysicalPerson=f.id.pkPhysicalPerson and \n"
//                                + "f.people.pkPerson=p.pkPerson and \n"
//                                + "p.pkPerson=h.people.pkPerson and \n"
//                                + "h.address.pkAddress=a.pkAddress and \n"
//                                + "a.neighborhood.id.pkNeighborhood=n.id.pkNeighborhood and \n"
//                                + "n.city.pkCity=c.pkCity and \n"
//                                + "c.federationUnity.pkFederationUnity='"
//                                + addressControl.getVar().getSelectUF().getPkFederationUnity() + "'");
//                if (list != null && !list.isEmpty()) {
//                    getObjMessage().warn("O número RG já está sendo utilizado por outro registro!", varPerson.getObjRg().getRg());
//                    varPerson.getObjRg().setRg("");
//                    list.clear();
//                }
//            }
//        } catch (Exception e) {
//            System.err.println("►►►►►►►►►►►►► ERRO public void checkDocumentPhysicalPersonRG(): " + e.toString());
//            new ModuleToCollectError().erroPage500("CheckOwnerDocument > checkDocumentPhysicalPersonRG", e.toString());
//        }
    }
}
