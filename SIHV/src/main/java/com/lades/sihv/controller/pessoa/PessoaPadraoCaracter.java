/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.pessoa;

import com.lades.sihv.controller.BeautyText;
import com.lades.sihv.model.Pessoa;

/**
 *
 * @author thiberius
 */
public class PessoaPadraoCaracter {

    public void padraoCaracter(Pessoa pessoa) {
        try {
            BeautyText stringer = new BeautyText();
            pessoa.setNome(stringer.Captalizador(pessoa.getNome()));
            pessoa.setEmail(pessoa.getEmail().toLowerCase());
            pessoa.setCidade(stringer.Captalizador(pessoa.getCidade()));
            pessoa.setBairro(stringer.Captalizador(pessoa.getBairro()));
            pessoa.setComplemento(stringer.firstCapital(pessoa.getComplemento()));
            pessoa.setLogra(stringer.firstCapital(pessoa.getLogra()));
        } catch (Exception e) {
        }
    }
}
