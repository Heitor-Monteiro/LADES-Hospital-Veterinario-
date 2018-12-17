/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.person;

import com.lades.sihv.model.Cpf;
import com.lades.sihv.model.LegalPerson;
import com.lades.sihv.model.Owners;
import com.lades.sihv.model.People;
import com.lades.sihv.model.PhysicalPerson;
import com.lades.sihv.model.Rg;
import com.lades.sihv.model.Users;
import java.io.Serializable;

/**
 *
 * @author thiberius
 */
public class VariablesPerson implements Serializable {

    private People person;
    private PhysicalPerson physicalPerson;
    private Cpf objCpf;
    private Rg objRg;
    private LegalPerson legalPerson;
    private Owners owner;
    private Users user;

    public VariablesPerson() {
        person = new People();
        physicalPerson = new PhysicalPerson();
        objCpf = new Cpf();
        objCpf.setCpf("");
        objRg = new Rg();
        objRg.setRg("");
        legalPerson = new LegalPerson();
        legalPerson.setCnpj("");
        owner = new Owners();
        user = new Users();
    }

    //-GETs e SETs--------------------------------------------------------------
    public People getPerson() {
        return person;
    }

    public void setPerson(People person) {
        this.person = person;
    }

    public PhysicalPerson getPhysicalPerson() {
        return physicalPerson;
    }

    public void setPhysicalPerson(PhysicalPerson physicalPerson) {
        this.physicalPerson = physicalPerson;
    }

    public Cpf getObjCpf() {
        return objCpf;
    }

    public void setObjCpf(Cpf objCpf) {
        this.objCpf = objCpf;
    }

    public Rg getObjRg() {
        return objRg;
    }

    public void setObjRg(Rg objRg) {
        this.objRg = objRg;
    }

    public LegalPerson getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(LegalPerson legalPerson) {
        this.legalPerson = legalPerson;
    }

    public Owners getOwner() {
        return owner;
    }

    public void setOwner(Owners owner) {
        this.owner = owner;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
