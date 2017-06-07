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
    
//---------------------------------------------------------------------------------------
    
    public void setUsername(String name) {
        SessionUtils.getSession().setAttribute("username", name);
    }
    
    public String getUsername() {
        return (String) SessionUtils.getSession().getAttribute("username");
    }

    public void setUserTipo(String tipo) {
        SessionUtils.getSession().setAttribute("UserTipo", tipo);
    }

    public String getUserTipo() {
        return (String) SessionUtils.getSession().getAttribute("UserTipo");
    }

    public void setCpfCnpj(String numbers) {
        SessionUtils.getSession().setAttribute("cpfCnpj", numbers);
    }

    public String getCpfCnpj() {
        return (String) SessionUtils.getSession().getAttribute("cpfCnpj");
    }

    public void setFullName(String name) {
        SessionUtils.getSession().setAttribute("fullName", name);
    }

    public String getFullName() {
        return (String) SessionUtils.getSession().getAttribute("fullName");
    }

    public void setPkPessoa(int pk) {
        SessionUtils.getSession().setAttribute("pkPessoa", pk);
    }

    public int getPkPessoa() {
        return (int) SessionUtils.getSession().getAttribute("pkPessoa");
    }
    
    public void setCrmvMatricula(String codMat) {
        SessionUtils.getSession().setAttribute("crmvMatricula", codMat);
    }

    public String getCrmvMatricula() {
        return (String) SessionUtils.getSession().getAttribute("crmvMatricula");
    }
    
    public void setSenhaUser(String pass) {
        SessionUtils.getSession().setAttribute("senhaUser", pass);
    }

    public String getSenhaUser() {
        return (String) SessionUtils.getSession().getAttribute("senhaUser");
    }
}