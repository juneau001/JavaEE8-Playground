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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 *
 * @author Juneau
 */
@Data
@Entity
@Table(name = "COLUMN_MODEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ColumnModel.findAll", query = "SELECT c FROM ColumnModel c"),
    @NamedQuery(name = "ColumnModel.findById", query = "SELECT c FROM ColumnModel c WHERE c.id = :id"),
    @NamedQuery(name = "ColumnModel.findByColumnName", query = "SELECT c FROM ColumnModel c WHERE c.columnName = :columnName"),
    @NamedQuery(name = "ColumnModel.findByColumnLabel", query = "SELECT c FROM ColumnModel c WHERE c.columnLabel = :columnLabel")})
public class ColumnModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 30)
    @Column(name = "COLUMN_NAME")
    private String columnName;
    @Size(max = 150)
    @Column(name = "COLUMN_LABEL")
    private String columnLabel;

}