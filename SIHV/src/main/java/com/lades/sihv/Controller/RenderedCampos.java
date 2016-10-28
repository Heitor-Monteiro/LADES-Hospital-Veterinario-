/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.Controller;

import java.io.Serializable;

/**
 *
 * @author thiberius
 */

/*Uma instancia dessa classe serve para
gerenciar um item na tela que precise de rendered*/
public class RenderedCampos implements Serializable{
    private String campoTEMP;
    private boolean varRenderedCAMPO;

    
    
    private RenderedCampos(){}
    
    public RenderedCampos(String campoTEMP, boolean varRenderedCAMPO){
        this.campoTEMP = campoTEMP;
        this.varRenderedCAMPO = varRenderedCAMPO;
    }
    
    
    
    
    /*Métodos que receberam a reposta escrita
    no campo, essa resposta será analisada
    pela classe RenderedController.*/
    public String getCampoTEMP() {
        return campoTEMP;
    }

    public void setCampoTEMP(String campoTEMP) {
        this.campoTEMP = campoTEMP;
    }
    //----------------------------------------------
    
    
    
    
    
    /*Métodos que retornaram uma resposta booleana
    para o rendered que esteja em um elemento da tela*/    
    public boolean isVarRenderedCAMPO() {
        return varRenderedCAMPO;
    }

    public void setVarRenderedCAMPO(boolean varRenderedCAMPO) {
        this.varRenderedCAMPO = varRenderedCAMPO;
    }
    //-----------------------------------------------------------
    
    
}
