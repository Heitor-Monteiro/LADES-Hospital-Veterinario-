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
 * SisDigestorio generated by hbm2java
 */
@Entity
@Table(name="sisDigestorio"
    ,catalog="bd_sihv"
)
public class SisDigestorio  implements java.io.Serializable {


     private SisDigestorioId id;
     private VetConsultation vetConsultation;
     private String estaSeAlimentando;
     private String descriNaoSeAlimeta;
     private String vomito;
     private String aspectoVomito;
     private String evoluVomito;
     private String regurgitacao;
     private String evoluRegurgitacao;
     private String diarreia;
     private String aspectDiarreia;
     private String evoluDiarreia;
     private String disquesiaTenesmo;
     private String evoluDisquesiaTenesmo;
     private String sistemaAfetado;

    public SisDigestorio() {
    }

	
    public SisDigestorio(SisDigestorioId id, VetConsultation vetConsultation, String estaSeAlimentando, String vomito, String regurgitacao, String diarreia, String disquesiaTenesmo, String sistemaAfetado) {
        this.id = id;
        this.vetConsultation = vetConsultation;
        this.estaSeAlimentando = estaSeAlimentando;
        this.vomito = vomito;
        this.regurgitacao = regurgitacao;
        this.diarreia = diarreia;
        this.disquesiaTenesmo = disquesiaTenesmo;
        this.sistemaAfetado = sistemaAfetado;
    }
    public SisDigestorio(SisDigestorioId id, VetConsultation vetConsultation, String estaSeAlimentando, String descriNaoSeAlimeta, String vomito, String aspectoVomito, String evoluVomito, String regurgitacao, String evoluRegurgitacao, String diarreia, String aspectDiarreia, String evoluDiarreia, String disquesiaTenesmo, String evoluDisquesiaTenesmo, String sistemaAfetado) {
       this.id = id;
       this.vetConsultation = vetConsultation;
       this.estaSeAlimentando = estaSeAlimentando;
       this.descriNaoSeAlimeta = descriNaoSeAlimeta;
       this.vomito = vomito;
       this.aspectoVomito = aspectoVomito;
       this.evoluVomito = evoluVomito;
       this.regurgitacao = regurgitacao;
       this.evoluRegurgitacao = evoluRegurgitacao;
       this.diarreia = diarreia;
       this.aspectDiarreia = aspectDiarreia;
       this.evoluDiarreia = evoluDiarreia;
       this.disquesiaTenesmo = disquesiaTenesmo;
       this.evoluDisquesiaTenesmo = evoluDisquesiaTenesmo;
       this.sistemaAfetado = sistemaAfetado;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="pkSisDigestorio", column=@Column(name="PK_sisDigestorio", nullable=false) ), 
        @AttributeOverride(name="vetConsultationPkVetConsultation", column=@Column(name="vetConsultation_PK_vetConsultation", nullable=false) ) } )
    public SisDigestorioId getId() {
        return this.id;
    }
    
    public void setId(SisDigestorioId id) {
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

    
    @Column(name="estaSeAlimentando", nullable=false, length=7)
    public String getEstaSeAlimentando() {
        return this.estaSeAlimentando;
    }
    
    public void setEstaSeAlimentando(String estaSeAlimentando) {
        this.estaSeAlimentando = estaSeAlimentando;
    }

    
    @Column(name="descriNaoSeAlimeta", length=254)
    public String getDescriNaoSeAlimeta() {
        return this.descriNaoSeAlimeta;
    }
    
    public void setDescriNaoSeAlimeta(String descriNaoSeAlimeta) {
        this.descriNaoSeAlimeta = descriNaoSeAlimeta;
    }

    
    @Column(name="vomito", nullable=false, length=7)
    public String getVomito() {
        return this.vomito;
    }
    
    public void setVomito(String vomito) {
        this.vomito = vomito;
    }

    
    @Column(name="aspectoVomito", length=100)
    public String getAspectoVomito() {
        return this.aspectoVomito;
    }
    
    public void setAspectoVomito(String aspectoVomito) {
        this.aspectoVomito = aspectoVomito;
    }

    
    @Column(name="evoluVomito", length=254)
    public String getEvoluVomito() {
        return this.evoluVomito;
    }
    
    public void setEvoluVomito(String evoluVomito) {
        this.evoluVomito = evoluVomito;
    }

    
    @Column(name="regurgitacao", nullable=false, length=7)
    public String getRegurgitacao() {
        return this.regurgitacao;
    }
    
    public void setRegurgitacao(String regurgitacao) {
        this.regurgitacao = regurgitacao;
    }

    
    @Column(name="evoluRegurgitacao", length=254)
    public String getEvoluRegurgitacao() {
        return this.evoluRegurgitacao;
    }
    
    public void setEvoluRegurgitacao(String evoluRegurgitacao) {
        this.evoluRegurgitacao = evoluRegurgitacao;
    }

    
    @Column(name="diarreia", nullable=false, length=7)
    public String getDiarreia() {
        return this.diarreia;
    }
    
    public void setDiarreia(String diarreia) {
        this.diarreia = diarreia;
    }

    
    @Column(name="aspectDiarreia", length=100)
    public String getAspectDiarreia() {
        return this.aspectDiarreia;
    }
    
    public void setAspectDiarreia(String aspectDiarreia) {
        this.aspectDiarreia = aspectDiarreia;
    }

    
    @Column(name="evoluDiarreia", length=254)
    public String getEvoluDiarreia() {
        return this.evoluDiarreia;
    }
    
    public void setEvoluDiarreia(String evoluDiarreia) {
        this.evoluDiarreia = evoluDiarreia;
    }

    
    @Column(name="disquesiaTenesmo", nullable=false, length=7)
    public String getDisquesiaTenesmo() {
        return this.disquesiaTenesmo;
    }
    
    public void setDisquesiaTenesmo(String disquesiaTenesmo) {
        this.disquesiaTenesmo = disquesiaTenesmo;
    }

    
    @Column(name="evoluDisquesiaTenesmo", length=254)
    public String getEvoluDisquesiaTenesmo() {
        return this.evoluDisquesiaTenesmo;
    }
    
    public void setEvoluDisquesiaTenesmo(String evoluDisquesiaTenesmo) {
        this.evoluDisquesiaTenesmo = evoluDisquesiaTenesmo;
    }

    
    @Column(name="sistemaAfetado", nullable=false, length=7)
    public String getSistemaAfetado() {
        return this.sistemaAfetado;
    }
    
    public void setSistemaAfetado(String sistemaAfetado) {
        this.sistemaAfetado = sistemaAfetado;
    }




}


