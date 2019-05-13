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
            listClientInitialData.clear();
            hql = new GenerateJoin().methodGenerateJoin(objVarSearch);
            List<?> tempList = getDaoGenerico().list(hql);
            if (tempList.isEmpty()) {
                new EmptyClientListMessage().methodEmptyClientListMessage(objVarSearch);
            } else {
                getObjMessage().info(tempList.size() + " itens encontrados.", "");
                for (Object[] obj : (List<Object[]>) tempList) {
                    ClientInitialData temp = new ClientInitialData();
                    temp.setRGHVsmallAnimal((Integer) obj[0]);
                    temp.setPkAnimal((Integer) obj[1]);
                    temp.setAnimalName((String) obj[2]);
                    temp.setNameRaces((String) obj[3]);
                    temp.setNameSpecies((String) obj[4]);
                    temp.setPkPerson((Integer) obj[5]);
                    temp.setNamePerson((String) obj[6]);
                    temp.setCpfCnpj(returnPhysicalPersonDocuments(temp.getPkPerson(), true));
                    temp.setRg(returnPhysicalPersonDocuments(temp.getPkPerson(), false));
                    if (temp.getCpfCnpj().isEmpty() && temp.getRg().isEmpty()) {

                    }
                    listClientInitialData.add(temp);
                }
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
        }
    }

    private String returnPhysicalPersonDocuments(Integer pkPerson, boolean documentType) {
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

        }
        return var;
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
