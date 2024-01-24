/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.integration.facade.impl;

import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.AggiornaCduUtenteInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.AggiornaCduUtenteOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.AggiornaCodiceVersamentoInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.AggiornaCodiceVersamentoOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.AggiornaEnteInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.AggiornaEnteOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.AggiornaRiferimentoContabileInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.AggiornaRiferimentoContabileOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.AggiornaTematicheUtenteInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.AggiornaTematicheUtenteOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.AggiornaUtenteInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.AggiornaUtenteOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.AutorizzaEsportazioneDatiInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.AutorizzaEsportazioneDatiOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.CallerInputDto;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.ChiudiRiferimentoContabileInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.ChiudiRiferimentoContabileOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.ElaboraCodaEmailInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.ElaboraCodaEmailOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.EliminaCodiceVersamentoInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.EliminaCodiceVersamentoOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.EliminaRiferimentoContabileInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.EliminaRiferimentoContabileOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.EliminaUtenteInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.EliminaUtenteOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.Epaypacatalogsrv;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.EpaypacatalogsrvException_Exception;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.EpaypacatalogsrvService;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.EseguiMigrazioneInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.EseguiMigrazioneOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.Exception_Exception;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetCodiceVersamentoInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetCodiceVersamentoOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetEnteInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetEnteOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetEntiAssociatiInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetEntiAssociatiOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetEntiConRiferimentoContabileSecondarioInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetEntiConRiferimentoContabileSecondarioOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetMessaggiInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetMessaggiOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetOpzioniAssociazioneUtenteCduOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetOpzioniAssociazioneUtenteTematicaOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetOpzioniCodiceVersamentoInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetOpzioniCodiceVersamentoOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetOpzioniInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetOpzioniMacrotipoOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetOpzioniModalitaAcquisizioneProvvisoriOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetOpzioniModalitaIntegrazioneOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetOpzioniPeriodicitaSchedulazioneRiconciliazioneOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetOpzioniStatoAggiornamentoEnteOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetOpzioniStatoAggiornamentoOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetOpzioniTematicaCleanOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetOpzioniTematicaOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetOpzioniTipoPagamentoOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetOpzioniTipologiaAccertamentoOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetOpzioniTipologiaDatoSpecificoRiscossioneOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetProfilazioneUtenteCorrenteInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetProfilazioneUtenteCorrenteOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetProfilazioneUtenteInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetProfilazioneUtenteOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetRiferimentoContabileInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetRiferimentoContabileOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetUtenteInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.GetUtenteOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.InserisciCodiceVersamentoInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.InserisciCodiceVersamentoOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.InserisciRiferimentoContabileInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.InserisciRiferimentoContabileOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.InserisciUtenteInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.InserisciUtenteOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.ParentInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.PrincipalInputDto;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaCodiceVersamentoInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaCodiceVersamentoOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaCodiceVersamentoRifContabileSecondarioInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaCodiceVersamentoRifContabileSecondarioOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaRiferimentiContabiliSecondariPerCovInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaRiferimentiContabiliSecondariPerCovOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaRiferimentoContabileInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaRiferimentoContabileOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaUtenteInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaUtenteOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaVoceEntrataDisponibileInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaVoceEntrataDisponibileOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaVoceEntrataInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.RicercaVoceEntrataOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.TestResourcesInput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.TestResourcesOutput;
import it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv.UnrecoverableException_Exception;
import it.csi.epay.epaypacatalogweb.security.UserDetails;
import it.csi.epay.epaypacatalogweb.util.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.BindingProvider;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

@Service
public class EpaypacatalogsrvFacade extends ParentFacade implements Epaypacatalogsrv {

    private static final String EPAYPACATALOG = "EPAYPACATALOGWEB";

    private EpaypacatalogsrvService service;

    private Epaypacatalogsrv port;

    public EpaypacatalogsrvFacade() {
        super();
        service = null;
        port = null;
    }

