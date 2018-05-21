/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consulta;

import com.lades.sihv.bean.AbstractBean;

/**
 *
 * @author thiberius
 */
public class ViewSisExame extends AbstractBean {
    private boolean systemVisionVariable = false;

    public boolean isSystemVisionVariable() {
        return systemVisionVariable;
    }

    public void setSystemVisionVariable(boolean systemVisionVariable) {
        this.systemVisionVariable = systemVisionVariable;
    }
}
