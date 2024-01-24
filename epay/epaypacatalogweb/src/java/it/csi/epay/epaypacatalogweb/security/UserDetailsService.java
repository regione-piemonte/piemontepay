/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.security;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import it.csi.epay.epaypacatalogweb.common.Constants;
import it.csi.epay.epaypacatalogweb.common.config.LogConfig;
import it.csi.epay.epaypacatalogweb.common.exceptions.ClientAuthenticationFailureException;
import it.csi.epay.epaypacatalogweb.common.exceptions.EnteNonPresenteException;
import it.csi.epay.epaypacatalogweb.integration.facade.impl.EpaypacatalogsrvFacade;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.EpaypacatalogsrvException_Exception;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.Exception_Exception;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetProfilazioneUtenteCorrenteCduOutputDto;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetProfilazioneUtenteCorrenteCodiceVersamentoOutputDto;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetProfilazioneUtenteCorrenteOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetProfilazioneUtenteCorrenteTematicaOutputDto;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.UnrecoverableException_Exception;


/**
 * Classe che si occupa di cercare l'utente il cui CF viene restituito da Iride
 *
 * Carica inoltre i ruoli dell'utente
 *
 *
 * @author lorenzo.fantini
 *
 *
 */
@Service
public class UserDetailsService implements ShibbolethDetailService {

    private Logger logger = LoggerFactory.getLogger ( LogConfig.HANDLER_SECURITY );

    @Autowired
    private EpaypacatalogsrvFacade facade;

    /**
     * Metodo principale di caricamento utente dall'Identita'
     */
    @Override
    public UserDetails caricaUtenteDaIdentita ( IdentityDetails identityDetails, String irideAPP ) throws UsernameNotFoundException {

        logger.debug (
            "[UserDetailsService::caricaUtenteDaIdentita] loadUserIdentity::" + ( identityDetails != null ? identityDetails.getIdentity () : "null" ) );

        if ( identityDetails == null || StringUtils.isBlank ( identityDetails.getCodiceEnte () ) ) {
            AuthenticationServiceException e = new AuthenticationServiceException ( "Codice ente non presente", new EnteNonPresenteException () );

            if ( RequestContextHolder.getRequestAttributes () != null ) {
                HttpServletRequest request = ( (ServletRequestAttributes) RequestContextHolder.getRequestAttributes () ).getRequest ();
                request.getSession ().setAttribute ( "LOGIN_FAILED_CAUSE", EnteNonPresenteException.class.getName () );
                request.setAttribute ( "LOGIN_FAILED_CAUSE", EnteNonPresenteException.class.getName () );
            }

            throw e;
        }

        GetProfilazioneUtenteCorrenteOutput infoUtente = getProfilazioneUtenteDaServizio ( identityDetails );

        UserDetails mapped = map ( infoUtente );
        mapped.setIdentitaOriginale ( identityDetails.getIdentity () );
        return mapped;
    }

    private GetProfilazioneUtenteCorrenteOutput getProfilazioneUtenteDaServizio ( IdentityDetails identityDetails ) {

        GetProfilazioneUtenteCorrenteOutput infoUtente = null;

        try {
            infoUtente = facade.getProfilazioneUtenteCorrente ( identityDetails.getCodiceUtente (), identityDetails.getCodiceEnte () );
        } catch ( Exception_Exception | EpaypacatalogsrvException_Exception | UnrecoverableException_Exception e ) {
            throw new AuthenticationServiceException ( e.getMessage (), e );
        }

        if ( !infoUtente.getCodiceEsito ().equals ( "OK" ) ) {
            throw new BadCredentialsException ( infoUtente.getDescrizioneEsito () );
        }

        return infoUtente;
    }
    
