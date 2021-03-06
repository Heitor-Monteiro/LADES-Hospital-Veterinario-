package com.lades.sihv.model;
// Generated 25/09/2018 14:47:05 by Hibernate Tools 4.3.1


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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * FederationUnity generated by hbm2java
 */
@Entity
@Table(name="federationUnity"
    ,catalog="bd_sihv"
)
public class FederationUnity  implements java.io.Serializable {


     private Integer pkFederationUnity;
     private Nation nation;
     private String uf;
     private String fullName;
     private Date registrationDate;
     private Set cities = new HashSet(0);

    public FederationUnity() {
    }

	
    public FederationUnity(Nation nation, String uf, String fullName, Date registrationDate) {
        this.nation = nation;
        this.uf = uf;
        this.fullName = fullName;
        this.registrationDate = registrationDate;
    }
    public FederationUnity(Nation nation, String uf, String fullName, Date registrationDate, Set cities) {
       this.nation = nation;
       this.uf = uf;
       this.fullName = fullName;
       this.registrationDate = registrationDate;
       this.cities = cities;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="PK_federationUnity", unique=true, nullable=false)
    public Integer getPkFederationUnity() {
        return this.pkFederationUnity;
    }
    
    public void setPkFederationUnity(Integer pkFederationUnity) {
        this.pkFederationUnity = pkFederationUnity;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="nation_PK_nation", nullable=false)
    public Nation getNation() {
        return this.nation;
    }
    
    public void setNation(Nation nation) {
        this.nation = nation;
    }

    
    @Column(name="uf", nullable=false, length=2)
    public String getUf() {
        return this.uf;
    }
    
    public void setUf(String uf) {
        this.uf = uf;
    }

    
    @Column(name="fullName", nullable=false, length=100)
    public String getFullName() {
        return this.fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="registrationDate", nullable=false, length=19)
    public Date getRegistrationDate() {
        return this.registrationDate;
    }
    
    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="federationUnity")
    public Set getCities() {
        return this.cities;
    }
    
    public void setCities(Set cities) {
        this.cities = cities;
    }




}


