package com.acme.acmepools.jsf;

import com.acme.acmepools.bean.ColumnBean;
import com.acme.acmepools.bean.PickListBean;
import com.acme.acmepools.session.ColumnModelFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Juneau
 */
@Named
@SessionScoped
public class ColumnModelController implements Serializable {

    @EJB
    ColumnModelFacade ejbFacade;

    private PickListBean pickListBean;
    private List<ColumnModel> columns;

    public DualListModel<ColumnBean> getColumns() {
        pickListBean = new PickListBean(ejbFacade.findAll());
        return pickListBean.getColumns();
    }

    public void setColumns(DualListModel<ColumnBean> columns) {
        pickListBean.setColumns(columns);
    }

    public List<ColumnModel> getDynamicColumns() {
        return columns;
    }

    public void preProcess(Object document) {
        System.out.println("starting preprocess");
        updateColumns();
    }

    /**
     * Called as preprocessor to export (after clicking Excel icon) to capture
     * the table component and call upon createDynamicColumns()
     *
     * @since 2.7.5
     */
    public void updateColumns() {
        //reset table state
        UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":customerExportForm:customerTable");
        table.setValueExpression("sortBy", null);

        //update columns
        createDynamicColumns();
    }

    /**
     * Creates column list dynamically based upon chosen selections from the
     * picklist on the CustomerExport.xhtml view
     *
     */
    private void createDynamicColumns() {
        String[] columnKeys = this.getIncludedColumnsByName().split(",");
        columns = new ArrayList<>();
        for (String columnKey : columnKeys) {
            String key = columnKey.trim();
            columns.add(new ColumnModel(getColumnLabel(key), key));

        }
    }

    /**
     * Returns a column label, given a specified columnName identifier
     *
     * @param columnName
     * @return
     */
    public String getColumnLabel(String columnName) {

        com.acme.acmepools.entity.ColumnModel model = ejbFacade.findId(columnName);
        return model.getColumnLabel();
    }

    /**
     * @return the included columns
     */
    public String getIncludedColumnsByName() {
        String tempIncludedColString = null;

        System.out.println("Number of included columns:" + pickListBean.getColumns().getTarget().size());
        List localSource = pickListBean.getColumns().getTarget();
        for (int x = 0; x <= localSource.size() - 1; x++) {
            String tempModel = (String) localSource.get(x);
            if (tempIncludedColString == null) {
                tempIncludedColString = tempModel;
            } else {
                tempIncludedColString = tempIncludedColString + "," + tempModel;
            }
        }

        return tempIncludedColString;
    }

    static public class ColumnModel implements Serializable {

        private String header;
        private String property;

        public ColumnModel(String header, String property) {
            this.header = header;
            this.property = property;
        }

        public String getHeader() {
            return header;
        }

        public String getProperty() {
            return property;
        }
    }
}
