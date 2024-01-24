/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.integration.facade.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.BindingProvider;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.AggiornaEnteInput;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.AggiornaEnteOutput;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.CallerInputDto;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.Epaypacatalogsrv;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.EpaypacatalogsrvException_Exception;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.EpaypacatalogsrvService;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.Exception_Exception;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.GetCodiceVersamentoInput;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.GetCodiceVersamentoOutput;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.GetEnteInput;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.GetEnteOutput;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.GetMessaggiInput;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.GetMessaggiOutput;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.GetOpzioniCodiceVersamentoInput;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.GetOpzioniCodiceVersamentoOutput;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.GetOpzioniInput;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.GetOpzioniMacrotipoOutput;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.GetOpzioniModalitaAcquisizioneProvvisoriOutput;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.GetOpzioniPeriodicitaSchedulazioneRiconciliazioneOutput;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.GetOpzioniStatoAggiornamentoEnteOutput;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.GetOpzioniTematicaOutput;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.GetOpzioniTipologiaAccertamentoOutput;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.GetProfilazioneUtenteCorrenteInput;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.GetProfilazioneUtenteCorrenteOutput;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.InserisciCodiceVersamentoInput;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.InserisciCodiceVersamentoOutput;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.ParentInput;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.PrincipalInputDto;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.RicercaCodiceVersamentoInput;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.RicercaCodiceVersamentoOutput;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.RicercaVoceEntrataInput;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.RicercaVoceEntrataOutput;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.TestResourcesInput;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.TestResourcesOutput;
import it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv.UnrecoverableException_Exception;
import it.csi.epay.epaysimweb.security.UserDetails;
import it.csi.epay.epaysimweb.util.SecurityUtils;


@Service
public class EpaycatalogWsFacade extends ParentFacade {

    private static final String EPAYCATALOG = "EPAYSIMWEB";

    private EpaypacatalogsrvService service;

    private Epaypacatalogsrv port;

    public EpaycatalogWsFacade () {
        super ();
        service = null;
        port = null;
    }

    private Epaypacatalogsrv getPort () {
        if ( service == null ) {
            try {
                String endPointUrl = java.util.ResourceBundle.getBundle ( "config" ).getString ( "service.epaycatalogws.endpoint" );

                try {
                    service = new EpaypacatalogsrvService ( new URL ( endPointUrl + "?WSDL" ) );
                } catch ( MalformedURLException e ) {
                    throw new RuntimeException ( e );
                }
                port = service.getEpaypacatalogsrvPort ();

                BindingProvider bp = (BindingProvider) port;

                Map<String, Object> context = bp.getRequestContext ();
                context.put ( BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPointUrl );

                return port;

            } catch ( Exception e ) {
                service = null;
                port = null;
                throw new RuntimeException ( "Error contacting remote service", e );
            }
        } else {
            return port;
        }
    }

    private void addCallerInfo ( ParentInput input ) {
        UserDetails user = SecurityUtils.getUser ();

        CallerInputDto caller = new CallerInputDto ();
        caller.setCodiceApplicativo ( EPAYCATALOG );

        if ( user != null ) {
            PrincipalInputDto principal = new PrincipalInputDto ();
            principal.setCodiceEnte ( user.getEnte ().getCodiceFiscale () );
            principal.setCodiceFiscale ( user.getUtente ().getCodiceFiscale () );
            caller.setPrincipal ( principal );
        }
        if ( RequestContextHolder.getRequestAttributes () != null ) {
            HttpServletRequest request = ( (ServletRequestAttributes) RequestContextHolder.getRequestAttributes () ).getRequest ();
            if ( request != null ) {
                caller.setIp ( request.getRemoteAddr () );
            }
        }
        input.setCaller ( caller );
    }

    public TestResourcesOutput testResources ( TestResourcesInput testResourcesInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( testResourcesInput );
        return getPort ().testResources ( testResourcesInput );
    }

    public GetOpzioniStatoAggiornamentoEnteOutput getOpzioniStatoAggiornamentoEnte ( GetOpzioniInput getOpzioniInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( getOpzioniInput );
        return getPort ().getOpzioniStatoAggiornamentoEnte ( getOpzioniInput );
    }

    public GetOpzioniMacrotipoOutput getOpzioniMacrotipo ( GetOpzioniInput getOpzioniInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( getOpzioniInput );
        return getPort ().getOpzioniMacrotipo ( getOpzioniInput );
    }

