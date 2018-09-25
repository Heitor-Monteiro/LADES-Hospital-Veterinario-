/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.pesquisa;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.BeautyText;
import com.lades.sihv.model.People;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class ListagemPessoa extends AbstractBean {

//    public List<Pessoa> listBySearchPESSOA(String searchMode, String search) {
//        search = "'%" + search + "%'";
//
//        String TypeClient = "";
//        String joinTipoCli = "";
//        switch (searchMode) {
//            case "cpf":
//                //pesquisa pessoa(cpf) fisica
//                searchMode = "p.cpfCnpj";
//                TypeClient = ", Fisica t";
//                joinTipoCli = "p.pkPessoa = t.id.fkPessoa and";
//                break;
//            case "rg":
//                //pesquisa pessoa(rg) fisica
//                searchMode = "t." + searchMode;
//                TypeClient = ", Fisica t";
//                joinTipoCli = "p.pkPessoa = t.id.fkPessoa and";
//                break;
//            case "cnpj":
//                //pesquisa pessoa(cnpj) juridica
//                searchMode = "p.cpfCnpj";
//                TypeClient = ", Juridica t";
//                joinTipoCli = "p.pkPessoa = t.id.fkPessoa and";
//                break;
//            case "nome":
//                //pesquisa pessoa(nome) não considera se é pessoa fisica ou juridica.
//                search = new BeautyText().Captalizador(search);
//                searchMode = "p." + searchMode;
//                break;
//            default:
//                break;
//        }
//
//        List<Pessoa> listarPessoa = (List<Pessoa>) 
//                getDaoGenerico().list("SELECT p from Pessoa p, Cliente c" 
//                        + TypeClient + " where " + joinTipoCli 
//                        + " p.pkPessoa=c.id.fkPessoa and p.exclusaoLogica=0 and " 
//                        + searchMode + " like " + search);
//        
//        System.out.println("BACK-END WARNING: LIST RETURNED! [ public List<Pessoa> listBySearchPESSOA(String searchMode, String search) ]");
//        return listarPessoa;
//    }
}
