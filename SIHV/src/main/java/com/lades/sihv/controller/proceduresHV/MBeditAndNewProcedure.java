/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.proceduresHV;

import com.lades.sihv.bean.*;
import com.lades.sihv.controller.RenderedFields;
import com.lades.sihv.controller.logBook.SaveLogControl;
import com.lades.sihv.model.Category;
import com.lades.sihv.model.Prices;
import com.lades.sihv.model.Procedures;
import com.lades.sihv.model.TypeProcedure;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.RowEditEvent;

//@author thiberius
@ManagedBean(name = "MBeditAndNewProcedure")
@ViewScoped

public class MBeditAndNewProcedure extends AbstractBean {

    private List<SetOfProcedureAttributes> setOfProcedureAttributes;
    //--------------------------------------------------------------------------
    private List<?> listTempProcedures;
    //--------------------------------------------------------------------------
    private List<TypeProcedure> listTypeProcedure;
    private List<Category> listCategory;
    //--------------------------------------------------------------------------
    private List<String> listTextTypeProcedure;
    private List<String[]> listTextCategory;
    //--------------------------------------------------------------------------
    private String hqlProcedures;
    private Procedures newProcedure;
    private Prices newPrice;
    //--------------------------------------------------------------------------
    private RenderedFields enableButtonSaveNewProcedure;

    @PostConstruct
    public void init() {
        System.out.println("►►►►►►►►►►►►► MBeditAndNewProcedure initiated");
        setOfProcedureAttributes = new ArrayList<>();
        hqlProcedures = "select p, t, pr from Procedures p, TypeProcedure t, Prices pr \n"
                + "where \n"
                + "p.typeProcedure.pkTypeProcedure=t.pkTypeProcedure and \n"
                + "t.logicalExclusion='0' and \n"
                + "pr.procedures=p.pkProcedure  ";
        listTempProcedures = getDaoGenerico().list(hqlProcedures);
        //----------------------------------------------------------------------
        listTextTypeProcedure = new ArrayList<>();
        listTextCategory = new ArrayList<>();
        listTypeProcedure = getDaoGenerico().list("select t from TypeProcedure t ");
        listCategory = getDaoGenerico().list("select c from Category c ");
        popularListTextTypeProcedure();
        popularListTextCategory();
        //----------------------------------------------------------------------
        collectProceduresAndTypeProcedure();
        collectCategoryOfProcedures();
        newProcedure = new Procedures();
        newPrice = new Prices();
        enableButtonSaveNewProcedure = new RenderedFields();
    }

    private void popularListTextTypeProcedure() {
        for (TypeProcedure obj : listTypeProcedure) {
            if (!obj.isLogicalExclusion()) {
                listTextTypeProcedure.add(obj.getNameTypeProced());
            }
        }
    }

    private void popularListTextCategory() {
        for (Category obj : listCategory) {
            if (!obj.isLogicalExclusion()) {
                String var[] = new String[2];
                var[0] = obj.getAbbreviation();
                var[1] = obj.getDescription();
                listTextCategory.add(var);
            }
        }
    }

    private void collectProceduresAndTypeProcedure() {
        for (Object[] item : (List<Object[]>) listTempProcedures) {
            SetOfProcedureAttributes newObj = new SetOfProcedureAttributes();
            newObj.setProcedure((Procedures) item[0]);
            newObj.setTypeProcedure((TypeProcedure) item[1]);
            newObj.setPrice((Prices) item[2]);
            setOfProcedureAttributes.add(newObj);
        }
    }

    private void collectCategoryOfProcedures() {
        try {
            for (SetOfProcedureAttributes obj : setOfProcedureAttributes) {
                if (obj.getPrice().getCategory() != null) {
                    Category selectCategory = (Category) getDaoGenerico().
                            list("select c from Category c \n"
                                    + "where \n"
                                    + "c.pkCategory='" + obj.getPrice().
                                            getCategory().getPkCategory() + "' ").get(0);
                    obj.setCategory(selectCategory);
                }
            }
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO public void collectCategoryOfProcedures(): " + e);
        }

    }

