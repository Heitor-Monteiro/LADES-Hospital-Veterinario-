/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.animal;

import com.lades.sihv.model.Animais;
import com.lades.sihv.bean.AbstractBean;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class AnimalGerarRGHV extends AbstractBean {
    public void gerarRGHV(Animais animal) {
        //pegando ano atual
        String rghv = "" + Calendar.getInstance().get(Calendar.YEAR);

        //Consultando o tipo(P,G,S)
        rghv += animal.getCategoriaAnimal();
        
        List<Animais> lista = getDaoGenerico().list("select a from Animais a where a.id.pkAnimal >= 1 and a.categoriaAnimal ='" + animal.getCategoriaAnimal() + "'");

        short rghvNum = 0;
        if (lista.size() > 0) {
            rghvNum = (short) getDaoGenerico().list("select max(a.rghvNum) from Animais a where a.categoriaAnimal ='" + animal.getCategoriaAnimal() + "'").get(0);
            System.out.println("Lista não vazia===========================================");
        }

        rghvNum++;

        if (rghvNum <= 9) {
            rghv += "000" + rghvNum;
        } else if (rghvNum <= 99) {
            rghv += "00" + rghvNum;
        } else if (rghvNum <= 999) {
            rghv += "0" + rghvNum;
        } else {
            rghv += rghvNum;
        }

        animal.setRghv(rghv);
        animal.setRghvNum(rghvNum);
        
        
        System.out.println(animal.getRghv()+"|"+animal.getRghvNum());
    }
}
