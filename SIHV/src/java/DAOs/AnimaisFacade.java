/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import MODELOs.Animais;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author heitor
 */
@Stateless
public class AnimaisFacade extends AbstractFacade<Animais> {

    @PersistenceContext(unitName = "SIHVPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnimaisFacade() {
        super(Animais.class);
    }
    
}
