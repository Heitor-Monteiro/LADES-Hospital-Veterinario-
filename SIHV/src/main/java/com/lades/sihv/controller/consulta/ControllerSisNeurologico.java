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
    private final String ndn = "Nada digno de nota.";
    
    private void prepareSisNeurologico(Consulta consulta) {
        sisNeurologicoId = new SisNeurologicoId();
        sisNeurologicoId.setConsultaFkConsulta(consulta.getPkConsulta());
        sisNeurologico.setId(sisNeurologicoId);
    }
    
    public void ConfirmeSisNeurologico(Consulta consulta) {
        try {
            if (sisNeurologico.getSistemaAfetado().equals("Não")) {
                System.out.println("BACK-END WARNING: N.D.N. [ public void ConfirmeSisNeurologico() ]");
                sisNeurologico.setConsciencia("Alerta");
                sisNeurologico.setComportamento("Normal");
                sisNeurologico.setAtaxia("Não");
                sisNeurologico.setAtaxiaTipo(ndn);
                sisNeurologico.setAtaxiaEvolu(ndn);
                sisNeurologico.setParalisia("Não");
                sisNeurologico.setParalisiaEspFla("N.D.N.");
                sisNeurologico.setParalisiaTipo(ndn);
                sisNeurologico.setParalisiaEvolu(ndn);
                sisNeurologico.setConvulsao("Não");
                sisNeurologico.setConvulsaoTipo(ndn);
                sisNeurologico.setConvulsaoEvolu(ndn);
                sisNeurologico.setAudicao("Normal");
            } else {
                System.out.println("BACK-END WARNING: CONFIRMED [ public void ConfirmeSisNeurologico() ]");
            }
            prepareSisNeurologico(consulta);
            getDaoGenerico().save(getSisNeurologico());
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
    
    public RenderedFields getViewSisNeurologico() {
        if (getListViewFields(0).isViewVariableBoolean()) {
            sisNeurologico.setSistemaAfetado("Sim");
        } else {
            sisNeurologico = new SisNeurologico();
            sisNeurologico.setSistemaAfetado("Não");
            startIndexListViewFields();
        }
        return listViewFields.get(0);
    }
    
    public boolean isViewAtaxiaTipoEvolu() {
        return getListViewFields(1).isViewVariableBoolean();
    }
    
    public void methodViewAtaxiaTipoEvolu() {
        if (sisNeurologico.getAtaxia() != null) {
            if (sisNeurologico.getAtaxia().equals("Sim")) {
                sisNeurologico.setAtaxiaTipo("");
                sisNeurologico.setAtaxiaEvolu("");
                listViewFields.get(1).setViewVariableBoolean(true);
            } else {
                sisNeurologico.setAtaxiaTipo(ndn);
                sisNeurologico.setAtaxiaEvolu(ndn);
                listViewFields.get(1).setViewVariableBoolean(false);
            }
        }
    }
    
    public boolean isViewParalisia() {
        return getListViewFields(2).isViewVariableBoolean();
    }
    
    public void methodViewParalisia() {
        if (sisNeurologico.getParalisia() != null) {
            if (sisNeurologico.getParalisia().equals("Sim")) {
                sisNeurologico.setParalisiaEspFla("");
                sisNeurologico.setParalisiaTipo("");
                sisNeurologico.setParalisiaEvolu("");
                listViewFields.get(2).setViewVariableBoolean(true);
            } else {
                sisNeurologico.setParalisiaEspFla("N.D.N.");
                sisNeurologico.setParalisiaTipo(ndn);
                sisNeurologico.setParalisiaEvolu(ndn);
                listViewFields.get(2).setViewVariableBoolean(false);
            }
        }
    }
    
    public boolean isViewConvulsaoTipoEvolu() {
        return getListViewFields(3).isViewVariableBoolean();
    }
    
    public void methodViewConvulsaoTipoEvolu() {
        if (sisNeurologico.getConvulsao() != null) {
            if (sisNeurologico.getConvulsao().equals("Sim")) {
                sisNeurologico.setConvulsaoTipo("");
                sisNeurologico.setConvulsaoEvolu("");
                listViewFields.get(3).setViewVariableBoolean(true);
            } else {
                sisNeurologico.setConvulsaoTipo(ndn);
                sisNeurologico.setConvulsaoEvolu(ndn);
                listViewFields.get(3).setViewVariableBoolean(false);
            }
        }
    }
}
