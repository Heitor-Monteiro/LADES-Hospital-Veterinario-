/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consulta;

import java.util.List;
import com.lades.sihv.bean.AbstractBean;
import java.util.Calendar;

/**
 *
 * @author thiberius
 */
public class CodExameImagem extends AbstractBean {
    
    /*O método é utilizar para saber o
    maior código de um exame por imagem.*/
    public int maxExameImagem() {
        int numCodImage = 0;
        List<?> list;
        list = getDaoGenerico().list("select e.id.pkExameImage from ExameImage e where e.id.pkExameImage=1");
        if (list.size() > 0) {
            numCodImage = (int) getDaoGenerico().list("select max(e.id.pkExameImage) from ExameImage e").get(0);
        }
        return numCodImage;
    }
    
    
    /*O método é utilizar para gera o código de
    um exame por imagem no formato AnoNumero.*/
    public void gerarCodExameImagem(boolean confirmeRAIOX, boolean confirmeUltrasson, 
            int numCodImage, String codRaioX, String codUltrasson) {
        int num1;
        int num2;
        if (confirmeRAIOX == true && confirmeUltrasson == true) {
            // Ambos exames por imagem
            num1 = numCodImage + 1;
            num2 = num1 + 1;
            codRaioX = "" + Calendar.getInstance().get(Calendar.YEAR) + num1;
            codUltrasson = "" + Calendar.getInstance().get(Calendar.YEAR) + num2;
        } else if (confirmeRAIOX == true && confirmeUltrasson == false) {
            //Para raio x
            num1 = numCodImage + 1;
            codRaioX = "" + Calendar.getInstance().get(Calendar.YEAR) + num1;
            codUltrasson = "";
        } else {
            //Para ultrassom
            num2 = numCodImage + 1;
            codUltrasson = "" + Calendar.getInstance().get(Calendar.YEAR) + num2;
            codRaioX = "";
        }
    }
}
