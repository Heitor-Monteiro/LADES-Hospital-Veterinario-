/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.bean;

import com.lades.sihv.controller.*;
import com.lades.sihv.controller.consulta.MaxCodigoConsulta;
import com.lades.sihv.classeMolde.FormsExames;
import com.lades.sihv.classeMolde.CollectionClasses;
import com.lades.sihv.model.User;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author thiberius
 */
@ManagedBean(name = "consultaBean")
@ViewScoped

public class MBconsulta extends AbstractBean{

    private int maxCodConsulta;
    
    private User medicoVET;

    private String confirmeCRMV;
    private String confirmeSENHA;
    private boolean medicoCOFIRMADO = false;

    private CollectionClasses collectionClasses;
    
    private FormsExames formsExame;

    private boolean confirmeRAIOX = false;
    private boolean confirmeUltrasson = false;

    private String codRaioX;
    private String codUltrasson;
    private int numCodImage;

    /*O método prepara os objetos necessários 
    para receber informações escritas pelo usuário, 
    o mesmo também faz a limpeza dos campos utilizados*/
    public void prepararNovaConsulta() {
        getFormsExame().prepararFormsConsulta();
    }

    /*Método utilizado para salvar uma nova consulta.
    Obs.: a consulta será salva caso tenha confirmação 
    do medico veterinário usando o método 
    confirmaMEDICO()*/
    public void adicionarNovaConsulta() {
        confirmaMEDICO();
        if (medicoCOFIRMADO == true) {
            try {
                getFormsExame().prepareConsulta(getObjData(), collectionClasses.getAnimais(), medicoVET);
                getDaoGenerico().save(getFormsExame().getConsulta());

                getFormsExame().prepareAnamnese();
                getFormsExame().prepareSisDigestorio();
                getFormsExame().prepareSisRespCardio();
                getFormsExame().prepareSisUrinarioMamaria();
                getFormsExame().prepareSisTegumentar();
                getFormsExame().prepareSisNeurologico();
                getFormsExame().prepareSisOftalmico();
                getFormsExame().prepareSisMuscEsque();
                getFormsExame().prepareExameFisico();
                getDaoGenerico().save(getFormsExame().getAnamnese());
                getDaoGenerico().save(getFormsExame().getSisDigestorio());
                getDaoGenerico().save(getFormsExame().getSisRespCardio());
                getDaoGenerico().save(getFormsExame().getSisUrinarioMamaria());
                getDaoGenerico().save(getFormsExame().getSisTegumentar());
                getDaoGenerico().save(getFormsExame().getSisNeurologico());
                getDaoGenerico().save(getFormsExame().getSisOftalmico());
                getDaoGenerico().save(getFormsExame().getSisMuscEsque());
                getDaoGenerico().save(getFormsExame().getExameFisico());
                if (confirmeRAIOX == true) {
                    getFormsExame().prepareExameImageRaioX(getObjData(), codRaioX);
                    getDaoGenerico().save(getFormsExame().getExameImageRaioX());
                }
                if (confirmeUltrasson == true) {
                    getFormsExame().prepareExameImageUltra(getObjData(), codUltrasson);
                    getDaoGenerico().save(getFormsExame().getExameImageUltra());
                }
                getObjMessage().info("Cosulta efetuada.", "Consulta realizada com sucesso.");
            } catch (Exception e) {
                getObjMessage().warn("Erro ao efetuar cadastro!", "Verifique os dados e tente novamente!");
            }
        }
    }

    /*O método é utilizar para saber o
    maior código de um exame por imagem.*/
    public void maxExameImagem() {
        List<?> list;
        list = getDaoGenerico().list("select e.id.pkExameImage from ExameImage e where e.id.pkExameImage=1");
        if (list.size() > 0) {
            numCodImage = (int) getDaoGenerico().list("select max(e.id.pkExameImage) from ExameImage e").get(0);
        }
    }

    /*O método é utilizar para gera o código de
    um exame por imagem no formato AnoNumero.*/
    private void gerarCodExameImagem() {
        int num1;
        int num2;
        if (confirmeRAIOX == true && confirmeUltrasson == true) {
            // Ambos exames por imagem
            num1 = numCodImage + 1;
            num2 = num1 + 1;
            this.codRaioX = "" + Calendar.getInstance().get(Calendar.YEAR) + num1;
            this.codUltrasson = "" + Calendar.getInstance().get(Calendar.YEAR) + num2;
        } else if (confirmeRAIOX == true && confirmeUltrasson == false) {
            //Para raio x
            num1 = numCodImage + 1;
            this.codRaioX = "" + Calendar.getInstance().get(Calendar.YEAR) + num1;
            codUltrasson = "";
        } else {
            //Para ultrassom
            num2 = numCodImage + 1;
            this.codUltrasson = "" + Calendar.getInstance().get(Calendar.YEAR) + num2;
            codRaioX = "";
        }
    }

    /*O método é chamado para atestar que um medico
    veterinário ira fazer a consulta, ou seja, 
    uma nova consulta só será concretizada 
    se houver o aval do mesmo*/
    private void confirmaMEDICO() {
        confirmeSENHA = new Security().encrypter(confirmeSENHA);
        List<User> userLista;
        userLista = getDaoGenerico().list("select u from User u where u.userSenha='" + confirmeSENHA + "' and u.crmvMatricula='" + confirmeCRMV + "'");

        if (userLista.size() > 0) {
            medicoVET = userLista.get(0);
            medicoCOFIRMADO = true;
        } else {
            medicoCOFIRMADO = false;
            getObjMessage().warn("Verificação não confirmada!", "É necessário um medico veterinário cadastrado!");
        }
    }

    /*O método direciona o usuário para uma
    pagina que exibirá todos os exames.*/
    public void verConsulta() {
        formsExame = getDaoGenerico().viewCONSULTA("" + collectionClasses.getConsulta().getPkConsulta());
        try {
            getObjTools().redirecionar("/SIHV/faces/sihv-telas-exame/Exames.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(MBconsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*O método direciona o usuário para o
    preenchimento dos formulário de consultas.*/
    public void continuarConsulta() {
        try {
            getObjTools().redirecionar("/SIHV/faces/sihv-telas-exame/Nova_Consulta.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(MBconsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*O métodos GETs e SETs utilizados para*/
    

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
        gerarCodExameImagem();
    }

    public boolean isConfirmeUltrasson() {
        return confirmeUltrasson;
    }

    public void setConfirmeUltrasson(boolean confirmeUltrasson) {
        this.confirmeUltrasson = confirmeUltrasson;
        gerarCodExameImagem();
    }

    public CollectionClasses getCollectionClasses() {
        return collectionClasses;
    }

    public void setCollectionClasses(CollectionClasses collectionClasses) {
        this.collectionClasses = collectionClasses;
    }

    public FormsExames getFormsExame() {
        if(formsExame == null){
            formsExame = new FormsExames();
        }
        return formsExame;
    }

    public void setFormsExame(FormsExames formsExame) {
        this.formsExame = formsExame;
    }

    /*Método GET para exibir código demostrativos
    ao finalizar uma nova consulta.*/
    public int getMaxCodConsulta() {
        if(maxCodConsulta > 0){
            maxCodConsulta = new MaxCodigoConsulta().maxConsultaCod();
        }
        return maxCodConsulta;
    }
    //------------------------------------------------------------------
}
