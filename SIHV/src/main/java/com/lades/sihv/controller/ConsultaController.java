/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.controller;

import com.lades.sihv.DAO.GenericoDAO;
import com.lades.sihv.Security;
import com.lades.sihv.Tools;
import com.lades.sihv.classeMolde.FormsExames;
import com.lades.sihv.classeMolde.PesquisaConsulta;
import com.lades.sihv.model.Consulta;
import com.lades.sihv.model.Animais;
import com.lades.sihv.model.User;
import com.lades.sihv.model.Anamnese;
import com.lades.sihv.model.AnamneseId;
import com.lades.sihv.model.SisDigestorio;
import com.lades.sihv.model.SisDigestorioId;
import com.lades.sihv.model.SisRespCardio;
import com.lades.sihv.model.SisRespCardioId;
import com.lades.sihv.model.SisUrinarioMamaria;
import com.lades.sihv.model.SisUrinarioMamariaId;
import com.lades.sihv.model.SisTegumentar;
import com.lades.sihv.model.SisTegumentarId;
import com.lades.sihv.model.SisNeurologico;
import com.lades.sihv.model.SisNeurologicoId;
import com.lades.sihv.model.SisOftalmico;
import com.lades.sihv.model.SisOftalmicoId;
import com.lades.sihv.model.SisMuscEsque;
import com.lades.sihv.model.SisMuscEsqueId;
import com.lades.sihv.model.ExameFisico;
import com.lades.sihv.model.ExameFisicoId;
import com.lades.sihv.model.ExameImage;
import com.lades.sihv.model.ExameImageId;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thiberius
 */
public class ConsultaController implements Serializable {

    private GenericoDAO daoGenerico;
    private FacesMessages message;
    private PesquisaController pesquisaControle;
    private Tools tools;

    private Consulta novaConsulta;
    private Date data;

    private User medicoVET;

    private String confirmeCRMV;
    private String confirmeSENHA;
    private boolean medicoCOFIRMADO = false;

    private PesquisaConsulta pesquisaConsulta;
    private FormsExames formsExame;

    private Anamnese anamnese;
    private AnamneseId anamneseId;
    private String vacinacao[];
    private String qualEctoparasitas[];
    private String acessoArua[];

    private SisDigestorio sisDigestorio;
    private SisDigestorioId sisDigestorioId;

    private SisRespCardio sisRespCardio;
    private SisRespCardioId sisRespCardioId;

    private SisUrinarioMamaria sisUrinarioMamaria;
    private SisUrinarioMamariaId sisUrinarioMamariaId;

    private SisTegumentar sisTegumentar;
    private SisTegumentarId sisTegumentarId;

    private SisNeurologico sisNeurologico;
    private SisNeurologicoId sisNeurologicoId;

    private SisOftalmico sisOftalmico;
    private SisOftalmicoId sisOftalmicoId;

    private SisMuscEsque sisMuscEsque;
    private SisMuscEsqueId sisMuscEsqueId;

    private ExameFisico exameFisico;
    private ExameFisicoId exameFisicoId;

    private ExameImage exameImageRaioX;
    private ExameImageId exameImageRaioXId;

    private ExameImage exameImageUltrason;
    private ExameImageId exameImageUltrasonId;

    private boolean confirmeRAIOX = false;
    private boolean confirmeUltrasson = false;

    private String codRaioX;
    private String codUltrasson;
    private int numCodImage;

    private ConsultaController() {
    }

    public ConsultaController(GenericoDAO daoGenerico, FacesMessages message) {
        this.daoGenerico = daoGenerico;
        this.message = message;
    }

    public ConsultaController(GenericoDAO daoGenerico, FacesMessages message, PesquisaController pesquisaControle, Tools tools) {
        this.daoGenerico = daoGenerico;
        this.message = message;
        this.pesquisaControle = pesquisaControle;
        this.tools = tools;
    }

