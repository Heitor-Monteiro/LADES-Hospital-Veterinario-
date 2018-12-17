/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.animal;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.model.Races;
import com.lades.sihv.model.RacesId;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class SaveScientificClassifications extends AbstractBean {

    public void saveNewBreed(VariablesAnimal varAnimal) {
        try {
            if (varAnimal.StatusRace().isViewVariableBoolean()) {
                varAnimal.createRacesId();
                getDaoGenerico().save(varAnimal.getSelectObjRaces());
                List<Races> listRaces = getDaoGenerico().list("select r from Species s, Races r \n"
                        + "where \n"
                        + "s.id.pkSpecies=r.species.id.pkSpecies and \n"
                        + "s.nameSpecies='" + varAnimal.getSelectTextSpecies() + "' and "
                        + "r.id.pkRaces=(select max(r.id.pkRaces) from Species s, Races r \n"
                        + "where \n"
                        + "s.id.pkSpecies=r.species.id.pkSpecies and \n"
                        + "s.nameSpecies='" + varAnimal.getSelectTextSpecies() + "')");
                System.out.println(".-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-: List" + listRaces.size());
                varAnimal.setSelectObjRaces(listRaces.get(0));
            }
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO public void saveNewBreed():" + e);
        }

    }

}
