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
import MODELO.Animais;
import MODELO.AnimaisId;
import java.io.Serializable;
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

public class PessoaController implements Serializable{
    
    private final GenericoDAO daoGenerico = new GenericoDAOImpl();
    private static final FacesMessages message = new FacesMessages();
    private String itenPesquisa,textoPesquisa;
    private boolean showDataTable;
    private Pessoa pessoa;
    private Telefone telefone;
    private Adm adm;
    private AdmId admId;
    private Cliente cliente;
    private ClienteId clienteId;
    private Animais animal;
    private AnimaisId animalID;
    private Date data;
    private List<Pessoa> pessoasBuscadas;

    public List<Pessoa> getPessoasBuscadas() {
        return pessoasBuscadas;
    }

    
    public void setPessoasBuscadas(List<Pessoa> pessoasBuscadas) {
        this.pessoasBuscadas = pessoasBuscadas;
    }
    

    public boolean isShowDataTable() {
        return showDataTable;
    }

    
    public void setShowDataTable(boolean showDataTable) {
        this.showDataTable = showDataTable;
    }

    
    
    public String getItenPesquisa(){return itenPesquisa;}

    public void setItenPesquisa(String itenPesquisa){this.itenPesquisa = itenPesquisa;}

    public String getTextoPesquisa(){return textoPesquisa;}

    public void setTextoPesquisa(String textoPesquisa){this.textoPesquisa = textoPesquisa;}

    
    
    
    
    
    public List<Pessoa> getListarPessoas(){   
        this.showDataTable=false;
        pessoasBuscadas = new GenericoDAOImpl().listIdName(itenPesquisa, textoPesquisa);
        if(pessoasBuscadas.isEmpty()){
            message.warn("Erro ao listar!","Item não encontrado.");
        }else{
            this.setShowDataTable(true);
        }
        return pessoasBuscadas;
    }
    
    
    
    public DataModel getListaPessoaDataModel(){
        DataModel listarPessoas = new ListDataModel(pessoasBuscadas);
        return listarPessoas;
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
    
    
    public void prepararAdicionarAnimal(){
        itenPesquisa = "";
        textoPesquisa = "";
        animal = new Animais();
        animal.setEspecie("");
        animal.setIdadeAtual(0);
        animal.setNome("");
        animal.setPelagem("");
        animal.setPeso(0);
        animal.setRaca("");
        animal.setSexo("");
        animalID = new AnimaisId();
        data = new Date();
        if(pessoasBuscadas != null){
            pessoasBuscadas.clear();
            setShowDataTable(false);
        }
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
       
        try{
            pessoa.setCadDataHora(data);
        
        daoGenerico.save(pessoa);
        
        telefone.setPessoa(pessoa);
        daoGenerico.save(telefone);
        
        admId.setFkPessoa(pessoa.getPkPessoa());
        adm.setId(admId);
        daoGenerico.save(adm);
        
        message.info("Cadastro efetuado!","Administrador cadastrado com sucesso.");
        }
        catch(Exception e){
            message.warn("Erro ao efetuar cadastro!", "Verifique os dados e tente novamente!");
        }
    }    
    
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
    
    
    public void adicionarANIMAL(){
        try{
        String clientePK;
        List<Object> lista;
        
        lista = daoGenerico.list("select c.id.pkCliente from Cliente c, Pessoa p where c.id.fkPessoa="+pessoa.getPkPessoa()+" and p.pkPessoa="+pessoa.getPkPessoa());
        clientePK = ""+lista.get(0);
        
        animalID.setClienteFkPessoa(pessoa.getPkPessoa());
        animalID.setClienteFkCliente(Integer.parseInt(clientePK));
        animal.setId(animalID);
        animal.setCadDataHora(data);
        daoGenerico.save(animal);

        message.info("Cadastro efetuado!","Animal cadastrado com sucesso.");
        }
        catch(Exception e){
            message.warn("Erro ao efetuar cadastro!", "Verifique os dados e tente novamente!");
        }
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
    
    //----------------------------------------------------------------

    public Animais getAnimal() {
        return animal;
    }

    public void setAnimal(Animais animal) {
        this.animal = animal;
    }
    
    
    
    
    
}
