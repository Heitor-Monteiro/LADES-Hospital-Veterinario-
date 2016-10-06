/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLE;

import DAO.GenericoDAO;
import DAO.GenericoDAOImpl;
import MODELO.Animais;
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
@ManagedBean(name = "AnimalControle")
@SessionScoped
public class AnimalController {
    
    private final GenericoDAO daoGenerico = new GenericoDAOImpl();
    private Animais animal;
    private DataModel listarAnimal;
    
    /*Pelagem*/
    
    private List<String> listaPelagem = daoGenerico.getPelagemNames();

    public List<String> getListaPelagem() {
        return listaPelagem;
    }

    public void setListaPelagem(List<String> listaPelagem) {
        this.listaPelagem = listaPelagem;
    }

    
    /*Pelagem*/
    
    public DataModel getListarAnimal(){
        List<Animais> lista = daoGenerico.list("sqlHQL");
        listarAnimal = new ListDataModel(lista);
        return listarAnimal;
    }
    
    public Animais getAnimal(){
        return animal;
    }
    
    public void setAnimal(Animais animal){
        this.animal = animal;
    }
    
    public void prepararAdicionarAnimal(){
        animal = new Animais();
    }
    
    public String prepararAlterarAnimal(){
        animal = (Animais)(listarAnimal.getRowData());
        return "gerenciarLivro";
    }
    
    public String excluirAnimal(){
        Animais animalTemp = (Animais)(listarAnimal.getRowData());
        daoGenerico.remove(animalTemp);
        return "index";
    }
    
    public void adicionarANIMAL(){
        daoGenerico.save(animal);
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Telefone cadastrado com sucesso."));
    }
    
    public String alterarAnimal(){
        daoGenerico.update(animal);
        return "index";
    }
    
}
