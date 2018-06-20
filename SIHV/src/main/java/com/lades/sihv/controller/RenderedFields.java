/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller;

import com.lades.sihv.bean.AbstractBean;

/**
 *
 * @author thiberius
 */

/*Uma instancia dessa classe serve para
gerenciar um item na tela que precise de rendered*/
public class RenderedFields extends AbstractBean {

    private boolean viewVariableBoolean;

    public boolean isViewVariableBoolean() {
        return viewVariableBoolean;
    }

    public void setViewVariableBoolean(boolean viewVariableBoolean) {
        this.viewVariableBoolean = viewVariableBoolean;
    }
}