    /*O método prepara os objetos necessários 
    para receber informações escritas pelo usuário, 
    o mesmo também faz a limpeza dos campos utilizados*/
    public void prepararNovaConsulta() {
        novaConsulta = new Consulta();
        data = new Date();

        anamnese = new Anamnese();
        anamneseId = new AnamneseId();

        sisDigestorio = new SisDigestorio();
        sisDigestorioId = new SisDigestorioId();

        sisRespCardio = new SisRespCardio();
        sisRespCardioId = new SisRespCardioId();

        sisUrinarioMamaria = new SisUrinarioMamaria();
        sisUrinarioMamariaId = new SisUrinarioMamariaId();

        sisTegumentar = new SisTegumentar();
        sisTegumentarId = new SisTegumentarId();

        sisNeurologico = new SisNeurologico();
        sisNeurologicoId = new SisNeurologicoId();

        sisOftalmico = new SisOftalmico();
        sisOftalmicoId = new SisOftalmicoId();

        sisMuscEsque = new SisMuscEsque();
        sisMuscEsqueId = new SisMuscEsqueId();

        exameFisico = new ExameFisico();
        exameFisicoId = new ExameFisicoId();

        exameImageRaioX = new ExameImage();
        exameImageRaioXId = new ExameImageId();

        exameImageUltrason = new ExameImage();
        exameImageUltrasonId = new ExameImageId();
    }

    /*Método utilizado para salvar uma nova consulta.
    Obs.: a consulta será salva caso tenha confirmação 
    do medico veterinário usando o método 
    confirmaMEDICO()*/
    public void adicionarNovaConsulta(Animais animais) {
        confirmaMEDICO();
        if (medicoCOFIRMADO == true) {
            try {
                novaConsulta.setDataConsulta(data);
                novaConsulta.setSistemasAfetados(sistemasAfetados());
                novaConsulta.setAnimais(animais);
                novaConsulta.setUser(medicoVET);
                daoGenerico.save(novaConsulta);

                anamneseId.setConsultaFkConsulta(novaConsulta.getPkConsulta());
                anamnese.setId(anamneseId);
                com.lades.sihv.BeautyText Stringer = new com.lades.sihv.BeautyText();
                anamnese.setVacinacao(Stringer.concatenaSTRING(vacinacao));
                anamnese.setQualEctoparasitas(Stringer.concatenaSTRING(qualEctoparasitas));
                anamnese.setAcessoRua(Stringer.concatenaSTRING(acessoArua));

                sisDigestorioId.setConsultaFkConsulta(novaConsulta.getPkConsulta());
                sisDigestorio.setId(sisDigestorioId);

                sisRespCardioId.setConsultaFkConsulta(novaConsulta.getPkConsulta());
                sisRespCardio.setId(sisRespCardioId);

                sisUrinarioMamariaId.setConsultaFkConsulta(novaConsulta.getPkConsulta());
                sisUrinarioMamaria.setId(sisUrinarioMamariaId);

                sisTegumentarId.setConsultaFkConsulta(novaConsulta.getPkConsulta());
                sisTegumentar.setId(sisTegumentarId);

                sisNeurologicoId.setConsultaFkConsulta(novaConsulta.getPkConsulta());
                sisNeurologico.setId(sisNeurologicoId);

                sisOftalmicoId.setConsultaFkConsulta(novaConsulta.getPkConsulta());
                sisOftalmico.setId(sisOftalmicoId);

                sisMuscEsqueId.setConsultaFkConsulta(novaConsulta.getPkConsulta());
                sisMuscEsque.setId(sisMuscEsqueId);

                exameFisicoId.setConsultaFkConsulta(novaConsulta.getPkConsulta());
                exameFisico.setId(exameFisicoId);

                daoGenerico.save(anamnese);
                daoGenerico.save(sisDigestorio);
                daoGenerico.save(sisRespCardio);
                daoGenerico.save(sisUrinarioMamaria);
                daoGenerico.save(sisTegumentar);
                daoGenerico.save(sisNeurologico);
                daoGenerico.save(sisOftalmico);
                daoGenerico.save(sisMuscEsque);
                daoGenerico.save(exameFisico);

                if (confirmeRAIOX == true) {
                    exameImageRaioXId.setConsultaFkConsulta(novaConsulta.getPkConsulta());
                    exameImageRaioX.setId(exameImageRaioXId);
                    exameImageRaioX.setDataExaImage(data);
                    exameImageRaioX.setTipo("RAIOX");
                    exameImageRaioX.setCodExameImage(codRaioX);
                    exameImageRaioX.setStatus("Pendente");
                    daoGenerico.save(exameImageRaioX);
                }
                if (confirmeUltrasson == true) {
                    exameImageUltrasonId.setConsultaFkConsulta(novaConsulta.getPkConsulta());
                    exameImageUltrason.setId(exameImageUltrasonId);
                    exameImageUltrason.setDataExaImage(data);
                    exameImageUltrason.setTipo("ULTRASSOM");
                    exameImageUltrason.setCodExameImage(codUltrasson);
                    exameImageUltrason.setStatus("Pendente");
                    daoGenerico.save(exameImageUltrason);
                }

                message.setTextoDialog("Cosulta efetuada!",
                        "Consulta realizada com sucesso.",
                        "/SIHV_Telas_Exame/Nova_Consulta");
                //message.info("Cosulta efetuada.","Consulta realizada com sucesso.");
            } catch (Exception e) {
                message.warn("Erro ao efetuar cadastro!", "Verifique os dados e tente novamente!");
            }
        }
    }

