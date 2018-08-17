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
import com.lades.sihv.controller.scheduleConsulta.OtherMethods;
import com.lades.sihv.controller.ListRenderedFields;
import com.lades.sihv.controller.RenderedFields;
import com.lades.sihv.controller.VariablesSearch;
import com.lades.sihv.model.Scheduling;
import com.lades.sihv.model.TemporaryClientData;
import com.lades.sihv.model.Animais;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import javax.faces.event.ActionEvent;
import org.primefaces.model.ScheduleEvent;

//@author thiberius
@ManagedBean(name = "MBscheduleConsulta")
@ViewScoped

public class MBscheduleConsulta extends AbstractBean {

    private GenericScheduling geneScheduling;
    private ListsForConsultaScheduling lists;
    private OtherMethods otherMethods;
    private Scheduling schedule;
    private TemporaryClientData tempCliData;
    private ListRenderedFields listRenderedFields;
    private VariablesSearch variablesSearch;
    private Animais animalSelect;
    private String num;
    private String timeConsulta;
    private String textButtonSchedule;

    @PostConstruct
    public void init() {
        System.out.println("BACK-END WARNING: MBscheduleConsulta initiated");
        geneScheduling = new GenericScheduling();
        lists = new ListsForConsultaScheduling();
        otherMethods = new OtherMethods();
        schedule = new Scheduling();
        tempCliData = new TemporaryClientData();
        listRenderedFields = new ListRenderedFields(10);
        listRenderedFields.startIndexListViewFields();
        variablesSearch = new VariablesSearch();
        timeConsulta = "";
        textButtonSchedule = "";

        lists.popularEventModel(geneScheduling);
    }

    public void listingOfAnimalsAndProprietary() {
        lists.listingOfAnimalsAndProprietary(variablesSearch, num);
    }

    private void captureAnimalNameRegistered() {
        if (schedule.getRegisterClient().equals("Cadastrado")) {
            ScheduleEvent event = new DefaultScheduleEvent(animalSelect.getNomeAnimal(),
                    geneScheduling.getEvent().getStartDate(),
                    geneScheduling.getEvent().getStartDate());
            ScheduleEvent eventDelete = geneScheduling.getEvent();
            geneScheduling.getEventModel().deleteEvent(eventDelete);
            if (geneScheduling.getEvent().getId() != null) {
                geneScheduling.getEventModel().addEvent(event);
            }
            geneScheduling.setEvent(event);
        }
    }

    public void addEvent(ActionEvent actionEvent) {

        try {
            String text = new SimpleDateFormat().format(geneScheduling.getEvent().getStartDate());
            text = text.replace("00:00", "");
            text += timeConsulta;
            Date date = new SimpleDateFormat().parse(text);
            geneScheduling.getEvent().getStartDate().setTime(date.getTime());
        } catch (ParseException e) {
            System.out.println("ERRO!!!");
        }

        captureAnimalNameRegistered();

        if (geneScheduling.addEvent(actionEvent)) {
            System.out.println("public void addEvent(ActionEvent actionEvent): !!! SALVE STARTED !!!");
            schedule.setSchedulingDate(geneScheduling.getEvent().getStartDate());
            schedule.setStatusService("agendado(a)");
            if (schedule.getRegisterClient().equals("Cadastrado")) {
                schedule.setAnimais(animalSelect);
                getDaoGenerico().save(schedule);
            } else {
                tempCliData.setAnimalName(geneScheduling.getEvent().getTitle());
                tempCliData.setScheduling(schedule);
                getDaoGenerico().save(schedule);
                getDaoGenerico().save(tempCliData);
            }
            getObjMessage().info("Agendamento salvo!", "Consulta marcada com sucesso");
        } else {
            System.out.println("public void addEvent(ActionEvent actionEvent): !!! UPDATE STARTED !!!");
            schedule.setSchedulingDate(geneScheduling.getEvent().getStartDate());
            if (schedule.getRegisterClient().equals("Cadastrado")) {
                schedule.setAnimais(animalSelect);
                getDaoGenerico().update(schedule);
            } else {
                tempCliData.setAnimalName(geneScheduling.getEvent().getTitle());
                getDaoGenerico().update(schedule);
                getDaoGenerico().update(tempCliData);
            }
            getObjMessage().info("Agendamento atualizado!", "Atualização efetivada com sucesso");
        }
        lists.popularListSchedule();
        lists.popularListEventID(geneScheduling);
        geneScheduling.setEvent(new DefaultScheduleEvent());
    }

