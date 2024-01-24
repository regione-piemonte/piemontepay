/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.interfacews;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

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


@WebService ( name = "Epaypacatalogsrv" )
public interface Epaypacatalogsrv {

    /*
     * MIGRAZIONE
     */

    @WebResult ( name = "result" )
    @WebMethod
    public EseguiMigrazioneOutput eseguiMigrazione (
        @WebParam ( name = "eseguiMigrazioneInput" ) EseguiMigrazioneInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    /*
     * EMAIL
     */

    @WebResult ( name = "result" )
    @WebMethod
    public ElaboraCodaEmailOutput elaboraCodaEmail (
        @WebParam ( name = "elaboraCodaEmailInput" ) ElaboraCodaEmailInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    /*
     * GESTIONE ENTI
     */

    @WebResult ( name = "result" )
    @WebMethod
    public GetEnteOutput getEnte ( @WebParam ( name = "getEnteInput" ) GetEnteInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public AggiornaEnteOutput aggiornaEnte ( @WebParam ( name = "modificaEnteInput" ) AggiornaEnteInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException;
    
    @WebResult ( name = "result" )
    @WebMethod
    public GetEntiConRiferimentoContabileSecondarioOutput getEntiConRiferimentoContabileSecondario 
    ( @WebParam ( name = "getEntiConRiferimentoContabileSecondarioInput" ) GetEntiConRiferimentoContabileSecondarioInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    /*
     * VOCI ENTRATA
     */

    @WebResult ( name = "result" )
    @WebMethod
    public RicercaVoceEntrataOutput ricercaVoceEntrata (
        @WebParam ( name = "ricercaVoceEntrataInput" ) RicercaVoceEntrataInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public RicercaVoceEntrataDisponibileOutput ricercaVoceEntrataDisponibile (
        @WebParam ( name = "ricercaVoceEntrataDisponibileInput" ) RicercaVoceEntrataDisponibileInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    /*
     * AUTENTICAZIONE
     */

    @WebResult ( name = "result" )
    @WebMethod
    public GetProfilazioneUtenteCorrenteOutput getProfilazioneUtenteCorrente (
        @WebParam ( name = "getProfilazioneUtenteCorrenteInput" ) GetProfilazioneUtenteCorrenteInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public GetProfilazioneUtenteOutput getProfilazioneUtente (
        @WebParam ( name = "getProfilazioneUtenteInput" ) GetProfilazioneUtenteInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public AutorizzaEsportazioneDatiOutput autorizzaEsportazioneDati (
        @WebParam ( name = "autorizzaEsportazioneDatiInput" ) AutorizzaEsportazioneDatiInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public GetEntiAssociatiOutput getEntiAssociati (
        @WebParam ( name = "getEntiAssociatiInput" ) GetEntiAssociatiInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    /*
     * CODICI VERSAMENTO
     */

    @WebResult ( name = "result" )
    @WebMethod
    public RicercaCodiceVersamentoOutput
        ricercaCodiceVersamento (
            @WebParam ( name = "ricercaCodiceVersamentoInput" ) RicercaCodiceVersamentoInput input )
                        throws EpaypacatalogsrvException, Exception, UnrecoverableException;
    
    
    @WebResult ( name = "result" )
    @WebMethod
    RicercaCodiceVersamentoRifContabileSecondarioOutput 
      ricercaCodiceVersamentoRifContabileSecondario(
    		  @WebParam ( name = "ricercaCodiceVersamentoRifContabileSecondarioInput" )  RicercaCodiceVersamentoRifContabileSecondarioInput input)
    				  throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public GetCodiceVersamentoOutput
        getCodiceVersamento (
            @WebParam ( name = "getCodiceVersamentoInput" ) GetCodiceVersamentoInput input )
                        throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public InserisciCodiceVersamentoOutput
        inserisciCodiceVersamento (
            @WebParam ( name = "inserisciCodiceVersamentoInput" ) InserisciCodiceVersamentoInput input )
                        throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public AggiornaCodiceVersamentoOutput
        aggiornaCodiceVersamento (
            @WebParam ( name = "aggiornaCodiceVersamentoInput" ) AggiornaCodiceVersamentoInput input )
                        throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public EliminaCodiceVersamentoOutput
        eliminaCodiceVersamento (
            @WebParam ( name = "eliminaCodiceVersamentoInput" ) EliminaCodiceVersamentoInput input )
                        throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    /*
     * RIFERIMENTI CONTABILI
     */

    @WebResult ( name = "result" )
    @WebMethod
    public RicercaRiferimentoContabileOutput
        ricercaRiferimentoContabile (
            @WebParam ( name = "ricercaRiferimentoContabileInput" ) RicercaRiferimentoContabileInput input )
                        throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public GetRiferimentoContabileOutput
        getRiferimentoContabile (
            @WebParam ( name = "getRiferimentoContabileInput" ) GetRiferimentoContabileInput input )
                        throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public InserisciRiferimentoContabileOutput
        inserisciRiferimentoContabile (
            @WebParam ( name = "inserisciRiferimentoContabileInput" ) InserisciRiferimentoContabileInput input )
                        throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public AggiornaRiferimentoContabileOutput
        aggiornaRiferimentoContabile (
            @WebParam ( name = "aggiornaRiferimentoContabileInput" ) AggiornaRiferimentoContabileInput input )
                        throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public EliminaRiferimentoContabileOutput
        eliminaRiferimentoContabile (
            @WebParam ( name = "eliminaRiferimentoContabileInput" ) EliminaRiferimentoContabileInput input )
                        throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public ChiudiRiferimentoContabileOutput
        chiudiRiferimentoContabile (
            @WebParam ( name = "chiudiRiferimentoContabileInput" ) ChiudiRiferimentoContabileInput input )
                        throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    /*
     * GESTIONE UTENTI
     */

    @WebResult ( name = "result" )
    @WebMethod
    public RicercaUtenteOutput
        ricercaUtente (
            @WebParam ( name = "ricercaUtenteInput" ) RicercaUtenteInput input )
                        throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public GetUtenteOutput
        getUtente (
            @WebParam ( name = "getUtenteInput" ) GetUtenteInput input )
                        throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public InserisciUtenteOutput
        inserisciUtente (
            @WebParam ( name = "inserisciUtenteInput" ) InserisciUtenteInput input )
                        throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public AggiornaUtenteOutput
        aggiornaUtente (
            @WebParam ( name = "aggiornaUtenteInput" ) AggiornaUtenteInput input )
                        throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public EliminaUtenteOutput
        eliminaUtente (
            @WebParam ( name = "eliminaUtenteInput" ) EliminaUtenteInput input )
                        throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public AggiornaCduUtenteOutput
        aggiornaCduUtente (
            @WebParam ( name = "aggiornaCduUtenteInput" ) AggiornaCduUtenteInput input )
                        throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public AggiornaTematicheUtenteOutput
        aggiornaTematicheUtente (
            @WebParam ( name = "aggiornaTematicheUtenteInput" ) AggiornaTematicheUtenteInput input )
                        throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    /*
     * TEST
     */

    @WebResult ( name = "result" )
    @WebMethod
    public TestResourcesOutput testResources ( @WebParam ( name = "testResourcesInput" ) TestResourcesInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    /*
     * DECODIFICHE
     */

    @WebResult ( name = "result" )
    @WebMethod
    public GetOpzioniModalitaAcquisizioneProvvisoriOutput getOpzioniModalitaAcquisizioneProvvisori (
        @WebParam ( name = "getOpzioniInput" ) GetOpzioniInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public GetOpzioniPeriodicitaSchedulazioneRiconciliazioneOutput getOpzioniPeriodicitaSchedulazioneRiconciliazione (
        @WebParam ( name = "getOpzioniInput" ) GetOpzioniInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public GetOpzioniTipologiaAccertamentoOutput getOpzioniTipologiaAccertamento (
        @WebParam ( name = "getOpzioniInput" ) GetOpzioniInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public GetOpzioniTematicaOutput getOpzioniTematica ( @WebParam ( name = "getOpzioniInput" ) GetOpzioniInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public GetOpzioniTematicaCleanOutput getOpzioniTematicaClean ( @WebParam ( name = "getOpzioniInput" ) GetOpzioniInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public GetOpzioniMacrotipoOutput getOpzioniMacrotipo ( @WebParam ( name = "getOpzioniInput" ) GetOpzioniInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public GetOpzioniStatoAggiornamentoEnteOutput getOpzioniStatoAggiornamentoEnte (
        @WebParam ( name = "getOpzioniInput" ) GetOpzioniInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public GetOpzioniTipoPagamentoOutput getOpzioniTipoPagamento (
        @WebParam ( name = "getOpzioniInput" ) GetOpzioniInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public GetOpzioniStatoAggiornamentoOutput getOpzioniStatoAggiornamento (
        @WebParam ( name = "getOpzioniInput" ) GetOpzioniInput input );

    @WebResult ( name = "result" )
    @WebMethod
    public GetMessaggiOutput getMessaggi ( @WebParam ( name = "getMessaggiInput" ) GetMessaggiInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public GetOpzioniCodiceVersamentoOutput getOpzioniCodiceVersamento (
        @WebParam ( name = "getOpzioniCodiceVersamentoInput" ) GetOpzioniCodiceVersamentoInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public GetOpzioniTipologiaDatoSpecificoRiscossioneOutput getOpzioniTipologiaDatoSpecificoRiscossione (
        @WebParam ( name = "getOpzioniInput" ) GetOpzioniInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public GetOpzioniAssociazioneUtenteTematicaOutput getOpzioniAssociazioneUtenteTematica (
        @WebParam ( name = "getOpzioniInput" ) GetOpzioniInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public GetOpzioniAssociazioneUtenteCduOutput getOpzioniAssociazioneUtenteCdu (
        @WebParam ( name = "getOpzioniInput" ) GetOpzioniInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
    public GetOpzioniModalitaIntegrazioneOutput getOpzioniModalitaIntegrazione (
        @WebParam ( name = "getOpzioniInput" ) GetOpzioniInput input )
                    throws EpaypacatalogsrvException, Exception, UnrecoverableException;

    @WebResult ( name = "result" )
    @WebMethod
	RicercaRiferimentiContabiliSecondariPerCovOutput ricercaRiferimentiContabiliSecondariPerCov(
			 @WebParam ( name = "ricercaRiferimentiContabiliSecondariPerCovInput" ) RicercaRiferimentiContabiliSecondariPerCovInput input)
			throws EpaypacatalogsrvException, Exception, UnrecoverableException;

	

}
