/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consulta;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.model.Consulta;
import com.lades.sihv.model.ExameImage;
import com.lades.sihv.model.ExameImageId;
import java.util.Date;

/**
 *
 * @author thiberius
 */
public class ControllerExameImagem extends AbstractBean {
    private ExameImage exameImageRaioX;
    private ExameImageId exameImageRaioXId;

    private ExameImage exameImageUltra;
    private ExameImageId exameImageUltraId;
    
    //------Métodos para ExameImageRaioX-------------------------------
    public void prepareExameImageRaioX(Consulta consulta ,Date date, String codRaioX) {
        exameImageRaioXId = new ExameImageId();
        exameImageRaioXId.setConsultaFkConsulta(consulta.getPkConsulta());
        exameImageRaioX.setId(exameImageRaioXId);
        exameImageRaioX.setTipo("RAIOX");
        exameImageRaioX.setDataExaImage(date);
        exameImageRaioX.setStatus("Pendente");
        exameImageRaioX.setCodExameImage(codRaioX);
    }

    public ExameImage getExameImageRaioX() {
        if (exameImageRaioX == null) {
            exameImageRaioX = new ExameImage();
        }
        return exameImageRaioX;
    }

    public void setExameImageRaioX(ExameImage exameImageRaioX) {
        this.exameImageRaioX = exameImageRaioX;
    }
    //-----------------------------------------------------------------

    //------Métodos para ExameImageUltra-------------------------------
    public void prepareExameImageUltra(Consulta consulta, Date date, String codUltrasson) {
        exameImageUltraId = new ExameImageId();
        exameImageUltraId.setConsultaFkConsulta(consulta.getPkConsulta());
        exameImageUltra.setId(exameImageUltraId);
        exameImageUltra.setTipo("ULTRASSOM");
        exameImageUltra.setDataExaImage(date);
        exameImageUltra.setStatus("Pendente");
        exameImageUltra.setCodExameImage(codUltrasson);
    }

    public ExameImage getExameImageUltra() {
        if (exameImageUltra == null) {
            exameImageUltra = new ExameImage();
        }
        return exameImageUltra;
    }

    public void setExameImageUltra(ExameImage exameImageUltra) {
        this.exameImageUltra = exameImageUltra;
    }
    //-----------------------------------------------------------------
}
