package MODELO;
// Generated 27/10/2016 10:35:02 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Adm generated by hbm2java
 */
@Entity
@Table(name="adm"
    ,catalog="BD_SIHV"
    , uniqueConstraints = @UniqueConstraint(columnNames="CRMV_MATRICULA") 
)
public class Adm  implements java.io.Serializable {


     private AdmId id;
     private Pessoa pessoa;
     private String admSenha;
     private String admLogin;
     private String tipoUser;
     private String crmvMatricula;
     private Set<Consulta> consultas = new HashSet<Consulta>(0);

    public Adm() {
    }

	
    public Adm(AdmId id, Pessoa pessoa, String admSenha, String admLogin, String tipoUser, String crmvMatricula) {
        this.id = id;
        this.pessoa = pessoa;
        this.admSenha = admSenha;
        this.admLogin = admLogin;
        this.tipoUser = tipoUser;
        this.crmvMatricula = crmvMatricula;
    }
    public Adm(AdmId id, Pessoa pessoa, String admSenha, String admLogin, String tipoUser, String crmvMatricula, Set<Consulta> consultas) {
       this.id = id;
       this.pessoa = pessoa;
       this.admSenha = admSenha;
       this.admLogin = admLogin;
       this.tipoUser = tipoUser;
       this.crmvMatricula = crmvMatricula;
       this.consultas = consultas;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="pkAdm", column=@Column(name="PK_adm", nullable=false) ), 
        @AttributeOverride(name="fkPessoa", column=@Column(name="FK_pessoa", nullable=false) ) } )
    public AdmId getId() {
        return this.id;
    }
    
    public void setId(AdmId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FK_pessoa", nullable=false, insertable=false, updatable=false)
    public Pessoa getPessoa() {
        return this.pessoa;
    }
    
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    
    @Column(name="adm_senha", nullable=false, length=100)
    public String getAdmSenha() {
        return this.admSenha;
    }
    
    public void setAdmSenha(String admSenha) {
        this.admSenha = admSenha;
    }

    
    @Column(name="adm_login", nullable=false, length=100)
    public String getAdmLogin() {
        return this.admLogin;
    }
    
    public void setAdmLogin(String admLogin) {
        this.admLogin = admLogin;
    }

    
    @Column(name="TIPO_USER", nullable=false, length=20)
    public String getTipoUser() {
        return this.tipoUser;
    }
    
    public void setTipoUser(String tipoUser) {
        this.tipoUser = tipoUser;
    }

    
    @Column(name="CRMV_MATRICULA", unique=true, nullable=false, length=50)
    public String getCrmvMatricula() {
        return this.crmvMatricula;
    }
    
    public void setCrmvMatricula(String crmvMatricula) {
        this.crmvMatricula = crmvMatricula;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="adm")
    public Set<Consulta> getConsultas() {
        return this.consultas;
    }
    
    public void setConsultas(Set<Consulta> consultas) {
        this.consultas = consultas;
    }




}


