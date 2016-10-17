package MODELO;
// Generated 17/10/2016 13:02:11 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AnimaisId generated by hbm2java
 */
@Embeddable
public class AnimaisId  implements java.io.Serializable {


     private int pkAnimal;
     private int clienteFkCliente;
     private int clienteFkPessoa;

    public AnimaisId() {
    }

    public AnimaisId(int pkAnimal, int clienteFkCliente, int clienteFkPessoa) {
       this.pkAnimal = pkAnimal;
       this.clienteFkCliente = clienteFkCliente;
       this.clienteFkPessoa = clienteFkPessoa;
    }
   


    @Column(name="PK_animal", nullable=false)
    public int getPkAnimal() {
        return this.pkAnimal;
    }
    
    public void setPkAnimal(int pkAnimal) {
        this.pkAnimal = pkAnimal;
    }


    @Column(name="cliente_FK_cliente", nullable=false)
    public int getClienteFkCliente() {
        return this.clienteFkCliente;
    }
    
    public void setClienteFkCliente(int clienteFkCliente) {
        this.clienteFkCliente = clienteFkCliente;
    }


    @Column(name="cliente_FK_pessoa", nullable=false)
    public int getClienteFkPessoa() {
        return this.clienteFkPessoa;
    }
    
    public void setClienteFkPessoa(int clienteFkPessoa) {
        this.clienteFkPessoa = clienteFkPessoa;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AnimaisId) ) return false;
		 AnimaisId castOther = ( AnimaisId ) other; 
         
		 return (this.getPkAnimal()==castOther.getPkAnimal())
 && (this.getClienteFkCliente()==castOther.getClienteFkCliente())
 && (this.getClienteFkPessoa()==castOther.getClienteFkPessoa());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getPkAnimal();
         result = 37 * result + this.getClienteFkCliente();
         result = 37 * result + this.getClienteFkPessoa();
         return result;
   }   


}