    //Codice provvisorio per richiamare il servizio rest 
//  private it.csi.epay.epaypacatalogweb.integration.rs.GetProfilazioneUtenteCorrenteOutput getProfilazioneUtenteDaServizio ( IdentityDetails identityDetails ) {
//
//      it.csi.epay.epaypacatalogweb.integration.rs.GetProfilazioneUtenteCorrenteOutput infoUtente = null;
//      EpaypacatalogsrvRsClient client = new EpaypacatalogsrvRsClient("http://localhost:8080/epaypacatalogsrvApplEpaypacatalogsrvWs/api/getProfilazioneUtenteCorrente");
//      
//      it.csi.epay.epaypacatalogweb.integration.rs.GetProfilazioneUtenteCorrenteInput getInfoUtenteInput = new it.csi.epay.epaypacatalogweb.integration.rs.GetProfilazioneUtenteCorrenteInput ();
//
//      it.csi.epay.epaypacatalogweb.integration.rs.CallerInputDto caller = new it.csi.epay.epaypacatalogweb.integration.rs.CallerInputDto();
//      caller.setCodiceApplicativo("EPAYPACATALOG");
//      it.csi.epay.epaypacatalogweb.integration.rs.PrincipalInputDto principal = new it.csi.epay.epaypacatalogweb.integration.rs.PrincipalInputDto();
//      principal.setCodiceEnte(identityDetails.getCodiceEnte());
//      principal.setCodiceFiscale(identityDetails.getCodiceUtente());
//      caller.setPrincipal(principal);
//      getInfoUtenteInput.setCaller(caller);
//
//      if ( RequestContextHolder.getRequestAttributes () != null ) {
//          HttpServletRequest request = ( (ServletRequestAttributes) RequestContextHolder.getRequestAttributes () ).getRequest ();
//          if ( request != null ) {
//              caller.setIp ( request.getRemoteAddr () );
//          }
//      }
//
//      try {
//      	  ObjectMapper mapper = new ObjectMapper();
//      	InputStream is= client.getService(getInfoUtenteInput);
//          infoUtente = mapper.readValue(is, it.csi.epay.epaypacatalogweb.integration.rs.GetProfilazioneUtenteCorrenteOutput.class);
//      } catch ( IOException e) {
//          throw new AuthenticationServiceException ( e.getMessage (), e );
//      }
//
//      if ( !infoUtente.getCodiceEsito ().equals ( "OK" ) ) {
//          throw new BadCredentialsException ( infoUtente.getDescrizioneEsito () );
//      }
//
//      return infoUtente;
//  }


    private UserDetails map ( GetProfilazioneUtenteCorrenteOutput infoUtente ) {

        List<GrantedAuthority> auths = new ArrayList<> ();

        auths.add ( new EpaypacatalogwebGrantedAuthority ( "UTENTE", "Accesso all'applicativo", 1 ) );

        if ( infoUtente.getUtente ().getCdu () != null ) {

            for ( GetProfilazioneUtenteCorrenteCduOutputDto o: infoUtente.getUtente ().getCdu () ) {
                auths.add ( new EpaypacatalogwebGrantedAuthority ( o.getCodice (), o.getDescrizione (), 1 ) );
            }
        }

        List<GrantedAuthority> toAdd = new ArrayList<> ();
        for ( GrantedAuthority auth: auths ) {
            if ( !auth.getAuthority ().startsWith ( "ROLE_" ) ) {
                EpaypacatalogwebGrantedAuthority a = (EpaypacatalogwebGrantedAuthority) auth;
                toAdd.add ( new EpaypacatalogwebGrantedAuthority ( "ROLE_" + a.getCodice (), a.getDescrizione (), a.getTipoAuthority () ) );
            }
        }
        auths.addAll ( toAdd );

        UserDetails result = new UserDetails (
            /* username */ infoUtente.getUtente ().getCodiceFiscale (),
            /* password */ "pwd-usr-" + infoUtente.getUtente ().getId (),
            /* enabled */ isEnabled ( infoUtente ),
            /* accountNonExpired */ isAccountNonExpired ( infoUtente ),
            /* credentialsNonExpired */ isCredentialsNonExpired ( infoUtente ),
            /* accountNonLocked */ isAccountNonLocked ( infoUtente ),
            /* authorities */ auths );

        UserDetailsInfoUtente utente = new UserDetailsInfoUtente ();
        UserDetailsInfoEnte ente = new UserDetailsInfoEnte ();

        List<UserDetailsInfoTematica> tematiche = new ArrayList<> ();

        try {
            PropertyUtils.copyProperties ( utente, infoUtente.getUtente () );
            PropertyUtils.copyProperties ( ente, infoUtente.getEnte () );
        } catch ( IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
            throw new ClientAuthenticationFailureException ( "errore in fase di profilazione: " + e.getMessage (), e );
        }

        if ( infoUtente.getTematiche () != null ) {
            for ( GetProfilazioneUtenteCorrenteTematicaOutputDto tematica: infoUtente.getTematiche () ) {
                UserDetailsInfoTematica dtoTematica = new UserDetailsInfoTematica ();
                dtoTematica.setId ( tematica.getId () );
                dtoTematica.setCodice ( tematica.getCodice () );
                dtoTematica.setDescrizione ( tematica.getDescrizione () );
                dtoTematica.setFlagVisibilitaTotale ( tematica.isFlagVisibilitaTotale () );
                dtoTematica.setCodiciVersamento ( new ArrayList<> () );
                if ( tematica.getCodiciVersamento () != null ) {
                    for ( GetProfilazioneUtenteCorrenteCodiceVersamentoOutputDto cv: tematica.getCodiciVersamento () ) {
                        dtoTematica.getCodiciVersamento ().add ( cv.getId () );
                    }
                }
                tematiche.add ( dtoTematica );
            }
        }

        result.setTematiche ( tematiche );
        result.setUtente ( utente );
        result.setEnte ( ente );

        return result;
    }

