/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consulta;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.classeMolde.CollectionClasses;
import com.lades.sihv.controller.RenderedFields;
import com.lades.sihv.model.VetConsultation;
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
    private final String ndnMasculino = "Nada digno de nota devido o animal ser do gênero masculino";
    private final String ndn = "Nada digno de nota.";

    private void prepareSisUrinarioMamaria(VetConsultation consultation) {
        sisUrinarioMamariaId = new SisUrinarioMamariaId();
        sisUrinarioMamariaId.setVetConsultationPkVetConsultation(consultation.getPkVetConsultation());
        sisUrinarioMamaria.setId(sisUrinarioMamariaId);
    }

    public void ConfirmeSisUrinarioMamaria(VetConsultation consultation) {
        try {
            if (sisUrinarioMamaria.getSistemaAfetado().equals("Não")) {
                System.out.println("BACK-END WARNING: N.D.N. [ public void ConfirmeSisUrinarioMamaria() ]");
                sisUrinarioMamaria.setIngestHidrica("Normal");
                sisUrinarioMamaria.setIngestHidricaEvolu(ndn);
                sisUrinarioMamaria.setEstadoUrina("Normal");
                sisUrinarioMamaria.setUrina("N.D.N.");
                sisUrinarioMamaria.setUrinaAspecto(ndn);
                sisUrinarioMamaria.setUrinaEvolu(ndn);
                sisUrinarioMamaria.setSecreVagiPeni("Não");
                sisUrinarioMamaria.setSecreVagiPeniTipo(ndn);
                sisUrinarioMamaria.setSecreVagiPeniEvolu(ndn);
                sisUrinarioMamaria.setCastrado("Não");
                if (!isViewFemea()) {
                    sisUrinarioMamaria.setUltimoCio(ndnMasculino);
                    sisUrinarioMamaria.setPrenhez(ndnMasculino);
                    sisUrinarioMamaria.setPrenhezDescricao(ndnMasculino);
                    sisUrinarioMamaria.setUltimoParto(ndnMasculino);
                } else {
                    sisUrinarioMamaria.setUltimoCio(ndn);
                    sisUrinarioMamaria.setPrenhez("Não");
                    sisUrinarioMamaria.setPrenhezDescricao(ndn);
                    sisUrinarioMamaria.setUltimoParto(ndn);
                }
            } else {
                System.out.println("BACK-END WARNING: CONFIRMED [ public void ConfirmeSisUrinarioMamaria() ]");
            }
            prepareSisUrinarioMamaria(consultation);
            getDaoGenerico().save(getSisUrinarioMamaria());
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

    public RenderedFields getViewSisUrinarioMamaria() {
        if (getListViewFields(0).isViewVariableBoolean()) {
            sisUrinarioMamaria.setSistemaAfetado("Sim");
        } else {
            sisUrinarioMamaria = new SisUrinarioMamaria();
            sisUrinarioMamaria.setSistemaAfetado("Não");
            startIndexListViewFields();
        }
        return listViewFields.get(0);
    }

    public boolean isViewIngestHidricaEvolu() {
        return getListViewFields(1).isViewVariableBoolean();
    }

    public void methodViewIngestHidricaEvolu() {
        if (sisUrinarioMamaria.getIngestHidrica() != null) {
            if (!sisUrinarioMamaria.getIngestHidrica().equals("Normal")) {
                sisUrinarioMamaria.setIngestHidricaEvolu("");
                listViewFields.get(1).setViewVariableBoolean(true);
            } else {
                sisUrinarioMamaria.setIngestHidricaEvolu(ndn);
                listViewFields.get(1).setViewVariableBoolean(false);
            }
        }
    }

    public boolean isViewEstadoUrina() {
        return getListViewFields(2).isViewVariableBoolean();
    }

    public void methodViewEstadoUrina() {
        if (sisUrinarioMamaria.getEstadoUrina() != null) {
            if (sisUrinarioMamaria.getEstadoUrina().equals("Alterado")) {
                sisUrinarioMamaria.setUrina("");
                sisUrinarioMamaria.setUrinaAspecto("");
                sisUrinarioMamaria.setUrinaEvolu("");
                listViewFields.get(2).setViewVariableBoolean(true);
            } else {
                sisUrinarioMamaria.setUrina("N.D.N.");
                sisUrinarioMamaria.setUrinaAspecto(ndn);
                sisUrinarioMamaria.setUrinaEvolu(ndn);
                listViewFields.get(2).setViewVariableBoolean(false);
            }
        }
    }

    private boolean isViewFemea() {
        CollectionClasses obj = (CollectionClasses) getVariaveisDeSessao().getObjetoTemp();
        getListViewFields(3).setViewVariableBoolean(false);
        if (obj.getAnimal().getGenderAnimal().equals("F")) {
            listViewFields.get(3).setViewVariableBoolean(true);
        }
        return listViewFields.get(3).isViewVariableBoolean();
    }

    public boolean isViewUltimoCio() {
        if (!isViewFemea()) {
            sisUrinarioMamaria.setUltimoCio(ndnMasculino);
        }
        return isViewFemea();
    }

    public boolean isViewPrenhe() {
        if (!isViewFemea()) {
            sisUrinarioMamaria.setPrenhez(ndnMasculino);
            sisUrinarioMamaria.setPrenhezDescricao(ndnMasculino);
        }
        return isViewFemea();
    }

    public boolean isViewPrenheDescricao() {
        return getListViewFields(4).isViewVariableBoolean();
    }

    public void methodViewPrenheDescricao() {
        if (sisUrinarioMamaria.getPrenhez() != null) {
            if (sisUrinarioMamaria.getPrenhez().equals("Sim")) {
                sisUrinarioMamaria.setPrenhezDescricao("");
                listViewFields.get(4).setViewVariableBoolean(true);
            } else {
                sisUrinarioMamaria.setPrenhezDescricao(ndn);
                listViewFields.get(4).setViewVariableBoolean(false);
            }
        }
    }

    public boolean isViewUltimoParto() {
        if (!isViewFemea()) {
            sisUrinarioMamaria.setUltimoParto(ndnMasculino);
        }
        return isViewFemea();
    }

    public boolean isViewSecreVagiPeni() {
        return getListViewFields(5).isViewVariableBoolean();
    }

    public void methodViewSecreVagiPeni() {
        if (sisUrinarioMamaria.getSecreVagiPeni() != null) {
            if (sisUrinarioMamaria.getSecreVagiPeni().equals("Sim")) {
                sisUrinarioMamaria.setSecreVagiPeniTipo("");
                sisUrinarioMamaria.setSecreVagiPeniEvolu("");
                listViewFields.get(5).setViewVariableBoolean(true);
            } else {
                sisUrinarioMamaria.setSecreVagiPeniTipo(ndn);
                sisUrinarioMamaria.setSecreVagiPeniEvolu(ndn);
                listViewFields.get(5).setViewVariableBoolean(false);
            }
        }
    }
}
