/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.NewConsultation;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.animal.CollectAnimalRghv;
import com.lades.sihv.model.Animals;
import com.lades.sihv.model.NewAnimalAndOwner;
import com.lades.sihv.model.OwnersHasAnimals;
import com.lades.sihv.model.Scheduling;
import com.lades.sihv.model.SmallAnimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class SearchForConfirmedSchedules extends AbstractBean {
    
    public void searchForConfirmedSchedules(List<SchedulesConfirmedForConsultation> schedulesConfirmedForConsultation) {
        try {
            schedulesConfirmedForConsultation.clear();
            List<?> list = getDaoGenerico().list("select s, n, h from Scheduling s, NewAnimalAndOwner n, OwnersHasAnimals h \n"
                    + "where \n"
                    + "s.pkSchedule=n.scheduling.pkSchedule and \n"
                    + "s.statusService = 'confirmado(a)' and \n"
                    + "s.ownersHasAnimals.pkOwnersHasAnimals=h.pkOwnersHasAnimals ");
            if (list.size() > 0) {
                for (Object[] object : (List<Object[]>) list) {
                    SchedulesConfirmedForConsultation obj = new SchedulesConfirmedForConsultation();
                    obj.setSchedule((Scheduling) object[0]);
                    obj.setNewAnimalAndOwner((NewAnimalAndOwner) object[1]);
                    obj.setOwnersHasAnimals((OwnersHasAnimals) object[2]);
                    schedulesConfirmedForConsultation.add(obj);
                }
                
                for (SchedulesConfirmedForConsultation obj : schedulesConfirmedForConsultation) {
                    obj.setCpf(listCpfRg("cpf", "Cpf", obj.getSchedule().getOwnersHasAnimals().getPkOwnersHasAnimals()));
                    obj.setRg(listCpfRg("rg", "Rg", obj.getSchedule().getOwnersHasAnimals().getPkOwnersHasAnimals()));
                    List<Object> tempList = insertRGHVtheAnimal(obj.getSchedule().getOwnersHasAnimals().getPkOwnersHasAnimals());
                    obj.setAnimal((Animals)tempList.get(0));
                    obj.setRghv((String) tempList.get(1));
                }
            } else {
                getObjMessage().info("Não a agendamentos confirmados para consulta", "");
            }
            
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO public void searchForConfirmedSchedules():" + e);
        }
    }
    
    private String listCpfRg(String varCpfRg, String entityCpfRg, int pkOwnersHasAnimals) {
        String var = "Não consta";
        List<?> listCpfRg = getDaoGenerico().list("select x." + varCpfRg
                + " from People p, PhysicalPerson f, " + entityCpfRg + " x, Owners o, OwnersHasAnimals h \n"
                + "where \n"
                + "h.pkOwnersHasAnimals='" + pkOwnersHasAnimals + "' and \n"
                + "h.owners.pkOwner=o.pkOwner and \n"
                + "p.pkPerson=o.people.pkPerson and \n"
                + "f.id.peoplePkPerson=p.pkPerson and \n"
                + "f.id.pkPhysicalPerson=x.physicalPerson.id.pkPhysicalPerson ");
        if (listCpfRg != null) {
            if (listCpfRg.size() == 1) {
                var = "" + listCpfRg.get(0);
            } else if (listCpfRg.size() > 1) {
                var = "ERRO!!!";
            }
        }
        return var;
    }
    
    private List<Object> insertRGHVtheAnimal(int pkOwnersHasAnimals) {
        String rghv = "";
        List<Object> tempList = new ArrayList<>();
        List<?> list = getDaoGenerico().list("select a, s from OwnersHasAnimals h ,Animals a, SmallAnimal s \n"
                + "where \n"
                + "h.pkOwnersHasAnimals='" + pkOwnersHasAnimals + "' and \n"
                + "h.animals.pkAnimal=a.pkAnimal and \n"
                + "a.pkAnimal=s.animals.pkAnimal ");
        if (list != null) {
            if (list.size() == 1) {
                for (Object[] object : (List<Object[]>) list) {
                    Animals animal = (Animals) object[0];
                    tempList.add(0, object[0]);
                    SmallAnimal smallAnimal = (SmallAnimal) object[1];
                    rghv = new CollectAnimalRghv().methodCollectAnimalRghv(smallAnimal.getPkSmallAnimal(), "P", animal);
                    tempList.add(1, rghv);
                }
            } else {
                System.out.println("►►►►►►►►►►►►► ERRO public void insertRGHVtheAnimal(): list " + list.size());
            }
        }
        return tempList;
    }
    
}
