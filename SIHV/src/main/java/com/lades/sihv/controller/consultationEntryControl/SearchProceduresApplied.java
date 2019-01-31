/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consultationEntryControl;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.model.Prices;
import com.lades.sihv.model.Procedures;
import com.lades.sihv.model.ProceduresApplied;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author thiberius
 */
public class SearchProceduresApplied extends AbstractBean {

    private List<ItemProceduresApplied> listProceduresApplied = new ArrayList();

    public void methodListProceduresApplied(ConsultationEntryItem selectItem) {
        if (!listProceduresApplied.isEmpty()) {
            listProceduresApplied.clear();
        }
        List<?> list = getDaoGenerico().list("select pa,p,pr from VetConsultation vc, ProceduresApplied pa, Procedures p, Prices pr \n"
                + "where \n"
                + "vc.pkVetConsultation=pa.vetConsultation.pkVetConsultation and \n"
                + "p.pkProcedure=pa.procedures.pkProcedure and \n"
                + "p.pkProcedure=pr.procedures.pkProcedure and \n"
                + "vc.pkVetConsultation='" + selectItem.getConsultation().getPkVetConsultation() + "'");
        if (list != null && !list.isEmpty()) {
            for (Object[] object : (List<Object[]>) list) {
                ItemProceduresApplied item = new ItemProceduresApplied();
                item.setApplied((ProceduresApplied) object[0]);
                item.setProcedure((Procedures) object[1]);
                item.setPrice((Prices) object[2]);
                listProceduresApplied.add(item);
            }
        }

//      implementar aqui a função para buscar 
//      a categoria pertencente ao procedimento.
    }

    public void onRowEdit(RowEditEvent event) {
        ItemProceduresApplied obj = (ItemProceduresApplied) event.getObject();
        getDaoGenerico().update(obj.getApplied());
    }

    public void onRowCancel(RowEditEvent event) {

    }

    // GETs & SET --------------------------------------------------------------
    public List<ItemProceduresApplied> getListProceduresApplied() {
        return listProceduresApplied;
    }
}
