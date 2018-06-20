/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consulta;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.BeautyText;
import com.lades.sihv.controller.RenderedFields;
import com.lades.sihv.model.Anamnese;
import com.lades.sihv.model.AnamneseId;
import com.lades.sihv.model.Consulta;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class ControllerAnamnese extends AbstractBean {

    private Anamnese anamnese;
    private AnamneseId anamneseId;
    private String vacinacao[] = null;
    private String qualEctoparasitas[];
    private final List<RenderedFields> listViewFields = new ArrayList();

    private void prepareAnamnese(Consulta consulta) {
        try {
            BeautyText Stringer = new BeautyText();
            anamneseId = new AnamneseId();
            anamneseId.setConsultaFkConsulta(consulta.getPkConsulta());
            anamnese.setId(anamneseId);
            anamnese.setVacinacao(Stringer.concatenaSTRING(vacinacao));
            anamnese.setQualEctoparasitas(Stringer.concatenaSTRING(qualEctoparasitas));
        } catch (Exception e) {
            System.out.println("BACK-END WARNING: "
                    + "ERRRO [public void prepareAnamnese()] "
                    + e.getMessage());
        }
    }

    public void ConfirmeAnamnese(Consulta consulta) {
        try {
            prepareAnamnese(consulta);
            getDaoGenerico().save(anamnese);
        } catch (Exception e) {
            System.out.println("BACK-END WARNING: ERROR [ public void ConfirmeAnamnese() ]"
                    + e.getMessage());
        }
    }

    public Anamnese getAnamnese() {
        if (anamnese == null) {
            anamnese = new Anamnese();
            anamnese.setJaFoiTratado("Não");
            methodViewAntecMorbido();
            methodViewHistoFamiliar();
            anamnese.setAlimentacaoCaseira("Não");
//            anamnese.setAlimentacaoRacao("Não");
            anamnese.setVermifugacao("Não");
            anamnese.setEctoparasitas("Não");
            anamnese.setContactantes("Não");
            anamnese.setContatoRoedor("Não");
        }
        return anamnese;
    }

    public void setAnamnese(Anamnese anamnese) {
        this.anamnese = anamnese;
    }

    public String[] getVacinacao() {
        return vacinacao;
    }

    public void setVacinacao(String[] vacinacao) {
        this.vacinacao = vacinacao;
    }

    public boolean getViewVacinacaoOutro() {
        boolean var = false;
        if (vacinacao != null) {
            for (String obj : vacinacao) {
                var = obj.equals("Outras");
            }
        }
        return var;
    }

    public String[] getQualEctoparasitas() {
        if (anamnese.getEctoparasitas().equals("Não")) {
            qualEctoparasitas = null;
        }
        return qualEctoparasitas;
    }

    public void setQualEctoparasitas(String[] qualEctoparasitas) {
        this.qualEctoparasitas = qualEctoparasitas;
    }

    public RenderedFields getListViewFields(int index) {
        if (listViewFields.isEmpty()) {
            listViewFields.add(index, new RenderedFields());
        } else if ((listViewFields.size() - index) == 0) {
            listViewFields.add(index, new RenderedFields());
        }
        return listViewFields.get(index);
    }

    public void methodViewAntecMorbido() {
        if (getListViewFields(0).isViewVariableBoolean()) {
            anamnese.setAntecMorbido("");
        } else {
            anamnese.setAntecMorbido("Nada digno de nota.");
        }
    }

    public void methodViewHistoFamiliar() {
        if (getListViewFields(1).isViewVariableBoolean()) {
            anamnese.setHistoFamiliar("");
        } else {
            anamnese.setHistoFamiliar("Nada digno de nota.");
        }
    }

    public boolean isViewQualProdutoUtiliza(int index) {
        if (anamnese.getEctoparasitas().equals("Sim")
                && anamnese.getControEctoparasitas() != null) {
            getListViewFields(index).setViewVariableBoolean(
                    anamnese.getControEctoparasitas().equals("Sim"));
        } else {
            getListViewFields(index).setViewVariableBoolean(false);
            anamnese.setControEctoparasitas("Não");
        }
        return getListViewFields(index).isViewVariableBoolean();
    }
}
