package com.lades.sihv.model;
// Generated 25/09/2018 14:47:05 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Houses generated by hbm2java
 */
@Entity
@Table(name="houses"
    ,catalog="bd_sihv"
)
public class Houses  implements java.io.Serializable {


     private Integer pkHouse;
     private Address address;
     private People people;
     private String numberHouse;
     private String numberCep;
     private String complement;

    public Houses() {
    }

	
    public Houses(Address address, People people, String numberHouse) {
        this.address = address;
        this.people = people;
        this.numberHouse = numberHouse;
    }
    public Houses(Address address, People people, String numberHouse, String numberCep, String complement) {
       this.address = address;
       this.people = people;
       this.numberHouse = numberHouse;
       this.numberCep = numberCep;
       this.complement = complement;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="PK_house", unique=true, nullable=false)
    public Integer getPkHouse() {
        return this.pkHouse;
    }
    
    public void setPkHouse(Integer pkHouse) {
        this.pkHouse = pkHouse;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="address_PK_address", nullable=false)
    public Address getAddress() {
        return this.address;
    }
    
    public void setAddress(Address address) {
        this.address = address;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="people_PK_person", nullable=false)
    public People getPeople() {
        return this.people;
    }
    
    public void setPeople(People people) {
        this.people = people;
    }

    
    @Column(name="numberHouse", nullable=false, length=9)
    public String getNumberHouse() {
        return this.numberHouse;
    }
    
    public void setNumberHouse(String numberHouse) {
        this.numberHouse = numberHouse;
    }

    
    @Column(name="numberCEP", length=9)
    public String getNumberCep() {
        return this.numberCep;
    }
    
    public void setNumberCep(String numberCep) {
        this.numberCep = numberCep;
    }

    
    @Column(name="complement", length=100)
    public String getComplement() {
        return this.complement;
    }
    
    public void setComplement(String complement) {
        this.complement = complement;
    }




}


