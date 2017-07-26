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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 *
 * @author Juneau
 */
@Data
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
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Lob
    @Column(name = "DESCRIPTION")
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "EST_HOURS")
    private Double estHours;
    @Column(name = "COST")
    private BigDecimal cost;
   // @Future
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
 
}
