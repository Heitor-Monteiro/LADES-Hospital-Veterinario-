/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLE;

import DAO.GenericoDAO;
import DAO.GenericoDAOImpl;
import MODELO.Adm;
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
@ManagedBean(name = "AdmControle")
@SessionScoped
public class AdmController {
    
    private final GenericoDAO daoGenerico = new GenericoDAOImpl();
    private Adm adm;
    private DataModel listarAdm;
    
    
    public DataModel getListarAdm(){
        List<Adm> lista = daoGenerico.list("sqlHQL");
        listarAdm = new ListDataModel(lista);
        return listarAdm;
    }
    
    public Adm getAdm(){
        return adm;
    }
    
    public void setAdm(Adm adm){
        this.adm = adm;
    }
    
    public void prepararAdicionarAdm(){
        adm = new Adm();
    }
    
    public String prepararAlterarAdm(){
        adm = (Adm)(listarAdm.getRowData());
        return "gerenciarLivro";
    }
    
    public String excluirAdm(){
        Adm admTemp = (Adm)(listarAdm.getRowData());
        daoGenerico.remove(admTemp);
        return "index";
    }
    
    public void adicionarAdm(){
        daoGenerico.save(adm);
    }
    
    public void adicionarAdm2(){
        daoGenerico.save(adm);
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Telefone cadastrado com sucesso."));
    }
    
    public String alterarAdm(){
        daoGenerico.update(adm);
        return "index";
    }
    
}
