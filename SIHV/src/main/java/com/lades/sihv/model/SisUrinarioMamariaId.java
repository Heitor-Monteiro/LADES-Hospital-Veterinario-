package com.lades.sihv.model;
// Generated 25/09/2018 14:47:05 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SisUrinarioMamariaId generated by hbm2java
 */
@Embeddable
public class SisUrinarioMamariaId  implements java.io.Serializable {


     private int pkSisUrinarioMamaria;
     private int vetConsultationPkVetConsultation;

    public SisUrinarioMamariaId() {
    }

    public SisUrinarioMamariaId(int pkSisUrinarioMamaria, int vetConsultationPkVetConsultation) {
       this.pkSisUrinarioMamaria = pkSisUrinarioMamaria;
       this.vetConsultationPkVetConsultation = vetConsultationPkVetConsultation;
    }
   


    @Column(name="PK_sisUrinarioMamaria", nullable=false)
    public int getPkSisUrinarioMamaria() {
        return this.pkSisUrinarioMamaria;
    }
    
    public void setPkSisUrinarioMamaria(int pkSisUrinarioMamaria) {
        this.pkSisUrinarioMamaria = pkSisUrinarioMamaria;
    }


    @Column(name="vetConsultation_PK_vetConsultation", nullable=false)
    public int getVetConsultationPkVetConsultation() {
        return this.vetConsultationPkVetConsultation;
    }
    
    public void setVetConsultationPkVetConsultation(int vetConsultationPkVetConsultation) {
        this.vetConsultationPkVetConsultation = vetConsultationPkVetConsultation;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SisUrinarioMamariaId) ) return false;
		 SisUrinarioMamariaId castOther = ( SisUrinarioMamariaId ) other; 
         
		 return (this.getPkSisUrinarioMamaria()==castOther.getPkSisUrinarioMamaria())
 && (this.getVetConsultationPkVetConsultation()==castOther.getVetConsultationPkVetConsultation());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getPkSisUrinarioMamaria();
         result = 37 * result + this.getVetConsultationPkVetConsultation();
         return result;
   }   


}


