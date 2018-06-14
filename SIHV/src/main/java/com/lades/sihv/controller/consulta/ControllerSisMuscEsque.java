/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consulta;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.RenderedFields;
import com.lades.sihv.model.Consulta;
import com.lades.sihv.model.SisMuscEsque;
import com.lades.sihv.model.SisMuscEsqueId;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class ControllerSisMuscEsque extends AbstractBean {

    private SisMuscEsque sisMuscEsque;
    private SisMuscEsqueId sisMuscEsqueId;
    private final List<RenderedFields> listViewFields = new ArrayList();

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
            sisMuscEsque.setSistemaAfetado("Não");
        }
        return sisMuscEsque;
    }

    public void setSisMuscEsque(SisMuscEsque sisMuscEsque) {
        this.sisMuscEsque = sisMuscEsque;
    }

    private RenderedFields getListViewFields(int index) {
        if (listViewFields.isEmpty()) {
            listViewFields.add(index, new RenderedFields());
        } else if ((listViewFields.size() - index) == 0) {
            listViewFields.add(index, new RenderedFields());
        }
        return listViewFields.get(index);
    }

    public RenderedFields getViewSisMuscEsque() {
        if (getListViewFields(0).isViewVariableBoolean()) {
            sisMuscEsque.setSistemaAfetado("Sim");
        } else {
            sisMuscEsque = new SisMuscEsque();
            sisMuscEsque.setSistemaAfetado("Não");
        }
        return listViewFields.get(0);
    }
}
