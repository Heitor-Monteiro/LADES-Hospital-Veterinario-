/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.bean;

import com.lades.sihv.model.Animais;
import com.lades.sihv.controller.animal.AnimalGerarRGHV;
import com.lades.sihv.controller.animal.AnimalListaPelagem;
import com.lades.sihv.controller.animal.AnimalCadastro;
import java.util.List;
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

    public void prepararAnimalPequeno() {
        getAnimal().setCategoriaAnimal("P");
        gerarRghvDeAnimal();
        animalListaPelagem();
    }

    private void gerarRghvDeAnimal() {
        new AnimalGerarRGHV().gerarRGHV(getAnimal());
    }

    private void animalListaPelagem() {
        AnimalListaPelagem obj = new AnimalListaPelagem();
        obj.carregarListaPelagem();
        getVariaveisDeSessao().setFerramentaTemp(obj);
    }

    public void cadastrarAnimal() {
        boolean var = new AnimalCadastro().cadastrarANIMAL(animal);
        getObjTools().setShowButtonPrint(var);//Habilitando visibilidade do botão para impressão
    }

//  Métodos GETs e SETs
    public Animais getAnimal() {
        if (animal == null) {
            animal = new Animais();
        }
        return animal;
    }

    public void setAnimal(Animais animal) {
        this.animal = animal;
    }

    public List<String> getListaPelagem() {
        AnimalListaPelagem obj = (AnimalListaPelagem) getVariaveisDeSessao().getFerramentaTemp();
        return obj.getListaPelagem();
    }
}
