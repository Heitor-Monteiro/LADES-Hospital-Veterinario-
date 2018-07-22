/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author thiberius
 */
public class Tools implements Serializable {

    //A variável controla a visibilidade do botão para imprimir o formulário
    private boolean showButtonPrint = false;

    public void setRestShowButtonPrint() {
        this.showButtonPrint = false;
    }

    public boolean isShowButtonPrint() {
        return showButtonPrint;
    }

    public void setShowButtonPrint(boolean showButtonPrint) {
        this.showButtonPrint = showButtonPrint;
    }
    //----------------------------------------------------------------------

    //Método para redirecionar para outra pagina.
    public void redirecionar(String url) throws IOException {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } catch (IOException e) {
            System.out.println("BACK-END WARNING: Erro ao redirecionar navegação! [ public void redirecionar(String url) ]"
                    + e.getMessage());
        }
    }

    //Bloqueio do botão back do Wizard PrimeFAces
    public void blockBackWizad() {
        RequestContext.getCurrentInstance().execute("wiz.hideBackNav();");
    }

    private boolean wizardNavBar;

    public boolean isWizardNavBar() {
        return wizardNavBar;
    }

    public void enableWizardNavBar() {
        wizardNavBar = true;
    }

    public void disableWizardNavBar() {
        wizardNavBar = false;
    }
}