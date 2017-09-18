package com.acme.acmepools.jsf;

import com.acme.acmepools.entity.Customer;
import com.acme.acmepools.entity.Job;
import com.acme.acmepools.entity.PoolCustomer;
import com.acme.acmepools.session.JobFacade;
import com.acme.acmepools.entity.util.JsfUtil;
import com.acme.acmepools.entity.util.JsfUtil.PersistAction;
import com.acme.acmepools.event.JobEvent;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@Named("jobController")
@SessionScoped
public class JobController implements Serializable {

    @EJB
    private com.acme.acmepools.session.JobFacade ejbFacade;
    @EJB
    private com.acme.acmepools.session.PoolCustomerFacade poolCustomerFacade;
    
    private List<Job> items = null;
    private List<Job> jobsByCustomer = null;
    private List<Job> jobsByPool = null;
    private Job selected;

    @Inject
    private Event<JobEvent> jobEvent;

    public JobController() {
    }

    public Job getSelected() {
        return selected;
    }
    
    public void populateRoundJobs(){
        this.jobsByPool = ejbFacade.findByCustPoolShape("ROUND");
        System.out.println("There are " + jobsByPool.size() + " round pool jobs");
    }
    
    public void findByCustomer(Customer customer){
        List<PoolCustomer> poolCustomerObjects = poolCustomerFacade.findByCustomerId(customer);
        jobsByCustomer = new ArrayList();
        for(PoolCustomer pCust:poolCustomerObjects){
            List<Job> customerJobs = ejbFacade.findByCustomer(pCust);
            jobsByCustomer.addAll(customerJobs);
        }
    }

    public void setSelected(Job selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private JobFacade getFacade() {
        return ejbFacade;
    }

    public Job prepareCreate() {
        selected = new Job();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("JobCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("JobUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("JobDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Job> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
    public List<Job> getJobsByCustomer(){
        return jobsByCustomer;
    }
    
    public void setJobsByCustomer(List<Job> jobs){
        this.jobsByCustomer = jobs;
    }
    
    public List<Job> getJobsByPool(){
        if(this.jobsByPool == null){
            populateRoundJobs();
        }
        return jobsByPool;
    }
    
    public void setJobsByPool(List<Job> jobs){
        this.jobsByPool = jobs;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                    if (persistAction == PersistAction.CREATE) {
                        jobEvent.fireAsync(new JobEvent("New job added", selected))
                                .whenComplete((event, throwable) -> {
                                    if (throwable != null) {
                                        System.out.println("Error has occurred: " + throwable.getMessage());
                                    } else {
                                        System.out.println("Successful Job Processing...");
                                    }
                                });
                    }
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

    public Job getJob(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Job> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Job> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Job.class)
    public static class JobControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            JobController controller = (JobController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "jobController");
            return controller.getJob(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(BigDecimal value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Job) {
                Job o = (Job) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Job.class.getName()});
                return null;
            }
        }

    }

}
