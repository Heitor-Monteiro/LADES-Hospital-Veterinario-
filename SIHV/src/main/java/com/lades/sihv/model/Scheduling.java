package com.lades.sihv.model;
// Generated 02/11/2018 14:22:32 by Hibernate Tools 4.3.1


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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Scheduling generated by hbm2java
 */
@Entity
@Table(name="scheduling"
    ,catalog="bd_sihv"
)
public class Scheduling  implements java.io.Serializable {


     private Integer pkSchedule;
     private OwnersHasAnimals ownersHasAnimals;
     private String typeService;
     private String statusService;
     private Date schedulingDate;
     private Set vetConsultations = new HashSet(0);
     private NewAnimalAndOwner newAnimalAndOwner;

    public Scheduling() {
    }

	
    public Scheduling(String typeService, String statusService, Date schedulingDate) {
        this.typeService = typeService;
        this.statusService = statusService;
        this.schedulingDate = schedulingDate;
    }
    public Scheduling(OwnersHasAnimals ownersHasAnimals, String typeService, String statusService, Date schedulingDate, Set vetConsultations, NewAnimalAndOwner newAnimalAndOwner) {
       this.ownersHasAnimals = ownersHasAnimals;
       this.typeService = typeService;
       this.statusService = statusService;
       this.schedulingDate = schedulingDate;
       this.vetConsultations = vetConsultations;
       this.newAnimalAndOwner = newAnimalAndOwner;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="Pk_schedule", unique=true, nullable=false)
    public Integer getPkSchedule() {
        return this.pkSchedule;
    }
    
    public void setPkSchedule(Integer pkSchedule) {
        this.pkSchedule = pkSchedule;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ownersHasAnimals_PK_ownersHasAnimals")
    public OwnersHasAnimals getOwnersHasAnimals() {
        return this.ownersHasAnimals;
    }
    
    public void setOwnersHasAnimals(OwnersHasAnimals ownersHasAnimals) {
        this.ownersHasAnimals = ownersHasAnimals;
    }

    
    @Column(name="typeService", nullable=false, length=21)
    public String getTypeService() {
        return this.typeService;
    }
    
    public void setTypeService(String typeService) {
        this.typeService = typeService;
    }

    
    @Column(name="statusService", nullable=false, length=51)
    public String getStatusService() {
        return this.statusService;
    }
    
    public void setStatusService(String statusService) {
        this.statusService = statusService;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="schedulingDate", nullable=false, length=19)
    public Date getSchedulingDate() {
        return this.schedulingDate;
    }
    
    public void setSchedulingDate(Date schedulingDate) {
        this.schedulingDate = schedulingDate;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="scheduling")
    public Set getVetConsultations() {
        return this.vetConsultations;
    }
    
    public void setVetConsultations(Set vetConsultations) {
        this.vetConsultations = vetConsultations;
    }

@OneToOne(fetch=FetchType.LAZY, mappedBy="scheduling")
    public NewAnimalAndOwner getNewAnimalAndOwner() {
        return this.newAnimalAndOwner;
    }
    
    public void setNewAnimalAndOwner(NewAnimalAndOwner newAnimalAndOwner) {
        this.newAnimalAndOwner = newAnimalAndOwner;
    }




}


