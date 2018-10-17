/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.animal;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.BeautyText;
import com.lades.sihv.model.Races;

/**
 *
 * @author thiberius
 */
public class CheckBreeds extends AbstractBean {

    public void methodCheckBreeds(VariablesAnimal varAnimal) {
        try {
            for (Races objRace : varAnimal.getListObjRaces()) {
                if (varAnimal.getSelectTextRaces().equals(objRace.getNameRaces())) {
                    varAnimal.StatusRace().setViewVariableBoolean(false);
                    varAnimal.setSelectObjRaces(objRace);
                    System.out.println("►►►►►►►►►►►►► Raça escolhida:" + objRace.getNameRaces());
                    break;
                } else {
                    varAnimal.StatusRace().setViewVariableBoolean(true);
                }
            }
            if (varAnimal.StatusRace().isViewVariableBoolean()) {
                varAnimal.setSelectTextRaces(new BeautyText().Captalizador(varAnimal.getSelectTextRaces()));
                getObjMessage().info("Nova raça para registro",
                        varAnimal.getSelectTextSpecies() + ":"
                        + varAnimal.getSelectTextRaces());
                System.out.println("►►►►►►►►►►►►► Nova Raça:"
                        + varAnimal.getSelectTextSpecies() + ":"
                        + varAnimal.getSelectTextRaces());
            }
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO public void methodCheckBreeds():" + e);
        }
    }
}
