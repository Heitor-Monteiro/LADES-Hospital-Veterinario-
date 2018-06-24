/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consulta;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.classeMolde.CollectionClasses;
import com.lades.sihv.controller.RenderedFields;
import com.lades.sihv.model.Consulta;
import com.lades.sihv.model.SisUrinarioMamaria;
import com.lades.sihv.model.SisUrinarioMamariaId;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class ControllerExamLaboratory extends AbstractBean {

    private final List<RenderedFields> listViewFields = new ArrayList();
    private final String ndn = "Nada digno de nota.";

    

    

//  GETs & SETs

    private RenderedFields getListViewFields(int index) {
        if (listViewFields.isEmpty()) {
            listViewFields.add(index, new RenderedFields());
        } else if ((listViewFields.size() - index) == 0) {
            listViewFields.add(index, new RenderedFields());
        }
        return listViewFields.get(index);
    }

    private void startIndexListViewFields() {
        for (int index = 0; index <= 5; index++) {
            listViewFields.add(index, new RenderedFields());
            listViewFields.get(index).setViewVariableBoolean(false);
        }
    }

    public RenderedFields getViewExamLaboratory() {
        if (getListViewFields(0).isViewVariableBoolean()) {
            
        } else {
            
            startIndexListViewFields();
        }
        return getListViewFields(0);
    }
}
