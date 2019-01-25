/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consultationEntryControl;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.VariablesSearch;
import com.lades.sihv.model.VetConsultation;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class PerformedConsultation extends AbstractBean {

    private final DateFormat formatUS = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

    public void listPerformedConsultation(VariablesSearch objVarSearch, List<ConsultationEntryItem> listItens) {
        String x = formatUS.format(objVarSearch.getDateInitial()).substring(0, 11) + "01:00:00";
        String y = formatUS.format(objVarSearch.getDateEnd()).substring(0, 11) + "23:59:59";

        List<VetConsultation> list = getDaoGenerico().list("select v from VetConsultation v, Scheduling s \n"
                + "where \n"
                + "v.scheduling.pkSchedule=s.pkSchedule and \n"
                + "s.statusService='efetivado(a)' and \n"
                + "v.ownersHasAnimals.pkOwnersHasAnimals > 0 and \n"
                + "v.users.pkUser > 0 and \n"
                + "v.applicationDate >='" + x + " 01:00:00' and \n"
                + "v.applicationDate <='" + y + " 23:59:59' ");
        if (list != null && !list.isEmpty()) {
            for (VetConsultation consult : list) {
                ConsultationEntryItem obj = new ConsultationEntryItem();
                obj.setConsultation(consult);
                listItens.add(obj);
            }
        }
    }
}
