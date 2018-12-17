/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.animal;

import com.lades.sihv.bean.AbstractBean;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class GenerateRghvFromAnimal extends AbstractBean {

    private String searchMaxRGHV(String typeAnimal) {
        String hql = "";
        switch (typeAnimal) {
            case "P":
                hql = "select s from SmallAnimal s";
                break;
            case "S":
                hql = "";
                break;
            case "N":
                hql = "";
                break;
            case "G":
                hql = "";
                break;
        }
        return hql;
    }

    public void methodNewRghvAnimal(VariablesAnimal varAnimal, String typeAnimal) {
        List<?> listAnimalSmall = getDaoGenerico().list(searchMaxRGHV(typeAnimal));
        int maxNumber = 0;
        if (listAnimalSmall.size() > 0) {
            maxNumber = listAnimalSmall.size() + 1;
        } else {
            maxNumber += 1;
        }
        varAnimal.setTempRGHV("" + maxNumber + typeAnimal
                + GregorianCalendar.getInstance().get(Calendar.YEAR));
    }
}
