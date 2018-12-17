package com.lades.sihv.model;
// Generated 25/09/2018 14:47:05 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SisMuscEsqueId generated by hbm2java
 */
@Embeddable
public class SisMuscEsqueId  implements java.io.Serializable {


     private int pkSisMuscEsque;
     private int vetConsultationPkVetConsultation;

    public SisMuscEsqueId() {
    }

    public SisMuscEsqueId(int pkSisMuscEsque, int vetConsultationPkVetConsultation) {
       this.pkSisMuscEsque = pkSisMuscEsque;
       this.vetConsultationPkVetConsultation = vetConsultationPkVetConsultation;
    }
   


    @Column(name="PK_sisMuscEsque", nullable=false)
    public int getPkSisMuscEsque() {
        return this.pkSisMuscEsque;
    }
    
    public void setPkSisMuscEsque(int pkSisMuscEsque) {
        this.pkSisMuscEsque = pkSisMuscEsque;
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
		 if ( !(other instanceof SisMuscEsqueId) ) return false;
		 SisMuscEsqueId castOther = ( SisMuscEsqueId ) other; 
         
		 return (this.getPkSisMuscEsque()==castOther.getPkSisMuscEsque())
 && (this.getVetConsultationPkVetConsultation()==castOther.getVetConsultationPkVetConsultation());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getPkSisMuscEsque();
         result = 37 * result + this.getVetConsultationPkVetConsultation();
         return result;
   }   


}


