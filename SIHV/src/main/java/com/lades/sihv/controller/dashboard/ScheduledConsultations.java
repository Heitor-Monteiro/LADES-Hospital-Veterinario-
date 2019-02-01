/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.dashboard;

import com.lades.sihv.bean.AbstractBean;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class ScheduledConsultations extends AbstractBean {

    private int qtd = 0;
    private String scheduleCanceledInTheMonth = "0";
    private String confirmedSchedules = "0";

    public ScheduledConsultations() {
        List<?> list = getDaoGenerico().list("select s from Scheduling s \n"
                + "where \n"
                + "s.statusService='agendado(a)'");
        if (list != null && !list.isEmpty()) {
            qtd = list.size();
        }
        scheduleCanceledInTheMonth = scheduleInTheMonth("cancelado(a)");
        confirmedSchedules = scheduleInTheMonth("confirmado(a)");
    }

    private String scheduleInTheMonth(String statusService) {
        String quantityOfItems = "0";
        Calendar cal = GregorianCalendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int currentMonth = cal.get(Calendar.MONTH);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MONTH, currentMonth);
        int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
//        List<?> list = getDaoGenerico().list("select count(s.pkSchedule) from Scheduling s \n"
//                + "where \n"
//                + "s.statusService='" + statusService + "' and \n"
//                + "s.schedulingDate>='" + currentYear + "-" + (currentMonth + 1) + "-01 01:00:00' and \n"
//                + "s.schedulingDate<='" + currentYear + "-" + (currentMonth + 1) + "-" + day + " 23:59:59' ");
        List<?> list = getDaoGenerico().list("select count(s.pkSchedule) from Scheduling s \n"
                + "where \n"
                + "s.statusService='" + statusService + "'");
        if (list != null && !list.isEmpty()) {
            quantityOfItems = "" + list.get(0);
        }
        return quantityOfItems;
    }

    public int getQtd() {
        return qtd;
    }

    public String getScheduleCanceledInTheMonth() {
        return scheduleCanceledInTheMonth;
    }

    public String getConfirmedSchedules() {
        return confirmedSchedules;
    }
}
