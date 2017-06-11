/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.classeMolde;

import com.lades.sihv.controller.BeautyText;
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

    //------Métodos para Consulta-------------------------------------- 
    public void prepareConsulta(Date date, Animais animal, User medicoVET) {
        getConsulta().setDataConsulta(date);
        consulta.setSistemasAfetados(sistemasAfetados());
        consulta.setAnimais(animal);
        consulta.setUser(medicoVET);
    }

    public Consulta getConsulta() {
        if (consulta == null) {
            consulta = new Consulta();
        }
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }
    //-----------------------------------------------------------------

    //------Métodos para Anamnese--------------------------------------
    public void prepareAnamnese() {
        BeautyText Stringer = new BeautyText();
        anamneseId = new AnamneseId();
        anamneseId.setConsultaFkConsulta(consulta.getPkConsulta());
        anamnese.setId(anamneseId);
        anamnese.setVacinacao(Stringer.concatenaSTRING(vacinacao));
        anamnese.setQualEctoparasitas(Stringer.concatenaSTRING(qualEctoparasitas));
        anamnese.setAcessoRua(Stringer.concatenaSTRING(acessoArua));
    }

    public Anamnese getAnamnese() {
        if (anamnese == null) {
            anamnese = new Anamnese();
        }
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
    public void prepareSisDigestorio() {
        sisDigestorioId = new SisDigestorioId();
        sisDigestorioId.setConsultaFkConsulta(consulta.getPkConsulta());
        sisDigestorio.setId(sisDigestorioId);
    }

    public SisDigestorio getSisDigestorio() {
        if (sisDigestorio == null) {
            sisDigestorio = new SisDigestorio();
        }
        return sisDigestorio;
    }

    public void setSisDigestorio(SisDigestorio sisDigestorio) {
        this.sisDigestorio = sisDigestorio;
    }
    //-----------------------------------------------------------------

    //------Métodos para SisRespCardio---------------------------------
    public void prepareSisRespCardio() {
        sisRespCardioId = new SisRespCardioId();
        sisRespCardioId.setConsultaFkConsulta(consulta.getPkConsulta());
        sisRespCardio.setId(sisRespCardioId);
    }

    public SisRespCardio getSisRespCardio() {
        if (sisRespCardio == null) {
            sisRespCardio = new SisRespCardio();
        }
        return sisRespCardio;
    }

    public void setSisRespCardio(SisRespCardio sisRespCardio) {
        this.sisRespCardio = sisRespCardio;
    }
    //-----------------------------------------------------------------

    //------Métodos para SisUrinarioMamaria----------------------------
    public void prepareSisUrinarioMamaria() {
        sisUrinarioMamariaId = new SisUrinarioMamariaId();
        sisUrinarioMamariaId.setConsultaFkConsulta(consulta.getPkConsulta());
        sisUrinarioMamaria.setId(sisUrinarioMamariaId);
    }

    public SisUrinarioMamaria getSisUrinarioMamaria() {
        if (sisUrinarioMamaria == null) {
            sisUrinarioMamaria = new SisUrinarioMamaria();
        }
        return sisUrinarioMamaria;
    }

    public void setSisUrinarioMamaria(SisUrinarioMamaria sisUrinarioMamaria) {
        this.sisUrinarioMamaria = sisUrinarioMamaria;
    }
    //-----------------------------------------------------------------

    //------Métodos para SisTegumentar---------------------------------
    public void prepareSisTegumentar() {
        sisTegumentarId = new SisTegumentarId();
        sisTegumentarId.setConsultaFkConsulta(consulta.getPkConsulta());
        sisTegumentar.setId(sisTegumentarId);
    }

    public SisTegumentar getSisTegumentar() {
        if (sisTegumentar == null) {
            sisTegumentar = new SisTegumentar();
        }
        return sisTegumentar;
    }

    public void setSisTegumentar(SisTegumentar sisTegumentar) {
        this.sisTegumentar = sisTegumentar;
    }
    //-----------------------------------------------------------------

    //------Métodos para SisNeurologico--------------------------------
    public void prepareSisNeurologico() {
        sisNeurologicoId = new SisNeurologicoId();
        sisNeurologicoId.setConsultaFkConsulta(consulta.getPkConsulta());
        sisNeurologico.setId(sisNeurologicoId);
    }

    public SisNeurologico getSisNeurologico() {
        if (sisNeurologico == null) {
            sisNeurologico = new SisNeurologico();
        }
        return sisNeurologico;
    }

    public void setSisNeurologico(SisNeurologico sisNeurologico) {
        this.sisNeurologico = sisNeurologico;
    }
    //-----------------------------------------------------------------

    //------Métodos para SisOftalmico----------------------------------
    public void prepareSisOftalmico() {
        sisOftalmicoId = new SisOftalmicoId();
        sisOftalmicoId.setConsultaFkConsulta(consulta.getPkConsulta());
        sisOftalmico.setId(sisOftalmicoId);
    }

    public SisOftalmico getSisOftalmico() {
        if (sisOftalmico == null) {
            sisOftalmico = new SisOftalmico();
        }
        return sisOftalmico;
    }

    public void setSisOftalmico(SisOftalmico sisOftalmico) {
        this.sisOftalmico = sisOftalmico;
    }
    //-----------------------------------------------------------------

    //------Métodos para SisMuscEsque----------------------------------
    public void prepareSisMuscEsque() {
        sisMuscEsqueId = new SisMuscEsqueId();
        sisMuscEsqueId.setConsultaFkConsulta(consulta.getPkConsulta());
        sisMuscEsque.setId(sisMuscEsqueId);
    }

    public SisMuscEsque getSisMuscEsque() {
        if (sisMuscEsque == null) {
            sisMuscEsque = new SisMuscEsque();
        }
        return sisMuscEsque;
    }

    public void setSisMuscEsque(SisMuscEsque sisMuscEsque) {
        this.sisMuscEsque = sisMuscEsque;
    }
    //-----------------------------------------------------------------

    //------Métodos para ExameFisico-----------------------------------
    public void prepareExameFisico() {
        exameFisicoId = new ExameFisicoId();
        exameFisicoId.setConsultaFkConsulta(consulta.getPkConsulta());
        exameFisico.setId(exameFisicoId);
    }

    public ExameFisico getExameFisico() {
        if (exameFisico == null) {
            exameFisico = new ExameFisico();
        }
        return exameFisico;
    }

    public void setExameFisico(ExameFisico exameFisico) {
        this.exameFisico = exameFisico;
    }
    //-----------------------------------------------------------------

    //------Métodos para ExameImageRaioX-------------------------------
    public void prepareExameImageRaioX(Date date, String codRaioX) {
        exameImageRaioXId = new ExameImageId();
        exameImageRaioXId.setConsultaFkConsulta(consulta.getPkConsulta());
        exameImageRaioX.setId(exameImageRaioXId);
        exameImageRaioX.setTipo("RAIOX");
        exameImageRaioX.setDataExaImage(date);
        exameImageRaioX.setStatus("Pendente");
        exameImageRaioX.setCodExameImage(codRaioX);
    }

    public ExameImage getExameImageRaioX() {
        if(exameImageRaioX == null){
            exameImageRaioX = new ExameImage();
        }
        return exameImageRaioX;
    }

    public void setExameImageRaioX(ExameImage exameImageRaioX) {
        this.exameImageRaioX = exameImageRaioX;
    }
    //-----------------------------------------------------------------

    //------Métodos para ExameImageUltra-------------------------------
    public void prepareExameImageUltra(Date date, String codUltrasson) {
        exameImageUltraId = new ExameImageId();
        exameImageUltraId.setConsultaFkConsulta(consulta.getPkConsulta());
        exameImageUltra.setId(exameImageUltraId);
        exameImageUltra.setTipo("ULTRASSOM");
        exameImageUltra.setDataExaImage(date);
        exameImageUltra.setStatus("Pendente");
        exameImageUltra.setCodExameImage(codUltrasson);
    }

    public ExameImage getExameImageUltra() {
        if(exameImageUltra == null){
            exameImageUltra = new ExameImage();
        }
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
}
