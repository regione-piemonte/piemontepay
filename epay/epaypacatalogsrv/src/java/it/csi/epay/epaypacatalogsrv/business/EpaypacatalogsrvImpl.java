/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatcher;
import it.csi.epay.epaypacatalogsrv.dto.autorizzazione.AutorizzaEsportazioneDatiInput;
import it.csi.epay.epaypacatalogsrv.dto.autorizzazione.AutorizzaEsportazioneDatiOutput;
import it.csi.epay.epaypacatalogsrv.dto.autorizzazione.GetEntiAssociatiInput;
import it.csi.epay.epaypacatalogsrv.dto.autorizzazione.GetEntiAssociatiOutput;
import it.csi.epay.epaypacatalogsrv.dto.autorizzazione.GetProfilazioneUtenteCorrenteInput;
import it.csi.epay.epaypacatalogsrv.dto.autorizzazione.GetProfilazioneUtenteCorrenteOutput;
import it.csi.epay.epaypacatalogsrv.dto.autorizzazione.GetProfilazioneUtenteInput;
import it.csi.epay.epaypacatalogsrv.dto.autorizzazione.GetProfilazioneUtenteOutput;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.AggiornaCodiceVersamentoInput;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.AggiornaCodiceVersamentoOutput;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.EliminaCodiceVersamentoInput;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.EliminaCodiceVersamentoOutput;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.GetCodiceVersamentoInput;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.GetCodiceVersamentoOutput;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.InserisciCodiceVersamentoInput;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.InserisciCodiceVersamentoOutput;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.RicercaCodiceVersamentoInput;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.RicercaCodiceVersamentoOutput;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.RicercaCodiceVersamentoRifContabileSecondarioInput;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.RicercaCodiceVersamentoRifContabileSecondarioOutput;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetMessaggiInput;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetMessaggiOutput;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniAssociazioneUtenteCduOutput;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniAssociazioneUtenteTematicaOutput;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniCodiceVersamentoInput;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniCodiceVersamentoOutput;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniInput;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniMacrotipoOutput;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniModalitaAcquisizioneProvvisoriOutput;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniModalitaIntegrazioneOutput;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniPeriodicitaSchedulazioneRiconciliazioneOutput;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniStatoAggiornamentoEnteOutput;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniStatoAggiornamentoOutput;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniTematicaCleanOutput;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniTematicaOutput;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniTipoPagamentoOutput;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniTipologiaAccertamentoOutput;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniTipologiaDatoSpecificoRiscossioneOutput;
import it.csi.epay.epaypacatalogsrv.dto.email.ElaboraCodaEmailInput;
import it.csi.epay.epaypacatalogsrv.dto.email.ElaboraCodaEmailOutput;
import it.csi.epay.epaypacatalogsrv.dto.ente.AggiornaEnteInput;
import it.csi.epay.epaypacatalogsrv.dto.ente.AggiornaEnteOutput;
import it.csi.epay.epaypacatalogsrv.dto.ente.GetEnteInput;
import it.csi.epay.epaypacatalogsrv.dto.ente.GetEnteOutput;
import it.csi.epay.epaypacatalogsrv.dto.ente.GetEntiConRiferimentoContabileSecondarioInput;
import it.csi.epay.epaypacatalogsrv.dto.ente.GetEntiConRiferimentoContabileSecondarioOutput;
import it.csi.epay.epaypacatalogsrv.dto.migrazione.EseguiMigrazioneInput;
import it.csi.epay.epaypacatalogsrv.dto.migrazione.EseguiMigrazioneOutput;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.AggiornaRiferimentoContabileInput;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.AggiornaRiferimentoContabileOutput;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.ChiudiRiferimentoContabileInput;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.ChiudiRiferimentoContabileOutput;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.EliminaRiferimentoContabileInput;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.EliminaRiferimentoContabileOutput;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.GetRiferimentoContabileInput;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.GetRiferimentoContabileOutput;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.InserisciRiferimentoContabileInput;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.InserisciRiferimentoContabileOutput;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.RicercaRiferimentiContabiliSecondariPerCovInput;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.RicercaRiferimentiContabiliSecondariPerCovOutput;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.RicercaRiferimentoContabileInput;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.RicercaRiferimentoContabileOutput;
import it.csi.epay.epaypacatalogsrv.dto.test.TestResourcesInput;
import it.csi.epay.epaypacatalogsrv.dto.test.TestResourcesOutput;
import it.csi.epay.epaypacatalogsrv.dto.utente.AggiornaCduUtenteInput;
import it.csi.epay.epaypacatalogsrv.dto.utente.AggiornaCduUtenteOutput;
import it.csi.epay.epaypacatalogsrv.dto.utente.AggiornaTematicheUtenteInput;
import it.csi.epay.epaypacatalogsrv.dto.utente.AggiornaTematicheUtenteOutput;
import it.csi.epay.epaypacatalogsrv.dto.utente.AggiornaUtenteInput;
import it.csi.epay.epaypacatalogsrv.dto.utente.AggiornaUtenteOutput;
import it.csi.epay.epaypacatalogsrv.dto.utente.EliminaUtenteInput;
import it.csi.epay.epaypacatalogsrv.dto.utente.EliminaUtenteOutput;
import it.csi.epay.epaypacatalogsrv.dto.utente.GetUtenteInput;
import it.csi.epay.epaypacatalogsrv.dto.utente.GetUtenteOutput;
import it.csi.epay.epaypacatalogsrv.dto.utente.InserisciUtenteInput;
import it.csi.epay.epaypacatalogsrv.dto.utente.InserisciUtenteOutput;
import it.csi.epay.epaypacatalogsrv.dto.utente.RicercaUtenteInput;
import it.csi.epay.epaypacatalogsrv.dto.utente.RicercaUtenteOutput;
import it.csi.epay.epaypacatalogsrv.dto.voceentrata.RicercaVoceEntrataDisponibileInput;
import it.csi.epay.epaypacatalogsrv.dto.voceentrata.RicercaVoceEntrataDisponibileOutput;
import it.csi.epay.epaypacatalogsrv.dto.voceentrata.RicercaVoceEntrataInput;
import it.csi.epay.epaypacatalogsrv.dto.voceentrata.RicercaVoceEntrataOutput;
import it.csi.epay.epaypacatalogsrv.exception.EpaypacatalogsrvException;
import it.csi.epay.epaypacatalogsrv.exception.UnrecoverableException;
import it.csi.epay.epaypacatalogsrv.interfacews.Epaypacatalogsrv;


