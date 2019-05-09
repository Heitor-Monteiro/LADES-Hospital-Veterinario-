/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consultation;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.ModuleToCollectError;
import com.lades.sihv.controller.VariablesSearch;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    private void generateJoin(VariablesSearch objVarSearch) {
        try {
            String join = "";
            switch (objVarSearch.getItemSearch()) {
                case "RGHV":
                    join = "and \n s.pkSmallAnimal='" + objVarSearch.getTextSearch() + "'";
                    break;
                case "NameAnimal":
                    join = "and \n a.animalName like '%" + objVarSearch.getTextSearch() + "%'";
                    break;
                case "NameOwner":
                    join = "and \n p.namePerson like '%" + objVarSearch.getTextSearch() + "%'";
                    break;
                case "BetweenDates":
                    DateFormat formatUS = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                    String x = formatUS.format(objVarSearch.getDateInitial()).substring(0, 11) + "01:00:00";
                    String y = formatUS.format(objVarSearch.getDateEnd()).substring(0, 11) + "23:59:59";
                    join = "and \n a.registrationDate>='" + x + "' and \n "
                            + "a.registrationDate<='" + y + "'";
                    break;
            }
            hql = "select \n"
                    + "s.pkSmallAnimal, \n"
                    + "a.pkAnimal, a.animalName, \n"
                    + "r.nameRaces, sp.nameSpecies, \n"
                    + "p.pkPerson, p.namePerson \n"
                    + "from People p, Owners o, OwnersHasAnimals oh, Animals a, SmallAnimal s, Races r, Species sp \n"
                    + "where \n"
                    + "p.pkPerson=o.people.pkPerson and \n"
                    + "o.pkOwner=oh.owners.pkOwner and \n"
                    + "oh.animals.pkAnimal=a.pkAnimal and \n"
                    + "a.pkAnimal=s.animals.pkAnimal and \n"
                    + "r.id.pkRaces=s.races.id.pkRaces and \n"
                    + "sp.id.pkSpecies=r.species.id.pkSpecies \n " + join;
        } catch (Exception e) {
        }
    }

    public void methodSearchOfClients(VariablesSearch objVarSearch) {
        try {
            listClientInitialData.clear();
            generateJoin(objVarSearch);
            List<?> tempList = getDaoGenerico().list(hql);
            if (tempList.isEmpty()) {
                String x = "";
                String y = objVarSearch.getTextSearch();
                switch (objVarSearch.getItemSearch()) {
                    case "RGHV":
                        x = "RGHV";
                        break;
                    case "NameAnimal":
                        x = "Nome do animal";
                        break;
                    case "NameOwner":
                        x = "Nome do proprietário";
                        break;
                    case "BetweenDates":
                        DateFormat formatUS = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                        y = formatUS.format(objVarSearch.getDateInitial()).substring(0, 11) + " a ";
                        y = y + formatUS.format(objVarSearch.getDateEnd()).substring(0, 11);
                        x = "Entre datas";
                        break;
                }
                getObjMessage().warn("Listagem vazia! Não foi encontrado '"
                        + x + "' para a seguinte ocorrência: "
                        + y + ".", "");
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
            }

        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO public void methodSearchOfClients(): " + e.toString());
            new ModuleToCollectError().erroPage500("SearchOfClients > methodSearchOfClients", e.toString());
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
