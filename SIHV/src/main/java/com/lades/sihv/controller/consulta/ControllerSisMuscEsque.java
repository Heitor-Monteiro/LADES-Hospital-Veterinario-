/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consulta;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.model.Consulta;
import com.lades.sihv.model.SisMuscEsque;
import com.lades.sihv.model.SisMuscEsqueId;

/**
 *
 * @author thiberius
 */
public class ControllerSisMuscEsque extends AbstractBean {
    private SisMuscEsque sisMuscEsque;
    private SisMuscEsqueId sisMuscEsqueId;
    
    private void prepareSisMuscEsque(Consulta consulta) {
        sisMuscEsqueId = new SisMuscEsqueId();
        sisMuscEsqueId.setConsultaFkConsulta(consulta.getPkConsulta());
        sisMuscEsque.setId(sisMuscEsqueId);
    }
    
    public void ConfirmeSisMuscEsque(Consulta consulta) {
        try {
            if (sisMuscEsque.getSistemaAfetado().equals("Sim")) {
                System.out.println("BACK-END WARNING: CONFIRMED [ public void ConfirmeSisMuscEsque() ]");
                prepareSisMuscEsque(consulta);
                getDaoGenerico().save(getSisMuscEsque());
            } else {
                System.out.println("BACK-END WARNING: NOT CONFIRMED [ public void ConfirmeSisMuscEsque() ]");
            }
        } catch (Exception e) {
            System.out.println("BACK-END WARNING: ERROR [ public void ConfirmeSisMuscEsque() ]"
                    + e.getMessage());
        }
    }

//  GETs & SETs
    public SisMuscEsque getSisMuscEsque() {
        if (sisMuscEsque == null) {
            sisMuscEsque = new SisMuscEsque();
        }
        return sisMuscEsque;
    }

    public void setSisMuscEsque(SisMuscEsque sisMuscEsque) {
        this.sisMuscEsque = sisMuscEsque;
    }
}
