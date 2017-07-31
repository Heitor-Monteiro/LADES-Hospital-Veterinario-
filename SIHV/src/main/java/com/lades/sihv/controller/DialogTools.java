/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import org.primefaces.context.RequestContext;

/**
 *
 * @author thiberius
 */
public class DialogTools implements Serializable {

    private Severity severity;
    private String title;
    private String message;
    private String page;

    private void dialogCustomized(boolean var) {
        Map<String, Object> options = new HashMap<>();
        options.put("widgetVar", "processDialog");
        options.put("modal", false);
        options.put("width", 640);
        options.put("height", 340);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("headerElement", "customheader");

        if (var) {
            //Exibe mensagem pelo dialogo
            RequestContext.getCurrentInstance().
                    showMessageInDialog(new FacesMessage(severity, title, message));
        } else {
            //Exibe uma pagina pelo dialogo
            RequestContext.getCurrentInstance().openDialog(page, options, null);
        }
    }

    private void titleMessage(String title, String message) {
        this.title = title;
        this.message = message;
    }

    //Método para exibir mensagem informativa na tela
    public void infoDialog(String title, String message) {
        severity = FacesMessage.SEVERITY_INFO;
        titleMessage(title, message);
        dialogCustomized(true);
    }

    //Método para exibir mensagem de erro na tela
    public void errorDialog(String title, String message) {
        severity = FacesMessage.SEVERITY_ERROR;
        titleMessage(title, message);
        dialogCustomized(true);
    }

    //Método para exibir mensagem de erro fatal na tela
    public void fatalErrorDialog(String title, String message) {
        severity = FacesMessage.SEVERITY_FATAL;
        titleMessage(title, message);
        dialogCustomized(true);
    }

    //Método para exibir mensagem de advertência na tela
    public void warnDialog(String title, String message) {
        severity = FacesMessage.SEVERITY_WARN;
        titleMessage(title, message);
        dialogCustomized(true);
    }

//    public void closeDialog() {
//        RequestContext.getCurrentInstance().closeDialog(this);
//    }
}
