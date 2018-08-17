/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.scheduleConsulta;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.ListRenderedFields;
import com.lades.sihv.controller.VariablesSearch;
import com.lades.sihv.model.Scheduling;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class OtherMethods extends AbstractBean {

    public void actionsClientRegisteredORnew(Scheduling schedule, ListRenderedFields listRenderedFields, VariablesSearch variablesSearch) {
        if (schedule.getRegisterClient() != null) {
            switch (schedule.getRegisterClient()) {
                case "Cadastrado":
                    listRenderedFields.getListViewFields(1).setViewVariableBoolean(true);
                    listRenderedFields.getListViewFields(4).setViewVariableBoolean(false);
                    listRenderedFields.getListViewFields(5).setViewVariableBoolean(true);
                    listRenderedFields.getListViewFields(6).setViewVariableBoolean(true);
                    break;
                case "Novo":
                    listRenderedFields.getListViewFields(4).setViewVariableBoolean(true);
                    listRenderedFields.getListViewFields(1).setViewVariableBoolean(false);
                    listRenderedFields.getListViewFields(2).setViewVariableBoolean(false);
                    listRenderedFields.getListViewFields(3).setViewVariableBoolean(false);
                    listRenderedFields.getListViewFields(6).setViewVariableBoolean(false);
                    variablesSearch.setItemSearch("");
                    break;
                default:
                    break;
            }
        }
    }

    private void fillFieldsCPFandRGorRGHV(VariablesSearch variablesSearch,
            ListRenderedFields listRenderedFields, String searchMask,
            String placeholderField, int maxLength, boolean CPForRG, boolean RGHV) {
        variablesSearch.setSearchMask(searchMask);
        variablesSearch.setPlaceholderField(placeholderField);
        variablesSearch.setMaxLength(maxLength);
        variablesSearch.setTextSearch("");
        listRenderedFields.getListViewFields(2).setViewVariableBoolean(CPForRG);
        listRenderedFields.getListViewFields(3).setViewVariableBoolean(RGHV);
    }

    public void methodViewFieldsCPFandRGorRGHV(VariablesSearch variablesSearch, ListRenderedFields listRenderedFields) {

        if (variablesSearch.getItemSearch() != null) {
            switch (variablesSearch.getItemSearch()) {
                case "CPF":
                    fillFieldsCPFandRGorRGHV(variablesSearch, listRenderedFields, "999.999.999-99", "999.999.999-99", 14, true, false);
                    break;
                case "CNPJ":
                    fillFieldsCPFandRGorRGHV(variablesSearch, listRenderedFields, "99.999.999/9999-99", "99.999.999/9999-99", 18, true, false);
                    break;
                case "RG":
                    fillFieldsCPFandRGorRGHV(variablesSearch, listRenderedFields, "", "9738162", 20, true, false);
                    break;
                case "RGHV":
                    listRenderedFields.getListViewFields(3).setViewVariableBoolean(true);
                    listRenderedFields.getListViewFields(2).setViewVariableBoolean(false);
                    break;
                default:
                    listRenderedFields.getListViewFields(3).setViewVariableBoolean(false);
                    listRenderedFields.getListViewFields(2).setViewVariableBoolean(false);
                    break;
            }
        }
    }

}
