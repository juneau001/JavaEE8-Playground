/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.acmepools.session;

import com.acme.acmepools.entity.DiscountCode;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Juneau
 */
@Stateless
public class DiscountCodeFacade extends AbstractFacade<DiscountCode> {

    @PersistenceContext(unitName = "AcmePools_2.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DiscountCodeFacade() {
        super(DiscountCode.class);
    }
    
    /**
     * Return a <code>DiscountCode</code> object, given a specified discount code string.
     * @param discountCode
     * @return 
     */
    public DiscountCode findByCode(String discountCode){
        return (DiscountCode) em.createQuery("select object(o) from DiscountCode o " +
                "where o.discountCode = :discountCode")
                .setParameter("discountCode", discountCode)
                .getSingleResult();
    }
    
}