    private boolean isEnabled ( GetProfilazioneUtenteCorrenteOutput infoUtente ) {

        for ( GetProfilazioneUtenteCorrenteCduOutputDto auth: infoUtente.getUtente ().getCdu () ) {
            if ( auth.getCodice ().equals ( Constants.USE_CASES.LOGIN ) ) {
                return true;
            }
        }

        return false;
    }

    private boolean isAccountNonExpired ( GetProfilazioneUtenteCorrenteOutput infoUtente ) {

        if ( infoUtente.getUtente ().getDataInizioValidita () == null && infoUtente.getUtente ().getDataInizioValiditaCurrent () == null ) {
            return false;
        }

        Date now = new Date ();
        Date dataInizioTrasversale = null != infoUtente.getUtente ().getDataInizioValidita ()
                        ? infoUtente.getUtente ().getDataInizioValidita ().toGregorianCalendar ().getTime () : null;
        Date dataInizioCurrent = null != infoUtente.getUtente ().getDataInizioValiditaCurrent ()
                        ? infoUtente.getUtente ().getDataInizioValiditaCurrent ().toGregorianCalendar ().getTime () : null;

        if ( (null!= dataInizioTrasversale && dataInizioTrasversale.after ( now )) || (null!= dataInizioCurrent && dataInizioCurrent.after ( now )) ) {
            return false;
        }

        if ( infoUtente.getUtente ().getDataFineValidita () == null && infoUtente.getUtente ().getDataFineValiditaCurrent () == null ) {
            return true;
        }

        Date dataFineTrasversale = null!= infoUtente.getUtente ().getDataFineValidita () ? infoUtente.getUtente ().getDataFineValidita ().toGregorianCalendar ().getTime () : null;
        Date dataFineCurrent = null!= infoUtente.getUtente ().getDataFineValiditaCurrent () ? infoUtente.getUtente ().getDataFineValiditaCurrent ().toGregorianCalendar ().getTime () : null ;

        if ( (null!= dataFineTrasversale && dataFineTrasversale.after ( now )) || (null!= dataFineCurrent && dataFineCurrent.after ( now )) ) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isCredentialsNonExpired ( GetProfilazioneUtenteCorrenteOutput infoUtente ) {

        return true;
    }

    private boolean isAccountNonLocked ( GetProfilazioneUtenteCorrenteOutput infoUtente ) {

        return true;
    }

}
