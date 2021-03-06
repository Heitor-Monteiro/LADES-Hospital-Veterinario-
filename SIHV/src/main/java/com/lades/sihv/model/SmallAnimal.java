package com.lades.sihv.model;
// Generated 04/10/2018 16:09:37 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * SmallAnimal generated by hbm2java
 */
@Entity
@Table(name="smallAnimal"
    ,catalog="bd_sihv"
)
public class SmallAnimal  implements java.io.Serializable {


     private Integer pkSmallAnimal;
     private Animals animals;
     private Races races;

    public SmallAnimal() {
    }

    public SmallAnimal(Animals animals, Races races) {
       this.animals = animals;
       this.races = races;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="PK_smallAnimal", unique=true, nullable=false)
    public Integer getPkSmallAnimal() {
        return this.pkSmallAnimal;
    }
    
    public void setPkSmallAnimal(Integer pkSmallAnimal) {
        this.pkSmallAnimal = pkSmallAnimal;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="animals_PK_animal", nullable=false)
    public Animals getAnimals() {
        return this.animals;
    }
    
    public void setAnimals(Animals animals) {
        this.animals = animals;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns( { 
        @JoinColumn(name="races_PK_races", referencedColumnName="PK_races", nullable=false), 
        @JoinColumn(name="races_species_PK_species", referencedColumnName="species_PK_species", nullable=false), 
        @JoinColumn(name="races_species_genre_PK_genre", referencedColumnName="species_genre_PK_genre", nullable=false), 
        @JoinColumn(name="races_species_genre_order_PK_order", referencedColumnName="species_genre_order_PK_order", nullable=false), 
        @JoinColumn(name="races_species_genre_order_classAnimal_PK_classAnimal", referencedColumnName="species_genre_order_classAnimal_PK_classAnimal", nullable=false) } )
    public Races getRaces() {
        return this.races;
    }
    
    public void setRaces(Races races) {
        this.races = races;
    }




}


