/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.address;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.model.Address;
import com.lades.sihv.model.City;
import com.lades.sihv.model.FederationUnity;
import com.lades.sihv.model.Houses;
import com.lades.sihv.model.Neighborhood;
import com.lades.sihv.model.People;
import com.lades.sihv.model.Street;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class SearchAddressOfPerson extends AbstractBean {

    public void methodSearchAddressOfPerson(People person, VariablesAddress var) {
        try {
            List<?> list = getDaoGenerico().list("select uf, c, n, a, s, h "
                    + "from FederationUnity uf, City c, Neighborhood n, Address a, Street s, Houses h \n"
                    + "where \n"
                    + "uf.pkFederationUnity=c.federationUnity.pkFederationUnity and \n"
                    + "c.pkCity=n.id.cityPkCity and \n"
                    + "n.id.pkNeighborhood=a.neighborhood.id.pkNeighborhood and \n"
                    + "n.id.cityPkCity=a.neighborhood.id.cityPkCity and \n"
                    + "s.pkStreet=a.street.pkStreet and \n"
                    + "a.pkAddress=h.address.pkAddress and \n"
                    + "h.people.pkPerson='" + person.getPkPerson() + "' ");
            System.out.println("------------------------------ person.getPkPerson(): " + person.getPkPerson());
            System.out.println("------------------------------ methodSearchAddressOfPerson: size list " + list.size());
            if (list.size() == 1) {
                for (Object[] object : (List<Object[]>) list) {
                    var.getListUF().add(0, (FederationUnity) object[0]);
                    var.getListObjCity().add(0, (City) object[1]);
                    var.getListObjNeighborhood().add(0, (Neighborhood) object[2]);
                    var.getListObjAddress().add(0, (Address) object[3]);
                    var.getListObjStreet().add(0, (Street) object[4]);
                    var.setHouse((Houses) object[5]);
                    //----------------------------------------------------------
                    var.setSelectUF(var.getListUF().get(0));
                    var.setObjCity(var.getListObjCity().get(0));
                    var.setObjNeighborhood(var.getListObjNeighborhood().get(0));
                    var.setObjAddress(var.getListObjAddress().get(0));
                    var.setObjStreet(var.getListObjStreet().get(0));
                    //----------------------------------------------------------
                    var.getListCity().add(var.getObjCity().getFullNameCity());
                    var.getListNeighborhood().add(var.getObjNeighborhood().getNeighborhood());
                    var.getListStreet().add(var.getObjStreet().getNameStreet());
                    //----------------------------------------------------------
                    var.setSelectCity(var.getObjCity().getFullNameCity());
                    var.setSelectNeighborhood(var.getObjNeighborhood().getNeighborhood());
                    var.setSelectStreet(var.getObjStreet().getNameStreet());
                }
            } else {
                getObjMessage().error("Erro de programação", "A listagem retornou: "+list.size());
            }
        } catch (Exception e) {
            getObjMessage().error("BACK-END WARNING: ERRO public void methodSearchAddressOfPerson(): ", e.getMessage());
            System.out.println("BACK-END WARNING: ERRO public void methodSearchAddressOfPerson(): " + e);
        }
    }

}
