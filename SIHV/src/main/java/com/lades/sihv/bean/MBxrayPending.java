/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.bean;

import com.lades.sihv.controller.NewConsultation.CollectionClasses;
import com.lades.sihv.controller.VariablesSearch;
import com.lades.sihv.controller.ListRenderedFields;
import com.lades.sihv.controller.RenderedFields;
import com.lades.sihv.controller.animal.CollectAnimalRghv;
import com.lades.sihv.model.Anamnese;
import com.lades.sihv.model.Animals;
import com.lades.sihv.model.VetConsultation;
import com.lades.sihv.model.ExameImage;
import com.lades.sihv.model.People;
import com.lades.sihv.model.Races;
import com.lades.sihv.model.SmallAnimal;
import com.lades.sihv.model.Species;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author thiberius
 */
@ManagedBean(name = "MBxrayPending")
@ViewScoped

public class MBxrayPending extends AbstractBean {

    private VariablesSearch objVarSearch;
    private ListRenderedFields listRenderedFields;
    private List<CollectionClasses> listXrayExams;
    private CollectionClasses selectXrayPending;
    private String joinXray;
    private String doseTechniqueArray[];

    @PostConstruct
    public void init() {
        System.out.println("BACK-END WARNING: MBxrayPending initiated");
        objVarSearch = new VariablesSearch();
        listRenderedFields = new ListRenderedFields(3);
        listRenderedFields.startIndexListViewFields();
        listXrayExams = new ArrayList<>();
    }

    private void dataXraySearch(boolean ViewVariableBoolean, String searchMask, String textSearch, String searchTip, int maxLength) {
        listRenderedFields.getListViewFields(0).setViewVariableBoolean(ViewVariableBoolean);
        objVarSearch.setSearchMask(searchMask);
        objVarSearch.setTextSearch(textSearch);
        objVarSearch.setSearchTip(searchTip);
        objVarSearch.setMaxLength(maxLength);
    }

    public void xRayItemSearch() {
        listRenderedFields.startIndexListViewFields();
        switch (objVarSearch.getItemSearch()) {
            case "Pendentes":
                dataXraySearch(false, "", "Pendentes", "", 9);
                break;
            case "solicitacaoData":
                dataXraySearch(true, "", "", "Ex: 2018-05-02", 10);
                break;
            case "namePerson":
                dataXraySearch(true, "", "", "Nome completo ou incompleto do proprietário", 50);
                break;
            case "animalName":
                dataXraySearch(true, "", "", "Nome completo ou incompleto do animal", 50);
                break;
            default:
                break;
        }
    }

    public void searchXrayPending() {
        objVarSearch.clearListObjectsReturned();
        listXrayExams.clear();
        listXrayPending();
        objVarSearch.objectsFullList();
        getVariaveisDeSessao().startWizardButtons();
        if (!listXrayExams.isEmpty()) {
            getVariaveisDeSessao().enableBtnNextWizard();
            getVariaveisDeSessao().enableBtnBackWizard();
        }
    }

    private void listXrayPending() {
        try {
            if (objVarSearch.checkMaxLength()) {
                joinXray = "i.statusExamImage = 'Pendente'";
                switch (objVarSearch.getItemSearch()) {
                    case "Pendentes":
                        joinXray = "i.statusExamImage = 'Pendente'";
                        break;
                    case "solicitacaoData":
                        joinXray += "and i.solicitacaoData like '%" + objVarSearch.getTextSearch() + "%'";
                        break;
                    case "namePerson":
                    case "animalName":
                        if (objVarSearch.getItemSearch().equals("namePerson")) {
                            joinXray += "and p." + objVarSearch.getItemSearch() + " like '%" + objVarSearch.getTextSearch() + "%'";
                        } else {
                            joinXray += " and a." + objVarSearch.getItemSearch() + " like '%" + objVarSearch.getTextSearch() + "%'";
                        }
                        break;
                }
                String hql = "select a,s,r,sp,e,i from People p, Owners c, OwnersHasAnimals h, "
                        + "Animals a, SmallAnimal s, Races r, Species sp, VetConsultation e, ExameImage i \n"
                        + "where \n"
                        + "p.pkPerson=c.people.pkPerson and \n"
                        + "c.pkOwner=h.owners.pkOwner and \n"
                        + "h.animals.pkAnimal=a.pkAnimal and \n"
                        + "a.pkAnimal=s.animals.pkAnimal and \n"
                        + "s.races.id.pkRaces=r.id.pkRaces and \n"
                        + "r.species.id.pkSpecies=sp.id.pkSpecies and \n"
                        + "h.pkOwnersHasAnimals=e.ownersHasAnimals.pkOwnersHasAnimals and \n"
                        + "e.pkVetConsultation=i.vetConsultation.pkVetConsultation and \n"
                        + "i.tipo= 'RaioX' and "
                        + joinXray;
                objVarSearch.setObjectsReturned(getDaoGenerico().list(hql));

                for (Object[] object : (List<Object[]>) objVarSearch.getObjectsReturned()) {
                    CollectionClasses obj = new CollectionClasses();
                    obj.setAnimais((Animals) object[0]);
                    obj.setSmallAnimal((SmallAnimal) object[1]);
                    obj.setRghv(new CollectAnimalRghv().methodCollectAnimalRghv(obj.getSmallAnimal()
                            .getPkSmallAnimal(), "P", obj.getAnimal()));
                    obj.setBreed((Races) object[2]);
                    obj.setSpecie((Species) object[3]);
                    obj.setVetConsultation((VetConsultation) object[4]);
                    obj.getFormsExams().getControleExaImage().setExamImageXray((ExameImage) object[5]);
                    listXrayExams.add(obj);
                }
            }
        } catch (Exception e) {
            getObjMessage().warn("Erro ao efetuar listagem!", e.getMessage());
        }
    }

