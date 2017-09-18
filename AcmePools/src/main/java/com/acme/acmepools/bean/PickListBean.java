/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.acmepools.bean;

/**
 *
 * @author juneau
 */

import java.util.ArrayList;
import java.util.List;
import com.acme.acmepools.entity.ColumnModel;

import org.primefaces.model.DualListModel;

public class PickListBean {

    private DualListModel<ColumnBean> columns;

    private List<ColumnBean> source = null;
    private List<ColumnBean> target = null;


    public PickListBean(List<ColumnModel> columnModelList) {
        //Columns  
        source = new ArrayList<ColumnBean>();
        target = new ArrayList<ColumnBean>();
   
        for(ColumnModel column:columnModelList){
            System.out.println(column.getColumnLabel());
            ColumnBean bean = new ColumnBean(column.getId(), column.getColumnName(), column.getColumnLabel());
            source.add(bean);
        }
        

        columns = new DualListModel<ColumnBean>(source, target);

    }

    /**
     * @return the columns
     */
    public DualListModel<ColumnBean> getColumns() {
        return columns;
    }

    /**
     * @param columns the columns to set
     */
    public void setColumns(DualListModel<ColumnBean> columns) {
        this.columns = columns;
    }

    

   
}
