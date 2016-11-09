/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller;

import com.lades.sihv.model.Pessoa;
import com.lades.sihv.model.Animais;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author thiberius
 */
@ManagedBean(name = "MasterControle")
@SessionScoped
public class MasterController implements Serializable{
    
    private Pessoa pessoa;
    private Animais animal;

    
    private PesquisaController pesquisaControle;
    private AnimalController animalControle;
    private ConsultaController consultaControle;
    
    
    
    
    
    
    
    
    
    //-------------------------------------------------------
    public void prepararCadastroAnimal(){
        pesquisaControle = new PesquisaController();
        animalControle = new AnimalController();
        animalControle.prepararAdicionarAnimal();
    }
    
    public void adicionarNovoAnimal(){
        animalControle.adicionarANIMAL(pessoa);
    }
    //-------------------------------------------------------
    public void prepararNovaConsulta(){
        pesquisaControle = new PesquisaController();
        consultaControle = new ConsultaController();
        consultaControle.prepararNovaConsulta();
    }
    
    public void adicionarNovaConsulta(){
        consultaControle.adicionarNovaConsulta(animal);
    }
    
    
    
    
    
    
    
    
    
    
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    public Animais getAnimal() {
        return animal;
    }

    public void setAnimal(Animais animal) {
        this.animal = animal;
    }

    public PesquisaController getPesquisaControle() {
        return pesquisaControle;
    }

    public void setPesquisaControle(PesquisaController pesquisaControle) {
        this.pesquisaControle = pesquisaControle;
    }

    public AnimalController getAnimalControle() {
        return animalControle;
    }

    public void setAnimalControle(AnimalController animalControle) {
        this.animalControle = animalControle;
    }

    public ConsultaController getConsultaControle() {
        return consultaControle;
    }

    public void setConsultaControle(ConsultaController consultaControle) {
        this.consultaControle = consultaControle;
    }
    
    
}
