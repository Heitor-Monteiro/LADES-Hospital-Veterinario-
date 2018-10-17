package com.lades.sihv.model;
// Generated 04/10/2018 16:09:37 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SpeciesId generated by hbm2java
 */
@Embeddable
public class SpeciesId  implements java.io.Serializable {


     private int pkSpecies;
     private int genrePkGenre;
     private int genreOrderPkOrder;
     private int genreOrderClassAnimalPkClassAnimal;

    public SpeciesId() {
    }

    public SpeciesId(int pkSpecies, int genrePkGenre, int genreOrderPkOrder, int genreOrderClassAnimalPkClassAnimal) {
       this.pkSpecies = pkSpecies;
       this.genrePkGenre = genrePkGenre;
       this.genreOrderPkOrder = genreOrderPkOrder;
       this.genreOrderClassAnimalPkClassAnimal = genreOrderClassAnimalPkClassAnimal;
    }
   


    @Column(name="PK_species", nullable=false)
    public int getPkSpecies() {
        return this.pkSpecies;
    }
    
    public void setPkSpecies(int pkSpecies) {
        this.pkSpecies = pkSpecies;
    }


    @Column(name="genre_PK_genre", nullable=false)
    public int getGenrePkGenre() {
        return this.genrePkGenre;
    }
    
    public void setGenrePkGenre(int genrePkGenre) {
        this.genrePkGenre = genrePkGenre;
    }


    @Column(name="genre_order_PK_order", nullable=false)
    public int getGenreOrderPkOrder() {
        return this.genreOrderPkOrder;
    }
    
    public void setGenreOrderPkOrder(int genreOrderPkOrder) {
        this.genreOrderPkOrder = genreOrderPkOrder;
    }


    @Column(name="genre_order_classAnimal_PK_classAnimal", nullable=false)
    public int getGenreOrderClassAnimalPkClassAnimal() {
        return this.genreOrderClassAnimalPkClassAnimal;
    }
    
    public void setGenreOrderClassAnimalPkClassAnimal(int genreOrderClassAnimalPkClassAnimal) {
        this.genreOrderClassAnimalPkClassAnimal = genreOrderClassAnimalPkClassAnimal;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SpeciesId) ) return false;
		 SpeciesId castOther = ( SpeciesId ) other; 
         
		 return (this.getPkSpecies()==castOther.getPkSpecies())
 && (this.getGenrePkGenre()==castOther.getGenrePkGenre())
 && (this.getGenreOrderPkOrder()==castOther.getGenreOrderPkOrder())
 && (this.getGenreOrderClassAnimalPkClassAnimal()==castOther.getGenreOrderClassAnimalPkClassAnimal());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getPkSpecies();
         result = 37 * result + this.getGenrePkGenre();
         result = 37 * result + this.getGenreOrderPkOrder();
         result = 37 * result + this.getGenreOrderClassAnimalPkClassAnimal();
         return result;
   }   


}


