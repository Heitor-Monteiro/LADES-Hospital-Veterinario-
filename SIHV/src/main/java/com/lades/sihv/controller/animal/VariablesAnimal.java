/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.animal;

import com.lades.sihv.controller.BeautyText;
import com.lades.sihv.controller.ListRenderedFields;
import com.lades.sihv.controller.RenderedFields;
import com.lades.sihv.model.Animals;
import com.lades.sihv.model.OwnersHasAnimals;
import com.lades.sihv.model.ClassAnimal;
import com.lades.sihv.model.Order;
import com.lades.sihv.model.OrderId;
import com.lades.sihv.model.Genre;
import com.lades.sihv.model.GenreId;
import com.lades.sihv.model.Species;
import com.lades.sihv.model.SpeciesId;
import com.lades.sihv.model.Races;
import com.lades.sihv.model.RacesId;
import com.lades.sihv.model.SmallAnimal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class VariablesAnimal implements Serializable {

    private OwnersHasAnimals ownersHasAnimals;
    private String tempRGHV;
    private Animals animal;
    private final List<TempListAnimals> tempListAnimals;
    private TempListAnimals selectTempListAnimals;
    //--------------------------------------------------------------------------
    private SmallAnimal smallAnimal;
    //--------------------------------------------------------------------------
    private final List<ClassAnimal> listObjClassAnimal;
    private final List<Order> listObjOrder;
    private final List<Genre> listObjGenre;
    private final List<Species> listObjSpecies;
    private final List<Races> listObjRaces;
    //--------------------------------------------------------------------------
    private final List<String> listTextClassAnimal;
    private final List<String> listTextOrder;
    private final List<String> listTextGenre;
    private final List<String> listTextSpecies;
    private final List<String> listTextRaces;
    //--------------------------------------------------------------------------
    private ClassAnimal selectObjClassAnimal;
    private Order selectObjOrder;
    private Genre selectObjGenre;
    private Species selectObjSpecies;
    private Races selectObjRaces;
    //--------------------------------------------------------------------------
    private String selectTextClassAnimal;
    private String selectTextOrder;
    private String selectTextGenre;
    private String selectTextSpecies;
    private String selectTextRaces;
    //--------------------------------------------------------------------------
    private final ListRenderedFields listScientificClassifications;
    //--------------------------------------------------------------------------
    private final RenderedFields statusNewAnimal;

    public VariablesAnimal() {
        ownersHasAnimals = new OwnersHasAnimals();
        animal = new Animals();
        tempListAnimals = new ArrayList<>();
        selectTempListAnimals = new TempListAnimals();
        //----------------------------------------------------------------------
        smallAnimal = new SmallAnimal();
        //----------------------------------------------------------------------
        listObjClassAnimal = new ArrayList<>();
        listObjOrder = new ArrayList<>();
        listObjGenre = new ArrayList<>();
        listObjSpecies = new ArrayList<>();
        listObjRaces = new ArrayList<>();
        //----------------------------------------------------------------------
        listTextClassAnimal = new ArrayList<>();
        listTextOrder = new ArrayList<>();
        listTextGenre = new ArrayList<>();
        listTextSpecies = new ArrayList<>();
        listTextRaces = new ArrayList<>();
        //----------------------------------------------------------------------
        listScientificClassifications = new ListRenderedFields(5);
        listScientificClassifications.startIndexListViewFields();
        statusNewAnimal = new RenderedFields();
        statusNewAnimal.setViewVariableBoolean(false);
    }

    // Creates for ID class ----------------------------------------------------
    public void createOrderId() {
        OrderId id = new OrderId();
        id.setClassAnimalPkClassAnimal(selectObjClassAnimal.getPkClassAnimal());
        selectObjOrder = new Order();
        selectObjOrder.setId(id);
        selectObjOrder.setOrderName(new BeautyText().Captalizador(selectTextOrder));
    }

    public void createGenreId() {
        GenreId id = new GenreId();
        id.setOrderClassAnimalPkClassAnimal(selectObjClassAnimal.getPkClassAnimal());
        id.setOrderPkOrder(selectObjOrder.getId().getPkOrder());
        selectObjGenre = new Genre();
        selectObjGenre.setId(id);
        selectObjGenre.setGenreName(new BeautyText().Captalizador(selectTextGenre));
    }

    public void createSpeciesId() {
        SpeciesId id = new SpeciesId();
        id.setGenreOrderClassAnimalPkClassAnimal(selectObjClassAnimal.getPkClassAnimal());
        id.setGenreOrderPkOrder(selectObjOrder.getId().getPkOrder());
        id.setGenrePkGenre(selectObjGenre.getId().getPkGenre());
        selectObjSpecies = new Species();
        selectObjSpecies.setId(id);
        selectObjSpecies.setNameSpecies(new BeautyText().Captalizador(selectTextSpecies));
    }

    public void createRacesId() {
        RacesId id = new RacesId();
        id.setSpeciesGenreOrderClassAnimalPkClassAnimal(selectObjClassAnimal.getPkClassAnimal());
        id.setSpeciesGenreOrderPkOrder(selectObjOrder.getId().getPkOrder());
        id.setSpeciesGenrePkGenre(selectObjGenre.getId().getPkGenre());
        id.setSpeciesPkSpecies(selectObjSpecies.getId().getPkSpecies());
        selectObjRaces = new Races();
        selectObjRaces.setId(id);
        selectObjRaces.setNameRaces(selectTextRaces);
    }

    public void searchAnimalSmall() {

    }

    // GETs & SETs--------------------------------------------------------------
    public OwnersHasAnimals getOwnersHasAnimals() {
        return ownersHasAnimals;
    }

    public void setOwnersHasAnimals(OwnersHasAnimals ownersHasAnimals) {
        this.ownersHasAnimals = ownersHasAnimals;
    }

    public String getTempRGHV() {
        return tempRGHV;
    }

    public void setTempRGHV(String tempRGHV) {
        this.tempRGHV = tempRGHV;
    }

    public Animals getAnimal() {
        return animal;
    }

    public void setAnimal(Animals animal) {
        this.animal = animal;
    }

    public SmallAnimal getSmallAnimal() {
        return smallAnimal;
    }

    public void setSmallAnimal(SmallAnimal smallAnimal) {
        this.smallAnimal = smallAnimal;
    }

    public List<TempListAnimals> getTempListAnimals() {
        return tempListAnimals;
    }

    public TempListAnimals getSelectTempListAnimals() {
        return selectTempListAnimals;
    }

    public void setSelectTempListAnimals(TempListAnimals selectTempListAnimals) {
        System.out.println("►►►►►►►►►►►►► SET utilizado");
        if (selectTempListAnimals != null) {
            this.selectTempListAnimals = selectTempListAnimals;
            if (selectTempListAnimals.getAnimals().getPkAnimal() != null) {
                new SearchRegisteredAnimal().methosSelectSmallAnimal(this);
            }
        }
    }

//    public ListRenderedFields getListScientificClassifications() {
//        return listScientificClassifications;
//    }
    //--------------------------------------------------------------------------
    public List<String> getListTextClassAnimal() {
        return listTextClassAnimal;
    }

    public List<String> getListTextOrder() {
        return listTextOrder;
    }

    public List<String> getListTextGenre() {
        return listTextGenre;
    }

    public List<String> getListTextSpecies() {
        return listTextSpecies;
    }

    public List<String> getListTextRaces() {
        return listTextRaces;
    }

    //--------------------------------------------------------------------------
    public String getSelectTextClassAnimal() {
        return selectTextClassAnimal;
    }

    public void setSelectTextClassAnimal(String selectTextClassAnimal) {
        this.selectTextClassAnimal = selectTextClassAnimal;
    }

    public String getSelectTextOrder() {
        return selectTextOrder;
    }

    public void setSelectTextOrder(String selectTextOrder) {
        this.selectTextOrder = selectTextOrder;
    }

    public String getSelectTextGenre() {
        return selectTextGenre;
    }

    public void setSelectTextGenre(String selectTextGenre) {
        this.selectTextGenre = selectTextGenre;
    }

    public String getSelectTextSpecies() {
        return selectTextSpecies;
    }

    public void setSelectTextSpecies(String selectTextSpecies) {
        this.selectTextSpecies = selectTextSpecies;
    }

    public String getSelectTextRaces() {
        return selectTextRaces;
    }

    public void setSelectTextRaces(String selectTextRaces) {
        this.selectTextRaces = selectTextRaces;
    }

    //--------------------------------------------------------------------------
    public List<ClassAnimal> getListObjClassAnimal() {
        return listObjClassAnimal;
    }

    public List<Order> getListObjOrder() {
        return listObjOrder;
    }

    public List<Genre> getListObjGenre() {
        return listObjGenre;
    }

    public List<Species> getListObjSpecies() {
        return listObjSpecies;
    }

    public List<Races> getListObjRaces() {
        return listObjRaces;
    }

    //--------------------------------------------------------------------------
    public ClassAnimal getSelectObjClassAnimal() {
        return selectObjClassAnimal;
    }

    public void setSelectObjClassAnimal(ClassAnimal selectObjClassAnimal) {
        this.selectObjClassAnimal = selectObjClassAnimal;
    }

    public Order getSelectObjOrder() {
        return selectObjOrder;
    }

    public void setSelectObjOrder(Order selectObjOrder) {
        this.selectObjOrder = selectObjOrder;
    }

    public Genre getSelectObjGenre() {
        return selectObjGenre;
    }

    public void setSelectObjGenre(Genre selectObjGenre) {
        this.selectObjGenre = selectObjGenre;
    }

    public Species getSelectObjSpecies() {
        return selectObjSpecies;
    }

    public void setSelectObjSpecies(Species selectObjSpecies) {
        this.selectObjSpecies = selectObjSpecies;
    }

    public Races getSelectObjRaces() {
        return selectObjRaces;
    }

    public void setSelectObjRaces(Races selectObjRaces) {
        this.selectObjRaces = selectObjRaces;
    }

    //--------------------------------------------------------------------------
    public RenderedFields StatusRace() {
        return listScientificClassifications.getListViewFields(5);
    }

    public RenderedFields getStatusNewAnimal() {
        return statusNewAnimal;
    }
}
