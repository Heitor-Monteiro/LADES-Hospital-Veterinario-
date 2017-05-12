/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.bean;

import com.lades.sihv.model.Animais;
import com.lades.sihv.controller.animal.AnimalGerarRGHV;
import com.lades.sihv.controller.animal.AnimalGetIdade;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author thiberius
 */
@ManagedBean(name = "MBanimal")
@ViewScoped
public class MBanimal extends AbstractBean {
    private Animais animal;

    
    
    
    
    
    
    public void gerarRghvDeAnimal() {
        ObjGeralRGHV().gerarRGHV(animal);
    }
    
    private AnimalGerarRGHV ObjGeralRGHV() {
        return new AnimalGerarRGHV();
    }
    
    private AnimalGetIdade ObjAnimalGetIdade(){
        return new AnimalGetIdade();
    }
    
//  Métodos GETs e SETs
    public Animais getAnimal() {
        if (animal == null){
            animal = new Animais();
        }
        return animal;
    }

    public void setAnimal(Animais animal) {
        this.animal = animal;
    }
    
    
}
