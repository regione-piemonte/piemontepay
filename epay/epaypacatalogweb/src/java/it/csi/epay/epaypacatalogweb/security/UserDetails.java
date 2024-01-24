/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.security;

import java.security.InvalidParameterException;
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

    private List<UserDetailsInfoTematica> tematiche;

    private String identitaOriginale;

    public UserDetailsInfoTematica cercaAssociazioneTematica ( String codice ) {
        if ( codice == null || codice.isEmpty () ) {
            throw new InvalidParameterException ();
        }

        if ( tematiche != null ) {
            for ( UserDetailsInfoTematica t: tematiche ) {
                if ( t.getCodice ().equals ( codice ) ) {
                    return t;
                }
            }
        }

        return null;
    }

    public boolean haVisibilitaTotaleSuTematica ( String codice ) {

        UserDetailsInfoTematica assoc = cercaAssociazioneTematica ( codice );
        if ( assoc == null ) {
            return false;
        }
        if ( assoc.getFlagVisibilitaTotale () == null || !assoc.getFlagVisibilitaTotale () ) {
            return false;
        }

        return true;
    }

    public boolean haVisibilitaParzialeSuTematica ( String codice ) {

        UserDetailsInfoTematica assoc = cercaAssociazioneTematica ( codice );
        if ( assoc == null ) {
            return false;
        }

        return true;
    }

    public boolean haVisibilitaSuCodiceVersamento ( Long idCodiceVersamento, String codiceTematica ) {

        UserDetailsInfoTematica assoc = cercaAssociazioneTematica ( codiceTematica );
        if ( assoc == null ) {
            return false;
        }

        if ( assoc.getFlagVisibilitaTotale () == null || !assoc.getFlagVisibilitaTotale () ) {
            if ( assoc.getCodiciVersamento () != null ) {
                for ( Long cva: assoc.getCodiciVersamento () ) {
                    if ( cva.equals ( idCodiceVersamento ) ) {
                        return true;
                    }
                }
            }
            return false;
        }

        return true;
    }

    public UserDetails ( String username, String password, boolean enabled, boolean accountNonExpired,
        boolean credentialsNonExpired, boolean accountNonLocked,
        Collection<? extends GrantedAuthority> authorities ) {
        super ( username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities );
    }

    public String getIdentitaOriginale () {
        return identitaOriginale;
    }

    public void setIdentitaOriginale ( String identitaOriginale ) {
        this.identitaOriginale = identitaOriginale;
    }

    public List<UserDetailsInfoTematica> getTematiche () {
        return tematiche;
    }

    public void setTematiche ( List<UserDetailsInfoTematica> tematiche ) {
        this.tematiche = tematiche;
    }

    public UserDetailsInfoUtente getUtente () {
        return utente;
    }

    public void setUtente ( UserDetailsInfoUtente utente ) {
        this.utente = utente;
    }

    public UserDetailsInfoEnte getEnte () {
        return ente;
    }

    public void setEnte ( UserDetailsInfoEnte ente ) {
        this.ente = ente;
    }

}
