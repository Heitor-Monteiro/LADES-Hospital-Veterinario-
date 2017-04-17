/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller;


import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author thiberius
 */
public class FacesMessages implements Serializable{
    
    private static  final long serialVersionUID = 1L;
    private String tituloDialog;
    private String messageDialog;
    private String urlButton;
    
    
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
//--------------------------------------------------------------------------------------------    
    private void addDialog(String title, String message, Severity severity){
        Map<String, Object> opcoes = new HashMap<>();
        opcoes.put("modal", true);
        opcoes.put("resizable", false);
        opcoes.put("draggable", false);
        
        //RequestContext.getCurrentInstance().openDialog("dialogTeste", opcoes, null);
        RequestContext.getCurrentInstance().
                showMessageInDialog(new FacesMessage(severity,title,message));
    }
    
    //Método para exibir mensagem informativa na tela
    public void infoDialog(String title, String message){
        addDialog(title, message, FacesMessage.SEVERITY_INFO);
    }
    
    //Método para exibir mensagem de erro na tela
    public void errorDialog(String title, String message){
        addDialog(title, message, FacesMessage.SEVERITY_ERROR);
    }
    
    //Método para exibir mensagem de erro fatal na tela
    public void fatalErrorDialog(String title, String message){
        addDialog(title, message, FacesMessage.SEVERITY_FATAL);
    }
    
    //Método para exibir mensagem de advertência na tela
    public void warnDialog(String title, String message){
        addDialog(title, message, FacesMessage.SEVERITY_WARN);
    }
//--------------------------------------------------------------------------------------------
    public void setTextoDialog(String titulo,String texto,String urlButton) throws IOException{
        this.tituloDialog = titulo;
        this.messageDialog = texto;
        this.urlButton = urlButton;
        FacesContext.getCurrentInstance().
                getExternalContext().
                redirect("/SIHV/faces/sihv-includes/dialogMessage.xhtml");
    }
    
    public String getTituloDialog() {
        return tituloDialog;
    }

    public String getMessageDialog() {
        return messageDialog;
    }

    public String getUrlButton() {
        return urlButton;
    }
}
