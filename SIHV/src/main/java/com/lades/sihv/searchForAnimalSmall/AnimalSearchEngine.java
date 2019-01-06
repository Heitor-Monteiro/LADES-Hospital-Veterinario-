/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.searchForAnimalSmall;

import com.lades.sihv.bean.AbstractBean;
import com.lades.sihv.controller.VariablesSearch;
import com.lades.sihv.model.Animals;
import com.lades.sihv.model.City;
import com.lades.sihv.model.Cpf;
import com.lades.sihv.model.FederationUnity;
import com.lades.sihv.model.Houses;
import com.lades.sihv.model.LegalPerson;
import com.lades.sihv.model.Neighborhood;
import com.lades.sihv.model.Owners;
import com.lades.sihv.model.People;
import com.lades.sihv.model.Phones;
import com.lades.sihv.model.PhysicalPerson;
import com.lades.sihv.model.Races;
import com.lades.sihv.model.Rg;
import com.lades.sihv.model.SmallAnimal;
import com.lades.sihv.model.Species;
import com.lades.sihv.model.Street;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class AnimalSearchEngine extends AbstractBean {

    private final VariablesSearch objVarSearch;
    private final List<AnimalDataGroup> listAnimal;
    private List<AnimalDataGroup> filterAnimal;
    private String join = "";

    public AnimalSearchEngine(VariablesSearch objVarSearch) {
        this.objVarSearch = objVarSearch;
        listAnimal = new ArrayList();
    }

    private void gerarJoins() throws ParseException {
        join = "";
        switch (objVarSearch.getItemSearch()) {
            case "RGHV":
                join = "and \n s.pkSmallAnimal='" + objVarSearch.getTextSearch() + "'";
                break;
            case "NameAnimal":
                join = "and \n a.animalName like '%" + objVarSearch.getTextSearch() + "%'";
                break;
            case "BetweenDates":
                DateFormat formatUS = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                String x = formatUS.format(objVarSearch.getDateInitial()).substring(0, 11) + "01:00:00";
                String y = formatUS.format(objVarSearch.getDateEnd()).substring(0, 11) + "23:59:59";
                join = "and \n a.registrationDate>='" + x + "' and \n "
                        + "a.registrationDate<='" + y + "'";
                break;
            case "NameOwner":
                join = "and \n p.namePerson like '%" + objVarSearch.getTextSearch() + "%'";
                break;
        }
    }

    public boolean isViewTablelistAnimals() {
        return !listAnimal.isEmpty();
    }

    public void listAnimals() throws ParseException {
        listAnimal.clear();
        gerarJoins();
        String hql = "";
        switch (objVarSearch.getItemSearch()) {
            case "RGHV":
            case "NameAnimal":
            case "BetweenDates":
            case "NameOwner":
                hql = "select a,s,r,sp from \n"
                        + "Animals a, SmallAnimal s, Races r, Species sp \n"
                        + ", OwnersHasAnimals oha, Owners o, People p \n"
                        + "where \n"
                        + "a.pkAnimal=s.animals.pkAnimal and \n"
                        + "s.races.id.pkRaces=r.id.pkRaces and \n"
                        + "r.species.id.pkSpecies=sp.id.pkSpecies and \n"
                        + "a.pkAnimal=oha.animals.pkAnimal and \n"
                        + "oha.owners.pkOwner=o.pkOwner and \n"
                        + "o.people.pkPerson=p.pkPerson " + join;
                break;
        }
        List<?> list = getDaoGenerico().list(hql);
        if (list != null && !list.isEmpty()) {
            for (Object[] obj : (List<Object[]>) list) {
                AnimalDataGroup x = new AnimalDataGroup();
                x.setAnimal((Animals) obj[0]);
                x.setSmallAnimal((SmallAnimal) obj[1]);
                x.setRace((Races) obj[2]);
                x.setSpecies((Species) obj[3]);
                listAnimal.add(x);
            }

            for (AnimalDataGroup obj : listAnimal) {
                List<?> list2 = getDaoGenerico().list("select p,o from Animals a, OwnersHasAnimals oha, Owners o, People p \n"
                        + "where \n"
                        + "a.pkAnimal=oha.animals.pkAnimal and \n"
                        + "oha.owners.pkOwner=o.pkOwner and \n"
                        + "o.people.pkPerson=p.pkPerson and \n"
                        + "a.pkAnimal='" + obj.getAnimal().getPkAnimal() + "'");
                if (list2 != null && !list2.isEmpty()) {
                    for (Object[] obj2 : (List<Object[]>) list2) {
                        OwnerDataGroup p = new OwnerDataGroup();
                        p.setPerson((People) obj2[0]);
                        p.setOwner((Owners) obj2[1]);
                        List<Phones> list3 = getDaoGenerico().list("select ph from Phones ph, People p \n"
                                + "where \n"
                                + "p.pkPerson=ph.people.pkPerson and \n"
                                + "p.pkPerson='" + p.getPerson().getPkPerson() + "'");
                        if (list3 != null && !list3.isEmpty()) {
                            p.setPhone1(list3.get(0));
                            if (list3.size() == 2) {
                                p.setPhone2(list3.get(1));
                            } else if (list3.size() == 3) {
                                p.setPhone3(list3.get(2));
                            }
                        }
                        List<?> list4 = getDaoGenerico().list("select phy from PhysicalPerson phy, People p \n"
                                + "where \n"
                                + "p.pkPerson=phy.id.peoplePkPerson and \n"
                                + "p.pkPerson='" + p.getPerson().getPkPerson() + "'");
                        if (list4 != null && !list4.isEmpty()) {
                            PhysicalPerson phy = (PhysicalPerson) list4.get(0);
                            p.setPhysicalPerson(phy);
                            Cpf cpf = (Cpf) getDaoGenerico().list("select c from Cpf c, PhysicalPerson phy \n"
                                    + "where \n"
                                    + "phy.id.pkPhysicalPerson=c.physicalPerson.id.pkPhysicalPerson and \n"
                                    + "phy.id.pkPhysicalPerson='" + p.getPhysicalPerson().getId().getPkPhysicalPerson() + "'").get(0);
                            if (cpf == null) {
                                cpf = new Cpf(null, "não informado");
                            }
                            Rg rg = (Rg) getDaoGenerico().list("select r from Rg r, PhysicalPerson phy \n"
                                    + "where \n"
                                    + "phy.id.pkPhysicalPerson=r.physicalPerson.id.pkPhysicalPerson and \n"
                                    + "phy.id.pkPhysicalPerson='" + p.getPhysicalPerson().getId().getPkPhysicalPerson() + "'").get(0);
                            if (rg == null) {
                                rg = new Rg(null, "não informado");
                            }
                            p.setRg(rg);
                            p.setCpf(cpf);
                        } else {
                            list4 = getDaoGenerico().list("select l from People p, LegalPerson l \n"
                                    + "where \n"
                                    + "p.pkPerson=l.people.pkPerson and \n"
                                    + "p.pkPerson='" + p.getPerson().getPkPerson() + "'");
                            p.setLegalPerson((LegalPerson) list4.get(0));
                        }

                        List<?> list5 = getDaoGenerico().list("select h,s,n,c,uf from \n"
                                + "People p, Houses h, Address a, Street s, Neighborhood n, City c, FederationUnity uf \n"
                                + "where \n"
                                + "p.pkPerson=h.people.pkPerson and \n"
                                + "h.address.pkAddress=a.pkAddress and \n"
                                + "a.street.pkStreet=s.pkStreet and \n"
                                + "a.neighborhood.id.pkNeighborhood=n.id.pkNeighborhood and \n"
                                + "n.id.cityPkCity=c.pkCity and \n"
                                + "c.federationUnity.pkFederationUnity=uf.pkFederationUnity and \n"
                                + "p.pkPerson='" + p.getPerson().getPkPerson() + "'");
                        if (list5 != null && !list5.isEmpty()) {
                            Object[] address = (Object[]) list5.get(0);
                            p.setHouse((Houses) address[0]);
                            p.setStreet((Street) address[1]);
                            p.setNeighborhood((Neighborhood) address[2]);
                            p.setCity((City) address[3]);
                            p.setUf((FederationUnity) address[4]);
                        }
                        obj.getListOwner().add(p);
                    }
                }
            }
        } else {
            getObjMessage().warn("Item não encontrado", "");
        }
    }

    // GETs & SETs -------------------------------------------------------------
    public List<AnimalDataGroup> getListAnimal() {
        return listAnimal;
    }

    public List<AnimalDataGroup> getFilterAnimal() {
        return filterAnimal;
    }

    public void setFilterAnimal(List<AnimalDataGroup> filterAnimal) {
        this.filterAnimal = filterAnimal;
    }
}
