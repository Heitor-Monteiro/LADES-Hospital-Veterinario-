/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller;

import com.lades.sihv.bean.AbstractBean;
import java.util.List;

/**
 *
 * @author thiberius
 */

/*Uma instancia dessa classe serve para
gerenciar um item na tela que precise de rendered*/
public class VariablesSearch extends AbstractBean {

    private String textSearch, itemSearch,
            searchMask, searchTip, placeholderField;
    private int maxLength;
    private RenderedFields showTable;
    private List<?> objectsReturned;

    public void objectsFullList() {
        if (objectsReturned != null) {
            if (!objectsReturned.isEmpty()) {
                getShowTable().setViewVariableBoolean(true);
            } else {
                getObjMessage().warn("Lista vazia!", "Item não encontrado.");
            }
        }
    }

    public void clearListObjectsReturned() {
        if (objectsReturned != null) {
            objectsReturned.clear();
        }
    }

    public boolean checkMaxLength() {
        boolean var = false;
        if (textSearch.length() <= maxLength) {
            var = true;
        } else {
            getObjMessage().warn("Valor invalido!", "Quantidade de caracteres não permitida.");
        }
        return var;
    }

    public String getTextSearch() {
        return textSearch;
    }

    public void setTextSearch(String textSearch) {
        this.textSearch = textSearch;
    }

    public String getItemSearch() {
        return itemSearch;
    }

    public void setItemSearch(String itemSearch) {
        this.itemSearch = itemSearch;
    }

    public String getSearchMask() {
        return searchMask;
    }

    public void setSearchMask(String searchMask) {
        this.searchMask = searchMask;
    }

    public String getSearchTip() {
        return searchTip;
    }

    public void setSearchTip(String searchTip) {
        this.searchTip = searchTip;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public List<?> getObjectsReturned() {
        return objectsReturned;
    }

    public void setObjectsReturned(List<?> objectsReturned) {
        this.objectsReturned = objectsReturned;
    }

    public RenderedFields getShowTable() {
        if (showTable == null) {
            showTable = new RenderedFields();
        }
        return showTable;
    }

    public String getPlaceholderField() {
        return placeholderField;
    }

    public void setPlaceholderField(String placeholderField) {
        this.placeholderField = placeholderField;
    }
    
    
}
