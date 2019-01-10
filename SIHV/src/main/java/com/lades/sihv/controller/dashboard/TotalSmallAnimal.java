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
public class TotalSmallAnimal extends AbstractBean {

    private String qtd = "0";
    private String qtdCurrentYear = "0";
    private String qtdCurrentMonth = "0";

    public TotalSmallAnimal() {
        List<?> list = getDaoGenerico().list("select max(s.pkSmallAnimal) from SmallAnimal s");
        if (list != null && !list.isEmpty()) {
            qtd = "" + list.get(0);
        }

        registeredInTheCurrentYear();
        currentMonth();
    }

    private void registeredInTheCurrentYear() {
        Calendar cal = GregorianCalendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MONTH, 11);
        int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        List<?> list = getDaoGenerico().list("select count(s.pkSmallAnimal) from SmallAnimal s, Animals a \n"
                + "where \n"
                + "a.pkAnimal=s.animals.pkAnimal and \n"
                + "a.registrationDate>='" + currentYear + "-01-01 01:00:00' and \n"
                + "a.registrationDate<='" + currentYear + "-12-" + day + " 23:59:59' ");
        if (list != null && !list.isEmpty()) {
            qtdCurrentYear = "" + list.get(0);
        }
    }

    private void currentMonth() {
        Calendar cal = GregorianCalendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int currentMonth = cal.get(Calendar.MONTH);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MONTH, currentMonth);
        int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        List<?> list = getDaoGenerico().list("select count(s.pkSmallAnimal) from SmallAnimal s, Animals a \n"
                + "where \n"
                + "a.pkAnimal=s.animals.pkAnimal and \n"
                + "a.registrationDate>='" + currentYear + "-" + (currentMonth + 1) + "-01 01:00:00' and \n"
                + "a.registrationDate<='" + currentYear + "-" + (currentMonth + 1) + "-" + day + " 23:59:59' ");
        if (list != null && !list.isEmpty()) {
            qtdCurrentMonth = "" + list.get(0);
        }
    }

    public String getQtd() {
        return qtd;
    }

    public String getQtdCurrentYear() {
        return qtdCurrentYear;
    }

    public String getQtdCurrentMonth() {
        return qtdCurrentMonth;
    }
}