    public void cancelEvent(ActionEvent actionEvent) {
        listRenderedFields.getListViewFields(1).setViewVariableBoolean(false);
        if (geneScheduling.cancelEvent(actionEvent)) {
            schedule.setStatusService("cancelado(a)");
            getDaoGenerico().update(schedule);
            lists.popularListSchedule();
            lists.popularListEventID(geneScheduling);
            getObjMessage().info("Consulta cancelada!", "O agendamento foi removido do calendário");
        }
    }

    public void onDateSelect(SelectEvent selectEvent) {
        textButtonSchedule = "Agendar";
        schedule = new Scheduling();
        schedule.setTypeService("Nova consulta");
        tempCliData = new TemporaryClientData();
        variablesSearch = new VariablesSearch();
        timeConsulta = "";
        listRenderedFields.startIndexListViewFields();
        lists.getListPerson().clear();
        lists.getListAnimal().clear();
        lists.setListPhones("");
        geneScheduling.onDateSelect(selectEvent);
    }

    private boolean checkRegisteredClientForm() {
        boolean var = false;
        if (schedule.getRegisterClient().equals("Cadastrado")) {
            List<Boolean> x = new ArrayList<>();
            x.add(0, geneScheduling.getEvent().getStartDate() != null);
            x.add(1, !timeConsulta.isEmpty());
            x.add(2, !lists.getListPerson().get(0).getNome().isEmpty());
            x.add(3, animalSelect != null);
            if (x.get(0) && x.get(1) && x.get(2) && x.get(3)) {
                var = true;
            }
        }
        return var;
    }

    private boolean checkUnregisteredClientForm() {
        boolean var = false;
        if (schedule.getRegisterClient().equals("Novo")) {
            List<Boolean> x = new ArrayList<>();
            x.add(0, geneScheduling.getEvent().getStartDate() != null);
            x.add(1, !timeConsulta.isEmpty());
            x.add(2, !tempCliData.getProprietaryName().isEmpty());
            x.add(3, !tempCliData.getAnimalName().isEmpty());
            x.add(4, !tempCliData.getProprietaryPhones().isEmpty());
            if (x.get(0) && x.get(1) && x.get(2) && x.get(3) && x.get(4)) {
                var = true;
            }
        }
        return var;
    }

    private boolean otherClient() {
        return checkRegisteredClientForm();
    }

    public void toggleFields() {
        if (listRenderedFields.getListViewFields(6).isViewVariableBoolean()) {
            listRenderedFields.getListViewFields(7).setViewVariableBoolean(false);
        }
    }

    private void lockFieldToChangeClientRecord() {
        boolean var = false;
        if (checkRegisteredClientForm()) {
            var = true;
        } else if (checkUnregisteredClientForm()) {
            var = true;
        }
        listRenderedFields.getListViewFields(8).setViewVariableBoolean(var);
    }

    private void methodToChangeAddButton() {
        boolean var = false;
        var = checkUnregisteredClientForm();
        listRenderedFields.getListViewFields(9).setViewVariableBoolean(var);
    }

