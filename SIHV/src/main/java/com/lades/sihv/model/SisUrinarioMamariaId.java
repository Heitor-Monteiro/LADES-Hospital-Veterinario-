package com.lades.sihv.model;
// Generated 08/11/2016 12:03:42 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SisUrinarioMamariaId generated by hbm2java
 */
@Embeddable
public class SisUrinarioMamariaId  implements java.io.Serializable {


     private int pkSisUrinarioMamaria;
     private int consultaFkConsulta;

    public SisUrinarioMamariaId() {
    }

    public SisUrinarioMamariaId(int pkSisUrinarioMamaria, int consultaFkConsulta) {
       this.pkSisUrinarioMamaria = pkSisUrinarioMamaria;
       this.consultaFkConsulta = consultaFkConsulta;
    }
   


    @Column(name="PK_sisUrinarioMamaria", nullable=false)
    public int getPkSisUrinarioMamaria() {
        return this.pkSisUrinarioMamaria;
    }
    
    public void setPkSisUrinarioMamaria(int pkSisUrinarioMamaria) {
        this.pkSisUrinarioMamaria = pkSisUrinarioMamaria;
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
		 if ( !(other instanceof SisUrinarioMamariaId) ) return false;
		 SisUrinarioMamariaId castOther = ( SisUrinarioMamariaId ) other; 
         
		 return (this.getPkSisUrinarioMamaria()==castOther.getPkSisUrinarioMamaria())
 && (this.getConsultaFkConsulta()==castOther.getConsultaFkConsulta());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getPkSisUrinarioMamaria();
         result = 37 * result + this.getConsultaFkConsulta();
         return result;
   }   


}


