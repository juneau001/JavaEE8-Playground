package com.acme.acmepools.jsf;

import com.acme.acmepools.entity.Pool;
import com.acme.acmepools.session.PoolFacade;
import com.acme.acmepools.entity.util.JsfUtil;
import com.acme.acmepools.entity.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("poolController")
@SessionScoped
public class PoolController implements Serializable {

    @EJB
    private com.acme.acmepools.session.PoolFacade ejbFacade;
    private List<Pool> items = null;
    private List<Pool> filteredPools;
    private Pool selected;

    public PoolController() {
    }

    public Pool getSelected() {
        return selected;
    }

    public void setSelected(Pool selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PoolFacade getFacade() {
        return ejbFacade;
    }

    public Pool prepareCreate() {
        selected = new Pool();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PoolCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PoolUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PoolDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Pool> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Pool getPool(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Pool> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Pool> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    /**
     * @return the filteredPools
     */
    public List<Pool> getFilteredPools() {
        return filteredPools;
    }

    /**
     * @param filteredPools the filteredPools to set
     */
    public void setFilteredPools(List<Pool> filteredPools) {
        this.filteredPools = filteredPools;
    }

    @FacesConverter(forClass = Pool.class)
    public static class PoolControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PoolController controller = (PoolController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "poolController");
            return controller.getPool(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Pool) {
                Pool o = (Pool) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Pool.class.getName()});
                return null;
            }
        }

    }
    
    public Pool findByPool(Pool pool){
        Pool returnPool = null;
        try {
            returnPool = ejbFacade.findByPool(pool.getId());
        } catch (NullPointerException ex){
            System.out.println(ex);
        }
        return returnPool;
    }

}
