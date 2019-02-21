/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.searchForAnimalSmall;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.ModuleToCollectError;
import com.lades.sihv.model.Animals;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class DeclareDeathOfSmallAnimal extends AbstractBean {

    private AnimalDataGroup item;

    public DeclareDeathOfSmallAnimal() {
        item = new AnimalDataGroup();
        item.setAnimal(new Animals());
    }

    public void methodToSelectSmallAnimalStatus(AnimalDataGroup selectAnimalDataGroup) {
        item = selectAnimalDataGroup;
    }

    public void methodToUpdateDeathData(List<AnimalDataGroup> listAnimal) {
        try {
            getDaoGenerico().update(item.getAnimal());
            int num = listAnimal.indexOf(item);
            listAnimal.remove(num);
            listAnimal.add(num, item);
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO public void methodToUpdateDeathData(): " + e.toString());
            new ModuleToCollectError().erroPage500("DeclareDeathOfSmallAnimal > methodToUpdateDeathData", e.toString());
        }
    }

    // GETs & SETs ------------------------------------------------------------
    public AnimalDataGroup getItem() {
        return item;
    }

}
