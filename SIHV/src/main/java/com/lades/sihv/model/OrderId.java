package com.lades.sihv.model;
// Generated 04/10/2018 16:09:37 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * OrderId generated by hbm2java
 */
@Embeddable
public class OrderId  implements java.io.Serializable {


     private int pkOrder;
     private int classAnimalPkClassAnimal;

    public OrderId() {
    }

    public OrderId(int pkOrder, int classAnimalPkClassAnimal) {
       this.pkOrder = pkOrder;
       this.classAnimalPkClassAnimal = classAnimalPkClassAnimal;
    }
   


    @Column(name="PK_order", nullable=false)
    public int getPkOrder() {
        return this.pkOrder;
    }
    
    public void setPkOrder(int pkOrder) {
        this.pkOrder = pkOrder;
    }


    @Column(name="classAnimal_PK_classAnimal", nullable=false)
    public int getClassAnimalPkClassAnimal() {
        return this.classAnimalPkClassAnimal;
    }
    
    public void setClassAnimalPkClassAnimal(int classAnimalPkClassAnimal) {
        this.classAnimalPkClassAnimal = classAnimalPkClassAnimal;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof OrderId) ) return false;
		 OrderId castOther = ( OrderId ) other; 
         
		 return (this.getPkOrder()==castOther.getPkOrder())
 && (this.getClassAnimalPkClassAnimal()==castOther.getClassAnimalPkClassAnimal());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getPkOrder();
         result = 37 * result + this.getClassAnimalPkClassAnimal();
         return result;
   }   


}


