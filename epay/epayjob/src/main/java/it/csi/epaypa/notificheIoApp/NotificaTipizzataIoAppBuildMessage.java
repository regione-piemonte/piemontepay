/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epaypa.notificheIoApp;

import java.math.BigDecimal;
import org.apache.commons.lang3.StringUtils;

import it.csi.epay.epayjob.model.notify.IoContent;
import it.csi.epay.epayjob.model.notify.IoPaymentData;
import it.csi.epay.epayservices.model.Pagamento;

public class NotificaTipizzataIoAppBuildMessage extends NotificaIoAppBuildMessage{
	
	
	
	
	@Override
		protected IoContent builContent(Pagamento pagamento) {
			
			
			IoContent ioContent= new IoContent();
			ioContent.setSubject(properties.getProperty("notify.tipizzata.message.payload.io.content.subject")+" - "
			+pagamento.getTipoPagamento ().getDescrizioneTextCov ());
//			"Avviso di scadenza di un pagamento"
			ioContent.setMarkdown(
					buildMessage ( pagamento )
					);
			ioContent.setDueDate(generaExpireAt(pagamento.getDataScadenza()));
			ioContent.setPaymentData ( buildPaymentData(pagamento));
			return ioContent;
			
			
		}

	@Override
    protected String buildMessage ( Pagamento pagamento ) {
        return (StringUtils.isNotBlank(pagamento.getPagatore().getNome())?
        		pagamento.getPagatore().getNome().toUpperCase():"")+" "
        +(StringUtils.isNotBlank(pagamento.getPagatore().getCognome())?
        		pagamento.getPagatore().getCognome().toUpperCase():" ")+" "+
        properties.getProperty("notify.tipizzata.message.payload.io.content.subject.markdown.1")+
        " " + pagamento.getIuv() +" - "+ pagamento.getCausale ()+" - "+
        properties.getProperty("notify.tipizzata.message.payload.io.content.subject.markdown.2")+" "
        +pagamento.getEnte().getNome()+" "+
        properties.getProperty("notify.tipizzata.message.payload.io.content.subject.markdown.3");
    }
	
	protected IoPaymentData buildPaymentData ( Pagamento pagamento ) {
        IoPaymentData paymentData = new IoPaymentData ();
        paymentData.setAmount (pagamento.getImporto ().multiply  ( new BigDecimal ( 100 ) ).intValueExact ());
        paymentData.setNoticeNumber ( pagamento.getAuxDigit ()+pagamento.getIuv () ); 
        
        return paymentData;
    }

	
	


}
