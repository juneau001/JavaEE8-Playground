/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.acmepools.session;

import com.acme.acmepools.entity.MicroMarket;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Juneau
 */
@Stateless
public class MicroMarketFacade extends AbstractFacade<MicroMarket> {

    @PersistenceContext(unitName = "AcmePools_2.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MicroMarketFacade() {
        super(MicroMarket.class);
    }
    
    /**
     * Returns <code>MicroMarket</code> for the specified zipCode.
     * @param zipCode
     * @return 
     */
    public MicroMarket findByZipCode(String zipCode){
        return (MicroMarket) em.createQuery("select object(o) from MicroMarket o " +
                "where o.zipCode = :zipCode")
                .setParameter("zipCode", zipCode)
                .getSingleResult();
    }
}
