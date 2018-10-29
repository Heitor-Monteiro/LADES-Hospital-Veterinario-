/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consulta;

import com.lades.sihv.model.Animals;
import com.lades.sihv.model.NewAnimalAndOwner;
import com.lades.sihv.model.OwnersHasAnimals;
import com.lades.sihv.model.Scheduling;
import java.io.Serializable;

/**
 *
 * @author thiberius
 */
public class SchedulesConfirmedForConsultation implements Serializable {

    private String rghv, cpf, rg;
    private Scheduling schedule;
    private NewAnimalAndOwner newAnimalAndOwner;
    private OwnersHasAnimals ownersHasAnimals;
    //--------------------------------------------------------------------------
    private Animals animal;

    // GETs & SETs -------------------------------------------------------------
    public String getRghv() {
        return rghv;
    }

    public void setRghv(String rghv) {
        this.rghv = rghv;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Scheduling getSchedule() {
        return schedule;
    }

    public void setSchedule(Scheduling schedule) {
        this.schedule = schedule;
    }

    public NewAnimalAndOwner getNewAnimalAndOwner() {
        return newAnimalAndOwner;
    }

    public void setNewAnimalAndOwner(NewAnimalAndOwner newAnimalAndOwner) {
        this.newAnimalAndOwner = newAnimalAndOwner;
    }

    public OwnersHasAnimals getOwnersHasAnimals() {
        return ownersHasAnimals;
    }

    public void setOwnersHasAnimals(OwnersHasAnimals ownersHasAnimals) {
        this.ownersHasAnimals = ownersHasAnimals;
    }

    public Animals getAnimal() {
        return animal;
    }

    public void setAnimal(Animals animal) {
        this.animal = animal;
    }
}
