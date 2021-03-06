package com.lades.sihv.model;
// Generated 25/09/2018 14:47:05 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ExameFisicoId generated by hbm2java
 */
@Embeddable
public class ExameFisicoId  implements java.io.Serializable {


     private int pkExameFisico;
     private int vetConsultationPkVetConsultation;

    public ExameFisicoId() {
    }

    public ExameFisicoId(int pkExameFisico, int vetConsultationPkVetConsultation) {
       this.pkExameFisico = pkExameFisico;
       this.vetConsultationPkVetConsultation = vetConsultationPkVetConsultation;
    }
   


    @Column(name="PK_exameFisico", nullable=false)
    public int getPkExameFisico() {
        return this.pkExameFisico;
    }
    
    public void setPkExameFisico(int pkExameFisico) {
        this.pkExameFisico = pkExameFisico;
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
		 if ( !(other instanceof ExameFisicoId) ) return false;
		 ExameFisicoId castOther = ( ExameFisicoId ) other; 
         
		 return (this.getPkExameFisico()==castOther.getPkExameFisico())
 && (this.getVetConsultationPkVetConsultation()==castOther.getVetConsultationPkVetConsultation());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getPkExameFisico();
         result = 37 * result + this.getVetConsultationPkVetConsultation();
         return result;
   }   


}


