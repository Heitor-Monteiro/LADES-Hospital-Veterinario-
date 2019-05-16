/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consultation;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.ModuleToCollectError;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class ReturnPhysicalPersonDocuments extends AbstractBean {

    public String methodReturnPhysicalPersonDocuments(Integer pkPerson, boolean documentType) {
        String var = "";
        try {
            String entity = "Rg";
            String column = "rg";
            if (documentType) {
                entity = "Cpf";
                column = "cpf";
            }
            List<?> list = getDaoGenerico().list("select c." + column + " from \n"
                    + "People p, PhysicalPerson ph, " + entity + " c \n"
                    + "where \n"
                    + "p.pkPerson=ph.people.pkPerson and \n"
                    + "ph.id.pkPhysicalPerson=c.physicalPerson.id.pkPhysicalPerson and \n"
                    + "p.pkPerson='" + pkPerson + "'");
            if (!list.isEmpty()) {
                var = "" + list.get(0);
            }
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO public String methodReturnPhysicalPersonDocuments(): " + e.toString());
            new ModuleToCollectError().erroPage500("ReturnPhysicalPersonDocuments > methodReturnPhysicalPersonDocuments", e.toString());
        }
        return var;
    }
}
