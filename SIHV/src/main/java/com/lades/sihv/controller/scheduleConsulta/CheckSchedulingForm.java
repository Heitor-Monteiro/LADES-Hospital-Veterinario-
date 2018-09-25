/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.scheduleConsulta;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.model.NewAnimalAndOwner;
import com.lades.sihv.model.Scheduling;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class CheckSchedulingForm extends AbstractBean {

    public boolean checkScheduling(Scheduling schedule,
            NewAnimalAndOwner tempCliData) {
        boolean var = false;
        List<Boolean> x = new ArrayList<>();
        x.add(0, schedule.getSchedulingDate() != null);
        x.add(1, tempCliData.getAnimalName() != null);
        x.add(2, tempCliData.getProprietaryName() != null);
        x.add(3, tempCliData.getProprietaryPhone1() != null);
        if (x.get(0) && x.get(1) && x.get(2) && x.get(3)) {
            var = true;
        }
        return var;
    }
}
