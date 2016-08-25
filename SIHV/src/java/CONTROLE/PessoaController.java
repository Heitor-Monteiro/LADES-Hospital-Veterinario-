/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLE;

import DAO.PessoaDao;
import DAO.PessoaDaoImp;
import DAO.TelefoneDao;
import DAO.TelefoneDaoImp;
import DAO.AdmDao;
import DAO.AdmDaoImp;
import DAO.ClienteDao;
import DAO.ClienteDaoImp;
import MODELO.Adm;
import MODELO.AdmId;
import MODELO.Cliente;
import MODELO.ClienteId;
import MODELO.Pessoa;
import MODELO.Telefone;
import CONTROLE.RenderedController;
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
    
    private Pessoa pessoa;
    private DataModel listarPessoas;
    
    private Telefone telefone;
    private Adm adm;
    private AdmId admId;
    private Cliente cliente;
    private ClienteId clienteId;
    private Date data;
    
    private List<Pessoa> pessoasBuscadas;

    public PessoaController() {
    }
    
    public DataModel getListarPessoas(){
        List<Pessoa> lista = new PessoaDaoImp().list();
        listarPessoas = new ListDataModel(lista);
        return listarPessoas;
    }
    
    
    public List<Pessoa> getPessoasBuscadas(){
        return pessoasBuscadas;
    }
    
    public void prepararPesquisarPessoa(){
        pessoasBuscadas = new ArrayList();
    }
    
    public Pessoa getPessoa(){
        return pessoa;
    }
    
    public void setPessoa(Pessoa pessoa){
        this.pessoa = pessoa;
    }
    
//    public void prepararAdicionarPessoa(){
//        pessoa = new Pessoa();
//        telefone = new Telefone();
//    }
    
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
    
    public String prepararAlterarPessoa(){
        pessoa = (Pessoa)(listarPessoas.getRowData());
        return "gerenciarLivro";
    }
    
    public String excluirPessoa(){
        Pessoa pessoaTemp = (Pessoa)(listarPessoas.getRowData());
        PessoaDao dao = new PessoaDaoImp();
        dao.remove(pessoaTemp);
        return "index";
    }
    
//    public String adicionarPessoa(){
//        PessoaDao dao = new PessoaDaoImp();
//        dao.save(pessoa);
//        return "index";
//    }
    
//    public void adicionarPessoa2(){
//        PessoaDao dao = new PessoaDaoImp();
//        dao.save(pessoa);
//        
//        telefone.setPessoa(pessoa);
//        
//        TelefoneDao dao2 = new TelefoneDaoImp();
//        dao2.save(telefone);
//        
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(null, new FacesMessage("Cadastro realizado com sucesso."));
//    }
    
    public void adicionarADM(){
        PessoaDao dao = new PessoaDaoImp();
        pessoa.setCadDataHora(data);
        dao.save(pessoa);
        
        telefone.setPessoa(pessoa);
        TelefoneDao dao2 = new TelefoneDaoImp();
        dao2.save(telefone);
        
        admId.setFkPessoa(pessoa.getPkPessoa());
        adm.setId(admId);
        AdmDao dao3 = new AdmDaoImp();
        dao3.save(adm);
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Cadastro realizado com sucesso."));
    }
    
    public void adicionarCLIENTE(){
        PessoaDao dao = new PessoaDaoImp();
        pessoa.setCadDataHora(data);
        dao.save(pessoa);
        
        telefone.setPessoa(pessoa);
        TelefoneDao dao2 = new TelefoneDaoImp();
        dao2.save(telefone);
        
        clienteId.setFkPessoa(pessoa.getPkPessoa());
        cliente.setId(clienteId);
        ClienteDao dao3 = new ClienteDaoImp();
        dao3.save(cliente);
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Cadastro realizado com sucesso."));
    }
    
    public String alterarPessoa(){
        PessoaDao dao = new PessoaDaoImp();
        dao.update(pessoa);
        return "index";
    }
    
    public Telefone getTelefone(){
        return telefone;
    }
    
    public Adm getADM(){
        return adm;
    }
    
}
