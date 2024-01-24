/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.security;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

/**
 * 
 * @author lorenzo.fantini Classe che effettua fisicamente la pre autenticazione
 *         su Shibboleth e passa il controllo a Spring MVC Da loadUserDetails,
 *         nel caso in cui l'utente venga riconosciuto, uscir un utente
 *         valorizzato con nome, cognome, cf e ruoli
 */
public class ShibbolethUserDetailsServiceWrapper implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken>, InitializingBean {

    private ShibbolethDetailService userDetailsService = null;

    private String irideAPP;

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken authentication) throws UsernameNotFoundException {

        IdentityDetails identityDetails = (IdentityDetails) authentication.getPrincipal();

        return this.userDetailsService.caricaUtenteDaIdentita(identityDetails, getIrideAPP());
    }

    public void setUserDetailsService(ShibbolethDetailService aUserDetailsService) {
        this.userDetailsService = aUserDetailsService;
    }

    public String getIrideAPP() {
        return irideAPP;
    }

    public void setIrideAPP(String irideAPP) {
        this.irideAPP = irideAPP;
    }

    public ShibbolethDetailService getUserDetailsService() {
        return userDetailsService;
    }

}
