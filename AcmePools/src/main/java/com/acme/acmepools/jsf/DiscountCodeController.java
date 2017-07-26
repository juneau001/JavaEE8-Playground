/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.acmepools.jsf;

import com.acme.acmepools.entity.DiscountCode;
import com.acme.acmepools.session.DiscountCodeFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Juneau
 */
@Named
@RequestScoped
@NoArgsConstructor
public class DiscountCodeController {
    
    @EJB
    private DiscountCodeFacade discountCodeFacade;
    
    @Setter
    private List<DiscountCode> itemsAvailableSelectOne;
    
    public List<DiscountCode> getItemsAvailableSelectOne(){
        itemsAvailableSelectOne = discountCodeFacade.findAll();
        return itemsAvailableSelectOne;
    }
    
    public DiscountCode obtainById(DiscountCode code){
        return discountCodeFacade.findByCode(code.getDiscountCode());
    }
    
    public DiscountCode obtainByCode(String code) {
        return discountCodeFacade.findByCode(code);
    }
    
}
