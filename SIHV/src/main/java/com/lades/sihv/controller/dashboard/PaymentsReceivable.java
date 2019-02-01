/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.dashboard;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.model.Prices;
import com.lades.sihv.model.VetConsultation;
import java.text.DecimalFormat;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class PaymentsReceivable extends AbstractBean {

    private double subtotal;
    private double totalDiscount;
    private double paymentReceivable;
    private DecimalFormat df;

    public PaymentsReceivable() {
        subtotal = 0;
        totalDiscount = 0;
        paymentReceivable = 0;
        df = new DecimalFormat("###,###,###,###,##0.00");
        fetchPendingPayments();
    }

    private void fetchPendingPayments() {
        fetchInquiriesWithPendingPayments();
        searchForTotalProceduresApplied();
    }

    private void fetchInquiriesWithPendingPayments() {
        List<VetConsultation> list = getDaoGenerico().list("select vc from VetConsultation vc, ProceduresApplied pa \n"
                + "where \n"
                + "vc.pkVetConsultation=pa.vetConsultation.pkVetConsultation and \n"
                + "pa.paymentStatus='0' \n"
                + "group by vc.pkVetConsultation");
        if (list != null && !list.isEmpty()) {
            for (VetConsultation obj : list) {
                totalDiscount += obj.getDiscountValue().doubleValue();
            }
        }
    }

    private void searchForTotalProceduresApplied() {
        List<Prices> list = getDaoGenerico().list("select pri from "
                + "VetConsultation vc, ProceduresApplied pa, Procedures pr, Prices pri \n"
                + "where \n"
                + "vc.pkVetConsultation=pa.vetConsultation.pkVetConsultation and \n"
                + "pa.paymentStatus='0' and \n"
                + "pa.procedures.pkProcedure=pr.pkProcedure and \n"
                + "pr.pkProcedure=pri.pkPrice ");
        if (list != null && !list.isEmpty()) {
            for (Prices prices : list) {
                subtotal += prices.getPrice().doubleValue();
            }
        }
        paymentReceivable = subtotal - totalDiscount;
    }

    // GETs & SETs -------------------------------------------------------------
    public String getSubtotal() {
        return df.format(subtotal);
    }

    public String getTotalDiscount() {
        return df.format(totalDiscount);
    }

    public String getPaymentReceivable() {
        return df.format(paymentReceivable);
    }
}
