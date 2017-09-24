/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.acmepools.converter;

import com.acme.acmepools.entity.DiscountCode;
import com.acme.acmepools.entity.MicroMarket;
import com.acme.acmepools.jsf.MicroMarketController;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ZipCodeConverter implements Converter
{
    @Inject
    MicroMarketController microMarketController;

    @Override
    public MicroMarket getAsObject(FacesContext context, UIComponent component, String value)
    {
        System.out.println(value);
        MicroMarket mm = null;
        try {
            mm = microMarketController.obtainByZipCode(value);
        } catch (NullPointerException ex){
            System.out.println(ex);
        }
        
        return mm;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
    {
        return value.toString();
    }

}
