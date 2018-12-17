/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.person;

import com.lades.sihv.controller.BeautyText;
import com.lades.sihv.model.People;

/**
 *
 * @author thiberius
 */
public class PessoaPadraoCaracter {

    public void padraoCaracter(People person) {
        try {
            BeautyText stringer = new BeautyText();
            person.setNamePerson(stringer.Captalizador(person.getNamePerson()));
            person.setEmail(person.getEmail().toLowerCase());
//            person.setCidade(stringer.Captalizador(person.getCidade()));
//            person.setBairro(stringer.Captalizador(person.getBairro()));
//            person.setComplemento(stringer.firstCapital(person.getComplemento()));
//            person.setLogra(stringer.firstCapital(person.getLogra()));
        } catch (Exception e) {
        }
    }
}
