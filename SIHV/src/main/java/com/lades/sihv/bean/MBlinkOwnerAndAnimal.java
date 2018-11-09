/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.bean;

import com.lades.sihv.controller.person.IntercalateCpfRg;
import java.util.List;
import com.lades.sihv.controller.ListRenderedFields;
import com.lades.sihv.controller.RenderedFields;
import com.lades.sihv.controller.address.AddressControl;
import com.lades.sihv.controller.animal.AnimalControl;
import com.lades.sihv.controller.person.PhonesControl;
import com.lades.sihv.controller.person.SaveVariablesPerson;
import com.lades.sihv.controller.person.VariablesPerson;
import com.lades.sihv.controller.person.VerifyPersonDocument;
import com.lades.sihv.controller.scheduleConsulta.ConfirmOwnerPresence;
import com.lades.sihv.model.NewAnimalAndOwner;
import com.lades.sihv.model.Scheduling;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author thiberius
 */
@ManagedBean(name = "MBlinkOwnerAndAnimal")
@ViewScoped
public class MBlinkOwnerAndAnimal extends AbstractBean {

    private VariablesPerson varPerson;
    private IntercalateCpfRg intercalateCpfRg;
    private VerifyPersonDocument verifyPersonDocument;

    public AddressControl addressControl;
    private PhonesControl phonesControl;
    private AnimalControl animalControl;

    private ListRenderedFields listRenderedFields;

    private Scheduling schedule;
    private NewAnimalAndOwner tempCliData;
    private List<Object> list;

    @PostConstruct
    public void init() {
        System.out.println("BACK-END WARNING: MBlinkOwnerAndAnimal initiated");
        varPerson = new VariablesPerson();
        phonesControl = new PhonesControl();
        intercalateCpfRg = new IntercalateCpfRg();
        verifyPersonDocument = new VerifyPersonDocument();
        //----------------------------------------------------------------------
        animalControl = new AnimalControl();
        listRenderedFields = new ListRenderedFields(3);
        listRenderedFields.startIndexListViewFields();
        //------------ Apagar linha quando check CNPJ for adaptado--------------
        listRenderedFields.getListViewFields(0).setViewVariableBoolean(true);
        //----------------------------------------------------------------------
        addressControl = new AddressControl();
        addressControl.loadLists();
        loadData();
    }

    private void loadData() {
        list = (List<Object>) getVariaveisDeSessao().getObjetoTemp();
        schedule = (Scheduling) list.get(0);
        tempCliData = (NewAnimalAndOwner) list.get(1);
        varPerson.getPerson().setNamePerson(tempCliData.getProprietaryName());
        animalControl.getVarAnimal().getAnimal().setAnimalName(tempCliData.getAnimalName());
        phonesControl.coletarPhoneTemp(tempCliData);
    }

    public void verifyPersonDocument() {
        if (getPhysicalOrLegalInterim().isViewVariableBoolean()) {
            //PhysicalPerson
            boolean newPerson = verifyPersonDocument.checkDocumentPhysicalPerson(varPerson, addressControl);
            if (!newPerson) {
                animalControl.methodSmallAnimalSpecies();
                animalControl.methodSearchRegisteredAnimal(varPerson.getPerson());
//                phonesControl.searchPhones(varPerson.getPerson());
            } else {
                animalControl.getVarAnimal().getStatusNewAnimal().setViewVariableBoolean(true);
                animalControl.getGenerateRghv().methodNewRghvAnimal(animalControl.getVarAnimal(), "P");
                animalControl.methodSmallAnimalSpecies();
            }
        } else {
            // LegalPerson
        }

    }

    public void salvarTeste() {

        SaveVariablesPerson savePerson = new SaveVariablesPerson();

        if (getPhysicalOrLegalInterim().isViewVariableBoolean()) {

            if (verifyPersonDocument.isNewPerson()) {
                savePerson.savePerson(varPerson);
                savePerson.savePhysicalPerson(varPerson);
                phonesControl.savePhones(varPerson.getPerson());
                addressControl.saveAddress(varPerson.getPerson());
                savePerson.saveOwners(varPerson);
            }

            if (verifyPersonDocument.isCheckCPF()
                    && verifyPersonDocument.isNewCPF()) {
                savePerson.saveCPF(varPerson);
            }

            if (verifyPersonDocument.isCheckRG()
                    && verifyPersonDocument.isNewRG()) {
                savePerson.saveRG(varPerson);
            }

            if (animalControl.getVarAnimal().getStatusNewAnimal().isViewVariableBoolean()) {
                animalControl.saveNewSmallAnimal();
                animalControl.saveOwnersHasAnimals(varPerson);
            }

            new ConfirmOwnerPresence()
                    .methodConfirmOwnerPresence(schedule,
                             tempCliData, varPerson, phonesControl,
                             animalControl.getVarAnimal());
            listRenderedFields.getListViewFields(1).setViewVariableBoolean(true);
            getObjMessage().info("Animal confirmado para consulta", "");
        } else {

        }
    }

    //-GETs e SETs--------------------------------------------------------------
    public VariablesPerson getVarPerson() {
        return varPerson;
    }

    public AddressControl getAddressControl() {
        return addressControl;
    }

    public PhonesControl getPhonesControl() {
        return phonesControl;
    }

    public RenderedFields getPhysicalOrLegalInterim() {
        return listRenderedFields.getListViewFields(0);
    }

    public IntercalateCpfRg getIntercalateCpfRg() {
        return intercalateCpfRg;
    }
    
    public RenderedFields getIntercalateSaveButtonsAndReturnToCalendar() {
        return listRenderedFields.getListViewFields(1);
    }

    public AnimalControl getAnimalControl() {
        return animalControl;
    }

}
