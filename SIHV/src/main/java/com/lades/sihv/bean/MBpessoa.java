/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.bean;

import com.lades.sihv.controller.pessoa.PessoaCheckExistLogin;
import com.lades.sihv.controller.pessoa.PessoaCheckExistCpfCnpj;
import com.lades.sihv.controller.pessoa.PessoaCheckCPF;
import com.lades.sihv.controller.pessoa.PessoaCheckCNPJ;
import com.lades.sihv.controller.pessoa.PessoaPadraoCaracter;
import com.lades.sihv.controller.pessoa.PessoaMetodosDeCadastro;
import com.lades.sihv.model.Fisica;
import com.lades.sihv.model.Juridica;
import com.lades.sihv.model.Pessoa;
import com.lades.sihv.model.Telefone;
import com.lades.sihv.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author thiberius
 */
@ManagedBean(name = "MBpessoa")
@ViewScoped
public class MBpessoa extends AbstractBean {

    private Pessoa pessoa;
    private Fisica fisica;
    private Juridica juridica;
    private final List<Telefone> telefoneList = new ArrayList<>();
    private boolean viewTelefone2;
    private boolean viewTelefone3;
    private User user;
    private boolean mudancaCpfCnpj = true;
    private boolean cpfOpcional = true;
    private boolean rgOpcional = true;
    private String numCRMV1 = "";
    private String numCRMV2 = "";
    /*Os dois atributos serão utilizado para
    a concatenação do CRMV do medico veterinário*/

 /*Os método e variáveis abaixo são utilizados para controlar a visibilidade
    de campos para cadastro de clientes e usuários do sistema*/
    private String tipoPessoa = "cliente";

    public boolean getTipoPessoa() {
        boolean var = true;
        switch (tipoPessoa) {
            case "cliente":
                var = true;
                break;
            case "user":
                var = false;
                break;
        }
        return var;
    }

    public void prepararAdicionarUSER() {
        tipoPessoa = "user";
    }

    public void cadastrarUserMedico() {
        getUser().setUserTipo("MEDICO");
        checkDadosUserPessoa("Residente");
    }

    public void cadastrarUserTecnico() {
        getUser().setUserTipo("TÉCNICO");
        checkDadosUserPessoa("Funcionário/técnico");
    }

    public void cadastrarUserAluno() {
        getUser().setUserTipo("ALUNO");
        checkDadosUserPessoa("Recepcionista");
    }

    private void checkDadosUserPessoa(String mensageTIPO) {
        getUser().setUserNick(getUser().getUserNick().toLowerCase());
        boolean checkCPF, existCpfCnpj, existLogin;
        checkCPF = ObjPessoaCheckCPF().checkCPF(pessoa);
        existCpfCnpj = ObjCheckExistCpfCnpj().checkExistCpfCnpj(pessoa, mudancaCpfCnpj);
        existLogin = ObjCheckExistLogin().checkExistingLogin(pessoa, user);
        if (checkCPF && existCpfCnpj && existLogin) {
            ObjPessoaPadraoCaracter().padraoCaracter(pessoa);
            cadastrarUser(mensageTIPO);
        }
    }

    private void cadastrarUser(String mensageTIPO) {
        try {
            PessoaMetodosDeCadastro obj = ObjPessoaMetodosDeCadastro();
            obj.cadastrarPessoa(pessoa);
            obj.cadastrarPessoaFisica(pessoa, fisica);
            obj.cadastrarTelefones(pessoa, telefoneList);
            obj.cadastrarUser(pessoa, user, numCRMV1, numCRMV2);
            getObjMessage().info("Cadastro efetuado!", mensageTIPO + " cadastrado com sucesso.");
            getObjTools().blockBackWizad();//Bloqueio do botão back do Wizard PrimeFAces
            getObjTools().setShowButtonPrint(true);
        } catch (Exception e) {
            getObjMessage().warn("Erro ao efetuar cadastro!", e.getMessage()
                    + "\nVerifique os dados e tente novamente!");
        }
    }

