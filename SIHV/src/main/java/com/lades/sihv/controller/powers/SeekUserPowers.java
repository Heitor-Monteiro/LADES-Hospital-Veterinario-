/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.powers;

import com.lades.sihv.controller.VariaveisDeSessao;
import com.lades.sihv.model.Powers;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class SeekUserPowers implements Serializable {

    private List<Powers> listPowers;

    private SeekUserPowers() {
    }

    public SeekUserPowers(VariaveisDeSessao variaveisDeSessao) {
        listPowers = variaveisDeSessao.getPowersUSER();
    }

    private boolean isSeekPower(String namePower) {
        boolean var = false;
        for (Powers power : listPowers) {
            if (power.getNamePower().equals(namePower)) {
                var = true;
                break;
            }
        }
        return var;
    }

    // Power: 1
    public boolean isViewRegisterUser() {
        return isSeekPower("registerUser");
    }

    // Power: 2
    public boolean isViewRegisterOwner() {
        return isSeekPower("registerOwner");
    }

    // Power: 3 
    public boolean isViewRegisterSmallAnimal() {
        return isSeekPower("registerSmallAnimal");
    }

    // Power: 4
    public boolean isViewMakeConsultations() {
        return isSeekPower("makeConsultations");
    }

    // Power: 5
    public boolean isViewManipulateConsultationSchedules() {
        return isSeekPower("manipulateConsultationSchedules");
    }

    // Power: 6
    public boolean isViewSearchSystemLogs() {
        return isSeekPower("searchSystemLogs");
    }

    // Power: 7
    public boolean isViewRegisterAndEditHOVETprocedures() {
        return isSeekPower("registerAndEditHOVETprocedures");
    }

    // Power: 8
    public boolean isViewDeclareSmallAnimalDeath() {
        return isSeekPower("declareSmallAnimalDeath");
    }

    // Power: 9 
    public boolean isViewPendingRadiographs() {
        return isSeekPower("pendingRadiographs");
    }
}
