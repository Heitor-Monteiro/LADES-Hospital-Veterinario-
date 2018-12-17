/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.pesquisa;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.NewConsultation.CollectionClasses;
import com.lades.sihv.controller.BeautyText;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class ListagemConsulta extends AbstractBean {
    
//    public List<CollectionClasses> listBySearchCONSULTA(String searchMode, String search) {
//        String tipoCliente = "";
//        String joinTipoCli = "";
//        switch (searchMode) {
//            case "cpf":
//                searchMode = "pcli.cpfCnpj";
//                tipoCliente = ", Fisica t ";
//                joinTipoCli = "pcli.pkPessoa = t.id.fkPessoa and ";
//                break;
//            case "cnpj":
//                searchMode = "pcli.cpfCnpj";
//                tipoCliente = ", Juridica t ";
//                joinTipoCli = "pcli.pkPessoa = t.id.fkPessoa and ";
//                break;
//            case "rg":
//                searchMode = "t." + searchMode;
//                tipoCliente = ", Fisica t ";
//                joinTipoCli = "pcli.pkPessoa = t.id.fkPessoa and ";
//                break;
//            case "nome":
//                search = new BeautyText().Captalizador(search);
//                searchMode = "pcli." + searchMode;
//                break;
//            case "rghv":
//                searchMode = "a." + searchMode;
//                break;
//            default:
//                break;
//        }
//
//        search = "'%" + search + "%'";
//        List<?> listaConsulta = getDaoGenerico().list("select c.pkConsulta, c.sistemasAfetados, c.dataConsulta, c.laudo, "
//                + "a.nomeAnimal, a.sexoAnimal, a.rghv, a.categoriaAnimal, a.especie, "
//                + "puser.nome, user.crmvMatricula, "
//                + "pcli.nome, pcli.cpfCnpj, pcli.logra, pcli.bairro, pcli.cidade "
//                + "from Consulta c, Animais a, Pessoa puser, User user, Pessoa pcli, Cliente cli " + tipoCliente
//                + "where " + joinTipoCli
//                + "pcli.pkPessoa=cli.id.fkPessoa "
//                + "and cli.id.fkPessoa=a.id.clienteFkPessoa "
//                + "and a.id.clienteFkPessoa=c.animais.id.clienteFkPessoa "
//                + "and " + searchMode + " like " + search + " "
//                + "and puser.pkPessoa=user.id.fkPessoa "
//                + "and user.id.fkPessoa=c.user.id.fkPessoa "
//                + "and a.id.pkAnimal=c.animais.id.pkAnimal");
//        List<CollectionClasses> retornaConsulta = new ArrayList<>();
//        for (Object[] obj : (List<Object[]>) listaConsulta) {
//
//            CollectionClasses objbusca = new CollectionClasses();
//
//            objbusca.getConsulta().setPkConsulta((int) obj[0]);
//            objbusca.getConsulta().setSistemasAfetados((String) obj[1]);
//            objbusca.getConsulta().setDataConsulta((Date) obj[2]);
//            objbusca.getConsulta().setLaudo((String) obj[3]);
//            objbusca.getAnimais().setNomeAnimal((String) obj[4]);
//            objbusca.getAnimais().setSexoAnimal((String) obj[5]);
//            objbusca.getAnimais().setRghv((String) obj[6]);
//            objbusca.getAnimais().setCategoriaAnimal((String) obj[8]);
//            objbusca.getAnimais().setEspecie((String) obj[9]);
//            objbusca.getResidente().setNome((String) obj[10]);
//            objbusca.getUser().setCrmvMatricula((String) obj[11]);
//            objbusca.getProprietario().setNome((String) obj[12]);
//            objbusca.getProprietario().setCpfCnpj((String) obj[13]);
//            objbusca.getProprietario().setLogra((String) obj[14]);
//            objbusca.getProprietario().setBairro((String) obj[15]);
//            objbusca.getProprietario().setCidade((String) obj[16]);
//
//            retornaConsulta.add(objbusca);
//        }
//        System.out.println("BACK-END WARNING: LIST RETURNED! [ public List<Consulta> listBySearchCONSULTA(String searchMode, String search) ]");
//        return retornaConsulta;
//    }

}
