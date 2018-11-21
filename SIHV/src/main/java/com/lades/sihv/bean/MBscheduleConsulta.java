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
import com.lades.sihv.controller.ModuleToCollectError;
import com.lades.sihv.controller.RenderedFields;
import com.lades.sihv.controller.logBook.SaveLogControl;
import com.lades.sihv.model.Scheduling;
import com.lades.sihv.model.NewAnimalAndOwner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

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
        try {
            System.out.println("►►►►►►►►►►►►► MBscheduleConsulta initiated");
            geneScheduling = new GenericScheduling();
            schedule = new Scheduling();
            tempCliData = new NewAnimalAndOwner();
            lists = new ListsForConsultaScheduling();
            lists.popularEventModel(geneScheduling);
            listRenderedFields = new ListRenderedFields(3);
            textButtonSchedule = "Agendar";
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO public void init(): " + e.toString());
            new ModuleToCollectError().erroPage500("MBscheduleConsulta > init", e.toString());
        }
    }

    public void addEvent(ActionEvent actionEvent) {
        try {
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
                RequestContext.getCurrentInstance().execute("PF('eventDialog').hide();");
                new SaveLogControl().saveLog(7, descriptionLog());
            } else {
                System.out.println("public void addEvent(ActionEvent actionEvent): !!! UPDATE STARTED !!!");
                schedule.setSchedulingDate(geneScheduling.getEvent().getStartDate());
                tempCliData.setAnimalName(geneScheduling.getEvent().getTitle());
                getDaoGenerico().update(schedule);
                getDaoGenerico().update(tempCliData);
                getObjMessage().info("Agendamento atualizado!", "Atualização efetivada com sucesso");
                RequestContext.getCurrentInstance().execute("PF('eventDialog').hide();");
                new SaveLogControl().saveLog(9, descriptionLog());
            }
            lists.popularListSchedule();
            lists.popularListEventID(geneScheduling);
            geneScheduling.setEvent(new DefaultScheduleEvent());
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO public void addEvent(): " + e.toString());
            new ModuleToCollectError().erroPage500("MBscheduleConsulta > addEvent", e.toString());
        }
    }

    public void cancelEvent(ActionEvent actionEvent) {
        try {
            if (geneScheduling.cancelEvent(actionEvent)) {
                schedule.setStatusService("cancelado(a)");
                getDaoGenerico().update(schedule);
                lists.popularListSchedule();
                lists.popularListEventID(geneScheduling);
                getObjMessage().info("Consulta cancelada!", "O agendamento foi removido do calendário");
                new SaveLogControl().saveLog(8, descriptionLog());
            }
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO public void cancelEvent(): " + e.toString());
            new ModuleToCollectError().erroPage500("MBscheduleConsulta > cancelEvent", e.toString());
        }
    }

    public void onDateSelect(SelectEvent selectEvent) {
        try {
            System.out.println("►►►►►►►►►►►►► public void onDateSelect(SelectEvent selectEvent)");
            listRenderedFields.getListViewFields(1).setViewVariableBoolean(false);
            listRenderedFields.getListViewFields(2).setViewVariableBoolean(false);
            setViewClientPresence(false);
            textButtonSchedule = "Agendar";
            timeConsultation = "";
            schedule = new Scheduling();
            schedule.setTypeService("Nova consulta");
            tempCliData = new NewAnimalAndOwner();
            geneScheduling.onDateSelect(selectEvent);
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO public void onDateSelect(): " + e.toString());
            new ModuleToCollectError().erroPage500("MBscheduleConsulta > onDateSelect", e.toString());
        }
    }

    public void onEventSelect(SelectEvent selectEvent) throws IOException {
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
            disableProprietaryPhone2();
            disableProprietaryPhone3();
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO public void onEventSelect(): " + e.toString());
            new ModuleToCollectError().erroPage500("MBscheduleConsulta > onEventSelect", e.toString());
        }
    }

    public void buttonToLinkOwnerAndAnimal() {
        try {
            List<Object> list;
            list = new ArrayList<>();
            list.add(0, schedule);
            list.add(1, tempCliData);
            getVariaveisDeSessao().setObjetoTemp(list);
            getObjTools().redirectView("/SIHV/faces/sihv-telas/linkOwnerAndAnimal.xhtml");
        } catch (IOException e) {
            System.out.println("►►►►►►►►►►►►► ERRO public void buttonToLinkOwnerAndAnimal(): " + e);
            new ModuleToCollectError().erroPage500("MBscheduleConsulta > buttonToLinkOwnerAndAnimal", e.toString());
        }
    }

    private String descriptionLog() {
        return "Proprietário:" + tempCliData.getProprietaryName()
                + ", Animal:" + tempCliData.getAnimalName()
                + ", Data e hora da consulta: " + schedule.getSchedulingDate()
                + ", Telefones:" + tempCliData.getProprietaryPhone1()
                + ", " + tempCliData.getProprietaryPhone2()
                + ", " + tempCliData.getProprietaryPhone3();
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

    // disable ProprietaryPhone 2 and 3 ----------------------------------------
    public void disableProprietaryPhone2() {
        try {
            String phone2 = tempCliData.getProprietaryPhone1().replace("_", "");
            if (phone2.length() == 15) {
                listRenderedFields.getListViewFields(1).setViewVariableBoolean(true);
            } else {
                listRenderedFields.getListViewFields(1).setViewVariableBoolean(false);
            }
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO public void disableProprietaryPhone2(): " + e.toString());
            new ModuleToCollectError().erroPage500("MBscheduleConsulta > disableProprietaryPhone2", e.toString());
        }
    }

    public RenderedFields getUseProprietaryPhone2() {
        return listRenderedFields.getListViewFields(1);
    }

    public void disableProprietaryPhone3() {
        try {
            String phone3 = tempCliData.getProprietaryPhone2().replace("_", "");
            if (phone3.length() == 15) {
                listRenderedFields.getListViewFields(2).setViewVariableBoolean(true);
            } else {
                listRenderedFields.getListViewFields(2).setViewVariableBoolean(false);
            }
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO public void disableProprietaryPhone3(): " + e.toString());
            new ModuleToCollectError().erroPage500("MBscheduleConsulta > disableProprietaryPhone3", e.toString());
        }
    }

    public RenderedFields getUseProprietaryPhone3() {
        return listRenderedFields.getListViewFields(2);
    }
}
