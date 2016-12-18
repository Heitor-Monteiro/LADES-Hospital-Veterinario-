package com.lades.sihv.model;
// Generated 14/12/2016 15:34:50 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * FisicaId generated by hbm2java
 */
@Embeddable
public class FisicaId  implements java.io.Serializable {


     private int pkFisica;
     private int fkPessoa;

    public FisicaId() {
    }

    public FisicaId(int pkFisica, int fkPessoa) {
       this.pkFisica = pkFisica;
       this.fkPessoa = fkPessoa;
    }
   


    @Column(name="PK_fisica", nullable=false)
    public int getPkFisica() {
        return this.pkFisica;
    }
    
    public void setPkFisica(int pkFisica) {
        this.pkFisica = pkFisica;
    }


    @Column(name="FK_pessoa", nullable=false)
    public int getFkPessoa() {
        return this.fkPessoa;
    }
    
    public void setFkPessoa(int fkPessoa) {
        this.fkPessoa = fkPessoa;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof FisicaId) ) return false;
		 FisicaId castOther = ( FisicaId ) other; 
         
		 return (this.getPkFisica()==castOther.getPkFisica())
 && (this.getFkPessoa()==castOther.getFkPessoa());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getPkFisica();
         result = 37 * result + this.getFkPessoa();
         return result;
   }   


}

