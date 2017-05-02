/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.pessoa;

import com.lades.sihv.model.Pessoa;
import com.lades.sihv.bean.AbstractBean;

/**
 *
 * @author thiberius
 */
public class PessoaCheckCPF extends AbstractBean {

    private boolean MetodoCheckCPF(String strCpf) {
        boolean var;
        strCpf = strCpf.replace(".", "");
        strCpf = strCpf.replace("-", "");

        if (strCpf.equals("00000000000") || strCpf.equals("11111111111")
                || strCpf.equals("22222222222") || strCpf.equals("33333333333")
                || strCpf.equals("44444444444") || strCpf.equals("55555555555")
                || strCpf.equals("66666666666") || strCpf.equals("77777777777")
                || strCpf.equals("88888888888") || strCpf.equals("99999999999")
                || (strCpf.length() != 11)) {
            var = false;
        } else {

            int d1, d2;
            int digito1, digito2, resto;
            int digitoCPF;
            String nDigResult;

            d1 = d2 = 0;
            digito1 = digito2 = resto = 0;

            for (int nCount = 1; nCount < strCpf.length() - 1; nCount++) {
                digitoCPF = Integer.valueOf(strCpf.substring(nCount - 1, nCount)).intValue();

                //multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante.
                d1 = d1 + (11 - nCount) * digitoCPF;

                //para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior.
                d2 = d2 + (12 - nCount) * digitoCPF;
            };

            //Primeiro resto da divisão por 11.
            resto = (d1 % 11);

            //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
            if (resto < 2) {
                digito1 = 0;
            } else {
                digito1 = 11 - resto;
            }

            d2 += 2 * digito1;

            //Segundo resto da divisão por 11.
            resto = (d2 % 11);

            //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
            if (resto < 2) {
                digito2 = 0;
            } else {
                digito2 = 11 - resto;
            }

            //Digito verificador do CPF que está sendo validado.
            String nDigVerific = strCpf.substring(strCpf.length() - 2, strCpf.length());

            //Concatenando o primeiro resto com o segundo.
            nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

            //comparar o digito verificador do cpf com o primeiro resto + o segundo resto.
            var = nDigVerific.equals(nDigResult);
        }
        return var;
    }

    public boolean checkCPF(Pessoa pessoa) {
        boolean var;
        var = MetodoCheckCPF(pessoa.getCpfCnpj());
        if (var) {
            System.out.println("BACK-END WARNING: Método checkCPF [CPF "
                    +"("+ pessoa.getCpfCnpj() +")"+" Válido:" + var + "]");
        } else {
            getObjMessage().warn("Erro ao efetuar cadastro!", "CPF Inválido!");
            pessoa.setCpfCnpj("");
            System.out.println("BACK-END WARNING: Método checkCPF [CPF "
                    +"("+ pessoa.getCpfCnpj() +")"+" Inválido:" + var + "]");
        }
        return var;
    }

}
