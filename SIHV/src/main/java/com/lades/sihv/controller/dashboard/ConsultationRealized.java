/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.dashboard;

import com.lades.sihv.bean.AbstractBean;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class ConsultationRealized extends AbstractBean {

    private String consultationsInTheCurrentYear = "0";
    private String consultationsInTheCurrentMonth = "0";
    private String consultationsInTheCurrentWeek = "0";

    public ConsultationRealized() {
        consultationsInTheCurrentYear();
        consultationsInTheCurrentMonth();
        consultationsInTheCurrentWeek();
    }

    private void consultationsInTheCurrentYear() {
        Calendar cal = GregorianCalendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MONTH, 11);
        int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        List<?> list = getDaoGenerico().list("select count(v.pkVetConsultation) from VetConsultation v, Scheduling s \n"
                + "where \n"
                + "v.scheduling.pkSchedule=s.pkSchedule and \n"
                + "s.statusService='efetivado(a)' and \n"
                + "v.ownersHasAnimals.pkOwnersHasAnimals > 0 and \n"
                + "v.users.pkUser > 0 and \n"
                + "v.applicationDate >='" + currentYear + "-01-01 01:00:00' and \n"
                + "v.applicationDate <='" + currentYear + "-12-" + day + " 23:59:59' ");
        if (list != null && !list.isEmpty()) {
            consultationsInTheCurrentYear = "" + list.get(0);
        }
    }

    private void consultationsInTheCurrentMonth() {
        Calendar cal = GregorianCalendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int currentMonth = cal.get(Calendar.MONTH);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MONTH, currentMonth);
        int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        List<?> list = getDaoGenerico().list("select count(v.pkVetConsultation) from VetConsultation v, Scheduling s \n"
                + "where \n"
                + "v.scheduling.pkSchedule=s.pkSchedule and \n"
                + "s.statusService='efetivado(a)' and \n"
                + "v.ownersHasAnimals.pkOwnersHasAnimals > 0 and \n"
                + "v.users.pkUser > 0 and \n"
                + "v.applicationDate >='" + currentYear + "-" + (currentMonth + 1) + "-01 01:00:00' and \n"
                + "v.applicationDate <='" + currentYear + "-" + (currentMonth + 1) + "-" + day + " 23:59:59' ");
        if (list != null && !list.isEmpty()) {
            consultationsInTheCurrentMonth = "" + list.get(0);
        }
    }

    private void consultationsInTheCurrentWeek() {
        Calendar cal = GregorianCalendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int currentMonth = cal.get(Calendar.MONTH);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MONTH, currentMonth);
        String firstDay = firstAndLastDayOfTheWeek(getObjData(), true);
        String lastDay = firstAndLastDayOfTheWeek(getObjData(), false);
        List<?> list = getDaoGenerico().list("select count(v.pkVetConsultation) from VetConsultation v, Scheduling s \n"
                + "where \n"
                + "v.scheduling.pkSchedule=s.pkSchedule and \n"
                + "s.statusService='efetivado(a)' and \n"
                + "v.ownersHasAnimals.pkOwnersHasAnimals > 0 and \n"
                + "v.users.pkUser > 0 and \n"
                + "v.applicationDate >='" + currentYear + "-" + (currentMonth + 1) + "-" + firstDay + " 01:00:00' and \n"
                + "v.applicationDate <='" + currentYear + "-" + (currentMonth + 1) + "-" + lastDay + " 23:59:59' ");
        if (list != null && !list.isEmpty()) {
            consultationsInTheCurrentWeek = "" + list.get(0);
        }
    }

    private String firstAndLastDayOfTheWeek(Date data, boolean isPrimeiro) {
        DateFormat formatUS = new SimpleDateFormat("dd");
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(data);
        if (isPrimeiro) {
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            calendar.set(Calendar.AM_PM, Calendar.AM);
            calendar.set(Calendar.HOUR, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
        } else {
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            calendar.set(Calendar.AM_PM, Calendar.PM);
            calendar.set(Calendar.HOUR, 11);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
        }
        return formatUS.format(calendar.getTime());
    }

    // GETs & SETs -------------------------------------------------------------
    public String getConsultationsInTheCurrentYear() {
        return consultationsInTheCurrentYear;
    }

    public String getConsultationsInTheCurrentMonth() {
        return consultationsInTheCurrentMonth;
    }

    public String getConsultationsInTheCurrentWeek() {
        return consultationsInTheCurrentWeek;
    }
}
