/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acme.acmepools.session;

import com.acme.acmepools.entity.Customer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Juneau
 */
@Stateless
public class CustomerFacade extends AbstractFacade<Customer> {
    @PersistenceContext(unitName = "AcmePools_2.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerFacade() {
        super(Customer.class);
    }
    
    /**
     * Returns a <code>Customer</code> object for a given <code>customerId</code>
     * @param customerId
     * @return 
     */
    public Customer findByCustomerId(Integer customerId){
        return (Customer) em.createNamedQuery("Customer.findByCustomerId")
                .setParameter("customerId", customerId)
                .getSingleResult();
    }
    
}
