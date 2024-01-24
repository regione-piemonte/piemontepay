/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.security;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.csi.epay.epaysimweb.common.config.LogConfig;
import it.csi.epay.epaysimweb.common.exceptions.ClientAuthenticationFailureException;
import it.csi.epay.epaysimweb.integration.facade.impl.EpaycatalogWsFacade;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.EpaypacatalogsrvException_Exception;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.Exception_Exception;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.GetProfilazioneUtenteCorrenteCduOutputDto;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.GetProfilazioneUtenteCorrenteOutput;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.UnrecoverableException_Exception;


/**
 * Classe che si occupa di cercare l'utente il cui CF viene restituito da Iride Carica inoltre i ruoli dell'utente
 *
 *
 * @author lorenzo.fantini
 *
 *
 */
@Service
public class UserDetailsService implements ShibbolethDetailService {

    private static final Logger LOGGER = LoggerFactory.getLogger ( LogConfig.HANDLER_SECURITY );

    @Autowired
    private EpaycatalogWsFacade facade;

    /**
     * Metodo principale di caricamento utente dall'Identita'
     */
    @Override
    public UserDetails caricaUtenteDaIdentita ( IdentityDetails identityDetails, String irideAPP ) throws UsernameNotFoundException {

        LOGGER.debug (
            "[UserDetailsService::caricaUtenteDaIdentita] loadUserIdentity::" + ( identityDetails != null ? identityDetails.getIdentity () : "null" ) );

        GetProfilazioneUtenteCorrenteOutput infoUtente = null;

        try {
            infoUtente = facade.getProfilazioneUtenteCorrente ( identityDetails.getCodiceUtente (), identityDetails.getCodiceEnte () );

            if ( !infoUtente.getCodiceEsito ().equals ( "OK" ) ) {
                throw new BadCredentialsException ( infoUtente.getDescrizioneEsito () );
            }
        } catch ( Exception_Exception | EpaypacatalogsrvException_Exception | UnrecoverableException_Exception e ) {
            throw new ClientAuthenticationFailureException ( e.getMessage (), e );
        }

        List<GrantedAuthority> auths = new ArrayList<> ();

        auths.add ( new EpaycatalogGrantedAuthority ( "ROLE_UTENTE", "Accesso all'applicativo", 1 ) );
        auths.add ( new EpaycatalogGrantedAuthority ( "UTENTE", "Accesso all'applicativo", 1 ) );

        if ( infoUtente.getEnte ().getIdUtenteAmministratore () != null
                        && infoUtente.getEnte ().getIdUtenteAmministratore ().equals ( infoUtente.getUtente ().getId () ) ) {
            auths.add ( new EpaycatalogGrantedAuthority ( "ROLE_EDIT_OWN_ENTE", "Modifica l'ente di appartenenza", 1 ) );
            auths.add ( new EpaycatalogGrantedAuthority ( "EDIT_OWN_ENTE", "Modifica l'ente di appartenenza", 1 ) );
        }

        if ( infoUtente.getUtente ().getCdu () != null ) {

            for ( GetProfilazioneUtenteCorrenteCduOutputDto o: infoUtente.getUtente ().getCdu () ) {
                auths.add ( new EpaycatalogGrantedAuthority ( o.getCodice (), o.getDescrizione (), 1 ) );
            }
        }

        UserDetails result = new UserDetails (
            infoUtente.getUtente ().getCodiceFiscale (),
            "pwd-usr-" + infoUtente.getUtente ().getId (),
            true,
            true,
            true,
            true,
            auths );

        UserDetailsInfoUtente utente = new UserDetailsInfoUtente ();
        UserDetailsInfoEnte ente = new UserDetailsInfoEnte ();

        try {
            PropertyUtils.copyProperties ( utente, infoUtente.getUtente () );
            PropertyUtils.copyProperties ( ente, infoUtente.getEnte () );
        } catch ( IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
            throw new ClientAuthenticationFailureException ( "errore in fase di profilazione", e );
        }

        result.setUtente ( utente );
        result.setEnte ( ente );

        return result;
    }

}
