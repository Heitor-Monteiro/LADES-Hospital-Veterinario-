/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consultationEntryControl;

import com.lades.sihv.model.Animals;
import com.lades.sihv.model.People;
import com.lades.sihv.model.Races;
import com.lades.sihv.model.Scheduling;
import com.lades.sihv.model.SmallAnimal;
import com.lades.sihv.model.Species;
import com.lades.sihv.model.VetConsultation;
import java.io.Serializable;

/**
 *
 * @author thiberius
 */
public class ConsultationEntryItem implements Serializable {

    private VetConsultation consultation;
    private Scheduling scheduling;
    private People vetPerson;
    private People ownerPerson;
    //--------------------------------------------------------------------------
    private Animals animal;
    private SmallAnimal smallAnimal;
    private Races breed;
    private Species species;
    //--------------------------------------------------------------------------
    private double subTotal;
    private String subTotalText;
    private boolean pendingPayment;

    // GETs & SETs -------------------------------------------------------------
    public VetConsultation getConsultation() {
        return consultation;
    }

    public void setConsultation(VetConsultation consultation) {
        this.consultation = consultation;
    }

    public Scheduling getScheduling() {
        return scheduling;
    }

    public void setScheduling(Scheduling scheduling) {
        this.scheduling = scheduling;
    }

    public People getVetPerson() {
        return vetPerson;
    }

    public void setVetPerson(People vetPerson) {
        this.vetPerson = vetPerson;
    }

    public People getOwnerPerson() {
        return ownerPerson;
    }

    public void setOwnerPerson(People ownerPerson) {
        this.ownerPerson = ownerPerson;
    }

    //--------------------------------------------------------------------------
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
        this.smallAnimal = smallAnimal;
    }

    public Races getBreed() {
        return breed;
    }

    public void setBreed(Races breed) {
        this.breed = breed;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }
    //--------------------------------------------------------------------------

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public String getSubTotalText() {
        return subTotalText;
    }

    public void setSubTotalText(String subTotalText) {
        this.subTotalText = subTotalText;
    }

    public boolean isPendingPayment() {
        return pendingPayment;
    }

    public void setPendingPayment(boolean pendingPayment) {
        this.pendingPayment = pendingPayment;
    }
}
