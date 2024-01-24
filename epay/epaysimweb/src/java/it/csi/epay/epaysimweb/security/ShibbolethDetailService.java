/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface ShibbolethDetailService {
    public UserDetails caricaUtenteDaIdentita(IdentityDetails identitaIride, String irideAPP)
                    throws UsernameNotFoundException;

}