    public void onEventSelect(SelectEvent selectEvent) {
        try {
            textButtonSchedule = "Atualizar";
            variablesSearch = new VariablesSearch();
            listRenderedFields.startIndexListViewFields();
            geneScheduling.onEventSelect(selectEvent);
            for (int i = 0; i < lists.getListEventID().size(); i++) {
                if (lists.getListEventID().get(i).equals(geneScheduling.getEvent().getId())) {
                    schedule = lists.getListSchedule().get(i);
                    timeConsulta = "" + schedule.getSchedulingDate();
                    timeConsulta = timeConsulta.substring(11, 16);
                    switch (schedule.getRegisterClient()) {
                        case "Cadastrado":
                            boolean var = lists.selectClientRegistered(schedule);
                            animalSelect = lists.getListAnimal().get(0);
                            listRenderedFields.getListViewFields(1).setViewVariableBoolean(var);
                            listRenderedFields.getListViewFields(5).setViewVariableBoolean(var);
                            listRenderedFields.getListViewFields(6).setViewVariableBoolean(!otherClient());
                            listRenderedFields.getListViewFields(7).setViewVariableBoolean(otherClient());
                            break;
                        case "Novo":
                            for (TemporaryClientData objTemp : lists.getListTempCliData()) {
                                if (Objects.equals(schedule.getPkSchedule(), objTemp.getScheduling().getPkSchedule())) {
                                    tempCliData = objTemp;
                                    listRenderedFields.getListViewFields(4).setViewVariableBoolean(true);
                                    break;
                                }
                            }
                            break;
                    }
                }
            }
            lockFieldToChangeClientRecord();
            methodToChangeAddButton();
        } catch (Exception e) {
            System.out.println("Ocorreu um erro!, Método: public void onEventSelect()"
                    + "\n erro:" + e);
        }
    }

    //-GETs e SETs--------------------------------------------------------------
    public Scheduling getSchedule() {
        return schedule;
    }

    public void setSchedule(Scheduling schedule) {
        this.schedule = schedule;
    }

    public TemporaryClientData getTempCliData() {
        return tempCliData;
    }

    public void setTempCliData(TemporaryClientData tempCliData) {
        this.tempCliData = tempCliData;
    }

    public VariablesSearch getVariablesSearch() {
        return variablesSearch;
    }

    public void setVariablesSearch(VariablesSearch variablesSearch) {
        this.variablesSearch = variablesSearch;
    }

    public Animais getAnimalSelect() {
        return animalSelect;
    }

    public void setAnimalSelect(Animais animalSelect) {
        this.animalSelect = animalSelect;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTimeConsulta() {
        return timeConsulta;
    }

    public void setTimeConsulta(String timeConsulta) {
        this.timeConsulta = timeConsulta;
    }

    public String getTextButtonSchedule() {
        return textButtonSchedule;
    }

    // methods of view ---------------------------------------------------------
    public boolean getViewCancelButton() {
        boolean var = false;
        if (geneScheduling.getEvent().getId() != null) {
            var = true;
        }
        return var;
    }

    public RenderedFields getViewIfReturn() {
        if (schedule.getTypeService() != null) {
            if (schedule.getTypeService().equals("Retorno")) {
                listRenderedFields.getListViewFields(0).setViewVariableBoolean(true);
            }
        }
        return listRenderedFields.getListViewFields(0);
    }

    public void actionsClientRegisteredORnew() {
        otherMethods.actionsClientRegisteredORnew(schedule, listRenderedFields, variablesSearch);
    }

    public void methodViewFieldsCPFandRGorRGHV() {
        otherMethods.methodViewFieldsCPFandRGorRGHV(variablesSearch, listRenderedFields);
    }

    public RenderedFields getViewFieldsClientRegistered() {
        return listRenderedFields.getListViewFields(1);
    }

    public RenderedFields getViewFieldsCPForRG() {
        return listRenderedFields.getListViewFields(2);
    }

    public RenderedFields getViewFieldsRGHVanimal() {
        return listRenderedFields.getListViewFields(3);
    }

    public RenderedFields getViewFieldsNewClient() {
        return listRenderedFields.getListViewFields(4);
    }

    public RenderedFields getViewFieldsAnimalSetect() {
        return listRenderedFields.getListViewFields(5);
    }

    public RenderedFields getViewClientSearchOptions() {
        return listRenderedFields.getListViewFields(6);
    }

    public RenderedFields getViewScheduleOtherClient() {
        return listRenderedFields.getListViewFields(7);
    }

    public RenderedFields getViewFieldStatusToChangeClientRegistered() {
        return listRenderedFields.getListViewFields(8);
    }

    public RenderedFields getViewChangeAddButton() {
        return listRenderedFields.getListViewFields(9);
    }

    public String getProprietaryName() {
        String name = "";
        if (!lists.getListPerson().isEmpty()) {
            name = lists.getListPerson().get(0).getNome();
        }
        return name;
    }

    public GenericScheduling getGeneScheduling() {
        return geneScheduling;
    }

    public ListsForConsultaScheduling getLists() {
        return lists;
    }
}
