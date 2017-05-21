/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller;

import com.lades.sihv.DAO.SessionUtils;
import com.lades.sihv.model.Animais;
import com.lades.sihv.model.Pessoa;

/**
 *
 * @author thiberius
 */
public class VariaveisDeSessao {

    public Object getObjetoTemp() {
        return (Object) SessionUtils.getSession().getAttribute("objetoTemporario");
    }

    public void setObjetoTemp(Object objeto) {
        SessionUtils.getSession().setAttribute("objetoTemporario", objeto);
    }

    public Pessoa objetoPessoa() {
        return (Pessoa) SessionUtils.getSession().getAttribute("objetoTemporario");
    }
    
    public Animais objetoAnimal() {
        return (Animais) SessionUtils.getSession().getAttribute("objetoTemporario");
    }
    
    public Object getFerramentaTemp() {
        return (Object) SessionUtils.getSession().getAttribute("ferramentaTemporaria");
    }

    public void setFerramentaTemp(Object objeto) {
        SessionUtils.getSession().setAttribute("ferramentaTemporaria", objeto);
    }
}