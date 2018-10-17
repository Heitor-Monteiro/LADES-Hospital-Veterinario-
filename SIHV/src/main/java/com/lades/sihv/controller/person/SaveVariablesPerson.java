/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.person;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.BeautyText;
import com.lades.sihv.model.PhysicalPerson;
import com.lades.sihv.model.PhysicalPersonId;

/**
 *
 * @author thiberius
 */
public class SaveVariablesPerson extends AbstractBean {

    public void savePerson(VariablesPerson varPerson) {
        try {
            varPerson.getPerson().setNamePerson(new BeautyText()
                    .Captalizador(varPerson.getPerson().getNamePerson()));
            varPerson.getPerson().setLogicalExclusion(false);
            varPerson.getPerson().setRegistrationDate(getObjData());
            getDaoGenerico().save(varPerson.getPerson());
        } catch (Exception e) {
            System.out.println("BACK-END WARNING: ERRO  public void savePerson():" + e);
            getObjMessage().error("Erro detectado!", "public void savePerson():" + e);
        }
    }

    public void savePhysicalPerson(VariablesPerson varPerson) {
        try {
            PhysicalPersonId id = new PhysicalPersonId();
            id.setPeoplePkPerson(varPerson.getPerson().getPkPerson());
            varPerson.getPhysicalPerson().setId(id);
            getDaoGenerico().save(varPerson.getPhysicalPerson());
            PhysicalPerson tempPhysicalPerson = (PhysicalPerson) getDaoGenerico().list("select f from PhysicalPerson f \n"
                    + "where \n"
                    + "f.id.pkPhysicalPerson=(select  max(f.id.pkPhysicalPerson) from PhysicalPerson f) ").get(0);
            varPerson.setPhysicalPerson(tempPhysicalPerson);
        } catch (Exception e) {
            System.out.println("BACK-END WARNING: ERRO  public void savePhysicalPerson():" + e);
            getObjMessage().error("Erro detectado!", "public void savePhysicalPerson():" + e);
        }
    }

    public void saveCPF(VariablesPerson varPerson) {
        try {
            varPerson.getObjCpf().setPhysicalPerson(varPerson.getPhysicalPerson());
            getDaoGenerico().save(varPerson.getObjCpf());
        } catch (Exception e) {
            System.out.println("BACK-END WARNING: ERRO  public void saveCPF():" + e);
            getObjMessage().error("Erro detectado!", "public void saveCPF():" + e);
        }
    }

    public void saveRG(VariablesPerson varPerson) {
        try {
            varPerson.getObjRg().setPhysicalPerson(varPerson.getPhysicalPerson());
            getDaoGenerico().save(varPerson.getObjRg());
        } catch (Exception e) {
            System.out.println("BACK-END WARNING: ERRO  public void saveRG():" + e);
            getObjMessage().error("Erro detectado!", "public void saveRG():" + e);
        }
    }

    public void saveOwners(VariablesPerson varPerson) {
        try {
            varPerson.getOwner().setPeople(varPerson.getPerson());
            getDaoGenerico().save(varPerson.getOwner());
        } catch (Exception e) {
            System.out.println("BACK-END WARNING: ERRO  public void saveOwners():" + e);
            getObjMessage().error("Erro detectado!", "public void saveOwners():" + e);
        }
    }

}