    public void maxExameImagem() {
        List<?> list;
        list = daoGenerico.list("select e.id.pkExameImage from ExameImage e where e.id.pkExameImage=1");
        if(list.size()>0){
            numCodImage = (int) daoGenerico.list("select max(e.id.pkExameImage) from ExameImage e").get(0);
        }
    }

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

    private String sistemasAfetados() {
        String sisAfetados = "";

        sisAfetados += ("Sim".equals(sisDigestorio.getSistemaAfetado())) ? "Sistema digestório e glândulas anexas, " : "";
        sisAfetados += ("Sim".equals(sisRespCardio.getSistemaAfetado())) ? "Sistema respiratório e cardiovascular, " : "";
        sisAfetados += ("Sim".equals(sisUrinarioMamaria.getSistemaAfetado())) ? "Sistema gênito-urinário e glândulas mamárias, " : "";
        sisAfetados += ("Sim".equals(sisTegumentar.getSistemaAfetado())) ? "Sistema tegumentar, " : "";
        sisAfetados += ("Sim".equals(sisNeurologico.getSistemaAfetado())) ? "Sistema neurológico, " : "";
        sisAfetados += ("Sim".equals(sisOftalmico.getSistemaAfetado())) ? "Sistema oftálmico, " : "";
        sisAfetados += ("Sim".equals(sisMuscEsque.getSistemaAfetado())) ? "Sistema músculo-esquelético, " : "";
        sisAfetados += ("".equals(sisAfetados)) ? "Não houve sistemas afetados" : "";

        return sisAfetados;
    }

    /*O método é chamado para atestar que um medico
    veterinário ira fazer a consulta, ou seja, 
    uma nova consulta só será concretizada 
    se houver o aval do mesmo*/
    private void confirmaMEDICO() {
        confirmeSENHA = new Security().encrypter(confirmeSENHA);
        List<User> userLista;
        userLista = daoGenerico.list("select u from User u where u.userSenha='" + confirmeSENHA + "' and u.crmvMatricula='" + confirmeCRMV + "'");

        if (userLista.size() > 0) {
            medicoVET = userLista.get(0);
            medicoCOFIRMADO = true;
        } else {
            medicoCOFIRMADO = false;
            message.warn("Verificação não confirmada!", "É necessário um medico veterinário cadastrado!");
        }
    }

