/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.security;


import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserDetails extends User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6480560073946353904L;

	private UserDetailsInfoUtente utente;
	private UserDetailsInfoEnte ente;
	private List<UserDetailsCodiciVersamento> codiciVersamento;
	
	
    public UserDetails(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	public UserDetailsInfoUtente getUtente() {
		return utente;
	}

	public void setUtente(UserDetailsInfoUtente utente) {
		this.utente = utente;
	}

	public UserDetailsInfoEnte getEnte() {
		return ente;
	}

	public void setEnte(UserDetailsInfoEnte ente) {
		this.ente = ente;
	}

    public List<UserDetailsCodiciVersamento> getCodiciVersamento () {
        return codiciVersamento;
    }

    public void setCodiciVersamento ( List<UserDetailsCodiciVersamento> codiciVersamento ) {
        this.codiciVersamento = codiciVersamento;
    }
}
