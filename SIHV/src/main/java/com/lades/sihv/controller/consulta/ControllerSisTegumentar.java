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
    private final String ndn = "Nada digno de nota.";

    private void prepareSisTegumentar(Consulta consulta) {
        sisTegumentarId = new SisTegumentarId();
        sisTegumentarId.setConsultaFkConsulta(consulta.getPkConsulta());
        sisTegumentar.setId(sisTegumentarId);
    }

    public void ConfirmeSisTegumentar(Consulta consulta) {
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
            } else {
                System.out.println("BACK-END WARNING: CONFIRMED [ public void ConfirmeSisTegumentar() ]");
            }
            prepareSisTegumentar(consulta);
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

    public RenderedFields getViewSisTegumentar() {
        if (getListViewFields(0).isViewVariableBoolean()) {
            sisTegumentar.setSistemaAfetado("Sim");
        } else {
            sisTegumentar = new SisTegumentar();
            sisTegumentar.setSistemaAfetado("Não");
            startIndexListViewFields();
        }
        return listViewFields.get(0);
    }

    public boolean isViewPruridoCutaneoEvolu() {
        return getListViewFields(1).isViewVariableBoolean();
    }

    public void methodViewPruridoCutaneoEvolu() {
        if (sisTegumentar.getPruridoCutaneo() != null) {
            if (sisTegumentar.getPruridoCutaneo().equals("Sim")) {
                sisTegumentar.setPruridoCutaneoEvolu("");
                listViewFields.get(1).setViewVariableBoolean(true);
            } else {
                sisTegumentar.setPruridoCutaneoEvolu(ndn);
                listViewFields.get(1).setViewVariableBoolean(false);
            }
        }
    }

    public boolean isViewPruridoOtologEvolu() {
        return getListViewFields(2).isViewVariableBoolean();
    }

    public void methodViewPruridoOtologEvolu() {
        if (sisTegumentar.getPruridoOtolog() != null) {
            if (sisTegumentar.getPruridoOtolog().equals("Sim")) {
                sisTegumentar.setPruridoOtologEvolu("");
                listViewFields.get(2).setViewVariableBoolean(true);
            } else {
                sisTegumentar.setPruridoOtologEvolu(ndn);
                listViewFields.get(2).setViewVariableBoolean(false);
            }
        }
    }

    public boolean isViewSecreOtologEvolu() {
        return getListViewFields(3).isViewVariableBoolean();
    }

    public void methodViewSecreOtologEvolu() {
        if (sisTegumentar.getSecreOtolog() != null) {
            if (sisTegumentar.getSecreOtolog().equals("Sim")) {
                sisTegumentar.setSecreOtologEvolu("");
                listViewFields.get(3).setViewVariableBoolean(true);
            } else {
                sisTegumentar.setSecreOtologEvolu(ndn);
                listViewFields.get(3).setViewVariableBoolean(false);
            }
        }
    }
}
