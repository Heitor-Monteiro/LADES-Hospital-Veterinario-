/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLE;

import DAO.GenericoDAO;
import DAO.GenericoDAOImpl;
//import DAO.ClienteDao;
//import DAO.ClienteDaoImp;
import MODELO.Adm;
import MODELO.AdmId;
import MODELO.Cliente;
import MODELO.ClienteId;
import MODELO.Pessoa;
import MODELO.Telefone;
//import CONTROLE.RenderedController;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
/**
 *
 * @author thiberius
 */
@ManagedBean(name = "PessoaControle")
@SessionScoped
public class PessoaController {
    
    private final GenericoDAO daoGenerico = new GenericoDAOImpl();
    private List<Pessoa> pessoasBuscadas = new ArrayList<>();
    private CamposController objControCampos;
    private Pessoa pessoa;
    private Telefone telefone;
    private Adm adm;
    private AdmId admId;
    private Cliente cliente;
    private ClienteId clienteId;
    private Date data;
    
    
    
    public List<Pessoa> getListarPessoas(String sqlHQL){
        pessoasBuscadas = new GenericoDAOImpl().list(sqlHQL);
        return pessoasBuscadas;
    }
    
    
    
    public DataModel getListaPessoaDataModel(){
        DataModel listarPessoas = new ListDataModel(pessoasBuscadas);
        return listarPessoas;
    }
    
    
    public void PesquisarOcorrencia(){
        if(null != objControCampos.getItenPesquisa() ) 
           switch (objControCampos.getItenPesquisa()) {
            case "cpf":
                getListarPessoas("sqlHQL");
                break;
            default:
                NotificacaoTela("Item não encontrado");
                break;
        }
    }
    
    public Pessoa getPessoa(){
        return pessoa;
    }
    
    public void setPessoa(Pessoa pessoa){
        this.pessoa = pessoa;
    }
    
    
    public void prepararAdicionarADM(){
        pessoa = new Pessoa();
        telefone = new Telefone();
        adm = new Adm();
        admId = new AdmId();
        data = new Date();
    }
    
    public void prepararAdicionarCliente(){
        pessoa = new Pessoa();
        telefone = new Telefone();
        cliente = new Cliente();
        clienteId = new ClienteId();
        data = new Date();
    }
    
    public CamposController getObjControCampos(){
        return objControCampos;
    }
    
    public void prepararPesquisaPessoa(){
        objControCampos = new CamposController();
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
    
    
    public void adicionarADM(){
        pessoa.setCadDataHora(data);
        daoGenerico.save(pessoa);
        
        telefone.setPessoa(pessoa);
        daoGenerico.save(telefone);
        
        admId.setFkPessoa(pessoa.getPkPessoa());
        adm.setId(admId);
        daoGenerico.save(adm);
        
        NotificacaoTela("Cadastro realizado com sucesso.");
    }    
    
    public void adicionarCLIENTE(){
        pessoa.setCadDataHora(data);
        daoGenerico.save(pessoa);
        
        telefone.setPessoa(pessoa);
        daoGenerico.save(telefone);
        
        clienteId.setFkPessoa(pessoa.getPkPessoa());
        cliente.setId(clienteId);
        daoGenerico.save(cliente);
        
        NotificacaoTela("Cadastro realizado com sucesso.");
    }
    
    
    //Método para exibir notificações em tela
    private void NotificacaoTela(String texto){
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(texto));
    }
    
    public String alterarPessoa(){
        daoGenerico.update(pessoa);
        return "index";
    }
    
    public Telefone getTelefone(){
        return telefone;
    }
    
    public Adm getADM(){
        return adm;
    }
    
}
