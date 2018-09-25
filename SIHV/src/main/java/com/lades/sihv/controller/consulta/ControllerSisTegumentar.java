/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consulta;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.RenderedFields;
import com.lades.sihv.controller.ListRenderedFields;
import com.lades.sihv.model.VetConsultation;
import com.lades.sihv.model.SisTegumentar;
import com.lades.sihv.model.SisTegumentarId;

/**
 *
 * @author thiberius
 */
public class ControllerSisTegumentar extends AbstractBean {

    private SisTegumentar sisTegumentar;
    private final ListRenderedFields listViewFields = new ListRenderedFields(4);
    private final String ndn = "Nada digno de nota.";

    private void prepareSisTegumentar(VetConsultation consultation) {
        SisTegumentarId sisTegumentarId = new SisTegumentarId();
        sisTegumentarId.setVetConsultationPkVetConsultation(consultation.getPkVetConsultation());
        sisTegumentar.setId(sisTegumentarId);
    }

    public void ConfirmeSisTegumentar(VetConsultation consultation) {
        try {
            if (sisTegumentar.getSistemaAfetado().equals("Não")) {
                System.out.println("BACK-END WARNING: N.D.N. [ public void ConfirmeSisTegumentar() ]");
                sisTegumentar.setEvoluLesao(ndn);
                sisTegumentar.setLocaliLesao(ndn);
                sisTegumentar.setPruridoCutaneo("Não");
                sisTegumentar.setPruridoCutaneoEvolu(ndn);
                sisTegumentar.setPruridoOtolog("Não");
                sisTegumentar.setPruridoOtologEvolu(ndn);
                sisTegumentar.setSecreOtolog("Não");
                sisTegumentar.setSecreOtologEvolu(ndn);
                sisTegumentar.setFrequeBanhos(ndn);
                sisTegumentar.setProduUtilBanho(ndn);
                sisTegumentar.setPreTratamen("Não");
                sisTegumentar.setPreTratamenDescri(ndn);
            } else {
                System.out.println("BACK-END WARNING: CONFIRMED [ public void ConfirmeSisTegumentar() ]");
            }
            prepareSisTegumentar(consultation);
            getDaoGenerico().save(getSisTegumentar());
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

    public RenderedFields getViewSisTegumentar() {
        if (listViewFields.getListViewFields(0).isViewVariableBoolean()) {
            sisTegumentar.setSistemaAfetado("Sim");
        } else {
            sisTegumentar = new SisTegumentar();
            sisTegumentar.setSistemaAfetado("Não");
            listViewFields.startIndexListViewFields();
        }
        return listViewFields.getListViewFields(0);
    }

    public boolean isViewPruridoCutaneoEvolu() {
        return listViewFields.getListViewFields(1).isViewVariableBoolean();
    }

    public void methodViewPruridoCutaneoEvolu() {
        if (sisTegumentar.getPruridoCutaneo() != null) {
            if (sisTegumentar.getPruridoCutaneo().equals("Sim")) {
                sisTegumentar.setPruridoCutaneoEvolu("");
                listViewFields.getListViewFields(1).setViewVariableBoolean(true);
            } else {
                sisTegumentar.setPruridoCutaneoEvolu(ndn);
                listViewFields.getListViewFields(1).setViewVariableBoolean(false);
            }
        }
    }

    public boolean isViewPruridoOtologEvolu() {
        return listViewFields.getListViewFields(2).isViewVariableBoolean();
    }

    public void methodViewPruridoOtologEvolu() {
        if (sisTegumentar.getPruridoOtolog() != null) {
            if (sisTegumentar.getPruridoOtolog().equals("Sim")) {
                sisTegumentar.setPruridoOtologEvolu("");
                listViewFields.getListViewFields(2).setViewVariableBoolean(true);
            } else {
                sisTegumentar.setPruridoOtologEvolu(ndn);
                listViewFields.getListViewFields(2).setViewVariableBoolean(false);
            }
        }
    }

    public boolean isViewSecreOtologEvolu() {
        return listViewFields.getListViewFields(3).isViewVariableBoolean();
    }

    public void methodViewSecreOtologEvolu() {
        if (sisTegumentar.getSecreOtolog() != null) {
            if (sisTegumentar.getSecreOtolog().equals("Sim")) {
                sisTegumentar.setSecreOtologEvolu("");
                listViewFields.getListViewFields(3).setViewVariableBoolean(true);
            } else {
                sisTegumentar.setSecreOtologEvolu(ndn);
                listViewFields.getListViewFields(3).setViewVariableBoolean(false);
            }
        }
    }

    public boolean isViewPreTratamenDescri() {
        return listViewFields.getListViewFields(4).isViewVariableBoolean();
    }

    public void methodViewPreTratamenDescri() {
        if (sisTegumentar.getPreTratamen() != null) {
            if (sisTegumentar.getPreTratamen().equals("Sim")) {
                sisTegumentar.setPreTratamenDescri("");
                listViewFields.getListViewFields(4).setViewVariableBoolean(true);
            } else {
                sisTegumentar.setPreTratamen("Não");
                sisTegumentar.setPreTratamenDescri(ndn);
                listViewFields.getListViewFields(4).setViewVariableBoolean(false);
            }
        }
    }
}
