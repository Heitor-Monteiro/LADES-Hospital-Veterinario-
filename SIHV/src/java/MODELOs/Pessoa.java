/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELOs;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author heitor
 */
@Entity
@Table(name = "pessoa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pessoa.findAll", query = "SELECT p FROM Pessoa p"),
    @NamedQuery(name = "Pessoa.findByCpf", query = "SELECT p FROM Pessoa p WHERE p.cpf = :cpf"),
    @NamedQuery(name = "Pessoa.findByRg", query = "SELECT p FROM Pessoa p WHERE p.rg = :rg"),
    @NamedQuery(name = "Pessoa.findByNome", query = "SELECT p FROM Pessoa p WHERE p.nome = :nome"),
    @NamedQuery(name = "Pessoa.findBySexo", query = "SELECT p FROM Pessoa p WHERE p.sexo = :sexo"),
    @NamedQuery(name = "Pessoa.findByLogra", query = "SELECT p FROM Pessoa p WHERE p.logra = :logra"),
    @NamedQuery(name = "Pessoa.findByBairro", query = "SELECT p FROM Pessoa p WHERE p.bairro = :bairro"),
    @NamedQuery(name = "Pessoa.findByCidade", query = "SELECT p FROM Pessoa p WHERE p.cidade = :cidade"),
    @NamedQuery(name = "Pessoa.findByCep", query = "SELECT p FROM Pessoa p WHERE p.cep = :cep"),
    @NamedQuery(name = "Pessoa.findByUf", query = "SELECT p FROM Pessoa p WHERE p.uf = :uf"),
    @NamedQuery(name = "Pessoa.findByExclusaoLogica", query = "SELECT p FROM Pessoa p WHERE p.exclusaoLogica = :exclusaoLogica")})
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CPF")
    private Long cpf;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RG")
    private int rg;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOME")
    private String nome;
    @Size(max = 3)
    @Column(name = "SEXO")
    private String sexo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "LOGRA")
    private String logra;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "BAIRRO")
    private String bairro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "CIDADE")
    private String cidade;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CEP")
    private int cep;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "UF")
    private String uf;
    @Column(name = "exclusaoLogica")
    private Boolean exclusaoLogica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoaCPF")
    private List<Cliente> clienteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoaCPF")
    private List<Telefone> telefoneList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoaCPF")
    private List<Usuario> usuarioList;

    public Pessoa() {
    }

    public Pessoa(Long cpf) {
        this.cpf = cpf;
    }

    public Pessoa(Long cpf, int rg, String nome, String logra, String bairro, String cidade, int cep, String uf) {
        this.cpf = cpf;
        this.rg = rg;
        this.nome = nome;
        this.logra = logra;
        this.bairro = bairro;
        this.cidade = cidade;
        this.cep = cep;
        this.uf = uf;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public int getRg() {
        return rg;
    }

    public void setRg(int rg) {
        this.rg = rg;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getLogra() {
        return logra;
    }

    public void setLogra(String logra) {
        this.logra = logra;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Boolean getExclusaoLogica() {
        return exclusaoLogica;
    }

    public void setExclusaoLogica(Boolean exclusaoLogica) {
        this.exclusaoLogica = exclusaoLogica;
    }

    @XmlTransient
    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    @XmlTransient
    public List<Telefone> getTelefoneList() {
        return telefoneList;
    }

    public void setTelefoneList(List<Telefone> telefoneList) {
        this.telefoneList = telefoneList;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cpf != null ? cpf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pessoa)) {
            return false;
        }
        Pessoa other = (Pessoa) object;
        if ((this.cpf == null && other.cpf != null) || (this.cpf != null && !this.cpf.equals(other.cpf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MODELOs.Pessoa[ cpf=" + cpf + " ]";
    }
    
}
