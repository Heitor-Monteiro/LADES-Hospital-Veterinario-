/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consulta;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.RenderedFields;
import com.lades.sihv.model.Consulta;
import com.lades.sihv.model.ExameFisico;
import com.lades.sihv.model.ExameFisicoId;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class ControllerExameFisico extends AbstractBean {

    private ExameFisico exameFisico;
    private ExameFisicoId exameFisicoId;
    private final List<RenderedFields> listViewFields = new ArrayList();

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
            methodViewPalpaAbdom();
            methodViewAuscuCardiaca();
            methodViewAuscuPulmona();
            methodViewLinfonodos();
            methodViewPelePelos();
        }
        return exameFisico;
    }

    public void setExameFisico(ExameFisico exameFisico) {
        this.exameFisico = exameFisico;
    }

    public RenderedFields getListViewFields(int index) {
        if (listViewFields.isEmpty()) {
            listViewFields.add(index, new RenderedFields());
        } else if (listViewFields.size() < (index + 1)) {
            listViewFields.add(index, new RenderedFields());
        }
        return listViewFields.get(index);
    }

    public void methodViewPalpaAbdom() {
        if (getListViewFields(0).isViewVariableBoolean()) {
            exameFisico.setPalpaAbdom("");
        } else {
            exameFisico.setPalpaAbdom("Nada digno de nota.");
        }
    }

    public void methodViewAuscuCardiaca() {
        if (getListViewFields(1).isViewVariableBoolean()) {
            exameFisico.setAuscuCardiaca("");
        } else {
            exameFisico.setAuscuCardiaca("Nada digno de nota.");
        }
    }

    public void methodViewAuscuPulmona() {
        if (getListViewFields(2).isViewVariableBoolean()) {
            exameFisico.setAuscuPulmona("");
        } else {
            exameFisico.setAuscuPulmona("Nada digno de nota.");
        }
    }

    public void methodViewLinfonodos() {
        if (getListViewFields(3).isViewVariableBoolean()) {
            exameFisico.setLinfonodos("");
        } else {
            exameFisico.setLinfonodos("Nada digno de nota.");
        }
    }

    public void methodViewPelePelos() {
        if (getListViewFields(4).isViewVariableBoolean()) {
            exameFisico.setPelePelos("");
        } else {
            exameFisico.setPelePelos("Nada digno de nota.");
        }
    }
}
