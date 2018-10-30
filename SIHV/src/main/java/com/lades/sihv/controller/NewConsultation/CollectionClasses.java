/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.NewConsultation;

import com.lades.sihv.model.Animals;
import com.lades.sihv.model.VetConsultation;
import com.lades.sihv.model.People;
import com.lades.sihv.model.Users;
import com.lades.sihv.model.PhysicalPerson;
import com.lades.sihv.model.LegalPerson;
import com.lades.sihv.model.Scheduling;
import com.lades.sihv.controller.ListClasses;
import com.lades.sihv.model.Races;
import com.lades.sihv.model.SmallAnimal;
import com.lades.sihv.model.Species;
import java.io.Serializable;

/**
 *
 * @author thiberius
 */
public class CollectionClasses implements Serializable {

    private Scheduling schedule;
    private People personOwner;
    //--------------------------------------------------------------------------
    private Animals animal;
    private String rghv;
    private SmallAnimal smallAnimal;
    private Races breed;
    private Species specie;
    //--------------------------------------------------------------------------
    private People personResident;
    private PhysicalPerson physicalPerson;
    private LegalPerson legalPerson;
    private Users user;

    private VetConsultation consultation;
    private ListClasses examList;
    private FormsExames formsExams;

    // GETs & SETs -------------------------------------------------------------
    public Animals getAnimal() {
        if (animal == null) {
            animal = new Animals();
        }
        return animal;
    }

    public void setAnimais(Animals animal) {
        this.animal = animal;
    }

    public String getRghv() {
        if (rghv.isEmpty()) {
            rghv = "";
        }
        return rghv;
    }

    public void setRghv(String rghv) {
        this.rghv = rghv;
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

    public Species getSpecie() {
        return specie;
    }

    public void setSpecie(Species specie) {
        this.specie = specie;
    }

    //--------------------------------------------------------------------------
    public VetConsultation getVetConsultation() {
        if (consultation == null) {
            consultation = new VetConsultation();
        }
        return consultation;
    }

    public void setVetConsultation(VetConsultation consultation) {
        this.consultation = consultation;
    }

    public Users getUsers() {
        if (user == null) {
            user = new Users();
        }
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public People getPersonResident() {
        if (personResident == null) {
            personResident = new People();
        }
        return personResident;
    }

    public void setPersonResident(People personResident) {
        this.personResident = personResident;
    }

    public People getPersonOwner() {
        if (personOwner == null) {
            personOwner = new People();
        }
        return personOwner;
    }

    public void setProprietario(People personOwner) {
        this.personOwner = personOwner;
    }

    public PhysicalPerson getPhysicalPerson() {
        if (physicalPerson == null) {
            physicalPerson = new PhysicalPerson();
        }
        return physicalPerson;
    }

    public void setPessoaFisica(PhysicalPerson physicalPerson) {
        this.physicalPerson = physicalPerson;
    }

    public LegalPerson getPessoaJuridica() {
        if (legalPerson == null) {
            legalPerson = new LegalPerson();
        }
        return legalPerson;
    }

    public void setPessoaJuridica(LegalPerson legalPerson) {
        this.legalPerson = legalPerson;
    }

    public ListClasses getExamList(int index) {
        if (examList == null) {
            examList = new ListClasses(index, new FormsExames());
        }
        return examList;
    }

    public FormsExames getFormsExams() {
        if (formsExams == null) {
            formsExams = new FormsExames();
        }
        return formsExams;
    }

    public Scheduling getSchedule() {
        return schedule;
    }

    public void setSchedule(Scheduling schedule) {
        this.schedule = schedule;
    }
}
