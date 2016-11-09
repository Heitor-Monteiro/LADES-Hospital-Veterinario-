package com.lades.sihv.model;
// Generated 09/11/2016 10:58:37 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Animais generated by hbm2java
 */
@Entity
@Table(name="animais"
    ,catalog="bd_sihv"
)
public class Animais  implements java.io.Serializable {


     private AnimaisId id;
     private Cliente cliente;
     private String especie;
     private String nomeAnimal;
     private String raca;
     private String pelagem;
     private String sexoAnimal;
     private int idadeAtual;
     private double peso;
     private Date cadDataHora;
     private Set<Consulta> consultas = new HashSet<Consulta>(0);

    public Animais() {
    }

	
    public Animais(AnimaisId id, Cliente cliente, String especie, String nomeAnimal, String raca, String pelagem, String sexoAnimal, int idadeAtual, double peso, Date cadDataHora) {
        this.id = id;
        this.cliente = cliente;
        this.especie = especie;
        this.nomeAnimal = nomeAnimal;
        this.raca = raca;
        this.pelagem = pelagem;
        this.sexoAnimal = sexoAnimal;
        this.idadeAtual = idadeAtual;
        this.peso = peso;
        this.cadDataHora = cadDataHora;
    }
    public Animais(AnimaisId id, Cliente cliente, String especie, String nomeAnimal, String raca, String pelagem, String sexoAnimal, int idadeAtual, double peso, Date cadDataHora, Set<Consulta> consultas) {
       this.id = id;
       this.cliente = cliente;
       this.especie = especie;
       this.nomeAnimal = nomeAnimal;
       this.raca = raca;
       this.pelagem = pelagem;
       this.sexoAnimal = sexoAnimal;
       this.idadeAtual = idadeAtual;
       this.peso = peso;
       this.cadDataHora = cadDataHora;
       this.consultas = consultas;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="pkAnimal", column=@Column(name="PK_animal", nullable=false) ), 
        @AttributeOverride(name="clienteFkCliente", column=@Column(name="cliente_FK_cliente", nullable=false) ), 
        @AttributeOverride(name="clienteFkPessoa", column=@Column(name="cliente_FK_pessoa", nullable=false) ) } )
    public AnimaisId getId() {
        return this.id;
    }
    
    public void setId(AnimaisId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns( { 
        @JoinColumn(name="cliente_FK_cliente", referencedColumnName="PK_cliente", nullable=false, insertable=false, updatable=false), 
        @JoinColumn(name="cliente_FK_pessoa", referencedColumnName="FK_pessoa", nullable=false, insertable=false, updatable=false) } )
    public Cliente getCliente() {
        return this.cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    
    @Column(name="especie", nullable=false, length=100)
    public String getEspecie() {
        return this.especie;
    }
    
    public void setEspecie(String especie) {
        this.especie = especie;
    }

    
    @Column(name="nomeAnimal", nullable=false, length=70)
    public String getNomeAnimal() {
        return this.nomeAnimal;
    }
    
    public void setNomeAnimal(String nomeAnimal) {
        this.nomeAnimal = nomeAnimal;
    }

    
    @Column(name="raca", nullable=false, length=100)
    public String getRaca() {
        return this.raca;
    }
    
    public void setRaca(String raca) {
        this.raca = raca;
    }

    
    @Column(name="pelagem", nullable=false, length=100)
    public String getPelagem() {
        return this.pelagem;
    }
    
    public void setPelagem(String pelagem) {
        this.pelagem = pelagem;
    }

    
    @Column(name="sexoAnimal", nullable=false, length=3)
    public String getSexoAnimal() {
        return this.sexoAnimal;
    }
    
    public void setSexoAnimal(String sexoAnimal) {
        this.sexoAnimal = sexoAnimal;
    }

    
    @Column(name="idadeAtual", nullable=false)
    public int getIdadeAtual() {
        return this.idadeAtual;
    }
    
    public void setIdadeAtual(int idadeAtual) {
        this.idadeAtual = idadeAtual;
    }

    
    @Column(name="peso", nullable=false, precision=22, scale=0)
    public double getPeso() {
        return this.peso;
    }
    
    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="cadDataHora", nullable=false, length=19)
    public Date getCadDataHora() {
        return this.cadDataHora;
    }
    
    public void setCadDataHora(Date cadDataHora) {
        this.cadDataHora = cadDataHora;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="animais")
    public Set<Consulta> getConsultas() {
        return this.consultas;
    }
    
    public void setConsultas(Set<Consulta> consultas) {
        this.consultas = consultas;
    }




}


