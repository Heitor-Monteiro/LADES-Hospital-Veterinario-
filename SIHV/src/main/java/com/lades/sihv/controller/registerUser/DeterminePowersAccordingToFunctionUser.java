/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.registerUser;

import com.lades.sihv.controller.ModuleToCollectError;
import com.lades.sihv.controller.person.VariablesPerson;
import com.lades.sihv.model.Powers;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class DeterminePowersAccordingToFunctionUser {

    public void determinePowersAccordingToFunctionUser(VariablesPerson varPerson,
            List<Powers> listPowers,
            List<Powers> selectPower) {
        System.out.println("►►►►►►►►►►►►► "
                + "DeterminePowersAccordingToFunctionUser "
                + "> public void determinePowersAccordingToFunctionUser()");
        try {
            selectPower.clear();
            String var = varPerson.getUser().getUserFunction();
            switch (varPerson.getUser().getUserProfile()) {
                case "médico veterinário":
                    if (var.equals("residente")) {
                        selectPower.add(listPowers.get(3));
                        System.out.println("►►►►►►►►►►►►► "
                                + listPowers.get(3).getDescriptionPower());
                        selectPower.add(listPowers.get(7));
                        System.out.println("►►►►►►►►►►►►► "
                                + listPowers.get(7).getDescriptionPower());
                    } else if (var.equals("docente") || var.equals("técnico")) {
                        selectPower.add(listPowers.get(0));
                        System.out.println("►►►►►►►►►►►►► "
                                + listPowers.get(0).getDescriptionPower());
                        selectPower.add(listPowers.get(3));
                        System.out.println("►►►►►►►►►►►►► "
                                + listPowers.get(3).getDescriptionPower());
                        selectPower.add(listPowers.get(5));
                        System.out.println("►►►►►►►►►►►►► "
                                + listPowers.get(5).getDescriptionPower());
                        selectPower.add(listPowers.get(6));
                        System.out.println("►►►►►►►►►►►►► "
                                + listPowers.get(6).getDescriptionPower());
                        selectPower.add(listPowers.get(7));
                        System.out.println("►►►►►►►►►►►►► "
                                + listPowers.get(7).getDescriptionPower());
                    }
                    break;
                case "discente":
                    if (var.equals("recepcionista")) {
                        selectPower.add(listPowers.get(1));
                        System.out.println("►►►►►►►►►►►►► "
                                + listPowers.get(1).getDescriptionPower());
                        selectPower.add(listPowers.get(2));
                        System.out.println("►►►►►►►►►►►►► "
                                + listPowers.get(2).getDescriptionPower());
                        selectPower.add(listPowers.get(4));
                        System.out.println("►►►►►►►►►►►►► "
                                + listPowers.get(4).getDescriptionPower());
                    }
                    break;
                case "radiologista":
                    selectPower.add(listPowers.get(8));
                    System.out.println("►►►►►►►►►►►►► "
                            + listPowers.get(8).getDescriptionPower());
                    break;
            }
        } catch (Exception e) {
            System.err.println("►►►►►►►►►►►►► ERRO public void determinePowersAccordingToFunctionUser(): " + e.toString());
            new ModuleToCollectError().erroPage500("DeterminePowersAccordingToFunctionUser > determinePowersAccordingToFunctionUser", e.toString());
        }
    }
}
