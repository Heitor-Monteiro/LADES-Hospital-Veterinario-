/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.address;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.BeautyText;
import com.lades.sihv.controller.ModuleToCollectError;
import com.lades.sihv.model.Address;
import com.lades.sihv.model.Street;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author thiberius
 */
public class FinalizeAddressSelection extends AbstractBean {

    public void methodFinalizeAddressSelection(VariablesAddress varr) {
        try {
            System.out.println("------------------ public void enableFildesHouse()");
            if (varr.getSelectStreet() != null) {
                if (varr.getSelectStreet().length() > 0) {
                    varr.setSelectStreet(new BeautyText().Captalizador(varr.getSelectStreet()));
                    boolean var = true;
                    for (Street street : varr.getListObjStreet()) {
                        if (street.getNameStreet().equals(varr.getSelectStreet())) {
                            varr.setObjStreet(street);
                            for (Address addres : varr.getListObjAddress()) {
                                if (Objects.equals(addres.getNeighborhood().getId().
                                        getPkNeighborhood(), varr.getObjNeighborhood().getId().getPkNeighborhood())
                                        && Objects.equals(addres.getStreet().getPkStreet(), varr.getObjStreet().getPkStreet())) {
                                    varr.setObjAddress(addres);
                                    break;
                                }
                            }
                            var = false;
                            varr.newStreet(var);
                            break;
                        }
                    }
                    if (var && !varr.statusNewCity() && !varr.statusNewNeighborhood()) {
                        List<Street> streets = getDaoGenerico().list("select s from Street s, Address a, Neighborhood n, City c, FederationUnity f \n"
                                + "where \n"
                                + "f.pkFederationUnity=c.pkCity and \n"
                                + "c.pkCity=n.id.cityPkCity and \n"
                                + "n.id.pkNeighborhood=a.neighborhood.id.pkNeighborhood and \n"
                                + "s.pkStreet=a.street.pkStreet and \n"
                                + "c.pkCity='" + varr.getObjCity().getPkCity() + "' and \n"
                                + "f.pkFederationUnity='" + varr.getSelectUF().getPkFederationUnity() + "' and \n"
                                + "s.nameStreet='" + varr.getSelectStreet() + "' ");
                        if (!streets.isEmpty()) {
                            if (streets.size() == 1) {
                                varr.setObjStreet(streets.get(0));
                                varr.newAddress(true);
                                var = false;
                                getObjMessage().info("A rua também existe em outros bairros:", varr.getSelectStreet());
                            }
                        }
                    }
                    if (var) {
                        varr.setObjStreet(new Street());
                        varr.newStreet(var);
                        varr.getObjStreet().setNameStreet(varr.getSelectStreet());
                        varr.getObjStreet().setRegistrationDate(getObjData());
                        getObjMessage().info("Nova rua para registro:", varr.getSelectStreet());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO methodFinalizeAddressSelection(): " + e.toString());
            new ModuleToCollectError().erroPage500("FinalizeAddressSelection > methodFinalizeAddressSelection", e.toString());
        }
    }
}
