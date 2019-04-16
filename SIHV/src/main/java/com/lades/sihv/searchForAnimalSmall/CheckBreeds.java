/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.searchForAnimalSmall;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.BeautyText;
import com.lades.sihv.controller.ModuleToCollectError;
import com.lades.sihv.model.Races;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class CheckBreeds extends AbstractBean {

    public boolean methodCheckBreeds(List<Races> listObjRaces,
            AnimalDataGroup item,
            boolean statusRace) {
        try {
            for (Races objRace : listObjRaces) {
                if (item.getSelectTextBreed().equals(objRace.getNameRaces())) {
                    System.out.println("-----------------" + item.getSelectTextBreed() + "--" + objRace.getNameRaces());
                    item.setRace(objRace);
                    System.out.println("►►►►►►►►►►►►► Raça escolhida:" + objRace.getNameRaces());
                    statusRace = false;
                    break;
                } else {
                    item.setRace(new Races());
                    statusRace = true;
                }
            }
            if (statusRace) {
                item.getRace().setNameRaces(new BeautyText().Captalizador(item.getSelectTextBreed()));
                item.setSelectTextBreed(item.getRace().getNameRaces());
                getObjMessage().info("Nova raça para registro",
                        item.getSpecies().getNameSpecies() + ":"
                        + item.getRace().getNameRaces());
            }
            return statusRace;
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO public void methodCheckBreeds(): " + e.toString());
            new ModuleToCollectError().erroPage500("CheckBreeds > methodCheckBreeds", e.toString());
            return statusRace;
        }
    }

}
