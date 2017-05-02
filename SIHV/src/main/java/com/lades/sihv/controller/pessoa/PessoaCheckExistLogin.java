/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.pessoa;

import com.lades.sihv.model.Pessoa;
import com.lades.sihv.model.User;
import com.lades.sihv.bean.AbstractBean;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class PessoaCheckExistLogin extends AbstractBean {

    public boolean checkExistingLogin(Pessoa pessoa, User user) {
        boolean var = false;
        try {
            List<Pessoa> checkLogin = (List<Pessoa>) getDaoGenerico().
                    list("select p from Pessoa p, User u where p.email = '" 
                            + pessoa.getEmail() + "' or u.userNick='" + user.getUserNick() + "'");
            var = checkLogin.isEmpty();

            if (var) {
                System.out.println("BACK-END WARNING: Método checkExistingLogin [Login e e-mail NÂO EXISTE:" + var + "]");
            } else {
                getObjMessage().warn("Erro ao efetuar cadastro!", "O nome de Login ou e-mail já existe!");
                pessoa.setEmail("");
                System.out.println("BACK-END WARNING: Método checkExistingLogin [Login ou e-mail JÁ EXISTE:" + var + "]");
            }
        } catch (Exception e) {
            getObjMessage().warn("Erro ao verificar login!", e.getMessage() + "\nVerifique os dados e tente novamente!");
        }
        return var;
    }
}
