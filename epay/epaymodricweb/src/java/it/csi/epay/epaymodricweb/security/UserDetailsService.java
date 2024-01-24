/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.security;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import it.csi.epay.epaymodricweb.common.Constants;
import it.csi.epay.epaymodricweb.common.config.LogConfig;
import it.csi.epay.epaymodricweb.common.exceptions.ClientAuthenticationFailureException;
import it.csi.epay.epaymodricweb.integration.facade.EpaymodricWsFacade;
import it.csi.epay.epaymodricweb.integration.facade.EpaypacatalogsrvFacade;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoCaller;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoCodiceVersamento;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoInputWsRicercaCodiciVersamento;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputWsCodiciVersamento;
import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoPrincipal;
import it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv.EpaypacatalogsrvException_Exception;
import it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv.Exception_Exception;
import it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv.GetProfilazioneUtenteCorrenteCduOutputDto;
import it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv.GetProfilazioneUtenteCorrenteCodiceVersamentoOutputDto;
import it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv.GetProfilazioneUtenteCorrenteOutput;
import it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv.GetProfilazioneUtenteCorrenteTematicaOutputDto;
import it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv.UnrecoverableException_Exception;

/**
 * Classe che si occupa di cercare l'utente il cui CF viene restituito da Iride
 * Carica inoltre i ruoli dell'utente
 *
 * @author lorenzo.fantini
 *
 *
 */
@Service
public class UserDetailsService implements ShibbolethDetailService {


    private Logger logger = LoggerFactory.getLogger(LogConfig.HANDLER_SECURITY);

    @Autowired
    private EpaypacatalogsrvFacade facade;

    @Autowired
    private EpaymodricWsFacade epaymodricWsFacade;

