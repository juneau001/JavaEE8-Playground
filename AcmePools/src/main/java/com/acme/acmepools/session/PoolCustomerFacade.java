/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acme.acmepools.session;

import com.acme.acmepools.entity.Customer;
import com.acme.acmepools.entity.PoolCustomer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Juneau
 */
@Stateless
public class PoolCustomerFacade extends AbstractFacade<PoolCustomer> {
    @PersistenceContext(unitName = "AcmePools_2.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PoolCustomerFacade() {
        super(PoolCustomer.class);
    }
    
    /**
     * Returns a <code>List<PoolCustomer></code> object for a given <code>Customer</code>
     * @param customer
     * @return 
     */
    public List<PoolCustomer> findByCustomerId(Customer customer){
        return  em.createQuery("select object(o) from PoolCustomer o " +
                "where o.customerId = :customerId")
                .setParameter("customerId", customer)
                .getResultList();
    }
}
