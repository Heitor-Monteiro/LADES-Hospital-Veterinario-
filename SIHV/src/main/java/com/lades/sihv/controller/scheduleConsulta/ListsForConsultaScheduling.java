/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.scheduleConsulta;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.GenericScheduling;
import com.lades.sihv.model.NewAnimalAndOwner;
import com.lades.sihv.model.Scheduling;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.ScheduleEvent;

/**
 *
 * @author thiberius
 */
public class ListsForConsultaScheduling extends AbstractBean {

    private List<Scheduling> listSchedule;
    private List<NewAnimalAndOwner> listTempCliData;
    private List<String> listEventID;

    public void popularListSchedule() {
        try {
            listSchedule = getDaoGenerico().list("select s from Scheduling s "
                    + "where s.statusService = 'agendado(a)'");

            listTempCliData = getDaoGenerico().list("select n from Scheduling s, NewAnimalAndOwner n "
                    + "where s.pkSchedule = n.schedulingPkSchedule and "
                    + "s.statusService = 'agendado(a)'");
        } catch (Exception e) {
            System.out.println("BACK-END WARNING: ERRO public void popularListSchedule(): " + e);
        }
    }

    public void popularListEventID(GenericScheduling geneScheduling) {
        try {
            listEventID = new ArrayList<>();
            for (ScheduleEvent objEvent : geneScheduling.getEventModel().getEvents()) {
                listEventID.add(objEvent.getId());
            }
        } catch (Exception e) {
            System.out.println("BACK-END WARNING: ERRO public void popularListEventID(): " + e);
        }
    }

    private void method(GenericScheduling geneScheduling, Scheduling schedule, String title) {
        geneScheduling.getEventModel().addEvent(new DefaultScheduleEvent(title,
                schedule.getSchedulingDate(), schedule.getSchedulingDate()));
    }

    public void popularEventModel(GenericScheduling geneScheduling) {
        popularListSchedule();
        try {
            for (Scheduling schedule : listSchedule) {
                boolean ownersHasAnimalsFK = schedule.getOwnersHasAnimals() != null;
                if (ownersHasAnimalsFK) {
                } else {
                    for (NewAnimalAndOwner tempCliData : listTempCliData) {
                        if (Objects.equals(schedule.getPkSchedule(), tempCliData.getScheduling().getPkSchedule())) {
                            method(geneScheduling, schedule, tempCliData.getAnimalName());
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("BACK-END WARNING: ERRO public void popularEventModel():" + e);
        }
        popularListEventID(geneScheduling);
    }
    
    //-GETs e SETs--------------------------------------------------------------

    public List<String> getListEventID() {
        return listEventID;
    }

    public List<Scheduling> getListSchedule() {
        return listSchedule;
    }

    public List<NewAnimalAndOwner> getListTempCliData() {
        return listTempCliData;
    }
    
    
}
