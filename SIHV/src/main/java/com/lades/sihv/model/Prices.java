package com.lades.sihv.model;
// Generated 02/11/2018 14:22:32 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Prices generated by hbm2java
 */
@Entity
@Table(name="prices"
    ,catalog="bd_sihv"
)
public class Prices  implements java.io.Serializable {


     private int proceduresPkProcedure;
     private Category category;
     private Procedures procedures;
     private BigDecimal price;
     private String dosage;
     private Date dateOfLastModification;
     private Date dateOfFirstRegistration;

    public Prices() {
    }

	
    public Prices(Procedures procedures, BigDecimal price, String dosage, Date dateOfLastModification, Date dateOfFirstRegistration) {
        this.procedures = procedures;
        this.price = price;
        this.dosage = dosage;
        this.dateOfLastModification = dateOfLastModification;
        this.dateOfFirstRegistration = dateOfFirstRegistration;
    }
    public Prices(Category category, Procedures procedures, BigDecimal price, String dosage, Date dateOfLastModification, Date dateOfFirstRegistration) {
       this.category = category;
       this.procedures = procedures;
       this.price = price;
       this.dosage = dosage;
       this.dateOfLastModification = dateOfLastModification;
       this.dateOfFirstRegistration = dateOfFirstRegistration;
    }
   
     @GenericGenerator(name="generator", strategy="foreign", parameters=@Parameter(name="property", value="procedures"))@Id @GeneratedValue(generator="generator")

    
    @Column(name="procedures_PK_procedure", unique=true, nullable=false)
    public int getProceduresPkProcedure() {
        return this.proceduresPkProcedure;
    }
    
    public void setProceduresPkProcedure(int proceduresPkProcedure) {
        this.proceduresPkProcedure = proceduresPkProcedure;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="category_PK_category")
    public Category getCategory() {
        return this.category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }

@OneToOne(fetch=FetchType.LAZY)@PrimaryKeyJoinColumn
    public Procedures getProcedures() {
        return this.procedures;
    }
    
    public void setProcedures(Procedures procedures) {
        this.procedures = procedures;
    }

    
    @Column(name="price", nullable=false, precision=6)
    public BigDecimal getPrice() {
        return this.price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    
    @Column(name="dosage", nullable=false, length=9)
    public String getDosage() {
        return this.dosage;
    }
    
    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="dateOfLastModification", nullable=false, length=10)
    public Date getDateOfLastModification() {
        return this.dateOfLastModification;
    }
    
    public void setDateOfLastModification(Date dateOfLastModification) {
        this.dateOfLastModification = dateOfLastModification;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="dateOfFirstRegistration", nullable=false, length=19)
    public Date getDateOfFirstRegistration() {
        return this.dateOfFirstRegistration;
    }
    
    public void setDateOfFirstRegistration(Date dateOfFirstRegistration) {
        this.dateOfFirstRegistration = dateOfFirstRegistration;
    }




}


