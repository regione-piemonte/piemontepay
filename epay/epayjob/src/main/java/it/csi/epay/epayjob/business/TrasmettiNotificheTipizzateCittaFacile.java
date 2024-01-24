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
import it.csi.epay.epayservices.interfaces.ejb.ConfigurazioneFacade;
import it.csi.epay.epayservices.interfaces.ejb.JobFacade;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.Configurazione;
import it.csi.epay.epayservices.model.EntePassphrase;
import it.csi.epay.epayservices.model.Pagamento;
import it.csi.epay.epayservices.model.TipoPagamentoPassphrase;
import it.csi.epay.epayservices.model.TracciaturaNotifyCittaFacile;
import it.csi.epaypa.notificheIoApp.InviaNotificaServiceResponse;
import it.csi.epaypa.notificheIoApp.NotificaTipizzataCittaFacileBuildMessage;


public class TrasmettiNotificheTipizzateCittaFacile extends RtXml {


    private static final String CONFIG_PROPERTIES = "config.properties";
    
    private static final String CONFIG_MAX_NUM_CAR_DESC_COV_INVIO_NOTIFY = "CONFIG_MAX_NUM_CAR_DESC_COV_INVIO_NOTIFY";

    private static final String COD_ENTE_DEFALUT = "PagoPa";

    private JobContext context;
    private Properties properties;
	private JobFacade jobFacade;
	private ConfigurazioneFacade configurazioneFacade;
	private String url; 
    public TrasmettiNotificheTipizzateCittaFacile ( JobContext context, JobFacade jobFacade, ConfigurazioneFacade configurazioneFacade ) throws IllegalArgumentException, NoDataException {
		super();
        this.context = context; 
        final String methodName = "constructor";
        
       
        try {
            context.infoStart ( methodName );

            this.jobFacade = jobFacade;
            this.configurazioneFacade = configurazioneFacade;

            try ( InputStream inputStream = this.getClass ().getClassLoader ().getResourceAsStream ( CONFIG_PROPERTIES ) ) {
                properties = new Properties ();
                properties.load ( inputStream );
                 url = properties.getProperty("service.notify.endpoint");
                 System.out.println (methodName+ " -url: "+url);
//                log.info(methodName, "url: "+url);
            } catch ( IOException e ) {
                context.debug ( methodName, "errore lettura parametri. " + e.getMessage () );
                System.out.println (methodName+ " - errore lettura parametri. " + e.getMessage ());
                throw new RuntimeException ( "Errore lettura parametri: " + e.getMessage () );
            }

            if ( properties.isEmpty () ) {
                context.debug ( methodName, "File di properties non trovato. L'elenco e' vuoto!" );
                System.out.println (methodName+ "File di properties non trovato. L'elenco e' vuoto!");
            }
        } catch ( Throwable t ) {
            context.error ( methodName, "Errore nel metodo " + methodName, t );
            System.out.println (methodName+ " -Errore nel metodo "+t.getMessage ());
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
            System.out.println (methodName+ " -Errore nell'esecuzione del Job "+t.getMessage ());
            throw t;
        } finally {
            context.infoEnd ( methodName );
        }
    }

