/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller;

import com.lades.sihv.DAO.SessionUtils;
import com.lades.sihv.model.Animals;
import com.lades.sihv.model.People;
import com.lades.sihv.model.Powers;
import com.lades.sihv.model.Users;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class VariaveisDeSessao implements Serializable {

    public Object getObjetoTemp() {
        return (Object) SessionUtils.getSession().getAttribute("objetoTemporario");
    }

    public void setObjetoTemp(Object objeto) {
        SessionUtils.getSession().setAttribute("objetoTemporario", objeto);
    }

    public People objetoPessoa() {
        return (People) SessionUtils.getSession().getAttribute("objetoTemporario");
    }

    public Animals objetoAnimal() {
        return (Animals) SessionUtils.getSession().getAttribute("objetoTemporario");
    }

    public Object getFerramentaTemp() {
        return (Object) SessionUtils.getSession().getAttribute("ferramentaTemporaria");
    }

    public void setFerramentaTemp(Object objeto) {
        SessionUtils.getSession().setAttribute("ferramentaTemporaria", objeto);
    }
//---------------------------------------------------------------------------------------

    public List<String> getErroListText() {
        return (List<String>) SessionUtils.getSession().getAttribute("erroListText");
    }

    public void setErroListText(List<String> erroListText) {
        SessionUtils.getSession().setAttribute("erroListText", erroListText);
    }

//------------------------------------------------------------------------------
    private ListRenderedFields wizardButtons;

    public void startWizardButtons() {
        wizardButtons = new ListRenderedFields(2);
        SessionUtils.getSession().setAttribute("wizardButtons", wizardButtons);
    }

    public boolean getWizardBtnNext() {
        if (wizardButtons == null) {
            wizardButtons = new ListRenderedFields(2);
            startWizardButtons();
        }
        ListRenderedFields var = (ListRenderedFields) SessionUtils.getSession().getAttribute("wizardButtons");
        return var.getListViewFields(0).isViewVariableBoolean();
    }

    public void enableBtnNextWizard() {
        wizardButtons = (ListRenderedFields) SessionUtils.getSession().getAttribute("wizardButtons");
        wizardButtons.getListViewFields(0).setViewVariableBoolean(true);
        SessionUtils.getSession().setAttribute("wizardButtons", wizardButtons);
    }

    public boolean getWizardBtnBack() {
        if (wizardButtons == null) {
            wizardButtons = new ListRenderedFields(2);
            startWizardButtons();
        }
        ListRenderedFields var = (ListRenderedFields) SessionUtils.getSession().getAttribute("wizardButtons");
        return var.getListViewFields(1).isViewVariableBoolean();
    }

    public void enableBtnBackWizard() {
        wizardButtons = (ListRenderedFields) SessionUtils.getSession().getAttribute("wizardButtons");
        wizardButtons.getListViewFields(1).setViewVariableBoolean(true);
        SessionUtils.getSession().setAttribute("wizardButtons", wizardButtons);
    }

//---------------------------------------------------------------------------------------
    public void setDadosUSER(Object objUser) {
        SessionUtils.getSession().setAttribute("dadosUser", objUser);
    }

    public Users getDadosUSER() {
        return (Users) SessionUtils.getSession().getAttribute("dadosUser");
    }

    public void setDadosPESSOA(Object objPessoa) {
        People obj = (People) objPessoa;
        SessionUtils.getSession().setAttribute("username", new BeautyText().fistNLast(obj.getNamePerson()));
        SessionUtils.getSession().setAttribute("dadosPessoa", objPessoa);
    }

    private People getDadosPESSOA() {
        return (People) SessionUtils.getSession().getAttribute("dadosPessoa");
    }

    public String getUsername() {
        return (String) SessionUtils.getSession().getAttribute("username");
    }

    public String getUserTipo() {
        return (String) getDadosUSER().getUserProfile();
    }

    //-Variables POWERS USER----------------------------------------------------
    public void setPowersUSER(List<Object> listPowers) {
        SessionUtils.getSession().setAttribute("powersUSER", listPowers);
    }

    public List<Powers> getPowersUSER() {
        return (List<Powers>) SessionUtils.getSession().getAttribute("powersUSER");
    }
    //--------------------------------------------------------------------------

//    public String getCpfCnpj() {
//        return (String) getDadosPESSOA().getCpfCnpj();
//    }
    public String getFullName() {
        return (String) getDadosPESSOA().getNamePerson();
    }

    public int getPkPessoa() {
        return (int) getDadosPESSOA().getPkPerson();
    }

    public String getCrmvMatricula() {
        return (String) getDadosUSER().getRegistrationNumber();
    }

    public String getSenhaUser() {
        return (String) getDadosUSER().getPassword();
    }
}
