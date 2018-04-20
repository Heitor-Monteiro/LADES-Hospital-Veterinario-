/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.pesquisa;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.BeautyText;
import com.lades.sihv.model.Pessoa;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class ListagemPessoa extends AbstractBean {

    public List<Pessoa> listBySearchPESSOA(String searchMode, String search) {
        search = "'%" + search + "%'";

        String tipoCliente = "";
        String joinTipoCli = "";
        switch (searchMode) {
            case "cpf":
                //pesquisa pessoa(cpf) fisica
                searchMode = "p.cpfCnpj";
                tipoCliente = ", Fisica t";
                joinTipoCli = "p.pkPessoa = t.id.fkPessoa and";
                break;
            case "rg":
                //pesquisa pessoa(rg) fisica
                searchMode = "t." + searchMode;
                tipoCliente = ", Fisica t";
                joinTipoCli = "p.pkPessoa = t.id.fkPessoa and";
                break;
            case "cnpj":
                //pesquisa pessoa(cnpj) juridica
                searchMode = "p.cpfCnpj";
                tipoCliente = ", Juridica t";
                joinTipoCli = "p.pkPessoa = t.id.fkPessoa and";
                break;
            case "nome":
                //pesquisa pessoa(nome) não considera se é pessoa fisica ou juridica.
                search = new BeautyText().Captalizador(search);
                searchMode = "p." + searchMode;
                break;
            default:
                break;
        }

        List<?> listaPessoa = getDaoGenerico().list("SELECT p.pkPessoa, p.nome, p.cpfCnpj, p.logra, p.casaNumero, p.bairro, p.cidade, p.complemento from Pessoa p, Cliente c" + tipoCliente + " where " + joinTipoCli + " p.pkPessoa=c.id.fkPessoa and p.exclusaoLogica=0 and " + searchMode + " like " + search);
        List<Pessoa> retornaPessoa = new ArrayList<>();
        for (Object[] obj : (List<Object[]>) listaPessoa) {
            Pessoa newPessoa = new Pessoa();
            newPessoa.setPkPessoa((int) obj[0]);
            newPessoa.setNome((String) obj[1]);
            newPessoa.setCpfCnpj((String) obj[2]);
            newPessoa.setLogra((String) obj[3]);
            newPessoa.setCasaNumero((String) obj[4]);
            newPessoa.setBairro((String) obj[5]);
            newPessoa.setCidade((String) obj[6]);
            newPessoa.setComplemento((String) obj[7]);
            retornaPessoa.add(newPessoa);
        }
        System.out.println("BACK-END WARNING: LIST RETURNED! [ public List<Pessoa> listBySearchPESSOA(String searchMode, String search) ]");
        return retornaPessoa;
    }
}
