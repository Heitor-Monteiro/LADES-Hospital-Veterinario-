/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.erroPages;

import com.lades.sihv.bean.*;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

//@author thiberius
@ManagedBean(name = "MBerroPages")
@ViewScoped

public class MBerroPages extends AbstractBean {

    private List<String> listTexts;

    //--------------------------------------------------------------------------
    @PostConstruct
    public void init() {
        try {
            System.out.println("►►►►►►►►►►►►► MBerroPages initiated");
            listTexts = new ArrayList(); 
            listTexts.add(0, "Teste");
            listTexts.add(1, "Teste1");
            List<String> list = getVariaveisDeSessao().getErroListText();
            if (list != null) {
                listTexts.clear();
                listTexts.addAll(list);
            }
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO - MBerroPages - method: init(): " + e);         
        }
    }

    //-GETs e SETs--------------------------------------------------------------
    
    public String getOrigin() {
        return listTexts.get(0);
    }
    
    public String getDescription() {
        return listTexts.get(1);
    }
}
