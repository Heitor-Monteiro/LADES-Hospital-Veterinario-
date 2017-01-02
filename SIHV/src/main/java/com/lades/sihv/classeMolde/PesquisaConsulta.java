/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.classeMolde;

import com.lades.sihv.model.Animais;
import com.lades.sihv.model.Consulta;
import com.lades.sihv.model.Pessoa;
import com.lades.sihv.model.User;

/**
 *
 * @author thiberius
 */
public class PesquisaConsulta {
    private Pessoa pessoa;
    private User user;
    private Animais animais;
    private Consulta consulta;

    
    public void geraObj(){
        pessoa = new Pessoa();
        user = new User();
        animais = new Animais();
        consulta = new Consulta();
    }

    
    
    
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Animais getAnimais() {
        return animais;
    }

    public void setAnimais(Animais animais) {
        this.animais = animais;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
