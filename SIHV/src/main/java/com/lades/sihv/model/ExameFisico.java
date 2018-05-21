package com.lades.sihv.model;
// Generated 21/05/2018 11:38:42 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
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
 * ExameFisico generated by hbm2java
 */
@Entity
@Table(name="exameFisico"
    ,catalog="bd_sihv"
)
public class ExameFisico  implements java.io.Serializable {


     private ExameFisicoId id;
     private Consulta consulta;
     private short fcBpm;
     private short frMpm;
     private BigDecimal termpeReta;
     private short tpc;
     private String estadoNutric;
     private String mucosaOral;
     private String mucosVagPeni;
     private String mucosaOcular;
     private String pulso;
     private String estadoPulso;
     private String hidratacao;
     private String palpaAbdom;
     private String auscuCardiaca;
     private String auscuPulmona;
     private String linfonodos;
     private String pelePelos;
     private String inforAdiciona;
     private String diagDifer;

    public ExameFisico() {
    }

	
    public ExameFisico(ExameFisicoId id, Consulta consulta, short fcBpm, short frMpm, BigDecimal termpeReta, short tpc, String estadoNutric, String mucosaOral, String mucosVagPeni, String mucosaOcular, String pulso, String estadoPulso, String hidratacao, String palpaAbdom, String auscuCardiaca, String auscuPulmona, String linfonodos, String pelePelos) {
        this.id = id;
        this.consulta = consulta;
        this.fcBpm = fcBpm;
        this.frMpm = frMpm;
        this.termpeReta = termpeReta;
        this.tpc = tpc;
        this.estadoNutric = estadoNutric;
        this.mucosaOral = mucosaOral;
        this.mucosVagPeni = mucosVagPeni;
        this.mucosaOcular = mucosaOcular;
        this.pulso = pulso;
        this.estadoPulso = estadoPulso;
        this.hidratacao = hidratacao;
        this.palpaAbdom = palpaAbdom;
        this.auscuCardiaca = auscuCardiaca;
        this.auscuPulmona = auscuPulmona;
        this.linfonodos = linfonodos;
        this.pelePelos = pelePelos;
    }
    public ExameFisico(ExameFisicoId id, Consulta consulta, short fcBpm, short frMpm, BigDecimal termpeReta, short tpc, String estadoNutric, String mucosaOral, String mucosVagPeni, String mucosaOcular, String pulso, String estadoPulso, String hidratacao, String palpaAbdom, String auscuCardiaca, String auscuPulmona, String linfonodos, String pelePelos, String inforAdiciona, String diagDifer) {
       this.id = id;
       this.consulta = consulta;
       this.fcBpm = fcBpm;
       this.frMpm = frMpm;
       this.termpeReta = termpeReta;
       this.tpc = tpc;
       this.estadoNutric = estadoNutric;
       this.mucosaOral = mucosaOral;
       this.mucosVagPeni = mucosVagPeni;
       this.mucosaOcular = mucosaOcular;
       this.pulso = pulso;
       this.estadoPulso = estadoPulso;
       this.hidratacao = hidratacao;
       this.palpaAbdom = palpaAbdom;
       this.auscuCardiaca = auscuCardiaca;
       this.auscuPulmona = auscuPulmona;
       this.linfonodos = linfonodos;
       this.pelePelos = pelePelos;
       this.inforAdiciona = inforAdiciona;
       this.diagDifer = diagDifer;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="pkExameFisico", column=@Column(name="PK_exameFisico", nullable=false) ), 
        @AttributeOverride(name="consultaFkConsulta", column=@Column(name="consulta_FK_consulta", nullable=false) ) } )
    public ExameFisicoId getId() {
        return this.id;
    }
    
    public void setId(ExameFisicoId id) {
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

    
    @Column(name="fcBpm", nullable=false, precision=4, scale=0)
    public short getFcBpm() {
        return this.fcBpm;
    }
    
    public void setFcBpm(short fcBpm) {
        this.fcBpm = fcBpm;
    }

    
    @Column(name="frMpm", nullable=false, precision=4, scale=0)
    public short getFrMpm() {
        return this.frMpm;
    }
    
    public void setFrMpm(short frMpm) {
        this.frMpm = frMpm;
    }

    
    @Column(name="termpeReta", nullable=false, precision=5, scale=1)
    public BigDecimal getTermpeReta() {
        return this.termpeReta;
    }
    
    public void setTermpeReta(BigDecimal termpeReta) {
        this.termpeReta = termpeReta;
    }

    
    @Column(name="tpc", nullable=false, precision=4, scale=0)
    public short getTpc() {
        return this.tpc;
    }
    
    public void setTpc(short tpc) {
        this.tpc = tpc;
    }

    
    @Column(name="estadoNutric", nullable=false, length=31)
    public String getEstadoNutric() {
        return this.estadoNutric;
    }
    
    public void setEstadoNutric(String estadoNutric) {
        this.estadoNutric = estadoNutric;
    }

    
    @Column(name="mucosaOral", nullable=false, length=55)
    public String getMucosaOral() {
        return this.mucosaOral;
    }
    
    public void setMucosaOral(String mucosaOral) {
        this.mucosaOral = mucosaOral;
    }

    
    @Column(name="mucosVagPeni", nullable=false, length=55)
    public String getMucosVagPeni() {
        return this.mucosVagPeni;
    }
    
    public void setMucosVagPeni(String mucosVagPeni) {
        this.mucosVagPeni = mucosVagPeni;
    }

    
    @Column(name="mucosaOcular", nullable=false, length=55)
    public String getMucosaOcular() {
        return this.mucosaOcular;
    }
    
    public void setMucosaOcular(String mucosaOcular) {
        this.mucosaOcular = mucosaOcular;
    }

    
    @Column(name="pulso", nullable=false, length=48)
    public String getPulso() {
        return this.pulso;
    }
    
    public void setPulso(String pulso) {
        this.pulso = pulso;
    }

    
    @Column(name="estadoPulso", nullable=false, length=25)
    public String getEstadoPulso() {
        return this.estadoPulso;
    }
    
    public void setEstadoPulso(String estadoPulso) {
        this.estadoPulso = estadoPulso;
    }

    
    @Column(name="hidratacao", nullable=false, length=96)
    public String getHidratacao() {
        return this.hidratacao;
    }
    
    public void setHidratacao(String hidratacao) {
        this.hidratacao = hidratacao;
    }

    
    @Column(name="palpaAbdom", nullable=false, length=200)
    public String getPalpaAbdom() {
        return this.palpaAbdom;
    }
    
    public void setPalpaAbdom(String palpaAbdom) {
        this.palpaAbdom = palpaAbdom;
    }

    
    @Column(name="auscuCardiaca", nullable=false, length=200)
    public String getAuscuCardiaca() {
        return this.auscuCardiaca;
    }
    
    public void setAuscuCardiaca(String auscuCardiaca) {
        this.auscuCardiaca = auscuCardiaca;
    }

    
    @Column(name="auscuPulmona", nullable=false, length=200)
    public String getAuscuPulmona() {
        return this.auscuPulmona;
    }
    
    public void setAuscuPulmona(String auscuPulmona) {
        this.auscuPulmona = auscuPulmona;
    }

    
    @Column(name="linfonodos", nullable=false, length=200)
    public String getLinfonodos() {
        return this.linfonodos;
    }
    
    public void setLinfonodos(String linfonodos) {
        this.linfonodos = linfonodos;
    }

    
    @Column(name="pelePelos", nullable=false, length=200)
    public String getPelePelos() {
        return this.pelePelos;
    }
    
    public void setPelePelos(String pelePelos) {
        this.pelePelos = pelePelos;
    }

    
    @Column(name="inforAdiciona", length=900)
    public String getInforAdiciona() {
        return this.inforAdiciona;
    }
    
    public void setInforAdiciona(String inforAdiciona) {
        this.inforAdiciona = inforAdiciona;
    }

    
    @Column(name="diagDifer", length=500)
    public String getDiagDifer() {
        return this.diagDifer;
    }
    
    public void setDiagDifer(String diagDifer) {
        this.diagDifer = diagDifer;
    }




}


