/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.address;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.BeautyText;
import com.lades.sihv.model.City;
import com.lades.sihv.model.Neighborhood;

/**
 *
 * @author thiberius
 */
public class EnableListNeighborhood extends AbstractBean {

    public void methodEnableListNeighborhood(VariablesAddress varr) {
        System.out.println("------------------public void enableListNeighborhood()");
        varr.getListNeighborhood().clear();
        varr.getListStreet().clear();
        varr.setSelectNeighborhood("");
        varr.setSelectStreet("");
        if (varr.getSelectCity() != null) {
            if (varr.getSelectCity().length() > 0) {
                varr.setSelectCity(new BeautyText().Captalizador(varr.getSelectCity()));
                boolean var = true;
                for (City city : varr.getListObjCity()) {
                    if (city.getFullNameCity().equals(varr.getSelectCity())) {
                        varr.setListObjNeighborhood(getDaoGenerico().list("select n from Neighborhood n, City c \n"
                                + "where \n"
                                + "n.id.cityPkCity=c.pkCity and \n"
                                + "n.id.cityPkCity='" + city.getPkCity() + "'"));
                        System.out.println("----------------------- Bairros encontrados: " + varr.getListObjNeighborhood().size());
                        varr.setObjCity(city);
                        for (Neighborhood neighborhood : varr.getListObjNeighborhood()) {
                            varr.getListNeighborhood().add(neighborhood.getNeighborhood());
                        }
                        var = false;
                        break;
                    }
                }
                if (var) {
                    varr.newCity(var);
                    varr.newNeighborhood(var);
                    varr.newStreet(var);
                    varr.getObjCity().setFederationUnity(varr.getSelectUF());
                    varr.getObjCity().setFullNameCity(varr.getSelectCity());
                    varr.getObjCity().setRegistrationDate(getObjData());
                    getObjMessage().info("Nova cidade para registro:", varr.getSelectCity());
                    System.out.println("----------------------- Nova cidades");
                }
            }
        }
    }
}
