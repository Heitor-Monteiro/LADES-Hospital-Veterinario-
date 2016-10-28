/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.Controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
/**
 *
 * @author thiberius
 */
@ManagedBean(name = "RenderedControle")
@SessionScoped
public class RenderedController implements Serializable{
    
    
    private RenderedCampos[] objetos;

    
    
    
    /*Este método altera o estado de visualização
    de um elemento no formulário*/
    public void renderedCampo(int index, String texto){
        if(objetos[index].getCampoTEMP().equals(texto)){
            objetos[index].setVarRenderedCAMPO(true);
        }else{
            objetos[index].setVarRenderedCAMPO(false);
        }
    }
    
    
    
    
    /*Este método cria uma quantidade n de objetos do
    tipo RenderedCampos de acordo com o parâmetro
    passado, cada objeto vai gerenciar um item
    na tela que precise sumir ou aparecer*/ 
    public void prepararRenderedArray(int qtd){
        
        objetos = new RenderedCampos[qtd];

        for (int i = 0; i <objetos.length; i++) {
            objetos[i] = new RenderedCampos("", false);
        }
        
    }
    
    
    
    
    //Este método permite acessar os campos da classe RenderedCampos
    public RenderedCampos getRenderedCampos(int index){
        return objetos[index];
    }
    
}