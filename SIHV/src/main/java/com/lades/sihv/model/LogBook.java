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
 * LogBook generated by hbm2java
 */
@Entity
@Table(name="logBook"
    ,catalog="bd_sihv"
)
public class LogBook  implements java.io.Serializable {


     private Integer pkLogBook;
     private String task;
     private Set logBookHasUserses = new HashSet(0);

    public LogBook() {
    }

	
    public LogBook(String task) {
        this.task = task;
    }
    public LogBook(String task, Set logBookHasUserses) {
       this.task = task;
       this.logBookHasUserses = logBookHasUserses;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="PK_logBook", unique=true, nullable=false)
    public Integer getPkLogBook() {
        return this.pkLogBook;
    }
    
    public void setPkLogBook(Integer pkLogBook) {
        this.pkLogBook = pkLogBook;
    }

    
    @Column(name="task", nullable=false, length=254)
    public String getTask() {
        return this.task;
    }
    
    public void setTask(String task) {
        this.task = task;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="logBook")
    public Set getLogBookHasUserses() {
        return this.logBookHasUserses;
    }
    
    public void setLogBookHasUserses(Set logBookHasUserses) {
        this.logBookHasUserses = logBookHasUserses;
    }




}


