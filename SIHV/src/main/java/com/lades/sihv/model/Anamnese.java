package com.lades.sihv.model;
// Generated 08/11/2016 12:03:42 by Hibernate Tools 4.3.1


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
 * Anamnese generated by hbm2java
 */
@Entity
@Table(name="anamnese"
    ,catalog="bd_sihv"
)
public class Anamnese  implements java.io.Serializable {


     private AnamneseId id;
     private Consulta consulta;
     private String queixaPrincipal;
     private String jaFoiTratado;
     private String medicacaoDose;
     private String antecMorbido;
     private String histoFamiliar;
     private String alimentacaoCaseira;
     private String descriCaseira;
     private String alimentacaoRacao;
     private String descriRacao;
     private String vacinacao;
     private String sobreVacina;
     private String origemVacina;
     private String vermifugacao;
     private String doseVermifugacao;
     private Date dataVermifugacao;
     private String ectoparasitas;
     private String qualEctoparasitas;
     private String controEctoparasitas;
     private String qualProdutoUtiliza;
     private String acessoRua;
     private String descriHabitat;
     private String contactantes;
     private String descriContactantes;
     private String mesmoProbleContacta;
     private String contatoRoedor;

    public Anamnese() {
    }

	
    public Anamnese(AnamneseId id, Consulta consulta, String queixaPrincipal, String jaFoiTratado, String antecMorbido, String histoFamiliar, String alimentacaoCaseira, String alimentacaoRacao, String vacinacao, String vermifugacao, String ectoparasitas, String acessoRua) {
        this.id = id;
        this.consulta = consulta;
        this.queixaPrincipal = queixaPrincipal;
        this.jaFoiTratado = jaFoiTratado;
        this.antecMorbido = antecMorbido;
        this.histoFamiliar = histoFamiliar;
        this.alimentacaoCaseira = alimentacaoCaseira;
        this.alimentacaoRacao = alimentacaoRacao;
        this.vacinacao = vacinacao;
        this.vermifugacao = vermifugacao;
        this.ectoparasitas = ectoparasitas;
        this.acessoRua = acessoRua;
    }
    public Anamnese(AnamneseId id, Consulta consulta, String queixaPrincipal, String jaFoiTratado, String medicacaoDose, String antecMorbido, String histoFamiliar, String alimentacaoCaseira, String descriCaseira, String alimentacaoRacao, String descriRacao, String vacinacao, String sobreVacina, String origemVacina, String vermifugacao, String doseVermifugacao, Date dataVermifugacao, String ectoparasitas, String qualEctoparasitas, String controEctoparasitas, String qualProdutoUtiliza, String acessoRua, String descriHabitat, String contactantes, String descriContactantes, String mesmoProbleContacta, String contatoRoedor) {
       this.id = id;
       this.consulta = consulta;
       this.queixaPrincipal = queixaPrincipal;
       this.jaFoiTratado = jaFoiTratado;
       this.medicacaoDose = medicacaoDose;
       this.antecMorbido = antecMorbido;
       this.histoFamiliar = histoFamiliar;
       this.alimentacaoCaseira = alimentacaoCaseira;
       this.descriCaseira = descriCaseira;
       this.alimentacaoRacao = alimentacaoRacao;
       this.descriRacao = descriRacao;
       this.vacinacao = vacinacao;
       this.sobreVacina = sobreVacina;
       this.origemVacina = origemVacina;
       this.vermifugacao = vermifugacao;
       this.doseVermifugacao = doseVermifugacao;
       this.dataVermifugacao = dataVermifugacao;
       this.ectoparasitas = ectoparasitas;
       this.qualEctoparasitas = qualEctoparasitas;
       this.controEctoparasitas = controEctoparasitas;
       this.qualProdutoUtiliza = qualProdutoUtiliza;
       this.acessoRua = acessoRua;
       this.descriHabitat = descriHabitat;
       this.contactantes = contactantes;
       this.descriContactantes = descriContactantes;
       this.mesmoProbleContacta = mesmoProbleContacta;
       this.contatoRoedor = contatoRoedor;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="pkAnamnese", column=@Column(name="PK_anamnese", nullable=false) ), 
        @AttributeOverride(name="consultaFkConsulta", column=@Column(name="consulta_FK_consulta", nullable=false) ) } )
    public AnamneseId getId() {
        return this.id;
    }
    
    public void setId(AnamneseId id) {
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

    
    @Column(name="queixaPrincipal", nullable=false, length=300)
    public String getQueixaPrincipal() {
        return this.queixaPrincipal;
    }
    
    public void setQueixaPrincipal(String queixaPrincipal) {
        this.queixaPrincipal = queixaPrincipal;
    }

    
    @Column(name="jaFoiTratado", nullable=false, length=3)
    public String getJaFoiTratado() {
        return this.jaFoiTratado;
    }
    
    public void setJaFoiTratado(String jaFoiTratado) {
        this.jaFoiTratado = jaFoiTratado;
    }

    
    @Column(name="medicacaoDose", length=200)
    public String getMedicacaoDose() {
        return this.medicacaoDose;
    }
    
    public void setMedicacaoDose(String medicacaoDose) {
        this.medicacaoDose = medicacaoDose;
    }

    
    @Column(name="antecMorbido", nullable=false, length=200)
    public String getAntecMorbido() {
        return this.antecMorbido;
    }
    
    public void setAntecMorbido(String antecMorbido) {
        this.antecMorbido = antecMorbido;
    }

    
    @Column(name="histoFamiliar", nullable=false, length=200)
    public String getHistoFamiliar() {
        return this.histoFamiliar;
    }
    
    public void setHistoFamiliar(String histoFamiliar) {
        this.histoFamiliar = histoFamiliar;
    }

    
    @Column(name="alimentacaoCaseira", nullable=false, length=3)
    public String getAlimentacaoCaseira() {
        return this.alimentacaoCaseira;
    }
    
    public void setAlimentacaoCaseira(String alimentacaoCaseira) {
        this.alimentacaoCaseira = alimentacaoCaseira;
    }

    
    @Column(name="descriCaseira", length=200)
    public String getDescriCaseira() {
        return this.descriCaseira;
    }
    
    public void setDescriCaseira(String descriCaseira) {
        this.descriCaseira = descriCaseira;
    }

    
    @Column(name="alimentacaoRacao", nullable=false, length=3)
    public String getAlimentacaoRacao() {
        return this.alimentacaoRacao;
    }
    
    public void setAlimentacaoRacao(String alimentacaoRacao) {
        this.alimentacaoRacao = alimentacaoRacao;
    }

    
    @Column(name="descriRacao", length=200)
    public String getDescriRacao() {
        return this.descriRacao;
    }
    
    public void setDescriRacao(String descriRacao) {
        this.descriRacao = descriRacao;
    }

    
    @Column(name="vacinacao", nullable=false, length=22)
    public String getVacinacao() {
        return this.vacinacao;
    }
    
    public void setVacinacao(String vacinacao) {
        this.vacinacao = vacinacao;
    }

    
    @Column(name="sobreVacina", length=100)
    public String getSobreVacina() {
        return this.sobreVacina;
    }
    
    public void setSobreVacina(String sobreVacina) {
        this.sobreVacina = sobreVacina;
    }

    
    @Column(name="origemVacina", length=18)
    public String getOrigemVacina() {
        return this.origemVacina;
    }
    
    public void setOrigemVacina(String origemVacina) {
        this.origemVacina = origemVacina;
    }

    
    @Column(name="vermifugacao", nullable=false, length=3)
    public String getVermifugacao() {
        return this.vermifugacao;
    }
    
    public void setVermifugacao(String vermifugacao) {
        this.vermifugacao = vermifugacao;
    }

    
    @Column(name="doseVermifugacao", length=50)
    public String getDoseVermifugacao() {
        return this.doseVermifugacao;
    }
    
    public void setDoseVermifugacao(String doseVermifugacao) {
        this.doseVermifugacao = doseVermifugacao;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="dataVermifugacao", length=10)
    public Date getDataVermifugacao() {
        return this.dataVermifugacao;
    }
    
    public void setDataVermifugacao(Date dataVermifugacao) {
        this.dataVermifugacao = dataVermifugacao;
    }

    
    @Column(name="ectoparasitas", nullable=false, length=3)
    public String getEctoparasitas() {
        return this.ectoparasitas;
    }
    
    public void setEctoparasitas(String ectoparasitas) {
        this.ectoparasitas = ectoparasitas;
    }

    
    @Column(name="qualEctoparasitas", length=34)
    public String getQualEctoparasitas() {
        return this.qualEctoparasitas;
    }
    
    public void setQualEctoparasitas(String qualEctoparasitas) {
        this.qualEctoparasitas = qualEctoparasitas;
    }

    
    @Column(name="controEctoparasitas", length=3)
    public String getControEctoparasitas() {
        return this.controEctoparasitas;
    }
    
    public void setControEctoparasitas(String controEctoparasitas) {
        this.controEctoparasitas = controEctoparasitas;
    }

    
    @Column(name="qualProdutoUtiliza", length=50)
    public String getQualProdutoUtiliza() {
        return this.qualProdutoUtiliza;
    }
    
    public void setQualProdutoUtiliza(String qualProdutoUtiliza) {
        this.qualProdutoUtiliza = qualProdutoUtiliza;
    }

    
    @Column(name="acessoRua", nullable=false, length=24)
    public String getAcessoRua() {
        return this.acessoRua;
    }
    
    public void setAcessoRua(String acessoRua) {
        this.acessoRua = acessoRua;
    }

    
    @Column(name="descriHabitat", length=200)
    public String getDescriHabitat() {
        return this.descriHabitat;
    }
    
    public void setDescriHabitat(String descriHabitat) {
        this.descriHabitat = descriHabitat;
    }

    
    @Column(name="contactantes", length=3)
    public String getContactantes() {
        return this.contactantes;
    }
    
    public void setContactantes(String contactantes) {
        this.contactantes = contactantes;
    }

    
    @Column(name="descriContactantes", length=200)
    public String getDescriContactantes() {
        return this.descriContactantes;
    }
    
    public void setDescriContactantes(String descriContactantes) {
        this.descriContactantes = descriContactantes;
    }

    
    @Column(name="mesmoProbleContacta", length=3)
    public String getMesmoProbleContacta() {
        return this.mesmoProbleContacta;
    }
    
    public void setMesmoProbleContacta(String mesmoProbleContacta) {
        this.mesmoProbleContacta = mesmoProbleContacta;
    }

    
    @Column(name="contatoRoedor", length=3)
    public String getContatoRoedor() {
        return this.contatoRoedor;
    }
    
    public void setContatoRoedor(String contatoRoedor) {
        this.contatoRoedor = contatoRoedor;
    }




}