    public void verConsulta() {
        formsExame = daoGenerico.viewCONSULTA("" + pesquisaConsulta.getConsulta().getPkConsulta());

        System.out.println(pesquisaConsulta.getAnimais().getNomeAnimal() + "===============================================================\n");

        try {
            tools.redirecionar("/SIHV/faces/SIHV_Telas_Exame/Exames.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ConsultaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*O métodos GETs e SETs utilizados para*/
    public Date getData() {
        return data;
    }

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

    //GETs & SET ANAMNESE
    public Anamnese getAnamnese() {
        return anamnese;
    }

    public void setAnamnese(Anamnese anamnese) {
        this.anamnese = anamnese;
    }

    public String[] getVacinacao() {
        return vacinacao;
    }

    public void setVacinacao(String[] vacinacao) {
        this.vacinacao = vacinacao;
    }

    public String[] getQualEctoparasitas() {
        return qualEctoparasitas;
    }

    public void setQualEctoparasitas(String[] qualEctoparasitas) {
        this.qualEctoparasitas = qualEctoparasitas;
    }

    public String[] getAcessoArua() {
        return acessoArua;
    }

    public void setAcessoArua(String[] acessoArua) {
        this.acessoArua = acessoArua;
    }
    //-----------------------------------------------------------------

    //------GETs & SETs de generico_Sis_Digestorio---------------------
    public SisDigestorio getSisDigestorio() {
        return sisDigestorio;
    }

    public void setSisDigestorio(SisDigestorio sisDigestorio) {
        this.sisDigestorio = sisDigestorio;
    }
    //-----------------------------------------------------------------

    //------GETs & SETs de generico_Sis_Resp_Cardio---------------------
    public SisRespCardio getSisRespCardio() {
        return sisRespCardio;
    }

    public void setSisRespCardio(SisRespCardio sisRespCardio) {
        this.sisRespCardio = sisRespCardio;
    }
    //------------------------------------------------------------------

    //------GETs & SETs SisUrinarioMamaria------------------------------
    public SisUrinarioMamaria getSisUrinarioMamaria() {
        return sisUrinarioMamaria;
    }

    public void setSisUrinarioMamaria(SisUrinarioMamaria sisUrinarioMamaria) {
        this.sisUrinarioMamaria = sisUrinarioMamaria;
    }
    //-----------------------------------------------------------------

    //------GETs & SETs SisTegumentar----------------------------------
    public SisTegumentar getSisTegumentar() {
        return sisTegumentar;
    }

    public void setSisTegumentar(SisTegumentar sisTegumentar) {
        this.sisTegumentar = sisTegumentar;
    }
    //-----------------------------------------------------------------

    //------GETs & SETs SisNeurologico----------------------------------
    public SisNeurologico getSisNeurologico() {
        return sisNeurologico;
    }

    public void setSisNeurologico(SisNeurologico sisNeurologico) {
        this.sisNeurologico = sisNeurologico;
    }
    //-----------------------------------------------------------------

    //------GETs & SETs SisOftalmico----------------------------------
    public SisOftalmico getSisOftalmico() {
        return sisOftalmico;
    }

    public void setSisOftalmico(SisOftalmico sisOftalmico) {
        this.sisOftalmico = sisOftalmico;
    }
    //-----------------------------------------------------------------

    //------GETs & SETs SisMuscEsque----------------------------------
    public SisMuscEsque getSisMuscEsque() {
        return sisMuscEsque;
    }

    public void setSisMuscEsque(SisMuscEsque sisMuscEsque) {
        this.sisMuscEsque = sisMuscEsque;
    }
    //-----------------------------------------------------------------

    //------GETs & SETs Exame Fisico----------------------------------
    public ExameFisico getExameFisico() {
        return exameFisico;
    }

    public void setExameFisico(ExameFisico exameFisico) {
        this.exameFisico = exameFisico;
    }
    //-----------------------------------------------------------------

    //------GETs & SETs Exame por Imagem----------------------------------
    public ExameImage getExameImageRaioX() {
        return exameImageRaioX;
    }

    public void setExameImageRaioX(ExameImage exameImageRaioX) {
        this.exameImageRaioX = exameImageRaioX;
    }

    public ExameImageId getExameImageRaioXId() {
        return exameImageRaioXId;
    }

    public void setExameImageRaioXId(ExameImageId exameImageRaioXId) {
        this.exameImageRaioXId = exameImageRaioXId;
    }

    public ExameImage getExameImageUltrason() {
        return exameImageUltrason;
    }

    public void setExameImageUltrason(ExameImage exameImageUltrason) {
        this.exameImageUltrason = exameImageUltrason;
    }

    public ExameImageId getExameImageUltrasonId() {
        return exameImageUltrasonId;
    }

    public void setExameImageUltrasonId(ExameImageId exameImageUltrasonId) {
        this.exameImageUltrasonId = exameImageUltrasonId;
    }
    //------------------------------------------------------------------
    
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

    public PesquisaController getPesquisaControle() {
        return pesquisaControle;
    }

    public void setPesquisaControle(PesquisaController pesquisaControle) {
        this.pesquisaControle = pesquisaControle;
    }

    public PesquisaConsulta getPesquisaConsulta() {
        return pesquisaConsulta;
    }

    public void setPesquisaConsulta(PesquisaConsulta pesquisaConsulta) {
        System.out.println("Selecionando objeto pesquisaConsulta========================");
        this.pesquisaConsulta = pesquisaConsulta;
    }

    public FormsExames getFormsExame() {
        return formsExame;
    }

    public void setFormsExame(FormsExames formsExame) {
        this.formsExame = formsExame;
    }
}
