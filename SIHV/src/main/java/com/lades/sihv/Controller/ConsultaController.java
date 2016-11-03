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
import com.lades.sihv.model.Adm;
import com.lades.sihv.model.Anamnese;
import com.lades.sihv.model.AnamneseId;
import com.lades.sihv.model.SisDigestorio;
import com.lades.sihv.model.SisDigestorioId;
import com.lades.sihv.model.SisRespCardio;
import com.lades.sihv.model.SisRespCardioId;
import com.lades.sihv.model.SisUrinarioMamaria;
import com.lades.sihv.model.SisUrinarioMamariaId;
//import com.lades.sihv.model.SisTegumentar;
//import com.lades.sihv.model.SisTegumentarId;
//import com.lades.sihv.model.SisNeurologico;
//import com.lades.sihv.model.SisNeurologicoId;
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
    
    private Adm medicoVET;
    
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
        
//        sisUrinarioMamaria =  new SisUrinarioMamaria();
//        sisUrinarioMamariaId = new SisUrinarioMamariaId();
    }
    
    
    
    
    
    /*Método utilizado para salvar uma nova consulta.
    Obs.: a consulta será salva caso tenha confirmação 
    do medico veterinário usando o método 
    confirmaMEDICO()*/
    public void adicionarNovaConsulta(Animais animais){
        try {
            
            
            novaConsulta.setDataConsulta(data);
            novaConsulta.setSistemasAfetados("Teste de sistemas afetados");
            novaConsulta.setAnimais(animais);
            confirmaMEDICO();
            if (medicoCOFIRMADO == true) {
                novaConsulta.setAdm(medicoVET);
                daoGenerico.save(novaConsulta);
                
                anamneseId.setConsultaPkConsulta(novaConsulta.getPkConsulta());
                anamnese.setId(anamneseId); 
                anamnese.setVacinacao(concatenaSTRING(vacinacao));
                anamnese.setQualEctoparasitas(concatenaSTRING(qualEctoparasitas));
                anamnese.setAcessoRua(concatenaSTRING(acessoArua));
                
                sisDigestorioId.setConsultaPkConsulta(novaConsulta.getPkConsulta());
                sisDigestorio.setId(sisDigestorioId);
                
                sisRespCardioId.setConsultaPkConsulta(novaConsulta.getPkConsulta());
                sisRespCardio.setId(sisRespCardioId);
                
                
                daoGenerico.save(anamnese);
                daoGenerico.save(sisDigestorio);
                daoGenerico.save(sisRespCardio);
                
                message.info("Cosulta efetuada.","Consulta realizada com sucesso.");
            }
        } catch (Exception e) {
            message.warn("Erro ao efetuar cadastro!", "Verifique os dados e tente novamente!");
        }
    } 
    
    
    
    
    /*O método é chamado para atestar que um medico
    veterinário ira fazer a consulta, ou seja, 
    uma nova consulta só será concretizada 
    se houver o aval do mesmo*/
    private void confirmaMEDICO(){
        String pwd = Security.getMD5(confirmeSENHA);
        List<Adm> admLista;
        admLista =  daoGenerico.list("select a from Adm a where a.admSenha='"+pwd+"' and a.crmvMatricula='"+confirmeCRMV+"'");
        
        if (admLista.size() > 0) {
            medicoVET = admLista.get(0);
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
    
    
    //------GETs & SETs SisUrinarioMamaria
    public SisUrinarioMamaria getSisUrinarioMamaria() {
        return sisUrinarioMamaria;
    }

    public void setSisUrinarioMamaria(SisUrinarioMamaria sisUrinarioMamaria) {
        this.sisUrinarioMamaria = sisUrinarioMamaria;
    }
    //-----------------------------------------------------------------
}
