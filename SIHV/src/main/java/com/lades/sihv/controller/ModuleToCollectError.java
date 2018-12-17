/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller;

import com.lades.sihv.bean.AbstractBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class ModuleToCollectError extends AbstractBean {

    private final List<String> erroListText;

    public ModuleToCollectError() {
        erroListText = new ArrayList<>();
    }

    public void erroPage500(String origin, String description) {
        try {
            erroListText.add(0, origin);
            erroListText.add(1, description);
            getVariaveisDeSessao().setErroListText(erroListText);
            getObjTools().redirectView("/SIHV/faces/sihv-telas/erro500.xhtml");
        } catch (IOException e) {
            System.out.println("►►►►►►►►►►►►► ERRO public void erroPage500: " + e.toString());
        }
    }

    // GETs & SETs -------------------------------------------------------------
    public List<String> getErroListText() {
        return erroListText;
    }
}
