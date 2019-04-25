/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.searchForAnimalSmall;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.ModuleToCollectError;
import com.lades.sihv.controller.address.AddressControl;
import com.lades.sihv.controller.editUserData.SearchAddressOfPerson;
import com.lades.sihv.controller.person.IntercalateCpfRg;
import com.lades.sihv.controller.person.PhonesControl;
import com.lades.sihv.model.Cpf;
import com.lades.sihv.model.FederationUnity;
import com.lades.sihv.model.People;
import com.lades.sihv.model.PhysicalPerson;
import com.lades.sihv.model.Rg;
import java.util.List;
import org.primefaces.context.RequestContext;

/**
 *
 * @author thiberius
 */
public class EditOwnerData extends AbstractBean {
    
    private AnimalDataGroup item;
    private OwnerDataGroup ownerDataGroup;
    private PhonesControl phonesControl;
    private IntercalateCpfRg intercalateCpfRg;
    private AddressControl addressControl;
    private final CheckOwnerDocument checkOwnerDocument;
    
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
        ownerDataGroup.setTempRg("x");
        ownerDataGroup.setUf(new FederationUnity());
        phonesControl = new PhonesControl();
        phonesControl.getPhone1().setNumberPhone("11111111111");
        intercalateCpfRg = new IntercalateCpfRg();
        checkOwnerDocument = new CheckOwnerDocument();
        addressControl = new AddressControl();
        addressControl.loadLists();
        addressControl.getVar().setSelectUF(addressControl.getVar().getListUF().get(0));
        addressControl.getVar().setSelectCity("x");
        addressControl.getVar().setSelectNeighborhood("x");
        addressControl.getVar().setSelectStreet("x");
        addressControl.getVar().getHouse().setNumberHouse("x");
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
                ownerDataGroup.setTempRg(ownerDataGroup.getRg().getRg());
            } else {
                intercalateCpfRg.setRgOptional(false);
            }
            SearchAddressOfPerson obj = new SearchAddressOfPerson();
            addressControl.getVar().instantiateLists();
            addressControl.getVar().setSelectUF(ownerDataGroup.getUf());
            addressControl.getVar().getListUF().add(ownerDataGroup.getUf());
            obj.ufListing(addressControl.getVar());
            addressControl.getVar().setSelectCity(ownerDataGroup.getCity().getFullNameCity());
            addressControl.getVar().setObjCity(ownerDataGroup.getCity());
            addressControl.getVar().getListObjCity().add(ownerDataGroup.getCity());
            obj.methodEnableListCitys(addressControl.getVar());
            addressControl.getVar().setSelectNeighborhood(ownerDataGroup.getNeighborhood().getNeighborhood());
            addressControl.getVar().setObjNeighborhood(ownerDataGroup.getNeighborhood());
            obj.methodEnableListNeighborhood(addressControl.getVar());
            addressControl.getVar().setSelectStreet(ownerDataGroup.getStreet().getNameStreet());
            addressControl.getVar().setObjStreet(ownerDataGroup.getStreet());
            obj.methodEnableListStreet(addressControl.getVar());
            addressControl.getVar().setHouse(ownerDataGroup.getHouse());
            addressControl.getVar().setObjAddress(ownerDataGroup.getAddress());
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO public void methodToSelectOwner(): " + e.toString());
            new ModuleToCollectError().erroPage500("EditOwnerData > methodToSelectOwner", e.toString());
        }
    }
    
    public void checkDocumentPhysicalPersonCPF() {
        checkOwnerDocument.checkDocumentPhysicalPersonCPF(ownerDataGroup);
    }
    
    public void checkDocumentPhysicalPersonRG() {
        checkOwnerDocument.checkDocumentPhysicalPersonRG(ownerDataGroup, addressControl);
    }
    
    public void methodToUpdateOwnerData(List<AnimalDataGroup> listAnimal) {
        new UpdateOwnerData().methodToUpdateOwnerData(listAnimal, ownerDataGroup,
                phonesControl, intercalateCpfRg, item, addressControl);
    }
    
    public void closeDialogEditOwnerData() {
        if (ownerDataGroup.getPerson().getPkPerson() != null) {
            item = new AnimalDataGroup();
            ownerDataGroup = new OwnerDataGroup();
            phonesControl = new PhonesControl();
            addressControl = new AddressControl();
            intercalateCpfRg = new IntercalateCpfRg();
            RequestContext.getCurrentInstance().execute("PF('dlg3').hide();");
        }
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
    
    public AddressControl getAddressControl() {
        return addressControl;
    }
}
