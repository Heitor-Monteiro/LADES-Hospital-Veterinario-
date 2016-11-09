/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller;

import com.lades.sihv.BeautyText;
import com.lades.sihv.DAO.GenericoDAO;
import com.lades.sihv.DAO.GenericoDAOImpl;
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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
/**
 *
 * @author thiberius
 */
@ManagedBean(name = "PessoaControle")
@SessionScoped
public class PessoaController implements Serializable{
    
    private final GenericoDAO daoGenerico = new GenericoDAOImpl();
    private static final FacesMessages message = new FacesMessages();
    private Pessoa pessoa;
    private Telefone telefone;
    private User user;
    private UserId userId;
    private Cliente cliente;
    private ClienteId clienteId;
    private Date data;
    private List<Pessoa> pessoasBuscadas;
    private boolean mudancaCpfCnpj = true;
    
    
    
    /*Os dois atributos serão utilizado para
    a concatenação do CRMV do medico
    veterinário*/
    private String numCRMV1,numCRMV2;
    
    
    
    
    /*O método prepara o cadastro de um
    usuário do sistema, ele deve ser
    chamado quando a pagina de cadastro
    for aberta.*/
    public void prepararAdicionarUSER(){
        pessoa = new Pessoa();
        telefone = new Telefone();
        user = new User();
        userId =  new UserId();
        data = new Date();
        numCRMV1 = "";
        numCRMV2 = "";
    }
    
    
    
    
    /*O método prepara o cadastro de um
    cliente, ele deve ser chamado quando
    a pagina de cadastro for aberta.*/
    public void prepararAdicionarCliente(){
        pessoa = new Pessoa();
        telefone = new Telefone();
        cliente = new Cliente();
        clienteId = new ClienteId();
        data = new Date();
    }
    
    
    
    
    /*O método realiza a persistência de usuários do sistema, 
    ele pode cadastrar medico veterinário, aluno bolsista e 
    funcionário comum, informando o tipo(Medico,Aluno,Tecnico) 
    no primeiro parâmetro, e no segundo parâmetro será 
    informado o tipo, mas, em um contexto diferente. 
    Exemplo: Medico veterinário; Aluno bolsista; 
    Funcionário/Técnico. Esses nomes complementaram a 
    frase de cadastro realizado com sucesso.*/
    public void adicionarUSER(String tipoUSER, String mensageTIPO){
        addUser(tipoUSER,mensageTIPO);
    }
    private boolean addUser(String tipoUSER, String mensageTIPO){
        boolean checkLogin=this.checkExistingLogin();
        boolean checkCPF=com.lades.sihv.Security.checkCPF(pessoa.getCpf());
        if(!checkLogin){
            message.warn("Erro ao efetuar cadastro!", "O nome de Login já existe!");
            pessoa.setEmail("");
        }
        
        if(!checkCPF){
            message.warn("Erro ao efetuar cadastro!", "CPF Inválido!");
            pessoa.setCpf("");
        }
        
        if(!checkCPF || !checkLogin)
            return false;
        try {
                pessoa.setCadDataHora(data);
                daoGenerico.save(pessoa);

                telefone.setPessoa(pessoa);
                daoGenerico.save(telefone);

                userId.setFkPessoa(pessoa.getPkPessoa());
                user.setId(userId);
                if (!"".equals(numCRMV1) && !"".equals(numCRMV2)) {
                    user.setCrmvMatricula(numCRMV1+" "+numCRMV2);
                }
                user.setUserTipo(tipoUSER);
                user.setUserSenha(Security.getMD5(user.getUserSenha()));
                daoGenerico.save(user);

                message.info("Cadastro efetuado!",mensageTIPO+" cadastrado com sucesso.");
            } catch (Exception e) {
                message.warn("Erro ao efetuar cadastro!", "Verifique os dados e tente novamente!");
            }
        return true;
    }
        
    public boolean checkExistingLogin(){
        List<Pessoa>checkLogin = (List<Pessoa>)daoGenerico.list("select p from Pessoa p where p.email = '"+pessoa.getEmail()+"'");
        try{
            System.out.print(checkLogin.get(0).getEmail());
        }
        catch(Exception ex){
            return true;
        }
        return false;
    }
    
    
    
    /*O método realiza a persistência de um novo cliente*/
    public void adicionarCLIENTE(){
        addCLIENTE();
    }
    private boolean addCLIENTE(){
        boolean resposta;
        try{
            resposta = com.lades.sihv.Security.checkCPF(pessoa.getCpf());
            if (resposta) {
                pessoa.setCadDataHora(data);
                daoGenerico.save(pessoa);

                telefone.setPessoa(pessoa);
                daoGenerico.save(telefone);

                clienteId.setFkPessoa(pessoa.getPkPessoa());
                cliente.setId(clienteId);
                daoGenerico.save(cliente);

                message.info("Cadastro efetuado!","Cliente cadastrado com sucesso.");
            }else{
                pessoa.setCpf("");
                message.warn("Erro ao efetuar cadastro!", "CPF invalido!");
            }
        }
        catch (Exception e){
            pessoa.setCpf("");
            message.warn("Erro ao efetuar cadastro!", "CPF já cadastrado!");
            resposta =  false;
        }
        return resposta;
    }
    
    
    
    
    
    /*O métodos GETs e SETs utilizados para 
    persistir usuários do sistema e clientes*/
    public Pessoa getPessoa(){
        return pessoa;
    }
    
    public void setPessoa(Pessoa pessoa){
        this.pessoa = pessoa;
    }
    
    public List<Pessoa> getPessoasBuscadas() {
        return pessoasBuscadas;
    }

    public void setPessoasBuscadas(List<Pessoa> pessoasBuscadas) {
        this.pessoasBuscadas = pessoasBuscadas;
    }
    
    public Telefone getTelefone(){
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
        return numCRMV1+" "+numCRMV2;
    }
    //-----------------------------------------------------------------------
    
    public boolean isMudancaCpfCnpj() {
        return mudancaCpfCnpj;
    }

    public void setMudancaCpfCnpj(boolean mudancaCpfCnpj) {
        this.mudancaCpfCnpj = mudancaCpfCnpj;
    }
    
    
    
    //métodos que não estão sendo utilizado ainda
    public String alterarPessoa(){
        daoGenerico.update(pessoa);
        return "index";
    }
    
    public DataModel getListaPessoaDataModel(){
        DataModel listarPessoas = new ListDataModel(pessoasBuscadas);
        return listarPessoas;
    }
    
    public String prepararAlterarPessoa(){
        pessoa = (Pessoa)(getListaPessoaDataModel().getRowData());
        return "gerenciarLivro";
    }
    
    public String excluirPessoa(){
        Pessoa pessoaTemp = (Pessoa)(getListaPessoaDataModel().getRowData());
        daoGenerico.remove(pessoaTemp);
        return "index";
    }
    //---------------------------------------------------------------
}
