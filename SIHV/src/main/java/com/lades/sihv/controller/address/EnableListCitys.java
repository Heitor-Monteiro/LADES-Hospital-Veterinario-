/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.address;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.model.City;

/**
 *
 * @author thiberius
 */
public class EnableListCitys extends AbstractBean {

    public void methodEnableListCitys(VariablesAddress var) {
        System.out.println("------------------public void enableListCitys()");
        var.getListCity().clear();
        var.getListNeighborhood().clear();
        var.getListStreet().clear();
        var.setSelectCity("");
        var.setSelectNeighborhood("");
        var.setSelectStreet("");
        if (var.getSelectUF() != null) {
            var.setListObjCity(getDaoGenerico().list("select c from City c, FederationUnity f\n"
                    + "where\n"
                    + "f.pkFederationUnity=c.federationUnity.pkFederationUnity and \n"
                    + "c.federationUnity.pkFederationUnity='" + var.getSelectUF().getPkFederationUnity() + "'"));
            for (City city : var.getListObjCity()) {
                var.getListCity().add(city.getFullNameCity());
            }
        }
    }

}
