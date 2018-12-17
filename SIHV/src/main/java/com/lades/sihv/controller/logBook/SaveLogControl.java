/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.logBook;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.ModuleToCollectError;
import com.lades.sihv.model.LogBook;
import com.lades.sihv.model.LogBookHasUsers;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class SaveLogControl extends AbstractBean {

    private List<LogBook> listLogs;

    public void saveLog(int indexListLogs, String description) {
        try {
            listLogs = getDaoGenerico().list("select l from LogBook l ");
            LogBookHasUsers x = new LogBookHasUsers();
            x.setLogBook(listLogs.get(indexListLogs));
            x.setUsers(getVariaveisDeSessao().getDadosUSER());
            x.setDescription(description);
            getDaoGenerico().save(x);
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO public void saveLog(): " + e.toString());
            new ModuleToCollectError().erroPage500("SaveLogControl > saveLog", e.toString());
        }
    }
}
