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
    private final String ndn = "Nada digno de nota.";

    private void prepareSisDigestorio(Consulta consulta) {
        sisDigestorioId = new SisDigestorioId();
        sisDigestorioId.setConsultaFkConsulta(consulta.getPkConsulta());
        sisDigestorio.setId(sisDigestorioId);
    }

    public void ConfirmeSisDigestorio(Consulta consulta) {
        try {
            if (sisDigestorio.getSistemaAfetado().equals("Não")) {
                System.out.println("BACK-END WARNING: N.D.N. [ public void ConfirmeSisDigestorio() ]");
                sisDigestorio.setEstaSeAlimentando("Sim");
                sisDigestorio.setDescriNaoSeAlimeta(ndn);
                sisDigestorio.setVomito("Não");
                sisDigestorio.setAspectoVomito(ndn);
                sisDigestorio.setEvoluVomito(ndn);
                sisDigestorio.setRegurgitacao("Não");
                sisDigestorio.setEvoluRegurgitacao(ndn);
                sisDigestorio.setDiarreia("Não");
                sisDigestorio.setAspectDiarreia(ndn);
                sisDigestorio.setEvoluDiarreia(ndn);
                sisDigestorio.setDisquesiaTenesmo("Não");
                sisDigestorio.setEvoluDisquesiaTenesmo(ndn);
            } else {
                System.out.println("BACK-END WARNING: CONFIRMED [ public void ConfirmeSisDigestorio() ]");
            }
            prepareSisDigestorio(consulta);
            getDaoGenerico().save(getSisDigestorio());
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
        } else if ((listViewFields.size() - index) == 0) {
            listViewFields.add(index, new RenderedFields());
        }
        return listViewFields.get(index);
    }

    private void startIndexListViewFields() {
        for (int index = 0; index <= 5; index++) {
            listViewFields.add(index, new RenderedFields());
            listViewFields.get(index).setViewVariableBoolean(false);
        }
    }

    public RenderedFields getViewSisDigestorio() {
        if (getListViewFields(0).isViewVariableBoolean()) {
            sisDigestorio.setSistemaAfetado("Sim");
        } else {
            sisDigestorio = new SisDigestorio();
            sisDigestorio.setSistemaAfetado("Não");
            startIndexListViewFields();
        }
        return listViewFields.get(0);
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
                sisDigestorio.setDescriNaoSeAlimeta(ndn);
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
                sisDigestorio.setAspectoVomito(ndn);
                sisDigestorio.setEvoluVomito(ndn);
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
                sisDigestorio.setEvoluRegurgitacao(ndn);
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
                sisDigestorio.setAspectDiarreia(ndn);
                sisDigestorio.setEvoluDiarreia(ndn);
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
                sisDigestorio.setEvoluDisquesiaTenesmo(ndn);
                listViewFields.get(5).setViewVariableBoolean(false);
            }
        }
    }
}
