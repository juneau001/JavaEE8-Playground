/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acme.acmepools.session;

import com.acme.acmepools.entity.ColumnModel;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Juneau
 */
@Stateless
public class ColumnModelFacade extends AbstractFacade<ColumnModel> {
    @PersistenceContext(unitName = "AcmePools_2.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ColumnModelFacade() {
        super(ColumnModel.class);
    }
    
    public ColumnModel findId(String columnName){
        return (ColumnModel) em.createQuery("select object(o) from ColumnModel as o " +
                              "where o.columnName = :columnName")
                              .setParameter("columnName", columnName)
                              .getSingleResult();
    }
    
}
