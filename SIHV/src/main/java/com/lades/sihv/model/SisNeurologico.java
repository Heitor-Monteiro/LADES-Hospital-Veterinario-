package com.lades.sihv.model;
// Generated 15/06/2018 11:17:09 by Hibernate Tools 4.3.1


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * SisNeurologico generated by hbm2java
 */
@Entity
@Table(name="sisNeurologico"
    ,catalog="bd_sihv"
)
public class SisNeurologico  implements java.io.Serializable {


     private SisNeurologicoId id;
     private Consulta consulta;
     private String consciencia;
     private String comportamento;
     private String ataxia;
     private String ataxiaTipo;
     private String ataxiaEvolu;
     private String paralisia;
     private String paralisiaEspFla;
     private String paralisiaTipo;
     private String paralisiaEvolu;
     private String convulsao;
     private String convulsaoTipo;
     private String convulsaoEvolu;
     private String audicao;
     private String sistemaAfetado;

    public SisNeurologico() {
    }

	
    public SisNeurologico(SisNeurologicoId id, Consulta consulta, String consciencia, String comportamento, String ataxia, String paralisia, String convulsao, String audicao, String sistemaAfetado) {
        this.id = id;
        this.consulta = consulta;
        this.consciencia = consciencia;
        this.comportamento = comportamento;
        this.ataxia = ataxia;
        this.paralisia = paralisia;
        this.convulsao = convulsao;
        this.audicao = audicao;
        this.sistemaAfetado = sistemaAfetado;
    }
    public SisNeurologico(SisNeurologicoId id, Consulta consulta, String consciencia, String comportamento, String ataxia, String ataxiaTipo, String ataxiaEvolu, String paralisia, String paralisiaEspFla, String paralisiaTipo, String paralisiaEvolu, String convulsao, String convulsaoTipo, String convulsaoEvolu, String audicao, String sistemaAfetado) {
       this.id = id;
       this.consulta = consulta;
       this.consciencia = consciencia;
       this.comportamento = comportamento;
       this.ataxia = ataxia;
       this.ataxiaTipo = ataxiaTipo;
       this.ataxiaEvolu = ataxiaEvolu;
       this.paralisia = paralisia;
       this.paralisiaEspFla = paralisiaEspFla;
       this.paralisiaTipo = paralisiaTipo;
       this.paralisiaEvolu = paralisiaEvolu;
       this.convulsao = convulsao;
       this.convulsaoTipo = convulsaoTipo;
       this.convulsaoEvolu = convulsaoEvolu;
       this.audicao = audicao;
       this.sistemaAfetado = sistemaAfetado;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="pkSisNeurologico", column=@Column(name="PK_sisNeurologico", nullable=false) ), 
        @AttributeOverride(name="consultaFkConsulta", column=@Column(name="consulta_FK_consulta", nullable=false) ) } )
    public SisNeurologicoId getId() {
        return this.id;
    }
    
    public void setId(SisNeurologicoId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="consulta_FK_consulta", nullable=false, insertable=false, updatable=false)
    public Consulta getConsulta() {
        return this.consulta;
    }
    
    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    
    @Column(name="consciencia", nullable=false, length=30)
    public String getConsciencia() {
        return this.consciencia;
    }
    
    public void setConsciencia(String consciencia) {
        this.consciencia = consciencia;
    }

    
    @Column(name="comportamento", nullable=false, length=29)
    public String getComportamento() {
        return this.comportamento;
    }
    
    public void setComportamento(String comportamento) {
        this.comportamento = comportamento;
    }

    
    @Column(name="ataxia", nullable=false, length=7)
    public String getAtaxia() {
        return this.ataxia;
    }
    
    public void setAtaxia(String ataxia) {
        this.ataxia = ataxia;
    }

    
    @Column(name="ataxiaTipo", length=50)
    public String getAtaxiaTipo() {
        return this.ataxiaTipo;
    }
    
    public void setAtaxiaTipo(String ataxiaTipo) {
        this.ataxiaTipo = ataxiaTipo;
    }

    
    @Column(name="ataxiaEvolu", length=200)
    public String getAtaxiaEvolu() {
        return this.ataxiaEvolu;
    }
    
    public void setAtaxiaEvolu(String ataxiaEvolu) {
        this.ataxiaEvolu = ataxiaEvolu;
    }

    
    @Column(name="paralisia", nullable=false, length=7)
    public String getParalisia() {
        return this.paralisia;
    }
    
    public void setParalisia(String paralisia) {
        this.paralisia = paralisia;
    }

    
    @Column(name="paralisiaEspFla", length=24)
    public String getParalisiaEspFla() {
        return this.paralisiaEspFla;
    }
    
    public void setParalisiaEspFla(String paralisiaEspFla) {
        this.paralisiaEspFla = paralisiaEspFla;
    }

    
    @Column(name="paralisiaTipo", length=50)
    public String getParalisiaTipo() {
        return this.paralisiaTipo;
    }
    
    public void setParalisiaTipo(String paralisiaTipo) {
        this.paralisiaTipo = paralisiaTipo;
    }

    
    @Column(name="paralisiaEvolu", length=200)
    public String getParalisiaEvolu() {
        return this.paralisiaEvolu;
    }
    
    public void setParalisiaEvolu(String paralisiaEvolu) {
        this.paralisiaEvolu = paralisiaEvolu;
    }

    
    @Column(name="convulsao", nullable=false, length=7)
    public String getConvulsao() {
        return this.convulsao;
    }
    
    public void setConvulsao(String convulsao) {
        this.convulsao = convulsao;
    }

    
    @Column(name="convulsaoTipo", length=50)
    public String getConvulsaoTipo() {
        return this.convulsaoTipo;
    }
    
    public void setConvulsaoTipo(String convulsaoTipo) {
        this.convulsaoTipo = convulsaoTipo;
    }

    
    @Column(name="convulsaoEvolu", length=200)
    public String getConvulsaoEvolu() {
        return this.convulsaoEvolu;
    }
    
    public void setConvulsaoEvolu(String convulsaoEvolu) {
        this.convulsaoEvolu = convulsaoEvolu;
    }

    
    @Column(name="audicao", nullable=false, length=21)
    public String getAudicao() {
        return this.audicao;
    }
    
    public void setAudicao(String audicao) {
        this.audicao = audicao;
    }

    
    @Column(name="sistemaAfetado", nullable=false, length=7)
    public String getSistemaAfetado() {
        return this.sistemaAfetado;
    }
    
    public void setSistemaAfetado(String sistemaAfetado) {
        this.sistemaAfetado = sistemaAfetado;
    }




}


