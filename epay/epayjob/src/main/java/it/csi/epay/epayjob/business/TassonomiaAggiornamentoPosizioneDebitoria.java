/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.business;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.csi.epay.epayjob.model.*;
import it.csi.epay.epayservices.interfaces.ejb.JobFacade;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;


public class TassonomiaAggiornamentoPosizioneDebitoria extends JobTassonomia {

	private final JobContext jobContext;

	private Properties properties;

	private String urlTassonomieAggiornate;

	private String urlRiferimentiContabiliByIdTassonomia;

	private String urlAggiornametoDatoSpecificoRiscossione;

	private JobFacade jobFacade; // serve per chiamate su sportello

	private static final String CONFIG_PROPERTIES = "config.properties";

	public TassonomiaAggiornamentoPosizioneDebitoria ( JobContext jobContext ) {
		super ();
		this.jobContext = jobContext;
		final String methodName = "constructor";
		try {
			jobContext.infoStart ( methodName );

			InputStream inputStream = this.getClass ().getClassLoader ().getResourceAsStream ( CONFIG_PROPERTIES );
			properties = new Properties ();
			properties.load ( inputStream );

			urlTassonomieAggiornate = properties.getProperty ( "service.tassonomia.get.all.aggiornate" );
			urlRiferimentiContabiliByIdTassonomia = properties.getProperty ( "service.riferimenti.contabili.get.by.id.tassonomia" );
			urlAggiornametoDatoSpecificoRiscossione = properties.getProperty ( "service.riferimenti.contabili.aggiorna.dato.specifico.riscossione" );

			if ( properties.isEmpty () ) {
				jobContext.debug ( methodName, "File di properties non trovato. L'elenco e' vuoto!" );
			}

		} catch ( Throwable t ) {
			jobContext.error ( methodName, "Errore nel metodo " + methodName, t );
		} finally {
			jobContext.infoEnd ( methodName );
		}
	}

	public void execute () throws IOException {
		final String methodName = "execute";
		jobContext.infoStart ( methodName );
		try {
			doExecute ();
		} catch ( Throwable t ) {
			jobContext.error ( methodName, "Errore nell'esecuzione del Job", t );
			throw t;
		} finally {
			jobContext.infoEnd ( methodName );
		}
	}