    public void updateDataXray() {
        try {
            selectXrayPending.getFormsExams().getControleExaImage().getExamImageXray().setAtendimentoData(getObjData());
            selectXrayPending.getFormsExams().getControleExaImage().getExamImageXray().setStatusExamImage("Atendido");
            getDaoGenerico().update(selectXrayPending.getFormsExams().getControleExaImage().getExamImageXray());
            getObjMessage().info("Cadastro efetuado!", "Raio-X cadastrado com sucesso.");
            getViewButtonNewXray().setViewVariableBoolean(true);
            getXrayRecordingComplete().setViewVariableBoolean(true);
            getVariaveisDeSessao().startWizardButtons();
        } catch (Exception e) {
            getObjMessage().warn("Erro ao efetuar cadastro!", e.getMessage()
                    + "\nVerifique os dados e tente novamente!");
        }
    }

    private void methodSelectWeightAnimal() {
        try {
            if (selectXrayPending != null) {
                List<?> list = getDaoGenerico().list("select x from People p, Owners c, OwnersHasAnimals h, Animals a, VetConsultation e, Anamnese x \n"
                        + "where p.pkPerson=c.people.pkPerson and \n"
                        + "c.pkOwner=h.owners.pkOwner and \n"
                        + "h.animals.pkAnimal=a.pkAnimal and \n"
                        + "h.pkOwnersHasAnimals=e.ownersHasAnimals.pkOwnersHasAnimals and \n"
                        + "e.pkVetConsultation=x.id.pkAnamnese and \n"
                        + "x.vetConsultation.pkVetConsultation='" + this.selectXrayPending.getVetConsultation().getPkVetConsultation() + "' ");
                selectXrayPending.getFormsExams().getControlAnamnese().setAnamnese((Anamnese) list.get(0));
            }
        } catch (Exception e) {
            selectXrayPending = null;
            System.out.println("►►►►►►►►►►►►► ERRO private void methodSelectWeightAnimal(): " + e.getMessage());
        }
    }

    private void methodSelectResident() {
        try {
            if (selectXrayPending != null) {
                List<?> list = getDaoGenerico().list("select p from People p, Users u, VetConsultation c \n"
                        + "where p.pkPerson=u.people.pkPerson and \n"
                        + "u.pkUser=c.users.pkUser and \n"
                        + "c.pkVetConsultation='" + selectXrayPending.getVetConsultation().getPkVetConsultation() + "'");
                selectXrayPending.setPersonResident((People) list.get(0));
            }
        } catch (Exception e) {
            System.out.println("►►►►►►►►►►►►► ERRO private void methodSelectResident(): " + e.getMessage());
        }
    }

    public String onFlowProcess(FlowEvent event) {
        String var;
        if (selectXrayPending == null) {
            var = "etapa1";
        } else {
            var = event.getNewStep();
        }
        return var;
    }

    public VariablesSearch getObjVarSearch() {
        return objVarSearch;
    }

    public RenderedFields getViewInputTextSearch() {
        return listRenderedFields.getListViewFields(0);
    }

    public List<CollectionClasses> getListXrayExams() {
        return listXrayExams;
    }

    public CollectionClasses getSelectXrayPending() {
        return selectXrayPending;
    }

    public void setSelectXrayPending(CollectionClasses selectXrayPending) {
        this.selectXrayPending = selectXrayPending;
        methodSelectWeightAnimal();
        methodSelectResident();
    }

    public RenderedFields getViewButtonNewXray() {
        return listRenderedFields.getListViewFields(1);
    }

    public RenderedFields getXrayRecordingComplete() {
        return listRenderedFields.getListViewFields(2);
    }

    public String[] getDoseTechniqueArray() {
        return doseTechniqueArray;
    }

    public void setDoseTechniqueArray(String[] doseTechniqueArray) {
        this.doseTechniqueArray = doseTechniqueArray;
    }
}
