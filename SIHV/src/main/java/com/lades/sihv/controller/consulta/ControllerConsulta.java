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

/**
 *
 * @author thiberius
 */
public class ControllerConsulta extends AbstractBean {

    private Consulta consulta;

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

    public Consulta getConsulta() {
        if (consulta == null) {
            consulta = new Consulta();
        }
        return consulta;
    }
}
