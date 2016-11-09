package com.lades.sihv.model;
// Generated 08/11/2016 12:03:42 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SisNeurologicoId generated by hbm2java
 */
@Embeddable
public class SisNeurologicoId  implements java.io.Serializable {


     private int pkSisNeurologico;
     private int consultaFkConsulta;

    public SisNeurologicoId() {
    }

    public SisNeurologicoId(int pkSisNeurologico, int consultaFkConsulta) {
       this.pkSisNeurologico = pkSisNeurologico;
       this.consultaFkConsulta = consultaFkConsulta;
    }
   


    @Column(name="PK_sisNeurologico", nullable=false)
    public int getPkSisNeurologico() {
        return this.pkSisNeurologico;
    }
    
    public void setPkSisNeurologico(int pkSisNeurologico) {
        this.pkSisNeurologico = pkSisNeurologico;
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
		 if ( !(other instanceof SisNeurologicoId) ) return false;
		 SisNeurologicoId castOther = ( SisNeurologicoId ) other; 
         
		 return (this.getPkSisNeurologico()==castOther.getPkSisNeurologico())
 && (this.getConsultaFkConsulta()==castOther.getConsultaFkConsulta());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getPkSisNeurologico();
         result = 37 * result + this.getConsultaFkConsulta();
         return result;
   }   


}

