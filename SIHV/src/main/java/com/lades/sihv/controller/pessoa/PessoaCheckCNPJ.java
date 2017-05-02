/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.pessoa;

import com.lades.sihv.model.Pessoa;
import com.lades.sihv.bean.AbstractBean;
import java.util.InputMismatchException;

/**
 *
 * @author thiberius
 */
public class PessoaCheckCNPJ extends AbstractBean {

    public boolean MetodoCheckCNPJ(String CNPJ) {
        boolean var;
        CNPJ = CNPJ.replace(".", "");
        CNPJ = CNPJ.replace("-", "");
        CNPJ = CNPJ.replace("/", "");

// considera-se erro CNPJ's formados por uma sequencia de numeros iguais
        if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111")
                || CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333")
                || CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555")
                || CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777")
                || CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999")
                || (CNPJ.length() != 14)) {
            var = false;
        }

        char dig13, dig14;
        int sm, i, r, num, peso;

// "try" - protege o código para eventuais erros de conversao de tipo (int)
        try {
// Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {
// converte o i-ésimo caractere do CNPJ em um número:
// por exemplo, transforma o caractere '0' no inteiro 0
// (48 eh a posição de '0' na tabela ASCII)
                num = (int) (CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig13 = '0';
            } else {
                dig13 = (char) ((11 - r) + 48);
            }

// Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = (int) (CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig14 = '0';
            } else {
                dig14 = (char) ((11 - r) + 48);
            }

// Verifica se os dígitos calculados conferem com os dígitos informados.
            if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13))) {
                var = true;
            } else {
                var = false;
            }
        } catch (InputMismatchException erro) {
            var = false;
        }
        
        return var;
    }
    
    public boolean checkCNPJ(Pessoa pessoa) {
        boolean var;
        var = MetodoCheckCNPJ(pessoa.getCpfCnpj());
        if (var) {
            System.out.println("BACK-END WARNING: Método checkCNPJ [CNPJ "
                    +"("+ pessoa.getCpfCnpj() +")"+" Válido:" + var + "]");
        } else {
            getObjMessage().warn("Erro ao efetuar cadastro!", "CNPJ Inválido!");
            pessoa.setCpfCnpj("");
            System.out.println("BACK-END WARNING: Método checkCNPJ [CNPJ "
                    +"("+ pessoa.getCpfCnpj() +")"+" Válido:" + var + "]");
        }
        return var;
    }
}
