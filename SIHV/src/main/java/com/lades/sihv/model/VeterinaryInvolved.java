package com.lades.sihv.model;
// Generated 04/10/2018 16:09:37 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * VeterinaryInvolved generated by hbm2java
 */
@Entity
@Table(name="veterinaryInvolved"
    ,catalog="bd_sihv"
)
public class VeterinaryInvolved  implements java.io.Serializable {


     private Integer pkVeterinaryInvolved;

    public VeterinaryInvolved() {
    }

   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="PK_veterinaryInvolved", unique=true, nullable=false)
    public Integer getPkVeterinaryInvolved() {
        return this.pkVeterinaryInvolved;
    }
    
    public void setPkVeterinaryInvolved(Integer pkVeterinaryInvolved) {
        this.pkVeterinaryInvolved = pkVeterinaryInvolved;
    }




}

