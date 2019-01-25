/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consultationEntryControl;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.model.Scheduling;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class SearchSchedulingEffective extends AbstractBean {

    public void listSchedulingEffective(List<ConsultationEntryItem> listItens) {
        for (ConsultationEntryItem listIten : listItens) {
            List<Scheduling> list = getDaoGenerico().list("select s from Scheduling s, VetConsultation v \n"
                    + "where \n"
                    + "v.scheduling.pkSchedule=s.pkSchedule and \n"
                    + "v.pkVetConsultation='" + listIten.getConsultation().getPkVetConsultation() + "'");
            if (list != null && !list.isEmpty()) {
                for (Scheduling scheduling : list) {
                    listIten.setScheduling(scheduling);
                }
            }
        }
    }
}
