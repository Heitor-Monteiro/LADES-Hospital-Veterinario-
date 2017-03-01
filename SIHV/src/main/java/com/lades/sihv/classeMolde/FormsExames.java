/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.classeMolde;

import com.lades.sihv.BeautyText;
import com.lades.sihv.model.Anamnese;
import com.lades.sihv.model.AnamneseId;
import com.lades.sihv.model.Animais;
import com.lades.sihv.model.Consulta;
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
import com.lades.sihv.model.User;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author thiberius
 */
public class FormsExames implements Serializable {

    public Consulta consulta;

    public Anamnese anamnese;
    public AnamneseId anamneseId;
    public String vacinacao[];
    public String qualEctoparasitas[];
    public String acessoArua[];

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

    private ExameImage exameImageUltra;
    private ExameImageId exameImageUltraId;

    /*O método prepara os objetos necessários 
    para receber informações escritas pelo usuário, 
    o mesmo também faz a limpeza dos campos utilizados*/
    public void prepararFormsConsulta() {
        consulta = new Consulta();

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

        exameImageUltra = new ExameImage();
        exameImageUltraId = new ExameImageId();
    }

    //------Métodos para Consulta--------------------------------------
    public void CreatObjConsulta() {
        this.consulta = new Consulta();
    }
    
    public void prepareConsulta(Date date, Animais animal, User medicoVET) {
        getConsulta().setDataConsulta(date);
        consulta.setSistemasAfetados(sistemasAfetados());
        consulta.setAnimais(animal);
        consulta.setUser(medicoVET);
    }
    
    public Consulta getConsulta() {
        if(consulta == null){
            consulta = new Consulta();
        }
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }
    //-----------------------------------------------------------------

    //------Métodos para Anamnese--------------------------------------
    public void CreatObjAnamnese() {
        this.anamnese = new Anamnese();
    }

    public void CreatObjAnamneseId() {
        this.anamneseId = new AnamneseId();
    }

    public void prepareAnamnese() {
        BeautyText Stringer = new BeautyText();
        
        anamneseId.setConsultaFkConsulta(consulta.getPkConsulta());
        anamnese.setId(anamneseId);
        anamnese.setVacinacao(Stringer.concatenaSTRING(vacinacao));
        anamnese.setQualEctoparasitas(Stringer.concatenaSTRING(qualEctoparasitas));
        anamnese.setAcessoRua(Stringer.concatenaSTRING(acessoArua));
    }

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

    //------Métodos para SisDigestorio---------------------------------
    public void CreatObjSisDigestorio() {
        this.sisDigestorio = new SisDigestorio();
    }

    public void CreatObjSisDigestorioId() {
        this.sisDigestorioId = new SisDigestorioId();
    }

    public void prepareSisDigestorio() {
        sisDigestorioId.setConsultaFkConsulta(consulta.getPkConsulta());
        sisDigestorio.setId(sisDigestorioId);
    }

    public SisDigestorio getSisDigestorio() {
        return sisDigestorio;
    }

    public void setSisDigestorio(SisDigestorio sisDigestorio) {
        this.sisDigestorio = sisDigestorio;
    }
    //-----------------------------------------------------------------

    //------Métodos para SisRespCardio---------------------------------
    public void CreatObjSisRespCardio() {
        this.sisRespCardio = new SisRespCardio();
    }

    public void CreatObjSisRespCardioId() {
        this.sisRespCardioId = new SisRespCardioId();
    }

    public void prepareSisRespCardio() {
        sisRespCardioId.setConsultaFkConsulta(consulta.getPkConsulta());
        sisRespCardio.setId(sisRespCardioId);
    }

    public SisRespCardio getSisRespCardio() {
        return sisRespCardio;
    }

    public void setSisRespCardio(SisRespCardio sisRespCardio) {
        this.sisRespCardio = sisRespCardio;
    }
    //-----------------------------------------------------------------

    //------Métodos para SisUrinarioMamaria----------------------------
    public void CreatObjSisUrinarioMamaria() {
        this.sisUrinarioMamaria = new SisUrinarioMamaria();
    }

    public void CreatObjSisUrinarioMamariaId() {
        this.sisUrinarioMamariaId = new SisUrinarioMamariaId();
    }

    public void prepareSisUrinarioMamaria() {
        sisUrinarioMamariaId.setConsultaFkConsulta(consulta.getPkConsulta());
        sisUrinarioMamaria.setId(sisUrinarioMamariaId);
    }

    public SisUrinarioMamaria getSisUrinarioMamaria() {
        return sisUrinarioMamaria;
    }

    public void setSisUrinarioMamaria(SisUrinarioMamaria sisUrinarioMamaria) {
        this.sisUrinarioMamaria = sisUrinarioMamaria;
    }
    //-----------------------------------------------------------------

    //------Métodos para SisTegumentar---------------------------------
    public void CreatObjSisTegumentar() {
        this.sisTegumentar = new SisTegumentar();
    }

    public void CreatObjSisTegumentarId() {
        this.sisTegumentarId = new SisTegumentarId();
    }

    public void prepareSisTegumentar() {
        sisTegumentarId.setConsultaFkConsulta(consulta.getPkConsulta());
        sisTegumentar.setId(sisTegumentarId);
    }

    public SisTegumentar getSisTegumentar() {
        return sisTegumentar;
    }

