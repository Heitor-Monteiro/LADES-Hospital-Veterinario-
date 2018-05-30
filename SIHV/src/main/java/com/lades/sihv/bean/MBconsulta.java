/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.bean;

import com.lades.sihv.controller.consulta.MaxCodigoConsulta;
import com.lades.sihv.controller.consulta.CodExameImagem;
import com.lades.sihv.controller.consulta.ConfirmarMedicoVeterinario;
import com.lades.sihv.controller.consulta.VisualizarConsulta;
import com.lades.sihv.classeMolde.FormsExames;
import com.lades.sihv.classeMolde.CollectionClasses;
import com.lades.sihv.model.Consulta;
import java.io.IOException;
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
    private boolean confirmeRAIOX = false;
    private boolean confirmeUltrasson = false;
    private String codRaioX;
    private String codUltrasson;
    private int numCodImage;

    @PostConstruct
    public void init() {

    }

    /*Método utilizado para salvar uma nova consulta.
    Obs.: a consulta será salva caso tenha confirmação 
    do medico veterinário usando o método 
    confirmaMEDICO()*/
    public void adicionarNovaConsulta() {
        boolean var = new ConfirmarMedicoVeterinario().confirmaMEDICO(confirmeSENHA, confirmeCRMV);
        if (var) {
            try {
                Consulta consulta = getFormsExame()
                        .getControlConsulta()
                        .ConfirmeConsulta(collectionClasses.getAnimais(),
                                 getVariaveisDeSessao().getDadosUSER());

//                getFormsExame().getControlAnamnese().ConfirmeAnamnese(consulta);
                getFormsExame().getControlExameFisico().ConfirmeExameFisico(consulta);
//                getFormsExame().getControlSisDigestorio().ConfirmeSisDigestorio(consulta);
//                getFormsExame().getControlSisRespCardio().ConfirmeSisRespCardio(consulta);
//                getFormsExame().getControlSisUrinarioMamaria().ConfirmeSisUrinarioMamaria(consulta);
//                getFormsExame().getControleSisTegumentar().ConfirmeSisTegumentar(consulta);
//                getFormsExame().getControleSisNeurologico().ConfirmeSisNeurologico(consulta);
//                getFormsExame().getControleSisOftalmico().ConfirmeSisOftalmico(consulta);
//                getFormsExame().getControleSisMuscEsque().ConfirmeSisMuscEsque(consulta);
                if (confirmeRAIOX == true) {
//                    getFormsExame().prepareExameImageRaioX(getObjData(), codRaioX);
//                    getDaoGenerico().save(getFormsExame().getExameImageRaioX());
                }
                if (confirmeUltrasson == true) {
//                    getFormsExame().prepareExameImageUltra(getObjData(), codUltrasson);
//                    getDaoGenerico().save(getFormsExame().getExameImageUltra());
                }
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

    public void maxExameImagem() {
        numCodImage = new CodExameImagem().maxExameImagem();
    }

    /*O método direciona o usuário para uma
    pagina que exibirá todos os exames.*/
    public void verConsulta() {
        formsExame = new VisualizarConsulta().viewCONSULTA("" + collectionClasses.getConsulta().getPkConsulta());
        try {
            getObjTools().redirecionar("/SIHV/faces/sihv-telas-exame/Exames.xhtml");
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

    //GETs e SETs para código de exames por imagens
    public String getCodRaioX() {
        return codRaioX;
    }

    public void setCodRaioX(String codRaioX) {
        this.codRaioX = codRaioX;
    }

    public String getCodUltrasson() {
        return codUltrasson;
    }

    public void setCodUltrasson(String codUltrasson) {
        this.codUltrasson = codUltrasson;
    }
    //------------------------------------------------------------------

    public boolean isConfirmeRAIOX() {
        return confirmeRAIOX;
    }

    public void setConfirmeRAIOX(boolean confirmeRAIOX) {
        this.confirmeRAIOX = confirmeRAIOX;
        new CodExameImagem().gerarCodExameImagem(this.confirmeRAIOX,
                confirmeUltrasson, numCodImage, codRaioX, codUltrasson);
    }

    public boolean isConfirmeUltrasson() {
        return confirmeUltrasson;
    }

    public void setConfirmeUltrasson(boolean confirmeUltrasson) {
        this.confirmeUltrasson = confirmeUltrasson;
        new CodExameImagem().gerarCodExameImagem(confirmeRAIOX,
                this.confirmeUltrasson, numCodImage, codRaioX, codUltrasson);
    }

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

    /*Método GET para exibir código demostrativos
    ao finalizar uma nova consulta.*/
    public int getMaxCodConsulta() {
        return new MaxCodigoConsulta().maxConsultaCod();
    }
    //------------------------------------------------------------------
}
