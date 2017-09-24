package com.acme.acmepools.jsf;

import com.acme.acmepools.entity.Customer;
import com.acme.acmepools.entity.util.JsfUtil;
import com.acme.acmepools.entity.util.JsfUtil.PersistAction;
import com.acme.acmepools.session.CustomerFacade;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonPointer;
import javax.json.JsonValue;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;

@Named("customerController")
@SessionScoped
public class CustomerController implements Serializable {

    @EJB
    private com.acme.acmepools.session.CustomerFacade ejbFacade;
    private List<Customer> customers = null;
    @Getter
    @Setter
    private Customer selected;

    @Getter
    @Setter
    private String addressSearchText;

    @Getter
    @Setter
    private String searchResult;

    public CustomerController() {
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CustomerFacade getFacade() {
        return ejbFacade;
    }

    public Customer prepareCreate() {
        selected = new Customer();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CustomerCreated"));
        if (!JsfUtil.isValidationFailed()) {
            customers = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CustomerUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CustomerDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            customers = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Customer> getItems() {
        if (customers == null) {
            customers = getFacade().findAll();
        }
        return customers;
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
                System.out.println(ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Customer getCustomer(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Customer> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Customer> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Customer.class)
    public static class CustomerControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CustomerController controller = (CustomerController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "customerController");
            return controller.getCustomer(getKey(value));
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
            if (object instanceof Customer) {
                Customer o = (Customer) object;
                return getStringKey(o.getCustomerId());
            } else {

                return null;
            }
        }

    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        if (!oldValue.equals(newValue)) {
            // Save to the database
            DataTable table = (DataTable) event.getSource();
            Customer customer = (Customer) table.getRowData();
            ejbFacade.edit(customer);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully Updated", "Updated value to " + newValue));
        }
    }

    public String loadCustomer() {
        Map requestMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String customer = (String) requestMap.get("customer");
        selected = ejbFacade.find(Integer.valueOf(customer));
        return "customerInfo";
    }

    /**
     * Utilizes the JSON-B API to create a JSON representation from a collection
     * of data and serializes.
     *
     * @return
     */
    public String fetchJson() {
        System.out.println("Items: " + customers);
        Jsonb jsonb = JsonbBuilder.create();
        String result = null;

        result = jsonb.toJson(customers);

        return result;
    }

    public void findCustomerByAddress() {
        searchResult = null;
        String text = "/" + this.addressSearchText;
        JsonObject json = Json.createObjectBuilder().build();
        JsonValue object = json.getJsonObject(fetchJson());
        if (addressSearchText != null) {
            JsonPointer pointer = Json.createPointer(text);
            JsonValue result = pointer.getValue(object.asJsonArray());

            // Replace a value
            JsonArray array = (JsonArray) pointer.replace(object.asJsonArray(), Json.createValue("1000 JsonP Drive"));
            searchResult = array.toString();
            //searchResult = result.toString();
        }

    }

    public Customer findById(Integer customerId) {
        return ejbFacade.findByCustomerId(customerId);
    }
}
