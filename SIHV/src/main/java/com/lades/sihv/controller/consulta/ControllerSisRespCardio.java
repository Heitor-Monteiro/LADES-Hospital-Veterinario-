/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consulta;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.RenderedFields;
import com.lades.sihv.model.Consulta;
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

    private void prepareSisRespCardio(Consulta consulta) {
        sisRespCardioId = new SisRespCardioId();
        sisRespCardioId.setConsultaFkConsulta(consulta.getPkConsulta());
        sisRespCardio.setId(sisRespCardioId);
    }

    public void ConfirmeSisRespCardio(Consulta consulta) {
        try {
            if (sisRespCardio.getSistemaAfetado().equals("Sim")) {
                System.out.println("BACK-END WARNING: CONFIRMED [ public void ConfirmeSisRespCardio() ]");
                prepareSisRespCardio(consulta);
                getDaoGenerico().save(getSisRespCardio());
            } else {
                System.out.println("BACK-END WARNING: NOT CONFIRMED [ public void ConfirmeSisRespCardio() ]");
            }
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
        } else if (listViewFields.size() < (index + 1)) {
            listViewFields.add(index, new RenderedFields());
        }
        return listViewFields.get(index);
    }

    public RenderedFields getViewSisRespCardio() {
        if (getListViewFields(0).isViewVariableBoolean()) {
            sisRespCardio.setSistemaAfetado("Sim");
        } else {
            sisRespCardio = new SisRespCardio();
            sisRespCardio.setSistemaAfetado("Não");
            for (RenderedFields listViewField : listViewFields) {
                listViewField.setViewVariableBoolean(false);
            }
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
                sisRespCardio.setTosseEvolu("Nada digno de nota.");
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
                sisRespCardio.setEspirroEvolu("Nada digno de nota.");
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
                sisRespCardio.setSecreNasalUniBilateral("");
                sisRespCardio.setSecreNasalTipo("Nada digno de nota.");
                sisRespCardio.setSecreNasalEvolu("Nada digno de nota.");
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
                sisRespCardio.setDispnTaquipEvolu("Nada digno de nota.");
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
                sisRespCardio.setCianoseEvolu("Nada digno de nota.");
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
                sisRespCardio.setCansaFacilEvolu("Nada digno de nota.");
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
                sisRespCardio.setSincopeEvolu("Nada digno de nota.");
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
                sisRespCardio.setEmagrecEvolu("Nada digno de nota.");
                listViewFields.get(8).setViewVariableBoolean(false);
            }
        }
    }
}