@WebService ( serviceName = "EpaypacatalogsrvService", portName = "EpaypacatalogsrvPort",
                endpointInterface = "it.csi.epay.epaypacatalogsrv.interfacews.Epaypacatalogsrv" )
public class EpaypacatalogsrvImpl implements Epaypacatalogsrv {

    @Resource
    WebServiceContext wsContext;

    @Autowired
    private OperationDispatcher dispatcher;

    public OperationDispatcher getDispatcher () {
        return dispatcher;
    }

    public void setDispatcher ( OperationDispatcher dispatcher ) {
        this.dispatcher = dispatcher;
    }

    @PostConstruct
    public void postConstruct () {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext ( this );
    }

    @Override
    public TestResourcesOutput testResources ( TestResourcesInput input )
                    throws it.csi.epay.epaypacatalogsrv.exception.EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, TestResourcesOutput.class );
    }

    @Override
    public GetEnteOutput getEnte ( GetEnteInput input ) throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, GetEnteOutput.class );
    }
    
    @Override
    public GetEntiConRiferimentoContabileSecondarioOutput getEntiConRiferimentoContabileSecondario
    ( GetEntiConRiferimentoContabileSecondarioInput input ) throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, GetEntiConRiferimentoContabileSecondarioOutput.class );
    }

    @Override
    public AggiornaEnteOutput aggiornaEnte ( AggiornaEnteInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, AggiornaEnteOutput.class );
    }

    @Override
    public GetOpzioniModalitaAcquisizioneProvvisoriOutput getOpzioniModalitaAcquisizioneProvvisori ( GetOpzioniInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, GetOpzioniModalitaAcquisizioneProvvisoriOutput.class );
    }

    @Override
    public GetOpzioniPeriodicitaSchedulazioneRiconciliazioneOutput getOpzioniPeriodicitaSchedulazioneRiconciliazione ( GetOpzioniInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, GetOpzioniPeriodicitaSchedulazioneRiconciliazioneOutput.class );
    }

    @Override
    public GetOpzioniTipologiaAccertamentoOutput getOpzioniTipologiaAccertamento ( GetOpzioniInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, GetOpzioniTipologiaAccertamentoOutput.class );
    }

    @Override
    public GetOpzioniTematicaOutput getOpzioniTematica ( GetOpzioniInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, GetOpzioniTematicaOutput.class );
    }

    @Override
    public GetOpzioniTematicaCleanOutput getOpzioniTematicaClean ( GetOpzioniInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, GetOpzioniTematicaCleanOutput.class );
    }

    @Override
    public GetOpzioniMacrotipoOutput getOpzioniMacrotipo ( GetOpzioniInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, GetOpzioniMacrotipoOutput.class );
    }

    @Override
    public GetOpzioniStatoAggiornamentoEnteOutput getOpzioniStatoAggiornamentoEnte ( GetOpzioniInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, GetOpzioniStatoAggiornamentoEnteOutput.class );
    }

    @Override
    public GetMessaggiOutput getMessaggi ( GetMessaggiInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, GetMessaggiOutput.class );
    }

    @Override
    public RicercaVoceEntrataOutput ricercaVoceEntrata ( RicercaVoceEntrataInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, RicercaVoceEntrataOutput.class );
    }

    @Override
    public GetOpzioniCodiceVersamentoOutput getOpzioniCodiceVersamento ( GetOpzioniCodiceVersamentoInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, GetOpzioniCodiceVersamentoOutput.class );
    }

    @Override
    public GetProfilazioneUtenteCorrenteOutput getProfilazioneUtenteCorrente ( GetProfilazioneUtenteCorrenteInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, GetProfilazioneUtenteCorrenteOutput.class );
    }

    @Override
    public GetProfilazioneUtenteOutput getProfilazioneUtente ( GetProfilazioneUtenteInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, GetProfilazioneUtenteOutput.class );
    }

    @Override
    public RicercaCodiceVersamentoOutput ricercaCodiceVersamento ( RicercaCodiceVersamentoInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, RicercaCodiceVersamentoOutput.class );
    }
    
    @Override
    public RicercaCodiceVersamentoRifContabileSecondarioOutput ricercaCodiceVersamentoRifContabileSecondario ( RicercaCodiceVersamentoRifContabileSecondarioInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, RicercaCodiceVersamentoRifContabileSecondarioOutput.class );
    }

    @Override
    public GetCodiceVersamentoOutput getCodiceVersamento ( GetCodiceVersamentoInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, GetCodiceVersamentoOutput.class );
    }

    @Override
    public InserisciCodiceVersamentoOutput inserisciCodiceVersamento ( InserisciCodiceVersamentoInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, InserisciCodiceVersamentoOutput.class );
    }

    @Override
    public AggiornaCodiceVersamentoOutput aggiornaCodiceVersamento ( AggiornaCodiceVersamentoInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, AggiornaCodiceVersamentoOutput.class );
    }

    @Override
    public EliminaCodiceVersamentoOutput eliminaCodiceVersamento ( EliminaCodiceVersamentoInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, EliminaCodiceVersamentoOutput.class );
    }

    @Override
    public GetOpzioniTipoPagamentoOutput getOpzioniTipoPagamento ( GetOpzioniInput input ) throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, GetOpzioniTipoPagamentoOutput.class );
    }

    @Override
    public GetOpzioniStatoAggiornamentoOutput getOpzioniStatoAggiornamento ( GetOpzioniInput input ) {
        return dispatcher.dispatch ( input, GetOpzioniStatoAggiornamentoOutput.class );
    }

    @Override
    public RicercaRiferimentoContabileOutput ricercaRiferimentoContabile ( RicercaRiferimentoContabileInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, RicercaRiferimentoContabileOutput.class );
    }

    @Override
    public GetRiferimentoContabileOutput getRiferimentoContabile ( GetRiferimentoContabileInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, GetRiferimentoContabileOutput.class );
    }

    @Override
    public InserisciRiferimentoContabileOutput inserisciRiferimentoContabile ( InserisciRiferimentoContabileInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, InserisciRiferimentoContabileOutput.class );
    }

    @Override
    public AggiornaRiferimentoContabileOutput aggiornaRiferimentoContabile ( AggiornaRiferimentoContabileInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, AggiornaRiferimentoContabileOutput.class );
    }

    @Override
    public EliminaRiferimentoContabileOutput eliminaRiferimentoContabile ( EliminaRiferimentoContabileInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, EliminaRiferimentoContabileOutput.class );
    }

    @Override
    public GetOpzioniTipologiaDatoSpecificoRiscossioneOutput getOpzioniTipologiaDatoSpecificoRiscossione ( GetOpzioniInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, GetOpzioniTipologiaDatoSpecificoRiscossioneOutput.class );
    }

    @Override
    public RicercaUtenteOutput ricercaUtente ( RicercaUtenteInput input ) throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, RicercaUtenteOutput.class );
    }

    @Override
    public GetUtenteOutput getUtente ( GetUtenteInput input ) throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, GetUtenteOutput.class );
    }

    @Override
    public AggiornaUtenteOutput aggiornaUtente ( AggiornaUtenteInput input ) throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, AggiornaUtenteOutput.class );
    }

    @Override
    public EliminaUtenteOutput eliminaUtente ( EliminaUtenteInput input ) throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, EliminaUtenteOutput.class );
    }

    @Override
    public InserisciUtenteOutput inserisciUtente ( InserisciUtenteInput input ) throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, InserisciUtenteOutput.class );
    }

    @Override
    public GetOpzioniAssociazioneUtenteTematicaOutput getOpzioniAssociazioneUtenteTematica ( GetOpzioniInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, GetOpzioniAssociazioneUtenteTematicaOutput.class );
    }

    @Override
    public AggiornaCduUtenteOutput aggiornaCduUtente ( AggiornaCduUtenteInput input ) throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, AggiornaCduUtenteOutput.class );
    }

    @Override
    public AggiornaTematicheUtenteOutput aggiornaTematicheUtente ( AggiornaTematicheUtenteInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, AggiornaTematicheUtenteOutput.class );
    }

    @Override
    public GetOpzioniAssociazioneUtenteCduOutput getOpzioniAssociazioneUtenteCdu ( GetOpzioniInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, GetOpzioniAssociazioneUtenteCduOutput.class );
    }

    @Override
    public AutorizzaEsportazioneDatiOutput autorizzaEsportazioneDati ( AutorizzaEsportazioneDatiInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, AutorizzaEsportazioneDatiOutput.class );
    }

    @Override
    public ElaboraCodaEmailOutput elaboraCodaEmail ( ElaboraCodaEmailInput input ) throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, ElaboraCodaEmailOutput.class );
    }

    @Override
    public RicercaVoceEntrataDisponibileOutput ricercaVoceEntrataDisponibile ( RicercaVoceEntrataDisponibileInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, RicercaVoceEntrataDisponibileOutput.class );
    }

    @Override
    public GetEntiAssociatiOutput getEntiAssociati ( GetEntiAssociatiInput input ) throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, GetEntiAssociatiOutput.class );
    }

    @Override
    public GetOpzioniModalitaIntegrazioneOutput getOpzioniModalitaIntegrazione ( GetOpzioniInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, GetOpzioniModalitaIntegrazioneOutput.class );
    }

    @Override
    public EseguiMigrazioneOutput eseguiMigrazione ( EseguiMigrazioneInput input ) throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, EseguiMigrazioneOutput.class );
    }

    @Override
    public ChiudiRiferimentoContabileOutput chiudiRiferimentoContabile ( ChiudiRiferimentoContabileInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, ChiudiRiferimentoContabileOutput.class );
    }
    
    @Override
    public RicercaRiferimentiContabiliSecondariPerCovOutput ricercaRiferimentiContabiliSecondariPerCov ( RicercaRiferimentiContabiliSecondariPerCovInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException {
        return dispatcher.dispatch ( input, RicercaRiferimentiContabiliSecondariPerCovOutput.class );
    }

}
