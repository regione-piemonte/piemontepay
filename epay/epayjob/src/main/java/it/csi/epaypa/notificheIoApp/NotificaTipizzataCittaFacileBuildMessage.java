/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epaypa.notificheIoApp;

import org.apache.commons.lang3.StringUtils;

import it.csi.epay.epayjob.model.notify.Email;
import it.csi.epay.epayjob.model.notify.IoContent;
import it.csi.epay.epayjob.model.notify.Mex;
import it.csi.epay.epayjob.model.notify.Notification;
import it.csi.epay.epayjob.model.notify.Push;
import it.csi.epay.epayservices.model.Pagamento;

public class NotificaTipizzataCittaFacileBuildMessage extends NotificaTipizzataIoAppBuildMessage{
	
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
    protected void buildTagPayload ( Notification payload, Pagamento pagamento ) {
        payload.setTag ( "citfac, "+pagamento.getEnte ().getCodiceIpa ()+", ppay" );
    }
	

    @Override
    protected void buildPushPayload ( Notification payload, Pagamento pagamento ) {
        Push push = new Push ();
        push.setTitle (properties.getProperty("notify.tipizzata.message.payload.io.content.subject")+" - "
                        +pagamento.getTipoPagamento ().getDescrizioneTextCov ());
        push.setBody ( buildMessage ( pagamento ) );
        push.setCallToAction (  generateCallToAction ( pagamento ) );
        payload.setPush ( push );
        
    }

    private String generateCallToAction ( Pagamento pagamento ) {
        return "https://"
                        +pagamento.getEnte ().getUrlDominio ()
                        +properties.getProperty("notify.tipizzata.cittafacile.calltoaction.1")
                        +pagamento.getIuv();
    }

    @Override
    protected void buildEmailPayload ( Notification payload, Pagamento pagamento ) {
        Email email = new Email ();
        email.setSubject (properties.getProperty("notify.tipizzata.message.payload.io.content.subject")+" - "
                        +pagamento.getTipoPagamento ().getDescrizioneTextCov ());
        email.setBody ( buildBodyEmail ( pagamento ) );
        email.setTemplateId ( pagamento.getEnte ().getTemplateEmailId () );
        payload.setEmail ( email );
    }

    @Override
    protected void buildMexPayload ( Notification payload, Pagamento pagamento ) {
        Mex mex = new Mex ();
        mex.setTitle (properties.getProperty("notify.tipizzata.message.payload.io.content.subject")+" - "
                        +pagamento.getTipoPagamento ().getDescrizioneTextCov ());
        mex.setBody ( buildMessage ( pagamento ) );
        mex.setCallToAction (  generateCallToAction ( pagamento ) );
        payload.setMex ( mex );
    }

    
    protected String buildBodyEmail  ( Pagamento pagamento ) {
        
        String a= "<p> <strong>"
                        +(StringUtils.isNotBlank(pagamento.getPagatore().getNome())?
                                      pagamento.getPagatore().getNome().toUpperCase():"") +" "
                      +(StringUtils.isNotBlank(pagamento.getPagatore().getCognome())?
                      pagamento.getPagatore().getCognome().toUpperCase():" ")+"</strong>,<br>"
                      +properties.getProperty("notify.tipizzata.message.payload.io.content.subject.markdown.1")+ " <strong>"
                      + pagamento.getIuv() +" - "+ pagamento.getCausale ()+" </strong> "
                      +properties.getProperty("notify.tipizzata.message.payload.io.content.subject.markdown.2")+" <strong>"
                      +pagamento.getEnte().getNome()
                      +" </strong>.<br>" 
                      +properties.getProperty("notify.tipizzata.cittafacile.email.3")
                      +".<br>"
                      +"</p>"
//                      +"<a href=\""
                      +properties.getProperty("notify.tipizzata.cittafacile.email.1")
                      +generateCallToAction ( pagamento ) 
                      +properties.getProperty("notify.tipizzata.cittafacile.email.2")
//                      +"\" class=\"send\">Paga ora</a>"
                      ;
        
        return a;

    }
	

	
	


}
