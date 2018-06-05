/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consulta;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.RenderedFields;
import com.lades.sihv.model.Consulta;
import com.lades.sihv.model.SisTegumentar;
import com.lades.sihv.model.SisTegumentarId;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class ControllerSisTegumentar extends AbstractBean {
    private SisTegumentar sisTegumentar;
    private SisTegumentarId sisTegumentarId;
    private final List<RenderedFields> listViewFields = new ArrayList();
    
    private void prepareSisTegumentar(Consulta consulta) {
        sisTegumentarId = new SisTegumentarId();
        sisTegumentarId.setConsultaFkConsulta(consulta.getPkConsulta());
        sisTegumentar.setId(sisTegumentarId);
    }
    
    public void ConfirmeSisTegumentar(Consulta consulta) {
        try {
            if (sisTegumentar.getSistemaAfetado().equals("Sim")) {
                System.out.println("BACK-END WARNING: CONFIRMED [ public void ConfirmeSisTegumentar() ]");
                prepareSisTegumentar(consulta);
                getDaoGenerico().save(getSisTegumentar());
            } else {
                System.out.println("BACK-END WARNING: NOT CONFIRMED [ public void ConfirmeSisTegumentar() ]");
            }
        } catch (Exception e) {
            System.out.println("BACK-END WARNING: ERROR [ public void ConfirmeSisTegumentar() ]"
                    + e.getMessage());
        }
    }

//  GETs & SETs
    public SisTegumentar getSisTegumentar() {
        if (sisTegumentar == null) {
            sisTegumentar = new SisTegumentar();
            sisTegumentar.setSistemaAfetado("Não");
        }
        return sisTegumentar;
    }

    public void setSisTegumentar(SisTegumentar sisTegumentar) {
        this.sisTegumentar = sisTegumentar;
    }
    
    private RenderedFields getListViewFields(int index) {
        if (listViewFields.isEmpty()) {
            listViewFields.add(index, new RenderedFields());
        } else if (listViewFields.size() < (index + 1)) {
            listViewFields.add(index, new RenderedFields());
        }
        return listViewFields.get(index);
    }

    public RenderedFields getViewSisTegumentar() {
        if (getListViewFields(0).isViewVariableBoolean()) {
            sisTegumentar.setSistemaAfetado("Sim");
        } else {
            sisTegumentar = new SisTegumentar();
            sisTegumentar.setSistemaAfetado("Não");
        }
        return listViewFields.get(0);
    }
}
