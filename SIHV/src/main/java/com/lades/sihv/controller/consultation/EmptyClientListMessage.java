/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consultation;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.VariablesSearch;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author thiberius
 */
public class EmptyClientListMessage extends AbstractBean {

    public void methodEmptyClientListMessage(VariablesSearch objVarSearch) {
        String x = "";
        String y = objVarSearch.getTextSearch();
        switch (objVarSearch.getItemSearch()) {
            case "RGHV":
                x = "RGHV";
                break;
            case "NameAnimal":
                x = "Nome do animal";
                break;
            case "NameOwner":
                x = "Nome do proprietário";
                break;
            case "BetweenDates":
                DateFormat formatUS = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                y = formatUS.format(objVarSearch.getDateInitial()).substring(0, 11) + " a ";
                y = y + formatUS.format(objVarSearch.getDateEnd()).substring(0, 11);
                x = "Entre datas";
                break;
        }
        getObjMessage().warn("Listagem vazia! Não foi encontrado '"
                + x + "' para a seguinte ocorrência: "
                + y + ".", "");
    }
}
