/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.classeMoldeBusca;

import com.lades.sihv.model.Pessoa;
import com.lades.sihv.model.Fisica;
import com.lades.sihv.model.Juridica;

/**
 *
 * @author thiberius
 */
public class PessoaBusca {
    private Pessoa pessoa = new Pessoa();
    private Fisica pesFisica = new Fisica();
    private Juridica pesJuridica = new Juridica();

    
    
    
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Fisica getPesFisica() {
        return pesFisica;
    }

    public void setPesFisica(Fisica pesFisica) {
        this.pesFisica = pesFisica;
    }

    public Juridica getPesJuridica() {
        return pesJuridica;
    }

    public void setPesJuridica(Juridica pesJuridica) {
        this.pesJuridica = pesJuridica;
    }
    
    
    
}
