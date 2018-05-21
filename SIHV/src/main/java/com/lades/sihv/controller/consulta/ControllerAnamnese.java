/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consulta;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.BeautyText;
import com.lades.sihv.model.Anamnese;
import com.lades.sihv.model.AnamneseId;
import com.lades.sihv.model.Consulta;

/**
 *
 * @author thiberius
 */
public class ControllerAnamnese extends AbstractBean {

    private Anamnese anamnese;
    private AnamneseId anamneseId;
    private String vacinacao[] = null;
    private String qualEctoparasitas[];
    private boolean ViewAntecMorbido;
    private boolean ViewHistoFamiliar;
    private boolean ViewQualProdutoUtiliza;

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
        return AnamneseArray(vacinacao, "Outras");
    }

    private boolean AnamneseArray(String list[], String texto) {
        boolean var = false;
        if (list != null) {
            for (String obj : list) {
                var = obj.equals(texto);
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

    public boolean isViewAntecMorbido() {
        if (ViewAntecMorbido) {
            anamnese.setAntecMorbido("");
        } else {
            anamnese.setAntecMorbido("Nada digno de nota.");
        }
        return ViewAntecMorbido;
    }

    public void setViewAntecMorbido(boolean ViewAntecMorbido) {
        this.ViewAntecMorbido = ViewAntecMorbido;
    }

    public boolean isViewHistoFamiliar() {
        if (ViewHistoFamiliar) {
            anamnese.setHistoFamiliar("");
        } else {
            anamnese.setHistoFamiliar("Nada digno de nota.");
        }
        return ViewHistoFamiliar;
    }

    public void setViewHistoFamiliar(boolean ViewHistoFamiliar) {
        this.ViewHistoFamiliar = ViewHistoFamiliar;
    }

    public boolean isViewQualProdutoUtiliza() {
        if (anamnese.getEctoparasitas().equals("Sim")
                && anamnese.getControEctoparasitas() != null) {
            ViewQualProdutoUtiliza = anamnese.getControEctoparasitas().equals("Sim");
        } else {
            ViewQualProdutoUtiliza = false;
            anamnese.setControEctoparasitas("Não");
        }
        return ViewQualProdutoUtiliza;
    }

}
