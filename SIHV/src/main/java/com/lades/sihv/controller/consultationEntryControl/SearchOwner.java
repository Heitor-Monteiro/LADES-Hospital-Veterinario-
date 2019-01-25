/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consultationEntryControl;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.model.People;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class SearchOwner extends AbstractBean {

    public void listSearchOwner(List<ConsultationEntryItem> listItens) {
        for (ConsultationEntryItem listIten : listItens) {
            List<People> list = getDaoGenerico().list("select p from VetConsultation v, Scheduling s, OwnersHasAnimals h, Owners o, People p \n"
                    + "where \n"
                    + "v.scheduling.pkSchedule=s.pkSchedule and \n"
                    + "v.ownersHasAnimals.pkOwnersHasAnimals=h.pkOwnersHasAnimals and \n"
                    + "s.ownersHasAnimals.pkOwnersHasAnimals=h.pkOwnersHasAnimals and \n"
                    + "h.owners.pkOwner=o.pkOwner and \n"
                    + "o.people.pkPerson=p.pkPerson and \n"
                    + "v.pkVetConsultation='" + listIten.getConsultation().getPkVetConsultation() + "'");
            if (list != null && !list.isEmpty()) {
                for (People people : list) {
                    listIten.setOwnerPerson(people);
                }
            }
        }
    }
}
