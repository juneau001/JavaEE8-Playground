/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acme.acmepools.bean;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;


/**
 *
 * @author juneau
 */

public class ColumnBean {

    private BigDecimal id;
    private String columnName;
    private String columnLabel;
    
    public ColumnBean(BigDecimal id,
                      String columnName,
                      String columnLabel){
        this.id = id;
        this.columnName = columnName;
        this.columnLabel = columnLabel;
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
}
