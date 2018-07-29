/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consulta;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.RenderedFields;
import com.lades.sihv.controller.ListRenderedFields;
import com.lades.sihv.model.Consulta;
import com.lades.sihv.model.ExameImage;
import com.lades.sihv.model.ExameImageId;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class ControllerExaImage extends AbstractBean {

    private ExameImage examImageXray;
    private ExameImage examImageUltrasound;
    private ListRenderedFields objListRendFieldsXray;
    private ListRenderedFields objListRendFieldsUltra;
    private String codRaioX;
    private String codUltrasson;
    private int numCodImage = 0;
    private final String ndn = "Nada digno de nota.";

    private void ConfirmeExamImage(Consulta consulta,
            String typeExamImage,
            ExameImage examImage,
            ListRenderedFields list,
            String codExamImage) {
        try {
            if (list.getListViewFields(0).isViewVariableBoolean()) {
                System.out.println("BACK-END WARNING: CONFIRMED [ public void ConfirmeExam" + typeExamImage + "() ]");
                ExameImageId examImageID = new ExameImageId();
                examImageID.setConsultaFkConsulta(consulta.getPkConsulta());
                examImage.setId(examImageID);
                examImage.setTipo(typeExamImage);
                examImage.setStatusExamImage("Pendente");
                examImage.setSolicitacaoData(getObjData());
                examImage.setExamImageCod(codExamImage);
                getDaoGenerico().save(examImage);
            }
        } catch (Exception e) {
            System.out.println("BACK-END WARNING: ERROR [ public void ConfirmeExam" + typeExamImage + "() ]"
                    + e.getMessage());
        }
    }

    public void ConfirmeExamXray(Consulta consulta) {
        ConfirmeExamImage(consulta, "RaioX", examImageXray, objListRendFieldsXray, codRaioX);
    }

    public void ConfirmeExamUltrasound(Consulta consulta) {
        ConfirmeExamImage(consulta, "Ultrassom", examImageUltrasound, objListRendFieldsUltra, codUltrasson);
    }

    /*O método é utilizar para saber o
    maior código de um exame por imagem.*/
    public void maxExameImagem() {
        numCodImage = 0;
        if (numCodImage == 0) {
            List<?> list;
            list = getDaoGenerico().list("select e.id.pkExameImage from ExameImage e where e.id.pkExameImage=1");
            if (list.size() > 0) {
                numCodImage = (int) getDaoGenerico().list("select max(e.id.pkExameImage) from ExameImage e").get(0);
            }
        }
    }

    /*O método é utilizar para gera o código de
    um exame por imagem no formato AnoNumero.*/
    private void gerarCodExameImagem(boolean confirmeRAIOX, boolean confirmeUltrasson) {
        int num1;
        int num2;
        if (confirmeRAIOX == true && confirmeUltrasson == true) {
            // Ambos exames por imagem
            num1 = numCodImage + 1;
            num2 = num1 + 1;
            codRaioX = "" + Calendar.getInstance().get(Calendar.YEAR) + "-" + num1;
            codUltrasson = "" + Calendar.getInstance().get(Calendar.YEAR) + "-" + num2;
        } else if (confirmeRAIOX == true && confirmeUltrasson == false) {
            //Para raio x
            num1 = numCodImage + 1;
            codRaioX = "" + Calendar.getInstance().get(Calendar.YEAR) + "-" + num1;
            codUltrasson = "";
        } else {
            //Para ultrassom
            num2 = numCodImage + 1;
            codUltrasson = "" + Calendar.getInstance().get(Calendar.YEAR) + "-" + num2;
            codRaioX = "";
        }
    }

//  GETs & SETs
    public RenderedFields getViewExamXray() {
        if (getObjListRendFieldsXray().getListViewFields(0).isViewVariableBoolean()) {
            maxExameImagem();
            gerarCodExameImagem(true, getObjListRendFieldsUltra().getListViewFields(0).isViewVariableBoolean());
        } else {
            examImageXray = new ExameImage();
            getObjListRendFieldsXray().startIndexListViewFields();
        }
        return getObjListRendFieldsXray().getListViewFields(0);
    }

    public RenderedFields getViewExamUltrasound() {
        if (getObjListRendFieldsUltra().getListViewFields(0).isViewVariableBoolean()) {
            maxExameImagem();
            gerarCodExameImagem(getObjListRendFieldsXray().getListViewFields(0).isViewVariableBoolean(), true);
        } else {
            examImageUltrasound = new ExameImage();
            getObjListRendFieldsUltra().startIndexListViewFields();
        }
        return getObjListRendFieldsUltra().getListViewFields(0);
    }

    public ExameImage getExamImageXray() {
        if (examImageXray == null) {
            examImageXray = new ExameImage();
        }
        return examImageXray;
    }

    public void setExamImageXray(ExameImage examImageXray) {
        this.examImageXray = examImageXray;
    }

    public ExameImage getExamImageUltrasound() {
        if (examImageUltrasound == null) {
            examImageUltrasound = new ExameImage();
        }
        return examImageUltrasound;
    }

    public void setExamImageUltrasound(ExameImage examImageUltrasound) {
        this.examImageUltrasound = examImageUltrasound;
    }

    public ListRenderedFields getObjListRendFieldsXray() {
        if (objListRendFieldsXray == null) {
            objListRendFieldsXray = new ListRenderedFields(5);
        }
        return objListRendFieldsXray;
    }

    public ListRenderedFields getObjListRendFieldsUltra() {
        if (objListRendFieldsUltra == null) {
            objListRendFieldsUltra = new ListRenderedFields(5);
        }
        return objListRendFieldsUltra;
    }

    public String getCodRaioX() {
        return codRaioX;
    }

    public String getCodUltrasson() {
        return codUltrasson;
    }
}
