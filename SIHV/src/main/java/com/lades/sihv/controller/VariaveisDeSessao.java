/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller;

import com.lades.sihv.DAO.SessionUtils;
import com.lades.sihv.model.Animais;
import com.lades.sihv.model.Pessoa;
import com.lades.sihv.model.User;

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
    public void setDadosUSER(Object objUser) {
        SessionUtils.getSession().setAttribute("dadosUser", objUser);
    }

    public User getDadosUSER() {
        return (User) SessionUtils.getSession().getAttribute("dadosUser");
    }

    public void setDadosPESSOA(Object objPessoa) {       
        Pessoa obj = (Pessoa) objPessoa;
        SessionUtils.getSession().setAttribute("username", new BeautyText().fistNLast(obj.getNome()));
        SessionUtils.getSession().setAttribute("dadosPessoa", objPessoa);
    }

    private Pessoa getDadosPESSOA() {
        return (Pessoa) SessionUtils.getSession().getAttribute("dadosPessoa");
    }

    public String getUsername() {
        return (String) SessionUtils.getSession().getAttribute("username");
    }

    public String getUserTipo() {
        return (String) getDadosUSER().getUserTipo();
    }

    public String getCpfCnpj() {
        return (String) getDadosPESSOA().getCpfCnpj();
    }

    public String getFullName() {
        return (String) getDadosPESSOA().getNome();
    }

    public int getPkPessoa() {
        return (int) getDadosPESSOA().getPkPessoa();
    }

    public String getCrmvMatricula() {
        return (String) getDadosUSER().getCrmvMatricula();
    }

    public String getSenhaUser() {
        return (String) getDadosUSER().getUserSenha();
    }
}
