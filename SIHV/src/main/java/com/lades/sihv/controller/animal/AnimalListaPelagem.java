/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.animal;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.model.Animais;
import com.lades.sihv.model.Pelagem;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class AnimalListaPelagem extends AbstractBean {

    private List<String> listaPelagem;

    public void carregarListaPelagem() {
        listaPelagem = getDaoGenerico().getPelagemNames();
    }

    public List<String> getListaPelagem() {
        return listaPelagem;
    }

    public void verificaNovaPelagem(Animais animal) {
        boolean newPelagem = true;
        for (String check : listaPelagem) {
            if (check.equals(animal.getPelagem())) {
                newPelagem = false;
            }
        }

        if (newPelagem) {
            Pelagem novaPelagem = new Pelagem();
            novaPelagem.setNomePelagem(animal.getPelagem());
            getDaoGenerico().save(novaPelagem);
        }
    }
}
