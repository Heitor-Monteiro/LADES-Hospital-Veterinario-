package com.lades.sihv.model;
// Generated 08/11/2016 12:03:42 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SisTegumentarId generated by hbm2java
 */
@Embeddable
public class SisTegumentarId  implements java.io.Serializable {


     private int pkSisTegumentar;
     private int consultaFkConsulta;

    public SisTegumentarId() {
    }

    public SisTegumentarId(int pkSisTegumentar, int consultaFkConsulta) {
       this.pkSisTegumentar = pkSisTegumentar;
       this.consultaFkConsulta = consultaFkConsulta;
    }
   


    @Column(name="PK_sisTegumentar", nullable=false)
    public int getPkSisTegumentar() {
        return this.pkSisTegumentar;
    }
    
    public void setPkSisTegumentar(int pkSisTegumentar) {
        this.pkSisTegumentar = pkSisTegumentar;
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
		 if ( !(other instanceof SisTegumentarId) ) return false;
		 SisTegumentarId castOther = ( SisTegumentarId ) other; 
         
		 return (this.getPkSisTegumentar()==castOther.getPkSisTegumentar())
 && (this.getConsultaFkConsulta()==castOther.getConsultaFkConsulta());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getPkSisTegumentar();
         result = 37 * result + this.getConsultaFkConsulta();
         return result;
   }   


}


