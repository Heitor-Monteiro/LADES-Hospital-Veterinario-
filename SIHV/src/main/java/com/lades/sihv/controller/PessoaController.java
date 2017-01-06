/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller;

import com.lades.sihv.DAO.GenericoDAO;
import com.lades.sihv.model.User;
import com.lades.sihv.model.UserId;
import com.lades.sihv.model.Cliente;
import com.lades.sihv.model.ClienteId;
import com.lades.sihv.model.Pessoa;
import com.lades.sihv.model.Telefone;
import com.lades.sihv.Security;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import com.lades.sihv.BeautyText;
import com.lades.sihv.Tools;
import com.lades.sihv.model.Fisica;
import com.lades.sihv.model.FisicaId;
import com.lades.sihv.model.Juridica;
import com.lades.sihv.model.JuridicaId;

/**
 *
 * @author thiberius
 */
public class PessoaController implements Serializable {

    private GenericoDAO daoGenerico;
    private FacesMessages message;
    private Pessoa pessoa;
    private Fisica fisica;
    private FisicaId fisicaId;
    private JuridicaId juridicaId;
    private Juridica juridica;
    private Telefone telefone;
    private Telefone celular;
    private User user;
    private UserId userId;
    private Cliente cliente;
    private ClienteId clienteId;
    private Date data;
    private Tools tools;
    private final Security secure = new Security();
    private final BeautyText stringer = new BeautyText();
    private boolean mudancaCpfCnpj = true;

    /*Os método e variáveis abaixo são
    utilizados para controlar a visibilidade
    de campos para cadastro de clientes e
    usuários do sistema*/
    private String tipoPessoa;

    public boolean getTipoPessoa() {
        boolean var;
        if (this.tipoPessoa.equals("cliente")) {
            var = true;
        } else {
            var = false;
        }
        return var;
    }
    //--------------------------------------------------

    /*Os dois atributos serão utilizado para
    a concatenação do CRMV do medico
    veterinário*/
    private String numCRMV1, numCRMV2;

    private PessoaController() {
    }

    public PessoaController(GenericoDAO daoGenerico, FacesMessages message, Tools tools) {
        this.daoGenerico = daoGenerico;
        this.message = message;
        this.tools = tools;
    }

    /*O método prepara o cadastro de um
    usuário do sistema, ele deve ser
    chamado quando a pagina de cadastro
    for aberta.*/
    public void prepararAdicionarUSER() {
        pessoa = new Pessoa();
        pessoa.setExclusaoLogica(false);
        fisica = new Fisica();
        fisicaId = new FisicaId();
        telefone = new Telefone();
        celular = new Telefone();
        user = new User();
        userId = new UserId();
        data = new Date();
        numCRMV1 = "";
        numCRMV2 = "";
        tipoPessoa = "user";
    }

    /*O método prepara o cadastro de um
    cliente, ele deve ser chamado quando
    a pagina de cadastro for aberta.*/
    public void prepararAdicionarCliente() {
        pessoa = new Pessoa();
        pessoa.setExclusaoLogica(false);
        telefone = new Telefone();
        celular = new Telefone();
        cliente = new Cliente();
        clienteId = new ClienteId();
        fisica = new Fisica();
        fisicaId = new FisicaId();
        juridica = new Juridica();
        juridicaId = new JuridicaId();
        data = new Date();
        tipoPessoa = "cliente";
    }

    /*O método realiza a persistência de usuários do sistema, 
    ele pode cadastrar medico veterinário, aluno bolsista e 
    funcionário comum, informando o tipo(Medico,Aluno,Tecnico) 
    no primeiro parâmetro, e no segundo parâmetro será 
    informado o tipo, mas, em um contexto diferente. 
    Exemplo: Medico veterinário; Aluno bolsista; 
    Funcionário/Técnico. Esses nomes complementaram a 
    frase de cadastro realizado com sucesso.*/
    public void adicionarUSER(String tipoUSER, String mensageTIPO) {
        addUser(tipoUSER, mensageTIPO);
    }

    private boolean addUser(String tipoUSER, String mensageTIPO) {

        user.setUserNick(user.getUserNick().toLowerCase());
        boolean checkLogin = this.checkExistingLogin();
        if (!checkLogin) {
            message.warn("Erro ao efetuar cadastro!", "O nome de Login ou e-mail já existe!");
            pessoa.setEmail("");
        }

        if (!checkLogin || !this.prepararSalvarPessoa()) {
            return false;
        }
        try {
            daoGenerico.save(pessoa);
            fisicaId.setFkPessoa(pessoa.getPkPessoa());
            fisica.setId(fisicaId);
            daoGenerico.save(fisica);
            addTelefones();
            userId.setFkPessoa(pessoa.getPkPessoa());
            user.setId(userId);
            user.setUserSenha(secure.encrypter(user.getUserSenha()));

            if (!"".equals(numCRMV1) && !"".equals(numCRMV2)) {
                user.setCrmvMatricula(numCRMV1 + " " + numCRMV2);
            }
            user.setUserTipo(tipoUSER);
            daoGenerico.save(user);

            String userTIPO = "";
            switch (tipoUSER) {
                case "MEDICO":
                    userTIPO = "cadUSER_medico";
                    break;
                case "ALUNO":
                    userTIPO = "cadUSER_aluno";
                    break;
                case "TÉCNICO":
                    userTIPO = "cadUSER_tecnico";
                    break;
                default:
                    break;
            }
            message.setTextoDialog("Cadastro efetuado!",
                    mensageTIPO + " cadastrado com sucesso.",
                    "/SIHV_Telas_Adm/" + userTIPO);
        } catch (Exception e) {
            message.warn("Erro ao efetuar cadastro!", e.getMessage() + "\nVerifique os dados e tente novamente!");
        }
        return true;
    }

