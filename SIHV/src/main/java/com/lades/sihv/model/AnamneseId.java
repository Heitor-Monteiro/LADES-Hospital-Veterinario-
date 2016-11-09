package com.lades.sihv.model;
// Generated 09/11/2016 10:58:37 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AnamneseId generated by hbm2java
 */
@Embeddable
public class AnamneseId  implements java.io.Serializable {


     private int pkAnamnese;
     private int consultaFkConsulta;

    public AnamneseId() {
    }

    public AnamneseId(int pkAnamnese, int consultaFkConsulta) {
       this.pkAnamnese = pkAnamnese;
       this.consultaFkConsulta = consultaFkConsulta;
    }
   


    @Column(name="PK_anamnese", nullable=false)
    public int getPkAnamnese() {
        return this.pkAnamnese;
    }
    
    public void setPkAnamnese(int pkAnamnese) {
        this.pkAnamnese = pkAnamnese;
    }


    @Column(name="consulta_FK_consulta", nullable=false)
    public int getConsultaFkConsulta() {
        return this.consultaFkConsulta;
    }
    
    public void setConsultaFkConsulta(int consultaFkConsulta) {
        this.consultaFkConsulta = consultaFkConsulta;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AnamneseId) ) return false;
		 AnamneseId castOther = ( AnamneseId ) other; 
         
		 return (this.getPkAnamnese()==castOther.getPkAnamnese())
 && (this.getConsultaFkConsulta()==castOther.getConsultaFkConsulta());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getPkAnamnese();
         result = 37 * result + this.getConsultaFkConsulta();
         return result;
   }   


}


