/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.address;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.ModuleToCollectError;
import com.lades.sihv.model.Neighborhood;
import com.lades.sihv.model.NeighborhoodId;
import com.lades.sihv.model.People;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class SaveAddress extends AbstractBean {

    public void methodSaveAddress(People person, VariablesAddress var) {

        try {
            if (var.statusNewCity()) {
                System.out.println("BACK-END WARNING: public void saveAddress(): New City(" + var.getObjCity().getFullNameCity() + ")");
                getDaoGenerico().save(var.getObjCity());
            }

            if (var.statusNewNeighborhood() && var.statusNewStreet()) {
                System.out.println("BACK-END WARNING: public void saveAddress(): "
                        + "New Neighborhood(" + var.getObjNeighborhood().getNeighborhood() + ") "
                        + "and Street(" + var.getObjStreet().getNameStreet() + ")");
                NeighborhoodId id = new NeighborhoodId();
                id.setCityPkCity(var.getObjCity().getPkCity());
                var.getObjNeighborhood().setId(id);

                getDaoGenerico().save(var.getObjNeighborhood());
                Neighborhood objTemp = new Neighborhood();
                List<Neighborhood> listTemp = getDaoGenerico().list("select ne from Neighborhood ne \n"
                        + "where \n"
                        + "ne.id.pkNeighborhood=(select max(n.id.pkNeighborhood) from Neighborhood n) and \n"
                        + "ne.neighborhood='" + var.getObjNeighborhood().getNeighborhood() + "'");
                if (!listTemp.isEmpty()) {
                    if (listTemp.size() == 1) {
                        objTemp = listTemp.get(0);
                    }
                }
                getDaoGenerico().save(var.getObjStreet());
                var.getObjAddress().setNeighborhood(objTemp);
                var.getObjAddress().setStreet(var.getObjStreet());
                getDaoGenerico().save(var.getObjAddress());

            }

            if (!var.statusNewNeighborhood() && var.statusNewStreet()) {
                System.out.println("BACK-END WARNING: public void saveAddress(): "
                        + "New Street -->" + var.getObjStreet().getNameStreet());
                var.getObjAddress().setNeighborhood(var.getObjNeighborhood());
                var.getObjAddress().setStreet(var.getObjStreet());
                getDaoGenerico().save(var.getObjStreet());
                getDaoGenerico().save(var.getObjAddress());

            }

            if (var.statusNewAddress()) {
                System.out.println("BACK-END WARNING: public void saveAddress(): "
                        + "New Addreess: " + var.getObjNeighborhood().getNeighborhood()
                        + " + " + var.getObjStreet().getNameStreet());
                var.getObjAddress().setNeighborhood(var.getObjNeighborhood());
                var.getObjAddress().setStreet(var.getObjStreet());
                getDaoGenerico().save(var.getObjAddress());
            }

            var.getHouse().setAddress(var.getObjAddress());
            var.getHouse().setPeople(person);
            getDaoGenerico().save(var.getHouse());

            getObjMessage().info("Os endereços foram salvos", "");
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO methodSaveAddress(): " + e.toString());
            new ModuleToCollectError().erroPage500("SaveAddress > methodSaveAddress", e.toString());
        }
    }

}
