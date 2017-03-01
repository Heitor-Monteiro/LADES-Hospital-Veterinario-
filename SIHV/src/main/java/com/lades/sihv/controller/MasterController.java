/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller;

import com.lades.sihv.DAO.GenericoDAO;
import com.lades.sihv.DAO.GenericoDAOImpl;
import com.lades.sihv.Tools;
import com.lades.sihv.classeMolde.CollectionClasses;
import com.lades.sihv.classeMolde.FormsExames;
import com.lades.sihv.model.Pessoa;
import com.lades.sihv.model.Animais;
import com.lades.sihv.model.Consulta;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author thiberius
 */
@ManagedBean(name = "MasterControle")
@SessionScoped
public class MasterController implements Serializable {

    private final GenericoDAO daoGenerico = new GenericoDAOImpl();
    private final FacesMessages message = new FacesMessages();
    private final Tools tools = new Tools();

    private Pessoa pessoa;
    private CollectionClasses collectionClasses;
    private FormsExames formsExame;
    private Animais animal;
    private Consulta consulta;
    private PesquisaController pesquisaControle;
    private PessoaController pessoaControle;
    private AnimalController animalControle;
    
    
    public void prepararControllerPessoa() {
        pessoaControle = new PessoaController(daoGenerico, message, tools);
    }

    //-------------------------------------------------------
    public void prepararCadastroAnimal() {
        prepararPesquisaController();
        animalControle = new AnimalController(daoGenerico, message, tools);
        animalControle.prepararListaPelagem();
    }

    public void adicionarNovoAnimal() {
        animalControle.adicionarANIMAL(pessoa);
    }

    //-------------------------------------------------------
    public void prepararNovaConsulta() {
        prepararPesquisaController();
        prepareFormsExames();
        formsExame.prepararFormsConsulta();
    }
    
    //-------------------------------------------------------
    public void pesquisarConsulta(){
        prepararPesquisaController();
        
    }
    
    //-------------------------------------------------------
    public void prepararPesquisaController() {
        pesquisaControle = new PesquisaController();
    }
    
    private void prepareFormsExames(){
        formsExame = new FormsExames();
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
//    public PessoaBusca getPessoaBusca() {
//        return pessoaBusca;
//    }
//
//    public void setPessoaBusca(PessoaBusca pessoaBusca) {
//        this.pessoaBusca = pessoaBusca;
//    }
    
    public Animais getAnimal() {
        return animal;
    }

    public void setAnimal(Animais animal) {
        this.animal = animal;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public PesquisaController getPesquisaControle() {
        return pesquisaControle;
    }

//    public void setPesquisaControle(PesquisaController pesquisaControle) {
//        this.pesquisaControle = pesquisaControle;
//    }
    public AnimalController getAnimalControle() {
        return animalControle;
    }

//    public void setAnimalControle(AnimalController animalControle) {
//        this.animalControle = animalControle;
//    }
    

    public FacesMessages getMessage() {
        return message;
    }

    public PessoaController getPessoaControle() {
        return pessoaControle;
    }

    public Tools getTools() {
        return tools;
    }

    public CollectionClasses getCollectionClasses() {
        return collectionClasses;
    }

    public void setCollectionClasses(CollectionClasses collectionClasses) {
        this.collectionClasses = collectionClasses;
    }

    public FormsExames getFormsExame() {
        return formsExame;
    }

    public void setFormsExame(FormsExames formsExame) {
        this.formsExame = formsExame;
    }
}
