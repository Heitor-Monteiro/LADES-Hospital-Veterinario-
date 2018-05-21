/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consulta;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.model.Consulta;
import com.lades.sihv.model.SisNeurologico;
import com.lades.sihv.model.SisNeurologicoId;

/**
 *
 * @author thiberius
 */
public class ControllerSisNeurologico extends AbstractBean {
    private SisNeurologico sisNeurologico;
    private SisNeurologicoId sisNeurologicoId;
    
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
        }
        return sisNeurologico;
    }

    public void setSisNeurologico(SisNeurologico sisNeurologico) {
        this.sisNeurologico = sisNeurologico;
    }
}
