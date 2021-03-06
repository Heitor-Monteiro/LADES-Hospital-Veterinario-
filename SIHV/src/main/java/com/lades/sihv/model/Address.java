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
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Address generated by hbm2java
 */
@Entity
@Table(name="address"
    ,catalog="bd_sihv"
)
public class Address  implements java.io.Serializable {


     private Integer pkAddress;
     private Neighborhood neighborhood;
     private Street street;
     private Set houseses = new HashSet(0);

    public Address() {
    }

	
    public Address(Neighborhood neighborhood, Street street) {
        this.neighborhood = neighborhood;
        this.street = street;
    }
    public Address(Neighborhood neighborhood, Street street, Set houseses) {
       this.neighborhood = neighborhood;
       this.street = street;
       this.houseses = houseses;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="PK_address", unique=true, nullable=false)
    public Integer getPkAddress() {
        return this.pkAddress;
    }
    
    public void setPkAddress(Integer pkAddress) {
        this.pkAddress = pkAddress;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumns( { 
        @JoinColumn(name="neighborhood_PK_neighborhood", referencedColumnName="PK_neighborhood", nullable=false), 
        @JoinColumn(name="neighborhood_city_PK_city", referencedColumnName="city_PK_city", nullable=false) } )
    public Neighborhood getNeighborhood() {
        return this.neighborhood;
    }
    
    public void setNeighborhood(Neighborhood neighborhood) {
        this.neighborhood = neighborhood;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="street_PK_street", nullable=false)
    public Street getStreet() {
        return this.street;
    }
    
    public void setStreet(Street street) {
        this.street = street;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="address")
    public Set getHouseses() {
        return this.houseses;
    }
    
    public void setHouseses(Set houseses) {
        this.houseses = houseses;
    }




}


