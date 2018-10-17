/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.animal;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.model.ClassAnimal;
import com.lades.sihv.model.Genre;
import com.lades.sihv.model.Order;
import com.lades.sihv.model.Species;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class SmallAnimalSpecies extends AbstractBean {

    public void methodSmallAnimalSpecies(VariablesAnimal varAnimal) {
        try {
            List<?> list = getDaoGenerico().list("select c, o, g, s from ClassAnimal c, Order o, Genre g, Species s \n"
                    + "where \n"
                    + "c.pkClassAnimal=o.id.classAnimalPkClassAnimal and \n"
                    + "o.id.pkOrder=g.id.orderPkOrder and \n"
                    + "g.id.pkGenre=s.id.genrePkGenre and \n"
                    + "s.id.pkSpecies>='1' and \n"
                    + "s.id.pkSpecies<='2'");
            cleanLists(varAnimal);
            collectDataFromTheList(list, varAnimal);
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO public void methodSmallAnimalSpecies():" + e);
        }
    }

    private void cleanLists(VariablesAnimal varAnimal) {
        varAnimal.getListObjClassAnimal().clear();
        varAnimal.getListObjOrder().clear();
        varAnimal.getListObjGenre().clear();
        varAnimal.getListObjSpecies().clear();
        //----------------------------------------------------------------------
        varAnimal.getListTextClassAnimal().clear();
        varAnimal.getListTextOrder().clear();
        varAnimal.getListTextGenre().clear();
        varAnimal.getListTextSpecies().clear();
    }

    private void collectDataFromTheList(List<?> list, VariablesAnimal varAnimal) {
        for (Object[] object : (List<Object[]>) list) {
            ClassAnimal objClassAnimal = (ClassAnimal) object[0];
            varAnimal.setSelectObjClassAnimal(objClassAnimal);
            varAnimal.setSelectTextClassAnimal(objClassAnimal.getNameClass());
            varAnimal.getListObjClassAnimal().add(objClassAnimal);
            varAnimal.getListTextClassAnimal().add(objClassAnimal.getNameClass());
            //------------------------------------------------------------------
            Order objOrder = (Order) object[1];
            varAnimal.setSelectObjOrder(objOrder);
            varAnimal.setSelectTextOrder(objOrder.getOrderName());
            varAnimal.getListObjOrder().add(objOrder);
            varAnimal.getListTextOrder().add(objOrder.getOrderName());
            //------------------------------------------------------------------
            Genre objGenre = (Genre) object[2];
            varAnimal.getListObjGenre().add(objGenre);
            varAnimal.getListTextGenre().add(objGenre.getGenreName());
            //------------------------------------------------------------------
            Species objSpecie = (Species) object[3];
            varAnimal.getListObjSpecies().add(objSpecie);
            varAnimal.getListTextSpecies().add(objSpecie.getNameSpecies());
        }
    }
}
