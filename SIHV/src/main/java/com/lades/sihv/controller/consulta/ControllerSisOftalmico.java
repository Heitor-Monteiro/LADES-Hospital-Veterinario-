/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consulta;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.model.Consulta;
import com.lades.sihv.model.SisOftalmico;
import com.lades.sihv.model.SisOftalmicoId;

/**
 *
 * @author thiberius
 */
public class ControllerSisOftalmico extends AbstractBean {
    private SisOftalmico sisOftalmico;
    private SisOftalmicoId sisOftalmicoId;
    
    private void prepareSisOftalmico(Consulta consulta) {
        sisOftalmicoId = new SisOftalmicoId();
        sisOftalmicoId.setConsultaFkConsulta(consulta.getPkConsulta());
        sisOftalmico.setId(sisOftalmicoId);
    }
    
    public void ConfirmeSisOftalmico(Consulta consulta) {
        try {
            if (sisOftalmico.getSistemaAfetado().equals("Sim")) {
                System.out.println("BACK-END WARNING: CONFIRMED [ public void ConfirmeSisOftalmico() ]");
                prepareSisOftalmico(consulta);
                getDaoGenerico().save(getSisOftalmico());
            } else {
                System.out.println("BACK-END WARNING: NOT CONFIRMED [ public void ConfirmeSisOftalmico() ]");
            }
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
}