    private Epaypacatalogsrv getPort() {
        if (port == null) {
            try {
                String endPointUrl = java.util.ResourceBundle.getBundle("config")
                                .getString("service.epaypacatalogwebws.endpoint");

                try {
                    service = new EpaypacatalogsrvService(new URL(endPointUrl + "?WSDL"));
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }

                port = service.getEpaypacatalogsrvPort();

                BindingProvider bp = (BindingProvider) port;

                Map<String, Object> context = bp.getRequestContext();
                context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPointUrl);

                return port;

            } catch (Exception e) {
                service = null;
                port = null;
                throw new RuntimeException("Error contacting remote service", e);
            }
        } else {
            return port;
        }
    }

    private void addCallerInfo(ParentInput input) {
        UserDetails user = SecurityUtils.getUser();

        CallerInputDto caller = new CallerInputDto();
        caller.setCodiceApplicativo(EPAYPACATALOG);

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

    @Override
    public TestResourcesOutput testResources(TestResourcesInput testResourcesInput)
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo(testResourcesInput);
        return getPort().testResources(testResourcesInput);
    }

    @Override
    public GetOpzioniStatoAggiornamentoEnteOutput getOpzioniStatoAggiornamentoEnte(GetOpzioniInput getOpzioniInput)
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo(getOpzioniInput);
        return getPort().getOpzioniStatoAggiornamentoEnte(getOpzioniInput);
    }

    @Override
    public GetOpzioniMacrotipoOutput getOpzioniMacrotipo(GetOpzioniInput getOpzioniInput)
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo(getOpzioniInput);
        return getPort().getOpzioniMacrotipo(getOpzioniInput);
    }

    @Override
    public GetOpzioniTipologiaAccertamentoOutput getOpzioniTipologiaAccertamento(GetOpzioniInput getOpzioniInput)
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo(getOpzioniInput);
        return getPort().getOpzioniTipologiaAccertamento(getOpzioniInput);
    }

    @Override
    public GetEnteOutput getEnte(GetEnteInput getEnteInput)
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {

        addCallerInfo ( getEnteInput );

		return getPort ().getEnte ( getEnteInput );
    }

    @Override
    public GetOpzioniTematicaOutput getOpzioniTematica(GetOpzioniInput getOpzioniInput)
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo(getOpzioniInput);
        return getPort().getOpzioniTematica(getOpzioniInput);
    }

    @Override
    public GetOpzioniTematicaCleanOutput getOpzioniTematicaClean ( GetOpzioniInput getOpzioniInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo(getOpzioniInput);
        return getPort().getOpzioniTematicaClean(getOpzioniInput);
    }

    @Override
    public GetOpzioniPeriodicitaSchedulazioneRiconciliazioneOutput getOpzioniPeriodicitaSchedulazioneRiconciliazione(
        GetOpzioniInput getOpzioniInput)
                        throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo(getOpzioniInput);
        return getPort().getOpzioniPeriodicitaSchedulazioneRiconciliazione(getOpzioniInput);
    }

    @Override
    public AggiornaEnteOutput aggiornaEnte(AggiornaEnteInput modificaEnteInput)
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo(modificaEnteInput);
        return getPort().aggiornaEnte(modificaEnteInput);
    }

    @Override
    public GetOpzioniModalitaAcquisizioneProvvisoriOutput getOpzioniModalitaAcquisizioneProvvisori(
        GetOpzioniInput getOpzioniInput)
                        throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo(getOpzioniInput);
        return getPort().getOpzioniModalitaAcquisizioneProvvisori(getOpzioniInput);
    }

    @Override
    public GetMessaggiOutput getMessaggi(GetMessaggiInput getMessaggiInput)
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo(getMessaggiInput);
        return getPort().getMessaggi(getMessaggiInput);
    }

    @Override
    public RicercaVoceEntrataOutput ricercaVoceEntrata(RicercaVoceEntrataInput ricercaVoceEntrataInput)
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo(ricercaVoceEntrataInput);
        return getPort().ricercaVoceEntrata(ricercaVoceEntrataInput);
    }

    @Override
    public GetOpzioniCodiceVersamentoOutput getOpzioniCodiceVersamento ( GetOpzioniCodiceVersamentoInput getOpzioniInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo(getOpzioniInput);
        return getPort().getOpzioniCodiceVersamento(getOpzioniInput);
    }

    public GetProfilazioneUtenteCorrenteOutput getProfilazioneUtenteCorrente ( String codiceUtente, String codiceEnte )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {

        GetProfilazioneUtenteCorrenteInput getInfoUtenteInput = new GetProfilazioneUtenteCorrenteInput ();

        CallerInputDto caller = new CallerInputDto();
        caller.setCodiceApplicativo(EPAYPACATALOG);
        PrincipalInputDto principal = new PrincipalInputDto();
        principal.setCodiceEnte(codiceEnte);
        principal.setCodiceFiscale(codiceUtente);
        caller.setPrincipal(principal);
        getInfoUtenteInput.setCaller(caller);

        if ( RequestContextHolder.getRequestAttributes () != null ) {
            HttpServletRequest request = ( (ServletRequestAttributes) RequestContextHolder.getRequestAttributes () ).getRequest ();
            if ( request != null ) {
                caller.setIp ( request.getRemoteAddr () );
            }
        }

        return getPort ().getProfilazioneUtenteCorrente ( getInfoUtenteInput );
    }

    @Override
    public GetCodiceVersamentoOutput getCodiceVersamento ( GetCodiceVersamentoInput getCodiceVersamentoInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( getCodiceVersamentoInput );
        return getPort ().getCodiceVersamento ( getCodiceVersamentoInput );
    }

    @Override
    public InserisciCodiceVersamentoOutput inserisciCodiceVersamento ( InserisciCodiceVersamentoInput inserisciCodiceVersamentoInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( inserisciCodiceVersamentoInput );
        return getPort ().inserisciCodiceVersamento ( inserisciCodiceVersamentoInput );
    }

    @Override
    public RicercaCodiceVersamentoOutput ricercaCodiceVersamento ( RicercaCodiceVersamentoInput ricercaCodiceVersamentoInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( ricercaCodiceVersamentoInput );
        return getPort ().ricercaCodiceVersamento ( ricercaCodiceVersamentoInput );
    }

    @Override
    public AggiornaCodiceVersamentoOutput aggiornaCodiceVersamento ( AggiornaCodiceVersamentoInput aggiornaCodiceVersamentoInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( aggiornaCodiceVersamentoInput );
        return getPort ().aggiornaCodiceVersamento ( aggiornaCodiceVersamentoInput );
    }

    @Override
    public EliminaCodiceVersamentoOutput eliminaCodiceVersamento ( EliminaCodiceVersamentoInput eliminaCodiceVersamentoInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( eliminaCodiceVersamentoInput );
        return getPort ().eliminaCodiceVersamento ( eliminaCodiceVersamentoInput );
    }

    @Override
    public GetOpzioniTipoPagamentoOutput getOpzioniTipoPagamento ( GetOpzioniInput getOpzioniInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( getOpzioniInput );
        return getPort ().getOpzioniTipoPagamento ( getOpzioniInput );
    }

    @Override
    public GetOpzioniStatoAggiornamentoOutput getOpzioniStatoAggiornamento ( GetOpzioniInput getOpzioniInput ) {
        addCallerInfo ( getOpzioniInput );
        return getPort ().getOpzioniStatoAggiornamento ( getOpzioniInput );
    }

    @Override
    public RicercaRiferimentoContabileOutput ricercaRiferimentoContabile ( RicercaRiferimentoContabileInput ricercaRiferimentoContabileInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( ricercaRiferimentoContabileInput );
        return getPort ().ricercaRiferimentoContabile ( ricercaRiferimentoContabileInput );
    }

    @Override
    public GetRiferimentoContabileOutput getRiferimentoContabile ( GetRiferimentoContabileInput getRiferimentoContabileInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( getRiferimentoContabileInput );
        return getPort ().getRiferimentoContabile ( getRiferimentoContabileInput );
    }

    @Override
    public GetOpzioniTipologiaDatoSpecificoRiscossioneOutput getOpzioniTipologiaDatoSpecificoRiscossione ( GetOpzioniInput getOpzioniInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( getOpzioniInput );
        return getPort ().getOpzioniTipologiaDatoSpecificoRiscossione ( getOpzioniInput );
    }

    @Override
    public AggiornaRiferimentoContabileOutput aggiornaRiferimentoContabile ( AggiornaRiferimentoContabileInput aggiornaRiferimentoContabileInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( aggiornaRiferimentoContabileInput );
        return getPort ().aggiornaRiferimentoContabile ( aggiornaRiferimentoContabileInput );
    }

    @Override
    public InserisciRiferimentoContabileOutput inserisciRiferimentoContabile ( InserisciRiferimentoContabileInput inserisciRiferimentoContabileInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( inserisciRiferimentoContabileInput );
        return getPort ().inserisciRiferimentoContabile ( inserisciRiferimentoContabileInput );
    }

    @Override
    public EliminaRiferimentoContabileOutput eliminaRiferimentoContabile ( EliminaRiferimentoContabileInput eliminaRiferimentoContabileInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( eliminaRiferimentoContabileInput );
        return getPort ().eliminaRiferimentoContabile ( eliminaRiferimentoContabileInput );
    }

    @Override
    public GetProfilazioneUtenteCorrenteOutput getProfilazioneUtenteCorrente ( GetProfilazioneUtenteCorrenteInput getProfilazioneUtenteCorrenteInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( getProfilazioneUtenteCorrenteInput );
        return getPort ().getProfilazioneUtenteCorrente ( getProfilazioneUtenteCorrenteInput );
    }

    @Override
    public GetUtenteOutput getUtente ( GetUtenteInput getUtenteInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( getUtenteInput );
        return getPort ().getUtente ( getUtenteInput );
    }

    @Override
    public RicercaUtenteOutput ricercaUtente ( RicercaUtenteInput ricercaUtenteInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( ricercaUtenteInput );
        return getPort ().ricercaUtente ( ricercaUtenteInput );
    }

    @Override
    public GetOpzioniAssociazioneUtenteTematicaOutput getOpzioniAssociazioneUtenteTematica ( GetOpzioniInput getOpzioniInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( getOpzioniInput );
        return getPort ().getOpzioniAssociazioneUtenteTematica ( getOpzioniInput );
    }

    @Override
    public EliminaUtenteOutput eliminaUtente ( EliminaUtenteInput eliminaUtenteInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( eliminaUtenteInput );
        return getPort ().eliminaUtente ( eliminaUtenteInput );
    }

    @Override
    public AggiornaTematicheUtenteOutput aggiornaTematicheUtente ( AggiornaTematicheUtenteInput aggiornaTematicheUtenteInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( aggiornaTematicheUtenteInput );
        return getPort ().aggiornaTematicheUtente ( aggiornaTematicheUtenteInput );
    }

    @Override
    public InserisciUtenteOutput inserisciUtente ( InserisciUtenteInput inserisciUtenteInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( inserisciUtenteInput );
        return getPort ().inserisciUtente ( inserisciUtenteInput );
    }

    @Override
    public AggiornaCduUtenteOutput aggiornaCduUtente ( AggiornaCduUtenteInput aggiornaCduUtenteInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( aggiornaCduUtenteInput );
        return getPort ().aggiornaCduUtente ( aggiornaCduUtenteInput );
    }

    @Override
    public AggiornaUtenteOutput aggiornaUtente ( AggiornaUtenteInput aggiornaUtenteInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( aggiornaUtenteInput );
        return getPort ().aggiornaUtente ( aggiornaUtenteInput );
    }

    @Override
    public GetOpzioniAssociazioneUtenteCduOutput getOpzioniAssociazioneUtenteCdu ( GetOpzioniInput getOpzioniInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( getOpzioniInput );
        return getPort ().getOpzioniAssociazioneUtenteCdu ( getOpzioniInput );
    }

    @Override
    public AutorizzaEsportazioneDatiOutput autorizzaEsportazioneDati ( AutorizzaEsportazioneDatiInput autorizzaEsportazioneDatiInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( autorizzaEsportazioneDatiInput );
        return getPort ().autorizzaEsportazioneDati ( autorizzaEsportazioneDatiInput );
    }

    @Override
    public ElaboraCodaEmailOutput elaboraCodaEmail ( ElaboraCodaEmailInput elaboraCodaEmailInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( elaboraCodaEmailInput );
        return getPort ().elaboraCodaEmail ( elaboraCodaEmailInput );
    }

    @Override
    public GetProfilazioneUtenteOutput getProfilazioneUtente ( GetProfilazioneUtenteInput getProfilazioneUtenteInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( getProfilazioneUtenteInput );
        return getPort ().getProfilazioneUtente ( getProfilazioneUtenteInput );
    }

    @Override
    public RicercaVoceEntrataDisponibileOutput ricercaVoceEntrataDisponibile ( RicercaVoceEntrataDisponibileInput ricercaVoceEntrataDisponibileInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( ricercaVoceEntrataDisponibileInput );
        return getPort ().ricercaVoceEntrataDisponibile ( ricercaVoceEntrataDisponibileInput );
    }

    @Override
    public GetEntiAssociatiOutput getEntiAssociati ( GetEntiAssociatiInput getEntiAssociatiInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( getEntiAssociatiInput );
        return getPort ().getEntiAssociati ( getEntiAssociatiInput );
    }

    @Override
    public GetOpzioniModalitaIntegrazioneOutput getOpzioniModalitaIntegrazione ( GetOpzioniInput getOpzioniInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( getOpzioniInput );
        return getPort ().getOpzioniModalitaIntegrazione ( getOpzioniInput );
    }

    @Override
    public ChiudiRiferimentoContabileOutput chiudiRiferimentoContabile ( ChiudiRiferimentoContabileInput chiudiRiferimentoContabileInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        addCallerInfo ( chiudiRiferimentoContabileInput );
        return getPort ().chiudiRiferimentoContabile ( chiudiRiferimentoContabileInput );
    }

    @Override
    public EseguiMigrazioneOutput eseguiMigrazione ( EseguiMigrazioneInput eseguiMigrazioneInput )
                    throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
        throw new RuntimeException ( "OPERAZIONE NON PERMESSA" );
    }

	@Override
	public GetEntiConRiferimentoContabileSecondarioOutput getEntiConRiferimentoContabileSecondario(
			GetEntiConRiferimentoContabileSecondarioInput getEntiConRiferimentoContabileSecondarioInput)
			throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
		addCallerInfo ( getEntiConRiferimentoContabileSecondarioInput );
		 return getPort ().getEntiConRiferimentoContabileSecondario(getEntiConRiferimentoContabileSecondarioInput);
	}

	@Override
	public RicercaCodiceVersamentoRifContabileSecondarioOutput ricercaCodiceVersamentoRifContabileSecondario(
			RicercaCodiceVersamentoRifContabileSecondarioInput ricercaCodiceVersamentoRifContabileSecondarioInput)
			throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
		addCallerInfo ( ricercaCodiceVersamentoRifContabileSecondarioInput );
		
		 return getPort ().ricercaCodiceVersamentoRifContabileSecondario(ricercaCodiceVersamentoRifContabileSecondarioInput);
	}

	@Override
	public RicercaRiferimentiContabiliSecondariPerCovOutput ricercaRiferimentiContabiliSecondariPerCov(
			RicercaRiferimentiContabiliSecondariPerCovInput ricercaRiferimentiContabiliSecondariPerCovInput)
			throws Exception_Exception, EpaypacatalogsrvException_Exception, UnrecoverableException_Exception {
		addCallerInfo ( ricercaRiferimentiContabiliSecondariPerCovInput );
		
		 return getPort ().ricercaRiferimentiContabiliSecondariPerCov(ricercaRiferimentiContabiliSecondariPerCovInput);
	
	}
	
	
    
    

}
