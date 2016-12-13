/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller;

import com.lades.sihv.model.Animais;
import com.lades.sihv.model.Cliente;
import com.lades.sihv.model.Consulta;
import com.lades.sihv.model.Pessoa;

/**
 *
 * @author thiberius
 */
public class ConsultaBusca {
    private Pessoa pessoa;
    private Cliente cliente;
    private Animais animais;
    private Consulta consulta;

    
    public void geraObj(){
        animais = new Animais();
        pessoa = new Pessoa();
        consulta = new Consulta();
    }

    
    
    
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    
    
    
}
