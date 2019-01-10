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
public class XrayPending extends AbstractBean {

    private int qtd = 0;
    private String xrayCurrentYear = "0";
    private String xrayCurrentMonth = "0";

    public XrayPending() {
        List<?> list = getDaoGenerico().list("select e from ExameImage e \n"
                + "where \n"
                + "e.tipo='RaioX' and \n"
                + "e.statusExamImage='Pendente'");
        if (list != null && !list.isEmpty()) {
            qtd = list.size();
        }
        xrayCurrentYear();
        xrayCurrentMonth();
    }

    private void xrayCurrentYear() {
        Calendar cal = GregorianCalendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MONTH, 11);
        int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        List<?> list = getDaoGenerico().list("select count(e.id.pkExameImage) from ExameImage e \n"
                + "where \n"
                + "e.tipo='RaioX' and \n"
                + "e.statusExamImage='Atendido' and \n"
                + "e.atendimentoData>='" + currentYear + "-01-01 01:00:00' and \n"
                + "e.atendimentoData<='" + currentYear + "-12-" + day + " 23:59:59' ");
        if (list != null && !list.isEmpty()) {
            xrayCurrentYear = "" + list.get(0);
        }
    }

    private void xrayCurrentMonth() {
        Calendar cal = GregorianCalendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        int currentMonth = cal.get(Calendar.MONTH);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MONTH, currentMonth);
        int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        List<?> list = getDaoGenerico().list("select count(e.id.pkExameImage) from ExameImage e \n"
                + "where \n"
                + "e.tipo='RaioX' and \n"
                + "e.statusExamImage='Atendido' and \n"
                + "e.atendimentoData>='" + currentYear + "-" + (currentMonth + 1) + "-01 01:00:00' and \n"
                + "e.atendimentoData<='" + currentYear + "-" + (currentMonth + 1) + "-" + day + " 23:59:59' ");
        if (list != null && !list.isEmpty()) {
            xrayCurrentMonth = "" + list.get(0);
        }
    }

    public int getQtd() {
        return qtd;
    }

    public String getXrayCurrentYear() {
        return xrayCurrentYear;
    }

    public String getXrayCurrentMonth() {
        return xrayCurrentMonth;
    }
}
