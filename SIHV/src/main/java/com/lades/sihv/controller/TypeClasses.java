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
public class TypeClasses extends AbstractBean {

    private Object typeClasse;
    
    private TypeClasses (){}
    
    public  TypeClasses (Object typeClasse){
        this.typeClasse = typeClasse;
    }

    public Object getTypeClasse() {
        return typeClasse;
    }

    public void setTypeClasse(Object typeClasse) {
        this.typeClasse = typeClasse;
    }
    
    
}
