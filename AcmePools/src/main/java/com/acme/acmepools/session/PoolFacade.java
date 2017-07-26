/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acme.acmepools.session;

import com.acme.acmepools.entity.Pool;
import com.acme.acmepools.session.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Juneau
 */
@Stateless
public class PoolFacade extends AbstractFacade<Pool> {
    @PersistenceContext(unitName = "AcmePools_2.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PoolFacade() {
        super(Pool.class);
    }
    
    /**
     * Returns a <code>Pool</code> for a given <code>poolId</code>.
     * @param pool
     * @return 
     */
    public Pool findByPool(Integer poolId){
        return (Pool) em.createQuery("select object(o) from Pool o " +
                "where o.id = :id")
                .setParameter("id", poolId).getSingleResult();
    }
    
}
