/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLE;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
/**
 *
 * @author thiberius
 */
@ManagedBean(name = "RenderedControle")
@SessionScoped
public class RenderedController {
    
    private String campoTEMP;
    private boolean varRenderedCAMPO = false;
    private static RenderedController[] objetos;

    
    
    
    
    public void renderedCampo(int num,Object objeto, String texto){
        RenderedController.objetos[num].varRenderedCAMPO = false;
        if(objeto.equals(texto)){
            RenderedController.objetos[num].varRenderedCAMPO = true;
        }
    }
    
    
    
    
    
    
    private RenderedController criarObjetoRendered(){
        RenderedController renderedObjeto = new RenderedController();
        return renderedObjeto;
    }
    
    
    
    
    
    
    public void prepararRenderedArray(){
        if(RenderedController.objetos == null){
            RenderedController.objetos = new RenderedController[2];
            RenderedController.objetos[0] = criarObjetoRendered();
            RenderedController.objetos[1] = criarObjetoRendered();
        }
    }
    
    
    
    
    
    
    public boolean getRenderedESTADO(int num){
        return RenderedController.objetos[num].varRenderedCAMPO;
    }
    
    
    
    
    public RenderedController getRenderedArray(int num){
        return RenderedController.objetos[num];
    }
    

    
    
    
    public String getCampoTEMP() {
        return this.campoTEMP;
    }
    
    
    
    
    
    public void setCampoTEMP(String campoTEMP){
        this.campoTEMP = campoTEMP;
    }
    
    
    
    
}
