/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.Controller;

import java.io.Serializable;
import java.util.List;
import com.lades.sihv.DAO.GenericoDAOImpl;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author thiberius
 */
@ManagedBean(name = "PesquisaController")
@SessionScoped

public class PesquisaController implements Serializable{
    
    private String itemPesquisa,textoPesquisa;
    private boolean showDataTable;
    private Object objGenerico;
    private List<?> objBuscados;
    private static final FacesMessages mensagem = new FacesMessages();
    
    
    /*Este método objetiva limpar os campos
    presentes no fomulário que envolva pesquisar itens,
    o mesmo também esvazia a listagem buscada.*/
    public void limparCampos(){
        itemPesquisa="";
        textoPesquisa="";
        if(objBuscados != null){
            objBuscados.clear();
            showDataTable=false;
        }
    }
    
    
    /*O método faz:
    Atribui um tipo de objeto especifico para objGenerico;
    Atribui um tipo de List especifico para objBuscados;
    Realiza a busca por objetos através de itemPesquisa e textoPesquisa.*/

    public void ListagemPessoa(List<?> objList){
        objBuscados = objList;
        objGenerico = new com.lades.sihv.model.Pessoa(); 
        showDataTable=false;
        objBuscados = new GenericoDAOImpl().listBySearchPESSOA(itemPesquisa, textoPesquisa);
        if(objBuscados.isEmpty()){
            mensagem.warn("Erro ao listar!","Item não encontrado.");
        }else{
            this.showDataTable=true;
        }
    }
    public void ListagemAnimais(List<?> objList){
        objBuscados = objList;
        objGenerico = new com.lades.sihv.model.Animais(); 
        showDataTable=false;
        objBuscados = new GenericoDAOImpl().listBySearchANIMAIS(itemPesquisa, textoPesquisa);
        if(objBuscados.isEmpty()){
            mensagem.warn("Erro ao listar!","Item não encontrado.");
        }else{
            this.showDataTable=true;
        }
    }
       
        
//MÉTODOS GET & SET    
//-----------------------------------------------------------------------------
    public String getItemPesquisa() {
        return itemPesquisa;
    }

    public void setItemPesquisa(String itemPesquisa) {
        this.itemPesquisa = itemPesquisa;
    }

    public String getTextoPesquisa() {
        return textoPesquisa;
    }

    public void setTextoPesquisa(String textoPesquisa) {
        this.textoPesquisa = textoPesquisa;
    }
//-----------------------------------------------------------------------------
    public boolean isShowDataTable() {
        return showDataTable;
    }

    public void setShowDataTable(boolean showDataTable) {
        this.showDataTable = showDataTable;
    }
//-----------------------------------------------------------------------------
    public List<?> getObjBuscados() {
        return objBuscados;
    }

    public void setObjBuscados(List<?> objBuscados) {
        this.objBuscados = objBuscados;
    }
//-----------------------------------------------------------------------------
    public Object getObjGenerico() {
        return objGenerico;
    }

    public void setObjGenerico(Object objGenerico) {
        this.objGenerico = objGenerico;
    } 
}