    public void cadastrarCliente() {
        try {
            PessoaMetodosDeCadastro obj = ObjPessoaMetodosDeCadastro();
            boolean checkCpfCnpj, existCpfCnpj, emptyCpfCnpj;
            emptyCpfCnpj = pessoa.getCpfCnpj().isEmpty();
            
            if (mudancaCpfCnpj && emptyCpfCnpj && !cpfOpcional) {
                do {
                    obj.cpfRandom(pessoa);
                    checkCpfCnpj = ObjCheckExistCpfCnpj().checkExistCpfCnpj(pessoa, mudancaCpfCnpj);
                } while (!checkCpfCnpj);
            } else if (mudancaCpfCnpj) {
                checkCpfCnpj = ObjPessoaCheckCPF().checkCPF(pessoa);
            } else {
                checkCpfCnpj = ObjPessoaCheckCNPJ().checkCNPJ(pessoa);
            }

            existCpfCnpj = ObjCheckExistCpfCnpj().checkExistCpfCnpj(pessoa, mudancaCpfCnpj);

            if (checkCpfCnpj && existCpfCnpj) {
                ObjPessoaPadraoCaracter().padraoCaracter(pessoa);
                obj.cadastrarPessoa(pessoa);
                obj.cadastrarTelefones(pessoa, telefoneList);
                obj.cadastrarCliente(pessoa);
                if (mudancaCpfCnpj) {
                    obj.cadastrarPessoaFisica(pessoa, fisica);
                } else {
                    obj.cadastrarPessoaJuridica(pessoa, getJuridica());
                }

                getObjTools().blockBackWizad();//Bloqueio do botão back do Wizard PrimeFAces
                getObjMessage().info("Cadastro efetuado!", "Cliente cadastrado com sucesso.");
                getObjTools().setShowButtonPrint(true); //Habilitando visibilidade do botão para impressão
            }
        } catch (Exception e) {
            getObjMessage().warn("Erro ao efetuar cadastro!", e.getMessage()
                    + "\nVerifique os dados e tente novamente!");
        }
    }

    private PessoaCheckExistLogin ObjCheckExistLogin() {
        return new PessoaCheckExistLogin();
    }

    private PessoaCheckCPF ObjPessoaCheckCPF() {
        return new PessoaCheckCPF();
    }

    private PessoaCheckCNPJ ObjPessoaCheckCNPJ() {
        return new PessoaCheckCNPJ();
    }

    private PessoaCheckExistCpfCnpj ObjCheckExistCpfCnpj() {
        return new PessoaCheckExistCpfCnpj();
    }

    private PessoaPadraoCaracter ObjPessoaPadraoCaracter() {
        return new PessoaPadraoCaracter();
    }

    private PessoaMetodosDeCadastro ObjPessoaMetodosDeCadastro() {
        return new PessoaMetodosDeCadastro();
    }

    /* O métodos GETs e SETs utilizados para persistir usuários do sistema e clientes */
    public Pessoa getPessoa() {
        if (pessoa == null) {
            pessoa = new Pessoa();
        }
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Fisica getFisica() {
        if (fisica == null) {
            fisica = new Fisica();
        }
        return fisica;
    }

    public void setFisica(Fisica fisica) {
        this.fisica = fisica;
    }

    public Juridica getJuridica() {
        if (juridica == null) {
            juridica = new Juridica();
        }
        return juridica;
    }

    public void setJuridica(Juridica juridica) {
        this.juridica = juridica;
    }

    public Telefone getTelefoneList(int index) {
        if (telefoneList.isEmpty()) {
            telefoneList.add(index, new Telefone());
        } else if (telefoneList.size() < (index+1)) {
            telefoneList.add(index, new Telefone());
        }
        return telefoneList.get(index);
    }

    public User getUser() {
        if (user == null) {
            user = new User();
        }
        return user;
    }
    //--------------------------------------------------------------------

    /*GETS & SETs para o campo temporário de CRMV do medico veterinário*/
    public String getNumCRMV1() {
        return numCRMV1;
    }

    public void setNumCRMV1(String numCRMV1) {
        this.numCRMV1 = numCRMV1;
    }

    public String getNumCRMV2() {
        return numCRMV2;
    }

    public void setNumCRMV2(String numCRMV2) {
        this.numCRMV2 = numCRMV2;
    }

    public String getCRMVcompleto() {
        return numCRMV1 + " " + numCRMV2;
    }
    //-----------------------------------------------------------------------

    public boolean isMudancaCpfCnpj() {
        return mudancaCpfCnpj;
    }

    public void setMudancaCpfCnpj(boolean mudancaCpfCnpj) {
        this.mudancaCpfCnpj = mudancaCpfCnpj;
    }

    //-----------------------------------------------------------------------
    public boolean isCpfOpcional() {
        return cpfOpcional;
    }

    public void setCpfOpcional(boolean cpfOpcional) {
        if (cpfOpcional == false && rgOpcional == false) {
            rgOpcional = true;
        }
        this.cpfOpcional = cpfOpcional;
    }

    public boolean isRgOpcional() {
        return rgOpcional;
    }

    public void setRgOpcional(boolean rgOpcional) {
        if (rgOpcional == false && cpfOpcional == false) {
            cpfOpcional = true;
        }
        this.rgOpcional = rgOpcional;
    }

    //-----------------------------------------------------------------------
    public boolean isViewTelefone2() {
        return viewTelefone2;
    }

    public void setViewTelefone2(boolean viewTelefone2) {
        this.viewTelefone2 = viewTelefone2;
    }

    public boolean isViewTelefone3() {
        if (viewTelefone2 == false) {
            viewTelefone3 = false;
        }
        return viewTelefone3;
    }

    public void setViewTelefone3(boolean viewTelefone3) {
        this.viewTelefone3 = viewTelefone3;
    }
}
