/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consultationEntryControl;

import com.lades.sihv.model.Category;
import com.lades.sihv.model.Prices;
import com.lades.sihv.model.Procedures;
import com.lades.sihv.model.ProceduresApplied;
import java.io.Serializable;

/**
 *
 * @author thiberius
 */
public class ItemProceduresApplied implements Serializable {

    private ProceduresApplied applied;
    private Procedures procedure;
    private Prices price;
    private Category category;
    
    // GETs & SETs -------------------------------------------------------------

    public ProceduresApplied getApplied() {
        return applied;
    }

    public void setApplied(ProceduresApplied applied) {
        this.applied = applied;
    }

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
        this.category = category;
    }
}
