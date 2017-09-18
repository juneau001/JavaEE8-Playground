package com.acme.acmepools.bean;

/*
 * Copyright 2016 Rudy De Busscher (www.c4j.be)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */


import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;


//@CustomFormAuthenticationMechanismDefinition(
//        loginToContinue = @LoginToContinue(
//                loginPage="/login.xhtml",
//                errorPage="" // DRAFT API - must be set to empty for now
//        )
//)
//
//@LdapIdentityStoreDefinition(
//        url = "ldap://www.zflexldap.com/",
//        baseDn = "cn=ro_admin,ou=sysadmins,dc=zflexsoftware,dc=com",
//        password = "zflexpass",
//        searchBase = "ou=developers,dc=zflexsoftware,dc=com",
//        searchExpression = "(&(uid=%s)(objectClass=person))",
//        groupBaseDn = "ou=groups,ou=developers,dc=zflexsoftware,dc=com"
//)

@Named
@RequestScoped
public class LoginBean {
//
//    @Inject
//    private SecurityContext securityContext;

    @NotNull
    @Size(min = 3, max = 15, message = "Username must be between 3 and 15 characters")
    private String username;

    @NotNull
    @Size(min = 5, max = 50, message = "Password must be between 5 and 50 characters")
    private String password;

    public void login() {

        FacesContext context = FacesContext.getCurrentInstance();
        Credential credential = new UsernamePasswordCredential(username, new Password(password));

//        // Request for authentication
//        AuthStatus status = securityContext.authenticate(
//                getResponse(context),
//                withParams().credential(credential));

//        if (status.equals(SEND_CONTINUE)) {
//            // Authentication mechanism has send a redirect, should not
//            // send anything to response from JSF now.
//            context.responseComplete();
//        } else if (status.equals(SEND_FAILURE) || status.equals(FAILURE)) {
//            // TODO: only 1 status should be returned. Change to enum or fix source.
//            addError(context, "Authentication failed");
//        }

    }

    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = getRequest(context);
        try {
            // Logout current request
            request.logout();
            // Invalidate session so user becomes anonymous.
            request.getSession().invalidate();
        } catch (ServletException e) {
            addError(context, e.getMessage());
        }

    }

    private static HttpServletResponse getResponse(FacesContext context) {
        return (HttpServletResponse) context
                .getExternalContext()
                .getResponse();
    }

    private static HttpServletRequest getRequest(FacesContext context) {
        return (HttpServletRequest) context
                .getExternalContext()
                .getRequest();
    }

    private static void addError(FacesContext context, String message) {
        context.addMessage(null,
                new FacesMessage(SEVERITY_ERROR, message, null));
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}