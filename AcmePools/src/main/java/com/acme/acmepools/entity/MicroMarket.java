/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.acmepools.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import lombok.Data;

/**
 *
 * @author Juneau
 */
@Entity
@Table(name = "MICRO_MARKET")
@XmlRootElement
@NamedQuery(name = "MicroMarket.findAll", query = "SELECT m FROM MicroMarket m")
@NamedQuery(name = "MicroMarket.findByZipCode", query = "SELECT m FROM MicroMarket m WHERE m.zipCode = :zipCode")
@NamedQuery(name = "MicroMarket.findByRadius", query = "SELECT m FROM MicroMarket m WHERE m.radius = :radius")
@NamedQuery(name = "MicroMarket.findByAreaLength", query = "SELECT m FROM MicroMarket m WHERE m.areaLength = :areaLength")
@NamedQuery(name = "MicroMarket.findByAreaWidth", query = "SELECT m FROM MicroMarket m WHERE m.areaWidth = :areaWidth")
public class MicroMarket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ZIP_CODE")
    private String zipCode;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "RADIUS")
    private Double radius;
    @Column(name = "AREA_LENGTH")
    private Double areaLength;
    @Column(name = "AREA_WIDTH")
    private Double areaWidth;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zip")
    private Collection<Customer> customerCollection;

    public MicroMarket() {
    }

    public MicroMarket(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return the zipCode
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode the zipCode to set
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return the radius
     */
    public Double getRadius() {
        return radius;
    }

    /**
     * @param radius the radius to set
     */
    public void setRadius(Double radius) {
        this.radius = radius;
    }

    /**
     * @return the areaLength
     */
    public Double getAreaLength() {
        return areaLength;
    }

    /**
     * @param areaLength the areaLength to set
     */
    public void setAreaLength(Double areaLength) {
        this.areaLength = areaLength;
    }

    /**
     * @return the areaWidth
     */
    public Double getAreaWidth() {
        return areaWidth;
    }

    /**
     * @param areaWidth the areaWidth to set
     */
    public void setAreaWidth(Double areaWidth) {
        this.areaWidth = areaWidth;
    }

    /**
     * @param customerCollection the customerCollection to set
     */
    public void setCustomerCollection(Collection<Customer> customerCollection) {
        this.customerCollection = customerCollection;
    }

    @XmlTransient
    public Collection<Customer> getCustomerCollection() {
        return customerCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (zipCode != null ? zipCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MicroMarket)) {
            return false;
        }
        MicroMarket other = (MicroMarket) object;
        if ((this.zipCode == null && other.zipCode != null) || (this.zipCode != null && !this.zipCode.equals(other.zipCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.acmepools.entity.MicroMarket[ zipCode=" + zipCode + " ]";
    }

}
