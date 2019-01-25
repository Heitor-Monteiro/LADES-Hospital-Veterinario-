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
public class SearchVeterinary extends AbstractBean {
    
    public void listSearchVeterinary(List<ConsultationEntryItem> listItens) {
        for (ConsultationEntryItem listIten : listItens) {
            List<People> list = getDaoGenerico().list("select p from VetConsultation v, Users u, People p \n"
                    + "where \n"
                    + "v.users.pkUser=u.pkUser and \n"
                    + "u.people.pkPerson=p.pkPerson and \n"
                    + "v.pkVetConsultation='" + listIten.getConsultation().getPkVetConsultation() + "'");
            if (list != null && !list.isEmpty()) {
                for (People people : list) {
                    listIten.setVetPerson(people);
                }
            }
        }
    }
}
