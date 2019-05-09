/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consultation;

import com.lades.sihv.controller.ListRenderedFields;
import com.lades.sihv.controller.RenderedFields;
import com.lades.sihv.controller.VariablesSearch;
import java.io.Serializable;

/**
 *
 * @author thiberius
 */
public class ConsultationSearchSelectionEngine implements Serializable {
    
    private final ListRenderedFields listRenderedFields;
    private final VariablesSearch objVarSearch;
    
    public ConsultationSearchSelectionEngine(VariablesSearch objVarSearch) {
        listRenderedFields = new ListRenderedFields(2);
        listRenderedFields.startIndexListViewFields();
        this.objVarSearch = objVarSearch;
    }
    
    public void selectSearchType() {
        objVarSearch.setTextSearch("");
        objVarSearch.setTextSearch2("");
        objVarSearch.setSearchMask("");
        objVarSearch.setDateInitial(null);
        objVarSearch.setDateEnd(null);
        objVarSearch.setMaxLength(0);
        switch (objVarSearch.getItemSearch()) {
            case "RGHV":
                objVarSearch.setMaxLength(14);
                listRenderedFields.getListViewFields(0).setViewVariableBoolean(false);
                break;
            case "NameAnimal":
                objVarSearch.setMaxLength(70);
                listRenderedFields.getListViewFields(0).setViewVariableBoolean(false);
                break;
            case "NameOwner":
                objVarSearch.setMaxLength(100);
                listRenderedFields.getListViewFields(0).setViewVariableBoolean(false);
                break;
            case "BetweenDates":
                objVarSearch.setMaxLength(30);
                listRenderedFields.getListViewFields(0).setViewVariableBoolean(true);
                break;
            default:
                break;
        }
    }
    
    public RenderedFields getViewRGHVandNameAnimal() {
        return listRenderedFields.getListViewFields(0);
    }
}
