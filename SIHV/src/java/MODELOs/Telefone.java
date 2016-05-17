/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELOs;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author heitor
 */
@Entity
@Table(name = "telefone")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Telefone.findAll", query = "SELECT t FROM Telefone t"),
    @NamedQuery(name = "Telefone.findByPKtelefone", query = "SELECT t FROM Telefone t WHERE t.pKtelefone = :pKtelefone"),
    @NamedQuery(name = "Telefone.findByNumero", query = "SELECT t FROM Telefone t WHERE t.numero = :numero")})
public class Telefone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_telefone")
    private Integer pKtelefone;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero")
    private long numero;
    @JoinColumn(name = "pessoa_CPF", referencedColumnName = "CPF")
    @ManyToOne(optional = false)
    private Pessoa pessoaCPF;

    public Telefone() {
    }

    public Telefone(Integer pKtelefone) {
        this.pKtelefone = pKtelefone;
    }

    public Telefone(Integer pKtelefone, long numero) {
        this.pKtelefone = pKtelefone;
        this.numero = numero;
    }

    public Integer getPKtelefone() {
        return pKtelefone;
    }

    public void setPKtelefone(Integer pKtelefone) {
        this.pKtelefone = pKtelefone;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public Pessoa getPessoaCPF() {
        return pessoaCPF;
    }

    public void setPessoaCPF(Pessoa pessoaCPF) {
        this.pessoaCPF = pessoaCPF;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pKtelefone != null ? pKtelefone.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Telefone)) {
            return false;
        }
        Telefone other = (Telefone) object;
        if ((this.pKtelefone == null && other.pKtelefone != null) || (this.pKtelefone != null && !this.pKtelefone.equals(other.pKtelefone))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MODELOs.Telefone[ pKtelefone=" + pKtelefone + " ]";
    }
    
}
