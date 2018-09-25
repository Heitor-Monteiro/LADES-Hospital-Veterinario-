/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consulta;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.RenderedFields;
import com.lades.sihv.model.VetConsultation;
import com.lades.sihv.model.SisOftalmico;
import com.lades.sihv.model.SisOftalmicoId;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class ControllerSisOftalmico extends AbstractBean {

    private SisOftalmico sisOftalmico;
    private SisOftalmicoId sisOftalmicoId;
    private final List<RenderedFields> listViewFields = new ArrayList();
    private final String ndn = "Nada digno de nota.";

    private void prepareSisOftalmico(VetConsultation consultation) {
        sisOftalmicoId = new SisOftalmicoId();
        sisOftalmicoId.setVetConsultationPkVetConsultation(consultation.getPkVetConsultation());
        sisOftalmico.setId(sisOftalmicoId);
    }

    public void ConfirmeSisOftalmico(VetConsultation consultation) {
        try {
            if (sisOftalmico.getSistemaAfetado().equals("Não")) {
                System.out.println("BACK-END WARNING: N.D.N [ public void ConfirmeSisOftalmico() ]");
                sisOftalmico.setOlhosPupila("Normal");
                sisOftalmico.setSecreOcular("Não");
                sisOftalmico.setSecreOcularUniBi("N.D.N.");
                sisOftalmico.setSecreOculaTipo(ndn);
                sisOftalmico.setSecreOculaEvolu(ndn);
                sisOftalmico.setBlefaroespasmo("Não");
                sisOftalmico.setBlefaroComenta(ndn);
                sisOftalmico.setExoftalmia("Não");
                sisOftalmico.setExoftalComenta(ndn);
            } else {
                System.out.println("BACK-END WARNING: CONFIRMED [ public void ConfirmeSisOftalmico() ]");
            }
            prepareSisOftalmico(consultation);
            getDaoGenerico().save(getSisOftalmico());
        } catch (Exception e) {
            System.out.println("BACK-END WARNING: ERROR [ public void ConfirmeSisOftalmico() ]"
                    + e.getMessage());
        }
    }

//  GETs & SETs
    public SisOftalmico getSisOftalmico() {
        if (sisOftalmico == null) {
            sisOftalmico = new SisOftalmico();
            sisOftalmico.setSistemaAfetado("Não");
        }
        return sisOftalmico;
    }

    public void setSisOftalmico(SisOftalmico sisOftalmico) {
        this.sisOftalmico = sisOftalmico;
    }

    private RenderedFields getListViewFields(int index) {
        if (listViewFields.isEmpty()) {
            listViewFields.add(index, new RenderedFields());
        } else if ((listViewFields.size() - index) == 0) {
            listViewFields.add(index, new RenderedFields());
        }
        return listViewFields.get(index);
    }

    private void startIndexListViewFields() {
        for (int index = 0; index <= 3; index++) {
            listViewFields.add(index, new RenderedFields());
            listViewFields.get(index).setViewVariableBoolean(false);
        }
    }

    public RenderedFields getViewSisRespCardio() {
        if (getListViewFields(0).isViewVariableBoolean()) {
            sisOftalmico.setSistemaAfetado("Sim");
        } else {
            sisOftalmico = new SisOftalmico();
            sisOftalmico.setSistemaAfetado("Não");
            startIndexListViewFields();
        }
        return listViewFields.get(0);
    }

    public boolean isViewSecreOcular() {
        return getListViewFields(1).isViewVariableBoolean();
    }

    public void methodViewSecreOcular() {
        if (sisOftalmico.getSecreOcular() != null) {
            if (sisOftalmico.getSecreOcular().equals("Sim")) {
                sisOftalmico.setSecreOcularUniBi("");
                sisOftalmico.setSecreOculaTipo("");
                sisOftalmico.setSecreOculaEvolu("");
                listViewFields.get(1).setViewVariableBoolean(true);
            } else {
                sisOftalmico.setSecreOcularUniBi("N.D.N.");
                sisOftalmico.setSecreOculaTipo(ndn);
                sisOftalmico.setSecreOculaEvolu(ndn);
                listViewFields.get(1).setViewVariableBoolean(false);
            }
        }
    }

    public boolean isViewBlefaroespasmo() {
        return getListViewFields(2).isViewVariableBoolean();
    }

    public void methodViewBlefaroespasmo() {
        if (sisOftalmico.getBlefaroespasmo() != null) {
            if (sisOftalmico.getBlefaroespasmo().equals("Sim")) {
                sisOftalmico.setBlefaroComenta("");
                listViewFields.get(2).setViewVariableBoolean(true);
            } else {
                sisOftalmico.setBlefaroComenta(ndn);
                listViewFields.get(2).setViewVariableBoolean(false);
            }
        }
    }

    public boolean isViewExoftalmia() {
        return getListViewFields(3).isViewVariableBoolean();
    }

    public void methodViewExoftalmia() {
        if (sisOftalmico.getExoftalmia() != null) {
            if (sisOftalmico.getExoftalmia().equals("Sim")) {
                sisOftalmico.setExoftalComenta("");
                listViewFields.get(3).setViewVariableBoolean(true);
            } else {
                sisOftalmico.setExoftalComenta(ndn);
                listViewFields.get(3).setViewVariableBoolean(false);
            }
        }
    }
}
