/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.person;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.model.CpfId;
import com.lades.sihv.model.PhysicalPersonId;
import com.lades.sihv.model.RgId;

/**
 *
 * @author thiberius
 */
public class SaveVariablesPerson extends AbstractBean {

    public void savePerson(VariablesPerson varPerson) {
        try {
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
        } catch (Exception e) {
            System.out.println("BACK-END WARNING: ERRO  public void savePhysicalPerson():" + e);
            getObjMessage().error("Erro detectado!", "public void savePhysicalPerson():" + e);
        }
    }

    public void saveCPF(VariablesPerson varPerson) {
        try {
            CpfId cpfId = new CpfId();
            cpfId.setPhysicalPersonPkPhysicalPerson(varPerson.getPhysicalPerson().getId().getPkPhysicalPerson());
            cpfId.setPhysicalPersonPeoplePkPerson(varPerson.getPhysicalPerson().getId().getPeoplePkPerson());
            varPerson.getObjCpf().setId(cpfId);
            getDaoGenerico().save(varPerson.getObjCpf());
        } catch (Exception e) {
            System.out.println("BACK-END WARNING: ERRO  public void saveCPF():" + e);
            getObjMessage().error("Erro detectado!", "public void saveCPF():" + e);
        }
    }

    public void saveRG(VariablesPerson varPerson) {
        try {
            RgId rgId = new RgId();
            rgId.setPhysicalPersonPkPhysicalPerson(varPerson.getPhysicalPerson().getId().getPkPhysicalPerson());
            rgId.setPhysicalPersonPeoplePkPerson(varPerson.getPhysicalPerson().getId().getPeoplePkPerson());
            varPerson.getObjRg().setId(rgId);
            getDaoGenerico().save(varPerson.getObjRg());
        } catch (Exception e) {
            System.out.println("BACK-END WARNING: ERRO  public void saveRG():" + e);
            getObjMessage().error("Erro detectado!", "public void saveRG():" + e);
        }
    }

}
