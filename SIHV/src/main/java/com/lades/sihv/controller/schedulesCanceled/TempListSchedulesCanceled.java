/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.schedulesCanceled;

import com.lades.sihv.model.NewAnimalAndOwner;
import com.lades.sihv.model.Scheduling;
import java.io.Serializable;

/**
 *
 * @author thiberius
 */
public class TempListSchedulesCanceled implements Serializable {
    private Scheduling schedule;
    private NewAnimalAndOwner newAnimalAndOwner;
    
    // GETs & SETs -------------------------------------------------------------

    public Scheduling getSchedule() {
        return schedule;
    }

    public void setSchedule(Scheduling schedule) {
        this.schedule = schedule;
    }

    public NewAnimalAndOwner getNewAnimalAndOwner() {
        return newAnimalAndOwner;
    }

    public void setNewAnimalAndOwner(NewAnimalAndOwner newAnimalAndOwner) {
        this.newAnimalAndOwner = newAnimalAndOwner;
    }
    
}
