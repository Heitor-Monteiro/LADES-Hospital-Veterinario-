/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.scheduleConsulta;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.animal.VariablesAnimal;
import com.lades.sihv.controller.person.PhonesControl;
import com.lades.sihv.controller.person.VariablesPerson;
import com.lades.sihv.model.NewAnimalAndOwner;
import com.lades.sihv.model.Scheduling;

/**
 *
 * @author thiberius
 */
public class ConfirmOwnerPresence extends AbstractBean {

    public void methodConfirmOwnerPresence(Scheduling schedule,
            NewAnimalAndOwner tempCliData,
            VariablesPerson varPerson,
            PhonesControl phonesControl,
            VariablesAnimal varAnimal) {
        try {
            schedule.setStatusService("confirmado(a)");
            schedule.setOwnersHasAnimals(varAnimal.getOwnersHasAnimals());
            getDaoGenerico().update(schedule);
            //----------------------------------------------------------------------
            tempCliData.setProprietaryName(varPerson.getPerson().getNamePerson());
            tempCliData.setAnimalName(varAnimal.getAnimal().getAnimalName());
            tempCliData.setProprietaryPhone1(phonesControl.getPhone1().getNumberPhone());
            if (phonesControl.getPhone2().getNumberPhone() != null) {
                if (!phonesControl.getPhone2().getNumberPhone().isEmpty()) {
                    tempCliData.setProprietaryPhone2(phonesControl.getPhone2().getNumberPhone());
                }
            }
            if (phonesControl.getPhone3().getNumberPhone() != null) {
                if (!phonesControl.getPhone3().getNumberPhone().isEmpty()) {
                    tempCliData.setProprietaryPhone3(phonesControl.getPhone3().getNumberPhone());
                }
            }
            getDaoGenerico().update(tempCliData);
        } catch (Exception e) {
            System.out.println("BACK-END WARNING: ERRO public void methodConfirmOwnerPresence():" + e);
        }
    }
}
