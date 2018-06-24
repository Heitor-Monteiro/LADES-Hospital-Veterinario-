/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller;

import com.lades.sihv.bean.AbstractBean;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiberius
 */

/*Uma instancia dessa classe serve para
gerenciar um item na tela que precise de rendered*/
public class ListRenderedFields extends AbstractBean {

    private final List<RenderedFields> listViewFields = new ArrayList();
    private int indexMax = 0;
    
    
    public ListRenderedFields (int indexMax){
        this.indexMax = indexMax;
    }
    
    public RenderedFields getListViewFields(int index) {
        if (listViewFields.isEmpty()) {
            listViewFields.add(index, new RenderedFields());
        } else if ((listViewFields.size() - index) == 0) {
            listViewFields.add(index, new RenderedFields());
        }
        return listViewFields.get(index);
    }
    
    public void startIndexListViewFields() {
        for (int index = 0; index <= indexMax; index++) {
            listViewFields.add(index, new RenderedFields());
            listViewFields.get(index).setViewVariableBoolean(false);
        }
    }
}
