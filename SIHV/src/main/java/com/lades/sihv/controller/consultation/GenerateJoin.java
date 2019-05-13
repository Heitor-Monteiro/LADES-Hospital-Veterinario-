/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller.consultation;

import com.lades.sihv.controller.ModuleToCollectError;
import com.lades.sihv.controller.VariablesSearch;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author thiberius
 */
public class GenerateJoin implements Serializable {

    public String methodGenerateJoin(VariablesSearch objVarSearch) {
        String hql = "";
        try {
            String join = "";
            switch (objVarSearch.getItemSearch()) {
                case "RGHV":
                    join = "and \n s.pkSmallAnimal='" + objVarSearch.getTextSearch() + "'";
                    break;
                case "NameAnimal":
                    join = "and \n a.animalName like '%" + objVarSearch.getTextSearch() + "%'";
                    break;
                case "NameOwner":
                    join = "and \n p.namePerson like '%" + objVarSearch.getTextSearch() + "%'";
                    break;
                case "BetweenDates":
                    DateFormat formatUS = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                    String x = formatUS.format(objVarSearch.getDateInitial()).substring(0, 11) + "01:00:00";
                    String y = formatUS.format(objVarSearch.getDateEnd()).substring(0, 11) + "23:59:59";
                    join = "and \n a.registrationDate>='" + x + "' and \n "
                            + "a.registrationDate<='" + y + "'";
                    break;
            }
            hql = "select \n"
                    + "s.pkSmallAnimal, \n"
                    + "a.pkAnimal, a.animalName, \n"
                    + "r.nameRaces, sp.nameSpecies, \n"
                    + "p.pkPerson, p.namePerson \n"
                    + "from People p, Owners o, OwnersHasAnimals oh, Animals a, SmallAnimal s, Races r, Species sp \n"
                    + "where \n"
                    + "p.pkPerson=o.people.pkPerson and \n"
                    + "o.pkOwner=oh.owners.pkOwner and \n"
                    + "oh.animals.pkAnimal=a.pkAnimal and \n"
                    + "a.pkAnimal=s.animals.pkAnimal and \n"
                    + "r.id.pkRaces=s.races.id.pkRaces and \n"
                    + "sp.id.pkSpecies=r.species.id.pkSpecies \n " + join;
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO public void methodGenerateJoin(): " + e.toString());
            new ModuleToCollectError().erroPage500("GenerateJoin > methodGenerateJoin", e.toString());
        }
        return hql;
    }
}
