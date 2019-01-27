/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consultationEntryControl;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.VariablesSearch;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

//@author thiberius
@ManagedBean(name = "MBconsultationEntryControl")
@ViewScoped

public class MBconsultationEntryControl extends AbstractBean {

    private boolean typeSearch;
    private VariablesSearch objVarSearch;
    private List<ConsultationEntryItem> listItens;
    private List<ConsultationEntryItem> filterlistItens;
    private ConsultationEntryItem selectItem;
    private DashConsultationEntry dash;
    private PerformedConsultation perfConsult;
    private SearchSchedulingEffective schedulingEffective;
    private SearchVeterinary searchVeterinary;
    private SearchOwner searchOwner;
    private SearchSmallAnimal searchSmallAnimal;
    private TotalProceduresApplied totalProceduresApplied;
    private String typeService[];

    @PostConstruct
    public void init() {
        System.out.println("►►►►►►►►►►►►► MBconsultationEntryControl initiated");
        listItens = new ArrayList();
        objVarSearch = new VariablesSearch();
        dash = new DashConsultationEntry();
        perfConsult = new PerformedConsultation();
        schedulingEffective = new SearchSchedulingEffective();
        searchVeterinary = new SearchVeterinary();
        searchOwner = new SearchOwner();
        searchSmallAnimal = new SearchSmallAnimal();
        totalProceduresApplied = new TotalProceduresApplied();
        typeService = new String[2];
        typeService[0] = "Nova consulta";
        typeService[1] = "Retorno";
    }

    public void searchConsultationEntry() {
        listItens.clear();
        perfConsult.listPerformedConsultation(objVarSearch, listItens);
        schedulingEffective.listSchedulingEffective(listItens);
        searchVeterinary.listSearchVeterinary(listItens);
        searchOwner.listSearchOwner(listItens);
        searchSmallAnimal.listSmallAnimal(listItens);
        totalProceduresApplied.listProceduresApplied(dash, listItens);
        //----------------------------------------------------------------------
        dash.setNumberOfConsultations(listItens.size());
        if (listItens.isEmpty()) {
            getObjMessage().warn("Não houve consultas efetivadas no intervalo estipulado.", "");
        } else {
            getObjMessage().info(listItens.size() + " itens encontrados no intervalo estipulado.", "");
        }
    }

    //-GETs e SETs--------------------------------------------------------------
    public List<ConsultationEntryItem> getListItens() {
        return listItens;
    }

    public List<ConsultationEntryItem> getFilterlistItens() {
        return filterlistItens;
    }

    public void setFilterlistItens(List<ConsultationEntryItem> filterlistItens) {
        this.filterlistItens = filterlistItens;
    }

    public VariablesSearch getObjVarSearch() {
        return objVarSearch;
    }

    public boolean isTypeSearch() {
        return typeSearch;
    }

    public void setTypeSearch(boolean typeSearch) {
        this.typeSearch = typeSearch;
        objVarSearch = new VariablesSearch();
    }

    public DashConsultationEntry getDash() {
        return dash;
    }

    public ConsultationEntryItem getSelectItem() {
        return selectItem;
    }

    public void setSelectItem(ConsultationEntryItem selectItem) {
        if (this.selectItem == null) {
            System.out.println("-----------------------null");
        }
        this.selectItem = selectItem;
        if (this.selectItem != null) {
            System.out.println("----------------------- not null");
        }
    }

    public String[] getTypeService() {
        return typeService;
    }
}