    public void onRowEdit(RowEditEvent event) {
        try {
            SetOfProcedureAttributes obj = (SetOfProcedureAttributes) event.getObject();
            for (TypeProcedure typeProcedure : listTypeProcedure) {
                if (typeProcedure.getNameTypeProced().equals(obj.getSelectTypeProcedure())) {
                    obj.setTypeProcedure(typeProcedure);
                    obj.insertNewTypeOfProcedure();
                    break;
                }
            }
            getDaoGenerico().update(obj.getProcedure());
            for (Category category : listCategory) {
                if (category.getAbbreviation().equals(obj.getAbbreviation())) {
                    obj.setCategory(category);
                    obj.insertNewCategory();
                    break;
                } else {
                    obj.setCategory(null);
                    obj.insertNewCategory();
                }
            }
            getDaoGenerico().update(obj.getPrice());
            getObjMessage().info("Os dados da categoria foram atualizados", "");
            new SaveLogControl().saveLog(13, "Procedimento:" + obj.getProcedure().getNameProcedure()
                    + ", Valor:" + obj.getPrice().getPrice() + ", Dosagem:" + obj.getPrice().getDosage()
                    + ", Categoria:" + obj.getAbbreviation() + " - " + obj.getDescription()
                    + ", Setor:" + obj.getSelectTypeProcedure());
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO public void onRowEdit(): " + e);
        }
    }

    public void onRowCancel(RowEditEvent event) {
        getObjMessage().info("Nada foi alterado!", "");
    }

    public void prepareAnewProcedure() {
        enableButtonSaveNewProcedure.setViewVariableBoolean(false);
        newProcedure = new Procedures();
        newPrice = new Prices();
    }

    public void onRowAddProcedure() {
        try {
            newProcedure.setDateOfLastModification(getObjData());
            newProcedure.setDisableProcedure(false);
            getDaoGenerico().save(newProcedure);
            newPrice.setDateOfLastModification(getObjData());
            newPrice.setProcedures(newProcedure);
            getDaoGenerico().save(newPrice);
            setOfProcedureAttributes.clear();
            listTempProcedures.clear();
            listTempProcedures = getDaoGenerico().list(hqlProcedures);
            collectProceduresAndTypeProcedure();
            collectCategoryOfProcedures();
            enableButtonSaveNewProcedure.setViewVariableBoolean(true);
            getObjMessage().info("Novo procedimento registrado com sucesso!", "");
            String category = "---";
            if (newPrice.getCategory() != null) {
                category = newPrice.getCategory().getAbbreviation()
                        + " - " + newPrice.getCategory().getDescription();
            }
            new SaveLogControl().saveLog(4, "Procedimento:" + newProcedure.getNameProcedure()
                    + ", Valor:" + newPrice.getPrice() + ", Dosagem:" + newPrice.getDosage()
                    + ", Categoria:" + category
                    + ", Setor:" + newProcedure.getTypeProcedure().getNameTypeProced());
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO public void onRowAddProcedure(): " + e);
        }
    }

    //-GETs e SETs--------------------------------------------------------------
    public Procedures getNewProcedure() {
        return newProcedure;
    }

    public Prices getNewPrice() {
        return newPrice;
    }

    public List<SetOfProcedureAttributes> getSetOfProcedureAttributes() {
        return setOfProcedureAttributes;
    }

    public List<TypeProcedure> getListTypeProcedure() {
        return listTypeProcedure;
    }

    public List<Category> getListCategory() {
        return listCategory;
    }

    public List<String> getListTextTypeProcedure() {
        return listTextTypeProcedure;
    }

    public List<String[]> getListTextCategory() {
        return listTextCategory;
    }

    public RenderedFields getEnableButtonSaveNewProcedure() {
        return enableButtonSaveNewProcedure;
    }
}
