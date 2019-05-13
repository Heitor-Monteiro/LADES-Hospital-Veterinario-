/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consultation;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.ModuleToCollectError;
import com.lades.sihv.controller.RenderedFields;
import com.lades.sihv.controller.VariablesSearch;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author thiberius
 */
@ManagedBean(name = "MBconsultation")
@ViewScoped

public class MBconsultation extends AbstractBean {

    private RenderedFields typeOfAction;
    private VariablesSearch objVarSearch;
    private ConsultationSearchSelectionEngine selectionEngine;
    private SearchOfClients searchOfClients;
    private SearchConsultationData searchConsultationData;

    @PostConstruct
    public void init() {
        System.out.println("►►►►►►►►►►►►► MBconsultation initiated");
        try {
            typeOfAction = new RenderedFields();
            objVarSearch = new VariablesSearch();
            selectionEngine = new ConsultationSearchSelectionEngine(objVarSearch);
            searchOfClients = new SearchOfClients();
            searchConsultationData = new SearchConsultationData();
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO public void init(): " + e.toString());
            new ModuleToCollectError().erroPage500("MBconsultation > init", e.toString());
        }
    }

    public void methodSearchOfClients() {
        searchOfClients.methodSearchOfClients(objVarSearch);
    }

    public String onFlowProcess(FlowEvent event) {
        String var;
        if (searchOfClients.getSelectClientInitialData() == null) {
            var = "etapa1";
            getObjMessage().warn("Selecione um cliente na tabela!", "");
        } else {
            var = event.getNewStep();
            searchConsultationData.methodSearchOfConsultation(searchOfClients.getSelectClientInitialData().getPkAnimal());
            
        }
        return var;
    }

    //-GETs e SETs--------------------------------------------------------------
    public RenderedFields getTypeOfAction() {
        return typeOfAction;
    }

    public VariablesSearch getObjVarSearch() {
        return objVarSearch;
    }

    public ConsultationSearchSelectionEngine getSelectionEngine() {
        return selectionEngine;
    }

    public SearchOfClients getSearchOfClients() {
        return searchOfClients;
    }

    public SearchConsultationData getSearchConsultationData() {
        return searchConsultationData;
    }
}
