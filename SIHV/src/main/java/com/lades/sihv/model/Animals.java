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
 * Animals generated by hbm2java
 */
@Entity
@Table(name="animals"
    ,catalog="bd_sihv"
)
public class Animals  implements java.io.Serializable {


     private Integer pkAnimal;
     private ClassAnimal classAnimal;
     private String animalName;
     private String genderAnimal;
     private String animalAge;
     private String rghv;
     private String categoryAnimal;
     private boolean deathAnimal;
     private Date registrationDate;
     private Set ownersHasAnimalses = new HashSet(0);
     private Set animalPhotos = new HashSet(0);

    public Animals() {
    }

	
    public Animals(ClassAnimal classAnimal, String animalName, String genderAnimal, String animalAge, String rghv, String categoryAnimal, boolean deathAnimal, Date registrationDate) {
        this.classAnimal = classAnimal;
        this.animalName = animalName;
        this.genderAnimal = genderAnimal;
        this.animalAge = animalAge;
        this.rghv = rghv;
        this.categoryAnimal = categoryAnimal;
        this.deathAnimal = deathAnimal;
        this.registrationDate = registrationDate;
    }
    public Animals(ClassAnimal classAnimal, String animalName, String genderAnimal, String animalAge, String rghv, String categoryAnimal, boolean deathAnimal, Date registrationDate, Set ownersHasAnimalses, Set animalPhotos) {
       this.classAnimal = classAnimal;
       this.animalName = animalName;
       this.genderAnimal = genderAnimal;
       this.animalAge = animalAge;
       this.rghv = rghv;
       this.categoryAnimal = categoryAnimal;
       this.deathAnimal = deathAnimal;
       this.registrationDate = registrationDate;
       this.ownersHasAnimalses = ownersHasAnimalses;
       this.animalPhotos = animalPhotos;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="PK_animal", unique=true, nullable=false)
    public Integer getPkAnimal() {
        return this.pkAnimal;
    }
    
    public void setPkAnimal(Integer pkAnimal) {
        this.pkAnimal = pkAnimal;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="classAnimal_PK_classAnimal", nullable=false)
    public ClassAnimal getClassAnimal() {
        return this.classAnimal;
    }
    
    public void setClassAnimal(ClassAnimal classAnimal) {
        this.classAnimal = classAnimal;
    }

    
    @Column(name="animalName", nullable=false, length=70)
    public String getAnimalName() {
        return this.animalName;
    }
    
    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    
    @Column(name="genderAnimal", nullable=false, length=3)
    public String getGenderAnimal() {
        return this.genderAnimal;
    }
    
    public void setGenderAnimal(String genderAnimal) {
        this.genderAnimal = genderAnimal;
    }

    
    @Column(name="animalAge", nullable=false, length=100)
    public String getAnimalAge() {
        return this.animalAge;
    }
    
    public void setAnimalAge(String animalAge) {
        this.animalAge = animalAge;
    }

    
    @Column(name="rghv", nullable=false, length=100)
    public String getRghv() {
        return this.rghv;
    }
    
    public void setRghv(String rghv) {
        this.rghv = rghv;
    }

    
    @Column(name="categoryAnimal", nullable=false, length=5)
    public String getCategoryAnimal() {
        return this.categoryAnimal;
    }
    
    public void setCategoryAnimal(String categoryAnimal) {
        this.categoryAnimal = categoryAnimal;
    }

    
    @Column(name="deathAnimal", nullable=false)
    public boolean isDeathAnimal() {
        return this.deathAnimal;
    }
    
    public void setDeathAnimal(boolean deathAnimal) {
        this.deathAnimal = deathAnimal;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="registrationDate", nullable=false, length=19)
    public Date getRegistrationDate() {
        return this.registrationDate;
    }
    
    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="animals")
    public Set getOwnersHasAnimalses() {
        return this.ownersHasAnimalses;
    }
    
    public void setOwnersHasAnimalses(Set ownersHasAnimalses) {
        this.ownersHasAnimalses = ownersHasAnimalses;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="animals")
    public Set getAnimalPhotos() {
        return this.animalPhotos;
    }
    
    public void setAnimalPhotos(Set animalPhotos) {
        this.animalPhotos = animalPhotos;
    }




}


