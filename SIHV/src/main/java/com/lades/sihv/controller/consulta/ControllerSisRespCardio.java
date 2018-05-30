/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consulta;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.model.Consulta;
import com.lades.sihv.model.SisRespCardio;
import com.lades.sihv.model.SisRespCardioId;

/**
 *
 * @author thiberius
 */
public class ControllerSisRespCardio extends AbstractBean {
    private SisRespCardio sisRespCardio;
    private SisRespCardioId sisRespCardioId;
    
    private void prepareSisRespCardio(Consulta consulta) {
        sisRespCardioId = new SisRespCardioId();
        sisRespCardioId.setConsultaFkConsulta(consulta.getPkConsulta());
        sisRespCardio.setId(sisRespCardioId);
    }
    
    public void ConfirmeSisRespCardio(Consulta consulta) {
        try {
            if (sisRespCardio.getSistemaAfetado().equals("Sim")) {
                System.out.println("BACK-END WARNING: CONFIRMED [ public void ConfirmeSisRespCardio() ]");
                prepareSisRespCardio(consulta);
                getDaoGenerico().save(getSisRespCardio());
            } else {
                System.out.println("BACK-END WARNING: NOT CONFIRMED [ public void ConfirmeSisRespCardio() ]");
            }
        } catch (Exception e) {
            System.out.println("BACK-END WARNING: ERROR [ public void ConfirmeSisRespCardio() ]"
                    + e.getMessage());
        }
    }

//  GETs & SETs
    public SisRespCardio getSisRespCardio() {
        if (sisRespCardio == null) {
            sisRespCardio = new SisRespCardio();
            sisRespCardio.setSistemaAfetado("Não");
        }
        return sisRespCardio;
    }

    public void setSisRespCardio(SisRespCardio sisRespCardio) {
        this.sisRespCardio = sisRespCardio;
    }
}
