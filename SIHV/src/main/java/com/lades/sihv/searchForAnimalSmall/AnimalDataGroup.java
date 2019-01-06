/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.searchForAnimalSmall;

import com.lades.sihv.model.Animals;
import com.lades.sihv.model.Races;
import com.lades.sihv.model.SmallAnimal;
import com.lades.sihv.model.Species;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class AnimalDataGroup implements Serializable {

    private Animals animal;
    private SmallAnimal smallAnimal;
    private String rghv;
    private Races race;
    private Species species;
    private List<OwnerDataGroup> listOwner;

    public AnimalDataGroup() {
        listOwner = new ArrayList();
    }

    // GETs & SETs -------------------------------------------------------------
    public Animals getAnimal() {
        return animal;
    }

    public void setAnimal(Animals animal) {
        this.animal = animal;
    }

    public SmallAnimal getSmallAnimal() {
        return smallAnimal;
    }

    public void setSmallAnimal(SmallAnimal smallAnimal) {
        String date = "" + animal.getRegistrationDate();
        rghv = "" + smallAnimal.getPkSmallAnimal() + "P" + date.substring(0, 4);
        this.smallAnimal = smallAnimal;
    }

    public String getRghv() {
        return rghv;
    }

    public Races getRace() {
        return race;
    }

    public void setRace(Races race) {
        this.race = race;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public List<OwnerDataGroup> getListOwner() {
        return listOwner;
    }

    public void setListOwner(List<OwnerDataGroup> listOwner) {
        this.listOwner = listOwner;
    }

    // viewsFields -------------------------------------------------------------
    public String getDeathAnimal() {
        String x = "Não";
        if (animal.isDeathAnimal()) {
            x = "Sim";
        }
        return x;
    }

}
