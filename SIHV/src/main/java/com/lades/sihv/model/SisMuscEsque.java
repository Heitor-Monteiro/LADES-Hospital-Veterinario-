package com.lades.sihv.model;
// Generated 04/11/2016 14:35:29 by Hibernate Tools 4.3.1


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
 * SisMuscEsque generated by hbm2java
 */
@Entity
@Table(name="Sis_MuscEsque"
    ,catalog="BD_SIHV"
)
public class SisMuscEsque  implements java.io.Serializable {


     private SisMuscEsqueId id;
     private Consulta consulta;
     private String claudicacao;
     private String claudicacaoEvolu;
     private String fraturas;
     private String fraturasEvolu;
     private String atrofMusc;
     private String atrofMuscEvolu;
     private String sistemaAfetado;

    public SisMuscEsque() {
    }

	
    public SisMuscEsque(SisMuscEsqueId id, Consulta consulta, String claudicacao, String fraturas, String atrofMusc, String sistemaAfetado) {
        this.id = id;
        this.consulta = consulta;
        this.claudicacao = claudicacao;
        this.fraturas = fraturas;
        this.atrofMusc = atrofMusc;
        this.sistemaAfetado = sistemaAfetado;
    }
    public SisMuscEsque(SisMuscEsqueId id, Consulta consulta, String claudicacao, String claudicacaoEvolu, String fraturas, String fraturasEvolu, String atrofMusc, String atrofMuscEvolu, String sistemaAfetado) {
       this.id = id;
       this.consulta = consulta;
       this.claudicacao = claudicacao;
       this.claudicacaoEvolu = claudicacaoEvolu;
       this.fraturas = fraturas;
       this.fraturasEvolu = fraturasEvolu;
       this.atrofMusc = atrofMusc;
       this.atrofMuscEvolu = atrofMuscEvolu;
       this.sistemaAfetado = sistemaAfetado;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="pkSisMuscEsque", column=@Column(name="PK_Sis_MuscEsque", nullable=false) ), 
        @AttributeOverride(name="consultaPkConsulta", column=@Column(name="consulta_PK_consulta", nullable=false) ) } )
    public SisMuscEsqueId getId() {
        return this.id;
    }
    
    public void setId(SisMuscEsqueId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="consulta_PK_consulta", nullable=false, insertable=false, updatable=false)
    public Consulta getConsulta() {
        return this.consulta;
    }
    
    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    
    @Column(name="Claudicacao", nullable=false, length=3)
    public String getClaudicacao() {
        return this.claudicacao;
    }
    
    public void setClaudicacao(String claudicacao) {
        this.claudicacao = claudicacao;
    }

    
    @Column(name="ClaudicacaoEvolu", length=50)
    public String getClaudicacaoEvolu() {
        return this.claudicacaoEvolu;
    }
    
    public void setClaudicacaoEvolu(String claudicacaoEvolu) {
        this.claudicacaoEvolu = claudicacaoEvolu;
    }

    
    @Column(name="Fraturas", nullable=false, length=3)
    public String getFraturas() {
        return this.fraturas;
    }
    
    public void setFraturas(String fraturas) {
        this.fraturas = fraturas;
    }

    
    @Column(name="FraturasEvolu", length=50)
    public String getFraturasEvolu() {
        return this.fraturasEvolu;
    }
    
    public void setFraturasEvolu(String fraturasEvolu) {
        this.fraturasEvolu = fraturasEvolu;
    }

    
    @Column(name="AtrofMusc", nullable=false, length=3)
    public String getAtrofMusc() {
        return this.atrofMusc;
    }
    
    public void setAtrofMusc(String atrofMusc) {
        this.atrofMusc = atrofMusc;
    }

    
    @Column(name="AtrofMuscEvolu", length=50)
    public String getAtrofMuscEvolu() {
        return this.atrofMuscEvolu;
    }
    
    public void setAtrofMuscEvolu(String atrofMuscEvolu) {
        this.atrofMuscEvolu = atrofMuscEvolu;
    }

    
    @Column(name="SistemaAfetado", nullable=false, length=7)
    public String getSistemaAfetado() {
        return this.sistemaAfetado;
    }
    
    public void setSistemaAfetado(String sistemaAfetado) {
        this.sistemaAfetado = sistemaAfetado;
    }




}


