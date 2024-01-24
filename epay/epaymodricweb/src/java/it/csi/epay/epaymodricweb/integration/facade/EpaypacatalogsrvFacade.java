/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.integration.facade;

import java.net.URL;
import java.util.Map;

import javax.xml.ws.BindingProvider;

import org.springframework.stereotype.Service;

import it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv.AggiornaEnteInput;
import it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv.AggiornaEnteOutput;
import it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv.CallerInputDto;
import it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv.Epaypacatalogsrv;
import it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv.EpaypacatalogsrvException_Exception;
import it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv.EpaypacatalogsrvService;
import it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv.Exception_Exception;
import it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv.GetEnteInput;
import it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv.GetEnteOutput;
import it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv.GetMessaggiInput;
import it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv.GetMessaggiOutput;
import it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv.GetOpzioniCodiceVersamentoInput;
import it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv.GetOpzioniCodiceVersamentoOutput;
import it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv.GetOpzioniInput;
import it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv.GetOpzioniMacrotipoOutput;
import it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv.GetOpzioniModalitaAcquisizioneProvvisoriOutput;
import it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv.GetOpzioniPeriodicitaSchedulazioneRiconciliazioneOutput;
import it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv.GetOpzioniStatoAggiornamentoEnteOutput;
import it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv.GetOpzioniTematicaOutput;
import it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv.GetOpzioniTipologiaAccertamentoOutput;
import it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv.GetProfilazioneUtenteCorrenteInput;
import it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv.GetProfilazioneUtenteCorrenteOutput;
import it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv.ParentInput;
import it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv.PrincipalInputDto;
import it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv.RicercaVoceEntrataInput;
import it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv.RicercaVoceEntrataOutput;
import it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv.TestResourcesInput;
import it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv.TestResourcesOutput;
import it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv.UnrecoverableException_Exception;
import it.csi.epay.epaymodricweb.security.UserDetails;
import it.csi.epay.epaymodricweb.util.SecurityUtils;


@Service
public class EpaypacatalogsrvFacade extends ParentFacade {

    private static final String THIS_APPLICATION = "EPAYMODRICWEB";

    private EpaypacatalogsrvService service;

    private Epaypacatalogsrv port;

    public EpaypacatalogsrvFacade () {
        super ();
        service = null;
        port = null;
    }

    private Epaypacatalogsrv getPort () {
        if ( service == null ) {
            try {
                String endPointUrl = java.util.ResourceBundle.getBundle ( "config" ).getString ( "service.epaycatalogws.endpoint" );

                service = new EpaypacatalogsrvService ( new URL ( endPointUrl + "?WSDL" ) );
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
        caller.setCodiceApplicativo ( THIS_APPLICATION );

        if ( user != null ) {
            PrincipalInputDto principal = new PrincipalInputDto ();
            principal.setCodiceEnte ( user.getEnte ().getCodiceFiscale () );
            principal.setCodiceFiscale ( user.getUtente ().getCodiceFiscale () );
            caller.setPrincipal ( principal );
        }

        input.setCaller ( caller );
    }

    public TestResourcesOutput testResources ( TestResourcesInput testResourcesInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( testResourcesInput );
        return getPort ().testResources ( testResourcesInput );
    }

    public GetOpzioniCodiceVersamentoOutput getOpzioniCodiceVersamento ( GetOpzioniCodiceVersamentoInput getOpzioniCodiceVersamentoInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( getOpzioniCodiceVersamentoInput );
        return getPort ().getOpzioniCodiceVersamento ( getOpzioniCodiceVersamentoInput );
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

    public GetProfilazioneUtenteCorrenteOutput getProfilazioneUtenteCorrente ( String codiceUtente, String codiceEnte, String codiceRuolo )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {

        GetProfilazioneUtenteCorrenteInput getInfoUtenteInput = new GetProfilazioneUtenteCorrenteInput ();

        CallerInputDto caller = new CallerInputDto ();
        caller.setCodiceApplicativo ( THIS_APPLICATION );
        PrincipalInputDto principal = new PrincipalInputDto ();
        principal.setCodiceEnte ( codiceEnte );
        principal.setCodiceFiscale ( codiceUtente );
        caller.setPrincipal ( principal );
        getInfoUtenteInput.setCaller ( caller );

        return getPort ().getProfilazioneUtenteCorrente ( getInfoUtenteInput );
    }

}
