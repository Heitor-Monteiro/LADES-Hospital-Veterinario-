/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consulta;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.model.Scheduling;

/**
 *
 * @author thiberius
 */
public class ControllerScheduling extends AbstractBean {

    private Scheduling schedule;
    
    
    
    
    
    

    public void ConfirmScheduling() {
        try {

            if (schedule.getRegisterClient().equals("Novo")) {
                
                schedule.setStatusService("");
                getDaoGenerico().save(schedule);
            } else if (schedule.getRegisterClient().equals("Cadastrado")) {

            }

        } catch (Exception e) {
            System.out.println("BACK-END WARNING: ERROR [ public void ConfirmScheduling() ] "
                    + e.getMessage());
        }
    }

    //GETs & SETs
    public Scheduling getSchedule() {
        if (schedule == null) {
            schedule = new Scheduling();
        }
        return schedule;
    }

    public void setSchedule(Scheduling schedule) {
        this.schedule = schedule;
    }

}
