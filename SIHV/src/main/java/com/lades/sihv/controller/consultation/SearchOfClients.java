/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consultation;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.ModuleToCollectError;
import com.lades.sihv.controller.VariablesSearch;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class SearchOfClients extends AbstractBean {

    private ClientInitialData selectClientInitialData;
    private final List<ClientInitialData> listClientInitialData;
    private List<ClientInitialData> filterClientInitialData;
    private String hql;

    public SearchOfClients() {
        listClientInitialData = new ArrayList();
    }

    public void methodSearchOfClients(VariablesSearch objVarSearch) {
        try {
            List<ClientInitialData> comparisonlist = new ArrayList();
            listClientInitialData.clear();
            hql = new GenerateJoin().methodGenerateJoin(objVarSearch);
            List<?> tempList = getDaoGenerico().list(hql);
            if (tempList.isEmpty()) {
                new EmptyClientListMessage().methodEmptyClientListMessage(objVarSearch);
            } else {
                CollectListData obj = new CollectListData();
                obj.methodCollectListData1(tempList, comparisonlist);
                obj.methodCollectListData2(comparisonlist, listClientInitialData);
                getObjMessage().info(listClientInitialData.size() + " itens encontrados.", "");
                cleanTableFilter(listClientInitialData, filterClientInitialData);
            }
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO public void methodSearchOfClients(): " + e.toString());
            new ModuleToCollectError().erroPage500("SearchOfClients > methodSearchOfClients", e.toString());
        }
    }

    private void cleanTableFilter(List<ClientInitialData> listClientInitialData,
            List<ClientInitialData> filterClientInitialData) {
        try {
            if (filterClientInitialData != null) {
                filterClientInitialData.clear();
                filterClientInitialData.addAll(listClientInitialData);
            }
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO private void cleanTableFilter(): " + e.toString());
            new ModuleToCollectError().erroPage500("SearchOfClients > cleanTableFilter", e.toString());
        }
    }

    // GETs & SETs -------------------------------------------------------------
    public List<ClientInitialData> getListClientInitialData() {
        return listClientInitialData;
    }

    public ClientInitialData getSelectClientInitialData() {
        return selectClientInitialData;
    }

    public void setSelectClientInitialData(ClientInitialData selectClientInitialData) {
        this.selectClientInitialData = selectClientInitialData;
    }

    public List<ClientInitialData> getFilterClientInitialData() {
        return filterClientInitialData;
    }

    public void setFilterClientInitialData(List<ClientInitialData> filterClientInitialData) {
        this.filterClientInitialData = filterClientInitialData;
    }
}
