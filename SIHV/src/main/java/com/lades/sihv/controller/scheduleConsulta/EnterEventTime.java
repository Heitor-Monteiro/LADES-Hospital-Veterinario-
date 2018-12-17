/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.scheduleConsulta;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.GenericScheduling;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author thiberius
 */
public class EnterEventTime extends AbstractBean {

    public void insertTimeMethod(GenericScheduling geneScheduling, String timeConsultation) {
        try {
            String text = new SimpleDateFormat().format(geneScheduling.getEvent().getStartDate());
            text = text.replace("00:00", "");
            text += timeConsultation;
            Date date = new SimpleDateFormat().parse(text);
            geneScheduling.getEvent().getStartDate().setTime(date.getTime());
        } catch (ParseException e) {
            System.out.println("BACK-END WARNING: ERRO public void insertTimeMethod(): "+ e);
        }
    }

}
