/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.acmepools.converter;

import com.acme.acmepools.entity.DiscountCode;
import com.acme.acmepools.jsf.DiscountCodeController;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class DiscountCodeConverter implements Converter
{
    @Inject
    DiscountCodeController discountCodeController;

    @Override
    public DiscountCode getAsObject(FacesContext context, UIComponent component, String value)
    {
        System.out.println(value);
        DiscountCode dc = null;
        try {
            dc = discountCodeController.obtainByCode(value);
        } catch (NullPointerException ex){
            System.out.println(ex);
        }
        
        return dc;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
    {
        return value.toString();
    }

}
