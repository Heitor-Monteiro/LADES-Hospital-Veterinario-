/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.animal;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.person.VariablesPerson;
import com.lades.sihv.model.People;

/**
 *
 * @author thiberius
 */
public class AnimalControl extends AbstractBean {

    private final VariablesAnimal varAnimal;
    private final GenerateRghvFromAnimal generateRghv;

    public AnimalControl() {
        varAnimal = new VariablesAnimal();
        generateRghv = new GenerateRghvFromAnimal();
    }

    public void methodSmallAnimalSpecies() {
        new SmallAnimalSpecies().methodSmallAnimalSpecies(varAnimal);
    }

    public void methodSearchForPossibleRaces() {
        new SearchForPossibleRaces().methodSearchForPossibleRaces(varAnimal);
    }

    public void methodCheckBreeds() {
        new CheckBreeds().methodCheckBreeds(varAnimal);
    }

    public void saveNewSmallAnimal() {
        new SaveScientificClassifications().saveNewBreed(varAnimal);
        new SaveAnimalAttributes().saveNewAnimal(varAnimal);
        new SaveAnimalAttributes().saveSmallAnimal(varAnimal);
    }

    public void saveOwnersHasAnimals(VariablesPerson varPerson) {
        new SaveAnimalAttributes().saveOwnersHasAnimals(varPerson, varAnimal);
    }

    public void methodSearchRegisteredAnimal(People person) {
        new SearchRegisteredAnimal().methodSearchRegisteredAnimal(person, varAnimal);
    }

    public void methosSelectSmallAnimal() {
        new SearchRegisteredAnimal().methosSelectSmallAnimal(varAnimal);
    }

    public void newSmallAnimalForRegisteredOwner() {
        if (varAnimal.getStatusNewAnimal().isViewVariableBoolean()) {
            generateRghv.methodNewRghvAnimal(varAnimal, "P");
            new SmallAnimalSpecies().methodSmallAnimalSpecies(varAnimal);
        }

    }

    // GETs & SETs -------------------------------------------------------------
    public VariablesAnimal getVarAnimal() {
        return varAnimal;
    }

    public GenerateRghvFromAnimal getGenerateRghv() {
        return generateRghv;
    }

}
