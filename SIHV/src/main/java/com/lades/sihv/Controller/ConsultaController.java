/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.Controller;

import com.lades.sihv.DAO.GenericoDAO;
import com.lades.sihv.DAO.GenericoDAOImpl;
import com.lades.sihv.Security;
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
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author thiberius
 */
public class ConsultaController implements Serializable{
    
    private final GenericoDAO daoGenerico = new GenericoDAOImpl();
    private final FacesMessages message = new FacesMessages();
    
    private Consulta novaConsulta;
    private Date data;
    
    private User medicoVET;
    
    private String confirmeCRMV;
    private String confirmeSENHA;
    private boolean medicoCOFIRMADO = false;
    
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
    
    private ExameImage exameImage;
    private ExameImageId exameImageId;
    
    private boolean confirmeRAIOX = false;
    private boolean confirmeUltrasson = false;
    
    
    
    
    /*O método prepara os objetos necessários 
    para receber informações escritas pelo usuário, 
    o mesmo também faz a limpeza dos campos utilizados 
    e listas(Obs.: a lista é limpa caso esteja cheia)*/
    public void prepararNovaConsulta(){
        novaConsulta = new Consulta();
        data = new Date();
        
        anamnese = new Anamnese();
        anamneseId =  new AnamneseId();
        
        sisDigestorio = new SisDigestorio();
        sisDigestorioId = new SisDigestorioId();
        
        sisRespCardio =  new SisRespCardio();
        sisRespCardioId =  new SisRespCardioId();
        
        sisUrinarioMamaria =  new SisUrinarioMamaria();
        sisUrinarioMamariaId = new SisUrinarioMamariaId();
        
        sisTegumentar =  new SisTegumentar();
        sisTegumentarId =  new SisTegumentarId();
        
        sisNeurologico = new SisNeurologico();
        sisNeurologicoId = new SisNeurologicoId();
    
        sisOftalmico = new SisOftalmico();
        sisOftalmicoId =  new SisOftalmicoId();
    
        sisMuscEsque = new SisMuscEsque();
        sisMuscEsqueId = new SisMuscEsqueId();
        
        exameFisico =  new ExameFisico();
        exameFisicoId = new ExameFisicoId();
        
        exameImage = new ExameImage();
        exameImageId = new ExameImageId();
    }
    
    
    
    
    
    /*Método utilizado para salvar uma nova consulta.
    Obs.: a consulta será salva caso tenha confirmação 
    do medico veterinário usando o método 
    confirmaMEDICO()*/
    public void adicionarNovaConsulta(Animais animais){
        confirmaMEDICO();
        if (medicoCOFIRMADO == true) {
            try {
                novaConsulta.setDataConsulta(data);
                novaConsulta.setSistemasAfetados("Teste de sistemas afetados");
                novaConsulta.setAnimais(animais);

                novaConsulta.setUser(medicoVET);
                daoGenerico.save(novaConsulta);

                anamneseId.setConsultaFkConsulta(novaConsulta.getPkConsulta());
                anamnese.setId(anamneseId); 
                anamnese.setVacinacao(concatenaSTRING(vacinacao));
                anamnese.setQualEctoparasitas(concatenaSTRING(qualEctoparasitas));
                anamnese.setAcessoRua(concatenaSTRING(acessoArua));

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
                
                if (confirmeRAIOX == true || confirmeUltrasson == true) {
                    exameImageId.setConsultaFkConsulta(novaConsulta.getPkConsulta());
                    exameImage.setId(exameImageId);
                    exameImage.setDataExaImage(data);
                    exameImage.setTipo((confirmeRAIOX == true)?"RAIOX":"ULTRASSOM");
                    daoGenerico.save(exameImage);
                }

                message.info("Cosulta efetuada.","Consulta realizada com sucesso.");
            } catch (Exception e) {
                message.warn("Erro ao efetuar cadastro!", "Verifique os dados e tente novamente!");
            }
        }
    } 
    
    
    
    
    /*O método é chamado para atestar que um medico
    veterinário ira fazer a consulta, ou seja, 
    uma nova consulta só será concretizada 
    se houver o aval do mesmo*/
    private void confirmaMEDICO(){
        confirmeSENHA = Security.getMD5(confirmeSENHA);
        List<User> userLista;
        userLista =  daoGenerico.list("select u from User u where u.userSenha='"+confirmeSENHA+"' and u.crmvMatricula='"+confirmeCRMV+"'");
        
        if (userLista.size() > 0) {
            medicoVET = userLista.get(0);
            medicoCOFIRMADO = true;
        }else{
            medicoCOFIRMADO = false;
            message.warn("Verificação não confirmada!", "É necessário um medico veterinário cadastrado!");
        }
    }
    
    
    
    
    /*O método é utilizado para concatenar valores
    pertencentes ao inputs do tipo checkBox.*/
    private String concatenaSTRING(String vetor[]){
        String textoTEMP="";
        for (String vetor1 : vetor) {
            textoTEMP += " "+vetor1;
        }
        return textoTEMP;
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
    public ExameImage getExameImage() {
        return exameImage;
    }

    public void setExameImage(ExameImage exameImage) {
        this.exameImage = exameImage;
    }

    //------------------------------------------------------------------

    public boolean isConfirmeRAIOX() {
        return confirmeRAIOX;
    }

    public void setConfirmeRAIOX(boolean confirmeRAIOX) {
        this.confirmeRAIOX = confirmeRAIOX;
    }

    public boolean isConfirmeUltrasson() {
        return confirmeUltrasson;
    }

    public void setConfirmeUltrasson(boolean confirmeUltrasson) {
        this.confirmeUltrasson = confirmeUltrasson;
    }

    
    
    
    
    
}