	private void doExecute () throws IOException {
		final String methodName = "doExecute";
		final Date startTime = new Date ();
		jobContext.info ( methodName, "avvio elaborazione: " + startTime );
		// chiamata alla api di epaycatalogsrv l'elenco delle tassonomie, con solo i campi id, tipo_servizio, dati_specifici_incasso
		String basicAuth = properties.getProperty ( "service.tassonomia.auth" );
		InputStream inputStream = doPostToRestService ( urlTassonomieAggiornate, basicAuth, null );
		ObjectMapper objectMapper = new ObjectMapper ();
		RicercaTassonomieAggiornateOutput serviceResponse = objectMapper.readValue ( inputStream, RicercaTassonomieAggiornateOutput.class );
		jobContext.info ( "Ricerca Tassonomia Aggiornate eseguita con successo" );

		for ( TassonomiaAggiornataOutputDto tassonomia : serviceResponse.getRisultati () ) {
			// per ogni tassonomia si fara' un'altra chiamata sempre su catalog
			TassonomiaIdDto tassonomiaIdDto = new TassonomiaIdDto ( tassonomia.getId () );
			inputStream = doPostToRestService ( urlRiferimentiContabiliByIdTassonomia, basicAuth, tassonomiaIdDto );
			RiferimentoContabile riferimentoContabile = objectMapper.readValue ( inputStream, RiferimentoContabile.class );
			if ( null != riferimentoContabile ) {
				jobContext.info ( "Ricerca Riferimento Contabile associato alla Tassonomia eseguita con successo" );
			} else {
				continue;
			}

			// dovrebbe essere sempre a '9' ?
			String codiceTipologiaDatoSpecificoRiscossione = riferimentoContabile.getCodiceTipologiaDatoSpecificoRiscossione ();

			// es = 0115105AP/2022.98
			String datoSpecificoRiscossione = riferimentoContabile.getDatoSpecificoRiscossione ();
			String[] arrDatoSpec = datoSpecificoRiscossione.split ( "/" );
			String annoAccertamento = riferimentoContabile.getAnnoAccertamento ();
			String numeroAccertamento = riferimentoContabile.getNumAccertamento ();

			// es = 9/0115115AP/
			String datiSpecificiIncassoTassonomia = tassonomia.getDatiSpecificiIncasso ();
			String datiSpecificiIncassoSenzaNove = datiSpecificiIncassoTassonomia.replaceFirst ( "^9/", "" );
			// es = 0115115AP/2022.98
			String datoSpecificoRiscossioneCatalogo = datiSpecificiIncassoSenzaNove + annoAccertamento + "." + numeroAccertamento;

			// vedesi CDU RDI41 Tassonomia_Batch Aggiornamento_Posizione_Debitoria_V01.docx
			boolean puntoAvero = codiceTipologiaDatoSpecificoRiscossione.equalsIgnoreCase ( "9" ) &&
							datoSpecificoRiscossioneCatalogo.equalsIgnoreCase ( datoSpecificoRiscossione ) &&
							annoAccertamento != null &&
							numeroAccertamento != null &&
							!annoAccertamento.equalsIgnoreCase ( "2900" ) &&
							!numeroAccertamento.equalsIgnoreCase ( "9999" );

			boolean puntoCvero = datoSpecificoRiscossione.contains ( "AP" ) || datoSpecificoRiscossione.contains ( "IM" ) ||
							datoSpecificoRiscossione.contains ( "SA" ) || datoSpecificoRiscossione.contains ( "SP" ) ||
							datoSpecificoRiscossione.contains ( "TS" );

			String tassonomiaEsistente = arrDatoSpec[0] + "/";                                                  // es 0115105AP/
			String tassonomiaNuova = datiSpecificiIncassoTassonomia.replaceFirst ( "^9/", "" ); // es 0115115AP/

			if ( puntoAvero ) {
				if ( puntoCvero ) {
					// chiamare sportello
					boolean ok = jobFacade.ricercaAndUpdateComponentiPosizioneValida ( tassonomiaEsistente, tassonomiaNuova );
					if ( ok ) {
						jobContext.info ( "Ricerca e Update delle Componenti Pagamento in Posizione Valida eseguita con successo" );
						// chimata catalog di update
						AggiornametoDatoSpecificoRiscossioneDto aggiornametoDatoSpecificoRiscossioneDto = new AggiornametoDatoSpecificoRiscossioneDto (
										tassonomia.getId (), tassonomiaEsistente, tassonomiaNuova
						);
						inputStream = doPostToRestService ( urlAggiornametoDatoSpecificoRiscossione, basicAuth, aggiornametoDatoSpecificoRiscossioneDto );
						UpdateComponentiPagamentiOutput output = objectMapper.readValue ( inputStream, UpdateComponentiPagamentiOutput.class );
						// Dopo avere salvato i dati sul DB CATALOG, il batch propaga i dati del riferimento contabile nel sottosistema configurato, MODRIC
						if (output.getCodiceStato () == 200) {
							jobContext.info ( "Update delle Componenti Pagamento eseguita con successo" );
						/*
							si usa un servizio già esistente
							CoopApplicativaPEC
							cosi fate prima
							metodo aggiornaRiferimentoContabile
							e confermaOperazione
							trovi esempi di utilizzo in catalogo
							forse forse fai prima a fare l'operazione direttamente da catalogo
							trovi l'operazione sulla classe AggiornaRiferimentoContabileOperation
							secondo me le operazioni da fare sono queste -> catalogo capisco cosa fare -> sportello aggiorno le posizioni debitorie -> catalogo aggiorno riferimento contabili e propago l'aggiornamento a modric.
							questo servizio fa quello che ti serve PropagazioneServiceImpl
						 */
						} else {
							jobContext.error ( methodName, "Il servizio di catalog di aggiornamento del dato specifico ha ritornato errore, Modric non sara' aggiornato" );
							jobContext.error ( methodName, "IdTassonomia: " +tassonomia.getId () );
						}
					}
				} else {
					loggaScarto ( "Il dato specifico riscossione non contiene una sottostringa aspettata", tassonomia.getId () );
				}
			} else {
				loggaScarto ( "Il codiceTipologiaDatoSpecificoRiscossione non e' nel formato corretto", tassonomia.getId () );
			}
		}
	}

	private void loggaScarto ( String motivo, long tassonomiaId ) {
		jobContext.error ( "doExecute", "___________________________________" );
		jobContext.error ( "doExecute", "Scartato record tassonomia con id=[" + tassonomiaId + "] per il seguente motivo: " + motivo );
		jobContext.error ( "doExecute", "___________________________________" );
	}
}
