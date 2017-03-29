/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acme.acmepools.bean;

import java.math.BigDecimal;
import lombok.Data;
import lombok.NonNull;

/**
 *
 * @author juneau
 */

public class ColumnBean {
    @NonNull
    private BigDecimal id;
    @NonNull
    private String columnName;
    @NonNull
    private String columnLabel;
    
    public ColumnBean(BigDecimal id,
                      String columnName,
                      String columnLabel){
        this.id = id;
        this.columnName = columnName;
        this.columnLabel = columnLabel;
    }
    
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnLabel() {
        return columnLabel;
    }

    public void setColumnLabel(String columnLabel) {
        this.columnLabel = columnLabel;
    }
}
