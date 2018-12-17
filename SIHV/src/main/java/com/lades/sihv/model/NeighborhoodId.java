package com.lades.sihv.model;
// Generated 25/09/2018 14:47:05 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * NeighborhoodId generated by hbm2java
 */
@Embeddable
public class NeighborhoodId  implements java.io.Serializable {


     private int pkNeighborhood;
     private int cityPkCity;

    public NeighborhoodId() {
    }

    public NeighborhoodId(int pkNeighborhood, int cityPkCity) {
       this.pkNeighborhood = pkNeighborhood;
       this.cityPkCity = cityPkCity;
    }
   


    @Column(name="PK_neighborhood", nullable=false)
    public int getPkNeighborhood() {
        return this.pkNeighborhood;
    }
    
    public void setPkNeighborhood(int pkNeighborhood) {
        this.pkNeighborhood = pkNeighborhood;
    }


    @Column(name="city_PK_city", nullable=false)
    public int getCityPkCity() {
        return this.cityPkCity;
    }
    
    public void setCityPkCity(int cityPkCity) {
        this.cityPkCity = cityPkCity;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof NeighborhoodId) ) return false;
		 NeighborhoodId castOther = ( NeighborhoodId ) other; 
         
		 return (this.getPkNeighborhood()==castOther.getPkNeighborhood())
 && (this.getCityPkCity()==castOther.getCityPkCity());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getPkNeighborhood();
         result = 37 * result + this.getCityPkCity();
         return result;
   }   


}


