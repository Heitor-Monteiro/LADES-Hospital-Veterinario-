/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.NewConsultation;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.ListRenderedFields;
import com.lades.sihv.controller.ModuleToCollectError;
import com.lades.sihv.controller.RenderedFields;
import com.lades.sihv.controller.Security;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.context.RequestContext;

/**
 *
 * @author thiberius
 */
public class ControllerToGrantDiscount extends AbstractBean {

    private String loginUserPowerDiscount;
    private String passwordUserPowerDiscount;
    private RenderedFields viewFieldsDiscount;
    private ControllerHVcostTable controllerHVcostTable;
    private ControllerConsulta controllerConsulta;
    private String administratorName;

    private ControllerToGrantDiscount() {
    }

    public ControllerToGrantDiscount(ControllerConsulta controllerConsulta, ControllerHVcostTable controllerHVcostTable) {
        this.viewFieldsDiscount = new RenderedFields();
        this.controllerHVcostTable = controllerHVcostTable;
        this.controllerConsulta = controllerConsulta;
        administratorName = "";
    }

    private int checkUserData() {
        int resposta = -1;
        try {
            loginUserPowerDiscount = loginUserPowerDiscount.toLowerCase();
            passwordUserPowerDiscount = new Security().encrypter(passwordUserPowerDiscount);
            List<Object> checkLogin = (List<Object>) getDaoGenerico().
                    list("select p.pkPerson from  People p, Users u, PowersHasUsers h \n"
                            + "where \n"
                            + "p.pkPerson = u.people.pkPerson and \n"
                            + "u.password='" + passwordUserPowerDiscount + "' and \n"
                            + "p.logicalExclusion='0' and \n"
                            + "(p.email='" + loginUserPowerDiscount + "' or \n"
                            + "u.userName='" + loginUserPowerDiscount + "') and \n"
                            + "h.users.pkUser=u.pkUser and \n"
                            + "h.powers.pkPower='10'");
            if (checkLogin != null) {
                if (!checkLogin.isEmpty()) {
                    resposta = (int) checkLogin.get(0);
                    administratorName = "" + getDaoGenerico()
                            .list("select p.namePerson from People p \n"
                                    + "where \n"
                                    + "p.pkPerson='" + resposta + "'").get(0);
                }
            }
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO private int checkUserData(): " + e.toString());
            new ModuleToCollectError().erroPage500("ControllerToGrantDiscount > checkUserData", e.toString());
        }
        return resposta;
    }

    public void checkUserWithDiscountPower() {
        try {
            viewFieldsDiscount.setViewVariableBoolean(false);
            int num = checkUserData();
            if (num != -1) {
                getObjMessage().info("Desconto permitido por " + administratorName + "!", "");
                viewFieldsDiscount.setViewVariableBoolean(true);
            } else {
                getObjMessage().warn("Desconto negado!", "Somente administradores podem conceder desconto.");
            }
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO private void ControllerToGrantDiscount(): " + e.toString());
            new ModuleToCollectError().erroPage500("ControllerToGrantDiscount > checkUserWithDiscountPower", e.toString());
        }
    }

    private String calculateDiscount() {
        String v;
        try {
            v = "" + (controllerHVcostTable.getTotalCost()
                    - controllerConsulta.getConsultation().getDiscountValue().doubleValue());
        } catch (Exception e) {
            v = "0";
        }
        return v;
    }

    public void confirmDiscount() {
        System.out.println("►►►►►►►►►►►►► " + calculateDiscount());
        try {
            if (controllerConsulta.getConsultation().getDiscountValue().doubleValue() > 0) {
                loginUserPowerDiscount = null;
                passwordUserPowerDiscount = null;
                administratorName = null;
                viewFieldsDiscount.setViewVariableBoolean(false);
                RequestContext.getCurrentInstance().execute("PF('enableDiscount').hide();");
            } else {
                getObjMessage().warn("Informe um valor para o desconto! &", "");
            }
        } catch (Exception e) {
            getObjMessage().warn("Informe um valor para o desconto! #", "");
        }
    }

    public void cancelDiscount() {
        try {
            loginUserPowerDiscount = null;
            passwordUserPowerDiscount = null;
            administratorName = null;
            viewFieldsDiscount.setViewVariableBoolean(false);
            controllerConsulta.getConsultation().setDiscountValue(BigDecimal.ZERO);
            RequestContext.getCurrentInstance().execute("PF('enableDiscount').hide();");
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO private void ControllerToGrantDiscount(): " + e.toString());
            new ModuleToCollectError().erroPage500("ControllerToGrantDiscount > cancelDiscount", e.toString());
        }
    }

    // GETs & SETs -------------------------------------------------------------
    public String getLoginUserPowerDiscount() {
        return loginUserPowerDiscount;
    }

    public void setLoginUserPowerDiscount(String loginUserPowerDiscount) {
        this.loginUserPowerDiscount = loginUserPowerDiscount;
    }

    public String getPasswordUserPowerDiscount() {
        return passwordUserPowerDiscount;
    }

    public void setPasswordUserPowerDiscount(String passwordUserPowerDiscount) {
        this.passwordUserPowerDiscount = passwordUserPowerDiscount;
    }

    public RenderedFields getViewFieldsDiscount() {
        return viewFieldsDiscount;
    }

    public String getCalculateDiscount() {
        return calculateDiscount();
    }

    public String getAdministratorName() {
        return administratorName;
    }

    //--------------------------------------------------------------------------
}
