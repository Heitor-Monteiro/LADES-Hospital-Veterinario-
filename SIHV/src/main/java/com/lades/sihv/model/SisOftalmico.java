package com.lades.sihv.model;
// Generated 15/06/2018 17:09:53 by Hibernate Tools 4.3.1


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
 * SisOftalmico generated by hbm2java
 */
@Entity
@Table(name="sisOftalmico"
    ,catalog="bd_sihv"
)
public class SisOftalmico  implements java.io.Serializable {


     private SisOftalmicoId id;
     private Consulta consulta;
     private String olhosPupila;
     private String secreOcular;
     private String secreOcularUniBi;
     private String secreOculaTipo;
     private String secreOculaEvolu;
     private String blefaroespasmo;
     private String blefaroComenta;
     private String exoftalmia;
     private String exoftalComenta;
     private String sistemaAfetado;

    public SisOftalmico() {
    }

	
    public SisOftalmico(SisOftalmicoId id, Consulta consulta, String olhosPupila, String secreOcular, String blefaroespasmo, String exoftalmia, String sistemaAfetado) {
        this.id = id;
        this.consulta = consulta;
        this.olhosPupila = olhosPupila;
        this.secreOcular = secreOcular;
        this.blefaroespasmo = blefaroespasmo;
        this.exoftalmia = exoftalmia;
        this.sistemaAfetado = sistemaAfetado;
    }
    public SisOftalmico(SisOftalmicoId id, Consulta consulta, String olhosPupila, String secreOcular, String secreOcularUniBi, String secreOculaTipo, String secreOculaEvolu, String blefaroespasmo, String blefaroComenta, String exoftalmia, String exoftalComenta, String sistemaAfetado) {
       this.id = id;
       this.consulta = consulta;
       this.olhosPupila = olhosPupila;
       this.secreOcular = secreOcular;
       this.secreOcularUniBi = secreOcularUniBi;
       this.secreOculaTipo = secreOculaTipo;
       this.secreOculaEvolu = secreOculaEvolu;
       this.blefaroespasmo = blefaroespasmo;
       this.blefaroComenta = blefaroComenta;
       this.exoftalmia = exoftalmia;
       this.exoftalComenta = exoftalComenta;
       this.sistemaAfetado = sistemaAfetado;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="pkSisOftalmico", column=@Column(name="PK_sisOftalmico", nullable=false) ), 
        @AttributeOverride(name="consultaFkConsulta", column=@Column(name="consulta_FK_consulta", nullable=false) ) } )
    public SisOftalmicoId getId() {
        return this.id;
    }
    
    public void setId(SisOftalmicoId id) {
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

    
    @Column(name="olhosPupila", nullable=false, length=52)
    public String getOlhosPupila() {
        return this.olhosPupila;
    }
    
    public void setOlhosPupila(String olhosPupila) {
        this.olhosPupila = olhosPupila;
    }

    
    @Column(name="secreOcular", nullable=false, length=7)
    public String getSecreOcular() {
        return this.secreOcular;
    }
    
    public void setSecreOcular(String secreOcular) {
        this.secreOcular = secreOcular;
    }

    
    @Column(name="secreOcularUniBi", length=57)
    public String getSecreOcularUniBi() {
        return this.secreOcularUniBi;
    }
    
    public void setSecreOcularUniBi(String secreOcularUniBi) {
        this.secreOcularUniBi = secreOcularUniBi;
    }

    
    @Column(name="secreOculaTipo", length=50)
    public String getSecreOculaTipo() {
        return this.secreOculaTipo;
    }
    
    public void setSecreOculaTipo(String secreOculaTipo) {
        this.secreOculaTipo = secreOculaTipo;
    }

    
    @Column(name="secreOculaEvolu", length=200)
    public String getSecreOculaEvolu() {
        return this.secreOculaEvolu;
    }
    
    public void setSecreOculaEvolu(String secreOculaEvolu) {
        this.secreOculaEvolu = secreOculaEvolu;
    }

    
    @Column(name="blefaroespasmo", nullable=false, length=7)
    public String getBlefaroespasmo() {
        return this.blefaroespasmo;
    }
    
    public void setBlefaroespasmo(String blefaroespasmo) {
        this.blefaroespasmo = blefaroespasmo;
    }

    
    @Column(name="blefaroComenta", length=50)
    public String getBlefaroComenta() {
        return this.blefaroComenta;
    }
    
    public void setBlefaroComenta(String blefaroComenta) {
        this.blefaroComenta = blefaroComenta;
    }

    
    @Column(name="exoftalmia", nullable=false, length=7)
    public String getExoftalmia() {
        return this.exoftalmia;
    }
    
    public void setExoftalmia(String exoftalmia) {
        this.exoftalmia = exoftalmia;
    }

    
    @Column(name="exoftalComenta", length=50)
    public String getExoftalComenta() {
        return this.exoftalComenta;
    }
    
    public void setExoftalComenta(String exoftalComenta) {
        this.exoftalComenta = exoftalComenta;
    }

    
    @Column(name="sistemaAfetado", nullable=false, length=7)
    public String getSistemaAfetado() {
        return this.sistemaAfetado;
    }
    
    public void setSistemaAfetado(String sistemaAfetado) {
        this.sistemaAfetado = sistemaAfetado;
    }




}


