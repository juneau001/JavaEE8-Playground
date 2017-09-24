/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.acmepools.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juneau
 */
@Entity
@Table(name = "COLUMN_MODEL")
@XmlRootElement

@NamedQuery(name = "ColumnModel.findAll", query = "SELECT c FROM ColumnModel c")
@NamedQuery(name = "ColumnModel.findById", query = "SELECT c FROM ColumnModel c WHERE c.id = :id")
@NamedQuery(name = "ColumnModel.findByColumnName", query = "SELECT c FROM ColumnModel c WHERE c.columnName = :columnName")
@NamedQuery(name = "ColumnModel.findByColumnLabel", query = "SELECT c FROM ColumnModel c WHERE c.columnLabel = :columnLabel")
public class ColumnModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    // Uncomment if using Apache Derby 10.6+
//    @GeneratedValue(strategy=GenerationType.SEQUENCE,
//    generator="column_model_s_generator")
//    @SequenceGenerator(name="column_model_s_generator",sequenceName="column_model_s", allocationSize=1)
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 30)
    @Column(name = "COLUMN_NAME")
    private String columnName;
    @Size(max = 150)
    @Column(name = "COLUMN_LABEL")
    private String columnLabel;

    public ColumnModel() {

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
     * @return the columnName
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * @param columnName the columnName to set
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    /**
     * @return the columnLabel
     */
    public String getColumnLabel() {
        return columnLabel;
    }

    /**
     * @param columnLabel the columnLabel to set
     */
    public void setColumnLabel(String columnLabel) {
        this.columnLabel = columnLabel;
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
        if (!(object instanceof ColumnModel)) {
            return false;
        }
        ColumnModel other = (ColumnModel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.acmepools.entity.ColumnModel[ id=" + id + " ]";
    }

}
