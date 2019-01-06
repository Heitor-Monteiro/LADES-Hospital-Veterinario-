/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.searchForOwner;

import com.lades.sihv.bean.*;
import com.lades.sihv.controller.VariablesSearch;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author thiberius
 */
@ManagedBean(name = "MBsearchForOwner")
@ViewScoped

public class MBsearchForOwner extends AbstractBean {

    private VariablesSearch objVarSearch;
    private ProprietarySearchSelectionEngine proprietarySearchSelectionEngine;
    
    @PostConstruct
    public void init() {
        System.out.println("►►►►►►►►►►►►► MBsearchForOwner initiated");
        objVarSearch = new VariablesSearch();
        proprietarySearchSelectionEngine = new ProprietarySearchSelectionEngine(objVarSearch);
    }

    public void buttonToSearchForOwner() {
        
        
    }
    
    // GETs & SETs -------------------------------------------------------------

    public VariablesSearch getObjVarSearch() {
        return objVarSearch;
    }

    public ProprietarySearchSelectionEngine getProprietarySearchSelectionEngine() {
        return proprietarySearchSelectionEngine;
    }
    
}
