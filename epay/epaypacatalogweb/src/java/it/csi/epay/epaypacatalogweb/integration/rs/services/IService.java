/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.integration.rs.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import it.csi.epay.epaypacatalogweb.integration.rs.CallerInputDto;
import it.csi.epay.epaypacatalogweb.integration.rs.ParentInput;
import it.csi.epay.epaypacatalogweb.integration.rs.PrincipalInputDto;
import it.csi.epay.epaypacatalogweb.security.UserDetails;
import it.csi.epay.epaypacatalogweb.util.SecurityUtils;

/**
 * Interfaccia per servizi rest
 */
public interface IService {

	

	default void addCallerInfo(ParentInput input) {
        UserDetails user = SecurityUtils.getUser();

        CallerInputDto caller = new CallerInputDto();
        caller.setCodiceApplicativo("EPAYPACATALOGWEB");

        if (user != null) {
            PrincipalInputDto principal = new PrincipalInputDto();
            principal.setCodiceEnte(user.getEnte().getCodiceFiscale());
            principal.setCodiceFiscale(user.getUtente().getCodiceFiscale());
            caller.setPrincipal(principal);
        }

        if ( RequestContextHolder.getRequestAttributes () != null ) {
            HttpServletRequest request = ( (ServletRequestAttributes) RequestContextHolder.getRequestAttributes () ).getRequest ();
            if ( request != null ) {
                caller.setIp ( request.getRemoteAddr () );
            }
        }

        input.setCaller(caller);
    }
    

}
