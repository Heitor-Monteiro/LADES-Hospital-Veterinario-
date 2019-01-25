/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consultationEntryControl;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.model.Animals;
import com.lades.sihv.model.Races;
import com.lades.sihv.model.SmallAnimal;
import com.lades.sihv.model.Species;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class SearchSmallAnimal extends AbstractBean {
    
    public void listSmallAnimal(List<ConsultationEntryItem> listItens) {
        for (ConsultationEntryItem listIten : listItens) {
            List<?> list = getDaoGenerico().list("select a,sa,r,s from "
                    + "Scheduling sc, OwnersHasAnimals o, Animals a, "
                    + "SmallAnimal sa, Races r, Species s \n"
                    + "where \n"
                    + "sc.ownersHasAnimals.pkOwnersHasAnimals=o.pkOwnersHasAnimals and \n"
                    + "o.animals.pkAnimal=a.pkAnimal and \n"
                    + "a.pkAnimal=sa.animals.pkAnimal and \n"
                    + "sa.races.id.pkRaces=r.id.pkRaces and \n"
                    + "r.species.id.pkSpecies=s.id.pkSpecies and \n"
                    + "sc.statusService='efetivado(a)' and \n"
                    + "sc.pkSchedule='" + listIten.getScheduling().getPkSchedule() + "' ");
            if (list != null && !list.isEmpty()) {
                for (Object[] object : (List<Object[]>) list) {
                    listIten.setAnimal((Animals) object[0]);
                    listIten.setSmallAnimal((SmallAnimal) object[1]);
                    listIten.setBreed((Races) object[2]);
                    listIten.setSpecies((Species) object[3]);
                }
            }
        }
    }
}