    private void doExecute () throws Exception {
        final String methodName = "doExecute";
        Date startTime = new Date ();
        context.info ( methodName, "avvio elaborazione: " + startTime );
        System.out.println (methodName+ " -avvio elaborazione:  "+startTime);
        
//        List<Long> idPagamentiEnte = new LinkedList<Long>();
        List<EntePassphrase> enti = new LinkedList<EntePassphrase>();
        NotificaTipizzataCittaFacileBuildMessage builderMessage = new NotificaTipizzataCittaFacileBuildMessage();
        
//       
        try {
//        //richiama epayservices per aver enti con notifiche da inviare e passphrase ente da passare a Notify
        enti = jobFacade.ricercaEntiNotifyCittaFacile (); // fare un metodo che mocki il servizio
        context.info ( methodName, "numero enti: " + (null!= enti? enti.size():0 ));
        System.out.println (methodName+ " - numero enti:   "+(null!= enti? enti.size():0 ));
       
    	
    	} catch (NoDataException nde) {
    		context.info(methodName, "Nessun ente trovato abilitato all'invio: "+nde);
    		 System.out.println (methodName+ " - Nessun ente trovato abilitato all'invio:   "+nde.getMessage ());
    		return;
		}
        catch (Exception e) {
    		context.info(methodName, "Non e' stato possibile estrarre gli enti: "+e);
    		 System.out.println (methodName+ " - Non e' stato possibile estrarre gli enti:   "+e.getMessage ());
		}
        int maxNumDesc  = estraiNumeroMassimoLunghezzaDesc ();
//        //cliclo sugli enti e chiedo i pagamenti per ogni ente No Paginazione
        for (EntePassphrase ente : enti) {
            context.info(methodName, "elaborazione ente " + ente.getIdEnte());
            System.out.println (methodName+ " - elaborazione ente:   "+ente.getIdEnte());
            List<TipoPagamentoPassphrase> tipiPagamentoEnte = new LinkedList<TipoPagamentoPassphrase>();
            //          da mettere quii?
            

            try
            {
                tipiPagamentoEnte = jobFacade.ricercaTipiPagamentoNotifyCittaFacilePerEnte ( ente.getIdEnte ()); 
            }
            catch (NoDataException nde) {
                context.info(methodName, "Nessun tipo pagamento trovato abilitato all'invio: "+nde);
                System.out.println (methodName+ " - Nessun tipo pagamento trovato abilitato all'invio:   "+nde.getMessage ());

            }
            catch (Exception e) {
                context.info(methodName, "Non e' stato possibile estrarre tipi pagamento per ente: "+e);
                System.out.println (methodName+ " - Non e' stato possibile estrarre tipi pagamento per ente:   "+e.getMessage ());
            }


            if (null!= tipiPagamentoEnte && !tipiPagamentoEnte.isEmpty())
            {
                for (TipoPagamentoPassphrase tipoPagamento : tipiPagamentoEnte) {

                    //        	    
                    List<Message> model = new LinkedList<Message>();
                    List<TracciaturaNotifyCittaFacile> tracciatureNotifyCittaFacile= new LinkedList<TracciaturaNotifyCittaFacile>();
                    String bulkId=  UUID.randomUUID().toString();
//                    TODO controllare che la descrizione  sia della lunghezza appropriata
                    try {
                        List<Pagamento> pagamentiEnte = jobFacade.ricercaPagamentiCittaFacileValidiPerTipoPagamento(tipoPagamento.getIdTipoPagamento ());

                        if (null!= pagamentiEnte && !pagamentiEnte.isEmpty())
                        {
                            for (Pagamento pagamento : pagamentiEnte) {
                                context.info ( methodName, "pagamento: " + pagamento.getIdPagamento());
                                System.out.println (methodName+ " - pagamento:   "+pagamento.getIdPagamento());
                                ////                             creare il body per la richiesta massiva : scorro tutti i pagamenti creo una chiamata bulk (massiva) per tutti i pagamenti dell'ente.
                                ////                             mappare nell'oggetto Model il pagamento e  inserirlo in una lista 
                                if (pagamento.getTipoPagamento ().getDescrizioneTextCov ().length ()<=maxNumDesc)
                                {
                                    String uuid= UUID.randomUUID().toString();
                                    Message msg= builderMessage.buildMessage(pagamento, uuid, bulkId);
                                    model.add(msg);
                                    tracciatureNotifyCittaFacile.add(buildTracciaturaNotify(pagamento, uuid, bulkId));
                                }
                                else
                                {
                                    context.info(methodName, "Lunghezza massima descrizione superata: "+maxNumDesc);
                                    System.out.println (methodName+ " - Lunghezza massima descrizione superata:   "+maxNumDesc);
                                }
                              
                            }
                        }
                        else
                        {
                            context.error(methodName, "nessun tipo pagamento valido trovato  per invio notifica tipizzata per tipo pagamento: "+tipoPagamento.getIdTipoPagamento ());
                            System.out.println (methodName+ " - nessun tipo pagamento valido trovato  per invio notifica tipizzata per tipo pagamento: "+tipoPagamento.getIdTipoPagamento ());
                        }

                    } catch (NoDataException nde) {
                        context.error(methodName, "nessun tipo pagamento valido trovato  per invio notifica tipizzata per tipo pagamento: "+tipoPagamento.getIdTipoPagamento (), nde);
                        System.out.println (methodName+ " -nessun tipo pagamento valido trovato  per invio notifica tipizzata per tipo pagamento: "+tipoPagamento.getIdTipoPagamento ()+nde.getMessage ());

                    }
                    catch (IOException ie) {
                        context.error(methodName, "Errore in fase di reperimento del messaggio per tipo pagamento: "+tipoPagamento.getIdTipoPagamento ()+ ": ", ie);
                        System.out.println (methodName+ " -Errore in fase di reperimento del messaggio per tipo pagamento: : "+tipoPagamento.getIdTipoPagamento ()+ie.getMessage ());
                    }
                    catch (Exception e) {
                        context.error(methodName, "Non e' stato possibile estrarre i pagamenti per tipo pagamento: "+tipoPagamento.getIdTipoPagamento ()+ ": ", e);
                        System.out.println (methodName+ " -Non e' stato possibile estrarre i pagamenti per tipo pagamento: "+tipoPagamento.getIdTipoPagamento ()+e.getMessage ());
                    }


                                  if (null!=  tracciatureNotifyCittaFacile && !tracciatureNotifyCittaFacile.isEmpty())
                                  {
                                      try
                                      {
                                          String json= buildJsonInvio(model);
                                          System.out.println (json);
                    //                      //chiamo notify e  invio della notifica
                                          InviaNotificaServiceResponse esito= inviaNotificaService(json ,  new String(tipoPagamento.getPassphrase()));
//                                          //         
//                                          InviaNotificaServiceResponse esito= new InviaNotificaServiceResponse ( "OK", "descrizione prova a buon fine" );
//                                          InviaNotificaServiceResponse esito= new InviaNotificaServiceResponse ( "KO", "descrizione prova non a buon fine" );
                                          for (TracciaturaNotifyCittaFacile tracciaturaNotifyCittaFacile : tracciatureNotifyCittaFacile)
                                          {
                                              // tracciaturaNotify.setEsitoStatoInvioNotify("Ok");    
                                              tracciaturaNotifyCittaFacile.setEsitoStatoInvioNotify(esito.getCodEsito());
                                          }
                                          jobFacade.inserisciTracciatureNotifyCittaFacile ( tracciatureNotifyCittaFacile );
                                      }
                                      catch (Exception e) {
                                          context.error ( methodName, "errore nell'invio delle notifiche tipizzate per ente "+ente.getIdEnte()+": ", e );
                                          System.out.println (methodName+ " -errore nell'invio delle notifiche tipizzate per ente: "+ente.getIdEnte ()+e.getMessage ());
                                      }
                                  }

                }
            }

        }
        long elapsedMs = ( new Date ().getTime () ) - startTime.getTime ();
        context.info ( methodName, "L'elaborazione ha impiegato " + ( elapsedMs / 1000.0 ) + " secondi" );
        System.out.println (methodName+ " -L'elaborazione ha impiegato: "+ ( elapsedMs / 1000.0 ) + " secondi" );
    }

