/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.searchForAnimalSmall;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.ModuleToCollectError;
import com.lades.sihv.model.Races;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class BreedListing extends AbstractBean {

    public void methodBreedListing(List<Races> listObjRaces, AnimalDataGroup item) {
        try {
            listObjRaces.clear();
            List<Races> listRaces = getDaoGenerico().list(
                    "select r from Species s, Races r \n"
                    + "where \n"
                    + "s.id.pkSpecies=r.species.id.pkSpecies and \n"
                    + "s.nameSpecies='" + item.getSelectTextSpecies() + "'");
            if (!listRaces.isEmpty()) {
                listObjRaces.addAll(listRaces);
            }
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO private void methodBreedListing(): " + e.toString());
            new ModuleToCollectError().erroPage500("BreedListing > methodBreedListing", e.toString());
        }
    }
}
