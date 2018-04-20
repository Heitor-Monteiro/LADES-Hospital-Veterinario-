/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.pessoa;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.Security;
import com.lades.sihv.model.Fisica;
import com.lades.sihv.model.FisicaId;
import com.lades.sihv.model.Juridica;
import com.lades.sihv.model.JuridicaId;
import com.lades.sihv.model.Telefone;
import com.lades.sihv.model.Pessoa;
import com.lades.sihv.model.User;
import com.lades.sihv.model.UserId;
import com.lades.sihv.model.Cliente;
import com.lades.sihv.model.ClienteId;

/**
 *
 * @author thiberius
 */
public class PessoaMetodosDeCadastro extends AbstractBean {

    public void cadastrarPessoa(Pessoa pessoa) {
        pessoa.setExclusaoLogica(false);
        pessoa.setCadDataHora(getObjData());
        getDaoGenerico().save(pessoa);
    }

    public void cadastrarPessoaFisica(Pessoa pessoa, Fisica fisica) {
        FisicaId fisicaId = new FisicaId();
        fisicaId.setFkPessoa(pessoa.getPkPessoa());
        fisica.setId(fisicaId);
        if ("".equals(fisica.getRg())) {
            fisica.setRg("naoInformado");
            System.out.println("RG-não-informado===========================");
        }
        getDaoGenerico().save(fisica);
    }

    public void cadastrarPessoaJuridica(Pessoa pessoa, Juridica juridica) {
        juridica.setTipoPessoaJuridica("Não informado");
        JuridicaId juridicaId = new JuridicaId();
        juridicaId.setFkPessoa(pessoa.getPkPessoa());
        juridica.setId(juridicaId);
        getDaoGenerico().save(juridica);
    }

    public void cadastrarUser(Pessoa pessoa, User user, String numCRMV1, String numCRMV2) {
        UserId userId = new UserId();
        userId.setFkPessoa(pessoa.getPkPessoa());
        user.setId(userId);
        user.setUserSenha(security().encrypter(user.getUserSenha()));
        if (!"".equals(numCRMV1) && !"".equals(numCRMV2)) {
            user.setCrmvMatricula(numCRMV1 + " " + numCRMV2);
        }
        getDaoGenerico().save(user);
//        numCRMV1 = "";
//        numCRMV2 = "";
    }

    public void cadastrarCliente(Pessoa pessoa) {
        ClienteId clienteId = new ClienteId();
        clienteId.setFkPessoa(pessoa.getPkPessoa());
        Cliente cliente = new Cliente();
        cliente.setId(clienteId);
        getDaoGenerico().save(cliente);
    }

    /*O método é utilizado para controlar
    a persistência das variáveis telefone e celular*/
    public void cadastrarTelefones(Pessoa pessoa, Telefone telefone, Telefone celular) {
        if (!"".equals(telefone.getNumero())) {
            telefone.setPessoa(pessoa);
            getDaoGenerico().save(telefone);
        }
        celular.setPessoa(pessoa);
        getDaoGenerico().save(celular);
    }

    public void emailOpcional(Pessoa pessoa) {
        if ("".equals(pessoa.getEmail())) {
            pessoa.setEmail("naoInformado@naoInformado.com");
            System.out.println("email-não-informado===========================");
        }
        
        if ("".equals(pessoa.getCep())) {
            pessoa.setCep("naoInform");
            System.out.println("CEP-não-informado===========================");
        }
    }

    private Security security() {
        return new Security();
    }
}
