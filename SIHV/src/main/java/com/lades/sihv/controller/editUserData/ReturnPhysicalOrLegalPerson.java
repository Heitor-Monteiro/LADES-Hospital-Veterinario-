/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.editUserData;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.ModuleToCollectError;
import com.lades.sihv.controller.person.VariablesPerson;
import com.lades.sihv.model.Cpf;
import com.lades.sihv.model.LegalPerson;
import com.lades.sihv.model.PhysicalPerson;
import com.lades.sihv.model.Rg;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class ReturnPhysicalOrLegalPerson extends AbstractBean {

    public void methodOfReturnOfPhysicalOrLegalPerson(VariablesPerson varPerson) {
        System.out.println("►►►►►►►►►►►►► "
                + "ReturnPhysicalOrLegalPerson "
                + "> public void methodOfReturnOfPhysicalOrLegalPerson()");
        try {
            List<?> list = getDaoGenerico()
                    .list("select f from PhysicalPerson f, People p \n"
                            + "where \n"
                            + "p.pkPerson=f.people.pkPerson and \n"
                            + "f.people.pkPerson='" + varPerson.getPerson().getPkPerson() + "' ");
            if (list != null && !list.isEmpty()) {
                varPerson.setPhysicalPerson((PhysicalPerson) list.get(0));
                //-return CPF---------------------------------------------------
                list.clear();
                list = getDaoGenerico()
                        .list("select c from PhysicalPerson f, Cpf c \n"
                                + "where \n"
                                + "f.id.pkPhysicalPerson=c.physicalPerson.id.pkPhysicalPerson and \n"
                                + "f.id.pkPhysicalPerson='" + varPerson.getPhysicalPerson().getId().getPkPhysicalPerson() + "'");
                if (list != null && !list.isEmpty()) {
                    varPerson.setObjCpf((Cpf) list.get(0));
                }
                //-return RG----------------------------------------------------
                list.clear();
                list = getDaoGenerico()
                        .list("select r from PhysicalPerson f, Rg r \n"
                                + "where \n"
                                + "f.id.pkPhysicalPerson=r.physicalPerson.id.pkPhysicalPerson and \n"
                                + "f.id.pkPhysicalPerson='" + varPerson.getPhysicalPerson().getId().getPkPhysicalPerson() + "'");
                if (list != null && !list.isEmpty()) {
                    varPerson.setObjRg((Rg) list.get(0));
                }
            } else {
                list = getDaoGenerico()
                        .list("select j from LegalPerson j, People p \n"
                                + "where \n"
                                + "p.pkPerson=j.people.pkPerson and \n"
                                + "j.people.pkPerson='" + varPerson.getPerson().getPkPerson() + "' ");
                varPerson.setLegalPerson((LegalPerson) list.get(0));
            }
        } catch (Exception e) {
            System.err.println("►►►►►►►►►►►►► ERRO public void methodOfReturnOfPhysicalOrLegalPerson(): " + e.toString());
            new ModuleToCollectError().erroPage500("ReturnPhysicalOrLegalPerson > methodOfReturnOfPhysicalOrLegalPerson", e.toString());
        }
    }
}
