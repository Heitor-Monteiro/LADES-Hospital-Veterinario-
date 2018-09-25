/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.address;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.model.People;

/**
 *
 * @author thiberius
 */
public class AddressControl extends AbstractBean {

    private final VariablesAddress var;

    public AddressControl() {
        var = new VariablesAddress();
    }

    public void startAddressControl(boolean v, People person) {
        try {
            var.setDisableAddressFields(!v);
            if (v) {
                // New identified person
//                loadLists();
            } else {
                // Registered person
                SearchAddressOfPerson obj = new SearchAddressOfPerson();
                obj.methodSearchAddressOfPerson(person, var);
            }
        } catch (Exception e) {
            getObjMessage().error("BACK-END WARNING: ERRO public startAddressControl():", e.getMessage());
            System.out.println("BACK-END WARNING: ERRO public startAddressControl(): " + e);
        }
    }

    public final void loadLists() {
        try {
            //var.setListNation(getDaoGenerico().list("select n from Nation n"));
            var.setListUF(getDaoGenerico().list("select u from FederationUnity u"));
        } catch (Exception e) {
            System.out.println("BACK-END WARNING: ERRO public final void loadAddresses(): " + e);
        }
    }

    public void saveAddress(People person) {
        new SaveAddress().methodSaveAddress(person, var);
    }

    //-GETs e SETs--------------------------------------------------------------
    public VariablesAddress getVar() {
        return var;
    }

    //-VIEWS--------------------------------------------------------------------
    public void enableListCitys() {
        new EnableListCitys().methodEnableListCitys(var);
    }

    public void enableListNeighborhood() {
        new EnableListNeighborhood().methodEnableListNeighborhood(var);
    }

    public void enableListStreet() {
        new EnableListStreet().methodEnableListStreet(var);
    }

    public void finalizeAddressSelection() {
        new FinalizeAddressSelection().methodFinalizeAddressSelection(var);
    }
}
