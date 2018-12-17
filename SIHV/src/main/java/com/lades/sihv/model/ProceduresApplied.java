package com.lades.sihv.model;
// Generated 10/12/2018 16:25:52 by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ProceduresApplied generated by hbm2java
 */
@Entity
@Table(name="proceduresApplied"
    ,catalog="bd_sihv"
)
public class ProceduresApplied  implements java.io.Serializable {


     private Integer pkProceduresApplied;
     private Procedures procedures;
     private VetConsultation vetConsultation;
     private boolean paymentStatus;
     private Date applicationDate;

    public ProceduresApplied() {
    }

    public ProceduresApplied(Procedures procedures, VetConsultation vetConsultation, boolean paymentStatus, Date applicationDate) {
       this.procedures = procedures;
       this.vetConsultation = vetConsultation;
       this.paymentStatus = paymentStatus;
       this.applicationDate = applicationDate;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="PK_proceduresApplied", unique=true, nullable=false)
    public Integer getPkProceduresApplied() {
        return this.pkProceduresApplied;
    }
    
    public void setPkProceduresApplied(Integer pkProceduresApplied) {
        this.pkProceduresApplied = pkProceduresApplied;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="procedures_PK_procedure", nullable=false)
    public Procedures getProcedures() {
        return this.procedures;
    }
    
    public void setProcedures(Procedures procedures) {
        this.procedures = procedures;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="vetConsultation_PK_vetConsultation", nullable=false)
    public VetConsultation getVetConsultation() {
        return this.vetConsultation;
    }
    
    public void setVetConsultation(VetConsultation vetConsultation) {
        this.vetConsultation = vetConsultation;
    }

    
    @Column(name="paymentStatus", nullable=false)
    public boolean isPaymentStatus() {
        return this.paymentStatus;
    }
    
    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="applicationDate", nullable=false, length=19)
    public Date getApplicationDate() {
        return this.applicationDate;
    }
    
    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }




}


