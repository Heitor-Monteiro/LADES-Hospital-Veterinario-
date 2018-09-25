/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.lades.sihv.controller.GenericScheduling;
import com.lades.sihv.controller.scheduleConsulta.ListsForConsultaScheduling;
import com.lades.sihv.controller.scheduleConsulta.EnterEventTime;
import com.lades.sihv.controller.scheduleConsulta.CheckSchedulingForm;
import com.lades.sihv.controller.ListRenderedFields;
import com.lades.sihv.controller.RenderedFields;
import com.lades.sihv.model.Scheduling;
import com.lades.sihv.model.NewAnimalAndOwner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import javax.faces.event.ActionEvent;

//@author thiberius
@ManagedBean(name = "MBscheduleConsulta")
@ViewScoped

public class MBscheduleConsulta extends AbstractBean {

    private GenericScheduling geneScheduling;
    private Scheduling schedule;
    private NewAnimalAndOwner tempCliData;
    private ListsForConsultaScheduling lists;
    private String timeConsultation;
    private String textButtonSchedule;
    private ListRenderedFields listRenderedFields;

    @PostConstruct
    public void init() {
        System.out.println("BACK-END WARNING: MBscheduleConsulta initiated");
        geneScheduling = new GenericScheduling();
        schedule = new Scheduling();
        tempCliData = new NewAnimalAndOwner();
        lists = new ListsForConsultaScheduling();
        lists.popularEventModel(geneScheduling);
        listRenderedFields = new ListRenderedFields(2);
        textButtonSchedule = "Agendar";
    }

    public void addEvent(ActionEvent actionEvent) {
        new EnterEventTime().insertTimeMethod(geneScheduling, timeConsultation);
        if (geneScheduling.addEvent(actionEvent)) {
            System.out.println("public void addEvent(ActionEvent actionEvent): !!! SALVE STARTED !!!");
            schedule.setSchedulingDate(geneScheduling.getEvent().getStartDate());
            schedule.setStatusService("agendado(a)");
            tempCliData.setScheduling(schedule);
            tempCliData.setAnimalName(geneScheduling.getEvent().getTitle());
            getDaoGenerico().save(schedule);
            getDaoGenerico().save(tempCliData);
            getObjMessage().info("Agendamento salvo!", "Consulta marcada com sucesso");
        } else {
            System.out.println("public void addEvent(ActionEvent actionEvent): !!! UPDATE STARTED !!!");
            schedule.setSchedulingDate(geneScheduling.getEvent().getStartDate());
            tempCliData.setAnimalName(geneScheduling.getEvent().getTitle());
            getDaoGenerico().update(schedule);
            getDaoGenerico().update(tempCliData);
            getObjMessage().info("Agendamento atualizado!", "Atualização efetivada com sucesso");
        }
        lists.popularListSchedule();
        lists.popularListEventID(geneScheduling);
        geneScheduling.setEvent(new DefaultScheduleEvent());
    }

    public void cancelEvent(ActionEvent actionEvent) {
        if (geneScheduling.cancelEvent(actionEvent)) {
            schedule.setStatusService("cancelado(a)");
            getDaoGenerico().update(schedule);
            lists.popularListSchedule();
            lists.popularListEventID(geneScheduling);
            getObjMessage().info("Consulta cancelada!", "O agendamento foi removido do calendário");
        }
    }

    public void onDateSelect(SelectEvent selectEvent) {
        System.out.println("BACK-END WARNING: public void onDateSelect(SelectEvent selectEvent)");
        setViewClientPresence(false);
        textButtonSchedule = "Agendar";
        timeConsultation = "";
        schedule = new Scheduling();
        schedule.setTypeService("Nova consulta");
        tempCliData = new NewAnimalAndOwner();
        geneScheduling.onDateSelect(selectEvent);
    }

    public void onEventSelect(SelectEvent selectEvent) {
        try {
            setViewClientPresence(true);
            textButtonSchedule = "Atualizar";
            geneScheduling.onEventSelect(selectEvent);
            for (int i = 0; i < lists.getListEventID().size(); i++) {
                if (lists.getListEventID().get(i).equals(geneScheduling.getEvent().getId())) {
                    schedule = lists.getListSchedule().get(i);
                    timeConsultation = "" + schedule.getSchedulingDate();
                    timeConsultation = timeConsultation.substring(11, 16);
                    tempCliData = lists.getListTempCliData().get(i);
                    i = lists.getListEventID().size();
                }
            }
        } catch (Exception e) {
            System.out.println("BACK-END WARNING: ERRO public void onEventSelect(): " + e);
        }
    }

    public void buttonToLinkOwnerAndAnimal() {
        try {
            List<Object> list;
            list = new ArrayList<>();
            list.add(0, schedule);
            list.add(1, tempCliData);
            getVariaveisDeSessao().setObjetoTemp(list);
            getObjTools().redirecionar("/SIHV/faces/sihv-telas/linkOwnerAndAnimal.xhtml");
        } catch (IOException e) {
            System.out.println("BACK-END WARNING: ERRO public void buttonToLinkOwnerAndAnimal(): " + e);
        }
    }

    //-GETs e SETs--------------------------------------------------------------
    public GenericScheduling getGeneScheduling() {
        return geneScheduling;
    }

    public Scheduling getSchedule() {
        return schedule;
    }

    public NewAnimalAndOwner getTempCliData() {
        return tempCliData;
    }

    public String getTimeConsultation() {
        return timeConsultation;
    }

    public void setTimeConsultation(String timeConsultation) {
        this.timeConsultation = timeConsultation;
    }

    public String getTextButtonSchedule() {
        return textButtonSchedule;
    }

    //--------------------------------------------------------------------------
    public String getClientPresenceText() {
        return (schedule.getOwnersHasAnimals() != null) ? "Confirmada" : "Não confirmada";
    }

    public RenderedFields getViewClientPresence() {
        return listRenderedFields.getListViewFields(0);
    }

    private void setViewClientPresence(boolean viewVariableBoolean) {
        listRenderedFields.getListViewFields(0).setViewVariableBoolean(viewVariableBoolean);
    }

    public boolean getViewInterleaveAddButton() {
        return new CheckSchedulingForm().checkScheduling(schedule, tempCliData);
    }

    public boolean getCustomerLinkedBlockForm() {
        return schedule.getOwnersHasAnimals() != null;
    }
}
