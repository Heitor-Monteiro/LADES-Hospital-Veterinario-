/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.editUserData;

import com.lades.sihv.controller.ListRenderedFields;
import com.lades.sihv.controller.person.VariablesPerson;
import java.io.Serializable;

/**
 *
 * @author thiberius
 */
public class GenerateFunctionsToCharge implements Serializable {

    public String methodGenerateFunctionsToCharge(ListRenderedFields viewFunctionsToCharge,
            VariablesPerson varPerson, String textFieldRecordNumber) {
        System.out.println("►►►►►►►►►►►►► "
                + "GenerateFunctionsToCharge > public void methodGenerateFunctionsToCharge()");
        try {
            viewFunctionsToCharge.startIndexListViewFields();
            switch (varPerson.getUser().getUserProfile()) {
                case "médico veterinário":
                    viewFunctionsToCharge.getListViewFields(0).setViewVariableBoolean(true);
                    viewFunctionsToCharge.getListViewFields(3).setViewVariableBoolean(true);
                    textFieldRecordNumber = "Número crmv:";
                    break;
                case "discente":
                    viewFunctionsToCharge.getListViewFields(1).setViewVariableBoolean(true);
                    textFieldRecordNumber = "Matrícula";
                    break;
                case "radiologista":
                    viewFunctionsToCharge.getListViewFields(2).setViewVariableBoolean(true);
                    textFieldRecordNumber = "Matrícula funcional";
                    break;
            }
            return textFieldRecordNumber;
        } catch (Exception e) {
            System.err.println("►►►►►►►►►►►►► ERRO generateFunctionsToCharge(): " + e);
            return null;
        }
    }
}
