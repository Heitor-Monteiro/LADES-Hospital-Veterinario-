package com.lades.sihv.model;
// Generated 09/11/2016 10:58:37 by Hibernate Tools 4.3.1


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
 * SisTegumentar generated by hbm2java
 */
@Entity
@Table(name="sisTegumentar"
    ,catalog="bd_sihv"
)
public class SisTegumentar  implements java.io.Serializable {


     private SisTegumentarId id;
     private Consulta consulta;
     private String evoluLesao;
     private String localiLesao;
     private String pruridoCutaneo;
     private String pruridoCutaneoEvolu;
     private String pruridoOtolog;
     private String pruridoOtologEvolu;
     private String secreOtolog;
     private String secreOtologEvolu;
     private String frequeBanhos;
     private String produUtilBanho;
     private String sistemaAfetado;

    public SisTegumentar() {
    }

	
    public SisTegumentar(SisTegumentarId id, Consulta consulta, String evoluLesao, String localiLesao, String pruridoCutaneo, String pruridoOtolog, String secreOtolog, String frequeBanhos, String sistemaAfetado) {
        this.id = id;
        this.consulta = consulta;
        this.evoluLesao = evoluLesao;
        this.localiLesao = localiLesao;
        this.pruridoCutaneo = pruridoCutaneo;
        this.pruridoOtolog = pruridoOtolog;
        this.secreOtolog = secreOtolog;
        this.frequeBanhos = frequeBanhos;
        this.sistemaAfetado = sistemaAfetado;
    }
    public SisTegumentar(SisTegumentarId id, Consulta consulta, String evoluLesao, String localiLesao, String pruridoCutaneo, String pruridoCutaneoEvolu, String pruridoOtolog, String pruridoOtologEvolu, String secreOtolog, String secreOtologEvolu, String frequeBanhos, String produUtilBanho, String sistemaAfetado) {
       this.id = id;
       this.consulta = consulta;
       this.evoluLesao = evoluLesao;
       this.localiLesao = localiLesao;
       this.pruridoCutaneo = pruridoCutaneo;
       this.pruridoCutaneoEvolu = pruridoCutaneoEvolu;
       this.pruridoOtolog = pruridoOtolog;
       this.pruridoOtologEvolu = pruridoOtologEvolu;
       this.secreOtolog = secreOtolog;
       this.secreOtologEvolu = secreOtologEvolu;
       this.frequeBanhos = frequeBanhos;
       this.produUtilBanho = produUtilBanho;
       this.sistemaAfetado = sistemaAfetado;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="pkSisTegumentar", column=@Column(name="PK_sisTegumentar", nullable=false) ), 
        @AttributeOverride(name="consultaFkConsulta", column=@Column(name="consulta_FK_consulta", nullable=false) ) } )
    public SisTegumentarId getId() {
        return this.id;
    }
    
    public void setId(SisTegumentarId id) {
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

    
    @Column(name="evoluLesao", nullable=false, length=200)
    public String getEvoluLesao() {
        return this.evoluLesao;
    }
    
    public void setEvoluLesao(String evoluLesao) {
        this.evoluLesao = evoluLesao;
    }

    
    @Column(name="localiLesao", nullable=false, length=200)
    public String getLocaliLesao() {
        return this.localiLesao;
    }
    
    public void setLocaliLesao(String localiLesao) {
        this.localiLesao = localiLesao;
    }

    
    @Column(name="pruridoCutaneo", nullable=false, length=3)
    public String getPruridoCutaneo() {
        return this.pruridoCutaneo;
    }
    
    public void setPruridoCutaneo(String pruridoCutaneo) {
        this.pruridoCutaneo = pruridoCutaneo;
    }

    
    @Column(name="pruridoCutaneoEvolu", length=50)
    public String getPruridoCutaneoEvolu() {
        return this.pruridoCutaneoEvolu;
    }
    
    public void setPruridoCutaneoEvolu(String pruridoCutaneoEvolu) {
        this.pruridoCutaneoEvolu = pruridoCutaneoEvolu;
    }

    
    @Column(name="pruridoOtolog", nullable=false, length=3)
    public String getPruridoOtolog() {
        return this.pruridoOtolog;
    }
    
    public void setPruridoOtolog(String pruridoOtolog) {
        this.pruridoOtolog = pruridoOtolog;
    }

    
    @Column(name="pruridoOtologEvolu", length=50)
    public String getPruridoOtologEvolu() {
        return this.pruridoOtologEvolu;
    }
    
    public void setPruridoOtologEvolu(String pruridoOtologEvolu) {
        this.pruridoOtologEvolu = pruridoOtologEvolu;
    }

    
    @Column(name="secreOtolog", nullable=false, length=3)
    public String getSecreOtolog() {
        return this.secreOtolog;
    }
    
    public void setSecreOtolog(String secreOtolog) {
        this.secreOtolog = secreOtolog;
    }

    
    @Column(name="secreOtologEvolu", length=50)
    public String getSecreOtologEvolu() {
        return this.secreOtologEvolu;
    }
    
    public void setSecreOtologEvolu(String secreOtologEvolu) {
        this.secreOtologEvolu = secreOtologEvolu;
    }

    
    @Column(name="frequeBanhos", nullable=false, length=50)
    public String getFrequeBanhos() {
        return this.frequeBanhos;
    }
    
    public void setFrequeBanhos(String frequeBanhos) {
        this.frequeBanhos = frequeBanhos;
    }

    
    @Column(name="produUtilBanho", length=50)
    public String getProduUtilBanho() {
        return this.produUtilBanho;
    }
    
    public void setProduUtilBanho(String produUtilBanho) {
        this.produUtilBanho = produUtilBanho;
    }

    
    @Column(name="sistemaAfetado", nullable=false, length=7)
    public String getSistemaAfetado() {
        return this.sistemaAfetado;
    }
    
    public void setSistemaAfetado(String sistemaAfetado) {
        this.sistemaAfetado = sistemaAfetado;
    }




}


