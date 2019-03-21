/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.editUserData;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.ModuleToCollectError;
import com.lades.sihv.controller.person.VariablesPerson;
import com.lades.sihv.model.Users;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class ReturnUserProfileData extends AbstractBean {

    public void methodOfReturnUserProfileData(VariablesPerson varPerson) {
        System.out.println("►►►►►►►►►►►►► "
                + "ReturnUserProfileData "
                + "> public void methodOfReturnUserProfileData()");
        try {
            List<Users> list = getDaoGenerico()
                    .list("select u from Users u, People p \n"
                            + "where \n"
                            + "u.people.pkPerson=p.pkPerson and \n"
                            + "p.pkPerson='" + varPerson.getPerson().getPkPerson() + "'");
            if (list != null && !list.isEmpty()) {
                varPerson.setUser(list.get(0));
            }
        } catch (Exception e) {
            System.err.println("►►►►►►►►►►►►► ERRO public void methodOfReturnUserProfileData(): " + e.toString());
            new ModuleToCollectError().erroPage500("ReturnUserProfileData > methodOfReturnUserProfileData", e.toString());
        }
    }
}