    public void setSisTegumentar(SisTegumentar sisTegumentar) {
        this.sisTegumentar = sisTegumentar;
    }
    //-----------------------------------------------------------------

    //------Métodos para SisNeurologico--------------------------------
    public void CreatObjSisNeurologico() {
        this.sisNeurologico = new SisNeurologico();
    }

    public void CreatObjSisNeurologicoId() {
        this.sisNeurologicoId = new SisNeurologicoId();
    }

    public void prepareSisNeurologico() {
        sisNeurologicoId.setConsultaFkConsulta(consulta.getPkConsulta());
        sisNeurologico.setId(sisNeurologicoId);
    }

    public SisNeurologico getSisNeurologico() {
        return sisNeurologico;
    }

    public void setSisNeurologico(SisNeurologico sisNeurologico) {
        this.sisNeurologico = sisNeurologico;
    }
    //-----------------------------------------------------------------

    //------Métodos para SisOftalmico----------------------------------
    public void CreatObjSisOftalmico() {
        this.sisOftalmico = new SisOftalmico();
    }

    public void CreatObjSisOftalmicoId() {
        this.sisOftalmicoId = new SisOftalmicoId();
    }

    public void prepareSisOftalmico() {
        sisOftalmicoId.setConsultaFkConsulta(consulta.getPkConsulta());
        sisOftalmico.setId(sisOftalmicoId);
    }

    public SisOftalmico getSisOftalmico() {
        return sisOftalmico;
    }

    public void setSisOftalmico(SisOftalmico sisOftalmico) {
        this.sisOftalmico = sisOftalmico;
    }
    //-----------------------------------------------------------------

    //------Métodos para SisMuscEsque----------------------------------
    public void CreatObjSisMuscEsque() {
        this.sisMuscEsque = new SisMuscEsque();
    }

    public void CreatObjSisMuscEsqueId() {
        this.sisMuscEsqueId = new SisMuscEsqueId();
    }

    public void prepareSisMuscEsque() {
        sisMuscEsqueId.setConsultaFkConsulta(consulta.getPkConsulta());
        sisMuscEsque.setId(sisMuscEsqueId);
    }

    public SisMuscEsque getSisMuscEsque() {
        return sisMuscEsque;
    }

    public void setSisMuscEsque(SisMuscEsque sisMuscEsque) {
        this.sisMuscEsque = sisMuscEsque;
    }
    //-----------------------------------------------------------------

    //------Métodos para ExameFisico-----------------------------------
    public void CreatObjExameFisico() {
        this.exameFisico = new ExameFisico();
    }

    public void CreatObjExameFisicoId() {
        this.exameFisicoId = new ExameFisicoId();
    }

    public void prepareExameFisico() {
        exameFisicoId.setConsultaFkConsulta(consulta.getPkConsulta());
        exameFisico.setId(exameFisicoId);
    }

    public ExameFisico getExameFisico() {
        return exameFisico;
    }

    public void setExameFisico(ExameFisico exameFisico) {
        this.exameFisico = exameFisico;
    }
    //-----------------------------------------------------------------

    //------Métodos para ExameImageRaioX-------------------------------
    public void CreatObjExameImageRaioX() {
        this.exameImageRaioX = new ExameImage();
    }

    public void CreatObjExameImageRaioXId() {
        this.exameImageRaioXId = new ExameImageId();
    }

    public void prepareExameImageRaioX(Date date, String codRaioX) {
        exameImageRaioXId.setConsultaFkConsulta(consulta.getPkConsulta());
        exameImageRaioX.setId(exameImageRaioXId);
        exameImageRaioX.setTipo("RAIOX");
        exameImageRaioX.setDataExaImage(date);
        exameImageRaioX.setStatus("Pendente");
        exameImageRaioX.setCodExameImage(codRaioX);
    }

    public ExameImage getExameImageRaioX() {
        return exameImageRaioX;
    }

    public void setExameImageRaioX(ExameImage exameImageRaioX) {
        this.exameImageRaioX = exameImageRaioX;
    }
    //-----------------------------------------------------------------

    //------Métodos para ExameImageUltra-------------------------------
    public void CreatObjExameImageUltra() {
        this.exameImageUltra = new ExameImage();
    }

    public void CreatObjExameImageUltraId() {
        this.exameImageUltraId = new ExameImageId();
    }

    public void prepareExameImageUltra(Date date, String codUltrasson) {
        exameImageUltraId.setConsultaFkConsulta(consulta.getPkConsulta());
        exameImageUltra.setId(exameImageUltraId);
        exameImageUltra.setTipo("ULTRASSOM");
        exameImageUltra.setDataExaImage(date);
        exameImageUltra.setStatus("Pendente");
        exameImageUltra.setCodExameImage(codUltrasson);
    }

    public ExameImage getExameImageUltra() {
        return exameImageUltra;
    }

    public void setExameImageUltra(ExameImage exameImageUltra) {
        this.exameImageUltra = exameImageUltra;
    }
    //-----------------------------------------------------------------

    /*O método verifica quais sistema
    de anamnese foram afetados.*/
    public String sistemasAfetados() {
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

    public AnamneseId getAnamneseId() {
        return anamneseId;
    }

    public void setAnamneseId(AnamneseId anamneseId) {
        this.anamneseId = anamneseId;
    }
    
    
}
