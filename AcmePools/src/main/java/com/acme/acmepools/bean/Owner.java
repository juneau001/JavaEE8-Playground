/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.acmepools.bean;

import java.util.Collection;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

/**
 *
 * @author Juneau
 */
@Builder
@Data
public class Owner {
    private String firstName;
    private String lastName;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    @Singular private Collection<String> phoneNumbers;
  
}
