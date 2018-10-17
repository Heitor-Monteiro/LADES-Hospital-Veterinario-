/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.animal;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.model.Genre;
import com.lades.sihv.model.Races;
import com.lades.sihv.model.Species;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class SearchForPossibleRaces extends AbstractBean {

    public void methodSearchForPossibleRaces(VariablesAnimal varAnimal) {
        try {
            for (Species objSpecies : varAnimal.getListObjSpecies()) {
                if (objSpecies.getNameSpecies().equals(varAnimal.getSelectTextSpecies())) {
                    varAnimal.setSelectObjSpecies(objSpecies);
                    break;
                }
            }
            for (Genre genre : varAnimal.getListObjGenre()) {
                if (genre.getId().getPkGenre() == varAnimal.getSelectObjSpecies().getId().getGenrePkGenre()) {
                    varAnimal.setSelectObjGenre(genre);
                    break;
                }
            }
            List<Races> listRaces = getDaoGenerico().list("select r from Species s, Races r \n"
                    + "where \n"
                    + "s.id.pkSpecies=r.species.id.pkSpecies and \n"
                    + "s.nameSpecies='" + varAnimal.getSelectTextSpecies() + "'");
            varAnimal.getListObjRaces().clear();
            varAnimal.getListObjRaces().addAll(listRaces);
            varAnimal.getListTextRaces().clear();
            for (Races listRace : varAnimal.getListObjRaces()) {
                varAnimal.getListTextRaces().add(listRace.getNameRaces());
            }
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO public void methodSearchForPossibleRaces():" + e);
        }
    }
}
