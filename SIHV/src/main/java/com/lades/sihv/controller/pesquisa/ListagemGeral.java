/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.pesquisa;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.classeMolde.CollectionClasses;
import com.lades.sihv.controller.BeautyText;
import com.lades.sihv.model.Animais;
import com.lades.sihv.model.Fisica;
import com.lades.sihv.model.Juridica;
import com.lades.sihv.model.Pessoa;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class ListagemGeral extends AbstractBean {
    
    /* O método generalSearchList realiza listagem de proprietários 
    e animais cadastrados, o mesmo identifica se o proprietário é 
    uma pessoa física ou jurídica informando: nome; data de cadastro. 
    O pesquisa retorna todos os dado de um proprietário ou animal
    (a pesquisa por animal retorna todos dado do animal e duas 
    informações de proprietário).*/
    public List<CollectionClasses> generalSearchList(String searchMode, String search) {
        search = "'%" + search + "%'";

        String tipoCliente = "";
        String joinTipoCli = "";
        
        /* As variáveis booleans determinam se a HQL para 
        listar pessoa física ou jurídica serão usadas -----------------------*/
        boolean tipoPessoaFisica = false;
        boolean tipoPessoJuridica = false;
        //---------------------------------------------------------------------
        
        /*A variável determina o tipo de indivíduo a ser
        pesquisado: proprietário, animal. -----------------------------------*/
        String tipoIndividuo = "";
        
        switch (searchMode) {
            case "cpf":
                //pesquisa pessoa(cpf) fisica
                searchMode = "p.cpfCnpj";
                tipoCliente = ", Fisica t";
                joinTipoCli = "p.pkPessoa = t.id.fkPessoa and";
                tipoPessoaFisica = true;
                tipoIndividuo = "proprietario";
                break;
            case "rg":
                //pesquisa pessoa(rg) fisica
                searchMode = "t.rg";
                tipoCliente = ", Fisica t";
                joinTipoCli = "p.pkPessoa = t.id.fkPessoa and";
                tipoPessoaFisica = true;
                tipoIndividuo = "proprietario";
                break;
            case "cnpj":
                //pesquisa pessoa(cnpj) juridica
                searchMode = "p.cpfCnpj";
                tipoCliente = ", Juridica t";
                joinTipoCli = "p.pkPessoa = t.id.fkPessoa and";
                tipoPessoJuridica = true;
                tipoIndividuo = "proprietario";
                break;
            case "nome":
                search = new BeautyText().Captalizador(search);
                searchMode = "p.nome";
                tipoPessoaFisica = true;
                tipoPessoJuridica = true;
                tipoIndividuo = "proprietario";
                break;
            case "cadDataHora":
                search = new BeautyText().Captalizador(search);
                searchMode = "p.cadDataHora";
                tipoPessoaFisica = true;
                tipoPessoJuridica = true;
                tipoIndividuo = "proprietario";
                break;
            case "nomeAnimal":
                search = new BeautyText().Captalizador(search);
                searchMode = "a.nomeAnimal";
                tipoIndividuo = "animal";
                break;
            case "rghv":
                search = new BeautyText().Captalizador(search);
                searchMode = "a.rghv";
                tipoIndividuo = "animal";
                break;
            case "cadDataHoraAnimal":
                search = new BeautyText().Captalizador(search);
                searchMode = "a.cadDataHora";
                tipoIndividuo = "animal";
                break;
            default:
                break;
        }

        List<CollectionClasses> listCollection = new ArrayList<>();

        switch (tipoIndividuo) {
            case "proprietario":
                List<Pessoa> listPessoa
                        = (List<Pessoa>) getDaoGenerico().list("SELECT p from Pessoa p, Cliente c"
                                + tipoCliente + " where " + joinTipoCli
                                + " p.pkPessoa=c.id.fkPessoa and p.exclusaoLogica=0 and "
                                + searchMode + " like " + search);

                for (Pessoa pessoa : listPessoa) {
                    CollectionClasses objCollection = new CollectionClasses();
                    objCollection.setProprietario(pessoa);

                    if (tipoPessoaFisica) {
                        List<Fisica> listFisica = (List<Fisica>) getDaoGenerico().list("select f from Fisica f where f.id.fkPessoa = " + pessoa.getPkPessoa());
                        if (listFisica.size() > 0) {
                            objCollection.setPessoaFisica((Fisica) listFisica.get(0));
                        }
                    }

                    if (tipoPessoJuridica) {
                        List<Juridica> listJuridica = (List<Juridica>) getDaoGenerico().list("select j from Juridica j where j.id.fkPessoa = " + pessoa.getPkPessoa());
                        if (listJuridica.size() > 0) {
                            objCollection.setPessoaJuridica((Juridica) listJuridica.get(0));
                            objCollection.setPessoaFisica(new Fisica());
                            objCollection.getPessoaFisica().setRg("---");
                            objCollection.getPessoaFisica().setSexo("---");
                            
                        }
                    }

                    List<String> listQtdAnimais = (List<String>) getDaoGenerico().list("select a.id.clienteFkPessoa from Animais a where a.id.clienteFkPessoa = '" + pessoa.getPkPessoa() + "'");
                    objCollection.getAnimais().setNomeAnimal("" + listQtdAnimais.size());

                    listCollection.add(objCollection);
                }
                break;
            case "animal":
                List<Animais> listAnimais
                        = (List<Animais>) getDaoGenerico().list("SELECT a from Animais a"
                                + " where " + searchMode + " like " + search);

                for (Animais animal : listAnimais) {
                    CollectionClasses objCollection = new CollectionClasses();
                    objCollection.setAnimais(animal);

                    List<?> listProprietario = (List<?>) getDaoGenerico().list("select p.nome, p.cpfCnpj from Pessoa p where p.pkPessoa = " + animal.getId().getClienteFkPessoa());
                    for (Object[] obj : (List<Object[]>) listProprietario) {
                        objCollection.getProprietario().setNome(""+obj[0]);
                        objCollection.getProprietario().setCpfCnpj(""+obj[1]);
                    }

                    listCollection.add(objCollection);
                }
                break;
            case "user":

                break;
        }

        System.out.println("BACK-END WARNING: LIST RETURNED! [ public List<CollectionClasses> generalSearchList(String searchMode, String search) ]");
        return listCollection;
    }

}
