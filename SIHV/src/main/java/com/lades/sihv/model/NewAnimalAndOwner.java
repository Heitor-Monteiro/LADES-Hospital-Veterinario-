package com.lades.sihv.model;
// Generated 25/09/2018 14:47:05 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * NewAnimalAndOwner generated by hbm2java
 */
@Entity
@Table(name="newAnimalAndOwner"
    ,catalog="bd_sihv"
)
public class NewAnimalAndOwner  implements java.io.Serializable {


     private int schedulingPkSchedule;
     private Scheduling scheduling;
     private String proprietaryName;
     private String animalName;
     private String proprietaryPhone1;
     private String proprietaryPhone2;
     private String proprietaryPhone3;

    public NewAnimalAndOwner() {
    }

	
    public NewAnimalAndOwner(Scheduling scheduling, String proprietaryName, String animalName, String proprietaryPhone1) {
        this.scheduling = scheduling;
        this.proprietaryName = proprietaryName;
        this.animalName = animalName;
        this.proprietaryPhone1 = proprietaryPhone1;
    }
    public NewAnimalAndOwner(Scheduling scheduling, String proprietaryName, String animalName, String proprietaryPhone1, String proprietaryPhone2, String proprietaryPhone3) {
       this.scheduling = scheduling;
       this.proprietaryName = proprietaryName;
       this.animalName = animalName;
       this.proprietaryPhone1 = proprietaryPhone1;
       this.proprietaryPhone2 = proprietaryPhone2;
       this.proprietaryPhone3 = proprietaryPhone3;
    }
   
     @GenericGenerator(name="generator", strategy="foreign", parameters=@Parameter(name="property", value="scheduling"))@Id @GeneratedValue(generator="generator")

    
    @Column(name="scheduling_Pk_schedule", unique=true, nullable=false)
    public int getSchedulingPkSchedule() {
        return this.schedulingPkSchedule;
    }
    
    public void setSchedulingPkSchedule(int schedulingPkSchedule) {
        this.schedulingPkSchedule = schedulingPkSchedule;
    }

@OneToOne(fetch=FetchType.LAZY)@PrimaryKeyJoinColumn
    public Scheduling getScheduling() {
        return this.scheduling;
    }
    
    public void setScheduling(Scheduling scheduling) {
        this.scheduling = scheduling;
    }

    
    @Column(name="proprietaryName", nullable=false, length=100)
    public String getProprietaryName() {
        return this.proprietaryName;
    }
    
    public void setProprietaryName(String proprietaryName) {
        this.proprietaryName = proprietaryName;
    }

    
    @Column(name="animalName", nullable=false, length=70)
    public String getAnimalName() {
        return this.animalName;
    }
    
    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    
    @Column(name="proprietaryPhone1", nullable=false, length=15)
    public String getProprietaryPhone1() {
        return this.proprietaryPhone1;
    }
    
    public void setProprietaryPhone1(String proprietaryPhone1) {
        this.proprietaryPhone1 = proprietaryPhone1;
    }

    
    @Column(name="proprietaryPhone2", length=15)
    public String getProprietaryPhone2() {
        return this.proprietaryPhone2;
    }
    
    public void setProprietaryPhone2(String proprietaryPhone2) {
        this.proprietaryPhone2 = proprietaryPhone2;
    }

    
    @Column(name="proprietaryPhone3", length=15)
    public String getProprietaryPhone3() {
        return this.proprietaryPhone3;
    }
    
    public void setProprietaryPhone3(String proprietaryPhone3) {
        this.proprietaryPhone3 = proprietaryPhone3;
    }




}


