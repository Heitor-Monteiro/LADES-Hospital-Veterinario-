/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.searchForAnimalSmall;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.ModuleToCollectError;
import com.lades.sihv.controller.address.AddressControl;
import com.lades.sihv.controller.person.IntercalateCpfRg;
import com.lades.sihv.controller.person.PhonesControl;
import com.lades.sihv.controller.person.VariablesPerson;
import com.lades.sihv.controller.person.VerifyPersonDocument;
import com.lades.sihv.model.Cpf;
import com.lades.sihv.model.People;
import com.lades.sihv.model.PhysicalPerson;
import com.lades.sihv.model.Rg;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class EditOwnerData extends AbstractBean {
    
    private AnimalDataGroup item;
    private OwnerDataGroup ownerDataGroup;
    private PhonesControl phonesControl;
    private IntercalateCpfRg intercalateCpfRg;
    private VerifyPersonDocument verifyPersonDocument;
    private AddressControl addressControl;
    private CheckOwnerDocument checkOwnerDocument;
    
    public EditOwnerData() {
        ownerDataGroup = new OwnerDataGroup();
        ownerDataGroup.setPerson(new People());
        ownerDataGroup.getPerson().setNamePerson("x");
        ownerDataGroup.getPerson().setEmail("x");
        ownerDataGroup.setPhysicalPerson(new PhysicalPerson());
        ownerDataGroup.getPhysicalPerson().setGender("F");
        ownerDataGroup.setCpf(new Cpf());
        ownerDataGroup.getCpf().setCpf("11111111111");
        ownerDataGroup.setCpfCnpj("11111111111");
        ownerDataGroup.setRg(new Rg());
        ownerDataGroup.getRg().setRg("x");
        phonesControl = new PhonesControl();
        phonesControl.getPhone1().setNumberPhone("x");
        intercalateCpfRg = new IntercalateCpfRg();
        checkOwnerDocument = new CheckOwnerDocument();
        addressControl = new AddressControl();
    }
    
    public void methodToSelectOwner(AnimalDataGroup selectAnimalDataGroup) {
        System.out.println("►►►►►►►►►►►►► "
                + "EditOwnerData > public void methodToSelectOwner");
        try {
            item = selectAnimalDataGroup;
            ownerDataGroup = selectAnimalDataGroup.getListOwner().get(0);
            phonesControl = new PhonesControl();
            phonesControl.setPhone1(ownerDataGroup.getPhone1());
            if (ownerDataGroup.getPhone2() != null) {
                phonesControl.setPhone2(ownerDataGroup.getPhone2());
                phonesControl.setViewPhone2(true);
            }
            if (ownerDataGroup.getPhone3() != null) {
                phonesControl.setPhone3(ownerDataGroup.getPhone3());
                phonesControl.setViewPhone3(true);
            }
            if (ownerDataGroup.getCpf().getPkCpf() != null) {
                intercalateCpfRg.setCpfOptional(true);
                ownerDataGroup.setCpfCnpj(ownerDataGroup.getCpf().getCpf());
            } else {
                intercalateCpfRg.setCpfOptional(false);
            }
            if (ownerDataGroup.getRg().getPkRg() != null) {
                intercalateCpfRg.setRgOptional(true);
            } else {
                intercalateCpfRg.setRgOptional(false);
            }
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO public void methodToSelectOwner(): " + e.toString());
            new ModuleToCollectError().erroPage500("EditOwnerData > methodToSelectOwner", e.toString());
        }
    }
    
    public void verifyPersonDocument() {
        VariablesPerson varPerson = new VariablesPerson();
        varPerson.setPerson(ownerDataGroup.getPerson());
        varPerson.setOwner(ownerDataGroup.getOwner());
        varPerson.setPhysicalPerson(ownerDataGroup.getPhysicalPerson());
        varPerson.setObjCpf(ownerDataGroup.getCpf());
        varPerson.setObjRg(ownerDataGroup.getRg());
        varPerson.setLegalPerson(ownerDataGroup.getLegalPerson());
        
        verifyPersonDocument = new VerifyPersonDocument();
        verifyPersonDocument.checkDocumentPhysicalPerson(varPerson, addressControl);
    }
    
    public void checkDocumentPhysicalPersonCPF() {
        checkOwnerDocument.checkDocumentPhysicalPersonCPF(ownerDataGroup);
    }
    
    public void methodToUpdateOwnerData(List<AnimalDataGroup> listAnimal) {
        new UpdateOwnerData().methodToUpdateOwnerData(listAnimal, ownerDataGroup,
                phonesControl, intercalateCpfRg, item);
    }

    // GETs & SETs ------------------------------------------------------------
    public OwnerDataGroup getOwnerDataGroup() {
        return ownerDataGroup;
    }
    
    public void setOwnerDataGroup(OwnerDataGroup ownerDataGroup) {
        this.ownerDataGroup = ownerDataGroup;
    }
    
    public PhonesControl getPhonesControl() {
        return phonesControl;
    }
    
    public IntercalateCpfRg getIntercalateCpfRg() {
        return intercalateCpfRg;
    }
}
