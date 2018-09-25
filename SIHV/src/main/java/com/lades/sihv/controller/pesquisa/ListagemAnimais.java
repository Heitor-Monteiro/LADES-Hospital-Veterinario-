/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.pesquisa;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.classeMolde.CollectionClasses;
import com.lades.sihv.controller.BeautyText;
//import com.lades.sihv.model.AnimaisId;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class ListagemAnimais extends AbstractBean {
    
//    public List<CollectionClasses> listBySearchANIMAIS(String searchMode, String search) {
//        String tipoCliente = "";
//        String joinTipoCli = "";
//        switch (searchMode) {
//            case "cpf":
//                searchMode = "p.cpfCnpj";
//                tipoCliente = ", Fisica t";
//                joinTipoCli = "p.pkPessoa = t.id.fkPessoa and";
//                break;
//            case "cnpj":
//                searchMode = "p.cpfCnpj";
//                tipoCliente = ", Juridica t";
//                joinTipoCli = "p.pkPessoa = t.id.fkPessoa and";
//                break;
//            case "rg":
//                searchMode = "t." + searchMode;
//                tipoCliente = ", Fisica t";
//                joinTipoCli = "p.pkPessoa = t.id.fkPessoa and";
//                break;
//            case "nome":
//                search = new BeautyText().Captalizador(search);
//                searchMode = "p." + searchMode;
//                break;
//            case "rghv":
//                searchMode = "a." + searchMode;
//                break;
//            default:
//                break;
//        }
//
//        search = "'%" + search + "%'";
//        List<?> listaPessoa = getDaoGenerico().list("select a.id.pkAnimal, a.id.clienteFkCliente, "
//                + "a.id.clienteFkPessoa, a.nomeAnimal, a.especie, a.sexoAnimal, a.rghv, "
//                + "p.nome, p.cpfCnpj "
//                + "from Animais a, Pessoa p, Cliente c" + tipoCliente + " where " + joinTipoCli + " "
//                + "p.pkPessoa = c.id.fkPessoa "
//                + "and c.id.fkPessoa = a.id.clienteFkPessoa "
//                + "and " + searchMode + " like " + search);
//        List<CollectionClasses> retornaAnimais = new ArrayList<>();
//        for (Object[] obj : (List<Object[]>) listaPessoa) {
//            CollectionClasses newAnimal = new CollectionClasses();
//            AnimaisId idAnimal = new AnimaisId();
//
//            idAnimal.setPkAnimal((int) obj[0]);
//            idAnimal.setFkCliente((int) obj[1]);
//            idAnimal.setFkPessoa((int) obj[2]);
//
//            newAnimal.getAnimais().setId(idAnimal);
//            newAnimal.getAnimais().setNomeAnimal((String) obj[3]);
//            newAnimal.getAnimais().setEspecie((String) obj[4]);
//            newAnimal.getAnimais().setSexoAnimal((String) obj[5]);
//            newAnimal.getAnimais().setRghv((String) obj[6]);
//            newAnimal.getProprietario().setNome((String) obj[7]);
//            newAnimal.getProprietario().setCpfCnpj((String) obj[8]);
//            retornaAnimais.add(newAnimal);
//        }
//        System.out.println("BACK-END WARNING: LIST RETURNED! [ public List<Animais> listBySearchANIMAIS(String searchMode, String search) ]");
//        return retornaAnimais;
//    }

}
