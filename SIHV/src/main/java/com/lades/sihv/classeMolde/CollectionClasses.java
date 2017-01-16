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
import com.lades.sihv.model.Fisica;
import java.io.Serializable;

/**
 *
 * @author thiberius
 */
public class CollectionClasses implements Serializable {
    private Pessoa residente;
    private Pessoa proprietario;
    private Fisica pessoaFisica;
    private User user;
    private Animais animais;
    private Consulta consulta;

    
    public void geraObj(){
        residente = new Pessoa();
        proprietario = new Pessoa();
        user = new User();
        animais = new Animais();
        consulta = new Consulta();
    }

    public void gerarObjNovaConsulta(){
        animais = new Animais();
        proprietario = new Pessoa();
    }
    
    public void gerarObjPessoaFisica(){
        pessoaFisica = new Fisica();
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

    public Pessoa getResidente() {
        return residente;
    }

    public void setResidente(Pessoa residente) {
        this.residente = residente;
    }

    public Pessoa getProprietario() {
        return proprietario;
    }

    public void setProprietario(Pessoa proprietario) {
        this.proprietario = proprietario;
    }

    public Fisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(Fisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }
    
}
