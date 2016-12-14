package com.lades.sihv.model;
// Generated 14/12/2016 15:34:50 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ExameImageId generated by hbm2java
 */
@Embeddable
public class ExameImageId  implements java.io.Serializable {


     private int pkExameImage;
     private int consultaFkConsulta;

    public ExameImageId() {
    }

    public ExameImageId(int pkExameImage, int consultaFkConsulta) {
       this.pkExameImage = pkExameImage;
       this.consultaFkConsulta = consultaFkConsulta;
    }
   


    @Column(name="PK_exameImage", nullable=false)
    public int getPkExameImage() {
        return this.pkExameImage;
    }
    
    public void setPkExameImage(int pkExameImage) {
        this.pkExameImage = pkExameImage;
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
		 if ( !(other instanceof ExameImageId) ) return false;
		 ExameImageId castOther = ( ExameImageId ) other; 
         
		 return (this.getPkExameImage()==castOther.getPkExameImage())
 && (this.getConsultaFkConsulta()==castOther.getConsultaFkConsulta());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getPkExameImage();
         result = 37 * result + this.getConsultaFkConsulta();
         return result;
   }   


}


