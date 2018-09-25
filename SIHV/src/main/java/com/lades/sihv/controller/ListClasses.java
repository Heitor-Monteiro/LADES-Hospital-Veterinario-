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
public class ListClasses extends AbstractBean {

    private final List<Object> listClasses = new ArrayList();
    private TypeClasses typeClasse;
    private int indexMax = 0;
    
    
    public ListClasses (){}
    
    public  ListClasses (Object typeClasse){
        this.typeClasse = new TypeClasses(typeClasse);
    }
    
    public ListClasses (int indexMax, Object typeClasse){
        this.indexMax = indexMax;
        this.typeClasse = new TypeClasses(typeClasse);
    }
    
    public TypeClasses getItemListClasses(int index) {
        if (listClasses.isEmpty()) {
            listClasses.add(index, typeClasse);
        } else if ((listClasses.size() - index) == 0) {
            listClasses.add(index, typeClasse);
        }
        return (TypeClasses) listClasses.get(index);
    }
    
    public void startIndexListViewFields() {
        for (int index = 0; index <= indexMax; index++) {
            listClasses.add(index, typeClasse);
        }
    }

    public void setIndexMax(int indexMax) {
        this.indexMax = indexMax;
    }
}
