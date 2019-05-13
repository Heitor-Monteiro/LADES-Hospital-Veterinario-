/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consultation;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author thiberius
 */
public class InitialConsultationData implements Serializable {

    private Integer pkVetConsultation;
    private Date applicationDate;
    private String textApplicationDate;
    //--------------------------------------------------------------------------
    private Integer pkSchedule;
    private String typeService;
    private Date schedulingDate;
    private String textSchedulingDate;
    //--------------------------------------------------------------------------
    private Integer pkPerson;
    private String namePerson;

    // GETs & SETs -------------------------------------------------------------
    public Integer getPkVetConsultation() {
        return pkVetConsultation;
    }

    public void setPkVetConsultation(Integer pkVetConsultation) {
        this.pkVetConsultation = pkVetConsultation;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Integer getPkSchedule() {
        return pkSchedule;
    }

    public void setPkSchedule(Integer pkSchedule) {
        this.pkSchedule = pkSchedule;
    }

    public String getTypeService() {
        return typeService;
    }

    public void setTypeService(String typeService) {
        this.typeService = typeService;
    }

    public Date getSchedulingDate() {
        return schedulingDate;
    }

    public void setSchedulingDate(Date schedulingDate) {
        this.schedulingDate = schedulingDate;
    }

    public Integer getPkPerson() {
        return pkPerson;
    }

    public void setPkPerson(Integer pkPerson) {
        this.pkPerson = pkPerson;
    }

    public String getNamePerson() {
        return namePerson;
    }

    public void setNamePerson(String namePerson) {
        this.namePerson = namePerson;
    }

    public String getTextApplicationDate() {
        return textApplicationDate;
    }

    public void setTextApplicationDate(String textApplicationDate) {
        this.textApplicationDate = textApplicationDate;
    }

    public String getTextSchedulingDate() {
        return textSchedulingDate;
    }

    public void setTextSchedulingDate(String textSchedulingDate) {
        this.textSchedulingDate = textSchedulingDate;
    }
}
