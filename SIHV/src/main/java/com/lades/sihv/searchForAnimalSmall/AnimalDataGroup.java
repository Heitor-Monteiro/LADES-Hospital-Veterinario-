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
    private String oldRGHV;
    private String animalNameTemp;
    private String rghv;
    private Races race;
    private Species species;
    private String selectTextSpecies;
    private String selectTextBreed;
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
        if (this.animal.getPkAnimal() != null) {
            oldRGHV = this.animal.getAnimalName().replaceAll("[^1234567890]", "");
            animalNameTemp = this.animal.getAnimalName().replaceAll("[-1234567890 ]", "");
        }
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
        if (this.race.getId() != null) {
            selectTextBreed = this.race.getNameRaces();
        }
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
        if (this.species.getId() != null) {
            selectTextSpecies = this.species.getNameSpecies();
        }
    }

    public List<OwnerDataGroup> getListOwner() {
        return listOwner;
    }

    public void setListOwner(List<OwnerDataGroup> listOwner) {
        this.listOwner = listOwner;
    }

    public String getOldRGHV() {
        return oldRGHV;
    }

    public void setOldRGHV(String oldRGHV) {
        this.oldRGHV = oldRGHV;
    }

    public String getAnimalNameTemp() {
        return animalNameTemp;
    }

    public void setAnimalNameTemp(String animalNameTemp) {
        this.animalNameTemp = animalNameTemp;
    }

    public String getSelectTextSpecies() {
        return selectTextSpecies;
    }

    public void setSelectTextSpecies(String selectTextSpecies) {
        this.selectTextSpecies = selectTextSpecies;
        System.out.println("++++++++++++++++++++++++++++ " + this.selectTextSpecies);
    }

    public String getSelectTextBreed() {
        return selectTextBreed;
    }

    public void setSelectTextBreed(String selectTextBreed) {
        this.selectTextBreed = selectTextBreed;
        if (this.selectTextBreed != null) {
            System.out.println("►►►►►►►►►►►►► setSelectTextBreed:" + this.selectTextBreed);
        }
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
