/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lades.sihv.Controller;

import com.lades.sihv.DAO.GenericoDAO;
import com.lades.sihv.DAO.GenericoDAOImpl;
import com.lades.sihv.model.Consulta;
import com.lades.sihv.model.Animais;
import com.lades.sihv.model.Adm;
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
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author thiberius
 */
@ManagedBean(name = "ConsultaControle")
@SessionScoped
public class ConsultaController implements Serializable{
    protected final GenericoDAO daoGenerico = new GenericoDAOImpl();
    protected final FacesMessages message = new FacesMessages();
    
    protected Consulta novaConsulta;
    protected Date data;
    
    protected Adm medicoVET;
    protected Animais animais;
    protected List<Animais> animaisBuscados;
    
    protected List<Adm> admLista;
    protected String confirmeCRMV;
    protected String confirmeSENHA;
    protected boolean medicoCOFIRMADO;
    
    protected Anamnese anamnese;
    protected AnamneseId anamneseId;
    protected String vacinacao[];
    protected String qualEctoparasitas[];
    protected String acessoArua[];
    
    protected SisDigestorio sisDigestorio;
    protected SisDigestorioId sisDigestorioId;
    
    protected SisRespCardio sisRespCardio;
    protected SisRespCardioId sisRespCardioId;
    
    protected SisUrinarioMamaria sisUrinarioMamaria;
    protected SisUrinarioMamariaId sisUrinarioMamariaId;
    
    
    
    
    
    
    
    
    
    /*O métodos GETs e SETs utilizados para*/
    public Animais getAnimais() {
        return animais;
    }
    
    public void setAnimais(Animais animais) {
        this.animais = animais;
    }

    public List<Animais> getAnimaisBuscados() {
        return animaisBuscados;
    }

    public Date getData() {
        return data;
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
    
    
    //------GETs & SETs SisUrinarioMamaria
    public SisUrinarioMamaria getSisUrinarioMamaria() {
        return sisUrinarioMamaria;
    }

    public void setSisUrinarioMamaria(SisUrinarioMamaria sisUrinarioMamaria) {
        this.sisUrinarioMamaria = sisUrinarioMamaria;
    }
    //-----------------------------------------------------------------
}
