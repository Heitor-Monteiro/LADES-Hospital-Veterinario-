package com.lades.sihv.model;
// Generated 27/02/2017 17:04:52 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Consulta generated by hbm2java
 */
@Entity
@Table(name="consulta"
    ,catalog="bd_sihv"
)
public class Consulta  implements java.io.Serializable {


     private Integer pkConsulta;
     private Animais animais;
     private User user;
     private Date dataConsulta;
     private String sistemasAfetados;
     private String laudo;
     private String laudoConfirme;
     private BigDecimal valorConsulta;
     private Set<SisDigestorio> sisDigestorios = new HashSet<SisDigestorio>(0);
     private Set<SisUrinarioMamaria> sisUrinarioMamarias = new HashSet<SisUrinarioMamaria>(0);
     private Set<ExameImage> exameImages = new HashSet<ExameImage>(0);
     private Set<SisMuscEsque> sisMuscEsques = new HashSet<SisMuscEsque>(0);
     private Set<ExameFisico> exameFisicos = new HashSet<ExameFisico>(0);
     private Set<SisRespCardio> sisRespCardios = new HashSet<SisRespCardio>(0);
     private Set<SisTegumentar> sisTegumentars = new HashSet<SisTegumentar>(0);
     private Set<Anamnese> anamneses = new HashSet<Anamnese>(0);
     private Set<SisOftalmico> sisOftalmicos = new HashSet<SisOftalmico>(0);
     private Set<SisNeurologico> sisNeurologicos = new HashSet<SisNeurologico>(0);

    public Consulta() {
    }

	
    public Consulta(Animais animais, User user, Date dataConsulta, BigDecimal valorConsulta) {
        this.animais = animais;
        this.user = user;
        this.dataConsulta = dataConsulta;
        this.valorConsulta = valorConsulta;
    }
    public Consulta(Animais animais, User user, Date dataConsulta, String sistemasAfetados, String laudo, String laudoConfirme, BigDecimal valorConsulta, Set<SisDigestorio> sisDigestorios, Set<SisUrinarioMamaria> sisUrinarioMamarias, Set<ExameImage> exameImages, Set<SisMuscEsque> sisMuscEsques, Set<ExameFisico> exameFisicos, Set<SisRespCardio> sisRespCardios, Set<SisTegumentar> sisTegumentars, Set<Anamnese> anamneses, Set<SisOftalmico> sisOftalmicos, Set<SisNeurologico> sisNeurologicos) {
       this.animais = animais;
       this.user = user;
       this.dataConsulta = dataConsulta;
       this.sistemasAfetados = sistemasAfetados;
       this.laudo = laudo;
       this.laudoConfirme = laudoConfirme;
       this.valorConsulta = valorConsulta;
       this.sisDigestorios = sisDigestorios;
       this.sisUrinarioMamarias = sisUrinarioMamarias;
       this.exameImages = exameImages;
       this.sisMuscEsques = sisMuscEsques;
       this.exameFisicos = exameFisicos;
       this.sisRespCardios = sisRespCardios;
       this.sisTegumentars = sisTegumentars;
       this.anamneses = anamneses;
       this.sisOftalmicos = sisOftalmicos;
       this.sisNeurologicos = sisNeurologicos;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="PK_consulta", unique=true, nullable=false)
    public Integer getPkConsulta() {
        return this.pkConsulta;
    }
    
