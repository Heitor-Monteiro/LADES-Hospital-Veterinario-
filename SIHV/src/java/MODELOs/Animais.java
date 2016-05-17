/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELOs;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author heitor
 */
@Entity
@Table(name = "animais")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Animais.findAll", query = "SELECT a FROM Animais a"),
    @NamedQuery(name = "Animais.findByPKanimal", query = "SELECT a FROM Animais a WHERE a.pKanimal = :pKanimal"),
    @NamedQuery(name = "Animais.findByDia", query = "SELECT a FROM Animais a WHERE a.dia = :dia"),
    @NamedQuery(name = "Animais.findByMes", query = "SELECT a FROM Animais a WHERE a.mes = :mes"),
    @NamedQuery(name = "Animais.findByAno", query = "SELECT a FROM Animais a WHERE a.ano = :ano"),
    @NamedQuery(name = "Animais.findByHora", query = "SELECT a FROM Animais a WHERE a.hora = :hora"),
    @NamedQuery(name = "Animais.findByEspecie", query = "SELECT a FROM Animais a WHERE a.especie = :especie"),
    @NamedQuery(name = "Animais.findByNome", query = "SELECT a FROM Animais a WHERE a.nome = :nome"),
    @NamedQuery(name = "Animais.findByRa\u00e7a", query = "SELECT a FROM Animais a WHERE a.ra\u00e7a = :ra\u00e7a"),
    @NamedQuery(name = "Animais.findByPelagem", query = "SELECT a FROM Animais a WHERE a.pelagem = :pelagem"),
    @NamedQuery(name = "Animais.findBySexo", query = "SELECT a FROM Animais a WHERE a.sexo = :sexo"),
    @NamedQuery(name = "Animais.findByIdade", query = "SELECT a FROM Animais a WHERE a.idade = :idade"),
    @NamedQuery(name = "Animais.findByPeso", query = "SELECT a FROM Animais a WHERE a.peso = :peso")})
public class Animais implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_animal")
    private Integer pKanimal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIA")
    private int dia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MES")
    private int mes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ANO")
    private int ano;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HORA")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "ESPECIE")
    private String especie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOME")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "RA\u00c7A")
    private String raça;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PELAGEM")
    private String pelagem;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "SEXO")
    private String sexo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDADE")
    private int idade;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PESO")
    private double peso;
    @JoinColumn(name = "cliente_pessoa_CPF", referencedColumnName = "pessoa_CPF")
    @ManyToOne(optional = false)
    private Cliente clientepessoaCPF;

    public Animais() {
    }

    public Animais(Integer pKanimal) {
        this.pKanimal = pKanimal;
    }

    public Animais(Integer pKanimal, int dia, int mes, int ano, Date hora, String especie, String nome, String raça, String pelagem, String sexo, int idade, double peso) {
        this.pKanimal = pKanimal;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.hora = hora;
        this.especie = especie;
        this.nome = nome;
        this.raça = raça;
        this.pelagem = pelagem;
        this.sexo = sexo;
        this.idade = idade;
        this.peso = peso;
    }

    public Integer getPKanimal() {
        return pKanimal;
    }

    public void setPKanimal(Integer pKanimal) {
        this.pKanimal = pKanimal;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaça() {
        return raça;
    }

    public void setRaça(String raça) {
        this.raça = raça;
    }

    public String getPelagem() {
        return pelagem;
    }

    public void setPelagem(String pelagem) {
        this.pelagem = pelagem;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Cliente getClientepessoaCPF() {
        return clientepessoaCPF;
    }

    public void setClientepessoaCPF(Cliente clientepessoaCPF) {
        this.clientepessoaCPF = clientepessoaCPF;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pKanimal != null ? pKanimal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Animais)) {
            return false;
        }
        Animais other = (Animais) object;
        if ((this.pKanimal == null && other.pKanimal != null) || (this.pKanimal != null && !this.pKanimal.equals(other.pKanimal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MODELOs.Animais[ pKanimal=" + pKanimal + " ]";
    }
    
}