    private TracciaturaNotifyCittaFacile buildTracciaturaNotify(Pagamento pagamento, String uuid, String bulkId) {

        TracciaturaNotifyCittaFacile tracciaturaNotify = new TracciaturaNotifyCittaFacile();

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
			System.out.println (methodName+ " -Ottengo la risposta con stato: "+ response.getStatusLine ().getStatusCode () );

                    if ( response.getStatusLine ().getStatusCode () == HttpStatus.SC_CREATED 
                    		||  response.getStatusLine ().getStatusCode () == HttpStatus.SC_OK) {
                    	inviaNotificaServiceResponse = new InviaNotificaServiceResponse ( "OK" ,
                            response.getStatusLine ().getReasonPhrase () );
                    	context.info ( methodName, "L'esito dell'invio notifica tipizzata e': " + inviaNotificaServiceResponse.getDesEsito() );
                        System.out.println (methodName+ " -L'esito dell'invio notifica tipizzata e': "+ inviaNotificaServiceResponse.getDesEsito() );
                    } else {
                    	inviaNotificaServiceResponse = new InviaNotificaServiceResponse ("KO", response.getStatusLine ().getReasonPhrase ());
                        context.info ( methodName, "L'esito dell'invio notifica tipizzata e': " + inviaNotificaServiceResponse.getDesEsito() );
                        System.out.println (methodName+ " -L'esito dell'invio notifica tipizzata e': "+ inviaNotificaServiceResponse.getDesEsito()  );
                    }
		    } catch ( IOException e ) {
		    	context.error ( methodName, "Errore nella chiamata", e );
		    	System.out.println (methodName+ " -Errore nella chiamata: "+ e.getMessage ()  );
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
			 System.out.println (methodName+ " -errore nella creazione del model json : "+ e.getMessage ()  );
			return "";
		}

		 context.info ( methodName, "fine "+methodName );
		 System.out.println (methodName+ " -fine : "  );
		return result;
	}
    
   



    private int estraiNumeroMassimoLunghezzaDesc() throws NoDataException {
        int numMax= 0;
        try
        {
            Configurazione config= configurazioneFacade.ricerca(CONFIG_MAX_NUM_CAR_DESC_COV_INVIO_NOTIFY, COD_ENTE_DEFALUT);

            if (null!= config && null !=config.getValore())
            {
                numMax= Integer.parseInt(config.getValore());
            }
        }
        catch (Exception e) {
            throw new NoDataException("Nessun valore valido per numero massimo di pagamenti",e.getCause());
        }
        return numMax;
    }

	

	

}
