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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author heitor
 */
@Entity
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByPKcliente", query = "SELECT c FROM Cliente c WHERE c.pKcliente = :pKcliente")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_cliente")
    private Integer pKcliente;
    @JoinColumn(name = "pessoa_CPF", referencedColumnName = "CPF")
    @ManyToOne(optional = false)
    private Pessoa pessoaCPF;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientepessoaCPF")
    private List<Animais> animaisList;

    public Cliente() {
    }

    public Cliente(Integer pKcliente) {
        this.pKcliente = pKcliente;
    }

    public Integer getPKcliente() {
        return pKcliente;
    }

    public void setPKcliente(Integer pKcliente) {
        this.pKcliente = pKcliente;
    }

    public Pessoa getPessoaCPF() {
        return pessoaCPF;
    }

    public void setPessoaCPF(Pessoa pessoaCPF) {
        this.pessoaCPF = pessoaCPF;
    }

    @XmlTransient
    public List<Animais> getAnimaisList() {
        return animaisList;
    }

    public void setAnimaisList(List<Animais> animaisList) {
        this.animaisList = animaisList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pKcliente != null ? pKcliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.pKcliente == null && other.pKcliente != null) || (this.pKcliente != null && !this.pKcliente.equals(other.pKcliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MODELOs.Cliente[ pKcliente=" + pKcliente + " ]";
    }
    
}
