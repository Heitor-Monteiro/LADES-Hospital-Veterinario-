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
import java.util.List;
import java.util.Random;

/**
 *
 * @author thiberius
 */
public class PessoaMetodosDeCadastro extends AbstractBean {

    public void cadastrarPessoa(Pessoa pessoa) {
        pessoa.setExclusaoLogica(false);
        pessoa.setCadDataHora(getObjData());
        emailOpcional(pessoa);
        cepOpcional(pessoa);
        getDaoGenerico().save(pessoa);
    }

    public void cadastrarPessoaFisica(Pessoa pessoa, Fisica fisica) {
        FisicaId fisicaId = new FisicaId();
        fisicaId.setFkPessoa(pessoa.getPkPessoa());
        fisica.setId(fisicaId);
        rgOpcional(fisica);
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
    public void cadastrarTelefones(Pessoa pessoa, List<Telefone> telefoneList) {
        try {
            int index = 0;
            for (Telefone telefone : telefoneList) {
                System.out.println("BACK-END WARNING: phone " + index + " [ public void cadastrarTelefones() ]");
                if (telefone != null) {
                    if (!telefone.getNumero().equals("")) {
                        telefone.setPessoa(pessoa);
                        getDaoGenerico().save(telefone);
                    }
                }
                index++;
            }
        } catch (Exception e) {
            System.out.println("BACK-END WARNING: ERROR registering phone [ public void cadastrarTelefones() ]" + e.getMessage());
        }
    }

    private void emailOpcional(Pessoa pessoa) {
        if ("".equals(pessoa.getEmail())) {
            pessoa.setEmail("naoInformado@naoInformado.com");
            System.out.println("BACK-END WARNING: Email not informed [ private void emailOpcional() ]");
        }
    }

    private void cepOpcional(Pessoa pessoa) {
        if ("".equals(pessoa.getCep())) {
            pessoa.setCep("NãoInform");
            System.out.println("BACK-END WARNING: CEP not informed [ private void cepOpcional() ]");
        }
    }

    public void cpfRandom(Pessoa pessoa) {
        Random random = new Random();
        String var = "" + random.nextInt(9999999);
        var = var + "" + random.nextInt(9999999);
        pessoa.setCpfCnpj("SIHV" + var);
        System.out.println("BACK-END WARNING: CPF not informed [ public void cpfRandom() ]: SIHV" + var);
    }

    private void rgOpcional(Fisica fisica) {
        if ("".equals(fisica.getRg())) {
            fisica.setRg("Não Informado");
            System.out.println("BACK-END WARNING: RG not informed [ private void rgOpcional() ]");
        }
    }

    private Security security() {
        return new Security();
    }
}
