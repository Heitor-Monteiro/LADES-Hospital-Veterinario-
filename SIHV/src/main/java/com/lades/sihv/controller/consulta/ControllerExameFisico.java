/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consulta;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.model.Consulta;
import com.lades.sihv.model.ExameFisico;
import com.lades.sihv.model.ExameFisicoId;

/**
 *
 * @author thiberius
 */
public class ControllerExameFisico extends AbstractBean {

    private ExameFisico exameFisico;
    private ExameFisicoId exameFisicoId;
    private boolean viewPalpaAbdom;
    private boolean viewAuscuCardiaca;
    private boolean viewAuscuPulmona;
    private boolean viewLinfonodos;
    private boolean viewPelePelos;

    private void prepareExameFisico(Consulta consulta) {
        exameFisicoId = new ExameFisicoId();
        exameFisicoId.setConsultaFkConsulta(consulta.getPkConsulta());
        exameFisico.setId(exameFisicoId);
    }

    public void ConfirmeExameFisico(Consulta consulta) {
        try {
            prepareExameFisico(consulta);
            getDaoGenerico().save(exameFisico);
        } catch (Exception e) {
            System.out.println("BACK-END WARNING: ERROR [ public void ConfirmeExameFisico() ]"
                    + e.getMessage());
        }
    }

    public boolean getViewEstadoPulso() {
        boolean var = false;
        if (null == exameFisico.getPulso()) {
        } else {
            switch (exameFisico.getPulso()) {
                case "Normocinético":
                case "Hipocinético":
                case "Hipercinético":
                    var = true;
                    break;
                case "Ausente":
                    exameFisico.setEstadoPulso("Ausente");
                    break;
                default:
                    break;
            }
        }
        return var;
    }

    public ExameFisico getExameFisico() {
        if (exameFisico == null) {
            exameFisico = new ExameFisico();
            String ndn = "Nada digno de nota.";
            exameFisico.setPalpaAbdom(ndn);
            exameFisico.setAuscuCardiaca(ndn);
            exameFisico.setAuscuPulmona(ndn);
            exameFisico.setLinfonodos(ndn);
            exameFisico.setPelePelos(ndn);
        }
        return exameFisico;
    }

    public void setExameFisico(ExameFisico exameFisico) {
        this.exameFisico = exameFisico;
    }

    public boolean isViewPalpaAbdom() {
        if (viewPalpaAbdom) {
            exameFisico.setPalpaAbdom("");
        } else {
            exameFisico.setPalpaAbdom("Nada digno de nota.");
        }
        return viewPalpaAbdom;
    }

    public void setViewPalpaAbdom(boolean viewPalpaAbdom) {
        this.viewPalpaAbdom = viewPalpaAbdom;
    }

    public boolean isViewAuscuCardiaca() {
        if (viewAuscuCardiaca) {
            exameFisico.setAuscuCardiaca("");
        } else {
            exameFisico.setAuscuCardiaca("Nada digno de nota.");
        }
        return viewAuscuCardiaca;
    }

    public void setViewAuscuCardiaca(boolean viewAuscuCardiaca) {
        this.viewAuscuCardiaca = viewAuscuCardiaca;
    }

    public boolean isViewAuscuPulmona() {
        if (viewAuscuPulmona) {
            exameFisico.setAuscuPulmona("");
        } else {
            exameFisico.setAuscuPulmona("Nada digno de nota.");
        }
        return viewAuscuPulmona;
    }

    public void setViewAuscuPulmona(boolean viewAuscuPulmona) {
        this.viewAuscuPulmona = viewAuscuPulmona;
    }

    public boolean isViewLinfonodos() {
        if (viewLinfonodos) {
            exameFisico.setLinfonodos("");
        } else {
            exameFisico.setLinfonodos("Nada digno de nota.");
        }
        return viewLinfonodos;
    }

    public void setViewLinfonodos(boolean viewLinfonodos) {
        this.viewLinfonodos = viewLinfonodos;
    }

    public boolean isViewPelePelos() {
        if (viewPelePelos) {
            exameFisico.setPelePelos("");
        } else {
            exameFisico.setPelePelos("Nada digno de nota.");
        }
        return viewPelePelos;
    }

    public void setViewPelePelos(boolean viewPelePelos) {
        this.viewPelePelos = viewPelePelos;
    }
}
