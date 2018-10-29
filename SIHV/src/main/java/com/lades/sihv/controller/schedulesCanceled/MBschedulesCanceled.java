/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.schedulesCanceled;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.model.NewAnimalAndOwner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.lades.sihv.model.Scheduling;

//@author thiberius
@ManagedBean(name = "MBschedulesCanceled")
@ViewScoped

public class MBschedulesCanceled extends AbstractBean {

    private DateFormat formatUS;
    private Date dateInitial;
    private Date dateEnd;
    private List<TempListSchedulesCanceled> tempListSchedulesCanceled;

    @PostConstruct
    public void init() {
        System.out.println("►►►►►►►►►►►►► MBschedulesCanceled initiated");
        tempListSchedulesCanceled = new ArrayList<>();
        formatUS = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    }

    public void searchForCanceledSchedules() {
        try {
            tempListSchedulesCanceled.clear();
            List<?> list = getDaoGenerico().list("select s, n from Scheduling s, NewAnimalAndOwner n \n"
                    + "where \n"
                    + "s.pkSchedule=n.scheduling.pkSchedule and \n"
                    + "s.statusService = 'cancelado(a)' and \n"
                    + "s.schedulingDate>='" + formatUS.format(dateInitial) + "' and \n"
                    + "s.schedulingDate<='" + formatUS.format(dateEnd) + "' ");
            if (list.size() > 0) {
                for (Object[] object : (List<Object[]>) list) {
                    TempListSchedulesCanceled obj = new TempListSchedulesCanceled();
                    obj.setSchedule((Scheduling) object[0]);
                    obj.setNewAnimalAndOwner((NewAnimalAndOwner) object[1]);
                    tempListSchedulesCanceled.add(obj);
                }
                getObjMessage().info("Itens encontrados!",
                         "Agendas canceladas no período estipulado: "
                        + tempListSchedulesCanceled.size());
            } else {
                getObjMessage().info("Não a agendas canceladas no período estipulado","");
            }

        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO public void searchForCanceledSchedules():" + e);
        }
    }

    //-GETs e SETs--------------------------------------------------------------
    public Date getDateInitial() {
        return dateInitial;
    }

    public void setDateInitial(Date dateInitial) {
        this.dateInitial = dateInitial;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public List<TempListSchedulesCanceled> getTempListSchedulesCanceled() {
        return tempListSchedulesCanceled;
    }
    
    public boolean isViewTableSchedulesCanceled() {
        return !tempListSchedulesCanceled.isEmpty();
    }

}
