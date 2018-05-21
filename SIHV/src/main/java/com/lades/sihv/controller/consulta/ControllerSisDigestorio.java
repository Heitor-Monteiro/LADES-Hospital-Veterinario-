/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consulta;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.model.Consulta;
import com.lades.sihv.model.SisDigestorio;
import com.lades.sihv.model.SisDigestorioId;

/**
 *
 * @author thiberius
 */
public class ControllerSisDigestorio extends AbstractBean {

    private SisDigestorio sisDigestorio;
    private SisDigestorioId sisDigestorioId;

    private void prepareSisDigestorio(Consulta consulta) {
        sisDigestorioId = new SisDigestorioId();
        sisDigestorioId.setConsultaFkConsulta(consulta.getPkConsulta());
        sisDigestorio.setId(sisDigestorioId);
    }

    public void ConfirmeSisDigestorio(Consulta consulta) {
        try {
            if (sisDigestorio.getSistemaAfetado().equals("Sim")) {
                System.out.println("BACK-END WARNING: CONFIRMED [ public void ConfirmeSisDigestorio() ]");
                prepareSisDigestorio(consulta);
                getDaoGenerico().save(getSisDigestorio());
            } else {
                System.out.println("BACK-END WARNING: NOT CONFIRMED [ public void ConfirmeSisDigestorio() ]");
            }
        } catch (Exception e) {
            System.out.println("BACK-END WARNING: ERROR [ public void ConfirmeSisDigestorio() ]"
                    + e.getMessage());
        }
    }

//  GETs & SETs
    public SisDigestorio getSisDigestorio() {
        if (sisDigestorio == null) {
            sisDigestorio = new SisDigestorio();
            sisDigestorio.setSistemaAfetado("Não");
        }
        return sisDigestorio;
    }

    public void setSisDigestorio(SisDigestorio sisDigestorio) {
        this.sisDigestorio = sisDigestorio;
    }
}
