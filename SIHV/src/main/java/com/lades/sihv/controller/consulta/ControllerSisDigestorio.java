/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consulta;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.RenderedFields;
import com.lades.sihv.model.Consulta;
import com.lades.sihv.model.SisDigestorio;
import com.lades.sihv.model.SisDigestorioId;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class ControllerSisDigestorio extends AbstractBean {

    private SisDigestorio sisDigestorio;
    private SisDigestorioId sisDigestorioId;
    private final List<RenderedFields> listViewFields = new ArrayList();

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

    private RenderedFields getListViewFields(int index) {
        if (listViewFields.isEmpty()) {
            listViewFields.add(index, new RenderedFields());
        } else if (listViewFields.size() < (index + 1)) {
            listViewFields.add(index, new RenderedFields());
        }
        return listViewFields.get(index);
    }

    public boolean isViewSisDigestorio() {
        return getListViewFields(0).isViewVariableBoolean();
    }

    public void methodViewSisDigestorio() {
        if (sisDigestorio.getSistemaAfetado() != null) {
            if (sisDigestorio.getSistemaAfetado().equals("Sim")) {
                listViewFields.get(0).setViewVariableBoolean(true);
            } else {
                sisDigestorio = new SisDigestorio();
                sisDigestorio.setSistemaAfetado("Não");
                listViewFields.get(0).setViewVariableBoolean(false);
            }
        }
    }

    public boolean isViewDescriNaoSeAlimeta() {
        return getListViewFields(1).isViewVariableBoolean();
    }

    public void methodViewDescriNaoSeAlimeta() {
        if (sisDigestorio.getEstaSeAlimentando() != null) {
            if (sisDigestorio.getEstaSeAlimentando().equals("Não")) {
                sisDigestorio.setDescriNaoSeAlimeta("");
                listViewFields.get(1).setViewVariableBoolean(true);
            } else {
                sisDigestorio.setDescriNaoSeAlimeta("Nada digno de nota.");
                listViewFields.get(1).setViewVariableBoolean(false);
            }
        }
    }

    public boolean isViewAspectoEvoluVomito() {
        return getListViewFields(2).isViewVariableBoolean();
    }

    public void methodViewAspectoEvoluVomito() {
        if (sisDigestorio.getVomito() != null) {
            if (sisDigestorio.getVomito().equals("Sim")) {
                sisDigestorio.setAspectoVomito("");
                sisDigestorio.setEvoluVomito("");
                listViewFields.get(2).setViewVariableBoolean(true);
            } else {
                sisDigestorio.setAspectoVomito("Nada digno de nota.");
                sisDigestorio.setEvoluVomito("Nada digno de nota.");
                listViewFields.get(2).setViewVariableBoolean(false);
            }
        }
    }

    public boolean isViewEvoluRegurgitacao() {
        return getListViewFields(3).isViewVariableBoolean();
    }

    public void methodViewEvoluRegurgitacao() {
        if (sisDigestorio.getRegurgitacao() != null) {
            if (sisDigestorio.getRegurgitacao().equals("Sim")) {
                sisDigestorio.setEvoluRegurgitacao("");
                listViewFields.get(3).setViewVariableBoolean(true);
            } else {
                sisDigestorio.setEvoluRegurgitacao("Nada digno de nota.");
                listViewFields.get(3).setViewVariableBoolean(false);
            }
        }
    }
    
    public boolean isViewAspectEvoluDiarreia() {
        return getListViewFields(4).isViewVariableBoolean();
    }

    public void methodViewAspectEvoluDiarreia() {
        if (sisDigestorio.getDiarreia() != null) {
            if (sisDigestorio.getDiarreia().equals("Sim")) {
                sisDigestorio.setAspectDiarreia("");
                sisDigestorio.setEvoluDiarreia("");
                listViewFields.get(4).setViewVariableBoolean(true);
            } else {
                sisDigestorio.setAspectDiarreia("Nada digno de nota.");
                sisDigestorio.setEvoluDiarreia("Nada digno de nota.");
                listViewFields.get(4).setViewVariableBoolean(false);
            }
        }
    }
    
    public boolean isViewEvoluDisquesiaTenesmo() {
        return getListViewFields(5).isViewVariableBoolean();
    }

    public void methodViewEvoluDisquesiaTenesmo() {
        if (sisDigestorio.getDisquesiaTenesmo() != null) {
            if (sisDigestorio.getDisquesiaTenesmo().equals("Sim")) {
                sisDigestorio.setEvoluDisquesiaTenesmo("");
                listViewFields.get(5).setViewVariableBoolean(true);
            } else {
                sisDigestorio.setEvoluDisquesiaTenesmo("Nada digno de nota.");
                listViewFields.get(5).setViewVariableBoolean(false);
            }
        }
    }
}
