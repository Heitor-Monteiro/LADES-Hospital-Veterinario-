/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.bean;

import com.lades.sihv.controller.NewConsultation.ConfirmarMedicoVeterinario;
import com.lades.sihv.controller.NewConsultation.VisualizarConsulta;
import com.lades.sihv.controller.NewConsultation.FormsExames;
import com.lades.sihv.controller.NewConsultation.CollectionClasses;
import com.lades.sihv.controller.NewConsultation.SchedulesConfirmedForConsultation;
import com.lades.sihv.controller.NewConsultation.SearchForConfirmedSchedules;
import com.lades.sihv.model.VetConsultation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author thiberius
 */
@ManagedBean(name = "MBconsulta")
@ViewScoped

public class MBconsulta extends AbstractBean {

    private String confirmeCRMV;
    private String confirmeSENHA;
    private CollectionClasses collectionClasses;
    private FormsExames formsExame;

    //--------------------------------------------------------------------------
    private List<SchedulesConfirmedForConsultation> schedulesConfirmedForConsultation;
    private SchedulesConfirmedForConsultation selectScheduleConfirmed;
    //--------------------------------------------------------------------------

    @PostConstruct
    public void init() {
        System.out.println("►►►►►►►►►►►►► MBconsulta initiated");
        formsExame = new FormsExames();
        formsExame.getControlConsulta().generateMaxExamCode();
        schedulesConfirmedForConsultation = new ArrayList<>();
        new SearchForConfirmedSchedules().searchForConfirmedSchedules(schedulesConfirmedForConsultation);
    }

    /*Método utilizado para salvar uma nova consulta.
    Obs.: a consulta será salva caso tenha confirmação 
    do medico veterinário usando o método 
    confirmaMEDICO()*/
    public void adicionarNovaConsulta() {
        boolean var = new ConfirmarMedicoVeterinario().confirmaMEDICO(confirmeSENHA, confirmeCRMV);
        if (var) {
            try {
                VetConsultation consulta = getFormsExame()
                        .getControlConsulta()
                        .ConfirmeConsulta(selectScheduleConfirmed,
                                getVariaveisDeSessao().getDadosUSER());
                getFormsExame().getControlAnamnese().ConfirmeAnamnese(consulta);
                getFormsExame().getControlExameFisico().ConfirmeExameFisico(consulta);
                getFormsExame().getControlSisDigestorio().ConfirmeSisDigestorio(consulta);
                getFormsExame().getControlSisRespCardio().ConfirmeSisRespCardio(consulta);
                getFormsExame().getControlSisUrinarioMamaria().ConfirmeSisUrinarioMamaria(consulta);
                getFormsExame().getControleSisTegumentar().ConfirmeSisTegumentar(consulta);
                getFormsExame().getControleSisNeurologico().ConfirmeSisNeurologico(consulta);
                getFormsExame().getControleSisOftalmico().ConfirmeSisOftalmico(consulta);
                getFormsExame().getControleSisMuscEsque().ConfirmeSisMuscEsque(consulta);
                getFormsExame().getControleExaImage().ConfirmeExamXray(consulta);
                getFormsExame().getControleExaImage().ConfirmeExamUltrasound(consulta);
                //--------------------------------------------------------------
                selectScheduleConfirmed.getSchedule().setStatusService("efetivado(a)");
                getDaoGenerico().update(selectScheduleConfirmed.getSchedule());
                //--------------------------------------------------------------
                getObjMessage().info("Cosulta efetuada.", "Consulta realizada com sucesso.");
                getObjTools().blockBackWizad();//Bloqueio do botão back do Wizard PrimeFAces
                getObjTools().setShowButtonPrint(true); //Habilitando visibilidade do botão para impressão
                getObjTools().disableWizardNavBar();
            } catch (Exception e) {
                getObjMessage().warn("Erro ao efetuar cadastro!", "Verifique os dados e tente novamente!");
                getObjTools().disableWizardNavBar();
            }
        }
    }

    /*O método direciona o usuário para uma
    pagina que exibirá todos os exames.*/
    public void verConsulta() {
        formsExame = new VisualizarConsulta().viewCONSULTA("" + collectionClasses.getVetConsultation().getPkVetConsultation());
        try {
            getObjTools().redirectView("/SIHV/faces/sihv-telas-exame/Exames.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(MBconsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*O métodos GETs e SETs utilizados para confirmar a identidade do residente*/
    //----------------------------------------------------
    public String getConfirmeCRMV() {
        return confirmeCRMV;
    }

    public void setConfirmeCRMV(String confirmeCRMV) {
        this.confirmeCRMV = confirmeCRMV;
    }

    public String getConfirmeSENHA() {
        return confirmeSENHA;
    }

    public void setConfirmeSENHA(String confirmeSENHA) {
        this.confirmeSENHA = confirmeSENHA;
    }

    //-----------------------------------------------------
    public CollectionClasses getCollectionClasses() {
        try {
            if (collectionClasses == null) {
                CollectionClasses obj = (CollectionClasses) getVariaveisDeSessao().getObjetoTemp();
                collectionClasses = obj;
            }
        } catch (Exception e) {
            collectionClasses = new CollectionClasses();
        }
        return collectionClasses;
    }

    public void setCollectionClasses(CollectionClasses collectionClasses) {
        this.collectionClasses = collectionClasses;
    }

    public FormsExames getFormsExame() {
        if (formsExame == null) {
            formsExame = new FormsExames();
        }
        return formsExame;
    }
    //------------------------------------------------------------------

    public List<SchedulesConfirmedForConsultation> getSchedulesConfirmedForConsultation() {
        return schedulesConfirmedForConsultation;
    }

    public SchedulesConfirmedForConsultation getSelectScheduleConfirmed() {
        return selectScheduleConfirmed;
    }

    public void setSelectScheduleConfirmed(SchedulesConfirmedForConsultation selectScheduleConfirmed) {
        this.selectScheduleConfirmed = selectScheduleConfirmed;
        if (this.selectScheduleConfirmed.getOwnersHasAnimals().getPkOwnersHasAnimals() != null) {
            System.out.println("►►►►►►►►►►►►► SelectScheduleConfirmed:"
                    + this.selectScheduleConfirmed.getOwnersHasAnimals().getPkOwnersHasAnimals());
            getVariaveisDeSessao().setObjetoTemp(this.selectScheduleConfirmed);
        }
    }

}
