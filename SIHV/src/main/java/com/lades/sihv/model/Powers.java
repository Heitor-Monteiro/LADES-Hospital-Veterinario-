package com.lades.sihv.model;
// Generated 25/09/2018 14:47:05 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Powers generated by hbm2java
 */
@Entity
@Table(name="powers"
    ,catalog="bd_sihv"
)
public class Powers  implements java.io.Serializable {


     private Integer pkPower;
     private String namePower;
     private String descriptionPower;
     private Set powersHasUserses = new HashSet(0);

    public Powers() {
    }

	
    public Powers(String namePower, String descriptionPower) {
        this.namePower = namePower;
        this.descriptionPower = descriptionPower;
    }
    public Powers(String namePower, String descriptionPower, Set powersHasUserses) {
       this.namePower = namePower;
       this.descriptionPower = descriptionPower;
       this.powersHasUserses = powersHasUserses;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="PK_power", unique=true, nullable=false)
    public Integer getPkPower() {
        return this.pkPower;
    }
    
    public void setPkPower(Integer pkPower) {
        this.pkPower = pkPower;
    }

    
    @Column(name="namePower", nullable=false, length=100)
    public String getNamePower() {
        return this.namePower;
    }
    
    public void setNamePower(String namePower) {
        this.namePower = namePower;
    }

    
    @Column(name="descriptionPower", nullable=false, length=254)
    public String getDescriptionPower() {
        return this.descriptionPower;
    }
    
    public void setDescriptionPower(String descriptionPower) {
        this.descriptionPower = descriptionPower;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="powers")
    public Set getPowersHasUserses() {
        return this.powersHasUserses;
    }
    
    public void setPowersHasUserses(Set powersHasUserses) {
        this.powersHasUserses = powersHasUserses;
    }




}


