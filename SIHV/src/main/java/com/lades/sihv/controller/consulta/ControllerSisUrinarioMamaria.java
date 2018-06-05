/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consulta;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.RenderedFields;
import com.lades.sihv.model.Consulta;
import com.lades.sihv.model.SisUrinarioMamaria;
import com.lades.sihv.model.SisUrinarioMamariaId;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class ControllerSisUrinarioMamaria extends AbstractBean {
    private SisUrinarioMamaria sisUrinarioMamaria;
    private SisUrinarioMamariaId sisUrinarioMamariaId;
    private final List<RenderedFields> listViewFields = new ArrayList();
    
    private void prepareSisUrinarioMamaria(Consulta consulta) {
        sisUrinarioMamariaId = new SisUrinarioMamariaId();
        sisUrinarioMamariaId.setConsultaFkConsulta(consulta.getPkConsulta());
        sisUrinarioMamaria.setId(sisUrinarioMamariaId);
    }
    
    public void ConfirmeSisUrinarioMamaria(Consulta consulta) {
        try {
            if (sisUrinarioMamaria.getSistemaAfetado().equals("Sim")) {
                System.out.println("BACK-END WARNING: CONFIRMED [ public void ConfirmeSisUrinarioMamaria() ]");
                prepareSisUrinarioMamaria(consulta);
                getDaoGenerico().save(getSisUrinarioMamaria());
            } else {
                System.out.println("BACK-END WARNING: NOT CONFIRMED [ public void ConfirmeSisUrinarioMamaria() ]");
            }
        } catch (Exception e) {
            System.out.println("BACK-END WARNING: ERROR [ public void ConfirmeSisUrinarioMamaria() ]"
                    + e.getMessage());
        }
    }

//  GETs & SETs
    public SisUrinarioMamaria getSisUrinarioMamaria() {
        if (sisUrinarioMamaria == null) {
            sisUrinarioMamaria = new SisUrinarioMamaria();
            sisUrinarioMamaria.setSistemaAfetado("Não");
        }
        return sisUrinarioMamaria;
    }

    public void setSisUrinarioMamaria(SisUrinarioMamaria sisUrinarioMamaria) {
        this.sisUrinarioMamaria = sisUrinarioMamaria;
    }
    
    private RenderedFields getListViewFields(int index) {
        if (listViewFields.isEmpty()) {
            listViewFields.add(index, new RenderedFields());
        } else if (listViewFields.size() < (index + 1)) {
            listViewFields.add(index, new RenderedFields());
        }
        return listViewFields.get(index);
    }

    public RenderedFields getViewSisUrinarioMamaria() {
        if (getListViewFields(0).isViewVariableBoolean()) {
            sisUrinarioMamaria.setSistemaAfetado("Sim");
        } else {
            sisUrinarioMamaria = new SisUrinarioMamaria();
            sisUrinarioMamaria.setSistemaAfetado("Não");
        }
        return listViewFields.get(0);
    }
}
