/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.searchForAnimalSmall;

import com.lades.sihv.model.Address;
import com.lades.sihv.model.City;
import com.lades.sihv.model.Cpf;
import com.lades.sihv.model.FederationUnity;
import com.lades.sihv.model.Houses;
import com.lades.sihv.model.LegalPerson;
import com.lades.sihv.model.Neighborhood;
import com.lades.sihv.model.Owners;
import com.lades.sihv.model.People;
import com.lades.sihv.model.Phones;
import com.lades.sihv.model.PhysicalPerson;
import com.lades.sihv.model.Rg;
import com.lades.sihv.model.Street;
import java.io.Serializable;

/**
 *
 * @author thiberius
 */
public class OwnerDataGroup implements Serializable {

    private People person;
    private Owners owner;
    private LegalPerson legalPerson;
    private PhysicalPerson physicalPerson;
    private Cpf cpf;
    private Rg rg;
    private String cpfCnpj;
    private String tempRg;
    private Phones phone1;
    private Phones phone2;
    private Phones phone3;
    //--------------------------------------------------------------------------
    private Houses house;
    private Street street;
    private Neighborhood neighborhood;
    private City city;
    private FederationUnity uf;
    private Address address;

    // GETs & SETs -------------------------------------------------------------
    public People getPerson() {
        return person;
    }

    public void setPerson(People person) {
        this.person = person;
    }

    public Owners getOwner() {
        return owner;
    }

    public void setOwner(Owners owner) {
        this.owner = owner;
    }

    public LegalPerson getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(LegalPerson legalPerson) {
        cpfCnpj = legalPerson.getCnpj();
        this.legalPerson = legalPerson;
    }

    public PhysicalPerson getPhysicalPerson() {
        return physicalPerson;
    }

    public void setPhysicalPerson(PhysicalPerson physicalPerson) {
        this.physicalPerson = physicalPerson;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public void setCpf(Cpf cpf) {
        cpfCnpj = cpf.getCpf();
        this.cpf = cpf;
    }

    public Rg getRg() {
        return rg;
    }

    public void setRg(Rg rg) {
        tempRg = rg.getRg();
        this.rg = rg;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getTempRg() {
        return tempRg;
    }

    public void setTempRg(String tempRg) {
        this.tempRg = tempRg;
    }

    public Phones getPhone1() {
        return phone1;
    }

    public void setPhone1(Phones phone1) {
        this.phone1 = phone1;
    }

    public Phones getPhone2() {
        return phone2;
    }

    public void setPhone2(Phones phone2) {
        this.phone2 = phone2;
    }

    public Phones getPhone3() {
        return phone3;
    }

    public void setPhone3(Phones phone3) {
        this.phone3 = phone3;
    }

    public Houses getHouse() {
        return house;
    }

    public void setHouse(Houses house) {
        this.house = house;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public Neighborhood getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(Neighborhood neighborhood) {
        this.neighborhood = neighborhood;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public FederationUnity getUf() {
        return uf;
    }

    public void setUf(FederationUnity uf) {
        this.uf = uf;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
