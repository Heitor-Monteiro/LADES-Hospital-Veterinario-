/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.searchForAnimalSmall;

import com.lades.sihv.bean.*;
import com.lades.sihv.controller.VariablesSearch;
import java.text.ParseException;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author thiberius
 */
@ManagedBean(name = "MBsearchForAnimalSmall")
@ViewScoped

public class MBsearchForAnimalSmall extends AbstractBean {

    private VariablesSearch objVarSearch;
    private AnimalSearchSelectionEngine selectionEngine;
    private AnimalSearchEngine animalSearchEngine;

    @PostConstruct
    public void init() {
        System.out.println("►►►►►►►►►►►►► MBsearchForAnimalSmall initiated");
        objVarSearch = new VariablesSearch();
        selectionEngine = new AnimalSearchSelectionEngine(objVarSearch);
        animalSearchEngine = new AnimalSearchEngine(objVarSearch);
    }

    public void buttonToSearchForAnimalSmall() throws ParseException {
        animalSearchEngine.listAnimals();
    }

    // GETs & SETs -------------------------------------------------------------
    public VariablesSearch getObjVarSearch() {
        return objVarSearch;
    }

    public AnimalSearchSelectionEngine getSelectionEngine() {
        return selectionEngine;
    }

    public AnimalSearchEngine getAnimalSearchEngine() {
        return animalSearchEngine;
    }

}