    /**
     * Metodo principale di caricamento utente dall'Identita'
     */
    @Override
    public UserDetails caricaUtenteDaIdentita(IdentityDetails identityDetails, String irideAPP) throws UsernameNotFoundException {

        logger.debug("[UserDetailsService::caricaUtenteDaIdentita] loadUserIdentity::" + (identityDetails != null ? identityDetails.getIdentity() : "null"));

        GetProfilazioneUtenteCorrenteOutput infoUtente = null;

        try {
            infoUtente = facade.getProfilazioneUtenteCorrente ( identityDetails.getCodiceUtente (), identityDetails.getCodiceEnte (),
                identityDetails.getCodiceRuolo () );

            if ( !"OK".equals ( infoUtente.getCodiceEsito () ) ) {
                throw new BadCredentialsException ( infoUtente.getDescrizioneEsito () );
            }
        } catch (Exception_Exception | EpaypacatalogsrvException_Exception | UnrecoverableException_Exception e) {
            throw new ClientAuthenticationFailureException(e.getMessage(), e);
        }

        List<GrantedAuthority> auths = new ArrayList<>();

        auths.add(new EpaymodricwebGrantedAuthority("ROLE_UTENTE", "Accesso all'applicativo", 3));
        auths.add(new EpaymodricwebGrantedAuthority("UTENTE", "Accesso all'applicativo", 3));

        /*
         * da ultime specifiche, l'assegnazione dei CDU non  pi legata al ruolo ma direttamente all'utente corrente. l'output del servizio ritorna sia i cdu
         * assegnati al ruolo che i cdu assegnati all'utente da oggi  necessario considerare i cdu legati all'utente
         */

        if ( infoUtente.getUtente ().getCdu () != null ) {

            for ( GetProfilazioneUtenteCorrenteCduOutputDto o: infoUtente.getUtente ().getCdu () ) {
                auths.add ( new EpaymodricwebGrantedAuthority ( o.getCodice (), o.getDescrizione (), 1 ) );
            }
        }

        UserDetails result = new UserDetails (
            /* username */ infoUtente.getUtente ().getCodiceFiscale (),
            /* password */ "pwd-usr-" + infoUtente.getUtente ().getId (),
            /* enabled */ isEnabled ( infoUtente ),
            /* accountNonExpired */ isAccountNonExpired ( infoUtente ),
            /* credentialsNonExpired */ isCredentialsNonExpired ( infoUtente ),
            /* accountNonLocked */ isAccountNonLocked ( infoUtente ),
            /* authorities */ auths );

        UserDetailsInfoUtente utente = new UserDetailsInfoUtente();
        UserDetailsInfoEnte ente = new UserDetailsInfoEnte();

        try {
            PropertyUtils.copyProperties(utente, infoUtente.getUtente());
            PropertyUtils.copyProperties(ente, infoUtente.getEnte());
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new ClientAuthenticationFailureException("errore in fase di profilazione", e);
        }

        //gestione codici versamento
        try {
            if ( !CollectionUtils.isEmpty ( infoUtente.getTematiche () ) ) {
                LinkedList<String> codVerListCatalog = infoUtente.getTematiche ()
                    .stream ()
                    .map ( new Function<GetProfilazioneUtenteCorrenteTematicaOutputDto, List<GetProfilazioneUtenteCorrenteCodiceVersamentoOutputDto>> () {

                        @Override
                        public List<GetProfilazioneUtenteCorrenteCodiceVersamentoOutputDto> apply ( GetProfilazioneUtenteCorrenteTematicaOutputDto tematica ) {
                            return tematica.getCodiciVersamento ();
                        }
                    } ).flatMap ( new Function<List<GetProfilazioneUtenteCorrenteCodiceVersamentoOutputDto>,
                                    Stream<? extends GetProfilazioneUtenteCorrenteCodiceVersamentoOutputDto>> () {

                        @Override
                        public Stream<? extends GetProfilazioneUtenteCorrenteCodiceVersamentoOutputDto>
                            apply ( List<GetProfilazioneUtenteCorrenteCodiceVersamentoOutputDto> codVer ) {
                            return codVer.stream ();
                        }
                    } )
                    .map ( new Function<GetProfilazioneUtenteCorrenteCodiceVersamentoOutputDto, String> () {

                        @Override
                        public String apply ( GetProfilazioneUtenteCorrenteCodiceVersamentoOutputDto codVers ) {
                            return codVers.getCodice ();
                        }
                    } )
                    .distinct ()
                    .collect ( Collectors.toCollection ( new Supplier<LinkedList<String>> () {

                        @Override
                        public LinkedList<String> get () {
                            return new LinkedList<> ();
                        }
                    } ) );
                DtoInputWsRicercaCodiciVersamento input = new DtoInputWsRicercaCodiciVersamento ();
                input.getCodiciVersamento ().addAll ( codVerListCatalog );
                
                DtoCaller caller = new DtoCaller ();
                caller.setCodiceApplicativo ( "EPAYMODRICWEB" );
                if ( null != ente && null!= utente ) {
                    DtoPrincipal principal = new DtoPrincipal ();
                    principal.setCodiceEnte (ente.getCodiceFiscale () );
                    principal.setCodiceFiscale ( utente.getCodiceFiscale () );
                    caller.setPrincipal ( principal );
                }
                input.setCaller ( caller );
               
                DtoOutputWsCodiciVersamento codVersModric = epaymodricWsFacade.ricercaCodiciVersamentoPerCodice ( input );
                if ( null != codVersModric && !CollectionUtils.isEmpty ( codVersModric.getCodiciVersamento () ) ) {
                    result.setCodiciVersamento (
                        codVersModric.getCodiciVersamento ()
                            .stream ()
                            .map ( new Function<DtoCodiceVersamento, UserDetailsCodiciVersamento> () {

                                @Override
                                public UserDetailsCodiciVersamento apply ( DtoCodiceVersamento x ) {
                                    return UserDetailsCodiciVersamento.builder ()
                                        .withId ( x.getId () )
                                        .withCodice ( x.getCodice () )
                                        .withDenominazione ( x.getCodice () + " - " + x.getDenominazione () )
                                        .build ();
                                }
                            } ).collect ( Collectors.toCollection ( new Supplier<LinkedList<UserDetailsCodiciVersamento>> () {

                                @Override
                                public LinkedList<UserDetailsCodiciVersamento> get () {
                                    return new LinkedList<> ();
                                }
                            } ) ) );
                }
            }
        } catch ( Exception e ) {
            logger.error ( "Errore in fase di reperimento dei codici versamento", e );
        }
        
        result.setUtente(utente);
        result.setEnte(ente);

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

        if ( infoUtente.getUtente ().getDataInizioValidita () == null ) {
            return false;
        }

        Date now = new Date ();
        Date dataInizio = infoUtente.getUtente ().getDataInizioValidita ().toGregorianCalendar ().getTime ();

        if ( dataInizio.after ( now ) ) {
            return false;
        }

        if ( infoUtente.getUtente ().getDataFineValidita () == null ) {
            return true;
        }

        Date dataFine = infoUtente.getUtente ().getDataFineValidita ().toGregorianCalendar ().getTime ();

        if ( dataFine.after ( now ) ) {
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
