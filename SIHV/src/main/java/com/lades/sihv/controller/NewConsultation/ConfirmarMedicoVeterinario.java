/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.NewConsultation;

import com.lades.sihv.controller.Security;
import com.lades.sihv.bean.AbstractBean;

/**
 *
 * @author thiberius
 */
public class ConfirmarMedicoVeterinario extends AbstractBean {

    /*O método é chamado para atestar que um medico
    veterinário ira fazer a consulta, ou seja, 
    uma nova consulta só será concretizada 
    se houver o aval do mesmo*/
    public boolean confirmaMEDICO(String confirmeSENHA, String confirmeCRMV) {
        boolean var = false;
        confirmeSENHA = new Security().encrypter(confirmeSENHA);

        if (confirmeSENHA.equals(getVariaveisDeSessao().getSenhaUser())
                && confirmeCRMV.equals(getVariaveisDeSessao().getCrmvMatricula())) {
            var = true;
        } else {
            getObjMessage().warn("Verificação não confirmada!", "É necessário um medico veterinário cadastrado!");
        }
        return var;
    }
}
