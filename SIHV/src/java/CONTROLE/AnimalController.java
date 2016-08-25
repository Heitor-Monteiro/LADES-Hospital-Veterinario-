/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLE;

import DAO.AnimalDao;
import DAO.AnimalDaoImp;
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
    
    private Animais animal;
    private DataModel listarAnimal;
    
    
    public DataModel getListarAnimal(){
        List<Animais> lista = new AnimalDaoImp().list();
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
        AnimalDao dao = new AnimalDaoImp();
        dao.remove(animalTemp);
        return "index";
    }
    
    public void adicionarANIMAL(){
        AnimalDao dao = new AnimalDaoImp();
        dao.save(animal);
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Telefone cadastrado com sucesso."));
    }
    
    public String alterarAnimal(){
        AnimalDao dao = new AnimalDaoImp();
        dao.update(animal);
        return "index";
    }
    
}
