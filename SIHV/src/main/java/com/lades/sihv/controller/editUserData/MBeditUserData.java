/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.editUserData;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.ListRenderedFields;
import com.lades.sihv.controller.ModuleToCollectError;
import com.lades.sihv.controller.RenderedFields;
import com.lades.sihv.controller.address.AddressControl;
import com.lades.sihv.controller.person.PhonesControl;
import com.lades.sihv.controller.person.SaveVariablesPerson;
import com.lades.sihv.controller.person.VariablesPerson;
import com.lades.sihv.controller.registerUser.DeterminePowersAccordingToFunctionUser;
import com.lades.sihv.model.People;
import com.lades.sihv.model.Powers;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

//@author thiberius
@ManagedBean(name = "MBeditUserData")
@ViewScoped

public class MBeditUserData extends AbstractBean {

    private List<People> listPersons;
    private VariablesPerson varPerson;
    private AddressControl addressControl;
    private PhonesControl phonesControl;
    private ListRenderedFields viewFunctionsToCharge;
    private String textFieldRecordNumber;
    private List<Powers> listPowers;
    private List<Powers> selectPower;
    private String number1, number2;

    //--------------------------------------------------------------------------
    @PostConstruct
    public void init() {
        try {
            System.out.println("►►►►►►►►►►►►► MBeditUserData initiated");
            instantiateObjects();
            listPersons = getDaoGenerico()
                    .list("select p from Users u, People p \n"
                            + "where \n"
                            + "u.people.pkPerson=p.pkPerson and \n"
                            + "p.logicalExclusion='0'");
            listPowers = getDaoGenerico().list("select pw from Powers pw ");
            selectPower = new ArrayList();
        } catch (Exception e) {
            System.err.println("►►►►►►►►►►►►► ERRO public void init(): " + e.toString());
            new ModuleToCollectError().erroPage500("MBeditUserData > init", e.toString());
        }
    }

    private void instantiateObjects() {
        System.out.println("►►►►►►►►►►►►► "
                + "MBeditUserData > public void instantiateObjects()");
        varPerson = new VariablesPerson();
        addressControl = new AddressControl();
        phonesControl = new PhonesControl();
        viewFunctionsToCharge = new ListRenderedFields(4);
        viewFunctionsToCharge.startIndexListViewFields();
    }

    public void returnUserRegistration() {
        System.out.println("►►►►►►►►►►►►► "
                + "MBeditUserData > public void returnUserRegistration()");
        try {
            if (varPerson.getPerson() != null) {
                addressControl.getVar().instantiateLists();
                new ReturnPhysicalOrLegalPerson().methodOfReturnOfPhysicalOrLegalPerson(varPerson);
                SearchAddressOfPerson obj = new SearchAddressOfPerson();
                obj.methodSearchAddressOfPerson(varPerson.getPerson(), addressControl.getVar());
                obj.ufListing(addressControl.getVar());
                obj.methodEnableListCitys(addressControl.getVar());
                obj.methodEnableListNeighborhood(addressControl.getVar());
                obj.methodEnableListStreet(addressControl.getVar());
                phonesControl.searchPhones(varPerson.getPerson());
                //--------------------------------------------------------------
                new ReturnUserProfileData().methodOfReturnUserProfileData(varPerson);
                generateFunctionsToCharge();
                if (varPerson.getUser().getUserProfile().equals("médico veterinário")) {
                    number1 = varPerson.getUser().getRegistrationNumber().replaceAll("[1234567890 ]", "");
                    number2 = varPerson.getUser().getRegistrationNumber().replaceAll("[^1234567890]", "");
                } else {
                    number2 = varPerson.getUser().getRegistrationNumber();
                }
            }
        } catch (Exception e) {
            System.err.println("►►►►►►►►►►►►► ERRO public void returnUserRegistration(): " + e.toString());
            new ModuleToCollectError().erroPage500("MBeditUserData > returnUserRegistration", e.toString());
        }
    }

    public void checkDocumentPhysicalPersonCPF() {
        new CheckUserDocument().checkDocumentPhysicalPersonCPF(varPerson, addressControl);
    }

    public void checkDocumentPhysicalPersonRG() {
        new CheckUserDocument().checkDocumentPhysicalPersonRG(varPerson, addressControl);
    }

    public void updateUserRegistrationData() {
        System.out.println("►►►►►►►►►►►►► "
                + "MBeditUserData > public void updateUserRegistrationData()");
        SaveVariablesPerson obj = new SaveVariablesPerson();
        obj.savePerson(varPerson);
        obj.savePhysicalPerson(varPerson);
        obj.saveCPF(varPerson);
        obj.saveRG(varPerson);
        phonesControl.savePhones(varPerson.getPerson());
        new SaveAddress().methodSaveAddress(varPerson.getPerson(), addressControl.getVar());

        instantiateObjects();
        getObjMessage().info("Os dados foram atualizados com sucesso!", "");
    }

    public void updateUserProfileData() {
        System.out.println("►►►►►►►►►►►►► "
                + "MBeditUserData > public void updateUserProfileData()");
        if (varPerson.getUser().getUserProfile().equals("médico veterinário")) {
            varPerson.getUser().setRegistrationNumber(number1 + " " + number2);
        } else {
            varPerson.getUser().setRegistrationNumber(number2);
        }
    }

    public void generateFunctionsToCharge() {
        textFieldRecordNumber = new GenerateFunctionsToCharge()
                .methodGenerateFunctionsToCharge(viewFunctionsToCharge, varPerson, textFieldRecordNumber);
    }

    public void determinePowersAccordingToFunctionUser() {
        new DeterminePowersAccordingToFunctionUser()
                .determinePowersAccordingToFunctionUser(varPerson, listPowers, selectPower);
    }

    //-GETs e SETs--------------------------------------------------------------
    public List<People> getListPersons() {
        return listPersons;
    }

    public VariablesPerson getVarPerson() {
        return varPerson;
    }

    public AddressControl getAddressControl() {
        return addressControl;
    }

    public PhonesControl getPhonesControl() {
        return phonesControl;
    }

    public RenderedFields viewFunctionsForVeterinaryDoctor() {
        return viewFunctionsToCharge.getListViewFields(0);
    }

    public RenderedFields viewFunctionsForStudent() {
        return viewFunctionsToCharge.getListViewFields(1);
    }

    public RenderedFields viewFunctionsForRadiologist() {
        return viewFunctionsToCharge.getListViewFields(2);
    }

    public RenderedFields viewNumber1() {
        return viewFunctionsToCharge.getListViewFields(3);
    }

    public String getTextFieldRecordNumber() {
        return textFieldRecordNumber;
    }

    public List<Powers> getSelectPower() {
        return selectPower;
    }

    public String getNumber1() {
        return number1;
    }

    public void setNumber1(String number1) {
        this.number1 = number1;
    }

    public String getNumber2() {
        return number2;
    }

    public void setNumber2(String number2) {
        this.number2 = number2;
    }

    public boolean isViewNumber2() {
        return varPerson.getUser().getUserProfile() != null;
    }
}