    public void setPkConsulta(Integer pkConsulta) {
        this.pkConsulta = pkConsulta;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns( { 
        @JoinColumn(name="animais_FK_animal", referencedColumnName="PK_animal", nullable=false), 
        @JoinColumn(name="animais_cliente_FK_cliente", referencedColumnName="cliente_FK_cliente", nullable=false), 
        @JoinColumn(name="animais_cliente_FK_pessoa", referencedColumnName="cliente_FK_pessoa", nullable=false) } )
    public Animais getAnimais() {
        return this.animais;
    }
    
    public void setAnimais(Animais animais) {
        this.animais = animais;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns( { 
        @JoinColumn(name="user_PK_user", referencedColumnName="PK_user", nullable=false), 
        @JoinColumn(name="user_FK_pessoa", referencedColumnName="FK_pessoa", nullable=false) } )
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="dataConsulta", nullable=false, length=19)
    public Date getDataConsulta() {
        return this.dataConsulta;
    }
    
    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    
    @Column(name="sistemasAfetados", length=500)
    public String getSistemasAfetados() {
        return this.sistemasAfetados;
    }
    
    public void setSistemasAfetados(String sistemasAfetados) {
        this.sistemasAfetados = sistemasAfetados;
    }

    
    @Column(name="laudo", length=500)
    public String getLaudo() {
        return this.laudo;
    }
    
    public void setLaudo(String laudo) {
        this.laudo = laudo;
    }

    
    @Column(name="laudoConfirme", length=7)
    public String getLaudoConfirme() {
        return this.laudoConfirme;
    }
    
    public void setLaudoConfirme(String laudoConfirme) {
        this.laudoConfirme = laudoConfirme;
    }

    
    @Column(name="valorConsulta", nullable=false, precision=6)
    public BigDecimal getValorConsulta() {
        return this.valorConsulta;
    }
    
    public void setValorConsulta(BigDecimal valorConsulta) {
        this.valorConsulta = valorConsulta;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="consulta")
    public Set<SisDigestorio> getSisDigestorios() {
        return this.sisDigestorios;
    }
    
    public void setSisDigestorios(Set<SisDigestorio> sisDigestorios) {
        this.sisDigestorios = sisDigestorios;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="consulta")
    public Set<SisUrinarioMamaria> getSisUrinarioMamarias() {
        return this.sisUrinarioMamarias;
    }
    
    public void setSisUrinarioMamarias(Set<SisUrinarioMamaria> sisUrinarioMamarias) {
        this.sisUrinarioMamarias = sisUrinarioMamarias;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="consulta")
    public Set<ExameImage> getExameImages() {
        return this.exameImages;
    }
    
    public void setExameImages(Set<ExameImage> exameImages) {
        this.exameImages = exameImages;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="consulta")
    public Set<SisMuscEsque> getSisMuscEsques() {
        return this.sisMuscEsques;
    }
    
    public void setSisMuscEsques(Set<SisMuscEsque> sisMuscEsques) {
        this.sisMuscEsques = sisMuscEsques;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="consulta")
    public Set<ExameFisico> getExameFisicos() {
        return this.exameFisicos;
    }
    
    public void setExameFisicos(Set<ExameFisico> exameFisicos) {
        this.exameFisicos = exameFisicos;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="consulta")
    public Set<SisRespCardio> getSisRespCardios() {
        return this.sisRespCardios;
    }
    
    public void setSisRespCardios(Set<SisRespCardio> sisRespCardios) {
        this.sisRespCardios = sisRespCardios;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="consulta")
    public Set<SisTegumentar> getSisTegumentars() {
        return this.sisTegumentars;
    }
    
    public void setSisTegumentars(Set<SisTegumentar> sisTegumentars) {
        this.sisTegumentars = sisTegumentars;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="consulta")
    public Set<Anamnese> getAnamneses() {
        return this.anamneses;
    }
    
    public void setAnamneses(Set<Anamnese> anamneses) {
        this.anamneses = anamneses;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="consulta")
    public Set<SisOftalmico> getSisOftalmicos() {
        return this.sisOftalmicos;
    }
    
    public void setSisOftalmicos(Set<SisOftalmico> sisOftalmicos) {
        this.sisOftalmicos = sisOftalmicos;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="consulta")
    public Set<SisNeurologico> getSisNeurologicos() {
        return this.sisNeurologicos;
    }
    
    public void setSisNeurologicos(Set<SisNeurologico> sisNeurologicos) {
        this.sisNeurologicos = sisNeurologicos;
    }




}


