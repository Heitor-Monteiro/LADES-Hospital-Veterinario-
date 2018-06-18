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
    private final String ndn = "Nada digno de nota.";

    private void prepareSisMuscEsque(Consulta consulta) {
        sisMuscEsqueId = new SisMuscEsqueId();
        sisMuscEsqueId.setConsultaFkConsulta(consulta.getPkConsulta());
        sisMuscEsque.setId(sisMuscEsqueId);
    }

    public void ConfirmeSisMuscEsque(Consulta consulta) {
        try {
            if (sisMuscEsque.getSistemaAfetado().equals("Não")) {
                System.out.println("BACK-END WARNING: N.D.N. [ public void ConfirmeSisMuscEsque() ]");
                sisMuscEsque.setClaudicacao("Não");
                sisMuscEsque.setClaudicacaoEvolu(ndn);
                sisMuscEsque.setFraturas("Não");
                sisMuscEsque.setFraturasEvolu(ndn);
                sisMuscEsque.setAtrofMusc("Não");
                sisMuscEsque.setAtrofMuscEvolu(ndn);
            } else {
                System.out.println("BACK-END WARNING: CONFIRMED [ public void ConfirmeSisMuscEsque() ]");
            }
            prepareSisMuscEsque(consulta);
            getDaoGenerico().save(getSisMuscEsque());
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

    private void startIndexListViewFields() {
        for (int index = 0; index <= 3; index++) {
            listViewFields.add(index, new RenderedFields());
            listViewFields.get(index).setViewVariableBoolean(false);
        }
    }

    public RenderedFields getViewSisMuscEsque() {
        if (getListViewFields(0).isViewVariableBoolean()) {
            sisMuscEsque.setSistemaAfetado("Sim");
        } else {
            sisMuscEsque = new SisMuscEsque();
            sisMuscEsque.setSistemaAfetado("Não");
            startIndexListViewFields();
        }
        return listViewFields.get(0);
    }

    public boolean isViewClaudicacao() {
        return getListViewFields(1).isViewVariableBoolean();
    }

    public void methodViewClaudicacao() {
        if (sisMuscEsque.getClaudicacao() != null) {
            if (sisMuscEsque.getClaudicacao().equals("Sim")) {
                sisMuscEsque.setClaudicacaoEvolu("");
                listViewFields.get(1).setViewVariableBoolean(true);
            } else {
                sisMuscEsque.setClaudicacaoEvolu(ndn);
                listViewFields.get(1).setViewVariableBoolean(false);
            }
        }
    }

    public boolean isViewFraturas() {
        return getListViewFields(2).isViewVariableBoolean();
    }

    public void methodViewFraturas() {
        if (sisMuscEsque.getFraturas() != null) {
            if (sisMuscEsque.getFraturas().equals("Sim")) {
                sisMuscEsque.setFraturasEvolu("");
                listViewFields.get(2).setViewVariableBoolean(true);
            } else {
                sisMuscEsque.setFraturasEvolu(ndn);
                listViewFields.get(2).setViewVariableBoolean(false);
            }
        }
    }

    public boolean isViewAtrofMusc() {
        return getListViewFields(3).isViewVariableBoolean();
    }

    public void methodViewAtrofMusc() {
        if (sisMuscEsque.getAtrofMusc() != null) {
            if (sisMuscEsque.getAtrofMusc().equals("Sim")) {
                sisMuscEsque.setAtrofMuscEvolu("");
                listViewFields.get(3).setViewVariableBoolean(true);
            } else {
                sisMuscEsque.setAtrofMuscEvolu(ndn);
                listViewFields.get(3).setViewVariableBoolean(false);
            }
        }
    }
}
