/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.person;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.ListRenderedFields;
import com.lades.sihv.controller.RenderedFields;

/**
 *
 * @author thiberius
 */
public class IntercalateCpfRg extends AbstractBean {

    private final ListRenderedFields listRenderedFields;

    public IntercalateCpfRg() {
        listRenderedFields = new ListRenderedFields(2);
        listRenderedFields.startIndexListViewFields();
        listRenderedFields.getListViewFields(0).setViewVariableBoolean(true);
        listRenderedFields.getListViewFields(1).setViewVariableBoolean(true);
    }

    //-GETs e SETs--------------------------------------------------------------
    public boolean isCpfOptional() {
        return listRenderedFields.getListViewFields(0).isViewVariableBoolean();
    }

    public void setCpfOptional(boolean cpfOptional) {
        listRenderedFields.getListViewFields(0).setViewVariableBoolean(cpfOptional);
    }

    public boolean isRgOptional() {
        return listRenderedFields.getListViewFields(1).isViewVariableBoolean();
    }

    public void setRgOptional(boolean rgOptional) {
        listRenderedFields.getListViewFields(1).setViewVariableBoolean(rgOptional);
    }

    public RenderedFields getRenderedFields(int index) {
        return listRenderedFields.getListViewFields(index);
    }

    public void methodCPF() {
        if (!listRenderedFields.getListViewFields(0).isViewVariableBoolean()
                && !listRenderedFields.getListViewFields(1).isViewVariableBoolean()) {
            listRenderedFields.getListViewFields(1).setViewVariableBoolean(true);
        }
    }
    
    public void methodRG() {
        if (!listRenderedFields.getListViewFields(1).isViewVariableBoolean()
                && !listRenderedFields.getListViewFields(0).isViewVariableBoolean()) {
            listRenderedFields.getListViewFields(0).setViewVariableBoolean(true);
        }
    }

}
