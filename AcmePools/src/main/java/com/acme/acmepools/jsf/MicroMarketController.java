/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.acmepools.jsf;

import com.acme.acmepools.entity.MicroMarket;
import com.acme.acmepools.session.MicroMarketFacade;
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
public class MicroMarketController {
    
    @EJB
    private MicroMarketFacade microMarketFacade;
    
    @Setter
    private List<MicroMarket> itemsAvailableSelectOne;
    
   
    public List<MicroMarket> getItemsAvailableSelectOne(){
        itemsAvailableSelectOne = microMarketFacade.findAll();
        return itemsAvailableSelectOne;
    }
    
    public MicroMarket obtainById(MicroMarket microMarket){
        return microMarketFacade.find(microMarket);
    }
    
    public MicroMarket obtainByZipCode(String zip){
        return microMarketFacade.findByZipCode(zip);
    }
}
