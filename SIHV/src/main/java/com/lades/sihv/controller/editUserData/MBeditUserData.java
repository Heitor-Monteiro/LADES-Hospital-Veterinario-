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
import com.lades.sihv.model.PowersHasUsers;
import com.lades.sihv.model.Users;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
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
    private List<Powers> previousStatusOfPowers;
    private List<PowersHasUsers> powerAndUserJoining;
    private String number1, number2;
    private Users tempUser;

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
            previousStatusOfPowers = new ArrayList();
            powerAndUserJoining = new ArrayList();
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
        viewFunctionsToCharge = new ListRenderedFields(6);
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
                ReturnUserProfileData objReturnUser = new ReturnUserProfileData();
                varPerson.setUser(objReturnUser.methodOfReturnUserProfileData(varPerson));
                tempUser = objReturnUser.methodOfReturnUserProfileData(varPerson);
                generateFunctionsToCharge();
                if (varPerson.getUser().getUserProfile().equals("médico veterinário")) {
                    number1 = varPerson.getUser().getRegistrationNumber().replaceAll("[1234567890 ]", "");
                    number2 = varPerson.getUser().getRegistrationNumber().replaceAll("[^1234567890]", "");
                } else {
                    number2 = varPerson.getUser().getRegistrationNumber();
                }
                selectPower.clear();
                powerAndUserJoining.clear();
                List<?> list = getDaoGenerico().list("select pw, phu from "
                        + "Powers pw,PowersHasUsers phu, Users u \n"
                        + "where \n"
                        + "pw.pkPower=phu.powers.pkPower and \n"
                        + "phu.users.pkUser=u.pkUser and \n"
                        + "u.pkUser='" + varPerson.getUser().getPkUser() + "'");
                for (Object[] object : (List<Object[]>) list) {
                    Powers power = (Powers) object[0];
                    for (Powers itemPower : listPowers) {
                        if (Objects.equals(power.getPkPower(), itemPower.getPkPower())) {
                            selectPower.add(itemPower);
                            previousStatusOfPowers.add(itemPower);
                        }
                    }
                    powerAndUserJoining.add((PowersHasUsers) object[1]);
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
        SaveVariablesPerson obj = new SaveVariablesPerson();
        obj.saveUser(varPerson);
        if (changeOfPowers().isViewVariableBoolean()) {
            System.out.println("############### selectPower: ChangeOfPowers");
            for (PowersHasUsers phu : powerAndUserJoining) {
                getDaoGenerico().remove(phu);
            }
            for (Powers power : selectPower) {
                PowersHasUsers obj2 = new PowersHasUsers();
                obj2.setPowers(power);
                obj2.setUsers(varPerson.getUser());
                getDaoGenerico().save(obj2);
            }
        }
        varPerson.setUser(new Users());
        getObjMessage().info("Os dados foram atualizados com sucesso!", "");
    }

    public void upgradePowersOnly() {
        System.out.println("►►►►►►►►►►►►► "
                + "MBeditUserData > public void upgradePowersOnly()");
        try {
            getObjMessage().warn("selectPower:" + selectPower.size(),
                    "previousStatusOfPowers:" + previousStatusOfPowers.size());

            if (selectPower.isEmpty()) {
                for (PowersHasUsers phu : powerAndUserJoining) {
                    getDaoGenerico().remove(phu);
                }
                getObjMessage().info("Alteração realizada!", "Todos os poderes foram retirados.");
            } else if (true) {
                getObjMessage().info("Condição 2!", "");
                boolean pauj;
                for (int i = 0; i < previousStatusOfPowers.size(); i++) {
                    pauj = false;
                    for (Iterator<Powers> iterator = selectPower.iterator(); iterator.hasNext();) {
                        Powers next = iterator.next();
                        if (Objects.equals(previousStatusOfPowers.get(i).getPkPower(), next.getPkPower())) {
                            pauj = true;
                            selectPower.remove(next);
                            break;
                        }
                    }
                    if (!pauj) {
                        getDaoGenerico().remove(powerAndUserJoining.get(i));
                    }
                }
                for (Powers power : selectPower) {
                    PowersHasUsers phu = new PowersHasUsers();
                    phu.setPowers(power);
                    phu.setUsers(tempUser);
                    getDaoGenerico().save(phu);
                }
            }
            tempUser = new Users();
        } catch (Exception e) {
            System.err.println("►►►►►►►►►►►►► ERRO public void upgradePowersOnly(): " + e.toString());
            new ModuleToCollectError().erroPage500("MBeditUserData > upgradePowersOnly", e.toString());
        }
    }

    public void generateFunctionsToCharge() {
        textFieldRecordNumber = new GenerateFunctionsToCharge()
                .methodGenerateFunctionsToCharge(viewFunctionsToCharge, varPerson, textFieldRecordNumber);
    }

    public void determinePowersAccordingToFunctionUser() {
        selectPower.clear();
        changeOfPowers().setViewVariableBoolean(true);
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

    public RenderedFields getViewFieldEditpassword() {
        return viewFunctionsToCharge.getListViewFields(4);
    }

    private RenderedFields changeOfPowers() {
        return viewFunctionsToCharge.getListViewFields(5);
    }

    public String getTextFieldRecordNumber() {
        return textFieldRecordNumber;
    }

    public List<Powers> getSelectPower() {
        return selectPower;
    }

    public void setSelectPower(List<Powers> selectPower) {
        this.selectPower = selectPower;
    }

    public List<Powers> getListPowers() {
        return listPowers;
    }

    public void setListPowers(List<Powers> listPowers) {
        this.listPowers = listPowers;
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

    public Users getTempUser() {
        return tempUser;
    }
}
