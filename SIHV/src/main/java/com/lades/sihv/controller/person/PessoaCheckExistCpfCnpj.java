/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.person;

import com.lades.sihv.model.People;
import com.lades.sihv.bean.AbstractBean;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class PessoaCheckExistCpfCnpj extends AbstractBean {

    public boolean checkExistCpfCnpj(People person, boolean mudancaCpfCnpj) {
        String tipoDocumento;
        boolean var = false;
//
//        if (mudancaCpfCnpj) {
//            tipoDocumento = "CPF";
//        } else {
//            tipoDocumento = "CNPJ";
//        }
//
//        try {
//            List<People> list;
//            list = (List<People>) getDaoGenerico().
//                    list("select p from Pessoa p where p.cpfCnpj = '" + person.getCpfCnpj() + "'");
//
//            var = list.isEmpty();
//            if (var) {
//                System.out.println("BACK-END WARNING: Método checkExistCpfCnpj [ "
//                        + tipoDocumento + "(" + person.getCpfCnpj() + ")"
//                        + " não existe:" + var + "]");
//            } else {
//                getObjMessage().warn("Erro ao efetuar cadastro!", "O "
//                        + tipoDocumento + " informado já existe!");
//                person.setCpfCnpj("");
//                System.out.println("BACK-END WARNING: Método checkExistCpfCnpj [ "
//                        + tipoDocumento + "(" + person.getCpfCnpj() + ")"
//                        + " já existe:" + var + "]");
//            }
//        } catch (Exception e) {
//            getObjMessage().warn("Erro ao verificar existência de CPF/CNPJ!",
//                    e.getMessage() + "\nVerifique os dados e tente novamente!");
//        }
        return var;
    }
}
