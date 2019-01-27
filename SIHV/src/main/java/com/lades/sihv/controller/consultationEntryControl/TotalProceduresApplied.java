/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consultationEntryControl;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.model.Prices;
import com.lades.sihv.model.ProceduresApplied;
import java.text.DecimalFormat;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class TotalProceduresApplied extends AbstractBean {
    
    public void listProceduresApplied(DashConsultationEntry dash, List<ConsultationEntryItem> listItens) {
        double total = 0;
        double totalPaid = 0;
        double totalOutstandingPayable = 0;
        double totalDiscount = 0;
        DecimalFormat df = new DecimalFormat("###,###,###,###,###.00");
        for (ConsultationEntryItem listIten : listItens) {
            List<?> list = getDaoGenerico().list("select pa,pr from "
                    + "VetConsultation v, ProceduresApplied pa, Procedures pc, Prices pr \n"
                    + "where \n"
                    + "v.pkVetConsultation=pa.vetConsultation.pkVetConsultation and \n"
                    + "pa.procedures.pkProcedure=pc.pkProcedure and \n"
                    + "pc.pkProcedure=pr.procedures.pkProcedure and \n"
                    + "v.pkVetConsultation='" + listIten.getConsultation().getPkVetConsultation() + "'");
            if (list != null && !list.isEmpty()) {
                double subTotal = 0;
                for (Object[] object : (List<Object[]>) list) {
                    Prices price = (Prices) object[1];
                    listIten.setSubTotal(listIten.getSubTotal() + price.getPrice().doubleValue());
                    total += price.getPrice().doubleValue();
                    ProceduresApplied pa = (ProceduresApplied) object[0];
                    if (!pa.isPaymentStatus()) {
                        totalOutstandingPayable += price.getPrice().doubleValue();
                    } else {
                        totalPaid += price.getPrice().doubleValue();
                    }
                }
                for (Object[] object : (List<Object[]>) list) {
                    ProceduresApplied pa = (ProceduresApplied) object[0];
                    if (!pa.isPaymentStatus()) {
                        listIten.setPendingPayment(false);
                        break;
                    } else {
                        listIten.setPendingPayment(true);
                    }
                }
                if (listIten.isPendingPayment()) {
                    totalPaid -= listIten.getConsultation().getDiscountValue().doubleValue();
                } else {
                    totalOutstandingPayable -= listIten.getConsultation().getDiscountValue().doubleValue();
                }
            }
            listIten.setSubTotal(listIten.getSubTotal() - listIten.getConsultation().getDiscountValue().doubleValue());
            totalDiscount += listIten.getConsultation().getDiscountValue().doubleValue();
            listIten.setSubTotalText(df.format(listIten.getSubTotal()));
        }
        dash.setTotal(total - totalDiscount);
        dash.setTotalPaid(totalPaid);
        dash.setTotalOutstandingPayable(totalOutstandingPayable);
    }
}
