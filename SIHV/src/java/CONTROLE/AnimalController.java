/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLE;

import DAO.GenericoDAO;
import DAO.GenericoDAOImpl;
import MODELO.Animais;
import MODELO.AnimaisId;
import MODELO.Pessoa;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
/**
 *
 * @author thiberius
 */
@ManagedBean(name = "AnimalControle")
@SessionScoped
public class AnimalController {
    
    private final GenericoDAO daoGenerico = new GenericoDAOImpl();
    private static final FacesMessages message = new FacesMessages();
    private Animais animal;
    private AnimaisId animalID;
    private Date data;
    private Pessoa pessoa;
    private List<Pessoa> pessoasBuscadas;
    
    
    
    
    /*Pelagem*/
    private List<String> listaPelagem = daoGenerico.getPelagemNames();

    public List<String> getListaPelagem() {
        return listaPelagem;
    }

    public void setListaPelagem(List<String> listaPelagem) {
        this.listaPelagem = listaPelagem;
    }
    /*Pelagem*/
    
    
    
    
    /*Este método prepara o cadastro de um novo animal,
    ele é invocado quando o formulários para cadastrar
    um novo animal for chamado, ele também realiza
    a limpeza dos campos para cadastrar um animal
    quando o formulário é aberto.*/
    public void prepararAdicionarAnimal(){
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
    }
    
    
    
    
    
    //Método para persistir um novo animal
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

        message.info("Cadastro efetuado!","Administrador cadastrado com sucesso.");
        }
        catch(Exception e){
            message.warn("Erro ao efetuar cadastro!", "Verifique os dados e tente novamente!");
        }
    }
    
    
    
    
//------GETs & SETs---------------------------------------
    public Animais getAnimal(){
        return animal;
    }
    public void setAnimal(Animais animal){
        this.animal = animal;
    }
//----------------------------------------------------------
    public Pessoa getPessoa() {
        return pessoa;
    }
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
//----------------------------------------------------------
    public List<Pessoa> getPessoasBuscadas() {
        return pessoasBuscadas;
    }
    public void setPessoasBuscadas(List<Pessoa> pessoasBuscadas) {
        this.pessoasBuscadas = pessoasBuscadas;
    }
}
