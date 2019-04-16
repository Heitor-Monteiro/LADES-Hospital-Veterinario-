/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.searchForAnimalSmall;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.ModuleToCollectError;
import com.lades.sihv.model.Species;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class LoadRegisteredSpeciesDogAndCat extends AbstractBean {
    
    public void methodLoadRegisteredSpeciesDogAndCat(List<Species> listObjSpecies) {
        try {
            List<Species> list = getDaoGenerico().list("select s from ClassAnimal c, Order o, Genre g, Species s \n"
                    + "where \n"
                    + "c.pkClassAnimal=o.id.classAnimalPkClassAnimal and \n"
                    + "o.id.pkOrder=g.id.orderPkOrder and \n"
                    + "g.id.pkGenre=s.id.genrePkGenre and \n"
                    + "s.id.pkSpecies>='1' and \n"
                    + "s.id.pkSpecies<='2'");
            if (!list.isEmpty()) {
                listObjSpecies.addAll(list);
            }
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO private void loadRegisteredSpeciesDogAndCat(): " + e.toString());
            new ModuleToCollectError().erroPage500("EditAnimalSmallData > loadRegisteredSpeciesDogAndCat", e.toString());
        }
    }
    
}
