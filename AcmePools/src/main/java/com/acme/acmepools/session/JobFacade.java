/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acme.acmepools.session;

import com.acme.acmepools.entity.Customer;
import com.acme.acmepools.entity.Job;
import com.acme.acmepools.entity.PoolCustomer;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Juneau
 */
@Stateless
public class JobFacade extends AbstractFacade<Job> {
    
    @PersistenceContext(unitName = "AcmePools_2.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public JobFacade() {
        super(Job.class);
    }
    
    public List<Job> findByCustomer(PoolCustomer customer){
        Stream<Job> jobList =  em.createQuery("select object(o) from Job o " +
                "where o.customerId = :customer")
                .setParameter("customer", customer).getResultStream();
        // Log the retrieved jobs for some other processing.  Commented out because it is only possible to perform
        //  one opertation on the stream.
        //
        //  jobList.map(j -> j.getCustomerId().getCustomerId().getCustomerId() + " ordered job " + j.getId() + " - Starting " + j.getWorkDate())
        //	.forEach(jm -> System.out.println(jm));
        return jobList.collect(Collectors.toList());
    }
    
    /**
     * Returns a List of customers by specified pool shape
     * @param poolShape
     * @return 
     */
    public List<Job> findByCustPoolShape(String poolShape){
        System.out.println("Finding " + poolShape + " shaped pools...");
        Stream<Job> jobstream = em.createQuery("select object(o) from Job o").getResultStream();
       
        return jobstream.filter(
                    c -> poolShape.equals(c.getCustomerId().getPoolId().getShape()))
            .collect(Collectors.toList());
        
    }
    
    
}
