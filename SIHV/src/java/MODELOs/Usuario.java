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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author heitor
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByPKusuario", query = "SELECT u FROM Usuario u WHERE u.pKusuario = :pKusuario"),
    @NamedQuery(name = "Usuario.findByAdmSenha", query = "SELECT u FROM Usuario u WHERE u.admSenha = :admSenha"),
    @NamedQuery(name = "Usuario.findByAdmLogin", query = "SELECT u FROM Usuario u WHERE u.admLogin = :admLogin")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_usuario")
    private Integer pKusuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "adm_senha")
    private String admSenha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "adm_login")
    private String admLogin;
    @JoinColumn(name = "pessoa_CPF", referencedColumnName = "CPF")
    @ManyToOne(optional = false)
    private Pessoa pessoaCPF;

    public Usuario() {
    }

    public Usuario(Integer pKusuario) {
        this.pKusuario = pKusuario;
    }

    public Usuario(Integer pKusuario, String admSenha, String admLogin) {
        this.pKusuario = pKusuario;
        this.admSenha = admSenha;
        this.admLogin = admLogin;
    }

    public Integer getPKusuario() {
        return pKusuario;
    }

    public void setPKusuario(Integer pKusuario) {
        this.pKusuario = pKusuario;
    }

    public String getAdmSenha() {
        return admSenha;
    }

    public void setAdmSenha(String admSenha) {
        this.admSenha = admSenha;
    }

    public String getAdmLogin() {
        return admLogin;
    }

    public void setAdmLogin(String admLogin) {
        this.admLogin = admLogin;
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
        hash += (pKusuario != null ? pKusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.pKusuario == null && other.pKusuario != null) || (this.pKusuario != null && !this.pKusuario.equals(other.pKusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MODELOs.Usuario[ pKusuario=" + pKusuario + " ]";
    }
    
}
