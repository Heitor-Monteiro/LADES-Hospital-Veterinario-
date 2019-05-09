/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consultation;

import java.io.Serializable;

/**
 *
 * @author thiberius
 */
public class ClientInitialData implements Serializable {

    private Integer pkAnimal;
    private String animalName;
    private Integer RGHVsmallAnimal;
    private String oldRghv;
    private String nameRaces;
    private String nameSpecies;
    private Integer pkPerson;
    private String namePerson;
    private String cpfCnpj;
    private String rg;
    private String dataOwner;

    // GETs & SETs -------------------------------------------------------------
    public Integer getPkAnimal() {
        return pkAnimal;
    }

    public void setPkAnimal(Integer pkAnimal) {
        this.pkAnimal = pkAnimal;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public Integer getRGHVsmallAnimal() {
        return RGHVsmallAnimal;
    }

    public void setRGHVsmallAnimal(Integer RGHVsmallAnimal) {
        this.RGHVsmallAnimal = RGHVsmallAnimal;
    }

    public String getOldRghv() {
        return oldRghv;
    }

    public void setOldRghv(String oldRghv) {
        this.oldRghv = oldRghv;
    }

    public String getNameRaces() {
        return nameRaces;
    }

    public void setNameRaces(String nameRaces) {
        this.nameRaces = nameRaces;
    }

    public String getNameSpecies() {
        return nameSpecies;
    }

    public void setNameSpecies(String nameSpecies) {
        this.nameSpecies = nameSpecies;
    }

    public Integer getPkPerson() {
        return pkPerson;
    }

    public void setPkPerson(Integer pkPerson) {
        this.pkPerson = pkPerson;
    }

    public String getNamePerson() {
        return namePerson;
    }

    public void setNamePerson(String namePerson) {
        this.namePerson = namePerson;
        dataOwner = namePerson;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
        dataOwner = dataOwner + " " + cpfCnpj;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
        dataOwner = dataOwner + " " + rg;
    }

    public String getDataOwner() {
        return dataOwner;
    }

    public void setDataOwner(String dataOwner) {
        this.dataOwner = dataOwner;
    }
}
