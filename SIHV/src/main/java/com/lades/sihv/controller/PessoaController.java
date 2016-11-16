/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller;
import com.lades.sihv.DAO.GenericoDAO;
import com.lades.sihv.DAO.GenericoDAOImpl;
import com.lades.sihv.model.User;
import com.lades.sihv.model.UserId;
import com.lades.sihv.model.Cliente;
import com.lades.sihv.model.ClienteId;
import com.lades.sihv.model.Pessoa;
import com.lades.sihv.model.Telefone;
import com.lades.sihv.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import com.lades.sihv.BeautyText;
import java.io.IOException;
import javax.faces.context.FacesContext;
/**
 *
 * @author thiberius
 */
@ManagedBean(name = "PessoaControle")
@SessionScoped
public class PessoaController implements Serializable{
    
    private final GenericoDAO daoGenerico = new GenericoDAOImpl();
    private final FacesMessages message = new FacesMessages();
    private Pessoa pessoa;
    private Telefone telefone;
    private User user;
    private UserId userId;
    private Cliente cliente;
    private ClienteId clienteId;
    private Date data;
    private List<Pessoa> pessoasBuscadas;
    private boolean mudancaCpfCnpj = true;
    private Security secure = new Security();
    private BeautyText stringer = new BeautyText();
    
    private boolean dialogMessager = false;
    private String mensageTIPO;
    
    
    
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
        if (dialogMessager){ 
            message.infoDialog("Cadastro efetuado!",mensageTIPO+" cadastrado com sucesso.");
            dialogMessager = false;
        }
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
        if (dialogMessager) {
            message.infoDialog("Cadastro efetuado!","Cliente cadastrado com sucesso.");
            dialogMessager = false;
        }
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
        
        user.setUserNick(user.getUserNick().toLowerCase());
        boolean checkLogin=this.checkExistingLogin();
        if(!checkLogin){
            message.warn("Erro ao efetuar cadastro!", "O nome de Login ou e-mail já existe!");
            pessoa.setEmail("");
        }
               
        if(!checkLogin || !this.prepararSalvarPessoa())
            return false;
        try {
                daoGenerico.save(pessoa);
                telefone.setPessoa(pessoa);
                daoGenerico.save(telefone);

                userId.setFkPessoa(pessoa.getPkPessoa());
                user.setId(userId);
                user.setUserSenha(secure.encrypter(user.getUserSenha()));
                
                if (!"".equals(numCRMV1) && !"".equals(numCRMV2)) {
                    user.setCrmvMatricula(numCRMV1+" "+numCRMV2);
                }
                user.setUserTipo(tipoUSER);
                daoGenerico.save(user);

                this.mensageTIPO = mensageTIPO;
                dialogMessager = true;
                switch (tipoUSER){
                    case "MEDICO":
                        redirecionar("/SIHV/faces/SIHV_Telas_Adm/cadUSER_medico.xhtml");
                        break;
                    case "ALUNO":
                        redirecionar("/SIHV/faces/SIHV_Telas_Adm/cadUSER_aluno.xhtml");
                        break;
                    case "TÉCNICO":
                        redirecionar("/SIHV/faces/SIHV_Telas_Adm/cadUSER_tecnico.xhtml");
                        break;
                    default:
                        break;
                }
                //message.info("Cadastro efetuado!",mensageTIPO+" cadastrado com sucesso.");
            } catch (Exception e) {
                message.warn("Erro ao efetuar cadastro!", e.getMessage()+"\nVerifique os dados e tente novamente!");
            }
        return true;
    }
      
    public boolean prepararSalvarPessoa(){
        boolean checkCPF=secure.checkCPF(pessoa.getCpf());
        boolean checkExCPF=this.checkExistingCPF(pessoa.getCpf());
        if(!checkCPF){
            message.warn("Erro ao efetuar cadastro!", "CPF Inválido!");
            pessoa.setCpf("");
        }        
                
        if(!checkExCPF){
            message.warn("Erro ao efetuar cadastro!", "O CPF informado já existe!");
            pessoa.setCpf("");
        }
        if(!checkExCPF || !checkCPF)
            return false;
        try{
        pessoa.setNome(stringer.Captalizador(pessoa.getNome()));
        pessoa.setEmail(pessoa.getEmail().toLowerCase());
        pessoa.setCidade(stringer.Captalizador(pessoa.getCidade()));
        pessoa.setBairro(stringer.Captalizador(pessoa.getBairro()));
        pessoa.setComplemento(stringer.firstCapital(pessoa.getComplemento()));
        pessoa.setLogra(stringer.firstCapital(pessoa.getLogra()));
        pessoa.setCadDataHora(data);return true;}catch(Exception ex){return false;}
    }
    
    public boolean checkExistingCPF(String cpf){
        try{
            List<Pessoa>checkLogin = (List<Pessoa>)daoGenerico.list("select p from Pessoa p where p.cpf='"+cpf+"'");
            return checkLogin.isEmpty();}catch(Exception ex){return true;}
    }
    
    public boolean checkExistingLogin(){
        try{List<Pessoa>checkLogin = (List<Pessoa>)daoGenerico.list("select p from Pessoa p, user u where p.email = '"+pessoa.getEmail()+"' or u.userNick='"+user.getUserNick()+"'");
        return checkLogin.isEmpty();}catch(Exception ex){return true;}
    }
    
    
    
    /*O método realiza a persistência de um novo cliente*/
    public void adicionarCLIENTE(){
        addCLIENTE();
    }
    private boolean addCLIENTE(){
        try{
            if (this.prepararSalvarPessoa()) {
                daoGenerico.save(pessoa);

                telefone.setPessoa(pessoa);
                daoGenerico.save(telefone);

                clienteId.setFkPessoa(pessoa.getPkPessoa());
                cliente.setId(clienteId);
                daoGenerico.save(cliente);

                //message.info("Cadastro efetuado!","Cliente cadastrado com sucesso.");
                dialogMessager = true;
                redirecionar("/SIHV/faces/SIHV_Telas_Adm/ADM_cad_cliente.xhtml");
                
                return true;
            }else
                throw new Exception();
        }
        catch (Exception e){
            pessoa.setCpf("");
            message.warn("Erro ao efetuar cadastro!", "Verifique os dados e tente novamente!");
            return false;
        }
    }
    
    
    public void redirecionar(String url) throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect(url);
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
