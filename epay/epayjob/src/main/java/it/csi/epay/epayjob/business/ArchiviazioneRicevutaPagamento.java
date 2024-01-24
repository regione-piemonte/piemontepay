/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.business;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import it.csi.epay.epayjob.business.mail.InviaEmailArchiviazioneRicevutaPagamento;
import it.csi.epay.epayjob.model.DocumentResponse;
import it.csi.epay.epayjob.model.DocumentiDocmeDto;
import it.csi.epay.epayjob.utilities.LogUtil;
import it.csi.epay.epayservices.interfaces.ejb.JobFacade;
import it.csi.epay.epayservices.interfaces.ejb.MailFacade;
import it.csi.epay.epayservices.model.ElaborazioneDto;
import it.csi.epay.epayservices.model.QuietanzaDaElaborareDto;
import org.apache.commons.lang3.time.DateUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class ArchiviazioneRicevutaPagamento {

	private final LogUtil log;

	private final JobFacade jobFacade;

	private static final String CONFIG_PROPERTIES = "config.properties";

	private static final String CHARSET = java.nio.charset.StandardCharsets.UTF_8.name ();

	private static final int TIMEOUT = 60000;

	private String nrTentativiGiornalieri;

	private String nrTotaleGiorniTentativi;

	private String urlChiamataDocme;

	private String ente;

	private String consumer;

	private String clientId;

	private String secret;

	private String tokenUrl;

	private final MailFacade mailFacade;

	private String emailTesto;

	private String emailOggetto;

	private String emailTo;

	private static final String pattern = "dd/MM/yyyy";

	public ArchiviazioneRicevutaPagamento ( JobFacade jobFacade, MailFacade mailFacade ) {
		super ();
		final String methodName = "constructor";
		log = new LogUtil ( this.getClass () );
		this.jobFacade = jobFacade;
		this.mailFacade = mailFacade;
		try {
			log.infoStart ( methodName );
			InputStream inputStream = this.getClass ().getClassLoader ().getResourceAsStream ( CONFIG_PROPERTIES );
			Properties properties = new Properties ();
			properties.load ( inputStream );

			nrTentativiGiornalieri = properties.getProperty ( "service.archiviazione.ricevuta.pagamento.nrTentativiGiornalieri" );
			nrTotaleGiorniTentativi = properties.getProperty ( "service.archiviazione.ricevuta.pagamento.nrTotaleGiorniTentativi" );
			urlChiamataDocme = properties.getProperty ( "service.archiviazione.ricevuta.pagamento.url.chiamata.docme" );
			ente = properties.getProperty ( "service.archiviazione.ricevuta.pagamento.ente.chiamata.docme" );
			consumer = properties.getProperty ( "service.archiviazione.ricevuta.pagamento.consumer.chiamata.docme" );
			clientId = properties.getProperty ( "service.archiviazione.ricevuta.pagamento.apimanager.client.id" );
			secret = properties.getProperty ( "service.archiviazione.ricevuta.pagamento.apimanager.client.secret" );
			tokenUrl = properties.getProperty ( "service.archiviazione.ricevuta.pagamento.apimanager.client.token.url" );
			emailTesto = properties.getProperty ( "service.archiviazione.ricevuta.pagamento.email.testo.01" );
			emailOggetto = properties.getProperty ( "service.archiviazione.ricevuta.pagamento.email.oggetto" );
			emailTo = properties.getProperty ( "service.archiviazione.ricevuta.pagamento.email.to" );

			if ( properties.isEmpty () ) {
				log.error ( methodName, "File di properties non trovato. L'elenco e' vuoto!" );
			}
		} catch ( Throwable t ) {
			log.error ( methodName, "Errore nel metodo " + methodName, t );
		} finally {
			log.infoEnd ( methodName );
		}
	}

	public void execute () throws IOException {
		final String methodName = "execute";
		log.infoStart ( methodName );
		System.out.println ( "start - " + methodName );

		Date today = new Date ();
		Long id_quietanza; // corrisponde al valore del campo id_quietanza_da_archiviare della tabella omonima per i record in oggetto;
		// Elaborazioni = identificativo univoco generato dal batch;
		int totaleDocumentiElaborati = 0; // contatore per i documenti inviati a DOCME;
		int totaleDocumentiArchiviati = 0; // contatore per i documenti inviati correttamente a DOCME;
		int totaleDocumentiNonArchiviati = 0; // contatore per i documenti inviati a DOCME e che hanno restituito errore;
		Date dataAvvio = new Date (); // data di avvio del batch;
		Date dataTermine; // data di fine elaborazione del bach;

		int nTentativiGiornalieri = Integer.parseInt ( nrTentativiGiornalieri );
		int nTotaleGiorniTentativi = Integer.parseInt ( nrTotaleGiorniTentativi );
		// 2. Ottiene da epay_t_quietanza_da_elaborare la lista L delle quietanze da archiviare che soddisfano la condizione:
		// Inviare = Si AND !termini scaduti (n.d.r. !termini_scaduti significa: "restano ancora tentativi da fare")
		System.out.println ( methodName + " - getListaQuietanzeDaElaborare - start" );
		List<QuietanzaDaElaborareDto> list = jobFacade.getListaQuietanzeDaElaborare ( nTentativiGiornalieri, nTotaleGiorniTentativi );
		System.out.println ( methodName + " - getListaQuietanzeDaElaborare - end - numRecord:" + list.size () );
		ElaborazioneDto elaborazioneDto = new ElaborazioneDto ();
		for ( QuietanzaDaElaborareDto q : list ) {
			totaleDocumentiElaborati++;
			// 3. Finche' ci sono quietanze da considerare in L, partendo dalla prima
			if ( nTentativiGiornalieri > 0 ) {
				if ( null != q.getInvioFallitoGiornaliero () && DateUtils.isSameDay ( q.getInvioFallitoGiornaliero (), today ) ) {
					// saltare il record
					totaleDocumentiNonArchiviati++;
					System.out.println ( "id_quietanza" + q.getIdQuietanzaDaElaborare () + " scartata - tentativo fallito nella giornata odierna" );
					continue;
				} else if ( null != q.getInvioFallitoGiornaliero () && q.getInvioFallitoGiornaliero ().before ( today ) ) {
					q.setInvioFallitoGiornaliero ( today );
					q.setNrInvii ( 0 );
				}
			}
			id_quietanza = q.getIdQuietanzaDaElaborare ();
			// 3.1 Invia la quietanza a DOCME invocando un servizio ReST che espone DOCME
			SimpleDateFormat df = new SimpleDateFormat ( pattern );
			DocumentiDocmeDto documentiDocmeDto = new DocumentiDocmeDto ();
			documentiDocmeDto.setContent ( q.getRicevutaPdf () );
			documentiDocmeDto.setDescrizione ( q.getCausalePagamento () );
			if ( null != q.getDataInizioPagamento () ) {
				documentiDocmeDto.setDataInizio ( df.format ( q.getDataInizioPagamento () ) );
			}
			if ( null != q.getDataFinePagamento () ) {
				documentiDocmeDto.setDataFine ( df.format ( q.getDataFinePagamento () ) );
			}
			documentiDocmeDto.setFilename ( "Ricevuta_pdf" );
			documentiDocmeDto.setCfCittadino ( q.getCfCittadino () );
			documentiDocmeDto.setIdAmbito ( 3L );
			documentiDocmeDto.setIdTipologia ( 34L );
			documentiDocmeDto.setDataCreazione ( df.format ( today ) );
			// prima di inviarlo a docme serve autenticarsi
			// ottengo il token per la chiamata passando dall'api manager
			String token = getToken ();
			boolean esitoOK = true;
			System.out.println ( "id_quietanza in fase di elaborazione:" + id_quietanza );
			DocumentResponse documentResponse = sendQuietanzaAndGetUUID ( documentiDocmeDto, token );
			if ( null != documentResponse ) {
				// chiamata esito ok
				totaleDocumentiArchiviati++;
				q.setIdentificativoChiamata ( documentResponse.getUuid () ); // quello che mi risponde, ma non conosco lo schema
				System.out.println ( methodName + " - id_quietanza :" + id_quietanza + " con esito OK" );
			} else {
				// esito ko
				esitoOK = false;
				totaleDocumentiNonArchiviati++;
				System.out.println ( methodName + " - id_quietanza :" + id_quietanza + " con esito KO" );
			}
			// 3.2 Aggiorna con l'esito dell'archiviazione sul record della quietanza da archiviare
			if ( null == q.getDataPrimaChiamata () ) {
				q.setDataPrimaChiamata ( today );
			}
			q.setCodEsito ( esitoOK ? "000" : null ); // analisi dice altra cosa, ma docme non ci restituisce il codice esito
			q.setInviare ( null == documentResponse );
			if ( null != q.getNrInvii () ) {
				q.setNrInvii ( q.getNrInvii () + 1 );
			} else {
				q.setNrInvii ( 1 );
			}

			if ( nTentativiGiornalieri > 0 ) {
				if ( q.getNrInvii () >= nTentativiGiornalieri ) {
					if ( !esitoOK ) {
						q.setInvioFallitoGiornaliero ( today );
					}
				}
			} else {
				q.setInvioFallitoGiornaliero ( null );
			}

			if ( nTotaleGiorniTentativi > 0 ) {
				if ( q.getDataPrimaChiamata () != null ) {
					long diff = today.getTime () - q.getDataPrimaChiamata ().getTime ();
					long diffInDays = TimeUnit.DAYS.convert ( diff, TimeUnit.MILLISECONDS );
					if ( diffInDays == 1 ) {
						q.setNrGiorni ( (int) diffInDays );
					}
					if ( diffInDays >= 2 ) {
						q.setNrGiorni ( q.getNrGiorni () + 1 );
					}
				}
			}

			if ( q.getNrGiorni () != null && q.getNrGiorni () >= nTotaleGiorniTentativi && nTotaleGiorniTentativi > 0 ) {
				q.setTerminiScaduti ( true );
				InviaEmailArchiviazioneRicevutaPagamento inviaEmailArchiviazioneRicevutaPagamento =
								new InviaEmailArchiviazioneRicevutaPagamento ( mailFacade, emailOggetto, emailTesto, emailTo );
				inviaEmailArchiviazioneRicevutaPagamento.inviaMailResponceError ( id_quietanza );
				q.setInviare ( false ); // perche' sono scaduti i termini per inviare
			} else {
				q.setTerminiScaduti ( false );
			}

			//	Inserire nella tabella epay_r_quietanza_elaborazione un record con la relazione
			//	epay_t_elaborazione.id_elaborazione (FK tabella epay_t_elaborazione) e
			//	epay_t_quietanza_da_elaborare. id_quietanza_da_elaborare
			//	(FK tabella epay_t_quietanza_da_elaborare)
			elaborazioneDto = new ElaborazioneDto ();
			elaborazioneDto.setDataAvvio ( dataAvvio );
			elaborazioneDto.setDataTermine ( new Date () );
			elaborazioneDto.setDocumentiElaborati ( totaleDocumentiElaborati );
			elaborazioneDto.setDocumentiArchiviati ( totaleDocumentiArchiviati );
			elaborazioneDto.setDocumentiNonArchiviati ( totaleDocumentiNonArchiviati );
			elaborazioneDto = jobFacade.aggiornaQuietanzaDaElaborare ( q, elaborazioneDto );
		}
		System.out.println ( methodName + " - Aggiorno contatori" );
		// 3.3 Aggiorna contatori temporanei (sul numero totale di documenti inviati, archiviati, falliti, ecc. in epay_t_elaborazioni)
		dataTermine = new Date ();
		elaborazioneDto.setDataAvvio ( dataAvvio );
		elaborazioneDto.setDataTermine ( dataTermine );
		elaborazioneDto.setDocumentiElaborati ( totaleDocumentiElaborati );
		elaborazioneDto.setDocumentiArchiviati ( totaleDocumentiArchiviati );
		elaborazioneDto.setDocumentiNonArchiviati ( totaleDocumentiNonArchiviati );
		elaborazioneDto = jobFacade.saveElaborazione ( elaborazioneDto );
		log.info ( methodName, "Inserito record elaborazione con id=" + elaborazioneDto.getIdElaborazione () );
		System.out.println ( methodName + "Inserito record elaborazione con id=" + elaborazioneDto.getIdElaborazione () );
		log.infoEnd ( methodName );
	}

	private String getToken () {
		String methodName = "getToken";
		log.infoStart ( methodName );
		final String auth = clientId + ":" + secret;
		String authentication = Base64.getEncoder ().encodeToString ( auth.getBytes () );
		String content = "grant_type=client_credentials";
		BufferedReader reader = null;
		HttpURLConnection connection = null;
		String jsonValue = "";
		try {
			URL url = new URL ( tokenUrl );
			connection = (HttpURLConnection) url.openConnection ();
			connection.setRequestMethod ( "POST" );
			connection.setDoOutput ( true );
			connection.setRequestProperty ( "Authorization", "Basic " + authentication );
			connection.setRequestProperty ( "Content-Type", "application/x-www-form-urlencoded" );
			connection.setRequestProperty ( "Accept", "application/json" );
			PrintStream os = new PrintStream ( connection.getOutputStream () );
			os.print ( content );
			os.close ();
			reader = new BufferedReader ( new InputStreamReader ( connection.getInputStream () ) );
			String line;
			StringWriter out = new StringWriter ( connection.getContentLength () > 0 ? connection.getContentLength () : 2048 );
			while ( ( line = reader.readLine () ) != null ) {
				out.append ( line );
			}
			jsonValue = out.toString ();
		} catch ( Exception e ) {
			log.error ( methodName, "Error : " + e.getMessage () );
		} finally {
			if ( reader != null ) {
				try {
					reader.close ();
				} catch ( IOException ignored ) {
				}
			}
			assert connection != null;
			connection.disconnect ();
		}

		Gson gson = new GsonBuilder ().create ();
		JsonObject jsonObject = gson.fromJson ( jsonValue, JsonObject.class );
		String accessToken = jsonObject.get ( "access_token" ).getAsString ();

		log.infoEnd ( methodName );
		return accessToken;
	}

	/*
	 * ritorna l'eventuale uuid
	 */
	private DocumentResponse sendQuietanzaAndGetUUID ( DocumentiDocmeDto documentiDocmeDto, String token ) throws IOException {
		String methodName = "sendQuietanzaAndGetUUID";
		log.infoStart ( methodName );

		DocumentResponse documentResponse = null;
		HttpURLConnection urlConnection = getHttpURLConnection ( urlChiamataDocme, ente, consumer, token );

		String requestString = buildJsonInvio ( documentiDocmeDto );

		OutputStream os = urlConnection.getOutputStream ();
		os.write ( requestString.getBytes ( CHARSET ) );
		os.flush ();

		int statusCode = urlConnection.getResponseCode ();
		String message;

		if ( statusCode == 200 ) {
			BufferedReader reader = new BufferedReader ( new InputStreamReader ( urlConnection.getInputStream () ) );
			String line;
			StringBuilder response = new StringBuilder ();
			while ( ( line = reader.readLine () ) != null ) {
				response.append ( line );
			}
			reader.close ();

			String uuidJson = response.toString ();
			Gson gson = new Gson ();
			documentResponse = gson.fromJson ( uuidJson, DocumentResponse.class );
			log.info ( methodName, "Response Data: " + uuidJson );
			System.out.println ( methodName + "Response Data: " + uuidJson );
		} else {
			switch ( statusCode ) {
			case 403:
				message = "Service for address non attivo";
				log.info ( methodName, message );
				System.out.println ( methodName + "Response Data: " + message + " - statusCode del server:" + statusCode );
				break;
			case 400:
				message = "Service for address: bad request. Check parameters: " + urlChiamataDocme;
				log.info ( methodName, message );
				System.out.println ( methodName + "Response Data: " + message + " - statusCode del server:" + statusCode );
				break;
			default:
				message = "Service for address: unknown error. Status: " + statusCode + " -- Check request: " + urlChiamataDocme;
				log.info ( methodName, message );
				System.out.println ( methodName + "Response Data: " + message + " - statusCode del server:" + statusCode );
				break;
			}
		}

		log.infoEnd ( methodName );
		return documentResponse;
	}

	private HttpURLConnection getHttpURLConnection ( String url, String ente, String consumer, String token ) throws IOException {
		HttpURLConnection urlConnection = (HttpURLConnection) ( new URL ( url ).openConnection () );
		urlConnection.setDoOutput ( true );
		urlConnection.setRequestProperty ( "Content-Type", "application/json;charset=" + CHARSET );
		urlConnection.setRequestProperty ( "Accept", "application/json" );
		urlConnection.setRequestProperty ( "ente", ente );
		urlConnection.setRequestProperty ( "consumer", consumer );
		urlConnection.setRequestProperty ( "Authorization", "Bearer " + token );
		urlConnection.setRequestMethod ( "POST" );
		urlConnection.setConnectTimeout ( TIMEOUT );
		urlConnection.setRequestProperty ( "Accept-Charset", CHARSET );
		urlConnection.connect ();
		return urlConnection;
	}

	private String buildJsonInvio ( Object model ) {
		final String methodName = "buildJsonInvio";
		log.infoStart ( methodName );
		String result;
		try {
			ObjectMapper mapper = new ObjectMapper ();
			mapper.setSerializationInclusion ( JsonInclude.Include.NON_NULL );
			result = mapper.writerWithDefaultPrettyPrinter ().writeValueAsString ( model );
		} catch ( Exception e ) {
			log.error ( methodName, "Errore nella creazione del model json ", e );
			return "{}";
		}
		log.infoEnd ( methodName );
		return result;
	}
}
