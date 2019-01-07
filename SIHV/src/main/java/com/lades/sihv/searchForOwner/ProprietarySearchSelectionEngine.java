/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.searchForOwner;

import com.lades.sihv.controller.ListRenderedFields;
import com.lades.sihv.controller.VariablesSearch;
import java.io.Serializable;

/**
 *
 * @author thiberius
 */
public class ProprietarySearchSelectionEngine implements Serializable {

    private ListRenderedFields listRenderedFields;
    private final VariablesSearch objVarSearch;

    public ProprietarySearchSelectionEngine(VariablesSearch objVarSearch) {
        listRenderedFields = new ListRenderedFields(2);
        this.objVarSearch = objVarSearch;
    }

    public void selectSearchType() {
        objVarSearch.setTextSearch("");
        objVarSearch.setSearchMask("");
        objVarSearch.setMaxLength(0);
        switch (objVarSearch.getItemSearch()) {
            case "CPF":
                objVarSearch.setSearchMask("999.999.999-99");
                objVarSearch.setMaxLength(14);
                break;
            case "RG":
                objVarSearch.setSearchMask("9999999");
                objVarSearch.setMaxLength(7);
                break;
            case "NameOwner":
                objVarSearch.setMaxLength(100);
                break;
            default:
                break;
        }
    }
}
