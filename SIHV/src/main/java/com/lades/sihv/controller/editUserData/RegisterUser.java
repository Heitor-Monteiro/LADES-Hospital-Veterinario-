/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.editUserData;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.ListRenderedFields;
import com.lades.sihv.controller.RenderedFields;
import com.lades.sihv.controller.address.AddressControl;
import com.lades.sihv.controller.logBook.SaveLogControl;
import com.lades.sihv.controller.person.PhonesControl;
import com.lades.sihv.controller.person.SaveVariablesPerson;
import com.lades.sihv.controller.person.VariablesPerson;
import com.lades.sihv.controller.person.VerifyPersonDocument;
import com.lades.sihv.controller.registerUser.DeterminePowersAccordingToFunctionUser;
import com.lades.sihv.model.Powers;
import com.lades.sihv.model.PowersHasUsers;
import com.lades.sihv.model.Users;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class RegisterUser extends AbstractBean {
    private VariablesPerson varPerson;
    private VerifyPersonDocument verifyPersonDocument;
    private AddressControl addressControl;
    private PhonesControl phonesControl;
    private List<Powers> listPowers;
    private List<Powers> selectPower;
    //--------------------------------------------------------------------------
    private ListRenderedFields viewFunctionsToCharge;
    private String typeRecordNumber;
    private String textFieldRecordNumber;
    private String number1, number2;

    //--------------------------------------------------------------------------
    public RegisterUser() {
        try {
            System.out.println("►►►►►►►►►►►►► MBregisterUser initiated");
            varPerson = new VariablesPerson();
            verifyPersonDocument = new VerifyPersonDocument();
            addressControl = new AddressControl();
            addressControl.loadLists();
            phonesControl = new PhonesControl();
            listPowers = getDaoGenerico().list("select pw from Powers pw ");
            selectPower = new ArrayList();
            //----------------------------------------------------------------------
            viewFunctionsToCharge = new ListRenderedFields(4);
            viewFunctionsToCharge.startIndexListViewFields();
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO init(): " + e);
        }
    }

    public void verifyPersonDocument() {
        boolean newPerson = verifyPersonDocument.checkDocumentPhysicalPerson(varPerson, addressControl);
    }

    public void generateFunctionsToCharge() {
        try {
            viewFunctionsToCharge.startIndexListViewFields();
            switch (varPerson.getUser().getUserProfile()) {
                case "médico veterinário":
                    viewFunctionsToCharge.getListViewFields(0).setViewVariableBoolean(true);
                    viewFunctionsToCharge.getListViewFields(3).setViewVariableBoolean(true);
                    textFieldRecordNumber = "Número crmv:";
                    break;
                case "discente":
                    viewFunctionsToCharge.getListViewFields(1).setViewVariableBoolean(true);
                    textFieldRecordNumber = "Matrícula";
                    break;
                case "radiologista":
                    viewFunctionsToCharge.getListViewFields(2).setViewVariableBoolean(true);
                    textFieldRecordNumber = "Matrícula funcional";
                    break;
            }
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO generateFunctionsToCharge(): " + e);
        }
    }

    public void determinePowersAccordingToFunctionUser() {
        new DeterminePowersAccordingToFunctionUser()
                .determinePowersAccordingToFunctionUser(varPerson, listPowers, selectPower);
    }

    public void saveUser() {
        try {
            SaveVariablesPerson savePerson = new SaveVariablesPerson();
            savePerson.savePerson(varPerson);
            savePerson.savePhysicalPerson(varPerson);
            phonesControl.savePhones(varPerson.getPerson());
            addressControl.saveAddress(varPerson.getPerson());
            if (varPerson.getUser().getUserProfile().equals("médico veterinário")) {
                varPerson.getUser().setRegistrationNumber(number1 + " " + number2);
            } else {
                varPerson.getUser().setRegistrationNumber(number2);
            }
            savePerson.saveUser(varPerson);
            Users userMax = (Users) getDaoGenerico().list("select u from Users u \n"
                    + "where \n"
                    + "u.pkUser=(select max(u.pkUser) from Users u) ").get(0);
            varPerson.setUser(userMax);
            if (verifyPersonDocument.isCheckCPF()
                    && verifyPersonDocument.isNewCPF()) {
                savePerson.saveCPF(varPerson);
            }
            if (verifyPersonDocument.isCheckRG()
                    && verifyPersonDocument.isNewRG()) {
                savePerson.saveRG(varPerson);
            }
            for (Powers power : selectPower) {
                PowersHasUsers obj = new PowersHasUsers();
                obj.setPowers(power);
                obj.setUsers(varPerson.getUser());
                getDaoGenerico().save(obj);
            }
            getObjMessage().info("Usuário salvo com sucesso", "Cargo: "
                    + varPerson.getUser().getUserProfile()
                    + "; Função: " + varPerson.getUser().getUserFunction());
            new SaveLogControl().saveLog(0, "Usuário:" + varPerson.getPerson().getNamePerson()
                    + ", Cargo:" + varPerson.getUser().getUserProfile()
                    + ", Função" + varPerson.getUser().getUserFunction()
                    + ", CPF:" + varPerson.getObjCpf().getCpf()
                    + ", RG:" + varPerson.getObjRg().getRg());
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO saveUser(): " + e);
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

    //--------------------------------------------------------------------------
    public String getTypeRecordNumber() {
        return typeRecordNumber;
    }

    public void setTypeRecordNumber(String typeRecordNumber) {
        this.typeRecordNumber = typeRecordNumber;
    }

    public String getNumber1() {
        return number1;
    }

    public void setNumber1(String number1) {
        this.number1 = number1;
        System.out.println("►►►►►►►►►►►►► " + this.number1);
    }

    public String getNumber2() {
        return number2;
    }

    public void setNumber2(String number2) {
        this.number2 = number2;
    }

    public String getTextFieldRecordNumber() {
        return textFieldRecordNumber;
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

    public boolean isViewNumber2() {
        return varPerson.getUser().getUserProfile() != null;
    }
    //--------------------------------------------------------------------------

    public List<Powers> getSelectPower() {
        return selectPower;
    }
}
