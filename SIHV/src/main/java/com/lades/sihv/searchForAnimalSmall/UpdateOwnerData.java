/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.searchForAnimalSmall;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.ModuleToCollectError;
import com.lades.sihv.controller.address.AddressControl;
import com.lades.sihv.controller.editUserData.SaveAddress;
import com.lades.sihv.controller.person.IntercalateCpfRg;
import com.lades.sihv.controller.person.PhonesControl;
import java.util.List;
import java.util.Objects;
import org.primefaces.context.RequestContext;

/**
 *
 * @author thiberius
 */
public class UpdateOwnerData extends AbstractBean {
    
    public void methodToUpdateOwnerData(List<AnimalDataGroup> listAnimal,
            OwnerDataGroup ownerDataGroup,
            PhonesControl phonesControl,
            IntercalateCpfRg intercalateCpfRg,
            AnimalDataGroup item,
            AddressControl addressControl) {
        System.out.println("►►►►►►►►►►►►► "
                + "EditOwnerData > public void methodToUpdateOwnerData");
        try {
            getDaoGenerico().update(ownerDataGroup.getPerson());
            phonesControl.savePhones(ownerDataGroup.getPerson());
            if (ownerDataGroup.getPhysicalPerson().getId() != null) {
                getDaoGenerico().update(ownerDataGroup.getPhysicalPerson());
                if (intercalateCpfRg.isCpfOptional()) {
                    getDaoGenerico().update(ownerDataGroup.getCpf());
                }
                if (intercalateCpfRg.isRgOptional()) {
                    getDaoGenerico().update(ownerDataGroup.getRg());
                }
            } else {
                
            }
            new SaveAddress().methodSaveAddress(ownerDataGroup.getPerson(), addressControl.getVar());
            ownerDataGroup.setUf(addressControl.getVar().getSelectUF());
            ownerDataGroup.setCity(addressControl.getVar().getObjCity());
            ownerDataGroup.setNeighborhood(addressControl.getVar().getObjNeighborhood());
            ownerDataGroup.setStreet(addressControl.getVar().getObjStreet());
            ownerDataGroup.setHouse(addressControl.getVar().getHouse());
            ownerDataGroup.setAddress(addressControl.getVar().getObjAddress());
            item.getListOwner().add(0, ownerDataGroup);
            int num = listAnimal.indexOf(item);
            listAnimal.remove(num);
            listAnimal.add(num, item);
            for (AnimalDataGroup obj : listAnimal) {
                if (Objects.equals(ownerDataGroup.getPerson().getPkPerson(),
                        obj.getListOwner().get(0).getPerson().getPkPerson())) {
                    obj.getListOwner().clear();
                    obj.getListOwner().add(0, ownerDataGroup);
                }
            }
            getObjMessage().info("Os dados foram atualizados com sucesso!", "");
            RequestContext.getCurrentInstance().execute("PF('dlg3').hide();");
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO public void methodToUpdateOwnerData(): " + e.toString());
            new ModuleToCollectError().erroPage500("EditOwnerData > methodToUpdateOwnerData", e.toString());
        }
    }
}
