package com.lades.sihv.model;
// Generated 25/09/2018 14:47:05 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CpfId generated by hbm2java
 */
@Embeddable
public class CpfId  implements java.io.Serializable {


     private int pkCpf;
     private int physicalPersonPkPhysicalPerson;
     private int physicalPersonPeoplePkPerson;

    public CpfId() {
    }

    public CpfId(int pkCpf, int physicalPersonPkPhysicalPerson, int physicalPersonPeoplePkPerson) {
       this.pkCpf = pkCpf;
       this.physicalPersonPkPhysicalPerson = physicalPersonPkPhysicalPerson;
       this.physicalPersonPeoplePkPerson = physicalPersonPeoplePkPerson;
    }
   


    @Column(name="PK_cpf", nullable=false)
    public int getPkCpf() {
        return this.pkCpf;
    }
    
    public void setPkCpf(int pkCpf) {
        this.pkCpf = pkCpf;
    }


    @Column(name="physicalPerson_PK_physicalPerson", nullable=false)
    public int getPhysicalPersonPkPhysicalPerson() {
        return this.physicalPersonPkPhysicalPerson;
    }
    
    public void setPhysicalPersonPkPhysicalPerson(int physicalPersonPkPhysicalPerson) {
        this.physicalPersonPkPhysicalPerson = physicalPersonPkPhysicalPerson;
    }


    @Column(name="physicalPerson_people_PK_person", nullable=false)
    public int getPhysicalPersonPeoplePkPerson() {
        return this.physicalPersonPeoplePkPerson;
    }
    
    public void setPhysicalPersonPeoplePkPerson(int physicalPersonPeoplePkPerson) {
        this.physicalPersonPeoplePkPerson = physicalPersonPeoplePkPerson;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof CpfId) ) return false;
		 CpfId castOther = ( CpfId ) other; 
         
		 return (this.getPkCpf()==castOther.getPkCpf())
 && (this.getPhysicalPersonPkPhysicalPerson()==castOther.getPhysicalPersonPkPhysicalPerson())
 && (this.getPhysicalPersonPeoplePkPerson()==castOther.getPhysicalPersonPeoplePkPerson());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getPkCpf();
         result = 37 * result + this.getPhysicalPersonPkPhysicalPerson();
         result = 37 * result + this.getPhysicalPersonPeoplePkPerson();
         return result;
   }   


}


