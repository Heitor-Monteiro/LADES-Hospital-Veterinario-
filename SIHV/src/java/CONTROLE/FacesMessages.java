/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLE;


import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

/**
 *
 * @author thiberius
 */
public class FacesMessages implements Serializable{
    
    private static  final long serialVersionUID = 1L;
    
    
    //Método gerador de mensagens
    private void add(String title, String message, Severity severity){
        FacesContext context = FacesContext.getCurrentInstance();	
	context.addMessage(null, new FacesMessage(severity,title,message));
    }
    
    //Método para exibir mensagem informativa na tela
    public void info(String title, String message){
        add(title, message, FacesMessage.SEVERITY_INFO);
    }
    
    //Método para exibir mensagem de erro na tela
    public void error(String title, String message){
        add(title, message, FacesMessage.SEVERITY_ERROR);
    }
    
    //Método para exibir mensagem de erro fatal na tela
    public void fatalError(String title, String message){
        add(title, message, FacesMessage.SEVERITY_FATAL);
    }
    
    //Método para exibir mensagem de advertência na tela
    public void warn(String title, String message){
        add(title, message, FacesMessage.SEVERITY_WARN);
    }
    
}
