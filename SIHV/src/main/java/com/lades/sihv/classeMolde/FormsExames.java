/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.classeMolde;

import com.lades.sihv.model.Anamnese;
import com.lades.sihv.model.AnamneseId;
import com.lades.sihv.model.ExameFisico;
import com.lades.sihv.model.ExameFisicoId;
import com.lades.sihv.model.ExameImage;
import com.lades.sihv.model.ExameImageId;
import com.lades.sihv.model.SisDigestorio;
import com.lades.sihv.model.SisDigestorioId;
import com.lades.sihv.model.SisMuscEsque;
import com.lades.sihv.model.SisMuscEsqueId;
import com.lades.sihv.model.SisNeurologico;
import com.lades.sihv.model.SisNeurologicoId;
import com.lades.sihv.model.SisOftalmico;
import com.lades.sihv.model.SisOftalmicoId;
import com.lades.sihv.model.SisRespCardio;
import com.lades.sihv.model.SisRespCardioId;
import com.lades.sihv.model.SisTegumentar;
import com.lades.sihv.model.SisTegumentarId;
import com.lades.sihv.model.SisUrinarioMamaria;
import com.lades.sihv.model.SisUrinarioMamariaId;
import java.io.Serializable;

/**
 *
 * @author thiberius
 */
public class FormsExames implements Serializable{
    
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*O método prepara os objetos necessários 
    para receber informações escritas pelo usuário, 
    o mesmo também faz a limpeza dos campos utilizados*/
    public void prepararFormsConsulta(){
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
    
    public void CreatObjAnamnese(){this.anamnese = new Anamnese();}
    public void CreatObjSisDigestorio(){this.sisDigestorio = new SisDigestorio();}
    public void CreatObjSisRespCardio(){this.sisRespCardio = new SisRespCardio();}
    public void CreatObjSisUrinarioMamaria(){this.sisUrinarioMamaria = new SisUrinarioMamaria();}
    public void CreatObjSisTegumentar(){this.sisTegumentar = new SisTegumentar();}
    public void CreatObjSisNeurologico(){this.sisNeurologico = new SisNeurologico();}
    public void CreatObjSisOftalmico(){this.sisOftalmico = new SisOftalmico();}
    public void CreatObjSisMuscEsque(){this.sisMuscEsque = new SisMuscEsque();}
    public void CreatObjExameFisico(){this.exameFisico = new ExameFisico();}
    public void CreatObjExameImage(){this.exameImage = new ExameImage();}
    
    
    
    
    
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
}
