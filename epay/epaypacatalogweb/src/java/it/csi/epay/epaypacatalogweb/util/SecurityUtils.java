/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.util;

import org.springframework.security.core.context.SecurityContextHolder;

import it.csi.epay.epaypacatalogweb.security.UserDetails;

/**
 * Classe statica helper
 * @author lorenzo.fantini
 */
public class SecurityUtils {
	
	public static UserDetails getUser() {
		return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	public static org.springframework.security.core.Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

}
