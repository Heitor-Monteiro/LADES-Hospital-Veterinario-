/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.address;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.BeautyText;
import com.lades.sihv.controller.ModuleToCollectError;
import com.lades.sihv.model.Address;
import com.lades.sihv.model.Neighborhood;
import com.lades.sihv.model.Street;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class EnableListStreet extends AbstractBean {

    private boolean checkCharactersField(String selectFild) {
        boolean var = false;
        if (selectFild != null) {
            if (selectFild.length() > 0) {
                var = true;
            }
        }
        return var;
    }

    private boolean checkListObj(List<Object> list) {
        return (!list.isEmpty());
    }

    private void ConfirmNewNeighborhood(VariablesAddress var) {
        try {
            var.newNeighborhood(true);
            var.newStreet(true);
            var.getObjNeighborhood().setNeighborhood(var.getSelectNeighborhood());
            var.getObjNeighborhood().setRegistrationDate(getObjData());
            getObjMessage().info("Novo bairro para registro:", var.getSelectNeighborhood());
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO ConfirmNewNeighborhood(): " + e.toString());
            new ModuleToCollectError().erroPage500("EnableListStreet > ConfirmNewNeighborhood", e.toString());
        }
    }

    public void methodEnableListStreet(VariablesAddress var) {
        try {
            System.out.println("------------------public void enableListStreet()");
            var.getListObjStreet().clear();
            var.getListStreet().clear();
            var.getListObjAddress().clear();
            var.setSelectStreet("");
            boolean checkSelectFild = checkCharactersField(var.getSelectNeighborhood());
            boolean checkList = checkListObj((List<Object>) (Object) var.getListObjNeighborhood());
            if (checkSelectFild) {
                var.setSelectNeighborhood(new BeautyText().Captalizador(var.getSelectNeighborhood()));
            }
            System.out.println("-------------------- checkSelectFild:" + checkSelectFild);
            System.out.println("-------------------- statusNewCity():" + var.statusNewCity());
            System.out.println("-------------------- checkList:" + checkList);
            var.newNeighborhood(true);
            if (checkSelectFild && !var.statusNewCity()) {
                for (Neighborhood neighborhood : var.getListObjNeighborhood()) {
                    if (neighborhood.getNeighborhood().equals(var.getSelectNeighborhood())) {
                        List<?> list = getDaoGenerico().list("select s, a from Street s, Neighborhood n, Address a \n"
                                + "where \n"
                                + "n.id.pkNeighborhood=a.neighborhood.id.pkNeighborhood and \n"
                                + "n.id.cityPkCity=a.neighborhood.id.cityPkCity and \n"
                                + "s.pkStreet=a.street.pkStreet and \n"
                                + "n.id.pkNeighborhood='" + neighborhood.getId().getPkNeighborhood() + "' and \n"
                                + "a.neighborhood.id.pkNeighborhood='" + neighborhood.getId().getPkNeighborhood() + "' ");
                        System.out.println("----------------------- list: " + list.size());
                        var.setObjNeighborhood(neighborhood);
                        for (Object[] object : (List<Object[]>) list) {
                            Street s = (Street) object[0];
                            var.getListObjStreet().add(s);
                            var.getListStreet().add(s.getNameStreet());
                            var.getListObjAddress().add((Address) object[1]);
                        }
                        System.out.println("----------------------- listObjStreet: " + var.getListObjStreet().size());
                        System.out.println("----------------------- listStreet: " + var.getListStreet().size());
                        System.out.println("----------------------- listObjAddress: " + var.getListObjAddress().size());
                        var.newNeighborhood(false);
                        break;
                    }
                }
                if (var.statusNewNeighborhood()) {
                    ConfirmNewNeighborhood(var);
                }
            } else {
                ConfirmNewNeighborhood(var);
            }
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO methodEnableListStreet(): " + e.toString());
            new ModuleToCollectError().erroPage500("EnableListStreet > methodEnableListStreet", e.toString());
        }
    }
}
