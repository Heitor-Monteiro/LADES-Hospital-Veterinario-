/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consulta;

import com.lades.sihv.bean.AbstractBean;

import java.util.List;

/**
 *
 * @author thiberius
 */
public class MaxCodigoConsulta extends AbstractBean {
    
    /*O método é utilizado para saber qual
    será o próximo código de uma nova consulta.*/
    public int maxConsultaCod() {
        int maxCodConsulta;
        List<?> list;
        list = getDaoGenerico().list("select c.pkConsulta from Consulta c where c.pkConsulta=1");
        if (list.size() > 0) {
            maxCodConsulta = (int) getDaoGenerico().list("select max(c.pkConsulta) from Consulta c").get(0);
            maxCodConsulta++;
        } else {
            maxCodConsulta = 1;
        }
        return maxCodConsulta;
    }
}
