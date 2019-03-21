/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.person;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.BeautyText;
import com.lades.sihv.controller.ModuleToCollectError;
import com.lades.sihv.controller.Security;
import com.lades.sihv.model.PhysicalPerson;
import com.lades.sihv.model.PhysicalPersonId;

/**
 *
 * @author thiberius
 */
public class SaveVariablesPerson extends AbstractBean {

    public void savePerson(VariablesPerson varPerson) {
        System.out.println("►►►►►►►►►►►►► "
                + "SaveVariablesPerson > public void savePerson()");
        try {
            if (varPerson.getPerson().getPkPerson() != null) {
                getDaoGenerico().update(varPerson.getPerson());
            } else {
                varPerson.getPerson().setNamePerson(new BeautyText()
                        .Captalizador(varPerson.getPerson().getNamePerson()));
                varPerson.getPerson().setLogicalExclusion(false);
                varPerson.getPerson().setEmail(varPerson.getPerson().getEmail().toLowerCase());
                varPerson.getPerson().setRegistrationDate(getObjData());
                getDaoGenerico().save(varPerson.getPerson());
            }
        } catch (Exception e) {
            System.err.println("►►►►►►►►►►►►► ERRO public void savePerson(): " + e.toString());
            new ModuleToCollectError().erroPage500("SaveVariablesPerson > savePerson", e.toString());
        }
    }

    public void savePhysicalPerson(VariablesPerson varPerson) {
        System.out.println("►►►►►►►►►►►►► "
                + "SaveVariablesPerson > public void savePhysicalPerson()");
        try {
            if (varPerson.getPhysicalPerson().getId() != null) {
                getDaoGenerico().update(varPerson.getPhysicalPerson());
            } else {
                PhysicalPersonId id = new PhysicalPersonId();
                id.setPeoplePkPerson(varPerson.getPerson().getPkPerson());
                varPerson.getPhysicalPerson().setId(id);
                getDaoGenerico().save(varPerson.getPhysicalPerson());
                PhysicalPerson tempPhysicalPerson = (PhysicalPerson) getDaoGenerico().list("select f from PhysicalPerson f \n"
                        + "where \n"
                        + "f.id.pkPhysicalPerson=(select  max(f.id.pkPhysicalPerson) from PhysicalPerson f) ").get(0);
                varPerson.setPhysicalPerson(tempPhysicalPerson);
            }
        } catch (Exception e) {
            System.err.println("►►►►►►►►►►►►► ERRO public void savePhysicalPerson(): " + e.toString());
            new ModuleToCollectError().erroPage500("SaveVariablesPerson > savePhysicalPerson", e.toString());
        }
    }

    public void saveCPF(VariablesPerson varPerson) {
        System.out.println("►►►►►►►►►►►►► "
                + "SaveVariablesPerson > public void saveCPF()");
        try {
            if (varPerson.getObjCpf().getPkCpf() != null) {
                getDaoGenerico().update(varPerson.getObjCpf());
            } else {
                varPerson.getObjCpf().setPhysicalPerson(varPerson.getPhysicalPerson());
                getDaoGenerico().save(varPerson.getObjCpf());
            }
        } catch (Exception e) {
            System.err.println("►►►►►►►►►►►►► ERRO public void saveCPF(): " + e.toString());
            new ModuleToCollectError().erroPage500("SaveVariablesPerson > saveCPF", e.toString());
        }
    }

    public void saveRG(VariablesPerson varPerson) {
        System.out.println("►►►►►►►►►►►►► "
                + "SaveVariablesPerson > public void saveRG()");
        try {
            if (varPerson.getObjRg().getPkRg() != null) {
                getDaoGenerico().update(varPerson.getObjRg());
            } else {
                varPerson.getObjRg().setPhysicalPerson(varPerson.getPhysicalPerson());
                getDaoGenerico().save(varPerson.getObjRg());
            }
        } catch (Exception e) {
            System.err.println("►►►►►►►►►►►►► ERRO public void saveRG(): " + e.toString());
            new ModuleToCollectError().erroPage500("SaveVariablesPerson > saveRG", e.toString());
        }
    }

    public void saveOwners(VariablesPerson varPerson) {
        System.out.println("►►►►►►►►►►►►► "
                + "SaveVariablesPerson > public void saveOwners()");
        try {
            if (varPerson.getOwner().getPkOwner() != null) {
                getDaoGenerico().update(varPerson.getOwner());
            } else {
                varPerson.getOwner().setPeople(varPerson.getPerson());
                getDaoGenerico().save(varPerson.getOwner());
            }
        } catch (Exception e) {
            System.err.println("►►►►►►►►►►►►► ERRO public void saveOwners(): " + e.toString());
            new ModuleToCollectError().erroPage500("SaveVariablesPerson > saveOwners", e.toString());
        }
    }

    public void saveUser(VariablesPerson varPerson) {
        System.out.println("►►►►►►►►►►►►► "
                + "SaveVariablesPerson > public void saveUser()");
        try {
            varPerson.getUser().setUserName(varPerson.getUser().getUserName().toLowerCase());
            varPerson.getUser().setPassword(new Security().encrypter(varPerson.getUser().getPassword()));
            if (varPerson.getUser().getPkUser() != null) {
                getDaoGenerico().update(varPerson.getUser());
            } else {
                varPerson.getUser().setPeople(varPerson.getPerson());
                getDaoGenerico().save(varPerson.getUser());
            }
        } catch (Exception e) {
            System.err.println("►►►►►►►►►►►►► ERRO public void saveUser(): " + e.toString());
            new ModuleToCollectError().erroPage500("SaveVariablesPerson > saveUser", e.toString());
        }
    }
}