    public boolean prepararSalvarPessoa() {
        if (mudancaCpfCnpj) {
            boolean checkCPF = secure.checkCPF(pessoa.getCpfCnpj());
            boolean checkExCPF = this.checkExistingCPF(pessoa.getCpfCnpj());
            if (!checkCPF) {
                message.warn("Erro ao efetuar cadastro!", "CPF Inválido!");
                pessoa.setCpfCnpj("");
            }

            if (!checkExCPF) {
                message.warn("Erro ao efetuar cadastro!", "O CPF informado já existe!");
                pessoa.setCpfCnpj("");
            }
            if (!checkExCPF || !checkCPF) {
                return false;
            }
        } else {
            boolean checkCnpj = secure.checkCNPJ(pessoa.getCpfCnpj());
            boolean checkExCnpj = this.checkExistingCPF(pessoa.getCpfCnpj());
            if (!checkCnpj) {
                message.warn("Erro ao efetuar cadastro!", "CNPJ Inválido!");
                pessoa.setCpfCnpj("");
            }

            if (!checkExCnpj) {
                message.warn("Erro ao efetuar cadastro!", "O CNPJ informado já existe!");
                pessoa.setCpfCnpj("");
            }
            if (!checkExCnpj || !checkCnpj) {
                return false;
            }
        }
        try {
            pessoa.setNome(stringer.Captalizador(pessoa.getNome()));
            pessoa.setEmail(pessoa.getEmail().toLowerCase());
            pessoa.setCidade(stringer.Captalizador(pessoa.getCidade()));
            pessoa.setBairro(stringer.Captalizador(pessoa.getBairro()));
            pessoa.setComplemento(stringer.firstCapital(pessoa.getComplemento()));
            pessoa.setLogra(stringer.firstCapital(pessoa.getLogra()));
            pessoa.setCadDataHora(data);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean checkExistingCPF(String cpf) {
        try {
            List<Pessoa> checkLogin = (List<Pessoa>) daoGenerico.list("select p from Pessoa p where p.cpfCnpj='" + cpf + "'");
            return checkLogin.isEmpty();
        } catch (Exception ex) {
            return true;
        }
    }

    public boolean checkExistingLogin() {
        try {
            List<Pessoa> checkLogin = (List<Pessoa>) daoGenerico.list("select p from Pessoa p, user u where p.email = '" + pessoa.getEmail() + "' or u.userNick='" + user.getUserNick() + "'");
            return checkLogin.isEmpty();
        } catch (Exception ex) {
            return true;
        }
    }

    /*O método realiza a persistência de um novo cliente*/
    public void adicionarCLIENTE() {
        addCLIENTE();
    }

    private boolean addCLIENTE() {
        try {
            if ("".equals(pessoa.getEmail())) {
                pessoa.setEmail("naoInformado@naoInformado.com");
                System.out.println("email-não-informado===========================");
            }

            if (this.prepararSalvarPessoa()) {
                daoGenerico.save(pessoa);
                addTelefones();
                if (mudancaCpfCnpj) {
                    fisicaId.setFkPessoa(pessoa.getPkPessoa());
                    fisica.setId(fisicaId);
                    daoGenerico.save(fisica);
                } else {
                    if (juridica.getTipoPessoaJuridica().equals("")) {
                        juridica.setTipoPessoaJuridica("Não informado");
                    }

                    juridicaId.setFkPessoa(pessoa.getPkPessoa());
                    juridica.setId(juridicaId);
                    daoGenerico.save(juridica);
                }
                clienteId.setFkPessoa(pessoa.getPkPessoa());
                cliente.setId(clienteId);
                daoGenerico.save(cliente);

                //Bloqueio do botão back do Wizard PrimeFAces
                tools.blockBackWizad();

                message.info("Cadastro efetuado!", "Cliente cadastrado com sucesso.");

                //Habilitando visibilidade do botão para impressão
                this.tools.setShowButtonPrint(true);

                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            pessoa.setCpfCnpj("");
            message.warn("Erro ao efetuar cadastro!", "Verifique os dados e tente novamente!");
            return false;
        }
    }

    /*O método é utilizado para controlar
    a persistência das variáveis telefone e celular*/
    private void addTelefones() {
        if (!"".equals(telefone.getNumero())) {
            telefone.setPessoa(pessoa);
            daoGenerico.save(telefone);
        }
        celular.setPessoa(pessoa);
        daoGenerico.save(celular);
    }

    /*O métodos GETs e SETs utilizados para 
    persistir usuários do sistema e clientes*/
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Fisica getFisica() {
        return fisica;
    }

    public void setFisica(Fisica fisica) {
        this.fisica = fisica;
    }

    public Juridica getJuridica() {
        return juridica;
    }

    public void setJuridica(Juridica juridica) {
        this.juridica = juridica;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public User getUser() {
        return user;
    }
    //--------------------------------------------------------------------

    /*GETS & SETs para o campo temporário
    de CRMV do medico veterinário*/
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

    //métodos que não estão sendo utilizado ainda
    public String alterarPessoa() {
        daoGenerico.update(pessoa);
        return "index";
    }

    public Telefone getCelular() {
        return celular;
    }

    public void setCelular(Telefone celular) {
        this.celular = celular;
    }
}
