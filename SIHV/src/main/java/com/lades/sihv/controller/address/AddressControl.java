/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.address;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.ModuleToCollectError;
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
            getObjMessage().error("►►►►►►►►►►►►► ERRO public startAddressControl():", e.toString());
            System.out.println("►►►►►►►►►►►►► ERRO public startAddressControl(): " + e.toString());
            new ModuleToCollectError().erroPage500("AddressControl > startAddressControl", e.toString());
        }
    }

    public final void loadLists() {
        try {
            //var.setListNation(getDaoGenerico().list("select n from Nation n"));
            var.setListUF(getDaoGenerico().list("select u from FederationUnity u"));
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO public final void loadAddresses(): " + e.toString());
            new ModuleToCollectError().erroPage500("AddressControl > loadLists", e.toString());
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
