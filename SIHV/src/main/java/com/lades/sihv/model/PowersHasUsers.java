package com.lades.sihv.model;
// Generated 25/09/2018 14:47:05 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * PowersHasUsers generated by hbm2java
 */
@Entity
@Table(name="powers_has_users"
    ,catalog="bd_sihv"
)
public class PowersHasUsers  implements java.io.Serializable {


     private Integer pkPowersHasUsers;
     private Powers powers;
     private Users users;

    public PowersHasUsers() {
    }

    public PowersHasUsers(Powers powers, Users users) {
       this.powers = powers;
       this.users = users;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="PK_powersHasUsers", unique=true, nullable=false)
    public Integer getPkPowersHasUsers() {
        return this.pkPowersHasUsers;
    }
    
    public void setPkPowersHasUsers(Integer pkPowersHasUsers) {
        this.pkPowersHasUsers = pkPowersHasUsers;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="powers_PK_power", nullable=false)
    public Powers getPowers() {
        return this.powers;
    }
    
    public void setPowers(Powers powers) {
        this.powers = powers;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="users_PK_user", nullable=false)
    public Users getUsers() {
        return this.users;
    }
    
    public void setUsers(Users users) {
        this.users = users;
    }




}

