/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.address;

import com.lades.sihv.controller.ListRenderedFields;
import com.lades.sihv.model.Address;
import com.lades.sihv.model.City;
import com.lades.sihv.model.FederationUnity;
import com.lades.sihv.model.Houses;
import com.lades.sihv.model.Nation;
import com.lades.sihv.model.Neighborhood;
import com.lades.sihv.model.Street;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class VariablesAddress implements Serializable {

    private Houses house;
    //--------------------------------------------------------------------------
    private List<Nation> listNation;
    private List<FederationUnity> listUF;
    private List<City> listObjCity;
    private List<Neighborhood> listObjNeighborhood;
    private List<Address> listObjAddress;
    private List<Street> listObjStreet;
    //--------------------------------------------------------------------------
    private List<String> listCity;
    private List<String> listNeighborhood;
    private List<String> listStreet;
    //--------------------------------------------------------------------------
    private Nation selectNation;
    private FederationUnity selectUF;
    private String selectCity;
    private String selectNeighborhood;
    private String selectStreet;
    //--------------------------------------------------------------------------
    private City objCity;
    private Neighborhood objNeighborhood;
    private Street objStreet;
    private Address objAddress;
    //--------------------------------------------------------------------------
    private boolean disableAddressFields;
    private final ListRenderedFields listRenderedFields;

    public VariablesAddress() {
        house = new Houses();
        //----------------------------------------------------------------------
        instantiateLists();
        //----------------------------------------------------------------------
        objCity = new City();
        objNeighborhood = new Neighborhood();
        objStreet = new Street();
        objAddress = new Address();
        //----------------------------------------------------------------------
        listRenderedFields = new ListRenderedFields(4);
        listRenderedFields.startIndexListViewFields();
    }

    public final void instantiateLists() {
        listNation = new ArrayList<>();
        listUF = new ArrayList<>();
        listObjCity = new ArrayList<>();
        listObjNeighborhood = new ArrayList<>();
        listObjAddress = new ArrayList<>();
        listObjStreet = new ArrayList<>();
        //----------------------------------------------------------------------
        listCity = new ArrayList<>();
        listNeighborhood = new ArrayList<>();
        listStreet = new ArrayList<>();
    }

    //-GETs e SETs--------------------------------------------------------------
    public Houses getHouse() {
        return house;
    }

    public void setHouse(Houses house) {
        this.house = house;
    }

    public List<Nation> getListNation() {
        return listNation;
    }

    public void setListNation(List<Nation> listNation) {
        this.listNation = listNation;
    }

    public List<FederationUnity> getListUF() {
        return listUF;
    }

    public void setListUF(List<FederationUnity> listUF) {
        this.listUF = listUF;
    }

    public List<City> getListObjCity() {
        return listObjCity;
    }

    public void setListObjCity(List<City> listObjCity) {
        this.listObjCity = listObjCity;
    }

    public List<Neighborhood> getListObjNeighborhood() {
        return listObjNeighborhood;
    }

    public void setListObjNeighborhood(List<Neighborhood> listObjNeighborhood) {
        this.listObjNeighborhood = listObjNeighborhood;
    }

    public List<Address> getListObjAddress() {
        return listObjAddress;
    }

    public void setListObjAddress(List<Address> listObjAddress) {
        this.listObjAddress = listObjAddress;
    }

    public List<Street> getListObjStreet() {
        return listObjStreet;
    }

    public void setListObjStreet(List<Street> listObjStreet) {
        this.listObjStreet = listObjStreet;
    }

    public List<String> getListCity() {
        return listCity;
    }

    public List<String> getListNeighborhood() {
        return listNeighborhood;
    }

    public List<String> getListStreet() {
        return listStreet;
    }

//    public void setListStreet(List<String> listStreet) {
//        this.listStreet = listStreet;
//    }
    //-SELECTS FIELDS-----------------------------------------------------------
    public Nation getSelectNation() {
        return selectNation;
    }

    public void setSelectNation(Nation selectNation) {
        this.selectNation = selectNation;
    }

    public FederationUnity getSelectUF() {
        return selectUF;
    }

    public void setSelectUF(FederationUnity selectUF) {
        this.selectUF = selectUF;
    }

    public String getSelectCity() {
        return selectCity;
    }

    public void setSelectCity(String selectCity) {
        this.selectCity = selectCity;
    }

    public String getSelectNeighborhood() {
        return selectNeighborhood;
    }

    public void setSelectNeighborhood(String selectNeighborhood) {
        this.selectNeighborhood = selectNeighborhood;
    }

    public String getSelectStreet() {
        return selectStreet;
    }

    public void setSelectStreet(String selectStreet) {
        this.selectStreet = selectStreet;
    }

    //-OBJECTS TEMP-----------------------------------------------------------
    public City getObjCity() {
        return objCity;
    }

    public void setObjCity(City objCity) {
        this.objCity = objCity;
    }

    public Neighborhood getObjNeighborhood() {
        return objNeighborhood;
    }

    public void setObjNeighborhood(Neighborhood objNeighborhood) {
        this.objNeighborhood = objNeighborhood;
    }

    public Street getObjStreet() {
        return objStreet;
    }

    public void setObjStreet(Street objStreet) {
        this.objStreet = objStreet;
    }

    public Address getObjAddress() {
        return objAddress;
    }

    public void setObjAddress(Address objAddress) {
        this.objAddress = objAddress;
    }

    public boolean isDisableAddressFields() {
        return disableAddressFields;
    }

    public void setDisableAddressFields(boolean disableAddressFields) {
        this.disableAddressFields = disableAddressFields;
    }

    //-STATUS ADDRESS-----------------------------------------------------------
    public void newCity(boolean var) {
        listRenderedFields.getListViewFields(0).setViewVariableBoolean(var);
    }

    public boolean statusNewCity() {
        return listRenderedFields.getListViewFields(0).isViewVariableBoolean();
    }

    public void newNeighborhood(boolean var) {
        listRenderedFields.getListViewFields(1).setViewVariableBoolean(var);
    }

    public boolean statusNewNeighborhood() {
        return listRenderedFields.getListViewFields(1).isViewVariableBoolean();
    }

    public void newStreet(boolean var) {
        listRenderedFields.getListViewFields(2).setViewVariableBoolean(var);
    }

    public boolean statusNewStreet() {
        return listRenderedFields.getListViewFields(2).isViewVariableBoolean();
    }

    public void newAddress(boolean var) {
        listRenderedFields.getListViewFields(3).setViewVariableBoolean(var);
    }

    public boolean statusNewAddress() {
        return listRenderedFields.getListViewFields(3).isViewVariableBoolean();
    }

    //-VIEW---------------------------------------------------------------------
    public boolean isConfirmSelectUF() {
        return selectUF == null;
    }
}
