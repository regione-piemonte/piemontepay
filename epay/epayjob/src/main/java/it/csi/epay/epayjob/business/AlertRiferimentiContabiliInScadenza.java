/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.business;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.csi.epay.epayjob.model.CodiceVersamento;
import it.csi.epay.epayjob.model.EnteRiferimentiContabiliInScadenza;
import it.csi.epay.epayjob.model.JobContext;
import it.csi.epay.epayjob.model.RiferimentoContabile;
import it.csi.epay.epayservices.interfaces.ejb.JobFacade;
import it.csi.epay.epayservices.interfaces.ejb.MailFacade;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.Mail;
import it.csi.epaypa.alertRiferimentiContabiliInScadenza.CatalogServiceResponse;
import it.csi.epaypa.alertRiferimentiContabiliInScadenza.EntiRiferimentiContabiliInScadenzaOutput;
import it.csi.epaypa.alertRiferimentiContabiliInScadenza.RiferimentiContabiliInScadenzaOutput;
import it.csi.epaypa.alertRiferimentiContabiliInScadenza.RiferimentoContabileInScadenzaInput;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class AlertRiferimentiContabiliInScadenza {

    public static final int TIMEOUT = 60000;
    private static final String CONFIG_PROPERTIES = "config.properties";
    private final JobContext context;
    private Properties properties;
	private MailFacade mailFacade;
	private String urlEnti; 
	private String urlRifContabili; 
	
    public AlertRiferimentiContabiliInScadenza ( JobContext context, JobFacade jobFacade , MailFacade mailFacade) throws IllegalArgumentException, NoDataException {
		super();
        this.context = context;

        final String methodName = "constructor";
        try {
            context.infoStart ( methodName );

            this.mailFacade = mailFacade;
            
            try ( InputStream inputStream = this.getClass ().getClassLoader ().getResourceAsStream ( CONFIG_PROPERTIES ) ) {
                properties = new Properties ();
                properties.load ( inputStream );
                
                urlEnti = properties.getProperty("service.tassonomia.enti.endpoint");
                urlRifContabili = properties.getProperty("service.tassonomia.rif.contabili.endpoint");
                
            } catch ( IOException e ) {
                context.debug ( methodName, "errore lettura parametri. " + e.getMessage () );
                throw new RuntimeException ( "Errore lettura parametri: " + e.getMessage () );
            }

            if ( properties.isEmpty () ) {
                context.debug ( methodName, "File di properties non trovato. L'elenco e' vuoto!" );
            }
        } catch ( Throwable t ) {
            context.error ( methodName, "Errore nel metodo " + methodName, t );
        } finally {
            context.infoEnd ( methodName );
        }
	}

    public void execute () throws Exception {
        final String methodName = "execute";
        context.infoStart ( methodName );
        try {
            doExecute ();
        } catch ( Throwable t ) {
            context.error ( methodName, "Errore nell'esecuzione del Job", t );
            throw t;
        } finally {
            context.infoEnd ( methodName );
        }
    }

    private void doExecute () throws Exception {
        final String methodName = "doExecute";
        Date startTime = new Date ();
        context.info ( methodName, "avvio elaborazione: " + startTime );
        
        
        // PRIMA PARTE ---------------------------------------------------
        //richiamamre servizio catalog per ottenre gli enti:
        
      
       
        String basicAuth =properties.getProperty("service.tassonomia.auth");
        InputStream is= getRestService(urlEnti, "", basicAuth);
        ObjectMapper mapper = new ObjectMapper();
        EntiRiferimentiContabiliInScadenzaOutput output = mapper.readValue(is, EntiRiferimentiContabiliInScadenzaOutput.class);
        
        if ( "000".equals ( output.getCodiceEsito () ) && null != output.getEnti () && !output.getEnti ().isEmpty () ) {
            //          Ciclo su ogni ente per andare su catalog e ottenere i riferimento contabili in scadenza :

            for ( EnteRiferimentiContabiliInScadenza ente: output.getEnti () ) {

                context.info ( ente.getIdEnte () );
                
                RiferimentoContabileInScadenzaInput inputRifContabile = new RiferimentoContabileInScadenzaInput ();

                inputRifContabile.setIdEnte ( ente.getIdEnte () );

                InputStream is2 = getRestService ( urlRifContabili, inputRifContabile, basicAuth );
                //        		List<RiferimentoContabile> riferimentiContabiliResponse = Arrays.asList(mapper.readValue(response2.getData(), RiferimentoContabile[].class));

                RiferimentiContabiliInScadenzaOutput riferimentiContabiliResponse = mapper.readValue ( is2, RiferimentiContabiliInScadenzaOutput.class );
                if ( "000".equals ( riferimentiContabiliResponse.getCodiceEsito () )
                    && null != riferimentiContabiliResponse.getRiferimentiContabiliInScadenza ()
                    && !riferimentiContabiliResponse.getRiferimentiContabiliInScadenza ().isEmpty () ) {
                    List<CodiceVersamento> codiciVersamento = raggruppaPerCodVersamento ( riferimentiContabiliResponse.getRiferimentiContabiliInScadenza () );
                    //              inviare una mail all'ente stesso con l'elenco dei riferimenti contabili in scadenza
                    if ( StringUtils.isNotBlank ( ente.getEmailAddress () ) ) {
                        inviaMailEnte ( ente, codiciVersamento );
                    } else {
                        context.error ( methodName, "Indirizzo email non valolizzato per l'ente: " + ente.getCodiceFiscale () + " " + ente.getDenominazione ());
                    }
                } else {
                    context.error ( methodName,
                        "Nessun riferimento contabile in scadenza trovato per l'ente: " + ente.getCodiceFiscale () + " " + ente.getDenominazione () );
                }

            }
        } else {
            context.info ( methodName, "Non e' stato trovato nessun ente con riferimenti contabili in scadenza." );
        }

        long elapsedMs = ( new Date ().getTime () ) - startTime.getTime ();
        context.info ( methodName, "L'elaborazione ha impiegato " + ( elapsedMs / 1000.0 ) + " secondi" );
    }

	private void inviaMailEnte( EnteRiferimentiContabiliInScadenza ente, 
			List<CodiceVersamento> codiciVersamento) throws IOException, JsonParseException, JsonMappingException {
		   StringBuilder sb = new StringBuilder();

		
		if (null!= codiciVersamento && !codiciVersamento.isEmpty())
		{
			
			//crea il messaggio per la mail //Che messaggio andrebbe inviato? - vedi mail 22/07/2021
			sb.append ( getTxt ( "mail.alertriferimenticontabiliinscadenza.testo.01" ));
			 
			sb.append ( getTxt ( "mail.alertriferimenticontabiliinscadenza.testo.02" ));
			sb.append ( getTxt ( "mail.alertriferimenticontabiliinscadenza.testo.03", ente.getDenominazione() ));
			sb.append ( getTxt ( "mail.alertriferimenticontabiliinscadenza.testo.04", ente.getCodiceFiscale() ));
			sb.append ("\n");
			sb.append ("\n");
			
			
			
			for (CodiceVersamento codVersamento : codiciVersamento) {
				
				sb.append ( getTxt ( "mail.alertriferimenticontabiliinscadenza.testo.05", codVersamento.getCodiceVersamento() ));
				sb.append ("\n");
				sb.append ("\n");
				
				for (RiferimentoContabile riferimentoContabile : codVersamento.getRiferimentiContabili()) {
				
				sb.append ( getTxt ( "mail.alertriferimenticontabiliinscadenza.testo.07", riferimentoContabile.getDatoSpecificoRiscossione()));
				sb.append ( getTxt ( "mail.alertriferimenticontabiliinscadenza.testo.08", riferimentoContabile.getAnnoAccertamento() ));
				sb.append ( getTxt ( "mail.alertriferimenticontabiliinscadenza.testo.09", riferimentoContabile.getNumAccertamento() ));
				sb.append ( getTxt ( "mail.alertriferimenticontabiliinscadenza.testo.10", riferimentoContabile.getDataFineValidita() ));
				}
				
				sb.append ("\n");    
			}
			sb.append ("\n");
			sb.append ( getTxt ( "mail.alertriferimenticontabiliinscadenza.testo.11" ));
			sb.append ( getTxt ( "mail.alertriferimenticontabiliinscadenza.testo.12" ));
        			inviaMail(sb.toString(), ente.getEmailAddress()); 
		}
	}
	
    
    private List<CodiceVersamento> raggruppaPerCodVersamento(List<RiferimentoContabile> riferimentiContabiliResponse) {
    	
    	Map<String , CodiceVersamento> mapCodVers= new LinkedHashMap<String, CodiceVersamento>();
    	for (RiferimentoContabile rif: riferimentiContabiliResponse)
    	{
//    		CodiceVersamento codTemp= rif.getCodiceVersamento();
    		if (mapCodVers.containsKey(rif.getCodiceVersamento().getCodiceVersamento()))
    		{
    			CodiceVersamento codTemp= mapCodVers.get(rif.getCodiceVersamento().getCodiceVersamento());
    			codTemp.getRiferimentiContabili().add(rif);
    		}
    		else
    		{
    			CodiceVersamento codTemp= rif.getCodiceVersamento();
    			codTemp.getRiferimentiContabili().add(rif);
    			mapCodVers.put(rif.getCodiceVersamento().getCodiceVersamento(), codTemp);
    		}
    		
    		
    	}
    	
    	List<CodiceVersamento> listCodVers = new ArrayList<CodiceVersamento>(mapCodVers.values());
		return listCodVers;
	}
    

	private void inviaMail(String text, String to) {
		 final String methodName = "inviaMail";

    	try {
    		Mail mail = new Mail();


    		mail.setTo(to); //indirizzo dell'ente
    		mail.setSubject(getTxt ( "mail.alertriferimenticontabiliinscadenza.oggetto" )); // 
    		
    		mail.setFrom("no-reply.piemontepay@csi.it"); //??
    		mail.setText(text);//

    		// utilizzare mail facade
    		
    		mailFacade.inviaMail(mail);

    		
    		//Come gestire le eccezioni?
    	} catch (Exception e) {
    		context.error ( methodName, "Errore nell'invio mail: ",e);
    	} 


    }

	private HttpPost  getHttpPost(String url, String token) {
//		HttpPost postRequest = new HttpPost ( "" );
		HttpPost postRequest = new HttpPost ( url );
		 postRequest.addHeader ( HttpHeaders.CONTENT_TYPE, "application/json" );
	     postRequest.addHeader ( HttpHeaders.ACCEPT, "application/json" );
	     postRequest.addHeader ( HttpHeaders.AUTHORIZATION, "Basic "+token );
//	     postRequest.addHeader("X-authentication", token);
			return postRequest;
	}
	
	
	private HttpURLConnection  getHttpURLConnection(String url, String token) throws MalformedURLException, IOException {
		 HttpURLConnection urlConnection;
			
		    //Connect
	     urlConnection = (HttpURLConnection) ((new URL(url).openConnection()));
	     urlConnection.setDoOutput(true);
	     urlConnection.setRequestProperty("Content-Type", "application/json");
	     urlConnection.setRequestProperty("Authorization", "Basic "+token);
	     urlConnection.setRequestProperty("Accept", "application/json");
	     urlConnection.setRequestMethod("POST");
	     urlConnection.setConnectTimeout(TIMEOUT);
	     urlConnection.connect();
	     
	     return urlConnection;
	}
	
	
	
    
    
  //PROVVISORIO: logica ipotetica vuota, il client sara' esterno?
    private  CatalogServiceResponse  richiamaServizioRest( String url, String json, String token)
    {
    	 final String methodName = "richiamaServizioRest";
    	 context.info ( methodName, "avvio "+methodName );
    	 CatalogServiceResponse esito=  new CatalogServiceResponse();
//    	 ObjectMapper mapper = new ObjectMapper ();
    	HttpPost postRequest= getHttpPost(url,token);
		postRequest.setEntity ( new StringEntity( json, ContentType.APPLICATION_JSON ) );
		try ( CloseableHttpClient httpClient = HttpClients.createDefault ();
                CloseableHttpResponse response = httpClient.execute ( postRequest ) ) {

			context.info ( "Ottengo la risposta con stato: " + response.getStatusLine ().getStatusCode () );

                if ( response.getStatusLine ().getStatusCode () != HttpStatus.SC_OK ) {
                    esito = new CatalogServiceResponse ( String.valueOf ( response.getStatusLine ().getStatusCode () ),
                        response.getStatusLine ().getReasonPhrase () );
                } else {
//                    esito = mapper.readValue ( responseString, CatalogServiceResponse.class );
                    esito=  new CatalogServiceResponse ( String.valueOf ( response.getStatusLine ().getStatusCode () ),
                            response.getStatusLine ().getReasonPhrase () );
                    esito.setData(EntityUtils.toString ( response.getEntity () ));
                    context.info ( "L'esito dell'elaborazione del pdf e': " + esito.getDesEsito () );
                }
            } catch ( IOException e ) {
            	context.error ( "Errore nella chiamata", e );
            	esito = new CatalogServiceResponse ( "400", "Problemi nella chiamata al servizio:" + e.getMessage () );
            }
		return esito;
    	
    }
    
    public InputStream getRestService(String url, Object json, String token) throws MalformedURLException, IOException {
		String methodName = "getServicetest";
		context.info ( methodName, "avvio "+methodName );

			      
//		try {
						
		 HttpURLConnection urlConnection = getHttpURLConnection(url, token);
			
			String requestString = buildJsonInvio(json);
			
			OutputStream os = urlConnection.getOutputStream();
	        os.write(requestString.getBytes());
	        os.flush();
			
			int statusCode = urlConnection.getResponseCode();
			String message ="";
//			CodiciEsito ce = CodiciEsito.ERRORE_DATI_SPECIFICI_RISCOSSIONE;
			
			if (statusCode != 200) {
				switch (statusCode) {
				case 403: //Forbidden
					message="Service for address non attivo";
					context.info (methodName, "Service for address non attivo");					
                   
					
				case 400:
					message="Service for address: bad request. Check parameters: " + url;
					context.info (methodName, "Service for address: bad request. Check parameters: " + url);
//					throw new TassonomiaServiceException (ce.getCodice(), message);
						
				default:
					message="Service for address: unknown error. Status: "  + statusCode + " -- Check request: " + url;
					context.info (methodName, "Service for address: unknown error. Status: "  + statusCode + " -- Check request: " + url);			
//					throw new TassonomiaServiceException (ce.getCodice(), message);
				}
			}
			
			context.info (methodName, "End");
			return urlConnection.getInputStream();
	}
	
    
    
    public String buildJsonInvio(Object model)  {
		final String methodName = "buildJsonInvio";
		 context.info ( methodName, "avvio "+methodName );

		String result;
		try {

			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Include.NON_NULL);

			result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(model);
		}
		catch (Exception e) {
			context.error(methodName, "errore nella creazione del model json ", e);
			return "";
		}

		 context.info ( methodName, "fine "+methodName );
		return result;
	}
    
    
   
    
    private String getTxt(String propertyKey, String ... parametri) {
		String riga = properties.getProperty(propertyKey);
		try {
			int i = 0;
			for (String parametro : parametri) {
				context.debug ( "getTxt", "riga:" + riga );
				if (null!= parametro)
				{
					riga = riga.replace("$" + i++, parametro);
				}
				else
				{
					riga = riga.replace("$" + i++, "");
				}
				
			}
			return riga + "\n";
		} catch (Exception e) {
			throw new RuntimeException("Errore preparazione riga della mail (" + propertyKey + ")", e.getCause());
		}

	}
    
    
    
    
    
    
    
    
    
    

   
   


   
}
