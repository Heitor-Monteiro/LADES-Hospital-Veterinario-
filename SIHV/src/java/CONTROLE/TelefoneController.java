/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLE;

import DAO.GenericoDAO;
import DAO.GenericoDAOImpl;
import MODELO.Telefone;
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
@ManagedBean(name = "TelefoneControle")
@SessionScoped
public class TelefoneController {
    
    private final GenericoDAO daoGenerico = new GenericoDAOImpl();
    private Telefone telefone;
    private DataModel listarTelefone;
    
    
    public DataModel getListarTelefone(){
        List<Telefone> lista = daoGenerico.list("hql");
        listarTelefone = new ListDataModel(lista);
        return listarTelefone;
    }
    
    public Telefone getTelefone(){
        return telefone;
    }
    
    public void setTelefone(Telefone telefone){
        this.telefone = telefone;
    }
    
    public void prepararAdicionarTelefone(){
        telefone = new Telefone();
    }
    
    public String prepararAlterarTelefone(){
        telefone = (Telefone)(listarTelefone.getRowData());
        return "gerenciarLivro";
    }
    
    public String excluirTelefone(){
        Telefone telefoneTemp = (Telefone)(listarTelefone.getRowData());
        daoGenerico.remove(telefoneTemp);
        return "index";
    }
    
    
    public void adicionarTelefone2(){
        daoGenerico.save(telefone);
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Telefone cadastrado com sucesso."));
    }
    
    public String alterarTelefone(){
        daoGenerico.update(telefone);
        return "index";
    }
    
}
