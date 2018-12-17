/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.NewConsultation;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.RenderedFields;
import com.lades.sihv.model.VetConsultation;
import com.lades.sihv.model.SisRespCardio;
import com.lades.sihv.model.SisRespCardioId;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class ControllerSisRespCardio extends AbstractBean {

    private SisRespCardio sisRespCardio;
    private SisRespCardioId sisRespCardioId;
    private final List<RenderedFields> listViewFields = new ArrayList();
    private final String ndn = "Nada digno de nota.";

    private void prepareSisRespCardio(VetConsultation consultation) {
        sisRespCardioId = new SisRespCardioId();
        sisRespCardioId.setVetConsultationPkVetConsultation(consultation.getPkVetConsultation());
        sisRespCardio.setId(sisRespCardioId);
    }

    public void ConfirmeSisRespCardio(VetConsultation consultation) {
        try {
            if (sisRespCardio.getSistemaAfetado().equals("Não")) {
                System.out.println("BACK-END WARNING: N.D.N [ public void ConfirmeSisRespCardio() ]");
                sisRespCardio.setTosse("Não");
                sisRespCardio.setTosseProdutiva("N.D.N.");
                sisRespCardio.setTosseEvolu(ndn);
                sisRespCardio.setEspirro("Não");
                sisRespCardio.setEspirroEvolu(ndn);
                sisRespCardio.setSecrecaoNasal("Não");
                sisRespCardio.setSecreNasalUniBilateral("N.D.N.");
                sisRespCardio.setSecreNasalTipo(ndn);
                sisRespCardio.setSecreNasalEvolu(ndn);
                sisRespCardio.setDispneiaTaquipneia("Não");
                sisRespCardio.setDispnTaquipEvolu(ndn);
                sisRespCardio.setCianose("Não");
                sisRespCardio.setCianoseEvolu(ndn);
                sisRespCardio.setCansacoFacil("Não");
                sisRespCardio.setCansaFacilEvolu(ndn);
                sisRespCardio.setSincope("Não");
                sisRespCardio.setSincopeEvolu(ndn);
                sisRespCardio.setEmagrecimento("Não");
                sisRespCardio.setEmagrecEvolu(ndn);
            } else {
                System.out.println("BACK-END WARNING: CONFIRMED [ public void ConfirmeSisRespCardio() ]");
            }
            prepareSisRespCardio(consultation);
            getDaoGenerico().save(getSisRespCardio());
        } catch (Exception e) {
            System.out.println("BACK-END WARNING: ERROR [ public void ConfirmeSisRespCardio() ]"
                    + e.getMessage());
        }
    }

//  GETs & SETs
    public SisRespCardio getSisRespCardio() {
        if (sisRespCardio == null) {
            sisRespCardio = new SisRespCardio();
            sisRespCardio.setSistemaAfetado("Não");
        }
        return sisRespCardio;
    }

    public void setSisRespCardio(SisRespCardio sisRespCardio) {
        this.sisRespCardio = sisRespCardio;
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
        for (int index = 0; index <= 8; index++) {
            listViewFields.add(index, new RenderedFields());
            listViewFields.get(index).setViewVariableBoolean(false);
        }
    }

    public RenderedFields getViewSisRespCardio() {
        if (getListViewFields(0).isViewVariableBoolean()) {
            sisRespCardio.setSistemaAfetado("Sim");
        } else {
            sisRespCardio = new SisRespCardio();
            sisRespCardio.setSistemaAfetado("Não");
            startIndexListViewFields();
        }
        return listViewFields.get(0);
    }

    public boolean isViewProdutEvoluTosse() {
        return getListViewFields(1).isViewVariableBoolean();
    }

    public void methodViewProdutEvoluTosse() {
        if (sisRespCardio.getTosse() != null) {
            if (sisRespCardio.getTosse().equals("Sim")) {
                sisRespCardio.setTosseEvolu("");
                listViewFields.get(1).setViewVariableBoolean(true);
            } else {
                sisRespCardio.setTosseProdutiva("N.D.N.");
                sisRespCardio.setTosseEvolu(ndn);
                listViewFields.get(1).setViewVariableBoolean(false);
            }
        }
    }

    public boolean isViewEspirroEvolu() {
        return getListViewFields(2).isViewVariableBoolean();
    }

    public void methodViewEspirroEvolu() {
        if (sisRespCardio.getEspirro() != null) {
            if (sisRespCardio.getEspirro().equals("Sim")) {
                sisRespCardio.setEspirroEvolu("");
                listViewFields.get(2).setViewVariableBoolean(true);
            } else {
                sisRespCardio.setEspirroEvolu(ndn);
                listViewFields.get(2).setViewVariableBoolean(false);
            }
        }
    }

    public boolean isViewSecreNasal() {
        return getListViewFields(3).isViewVariableBoolean();
    }

    public void methodViewSecreNasal() {
        if (sisRespCardio.getSecrecaoNasal() != null) {
            if (sisRespCardio.getSecrecaoNasal().equals("Sim")) {
                sisRespCardio.setSecreNasalUniBilateral("");
                sisRespCardio.setSecreNasalTipo("");
                sisRespCardio.setSecreNasalEvolu("");
                listViewFields.get(3).setViewVariableBoolean(true);
            } else {
                sisRespCardio.setSecreNasalUniBilateral("N.D.N.");
                sisRespCardio.setSecreNasalTipo(ndn);
                sisRespCardio.setSecreNasalEvolu(ndn);
                listViewFields.get(3).setViewVariableBoolean(false);
            }
        }
    }

    public boolean isViewDispTaquip() {
        return getListViewFields(4).isViewVariableBoolean();
    }

    public void methodViewDispTaquip() {
        if (sisRespCardio.getDispneiaTaquipneia() != null) {
            if (sisRespCardio.getDispneiaTaquipneia().equals("Sim")) {
                sisRespCardio.setDispnTaquipEvolu("");
                listViewFields.get(4).setViewVariableBoolean(true);
            } else {
                sisRespCardio.setDispnTaquipEvolu(ndn);
                listViewFields.get(4).setViewVariableBoolean(false);
            }
        }
    }

    public boolean isViewCianoseEvolu() {
        return getListViewFields(5).isViewVariableBoolean();
    }

    public void methodViewCianoseEvolu() {
        if (sisRespCardio.getCianose() != null) {
            if (sisRespCardio.getCianose().equals("Sim")) {
                sisRespCardio.setCianoseEvolu("");
                listViewFields.get(5).setViewVariableBoolean(true);
            } else {
                sisRespCardio.setCianoseEvolu(ndn);
                listViewFields.get(5).setViewVariableBoolean(false);
            }
        }
    }

    public boolean isViewCansaFacilEvolu() {
        return getListViewFields(6).isViewVariableBoolean();
    }

    public void methodViewCansaFacilEvolu() {
        if (sisRespCardio.getCansacoFacil() != null) {
            if (sisRespCardio.getCansacoFacil().equals("Sim")) {
                sisRespCardio.setCansaFacilEvolu("");
                listViewFields.get(6).setViewVariableBoolean(true);
            } else {
                sisRespCardio.setCansaFacilEvolu(ndn);
                listViewFields.get(6).setViewVariableBoolean(false);
            }
        }
    }

    public boolean isViewSincopeEvolu() {
        return getListViewFields(7).isViewVariableBoolean();
    }

    public void methodViewSincopeEvolu() {
        if (sisRespCardio.getSincope() != null) {
            if (sisRespCardio.getSincope().equals("Sim")) {
                sisRespCardio.setSincopeEvolu("");
                listViewFields.get(7).setViewVariableBoolean(true);
            } else {
                sisRespCardio.setSincopeEvolu(ndn);
                listViewFields.get(7).setViewVariableBoolean(false);
            }
        }
    }

    public boolean isViewEmagrecEvolu() {
        return getListViewFields(8).isViewVariableBoolean();
    }

    public void methodViewEmagrecEvolu() {
        if (sisRespCardio.getEmagrecimento() != null) {
            if (sisRespCardio.getEmagrecimento().equals("Sim")) {
                sisRespCardio.setEmagrecEvolu("");
                listViewFields.get(8).setViewVariableBoolean(true);
            } else {
                sisRespCardio.setEmagrecEvolu(ndn);
                listViewFields.get(8).setViewVariableBoolean(false);
            }
        }
    }
}
