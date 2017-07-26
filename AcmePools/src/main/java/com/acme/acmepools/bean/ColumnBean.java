/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.acme.acmepools.bean;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
/**
 *
 * @author juneau
 */

public class ColumnBean {
    @NotNull
    private BigDecimal id;
    @NotNull
    private String columnName;
    @NotNull
    private String columnLabel;
    
    public ColumnBean(BigDecimal id,
                      String columnName,
                      String columnLabel){
        this.id = id;
        this.columnName = columnName;
        this.columnLabel = columnLabel;
    }
        
}
