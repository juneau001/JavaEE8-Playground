/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.acmepools.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juneau
 */
@Entity
@Table(name = "POOL_CUSTOMER")
@XmlRootElement
@NamedQuery(name = "PoolCustomer.findAll", query = "SELECT p FROM PoolCustomer p")
@NamedQuery(name = "PoolCustomer.findById", query = "SELECT p FROM PoolCustomer p WHERE p.id = :id")
public class PoolCustomer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    // Uncomment if using Apache Derby 10.6+
//    @GeneratedValue(strategy=GenerationType.SEQUENCE,
//    generator="pool_cust_s_generator")
//    @SequenceGenerator(name="pool_cust_s_generator",sequenceName="pool_cust_s", allocationSize=1)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "CUSTOMER_ID")
    @ManyToOne
    private Customer customerId;
    @JoinColumn(name = "POOL_ID", referencedColumnName = "ID")
    @ManyToOne
    private Pool poolId;
    @OneToMany(mappedBy = "customerId")
    private Collection<Job> jobCollection;

    public PoolCustomer() {
    }

    public PoolCustomer(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the poolId
     */
    public Pool getPoolId() {
        return poolId;
    }

    /**
     * @param poolId the poolId to set
     */
    public void setPoolId(Pool poolId) {
        this.poolId = poolId;
    }

    /**
     * @return the jobCollection
     */
    public Collection<Job> getJobCollection() {
        return jobCollection;
    }

    /**
     * @param jobCollection the jobCollection to set
     */
    public void setJobCollection(Collection<Job> jobCollection) {
        this.jobCollection = jobCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PoolCustomer)) {
            return false;
        }
        PoolCustomer other = (PoolCustomer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.acmepools.entity.PoolCustomer[ id=" + id + " ]";
    }

}
