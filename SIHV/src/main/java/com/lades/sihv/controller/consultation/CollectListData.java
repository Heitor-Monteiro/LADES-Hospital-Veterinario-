/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consultation;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author thiberius
 */
public class CollectListData implements Serializable {

    public void methodCollectListData1(List<?> tempList, List<ClientInitialData> comparisonlist) {
        for (Object[] obj : (List<Object[]>) tempList) {
            ClientInitialData temp = new ClientInitialData();
            temp.setRGHVsmallAnimal((Integer) obj[0]);
            temp.setPkAnimal((Integer) obj[1]);
            temp.setAnimalName((String) obj[2]);
            temp.setNameRaces((String) obj[3]);
            temp.setNameSpecies((String) obj[4]);
            temp.setPkPerson((Integer) obj[5]);
            temp.setNamePerson((String) obj[6]);
            temp.setCpfCnpj(new ReturnPhysicalPersonDocuments().methodReturnPhysicalPersonDocuments(temp.getPkPerson(), true));
            temp.setRg(new ReturnPhysicalPersonDocuments().methodReturnPhysicalPersonDocuments(temp.getPkPerson(), false));
            if (temp.getCpfCnpj().isEmpty() && temp.getRg().isEmpty()) {
            }
            comparisonlist.add(temp);
        }
    }

    public void methodCollectListData2(List<ClientInitialData> comparisonlist,
            List<ClientInitialData> listClientInitialData) {
        int qtd = comparisonlist.size();
        int index = 0;
        while (qtd != 0) {
            if (listClientInitialData.isEmpty()) {
                listClientInitialData.add(comparisonlist.get(0));
                comparisonlist.remove(0);
                qtd = comparisonlist.size();
                index = listClientInitialData.size();
            } else {
                boolean var = true;
                for (ClientInitialData obj : comparisonlist) {
                    if (Objects.equals(obj.getPkAnimal(), listClientInitialData.get(index - 1).getPkAnimal())) {
                        var = false;
                        comparisonlist.remove(0);
                        qtd = comparisonlist.size();
                        break;
                    }
                }
                if (var) {
                    listClientInitialData.add(comparisonlist.get(0));
                    comparisonlist.remove(0);
                    qtd = comparisonlist.size();
                    index = listClientInitialData.size();
                }
            }
        }
    }
}
