/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayjob.business;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.csi.epay.epayjob.model.JobContext;
import it.csi.epay.epayjob.model.notify.Message;
import it.csi.epay.epayservices.interfaces.ejb.JobFacade;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.EntePassphrase;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.TracciaturaNotify;
import it.csi.epaypa.notificheIoApp.InviaNotificaServiceResponse;
import it.csi.epaypa.notificheIoApp.NotificaIoAppBuildMessage;


public class TrasmettiNotificheIOAPP extends RtXml {


    private static final String CONFIG_PROPERTIES = "config.properties";

    private JobContext context;
    private Properties properties;
	private JobFacade jobFacade;
	private String url; 
    public TrasmettiNotificheIOAPP ( JobContext context, JobFacade jobFacade ) throws IllegalArgumentException, NoDataException {
		super();
        this.context = context;
        final String methodName = "constructor";
        
       
        try {
            context.infoStart ( methodName );

            this.jobFacade = jobFacade;

            try ( InputStream inputStream = this.getClass ().getClassLoader ().getResourceAsStream ( CONFIG_PROPERTIES ) ) {
                properties = new Properties ();
                properties.load ( inputStream );
                 url = properties.getProperty("service.notify.endpoint");
//                log.info(methodName, "url: "+url);
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
        
//        List<Long> idPagamentiEnte = new LinkedList<Long>();
        List<EntePassphrase> enti = new LinkedList<EntePassphrase>();
        NotificaIoAppBuildMessage builderMessage = new NotificaIoAppBuildMessage();
        
//       
        try {
//        //richiama epayservices per aver enti con notifiche da inviare e passphrase ente da passare a Notify
        enti = jobFacade.ricercaEntiPerInvioNotifiche(); // fare un metodo che mocki il servizio
        context.info ( methodName, "numero enti: " + (null!= enti? enti.size():0 ));
    	
    	} catch (NoDataException nde) {
    		context.info(methodName, "Nessun ente trovato abilitato all'invio: "+nde);
    		return;
		}
        catch (Exception e) {
    		context.info(methodName, "Non e' stato possibile estrarre gli enti: "+e);
		}
//        //cliclo sugli enti e chiedo i pagamenti per ogni ente No Paginazione
        for (EntePassphrase ente : enti) {
        	context.info(methodName, "elaborazione ente " + ente.getIdEnte());
        	String bulkId=  UUID.randomUUID().toString();
        	List<Message> model = new LinkedList<Message>();
        	List<TracciaturaNotify> tracciatureNotify= new LinkedList<TracciaturaNotify>();
        	try {
        		List<Pagamento> pagamentiEnte = jobFacade.ricercaPagamentiValidiPerEnte(ente.getIdEnte());

        		if (null!= pagamentiEnte && !pagamentiEnte.isEmpty())
        		{
        			for (Pagamento pagamento : pagamentiEnte) {
        				context.info ( methodName, "pagamento: " + pagamento.getIdPagamento());
        				////							 creare il body per la richiesta massiva : scorro tutti i pagamenti creo una chiamata bulk (massiva) per tutti i pagamenti dell'ente.
        				////							 mappare nell'oggetto Model il pagamento e  inserirlo in una lista 
        				String uuid= UUID.randomUUID().toString();
        				Message msg= builderMessage.buildMessage(pagamento, uuid, bulkId);
        				model.add(msg);
        				context.info ( methodName, "message: " + msg.toString());
        				tracciatureNotify.add(buildTracciaturaNotify(pagamento, uuid, bulkId));
        			}
        		}
        		else
        		{
        			context.error(methodName, "nessun tipo pagamento valido trovato  per invio notifica per ente: "+ente.getIdEnte());
        		}

        	} catch (NoDataException nde) {
        		context.error(methodName, "nessun tipo pagamento valido trovato  per invio notifica per ente: "+ente.getIdEnte(), nde);
        		
			}
        	catch (IOException ie) {
        		context.error(methodName, "Errore in fase di reperimento del messaggio per ente: "+ente.getIdEnte()+ ": ", ie);
			}
        	 catch (Exception e) {
         		context.error(methodName, "Non e' stato possibile estrarre i pagamenti per ente: "+ente.getIdEnte()+ ": ", e);
     		}
        	
        	
        	if (null!=  tracciatureNotify && !tracciatureNotify.isEmpty())
        	{
        		try
        		{
        			String json= buildJsonInvio(model);
        			//chiamo notify e  invio della notifica
        			InviaNotificaServiceResponse esito= inviaNotificaService(json ,  new String(ente.getPassphrase()));
        			//        		
        			for (TracciaturaNotify tracciaturaNotify : tracciatureNotify)
        			{
        				// tracciaturaNotify.setEsitoStatoInvioNotify("Ok");	
        				tracciaturaNotify.setEsitoStatoInvioNotify(esito.getCodEsito());
        			}
        			jobFacade.inserisciTracciatureNotify(tracciatureNotify);
        		}
        		catch (Exception e) {
        			context.error ( methodName, "errore nell'invio delle notifiche per ente "+ente.getIdEnte()+": ", e );
        		}
        	}
        }
      
       
        long elapsedMs = ( new Date ().getTime () ) - startTime.getTime ();
        context.info ( methodName, "L'elaborazione ha impiegato " + ( elapsedMs / 1000.0 ) + " secondi" );
    }

    private TracciaturaNotify buildTracciaturaNotify(Pagamento pagamento, String uuid, String bulkId) {

    	TracciaturaNotify tracciaturaNotify = new TracciaturaNotify();

    	tracciaturaNotify.setIdPagamento(pagamento.getIdPagamento());
    	tracciaturaNotify.setDataInivioNotify(new Timestamp ( new java.util.Date ().getTime () ));
    	tracciaturaNotify.setMessageUuid(uuid);
    	tracciaturaNotify.setBulkId(bulkId);
    	return tracciaturaNotify;
    }

	private InviaNotificaServiceResponse  inviaNotificaService( String json, String token) {
		  final String methodName = "inviaNotificaService";
		  InviaNotificaServiceResponse inviaNotificaServiceResponse = null;
		HttpPost postRequest= getHttpPost(token);
		postRequest.setEntity ( new StringEntity( json, ContentType.APPLICATION_JSON ) );
		
		try ( CloseableHttpClient httpClient = HttpClients.createDefault ();
		        CloseableHttpResponse response = httpClient.execute ( postRequest ) ) {
			context.info ( methodName, "Ottengo la risposta con stato: " + response.getStatusLine ().getStatusCode () );

                    if ( response.getStatusLine ().getStatusCode () == HttpStatus.SC_CREATED 
                    		||  response.getStatusLine ().getStatusCode () == HttpStatus.SC_OK) {
                    	inviaNotificaServiceResponse = new InviaNotificaServiceResponse ( "OK" ,
                            response.getStatusLine ().getReasonPhrase () );
                    	context.info ( methodName, "L'esito dell'invio notifica e': " + inviaNotificaServiceResponse.getDesEsito() );
                    } else {
                    	inviaNotificaServiceResponse = new InviaNotificaServiceResponse ("KO", response.getStatusLine ().getReasonPhrase ());
                        context.info ( methodName, "L'esito dell'invio notifica e': " + inviaNotificaServiceResponse.getDesEsito() );
                    }
		    } catch ( IOException e ) {
		    	context.error ( methodName, "Errore nella chiamata", e );
		    	inviaNotificaServiceResponse = new InviaNotificaServiceResponse ( "400", "Problemi nella chiamata al servizio:" + e.getMessage () );
		    }
		return inviaNotificaServiceResponse;
	}

	private HttpPost  getHttpPost(String token) {
//		HttpPost postRequest = new HttpPost ( "http://tst-notify.csi.it/notify-mb/api/v1/topics/messages" );
		HttpPost postRequest = new HttpPost ( url );
		 postRequest.addHeader ( HttpHeaders.CONTENT_TYPE, "application/json" );
	     postRequest.addHeader ( HttpHeaders.ACCEPT, "application/json" );
//	     postRequest.addHeader ( HttpHeaders.AUTHORIZATION, token );
	     postRequest.addHeader("X-authentication", token);
			return postRequest;
	}
    
    
    public String buildJsonInvio(List<Message> model)  {
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
    
   


    

	
	

	

}
