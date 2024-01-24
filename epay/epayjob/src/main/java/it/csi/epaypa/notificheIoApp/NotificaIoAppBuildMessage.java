/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epaypa.notificheIoApp;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import it.csi.epay.epayjob.model.notify.Io;
import it.csi.epay.epayjob.model.notify.IoContent;
import it.csi.epay.epayjob.model.notify.Message;
import it.csi.epay.epayjob.model.notify.Notification;
import it.csi.epay.epayservices.model.Pagamento;

public class NotificaIoAppBuildMessage {
	
	protected Properties properties;
	
	
	
	
	public Message buildMessage(Pagamento pagamento, String uuid, String bulkId) throws IOException {
	    	
		    InputStream inputStream = this.getClass ().getClassLoader ().getResourceAsStream ( "invioNotify.properties" );
             properties = new Properties ();
             properties.load ( inputStream );
	    	Message message= new Message();
	    	message.setUuid(uuid);
	    	message.setExpireAt(generaExpireAt(pagamento.getDataScadenza()));
	    	message.setPayload(buildPayLoad(pagamento,uuid, bulkId));
			return message;
		}

	protected Notification buildPayLoad(Pagamento pagamento, String uuid, String bulkId) {
	    	
	    	Notification payload= new Notification();
	        payload.setId(uuid);
	        payload.setBulkId(bulkId);
	        payload.setUserId(pagamento.getPagatore().getCodiceFiscale());
	        buildTagPayload(payload, pagamento);
	        
	        buildPushPayload(payload, pagamento);
	        buildEmailPayload(payload, pagamento);
	        buildMexPayload(payload, pagamento);
	    	buidIoPayload(payload, pagamento);
	    	
			return payload;
		}
	
	protected void buildTagPayload(Notification payload , Pagamento pagamento) {
        
//        payload.setTag ( "" );
    }

	protected void buidIoPayload(Notification payload , Pagamento pagamento) {
			
			Io  io = new Io();
			
			io.setTimeToLive(3600);
			io.setContent(builContent(pagamento));
			payload.setIo ( io );
		}
	
	protected void buildPushPayload(Notification payload , Pagamento pagamento) {
        
        
    }
	
	protected void buildEmailPayload(Notification payload , Pagamento pagamento) {
        
        
    }
	
	protected void buildMexPayload(Notification payload , Pagamento pagamento) {
        
        
    }

	protected IoContent builContent(Pagamento pagamento) {
			
			
			IoContent ioContent= new IoContent();
			ioContent.setSubject(properties.getProperty("notify.message.payload.io.content.subject"));
//			"Avviso di scadenza di un pagamento"
			ioContent.setMarkdown(
					buildMessage ( pagamento )
					);
			ioContent.setDueDate(generaExpireAt(pagamento.getDataScadenza()));
			return ioContent;
			
			
		}


    protected String buildMessage ( Pagamento pagamento ) {
        return (StringUtils.isNotBlank(pagamento.getPagatore().getNome())?
        		pagamento.getPagatore().getNome().toUpperCase():"")+" "
        +(StringUtils.isNotBlank(pagamento.getPagatore().getCognome())?
        		pagamento.getPagatore().getCognome().toUpperCase():"")+
        properties.getProperty("notify.message.payload.io.content.subject.markdown.1")+
        " " + pagamento.getIuv() +" - "+ pagamento.getTipoPagamento().getDescrizionePortale()+" - "+
        properties.getProperty("notify.message.payload.io.content.subject.markdown.2")+" "
        +pagamento.getEnte().getNome()+" "+
        properties.getProperty("notify.message.payload.io.content.subject.markdown.3");
    }



//	protected IoPaymentData buidPaymentData(Pagamento pagamento) {
//			IoPaymentData ioPaymentData = new IoPaymentData();
//			
//			ioPaymentData.setAmount(pagamento.getImporto().setScale(2, RoundingMode.HALF_UP).intValue());
//			ioPaymentData.setNoticeNumber(pagamento.getAuxDigit ()+pagamento.getIuv());
//			return ioPaymentData;
//		}

	protected String generaExpireAt(Date dataScadenza) {
			if (dataScadenza == null )
				return null;
			
	    	return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ITALY)
	    	    	.format(dataScadenza);
	    }

	  
	
	


}
