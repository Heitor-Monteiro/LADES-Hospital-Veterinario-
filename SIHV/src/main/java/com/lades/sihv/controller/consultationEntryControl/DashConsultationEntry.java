/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consultationEntryControl;

import java.io.Serializable;

/**
 *
 * @author thiberius
 */
public class DashConsultationEntry implements Serializable {

    private int numberOfConsultations;
    private double total;
    private double totalPaid;
    private double totalOutstandingPayable;

    // GETs & SETs -------------------------------------------------------------
    public int getNumberOfConsultations() {
        return numberOfConsultations;
    }

    public void setNumberOfConsultations(int numberOfConsultations) {
        this.numberOfConsultations = numberOfConsultations;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(double totalPaid) {
        this.totalPaid = totalPaid;
    }

    public double getTotalOutstandingPayable() {
        return totalOutstandingPayable;
    }

    public void setTotalOutstandingPayable(double totalOutstandingPayable) {
        this.totalOutstandingPayable = totalOutstandingPayable;
    }
}
