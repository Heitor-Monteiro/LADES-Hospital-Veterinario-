package com.lades.sihv.model;
// Generated 25/09/2018 14:47:05 by Hibernate Tools 4.3.1


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
 * SisUrinarioMamaria generated by hbm2java
 */
@Entity
@Table(name="sisUrinarioMamaria"
    ,catalog="bd_sihv"
)
public class SisUrinarioMamaria  implements java.io.Serializable {


     private SisUrinarioMamariaId id;
     private VetConsultation vetConsultation;
     private String ingestHidrica;
     private String ingestHidricaEvolu;
     private String estadoUrina;
     private String urina;
     private String urinaAspecto;
     private String urinaEvolu;
     private String ultimoCio;
     private String prenhez;
     private String prenhezDescricao;
     private String ultimoParto;
     private String secreVagiPeni;
     private String secreVagiPeniTipo;
     private String secreVagiPeniEvolu;
     private String castrado;
     private String sistemaAfetado;

    public SisUrinarioMamaria() {
    }

	
    public SisUrinarioMamaria(SisUrinarioMamariaId id, VetConsultation vetConsultation, String ingestHidrica, String estadoUrina, String urina, String prenhez, String secreVagiPeni, String castrado, String sistemaAfetado) {
        this.id = id;
        this.vetConsultation = vetConsultation;
        this.ingestHidrica = ingestHidrica;
        this.estadoUrina = estadoUrina;
        this.urina = urina;
        this.prenhez = prenhez;
        this.secreVagiPeni = secreVagiPeni;
        this.castrado = castrado;
        this.sistemaAfetado = sistemaAfetado;
    }
    public SisUrinarioMamaria(SisUrinarioMamariaId id, VetConsultation vetConsultation, String ingestHidrica, String ingestHidricaEvolu, String estadoUrina, String urina, String urinaAspecto, String urinaEvolu, String ultimoCio, String prenhez, String prenhezDescricao, String ultimoParto, String secreVagiPeni, String secreVagiPeniTipo, String secreVagiPeniEvolu, String castrado, String sistemaAfetado) {
       this.id = id;
       this.vetConsultation = vetConsultation;
       this.ingestHidrica = ingestHidrica;
       this.ingestHidricaEvolu = ingestHidricaEvolu;
       this.estadoUrina = estadoUrina;
       this.urina = urina;
       this.urinaAspecto = urinaAspecto;
       this.urinaEvolu = urinaEvolu;
       this.ultimoCio = ultimoCio;
       this.prenhez = prenhez;
       this.prenhezDescricao = prenhezDescricao;
       this.ultimoParto = ultimoParto;
       this.secreVagiPeni = secreVagiPeni;
       this.secreVagiPeniTipo = secreVagiPeniTipo;
       this.secreVagiPeniEvolu = secreVagiPeniEvolu;
       this.castrado = castrado;
       this.sistemaAfetado = sistemaAfetado;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="pkSisUrinarioMamaria", column=@Column(name="PK_sisUrinarioMamaria", nullable=false) ), 
        @AttributeOverride(name="vetConsultationPkVetConsultation", column=@Column(name="vetConsultation_PK_vetConsultation", nullable=false) ) } )
    public SisUrinarioMamariaId getId() {
        return this.id;
    }
    
    public void setId(SisUrinarioMamariaId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="vetConsultation_PK_vetConsultation", nullable=false, insertable=false, updatable=false)
    public VetConsultation getVetConsultation() {
        return this.vetConsultation;
    }
    
    public void setVetConsultation(VetConsultation vetConsultation) {
        this.vetConsultation = vetConsultation;
    }

    
    @Column(name="ingestHidrica", nullable=false, length=28)
    public String getIngestHidrica() {
        return this.ingestHidrica;
    }
    
    public void setIngestHidrica(String ingestHidrica) {
        this.ingestHidrica = ingestHidrica;
    }

    
    @Column(name="ingestHidricaEvolu", length=254)
    public String getIngestHidricaEvolu() {
        return this.ingestHidricaEvolu;
    }
    
    public void setIngestHidricaEvolu(String ingestHidricaEvolu) {
        this.ingestHidricaEvolu = ingestHidricaEvolu;
    }

    
    @Column(name="estadoUrina", nullable=false, length=15)
    public String getEstadoUrina() {
        return this.estadoUrina;
    }
    
    public void setEstadoUrina(String estadoUrina) {
        this.estadoUrina = estadoUrina;
    }

    
    @Column(name="urina", nullable=false, length=53)
    public String getUrina() {
        return this.urina;
    }
    
    public void setUrina(String urina) {
        this.urina = urina;
    }

    
    @Column(name="urinaAspecto", length=254)
    public String getUrinaAspecto() {
        return this.urinaAspecto;
    }
    
    public void setUrinaAspecto(String urinaAspecto) {
        this.urinaAspecto = urinaAspecto;
    }

    
    @Column(name="urinaEvolu", length=254)
    public String getUrinaEvolu() {
        return this.urinaEvolu;
    }
    
    public void setUrinaEvolu(String urinaEvolu) {
        this.urinaEvolu = urinaEvolu;
    }

    
    @Column(name="ultimoCio", length=254)
    public String getUltimoCio() {
        return this.ultimoCio;
    }
    
    public void setUltimoCio(String ultimoCio) {
        this.ultimoCio = ultimoCio;
    }

    
    @Column(name="prenhez", nullable=false, length=66)
    public String getPrenhez() {
        return this.prenhez;
    }
    
    public void setPrenhez(String prenhez) {
        this.prenhez = prenhez;
    }

    
    @Column(name="prenhezDescricao", length=254)
    public String getPrenhezDescricao() {
        return this.prenhezDescricao;
    }
    
    public void setPrenhezDescricao(String prenhezDescricao) {
        this.prenhezDescricao = prenhezDescricao;
    }

    
    @Column(name="ultimoParto", length=254)
    public String getUltimoParto() {
        return this.ultimoParto;
    }
    
    public void setUltimoParto(String ultimoParto) {
        this.ultimoParto = ultimoParto;
    }

    
    @Column(name="secreVagiPeni", nullable=false, length=7)
    public String getSecreVagiPeni() {
        return this.secreVagiPeni;
    }
    
    public void setSecreVagiPeni(String secreVagiPeni) {
        this.secreVagiPeni = secreVagiPeni;
    }

    
    @Column(name="secreVagiPeniTipo", length=100)
    public String getSecreVagiPeniTipo() {
        return this.secreVagiPeniTipo;
    }
    
    public void setSecreVagiPeniTipo(String secreVagiPeniTipo) {
        this.secreVagiPeniTipo = secreVagiPeniTipo;
    }

    
    @Column(name="secreVagiPeniEvolu", length=254)
    public String getSecreVagiPeniEvolu() {
        return this.secreVagiPeniEvolu;
    }
    
    public void setSecreVagiPeniEvolu(String secreVagiPeniEvolu) {
        this.secreVagiPeniEvolu = secreVagiPeniEvolu;
    }

    
    @Column(name="castrado", nullable=false, length=7)
    public String getCastrado() {
        return this.castrado;
    }
    
    public void setCastrado(String castrado) {
        this.castrado = castrado;
    }

    
    @Column(name="sistemaAfetado", nullable=false, length=7)
    public String getSistemaAfetado() {
        return this.sistemaAfetado;
    }
    
    public void setSistemaAfetado(String sistemaAfetado) {
        this.sistemaAfetado = sistemaAfetado;
    }




}


