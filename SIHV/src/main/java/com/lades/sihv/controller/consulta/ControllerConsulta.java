/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consulta;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.model.Animais;
import com.lades.sihv.model.Consulta;
import com.lades.sihv.model.User;
import com.lades.sihv.controller.ListRenderedFields;
import com.lades.sihv.controller.RenderedFields;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class ControllerConsulta extends AbstractBean {

    private Consulta consulta;
    private int codNewConsulta = 0;
    private final ListRenderedFields listViewFields = new ListRenderedFields(3);

    private void prepareConsulta(Animais animal, User medicoVET) {
        getConsulta().setDataConsulta(getObjData());
//          consulta.setSistemasAfetados(sistemasAfetados());
        consulta.setAnimais(animal);
        consulta.setUser(medicoVET);
    }

    public Consulta ConfirmeConsulta(Animais animal, User medicoVET) {
        try {
            prepareConsulta(animal, medicoVET);
            getDaoGenerico().save(consulta);
        } catch (Exception e) {
            System.out.println("BACK-END WARNING: ERROR [ public Consulta ConfirmeConsulta() ] "
                    + e.getMessage());
        }
        return consulta;
    }

    /*O método é utilizado para saber qual
    será o próximo código de uma nova consulta.*/
    public void generateMaxExamCode() {
        List<?> list;
        list = getDaoGenerico().list("select c.pkConsulta from Consulta c where c.pkConsulta=1");
        if (list.size() > 0) {
            codNewConsulta = (int) getDaoGenerico().list("select max(c.pkConsulta) from Consulta c").get(0);
            codNewConsulta++;
        } else {
            codNewConsulta = 1;
        }
    }

    private String methodDiagnosis(int index, String typeDiagnosis) {
        if (listViewFields.getListViewFields(index).isViewVariableBoolean()) {
            typeDiagnosis = "Diagnósticos " + typeDiagnosis + " inconclusivo.";
        } else {
            typeDiagnosis = "";
        }
        return typeDiagnosis;
    }

    public RenderedFields getStatusDiagPresuntivo() {
        return listViewFields.getListViewFields(0);
    }

    public void methodDiagPresuntivo() {
        getConsulta().setDiagPresuntivo(methodDiagnosis(0, "presuntivo"));
    }

    public RenderedFields getStatusDiagDiferencial() {
        return listViewFields.getListViewFields(1);
    }

    public void methodDiagDiferencial() {
        getConsulta().setDiagDiferencial(methodDiagnosis(1, "diferencial"));
    }

    public RenderedFields getStatusDiagDefinitivo() {
        return listViewFields.getListViewFields(2);
    }

    public void methodDiagDefinitivo() {
        getConsulta().setDiagDefinitivo(methodDiagnosis(2, "definitivo"));
    }

    //GETs & SETs
    public Consulta getConsulta() {
        if (consulta == null) {
            consulta = new Consulta();
        }
        return consulta;
    }

    public int getCodNewConsulta() {
        return codNewConsulta;
    }
}
