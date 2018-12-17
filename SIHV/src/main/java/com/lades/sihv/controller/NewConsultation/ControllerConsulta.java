/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.NewConsultation;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.model.VetConsultation;
import com.lades.sihv.model.Users;
import com.lades.sihv.controller.ListRenderedFields;
import com.lades.sihv.controller.RenderedFields;
import com.lades.sihv.model.OwnersHasAnimals;
import com.lades.sihv.model.Scheduling;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class ControllerConsulta extends AbstractBean {

    private final VetConsultation consultation;
    private int codNewConsulta;
    private final ListRenderedFields listViewFields;

    public ControllerConsulta() {
        codNewConsulta = 0;
        consultation = new VetConsultation();
        consultation.setDiscountValue(BigDecimal.ZERO);
        listViewFields = new ListRenderedFields(3);
        listViewFields.startIndexListViewFields();
    }

    private void prepareConsulta(Scheduling scheduling, OwnersHasAnimals ownersHasAnimals, Users residente) {
        consultation.setApplicationDate(getObjData());
        consultation.setScheduling(scheduling);
        consultation.setOwnersHasAnimals(ownersHasAnimals);
        consultation.setUsers(residente);
    }

    public VetConsultation ConfirmeConsulta(SchedulesConfirmedForConsultation selectScheduleConfirmed, Users residente) {
        try {
            prepareConsulta(selectScheduleConfirmed.getSchedule(), selectScheduleConfirmed.getOwnersHasAnimals(), residente);
            getDaoGenerico().save(consultation);
        } catch (Exception e) {
            System.out.println("BACK-END WARNING: ERROR [ public Consulta ConfirmeConsulta() ] "
                    + e.getMessage());
        }
        return consultation;
    }

    /*O método é utilizado para saber qual
    será o próximo código de uma nova consulta.*/
    public void generateMaxExamCode() {
        List<?> list;
        list = getDaoGenerico().list("select c.pkVetConsultation from VetConsultation c where c.pkVetConsultation=1");
        if (list.size() > 0) {
            codNewConsulta = (int) getDaoGenerico().list("select max(c.pkVetConsultation) from VetConsultation c").get(0);
            codNewConsulta++;
        } else {
            codNewConsulta = 1;
        }
    }

    private String methodDiagnosis(int index, String typeDiagnosis) {
        if (listViewFields.getListViewFields(index).isViewVariableBoolean()) {
            typeDiagnosis = "Diagnósticos " + typeDiagnosis + " inconclusivo.";
        } else {
            typeDiagnosis = "";
        }
        return typeDiagnosis;
    }

    public RenderedFields getStatusDiagPresuntivo() {
        return listViewFields.getListViewFields(0);
    }

    public void methodDiagPresuntivo() {
        consultation.setDiagPresuntivo(methodDiagnosis(0, "presuntivo"));
    }

    public RenderedFields getStatusDiagDiferencial() {
        return listViewFields.getListViewFields(1);
    }

    public void methodDiagDiferencial() {
        consultation.setDiagDiferencial(methodDiagnosis(1, "diferencial"));
    }

    public RenderedFields getStatusDiagDefinitivo() {
        return listViewFields.getListViewFields(2);
    }

    public void methodDiagDefinitivo() {
        consultation.setDiagDefinitivo(methodDiagnosis(2, "definitivo"));
    }

    //GETs & SETs
    public VetConsultation getConsultation() {
        return consultation;
    }

    public int getCodNewConsulta() {
        return codNewConsulta;
    }
}
