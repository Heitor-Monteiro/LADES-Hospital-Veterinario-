/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.schedulesConfirmed;

import com.lades.sihv.bean.*;
import com.lades.sihv.controller.animal.CollectAnimalRghv;
import com.lades.sihv.controller.logBook.SaveLogControl;
import com.lades.sihv.model.Animals;
import com.lades.sihv.model.NewAnimalAndOwner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.lades.sihv.model.Scheduling;
import com.lades.sihv.model.SmallAnimal;

//@author thiberius
@ManagedBean(name = "MBschedulesConfirmed")
@ViewScoped

public class MBschedulesConfirmed extends AbstractBean {

    private DateFormat formatUS;
    private Date dateInitial;
    private Date dateEnd;
    private List<TempListSchedulesConfirmed> tempListSchedulesConfirmed;
    private CollectAnimalRghv collectAnimalRghv;

    @PostConstruct
    public void init() {
        System.out.println("►►►►►►►►►►►►► MBschedulesConfirmed initiated");
        tempListSchedulesConfirmed = new ArrayList<>();
        formatUS = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        collectAnimalRghv = new CollectAnimalRghv();
    }

    public void searchForConfirmedSchedules() {
        try {
            int number = 0;
            tempListSchedulesConfirmed.clear();
            List<?> list = getDaoGenerico().list("select s, n from Scheduling s, NewAnimalAndOwner n \n"
                    + "where \n"
                    + "s.pkSchedule=n.scheduling.pkSchedule and \n"
                    + "s.statusService = 'confirmado(a)' and \n"
                    + "s.ownersHasAnimals.pkOwnersHasAnimals!='null' and \n"
                    + "s.schedulingDate>='" + formatUS.format(dateInitial) + "' and \n"
                    + "s.schedulingDate<='" + formatUS.format(dateEnd) + "' ");
            if (list.size() > 0) {
                for (Object[] object : (List<Object[]>) list) {
                    TempListSchedulesConfirmed obj = new TempListSchedulesConfirmed();
                    obj.setSchedule((Scheduling) object[0]);
                    obj.setNewAnimalAndOwner((NewAnimalAndOwner) object[1]);
                    tempListSchedulesConfirmed.add(obj);
                }

                for (TempListSchedulesConfirmed obj : tempListSchedulesConfirmed) {
                    obj.setCpf(listCpfRg("cpf", "Cpf", obj.getSchedule().getOwnersHasAnimals().getPkOwnersHasAnimals()));
                    obj.setRg(listCpfRg("rg", "Rg", obj.getSchedule().getOwnersHasAnimals().getPkOwnersHasAnimals()));
                    obj.setRghv(insertRGHVtheAnimal(obj.getSchedule().getOwnersHasAnimals().getPkOwnersHasAnimals()));
                }

                getObjMessage().info("Itens encontrados!",
                        "Agendas confirmadas no período estipulado: "
                        + tempListSchedulesConfirmed.size());
                number = tempListSchedulesConfirmed.size();
            } else {
                getObjMessage().info("Não a agendas confirmadas no período estipulado", "");
            }
            String initial, end;
            initial = "" + formatUS.format(dateInitial).substring(0, 10);
            end = "" + formatUS.format(dateEnd).substring(0, 10);
            new SaveLogControl().saveLog(12, "Período consultado: de "
                    + initial + " ate " + end
                    + " - Numero de registros achados: " + number);
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

    private String insertRGHVtheAnimal(int pkOwnersHasAnimals) {
        String rghv = "";
        List<?> list = getDaoGenerico().list("select a, s from OwnersHasAnimals h ,Animals a, SmallAnimal s \n"
                + "where \n"
                + "h.pkOwnersHasAnimals='" + pkOwnersHasAnimals + "' and \n"
                + "h.animals.pkAnimal=a.pkAnimal and \n"
                + "a.pkAnimal=s.animals.pkAnimal ");
        if (list != null) {
            if (list.size() == 1) {
                for (Object[] object : (List<Object[]>) list) {
                    Animals animal = (Animals) object[0];
                    SmallAnimal smallAnimal = (SmallAnimal) object[1];
                    rghv = collectAnimalRghv.methodCollectAnimalRghv(smallAnimal.getPkSmallAnimal(), "P", animal);
                }
            } else {
                System.out.println("►►►►►►►►►►►►► ERRO public void insertRGHVtheAnimal(): list " + list.size());
            }
        }
        return rghv;
    }

    //-GETs e SETs--------------------------------------------------------------
    public Date getDateInitial() {
        return dateInitial;
    }

    public void setDateInitial(Date dateInitial) {
        this.dateInitial = dateInitial;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public List<TempListSchedulesConfirmed> getTempListSchedulesConfirmed() {
        return tempListSchedulesConfirmed;
    }

    public boolean isViewTableSchedulesConfirmed() {
        return !tempListSchedulesConfirmed.isEmpty();
    }

}
