/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.animal;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.BeautyText;
import com.lades.sihv.controller.person.VariablesPerson;
import com.lades.sihv.model.OwnersHasAnimals;
import com.lades.sihv.model.SmallAnimal;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class SaveAnimalAttributes extends AbstractBean {

    public void saveNewAnimal(VariablesAnimal varAnimal) {
        try {
            varAnimal.getAnimal().setAnimalName(new BeautyText()
                    .Captalizador(varAnimal.getAnimal().getAnimalName()));
            String nameAnimal = varAnimal.getAnimal().getAnimalName();
            nameAnimal += " - " + varAnimal.getOldRGHV();
            varAnimal.getAnimal().setAnimalName(nameAnimal);
            varAnimal.getAnimal().setAnimalAge(varAnimal.getAnimal().getAnimalAge().toLowerCase());
            varAnimal.getAnimal().setDeathAnimal(false);
            varAnimal.getAnimal().setRegistrationDate(getObjData());
            getDaoGenerico().save(varAnimal.getAnimal());
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO public void saveNewAnimal():" + e);
        }
    }

    public void saveSmallAnimal(VariablesAnimal varAnimal) {
        try {
            SmallAnimal smallAnimal = new SmallAnimal();
            smallAnimal.setAnimals(varAnimal.getAnimal());
            smallAnimal.setRaces(varAnimal.getSelectObjRaces());
            getDaoGenerico().save(smallAnimal);
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO public void saveSmallAnimal():" + e);
        }
    }

    public void saveOwnersHasAnimals(VariablesPerson varPerson, VariablesAnimal varAnimal) {
        try {
            OwnersHasAnimals obj = new OwnersHasAnimals();
            obj.setAnimals(varAnimal.getAnimal());
            obj.setOwners(varPerson.getOwner());
            getDaoGenerico().save(obj);
            List<?> list = getDaoGenerico().list("select h from OwnersHasAnimals h \n"
                    + "where \n"
                    + "h.pkOwnersHasAnimals=(select max(o.pkOwnersHasAnimals) from OwnersHasAnimals o)");
            obj = (OwnersHasAnimals) list.get(0);
            varAnimal.setOwnersHasAnimals(obj);
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO public void saveOwnersHasAnimals():" + e);
        }
    }

}
