/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.animal;

import com.lades.sihv.model.Animals;
import com.lades.sihv.model.OwnersHasAnimals;
import com.lades.sihv.model.Races;
import com.lades.sihv.model.SmallAnimal;
import com.lades.sihv.model.Species;
import java.io.Serializable;

/**
 *
 * @author thiberius
 */
public class TempListAnimals implements Serializable {

    private String tempRGHV;
    private Animals animals;
    private SmallAnimal smallAnimal;
    private Races race;
    private Species specie;
    private OwnersHasAnimals ownersHasAnimals;

    public TempListAnimals() {
        tempRGHV = "";
        animals = new Animals();
        smallAnimal = new SmallAnimal();
        race = new Races();
        specie = new Species();
    }

    public Animals getAnimals() {
        return animals;
    }

    public void setAnimals(Animals animals) {
        this.animals = animals;
    }

    public SmallAnimal getSmallAnimal() {
        return smallAnimal;
    }

    public void setSmallAnimal(SmallAnimal smallAnimal) {
        this.smallAnimal = smallAnimal;
    }

    public Races getRace() {
        return race;
    }

    public void setRace(Races race) {
        this.race = race;
    }

    public Species getSpecie() {
        return specie;
    }

    public void setSpecie(Species specie) {
        this.specie = specie;
    }

    public String getTempRGHV() {
        return tempRGHV;
    }

    public void setTempRGHV(String tempRGHV) {
        this.tempRGHV = tempRGHV;
    }

    public OwnersHasAnimals getOwnersHasAnimals() {
        return ownersHasAnimals;
    }

    public void setOwnersHasAnimals(OwnersHasAnimals ownersHasAnimals) {
        this.ownersHasAnimals = ownersHasAnimals;
    }
}
