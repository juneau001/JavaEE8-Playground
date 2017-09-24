/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acme.acmepools.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 *
 * @author Juneau
 */
@Entity
@Table(name = "JOB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Job.findAll", query = "SELECT j FROM Job j"),
    @NamedQuery(name = "Job.findById", query = "SELECT j FROM Job j WHERE j.id = :id"),
    @NamedQuery(name = "Job.findByEstHours", query = "SELECT j FROM Job j WHERE j.estHours = :estHours"),
    @NamedQuery(name = "Job.findByCost", query = "SELECT j FROM Job j WHERE j.cost = :cost")})
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    // Uncomment if using Apache Derby 10.6+
//    @GeneratedValue(strategy=GenerationType.SEQUENCE,
//    generator="job_s_generator")
//    @SequenceGenerator(name="job_s_generator",sequenceName="job_s", allocationSize=1)
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Lob
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "EST_HOURS")
    private Double estHours;
    @Column(name = "COST")
    private BigDecimal cost;
    @Future (message="Please ensure you are entering a work date in the future.")
    @Column(name = "WORK_DATE")
    private LocalDate workDate;
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID")
    @ManyToOne
    private PoolCustomer customerId;

    public Job() {
    }

    public Job(BigDecimal id) {
        this.id = id;
    }
    
    
    /**
     * @return the id
     */
    public BigDecimal getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(BigDecimal id) {
        this.id = id;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the estHours
     */
    public Double getEstHours() {
        return estHours;
    }

    /**
     * @param estHours the estHours to set
     */
    public void setEstHours(Double estHours) {
        this.estHours = estHours;
    }

    /**
     * @return the cost
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    /**
     * @return the workDate
     */
    public LocalDate getWorkDate() {
        return workDate;
    }

    /**
     * @param workDate the workDate to set
     */
    public void setWorkDate(LocalDate workDate) {
        this.workDate = workDate;
    }

    /**
     * @return the customerId
     */
    public PoolCustomer getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(PoolCustomer customerId) {
        this.customerId = customerId;
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
        if (!(object instanceof Job)) {
            return false;
        }
        Job other = (Job) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.acmepools.entity.Job[ id=" + id + " ]";
    }
 
}
