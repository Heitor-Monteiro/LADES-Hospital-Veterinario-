/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.animal;

import com.lades.sihv.model.Animals;

/**
 *
 * @author thiberius
 */
public class CollectAnimalRghv {

    // primaryKeyCategory: SmallAnimal or ????? or ????? or ??????
    // typeAnimal: P or G or S or N
    public String methodCollectAnimalRghv(int primaryKeyCategory,
            String typeAnimal, Animals animal) {
        String rghv = "ERRO!!!";
        try {
            rghv = "" + primaryKeyCategory + typeAnimal
                    + (("" + animal.getRegistrationDate()).substring(0, 4));
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO public String methodCollectAnimalRghv():" + e);
        }
        return rghv;
    }
}
