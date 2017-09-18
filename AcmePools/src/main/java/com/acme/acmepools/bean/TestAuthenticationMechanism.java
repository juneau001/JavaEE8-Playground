/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.acmepools.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.security.auth.message.AuthException;
import javax.security.auth.message.AuthStatus;
import javax.security.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.authentication.mechanism.http.HttpMessageContext;
import javax.security.identitystore.CredentialValidationResult;
import static javax.security.identitystore.CredentialValidationResult.Status.VALID;
import javax.security.identitystore.IdentityStore;
import javax.security.identitystore.credential.Password;
import javax.security.identitystore.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Juneau
 */
@RequestScoped
public class TestAuthenticationMechanism implements HttpAuthenticationMechanism {
     
   // @Inject
    private IdentityStore identityStore;
 
    @Override
    public AuthStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext) throws AuthException {
 
        if (request.getParameter("name") == null || request.getParameter("password") == null) {
            return httpMessageContext.doNothing();
        }
 
        String name = request.getParameter("name");
        Password password = new Password(request.getParameter("password"));
 
        CredentialValidationResult result = identityStore.validate(
            new UsernamePasswordCredential(name, password));
 
        if (result.getStatus() != VALID) {
            throw new AuthException("Login failed");
        }
 
        return httpMessageContext.notifyContainerAboutLogin(
            result.getCallerPrincipal(), 
            result.getCallerGroups());
    }
}