/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.proceduresHV;

import com.lades.sihv.model.Category;
import com.lades.sihv.model.Prices;
import com.lades.sihv.model.Procedures;
import com.lades.sihv.model.TypeProcedure;
import java.io.Serializable;

/**
 *
 * @author thiberius
 */
public class SetOfProcedureAttributes implements Serializable {

    private Procedures procedure;
    private Prices price;
    private Category category;
    private TypeProcedure typeProcedure;
    private String selectTypeProcedure;
    private final String selectCategory[];

    public SetOfProcedureAttributes() {
        procedure = new Procedures();
        price = new Prices();
        category = new Category();
        typeProcedure = new TypeProcedure();
        selectCategory = new String[2];
    }

    public void insertNewTypeOfProcedure() {
        procedure.setTypeProcedure(typeProcedure);
    }

    public void insertNewCategory() {
        price.setCategory(category);
    }

    // GETs and SETs -----------------------------------------------------------
    public Procedures getProcedure() {
        return procedure;
    }

    public void setProcedure(Procedures procedure) {
        this.procedure = procedure;
    }

    public Prices getPrice() {
        return price;
    }

    public void setPrice(Prices price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        if (category != null) {
            this.category = category;
            selectCategory[0] = this.category.getAbbreviation();
            selectCategory[1] = this.category.getDescription();
        } else {
            this.category = null;
        }
    }

    public TypeProcedure getTypeProcedure() {
        return typeProcedure;
    }

    public void setTypeProcedure(TypeProcedure typeProcedure) {
        this.typeProcedure = typeProcedure;
        selectTypeProcedure = this.typeProcedure.getNameTypeProced();
    }

    public String getSelectTypeProcedure() {
        return selectTypeProcedure;
    }

    public void setSelectTypeProcedure(String selectTypeProcedure) {
        this.selectTypeProcedure = selectTypeProcedure;
    }

    public String getAbbreviation() {
        return selectCategory[0];
    }

    public void setAbbreviation(String abbreviation) {
        this.selectCategory[0] = abbreviation;
    }

    public String getDescription() {
        return selectCategory[1];
    }

    public void setDescription(String description) {
        this.selectCategory[1] = description;
    }
}
