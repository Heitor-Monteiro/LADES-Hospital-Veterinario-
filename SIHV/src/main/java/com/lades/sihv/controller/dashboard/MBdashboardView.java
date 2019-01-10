/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.dashboard;

import com.lades.sihv.bean.AbstractBean;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

//@author thiberius
@ManagedBean(name = "MBdashboardView")
@ViewScoped

public class MBdashboardView extends AbstractBean {

    private DashboardModel model;
    //objetos que gerenciam as informações da dash.

    private List<DashboardColumn> columns;
    
    private ScheduledConsultations scheduledConsultations; 
    private TotalSmallAnimal totalSmallAnimal;
    private XrayPending xrayPending;
    
    
    @PostConstruct
    public void init() {
        System.out.println("►►►►►►►►►►►►► MBdashboardView initiated");
        scheduledConsultations = new ScheduledConsultations();
        totalSmallAnimal = new TotalSmallAnimal();
        xrayPending = new XrayPending();
        
        //----------------------------------------------------------------------
        model = new DefaultDashboardModel();
        createColumns();
        columns.get(0).addWidget("smallAnimal");
        columns.get(1).addWidget("xRay");
        columns.get(2).addWidget("scheduledConsultations");
        for (DashboardColumn column : columns) {
            model.addColumn(column);
        }
    }

    private void createColumns() {
        columns = new ArrayList();
        for (int i = 0; i < 3; i++) {
            DashboardColumn c = new DefaultDashboardColumn();
            columns.add(c);
        }
    }

    public void handleReorder(DashboardReorderEvent event) {
        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        message.setSummary("Reordered: " + event.getWidgetId());
        message.setDetail("Item index: " + event.getItemIndex() + ", Column index: " + event.getColumnIndex() + ", Sender index: " + event.getSenderColumnIndex());

        addMessage(message);
    }

    public void handleClose(CloseEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed", "Closed panel id:'" + event.getComponent().getId() + "'");

        addMessage(message);
    }

    public void handleToggle(ToggleEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled", "Status:" + event.getVisibility().name());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public DashboardModel getModel() {
        return model;
    }

    //-GETs e SETs--------------------------------------------------------------

    public ScheduledConsultations getScheduledConsultations() {
        return scheduledConsultations;
    }

    public TotalSmallAnimal getTotalSmallAnimal() {
        return totalSmallAnimal;
    }

    public XrayPending getXrayPending() {
        return xrayPending;
    }
    
}
