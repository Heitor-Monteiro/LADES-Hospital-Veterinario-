///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.lades.sihv.controller.scheduleConsulta;
//
//import com.lades.sihv.bean.AbstractBean;
//import com.lades.sihv.controller.GenericScheduling;
//import com.lades.sihv.controller.VariablesSearch;
//import com.lades.sihv.model.Animais;
//import com.lades.sihv.model.Pessoa;
//import com.lades.sihv.model.Scheduling;
//import com.lades.sihv.model.Telefone;
//import com.lades.sihv.model.TemporaryClientData;
//import java.util.ArrayList;
//import java.util.List;
//import org.primefaces.model.DefaultScheduleEvent;
//import org.primefaces.model.ScheduleEvent;
//
///**
// *
// * @author thiberius
// */
//public class ListsForConsultaScheduling extends AbstractBean {
//
//    private List<Scheduling> listSchedule;
//    private List<TemporaryClientData> listTempCliData;
//    private List<String> listEventID;
//    //--------------------------------------------------------------------------
//    private List<String> listCellPhones;
//
//    //--------------------------------------------------------------------------
//    private final List<Pessoa> listPerson;
//    private final List<Pessoa> listPersonCache;
//    private final List<Animais> listAnimal;
//    private final List<Animais> listAnimalCache;
//    private String listPhones;
//    private List<Telefone> listPhonesCache;
//
//    public ListsForConsultaScheduling() {
//        listPerson = new ArrayList<>();
//        listPersonCache = new ArrayList<>();
//        listAnimal = new ArrayList<>();
//        listAnimalCache = new ArrayList<>();
//        listPhones = "";
//        listPhonesCache = new ArrayList<>();
//    }
//
//    public void popularListSchedule() {
//        listSchedule = getDaoGenerico().list("select s from Scheduling s"
//                + " where s.statusService = 'agendado(a)'");
//
//        listTempCliData = getDaoGenerico().list("select t from Scheduling s, TemporaryClientData t "
//                + "where s.pkSchedule = t.scheduling.pkSchedule and "
//                + "s.statusService = 'agendado(a)' and "
//                + "s.registerClient = 'Novo'");
//    }
//
//    public void popularListEventID(GenericScheduling geneScheduling) {
//        listEventID = new ArrayList<>();
//        for (ScheduleEvent objEvent : geneScheduling.getEventModel().getEvents()) {
//            listEventID.add(objEvent.getId());
//        }
//    }
//
//    private void method(GenericScheduling geneScheduling, Scheduling item, String title) {
//        geneScheduling.getEventModel().addEvent(new DefaultScheduleEvent(title,
//                item.getSchedulingDate(), item.getSchedulingDate()));
//    }
//
//    public void popularEventModel(GenericScheduling geneScheduling) {
//        popularListSchedule();
//        int num = 0;
//        for (Scheduling item : listSchedule) {
//            System.out.println("Item: " + num);
//            switch (item.getRegisterClient()) {
//                case "Cadastrado":
//                    System.out.println("FK animal:" + item.getAnimais().getId().getPkAnimal());
//                    System.out.println("FK pessoa:" + item.getAnimais().getId().getFkPessoa());
//
//                    List<?> list = getDaoGenerico().list("select p, a from Pessoa p, Cliente c, Animais a "
//                            + "where p.pkPessoa=c.id.fkPessoa and "
//                            + "c.id.fkPessoa=a.id.fkPessoa and "
//                            + "c.id.pkCliente=a.id.fkCliente and "
//                            + "p.pkPessoa='" + item.getAnimais().getId().getFkPessoa() + "' and "
//                            + "a.id.pkAnimal='" + item.getAnimais().getId().getPkAnimal() + "'");
//                    System.out.println("Tamanho lista: " + list.size());
//
//                    if (!list.isEmpty()) {
//                        for (Object[] obj : (List<Object[]>) list) {
//                            Pessoa person = (Pessoa) obj[0];
//                            listPersonCache.add(person);
//                            Animais animal = (Animais) obj[1];
//                            listAnimalCache.add(animal);
//                            method(geneScheduling, item, animal.getNomeAnimal());
//                        }
//                        listPhonesCache = searchPhoneProprietarySelected(listPersonCache);
//                    }
//                    System.out.println("0000000000000000000000000000000000000");
//                    break;
//                case "Novo":
//
//                    for (TemporaryClientData tempCliData : listTempCliData) {
//                        System.out.println("PKScheduling: " + item.getPkSchedule());
//                        System.out.println("FKScheduling: " + tempCliData.getScheduling().getPkSchedule());
//                        if (item.getPkSchedule() == tempCliData.getScheduling().getPkSchedule()) {
//                            method(geneScheduling, item, tempCliData.getAnimalName());
//                            System.out.println("111111111111111111111111111");
//                            break;
//                        }
//                    }
//                    break;
//            }
//            ++num;
//        }
//        popularListEventID(geneScheduling);
//    }
//
//    public boolean selectClientRegistered(Scheduling schedule) {
//        listPerson.clear();
//        listAnimal.clear();
//        boolean var = false;
//        System.out.println("public void selectClientClientRegistered(Scheduling schedule)");
//        System.out.println("------------------------------ listPersonCache - Size:" + listPersonCache.size());
//        for (Pessoa pessoa : listPersonCache) {
//            if (schedule.getAnimais().getId().getFkPessoa() == pessoa.getPkPessoa()) {
//                listPerson.add(pessoa);
//                var = true;
//                break;
//            }
//        }
//        listPhones = concatenateProprietaryPhone(listPerson);
//        System.out.println("------------------------------ listAnimalCache - Size:" + listAnimalCache.size());
//        for (Animais animal : listAnimalCache) {
//            if (schedule.getAnimais().getId().getPkAnimal() == animal.getId().getPkAnimal()) {
//                listAnimal.add(animal);
//                var = true;
//                break;
//            }
//        }
//        return var;
//    }
//
//    public void listingOfAnimalsAndProprietary(VariablesSearch variablesSearch, String num) {
//        String hql, typePerson, join;
//        hql = "";
//        typePerson = "";
//        join = "";
//        switch (variablesSearch.getItemSearch()) {
//            case "CPF":
//                typePerson = ", Fisica f";
//                join = "p.pkPessoa=f.id.fkPessoa and ";
//                hql = "p.cpfCnpj = '";
//                break;
//            case "CNPJ":
//                typePerson = ", Juridica j";
//                join = "p.pkPessoa=j.id.fkPessoa and ";
//                hql = "p.cpfCnpj = '";
//                break;
//            case "RG":
//                typePerson = ", Fisica f";
//                join = "p.pkPessoa=f.id.fkPessoa and ";
//                hql = "f.rg = '";
//                break;
//            case "RGHV":
//                typePerson = "";
//                join = "";
//                hql = "a.rghv = '" + num;
//                break;
//            default:
//                break;
//        }
//        hql += "" + variablesSearch.getTextSearch() + "'";
//        System.out.println("++++++++++++++++++++++++++++++++" + hql);
//        List<?> list = getDaoGenerico().list("select p, a from Pessoa p, Cliente c, Animais a" + typePerson
//                + " where " + join
//                + "p.pkPessoa=c.id.fkPessoa and "
//                + "c.id.fkPessoa=a.id.fkPessoa and "
//                + "c.id.pkCliente=a.id.fkCliente and "
//                + hql);
//        System.out.println("------------------------ " + list.size());
//        listPerson.clear();
//        listAnimal.clear();
//        if (!list.isEmpty()) {
//            for (Object[] item : (List<Object[]>) list) {
//                Pessoa person = (Pessoa) item[0];
//                listPerson.add(person);
//                Animais animal = (Animais) item[1];
//                listAnimal.add(animal);
//            }
//            System.out.println("============= listPerson: " + listPerson.size());
//            System.out.println("============= listAnimal: " + listAnimal.size());
//            listPhones = concatenateProprietaryPhone(listPerson);
//        } else {
//
//            switch (variablesSearch.getItemSearch()) {
//                case "CPF":
//                    getObjMessage().warn("Registro não encontrado",
//                            "Verifique os dados ou experimente procurar por RG");
//                    break;
//                case "RG":
//                    getObjMessage().warn("Registro não encontrado",
//                            "Verifique os dados ou experimente procurar por CPF");
//                    break;
//                default:
//                    getObjMessage().warn("Registro não encontrado",
//                            "O animal ainda não foi registrado");
//                    break;
//            }
//        }
//
//    }
//
//    private List<Telefone> searchPhoneProprietarySelected(List<Pessoa> list) {
//        List<Telefone> telList = new ArrayList<>();
//        for (Pessoa pessoa : list) {
//            telList = getDaoGenerico().list("select t from Pessoa p, Telefone t "
//                    + "where t.pessoa.pkPessoa = p.pkPessoa and "
//                    + "t.pessoa.pkPessoa = '" + pessoa.getPkPessoa() + "'");
//            System.out.println("==========:" + telList.size());
//        }
//        return telList;
//    }
//
//    private String concatenateProprietaryPhone(List<Pessoa> list) {
//        String phones = "";
//        for (Telefone phone : searchPhoneProprietarySelected(list)) {
//            if (phones.length() > 0) {
//                phones += ", " + phone.getNumero();
//            } else {
//                phones += phone.getNumero();
//            }
//        }
//        return phones;
//    }
//
//    public List<Scheduling> getListSchedule() {
//        return listSchedule;
//    }
//
//    public List<TemporaryClientData> getListTempCliData() {
//        return listTempCliData;
//    }
//
//    public List<String> getListCellPhones() {
//        return listCellPhones;
//    }
//
//    public List<String> getListEventID() {
//        return listEventID;
//    }
//
//    public List<Pessoa> getListPerson() {
//        return listPerson;
//    }
//
//    public List<Animais> getListAnimal() {
//        return listAnimal;
//    }
//
//    public List<Pessoa> getListPersonCache() {
//        return listPersonCache;
//    }
//
//    public List<Animais> getListAnimalCache() {
//        return listAnimalCache;
//    }
//
//    public String getListPhones() {
//        return listPhones;
//    }
//
//    public void setListPhones(String listPhones) {
//        this.listPhones = listPhones;
//    }
//    
//}