    public GetOpzioniTipologiaAccertamentoOutput getOpzioniTipologiaAccertamento ( GetOpzioniInput getOpzioniInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( getOpzioniInput );
        return getPort ().getOpzioniTipologiaAccertamento ( getOpzioniInput );
    }

    public GetEnteOutput getEnte ( GetEnteInput getEnteInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( getEnteInput );
        return getPort ().getEnte ( getEnteInput );
    }

    public GetOpzioniTematicaOutput getOpzioniTematica ( GetOpzioniInput getOpzioniInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( getOpzioniInput );
        return getPort ().getOpzioniTematica ( getOpzioniInput );
    }

    public GetOpzioniPeriodicitaSchedulazioneRiconciliazioneOutput getOpzioniPeriodicitaSchedulazioneRiconciliazione (
        GetOpzioniInput getOpzioniInput )
                        throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( getOpzioniInput );
        return getPort ().getOpzioniPeriodicitaSchedulazioneRiconciliazione ( getOpzioniInput );
    }

    public AggiornaEnteOutput aggiornaEnte ( AggiornaEnteInput modificaEnteInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( modificaEnteInput );
        return getPort ().aggiornaEnte ( modificaEnteInput );
    }

    public GetOpzioniModalitaAcquisizioneProvvisoriOutput getOpzioniModalitaAcquisizioneProvvisori (
        GetOpzioniInput getOpzioniInput )
                        throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( getOpzioniInput );
        return getPort ().getOpzioniModalitaAcquisizioneProvvisori ( getOpzioniInput );
    }

    public GetMessaggiOutput getMessaggi ( GetMessaggiInput getMessaggiInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( getMessaggiInput );
        return getPort ().getMessaggi ( getMessaggiInput );
    }

    public RicercaVoceEntrataOutput ricercaVoceEntrata ( RicercaVoceEntrataInput ricercaVoceEntrataInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( ricercaVoceEntrataInput );
        return getPort ().ricercaVoceEntrata ( ricercaVoceEntrataInput );
    }

    public GetOpzioniCodiceVersamentoOutput getOpzioniCodiceVersamento ( GetOpzioniCodiceVersamentoInput getOpzioniInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( getOpzioniInput );
        return getPort ().getOpzioniCodiceVersamento ( getOpzioniInput );
    }

    public GetProfilazioneUtenteCorrenteOutput getInfoUtente ( GetProfilazioneUtenteCorrenteInput getInfoUtenteInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( getInfoUtenteInput );
        return getPort ().getProfilazioneUtenteCorrente ( getInfoUtenteInput );
    }

    public GetProfilazioneUtenteCorrenteOutput getProfilazioneUtenteCorrente ( String codiceUtente, String codiceEnte )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {

        GetProfilazioneUtenteCorrenteInput getInfoUtenteInput = new GetProfilazioneUtenteCorrenteInput ();

        CallerInputDto caller = new CallerInputDto ();
        caller.setCodiceApplicativo ( EPAYCATALOG );
        PrincipalInputDto principal = new PrincipalInputDto ();
        principal.setCodiceEnte ( codiceEnte );
        principal.setCodiceFiscale ( codiceUtente );
        caller.setPrincipal ( principal );
        getInfoUtenteInput.setCaller ( caller );

        return getPort ().getProfilazioneUtenteCorrente ( getInfoUtenteInput );
    }

    public GetCodiceVersamentoOutput getCodiceVersamento ( GetCodiceVersamentoInput getCodiceVersamentoInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( getCodiceVersamentoInput );
        return getPort ().getCodiceVersamento ( getCodiceVersamentoInput );
    }

    public InserisciCodiceVersamentoOutput inserisciCodiceVersamento ( InserisciCodiceVersamentoInput inserisciCodiceVersamentoInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( inserisciCodiceVersamentoInput );
        return getPort ().inserisciCodiceVersamento ( inserisciCodiceVersamentoInput );
    }

    public RicercaCodiceVersamentoOutput ricercaCodiceVersamento ( RicercaCodiceVersamentoInput ricercaCodiceVersamentoInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( ricercaCodiceVersamentoInput );
        return getPort ().ricercaCodiceVersamento ( ricercaCodiceVersamentoInput );
    }

}
