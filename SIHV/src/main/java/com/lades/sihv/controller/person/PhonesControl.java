/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.person;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.ListRenderedFields;
import com.lades.sihv.model.NewAnimalAndOwner;
import com.lades.sihv.model.People;
import com.lades.sihv.model.Phones;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class PhonesControl extends AbstractBean {
    
    private Phones phone1;
    private Phones phone2;
    private Phones phone3;
    private final ListRenderedFields listRenderedFields;
    
    public PhonesControl() {
        phone1 = new Phones();
        phone2 = new Phones();
        phone3 = new Phones();
        listRenderedFields = new ListRenderedFields(3);
        listRenderedFields.startIndexListViewFields();
    }
    
    public void searchPhones(People person) {
        List<Phones> listPhones = getDaoGenerico().list("select phone from Phones phone, People p\n"
                + "where \n"
                + "p.pkPerson=phone.people.pkPerson and \n"
                + "p.pkPerson='" + person.getPkPerson() + "' ");
        
        if (!listPhones.isEmpty()) {
            if (listPhones.size() <= 3) {
                phone1 = (Phones) (((listPhones.size() - 3) == 0) ? listPhones.get(0) : new Phones());
                phone2 = (Phones) (((listPhones.size() - 2) == 1) ? listPhones.get(1) : new Phones());
                phone3 = (Phones) (((listPhones.size() - 1) == 2) ? listPhones.get(2) : new Phones());
            }
        }
        
    }
    
    public void coletarPhoneTemp(NewAnimalAndOwner tempCliData) {
        phone1.setNumberPhone(tempCliData.getProprietaryPhone1());
        setViewPhone2(!tempCliData.getProprietaryPhone2().isEmpty());
        if (isViewPhone2()) {
            phone2.setNumberPhone(tempCliData.getProprietaryPhone2());
        }
        setViewPhone3(!tempCliData.getProprietaryPhone3().isEmpty());
        if (isViewPhone3()) {
            phone3.setNumberPhone(tempCliData.getProprietaryPhone3());
        }
    }
    
    public void savePhones(People person) {
        phone1.setPeople(person);
        getDaoGenerico().save(phone1);
        if (listRenderedFields.getListViewFields(0).isViewVariableBoolean()) {
            phone2.setPeople(person);
            getDaoGenerico().save(phone2);
        }
        if (listRenderedFields.getListViewFields(1).isViewVariableBoolean()) {
            phone3.setPeople(person);
            getDaoGenerico().save(phone3);
        }
    }

    //-GETs e SETs--------------------------------------------------------------
    public Phones getPhone1() {
        return phone1;
    }
    
    public void setPhone1(Phones phone1) {
        this.phone1 = phone1;
    }
    
    public Phones getPhone2() {
        return phone2;
    }
    
    public void setPhone2(Phones phone2) {
        this.phone2 = phone2;
    }
    
    public Phones getPhone3() {
        return phone3;
    }
    
    public void setPhone3(Phones phone3) {
        this.phone3 = phone3;
    }

    //-VIEWS--------------------------------------------------------------------
    public boolean isViewPhone2() {
        return listRenderedFields.getListViewFields(0).isViewVariableBoolean();
    }
    
    public void setViewPhone2(boolean viewPhone2) {
        listRenderedFields.getListViewFields(0).setViewVariableBoolean(viewPhone2);
    }
    
    public boolean isViewPhone3() {
        if (!listRenderedFields.getListViewFields(0).isViewVariableBoolean()) {
            listRenderedFields.getListViewFields(1).setViewVariableBoolean(false);
        }
        return listRenderedFields.getListViewFields(1).isViewVariableBoolean();
    }
    
    public void setViewPhone3(boolean viewPhone3) {
        listRenderedFields.getListViewFields(1).setViewVariableBoolean(viewPhone3);
    }
}
