/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.animal;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.model.Animais;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author thiberius
 */
public class AnimalGetIdade extends AbstractBean {
    public String getIdade(Animais animal) {
        Date data2 = animal.getDataNac();
        int mes = data2.getMonth();
        int ano = data2.getYear();
        Calendar now = Calendar.getInstance();
        int mesAtual = now.get(Calendar.MONTH);
        String output = "";
        String output2 = "";
        if (mesAtual > mes) {
            output2 = (mesAtual - mes) + " meses";
        }

        if (mesAtual < mes) {
            ano--;
            output2 = (12 - (mes - mesAtual)) + " meses";
        }
        if (ano > 0) {
            output = (now.get(Calendar.YEAR) - ano) + " anos";
            if (mesAtual != mes) {
                output2 = " e " + output2;
            }
        }
        return output + output2;
    }
}
