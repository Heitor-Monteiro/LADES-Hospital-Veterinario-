/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consulta;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.RenderedFields;
import com.lades.sihv.model.Consulta;
import com.lades.sihv.model.SisNeurologico;
import com.lades.sihv.model.SisNeurologicoId;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class ControllerSisNeurologico extends AbstractBean {
    private SisNeurologico sisNeurologico;
    private SisNeurologicoId sisNeurologicoId;
    private final List<RenderedFields> listViewFields = new ArrayList();
    
    private void prepareSisNeurologico(Consulta consulta) {
        sisNeurologicoId = new SisNeurologicoId();
        sisNeurologicoId.setConsultaFkConsulta(consulta.getPkConsulta());
        sisNeurologico.setId(sisNeurologicoId);
    }
    
    public void ConfirmeSisNeurologico(Consulta consulta) {
        try {
            if (sisNeurologico.getSistemaAfetado().equals("Sim")) {
                System.out.println("BACK-END WARNING: CONFIRMED [ public void ConfirmeSisNeurologico() ]");
                prepareSisNeurologico(consulta);
                getDaoGenerico().save(getSisNeurologico());
            } else {
                System.out.println("BACK-END WARNING: NOT CONFIRMED [ public void ConfirmeSisNeurologico() ]");
            }
        } catch (Exception e) {
            System.out.println("BACK-END WARNING: ERROR [ public void ConfirmeSisNeurologico() ]"
                    + e.getMessage());
        }
    }

//  GETs & SETs
    public SisNeurologico getSisNeurologico() {
        if (sisNeurologico == null) {
            sisNeurologico = new SisNeurologico();
            sisNeurologico.setSistemaAfetado("Não");
        }
        return sisNeurologico;
    }

    public void setSisNeurologico(SisNeurologico sisNeurologico) {
        this.sisNeurologico = sisNeurologico;
    }
    
    private RenderedFields getListViewFields(int index) {
        if (listViewFields.isEmpty()) {
            listViewFields.add(index, new RenderedFields());
        } else if (listViewFields.size() < (index + 1)) {
            listViewFields.add(index, new RenderedFields());
        }
        return listViewFields.get(index);
    }

    public RenderedFields getViewSisNeurologico() {
        if (getListViewFields(0).isViewVariableBoolean()) {
            sisNeurologico.setSistemaAfetado("Sim");
        } else {
            sisNeurologico = new SisNeurologico();
            sisNeurologico.setSistemaAfetado("Não");
        }
        return listViewFields.get(0);
    }
}
