/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.pessoa;

import com.lades.sihv.model.Pessoa;
import com.lades.sihv.bean.AbstractBean;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class PessoaCheckExistCpfCnpj extends AbstractBean {

    public boolean checkExistCpfCnpj(Pessoa pessoa, boolean mudancaCpfCnpj) {
        String tipoDocumento;
        boolean var = false;
        
        if (mudancaCpfCnpj) {
            tipoDocumento = "CPF";
        } else {
            tipoDocumento = "CNPJ";
        }
        
        try {
            List<Pessoa> list;
            list = (List<Pessoa>) getDaoGenerico().
                    list("select p from Pessoa p where p.cpfCnpj = '" + pessoa.getCpfCnpj() + "'");
            
            var = list.isEmpty();
            if (var) {
                System.out.println("BACK-END WARNING: Método checkExistCpfCnpj [ " 
                        + tipoDocumento + "(" + pessoa.getCpfCnpj() + ")" 
                                + " não existe:" + var + "]");
            } else {
                getObjMessage().warn("Erro ao efetuar cadastro!", "O " 
                        + tipoDocumento + " informado já existe!");
                pessoa.setCpfCnpj("");
                System.out.println("BACK-END WARNING: Método checkExistCpfCnpj [ " 
                        + tipoDocumento + "(" + pessoa.getCpfCnpj() + ")" 
                                + " já existe:" + var + "]");
            }
        } catch (Exception e) {
            getObjMessage().warn("Erro ao verificar existência de CPF/CNPJ!", 
                    e.getMessage() + "\nVerifique os dados e tente novamente!");
        }
        return var;
    }
}
