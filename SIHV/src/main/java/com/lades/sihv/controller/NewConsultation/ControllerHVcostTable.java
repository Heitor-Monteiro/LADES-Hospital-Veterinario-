/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.NewConsultation;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.ModuleToCollectError;
import com.lades.sihv.controller.proceduresHV.SetOfProcedureAttributes;
import com.lades.sihv.model.Category;
import com.lades.sihv.model.Prices;
import com.lades.sihv.model.Procedures;
import com.lades.sihv.model.ProceduresApplied;
import com.lades.sihv.model.TypeProcedure;
import com.lades.sihv.model.VetConsultation;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class ControllerHVcostTable extends AbstractBean {

    private VetConsultation consultation;
    private final ModuleToCollectError objErro;
    private List<SetOfProcedureAttributes> listProcedures;
    private List<SetOfProcedureAttributes> selectProcedures;
    private List<SetOfProcedureAttributes> filterProcedures;
    private List<String> typeProcedureOptions;
    private List<String> category;
    private double totalCost;

    public ControllerHVcostTable(VetConsultation consultation) {
        this.consultation = consultation;
        selectProcedures = new ArrayList<>();
        objErro = new ModuleToCollectError();
        loadTypeProcedure();
        totalCost = 0;
        collectProceduresHV();
    }

    private void loadTypeProcedure() {
        typeProcedureOptions = getDaoGenerico().list("select t.nameTypeProced from TypeProcedure t ");
        category = getDaoGenerico().list("select c.abbreviation from Category c \n"
                + "where \n"
                + "c.logicalExclusion='0' ");
    }

    public void confirmeStatusConsultation() {
        try {
            SchedulesConfirmedForConsultation obj = (SchedulesConfirmedForConsultation) getVariaveisDeSessao().getObjetoTemp();
            if (obj != null && obj.getSchedule().getTypeService().equals("Nova consulta")) {
                selectProcedures.add(listProcedures.get(0));
                totalCost = listProcedures.get(0).getPrice().getPrice().doubleValue();
            }
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO private void confirmeStatusConsultation(): " + e.toString());
            new ModuleToCollectError().erroPage500("ControllerHVcostTable > confirmeStatusConsultation", e.toString());
        }
    }

    private void collectProceduresHV() {
        try {
            listProcedures = new ArrayList<>();
            List<?> tempList = getDaoGenerico().list("select p, t, pr "
                    + "from Procedures p, TypeProcedure t, Prices pr \n"
                    + "where \n"
                    + "p.typeProcedure.pkTypeProcedure=t.pkTypeProcedure and \n"
                    + "t.logicalExclusion='0' and \n"
                    + "pr.procedures=p.pkProcedure  ");
            for (Object[] item : (List<Object[]>) tempList) {
                SetOfProcedureAttributes newObj = new SetOfProcedureAttributes();
                newObj.setProcedure((Procedures) item[0]);
                newObj.setTypeProcedure((TypeProcedure) item[1]);
                newObj.setPrice((Prices) item[2]);
                listProcedures.add(newObj);
            }
            collectCategoryOfProcedures();
            //confirmeStatusConsultation();
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO private void collectProceduresHV(): " + e.toString());
            objErro.erroPage500("ControllerHVcostTable > collectProceduresHV", e.toString());
        }
    }

    private void collectCategoryOfProcedures() {
        try {
            for (SetOfProcedureAttributes obj : listProcedures) {
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
            System.out.println("►►►►►►►►►►►►► ERRO private void collectCategoryOfProcedures(): " + e.toString());
            new ModuleToCollectError().erroPage500("ControllerHVcostTable > collectCategoryOfProcedures", e.toString());
        }

    }

    public void addCostsProcedureHV() {
        totalCost = 0;
        consultation.setDiscountValue(BigDecimal.ZERO);
        for (SetOfProcedureAttributes obj : selectProcedures) {
            totalCost += obj.getPrice().getPrice().doubleValue();
        }
        System.out.println("►►►►►►►►►►►►► Cost: " + totalCost);
    }

    public String getSizeSelectProcedures() {
        String x = "0";
        if (!selectProcedures.isEmpty()) {
            x = "" + selectProcedures.size();
        }
        return x;
    }

    public void saveSelectProcedures(VetConsultation consulta) {
        try {
            for (SetOfProcedureAttributes obj : selectProcedures) {
                ProceduresApplied proceduresApplied = new ProceduresApplied();
                proceduresApplied.setProcedures(obj.getProcedure());
                proceduresApplied.setVetConsultation(consulta);
                proceduresApplied.setPaymentStatus(false);
                getDaoGenerico().save(proceduresApplied);
            }
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO private void saveSelectProcedures(): " + e.toString());
            new ModuleToCollectError().erroPage500("ControllerHVcostTable > saveSelectProcedures", e.toString());
        }
    }

    //GETs & SETs
    public List<SetOfProcedureAttributes> getListProcedures() {
        return listProcedures;
    }

    public List<SetOfProcedureAttributes> getSelectProcedures() {
        return selectProcedures;
    }

    public void setSelectProcedures(List<SetOfProcedureAttributes> selectProcedures) {
        this.selectProcedures = selectProcedures;
    }

    public List<SetOfProcedureAttributes> getFilterProcedures() {
        return filterProcedures;
    }

    public void setFilterProcedures(List<SetOfProcedureAttributes> filterProcedures) {
        this.filterProcedures = filterProcedures;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public List<String> getTypeProcedureOptions() {
        return typeProcedureOptions;
    }

    public void setTypeProcedureOptions(List<String> typeProcedureOptions) {
        this.typeProcedureOptions = typeProcedureOptions;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }
}
