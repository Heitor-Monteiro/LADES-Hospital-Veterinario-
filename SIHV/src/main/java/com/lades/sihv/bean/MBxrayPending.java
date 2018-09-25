///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.lades.sihv.bean;
//
//import com.lades.sihv.classeMolde.CollectionClasses;
//import com.lades.sihv.controller.VariablesSearch;
//import com.lades.sihv.controller.ListRenderedFields;
//import com.lades.sihv.controller.RenderedFields;
//import com.lades.sihv.model.Anamnese;
//import com.lades.sihv.model.Animais;
//import com.lades.sihv.model.Consulta;
//import com.lades.sihv.model.ExameImage;
//import com.lades.sihv.model.Pessoa;
//import java.util.ArrayList;
//import java.util.List;
//import javax.annotation.PostConstruct;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ViewScoped;
//import org.primefaces.event.FlowEvent;
//
///**
// *
// * @author thiberius
// */
//@ManagedBean(name = "MBxrayPending")
//@ViewScoped
//
//public class MBxrayPending extends AbstractBean {
//    
//    private VariablesSearch objVarSearch;
//    private ListRenderedFields listRenderedFields;
//    private List<CollectionClasses> listXrayExams;
//    private CollectionClasses selectXrayPending;
//    private String joinXray;
//    private String doseTechniqueArray[];
//    
//    @PostConstruct
//    public void init() {
//        System.out.println("BACK-END WARNING: MBxrayPending initiated");
//        objVarSearch = new VariablesSearch();
//        listRenderedFields = new ListRenderedFields(3);
//        listRenderedFields.startIndexListViewFields();
//        listXrayExams = new ArrayList<>();
//    }
//    
//    private void dataXraySearch(boolean ViewVariableBoolean, String searchMask, String textSearch, String searchTip, int maxLength) {
//        listRenderedFields.getListViewFields(0).setViewVariableBoolean(ViewVariableBoolean);
//        objVarSearch.setSearchMask(searchMask);
//        objVarSearch.setTextSearch(textSearch);
//        objVarSearch.setSearchTip(searchTip);
//        objVarSearch.setMaxLength(maxLength);
//    }
//    
//    public void xRayItemSearch() {
//        listRenderedFields.startIndexListViewFields();
//        switch (objVarSearch.getItemSearch()) {
//            case "Pendentes":
//                dataXraySearch(false, "", "Pendentes", "", 9);
//                break;
//            case "solicitacaoData":
//                dataXraySearch(true, "", "", "Ex: 2018-05-02", 10);
//                break;
//            case "nome":
//                dataXraySearch(true, "", "", "Nome completo ou incompleto do proprietário", 50);
//                break;
//            case "nomeAnimal":
//                dataXraySearch(true, "", "", "Nome completo ou incompleto do animal", 50);
//                break;
//            default:
//                break;
//        }
//    }
//    
//    public void searchXrayPending() {
//        objVarSearch.clearListObjectsReturned();
//        listXrayExams.clear();
//        listXrayPending();
//        objVarSearch.objectsFullList();
//        getVariaveisDeSessao().startWizardButtons();
//        if (!listXrayExams.isEmpty()) {
//            getVariaveisDeSessao().enableBtnNextWizard();
//            getVariaveisDeSessao().enableBtnBackWizard();
//        }
//    }
//    
//    private void listXrayPending() {
//        try {
//            if (objVarSearch.checkMaxLength()) {
//                joinXray = "i.statusExamImage = 'Pendente'";
//                switch (objVarSearch.getItemSearch()) {
//                    case "Pendentes":
//                        joinXray = "i.statusExamImage = 'Pendente'";
//                        break;
//                    case "solicitacaoData":
//                        joinXray += "and i.solicitacaoData like '%" + objVarSearch.getTextSearch() + "%'";
//                        break;
//                    case "nome":
//                    case "nomeAnimal":
//                        if (objVarSearch.getItemSearch().equals("nome")) {
//                            joinXray += "and p." + objVarSearch.getItemSearch() + " like '%" + objVarSearch.getTextSearch() + "%'";
//                        } else {
//                            joinXray += " and a." + objVarSearch.getItemSearch() + " like '%" + objVarSearch.getTextSearch() + "%'";
//                        }
//                        break;
//                }
//                String hql = "select a,e,i from Pessoa p, Cliente c, Animais a, Consulta e, ExameImage i "
//                        + "where p.pkPessoa=c.id.fkPessoa and "
//                        + "c.id.fkPessoa=a.id.clienteFkPessoa and "
//                        + "a.id.pkAnimal=e.animais.id.pkAnimal and "
//                        + "e.pkConsulta=i.id.consultaFkConsulta and "
//                        + "i.tipo= 'RaioX' and "
//                        + joinXray;
//                objVarSearch.setObjectsReturned(getDaoGenerico().list(hql));
//                
//                for (Object[] object : (List<Object[]>) objVarSearch.getObjectsReturned()) {
//                    CollectionClasses obj = new CollectionClasses();
//                    obj.setAnimais((Animais) object[0]);
//                    obj.setConsulta((Consulta) object[1]);
//                    obj.getFormsExams().getControleExaImage().setExamImageXray((ExameImage) object[2]);
//                    listXrayExams.add(obj);
//                }
//            }
//        } catch (Exception e) {
//            getObjMessage().warn("Erro ao efetuar listagem!", e.getMessage());
//        }
//    }
//    
//    public void updateDataXray() {
//        try {
//            selectXrayPending.getFormsExams().getControleExaImage().getExamImageXray().setAtendimentoData(getObjData());
//            selectXrayPending.getFormsExams().getControleExaImage().getExamImageXray().setStatusExamImage("Atendido");
//            getDaoGenerico().update(selectXrayPending.getFormsExams().getControleExaImage().getExamImageXray());
//            getObjMessage().info("Cadastro efetuado!", "Raio-X cadastrado com sucesso.");
//            getViewButtonNewXray().setViewVariableBoolean(true);
//            getXrayRecordingComplete().setViewVariableBoolean(true);
//            getVariaveisDeSessao().startWizardButtons();
//        } catch (Exception e) {
//            getObjMessage().warn("Erro ao efetuar cadastro!", e.getMessage()
//                    + "\nVerifique os dados e tente novamente!");
//        }
//    }
//    
//    private void methodSelectWeightAnimal() {
//        try {
//            if (selectXrayPending != null) {
//                List<?> list = getDaoGenerico().list("select x from Pessoa p, Cliente c, Animais a, Consulta e, Anamnese x "
//                        + "where p.pkPessoa=c.id.fkPessoa and "
//                        + "c.id.fkPessoa=a.id.clienteFkPessoa and "
//                        + "a.id.pkAnimal=e.animais.id.pkAnimal and "
//                        + "e.pkConsulta=x.id.consultaFkConsulta and "
//                        + "x.id.consultaFkConsulta='" + this.selectXrayPending.getConsulta().getPkConsulta() + "'");
//                selectXrayPending.getFormsExams().getControlAnamnese().setAnamnese((Anamnese) list.get(0));
//            }
//        } catch (Exception e) {
//            selectXrayPending = null;
//            System.out.println("BACK-END WARNING: Erro ao selecionar Item! [ public void methodSelectWeightAnimal() ]"
//                    + e.getMessage());
//        }
//    }
//    
//    private void methodSelectResident() {
//        try {
//            if (selectXrayPending != null) {
//                List<?> list = getDaoGenerico().list("select p from Pessoa p, User u, Consulta c "
//                        + "where p.pkPessoa=u.id.fkPessoa and "
//                        + "u.id.pkUser=c.user.id.pkUser and "
//                        + "c.pkConsulta='" + selectXrayPending.getConsulta().getPkConsulta() + "'");
//                selectXrayPending.setResidente((Pessoa) list.get(0));
//            }
//        } catch (Exception e) {
//            System.out.println("BACK-END WARNING: Erro ao selecionar Item! [ public void methodSelectResident() ]"
//                    + e.getMessage());
//        }
//    }
//    
//    public String onFlowProcess(FlowEvent event) {
//        String var;
//        if (selectXrayPending == null) {
//            var = "etapa1";
//        } else {
//            var = event.getNewStep();
//        }
//        return var;
//    }
//    
//    public VariablesSearch getObjVarSearch() {
//        return objVarSearch;
//    }
//    
//    public RenderedFields getViewInputTextSearch() {
//        return listRenderedFields.getListViewFields(0);
//    }
//    
//    public List<CollectionClasses> getListXrayExams() {
//        return listXrayExams;
//    }
//    
//    public CollectionClasses getSelectXrayPending() {
//        return selectXrayPending;
//    }
//    
//    public void setSelectXrayPending(CollectionClasses selectXrayPending) {
//        this.selectXrayPending = selectXrayPending;
//        methodSelectWeightAnimal();
//        methodSelectResident();
//    }
//    
//    public RenderedFields getViewButtonNewXray() {
//        return listRenderedFields.getListViewFields(1);
//    }
//    
//    public RenderedFields getXrayRecordingComplete() {
//        return listRenderedFields.getListViewFields(2);
//    }
//    
//    public String[] getDoseTechniqueArray() {
//        return doseTechniqueArray;
//    }
//    
//    public void setDoseTechniqueArray(String[] doseTechniqueArray) {
//        this.doseTechniqueArray = doseTechniqueArray;
//    }
//}
