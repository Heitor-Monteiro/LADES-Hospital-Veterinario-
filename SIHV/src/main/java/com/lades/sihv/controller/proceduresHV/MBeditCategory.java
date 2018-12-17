/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.proceduresHV;

import com.lades.sihv.bean.*;
import com.lades.sihv.model.Category;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.RowEditEvent;

//@author thiberius
@ManagedBean(name = "MBeditCategory")
@ViewScoped

public class MBeditCategory extends AbstractBean {

    private List<Category> listCategory;
    private Category newCategory;
    private String hql;

    @PostConstruct
    public void init() {
        System.out.println("►►►►►►►►►►►►► MBeditCategory initiated");
        hql = "select c from Category c ";
        listCategory = getDaoGenerico().list(hql);
        newCategory = new Category();
    }

    public void onRowEdit(RowEditEvent event) {
        try {
            Category obj = (Category) event.getObject();
            getDaoGenerico().update(obj);
            getObjMessage().info("Os dados da categoria foram atualizados", "");
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO public void onRowEdit(): " + e);
        }
    }

    public void onRowCancel(RowEditEvent event) {
        getObjMessage().info("Nada foi alterado!", "");
    }

    public void newPreparationItem() {
        newCategory = new Category();
    }

    public void onRowAdd() {
        try {
            newCategory.setDateOfLastModification(getObjData());
            newCategory.setLogicalExclusion(false);
            getDaoGenerico().save(newCategory);
            listCategory = getDaoGenerico().list(hql);
            getObjMessage().info("A nova categoria foi salva", "");
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO public void onRowAdd(): " + e);
        }
    }

    //-GETs e SETs--------------------------------------------------------------
    public List<Category> getListCategory() {
        return listCategory;
    }

    public Category getNewCategory() {
        return newCategory;
    }
}
