/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLE;

import DAO.GenericoDAO;
import DAO.GenericoDAOImpl;
import MODELO.Adm;
import MODELO.AdmId;
import MODELO.Cliente;
import MODELO.ClienteId;
import MODELO.Pessoa;
import MODELO.Telefone;
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
    private Adm adm;
    private AdmId admId;
    private Cliente cliente;
    private ClienteId clienteId;
    private Date data;
    private List<Pessoa> pessoasBuscadas;
    
    
    
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
        adm = new Adm();
        admId = new AdmId();
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
        try {
            pessoa.setCadDataHora(data);
            daoGenerico.save(pessoa);
            
            telefone.setPessoa(pessoa);
            daoGenerico.save(telefone);
            
            admId.setFkPessoa(pessoa.getPkPessoa());
            adm.setId(admId);
            if (!"".equals(numCRMV1) && !"".equals(numCRMV2)) {
                adm.setCrmvMatricula(numCRMV1+" "+numCRMV2);
            }
            adm.setTipoUser(tipoUSER);
            daoGenerico.save(adm);
            
            message.info("Cadastro efetuado!",mensageTIPO+" cadastrado com sucesso.");
        } catch (Exception e) {
            message.warn("Erro ao efetuar cadastro!", "Verifique os dados e tente novamente!");
        }
    } 
    
    
    
    
    
    /*O método realiza a persistência de um novo cliente*/
    public void adicionarCLIENTE(){
        try{
            pessoa.setCadDataHora(data);
            daoGenerico.save(pessoa);

            telefone.setPessoa(pessoa);
            daoGenerico.save(telefone);

            clienteId.setFkPessoa(pessoa.getPkPessoa());
            cliente.setId(clienteId);
            daoGenerico.save(cliente);

            message.info("Cadastro efetuado!","Cliente cadastrado com sucesso.");
        }
        catch (Exception e)
        {
            message.warn("Erro ao efetuar cadastro!", "Verifique os dados e tente novamente!");
        }
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
    
    public Adm getADM(){
        return adm;
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
