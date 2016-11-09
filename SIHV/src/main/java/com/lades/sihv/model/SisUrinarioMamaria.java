package com.lades.sihv.model;
// Generated 09/11/2016 10:58:37 by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SisUrinarioMamaria generated by hbm2java
 */
@Entity
@Table(name="sisUrinarioMamaria"
    ,catalog="bd_sihv"
)
public class SisUrinarioMamaria  implements java.io.Serializable {


     private SisUrinarioMamariaId id;
     private Consulta consulta;
     private String ingestHidrica;
     private String ingestHidricaEvolu;
     private String estadoUrina;
     private String urina;
     private String urinaAspecto;
     private String urinaEvolu;
     private Date ultimoCio;
     private Date ultimoParto;
     private String secreVagiPeni;
     private String secreVagiPeniTipo;
     private String secreVagiPeniEvolu;
     private String castrado;
     private String prenhe;
     private String sistemaAfetado;

    public SisUrinarioMamaria() {
    }

	
    public SisUrinarioMamaria(SisUrinarioMamariaId id, Consulta consulta, String ingestHidrica, String estadoUrina, String urina, Date ultimoCio, Date ultimoParto, String secreVagiPeni, String castrado, String prenhe, String sistemaAfetado) {
        this.id = id;
        this.consulta = consulta;
        this.ingestHidrica = ingestHidrica;
        this.estadoUrina = estadoUrina;
        this.urina = urina;
        this.ultimoCio = ultimoCio;
        this.ultimoParto = ultimoParto;
        this.secreVagiPeni = secreVagiPeni;
        this.castrado = castrado;
        this.prenhe = prenhe;
        this.sistemaAfetado = sistemaAfetado;
    }
    public SisUrinarioMamaria(SisUrinarioMamariaId id, Consulta consulta, String ingestHidrica, String ingestHidricaEvolu, String estadoUrina, String urina, String urinaAspecto, String urinaEvolu, Date ultimoCio, Date ultimoParto, String secreVagiPeni, String secreVagiPeniTipo, String secreVagiPeniEvolu, String castrado, String prenhe, String sistemaAfetado) {
       this.id = id;
       this.consulta = consulta;
       this.ingestHidrica = ingestHidrica;
       this.ingestHidricaEvolu = ingestHidricaEvolu;
       this.estadoUrina = estadoUrina;
       this.urina = urina;
       this.urinaAspecto = urinaAspecto;
       this.urinaEvolu = urinaEvolu;
       this.ultimoCio = ultimoCio;
       this.ultimoParto = ultimoParto;
       this.secreVagiPeni = secreVagiPeni;
       this.secreVagiPeniTipo = secreVagiPeniTipo;
       this.secreVagiPeniEvolu = secreVagiPeniEvolu;
       this.castrado = castrado;
       this.prenhe = prenhe;
       this.sistemaAfetado = sistemaAfetado;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="pkSisUrinarioMamaria", column=@Column(name="PK_sisUrinarioMamaria", nullable=false) ), 
        @AttributeOverride(name="consultaFkConsulta", column=@Column(name="consulta_FK_consulta", nullable=false) ) } )
    public SisUrinarioMamariaId getId() {
        return this.id;
    }
    
    public void setId(SisUrinarioMamariaId id) {
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

    
    @Column(name="ingestHidrica", nullable=false, length=10)
    public String getIngestHidrica() {
        return this.ingestHidrica;
    }
    
    public void setIngestHidrica(String ingestHidrica) {
        this.ingestHidrica = ingestHidrica;
    }

    
    @Column(name="ingestHidricaEvolu", length=200)
    public String getIngestHidricaEvolu() {
        return this.ingestHidricaEvolu;
    }
    
    public void setIngestHidricaEvolu(String ingestHidricaEvolu) {
        this.ingestHidricaEvolu = ingestHidricaEvolu;
    }

    
    @Column(name="estadoUrina", nullable=false, length=8)
    public String getEstadoUrina() {
        return this.estadoUrina;
    }
    
    public void setEstadoUrina(String estadoUrina) {
        this.estadoUrina = estadoUrina;
    }

    
    @Column(name="urina", nullable=false, length=13)
    public String getUrina() {
        return this.urina;
    }
    
    public void setUrina(String urina) {
        this.urina = urina;
    }

    
    @Column(name="urinaAspecto", length=200)
    public String getUrinaAspecto() {
        return this.urinaAspecto;
    }
    
    public void setUrinaAspecto(String urinaAspecto) {
        this.urinaAspecto = urinaAspecto;
    }

    
    @Column(name="urinaEvolu", length=200)
    public String getUrinaEvolu() {
        return this.urinaEvolu;
    }
    
    public void setUrinaEvolu(String urinaEvolu) {
        this.urinaEvolu = urinaEvolu;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="ultimoCio", nullable=false, length=10)
    public Date getUltimoCio() {
        return this.ultimoCio;
    }
    
    public void setUltimoCio(Date ultimoCio) {
        this.ultimoCio = ultimoCio;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="ultimoParto", nullable=false, length=10)
    public Date getUltimoParto() {
        return this.ultimoParto;
    }
    
    public void setUltimoParto(Date ultimoParto) {
        this.ultimoParto = ultimoParto;
    }

    
    @Column(name="secreVagiPeni", nullable=false, length=3)
    public String getSecreVagiPeni() {
        return this.secreVagiPeni;
    }
    
    public void setSecreVagiPeni(String secreVagiPeni) {
        this.secreVagiPeni = secreVagiPeni;
    }

    
    @Column(name="secreVagiPeniTipo", length=50)
    public String getSecreVagiPeniTipo() {
        return this.secreVagiPeniTipo;
    }
    
    public void setSecreVagiPeniTipo(String secreVagiPeniTipo) {
        this.secreVagiPeniTipo = secreVagiPeniTipo;
    }

    
    @Column(name="secreVagiPeniEvolu", length=200)
    public String getSecreVagiPeniEvolu() {
        return this.secreVagiPeniEvolu;
    }
    
    public void setSecreVagiPeniEvolu(String secreVagiPeniEvolu) {
        this.secreVagiPeniEvolu = secreVagiPeniEvolu;
    }

    
    @Column(name="castrado", nullable=false, length=3)
    public String getCastrado() {
        return this.castrado;
    }
    
    public void setCastrado(String castrado) {
        this.castrado = castrado;
    }

    
    @Column(name="prenhe", nullable=false, length=3)
    public String getPrenhe() {
        return this.prenhe;
    }
    
    public void setPrenhe(String prenhe) {
        this.prenhe = prenhe;
    }

    
    @Column(name="sistemaAfetado", nullable=false, length=7)
    public String getSistemaAfetado() {
        return this.sistemaAfetado;
    }
    
    public void setSistemaAfetado(String sistemaAfetado) {
        this.sistemaAfetado = sistemaAfetado;
    }




}


