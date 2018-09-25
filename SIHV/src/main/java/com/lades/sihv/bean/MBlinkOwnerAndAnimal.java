/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.bean;

import com.lades.sihv.controller.person.PessoaCheckExistLogin;
import com.lades.sihv.controller.person.PessoaCheckExistCpfCnpj;
import com.lades.sihv.controller.person.PessoaCheckCPF;
import com.lades.sihv.controller.person.PessoaCheckCNPJ;
import com.lades.sihv.controller.person.PessoaPadraoCaracter;
import com.lades.sihv.controller.person.IntercalateCpfRg;
import java.util.ArrayList;
import java.util.List;
import com.lades.sihv.model.People;
import com.lades.sihv.model.PhysicalPerson;
import com.lades.sihv.model.PhysicalPersonId;
import com.lades.sihv.model.Cpf;
import com.lades.sihv.model.CpfId;
import com.lades.sihv.model.Rg;
import com.lades.sihv.model.RgId;
import com.lades.sihv.model.LegalPerson;
import com.lades.sihv.model.Owners;
import com.lades.sihv.model.Animals;
import com.lades.sihv.controller.ListRenderedFields;
import com.lades.sihv.controller.RenderedFields;
import com.lades.sihv.controller.address.AddressControl;
import com.lades.sihv.controller.person.PhonesControl;
import com.lades.sihv.controller.person.SaveVariablesPerson;
import com.lades.sihv.controller.person.VariablesPerson;
import com.lades.sihv.controller.person.VerifyPersonDocument;
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
    private Animals animal;

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
        animal = new Animals();
        listRenderedFields = new ListRenderedFields(3);
        listRenderedFields.startIndexListViewFields();
        addressControl = new AddressControl();
        addressControl.loadLists();
        loadData();
    }

    private void loadData() {
        list = (List<Object>) getVariaveisDeSessao().getObjetoTemp();
        schedule = (Scheduling) list.get(0);
        tempCliData = (NewAnimalAndOwner) list.get(1);
        varPerson.getPerson().setNamePerson(tempCliData.getProprietaryName());
        animal.setAnimalName(tempCliData.getAnimalName());
        phonesControl.coletarPhoneTemp(tempCliData);
    }

    public void verifyPersonDocument() {
        if (getPhysicalOrLegalInterim().isViewVariableBoolean()) {
            //PhysicalPerson

            boolean teste = false;
            teste = verifyPersonDocument.checkDocumentPhysicalPerson(varPerson, addressControl);

        } else {
            // LegalPerson
        }

    }

    public void salvarTeste() {

        SaveVariablesPerson savePerson = new SaveVariablesPerson();

        if (getPhysicalOrLegalInterim().isViewVariableBoolean()) {
            savePerson.savePerson(varPerson);
            savePerson.savePhysicalPerson(varPerson);
            addressControl.saveAddress(varPerson.getPerson());

            
            if (verifyPersonDocument.isCheckCPF()) {
                savePerson.saveCPF(varPerson);
            }

            if (verifyPersonDocument.isCheckRG()) {
                savePerson.saveRG(varPerson);
            }
            
            getObjMessage().info("Os dados foram salvos", "");
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

    public RenderedFields getViewFormsOwner() {
        return listRenderedFields.getListViewFields(1);
    }

    private void confirmViewFormsOwner(boolean var) {
        listRenderedFields.getListViewFields(1).setViewVariableBoolean(var);
    }

}
