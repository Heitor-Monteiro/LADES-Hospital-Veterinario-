/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consultation;

import com.lades.sihv.bean.AbstractBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class SearchConsultationData extends AbstractBean {

    private InitialConsultationData selectInitialConsultationData;
    private final List<InitialConsultationData> listInitialConsultationData;
    private List<InitialConsultationData> filterInitialConsultationData;

    public SearchConsultationData() {
        listInitialConsultationData = new ArrayList();
    }

    public void methodSearchOfConsultation(Integer pkAnimal) {
        listInitialConsultationData.clear();
        List<?> tempList = getDaoGenerico().list(
                "select vc.applicationDate, sc.typeService, p.namePerson, sc.schedulingDate \n"
                + "from VetConsultation vc, Animals a, OwnersHasAnimals oha, People p, Users u, Scheduling sc \n"
                + "where \n"
                + "a.pkAnimal=oha.animals.pkAnimal and \n"
                + "oha.pkOwnersHasAnimals=vc.ownersHasAnimals.pkOwnersHasAnimals and \n"
                + "p.pkPerson=u.people.pkPerson and \n"
                + "u.pkUser=vc.users.pkUser and \n"
                + "vc.scheduling.pkSchedule=sc.pkSchedule and \n"
                + "a.pkAnimal='" + pkAnimal + "'");
        if (!tempList.isEmpty()) {
            for (Object[] obj : (List<Object[]>) tempList) {
                InitialConsultationData tempObj = new InitialConsultationData();
                tempObj.setApplicationDate((Date) obj[0]);
                tempObj.setTextApplicationDate(tempObj.getApplicationDate().toString());
                tempObj.setTypeService((String) obj[1]);
                tempObj.setNamePerson((String) obj[2]);
                tempObj.setSchedulingDate((Date) obj[3]);
                tempObj.setTextSchedulingDate(tempObj.getSchedulingDate().toString());

                listInitialConsultationData.add(tempObj);
            }
        }

        cleanTableFilter(listInitialConsultationData, filterInitialConsultationData);
    }

    private void cleanTableFilter(List<InitialConsultationData> listDadosIniciaisDeConsulta,
            List<InitialConsultationData> filterDadosIniciaisDeConsulta) {
        if (filterDadosIniciaisDeConsulta != null) {
            filterDadosIniciaisDeConsulta.clear();
            filterDadosIniciaisDeConsulta.addAll(listDadosIniciaisDeConsulta);
        }
    }

    // GETs & SETs -------------------------------------------------------------
    public List<InitialConsultationData> getListInitialConsultationData() {
        return listInitialConsultationData;
    }

    public List<InitialConsultationData> getFilterInitialConsultationData() {
        return filterInitialConsultationData;
    }

    public void setFilterInitialConsultationData(List<InitialConsultationData> filterInitialConsultationData) {
        this.filterInitialConsultationData = filterInitialConsultationData;
    }

    public InitialConsultationData getSelectInitialConsultationData() {
        return selectInitialConsultationData;
    }

    public void setSelectInitialConsultationData(InitialConsultationData selectInitialConsultationData) {
        this.selectInitialConsultationData = selectInitialConsultationData;
    }
}
