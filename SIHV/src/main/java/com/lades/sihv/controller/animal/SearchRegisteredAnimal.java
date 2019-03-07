/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.animal;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.model.Animals;
import com.lades.sihv.model.OwnersHasAnimals;
import com.lades.sihv.model.People;
import com.lades.sihv.model.Races;
import com.lades.sihv.model.SmallAnimal;
import com.lades.sihv.model.Species;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author thiberius
 */
public class SearchRegisteredAnimal extends AbstractBean {

    public void methosSelectSmallAnimal(VariablesAnimal varAnimal) {
        try {
            for (TempListAnimals obj : varAnimal.getTempListAnimals()) {
                if (Objects.equals(varAnimal.getSelectTempListAnimals()
                        .getAnimals().getPkAnimal(), obj.getAnimals().getPkAnimal())) {
                    varAnimal.setTempRGHV(varAnimal.getSelectTempListAnimals().getTempRGHV());
                    varAnimal.setAnimal(varAnimal.getSelectTempListAnimals().getAnimals());
                    varAnimal.setOldRGHV(filterRghvOfAnimalName(varAnimal.getAnimal().getAnimalName()));
                    varAnimal.setSmallAnimal(varAnimal.getSelectTempListAnimals().getSmallAnimal());
                    varAnimal.setSelectObjSpecies(varAnimal.getSelectTempListAnimals().getSpecie());
                    varAnimal.setSelectObjRaces(varAnimal.getSelectTempListAnimals().getRace());
                    varAnimal.setSelectTextRaces(varAnimal.getSelectTempListAnimals().getRace().getNameRaces());
                    varAnimal.setOwnersHasAnimals(varAnimal.getSelectTempListAnimals().getOwnersHasAnimals());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO public void methosSelectSmallAnimal():" + e);
        }
    }

    private String filterRghvOfAnimalName(String nameAnimal) {
        return nameAnimal.replaceAll("[^1234567890]", "");
    }

    public void methodSearchRegisteredAnimal(People person, VariablesAnimal varAnimal) {
        try {
            varAnimal.getTempListAnimals().clear();
            CollectAnimalRghv collectRghv = new CollectAnimalRghv();

            List<?> listanimals = getDaoGenerico().list("select a, s, r, x, h from "
                    + "Owners o, OwnersHasAnimals h, Animals a, SmallAnimal s, Races r, Species x \n"
                    + "where \n"
                    + "o.pkOwner=h.owners.pkOwner and \n"
                    + "a.pkAnimal=h.animals.pkAnimal and \n"
                    + "a.pkAnimal=s.animals.pkAnimal and \n"
                    + "s.races.id.pkRaces=r.id.pkRaces and \n"
                    + "r.id.speciesPkSpecies=x.id.pkSpecies and \n"
                    + "o.people.pkPerson='" + person.getPkPerson() + "'");
            for (Object[] object : (List<Object[]>) listanimals) {
                TempListAnimals obj = new TempListAnimals();
                obj.setAnimals((Animals) object[0]);
                obj.setSmallAnimal((SmallAnimal) object[1]);
                obj.setRace((Races) object[2]);
                obj.setSpecie((Species) object[3]);
                obj.setTempRGHV(collectRghv.methodCollectAnimalRghv(obj.getSmallAnimal().getPkSmallAnimal(), "P", obj.getAnimals()));
                obj.setOwnersHasAnimals((OwnersHasAnimals) object[4]);
                varAnimal.getTempListAnimals().add(obj);
            }
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO public void methodSearchRegisteredAnimal():" + e);
        }
    }
}
