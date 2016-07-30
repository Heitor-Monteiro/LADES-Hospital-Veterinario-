/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLE;

import DAO.TelefoneDao;
import DAO.TelefoneDaoImp;
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
    
    private Telefone telefone;
    private DataModel listarTelefone;
    
    
    public DataModel getListarTelefone(){
        List<Telefone> lista = new TelefoneDaoImp().list();
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
        TelefoneDao dao = new TelefoneDaoImp();
        dao.remove(telefoneTemp);
        return "index";
    }
    
    public void adicionarTelefone(){
        TelefoneDao dao = new TelefoneDaoImp();
        dao.save(telefone);
    }
    
    public void adicionarTelefone2(){
        TelefoneDao dao = new TelefoneDaoImp();
        dao.save(telefone);
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Telefone cadastrado com sucesso."));
    }
    
    public String alterarTelefone(){
        TelefoneDao dao = new TelefoneDaoImp();
        dao.update(telefone);
        return "index";
    }
    
}
