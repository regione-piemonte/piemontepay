/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;

import java.io.ByteArrayInputStream;

import javax.ejb.EJB;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import it.csi.epay.epaypaweb.business.interf.GestioneFlussiBusiness;
import it.csi.epay.epaypaweb.dto.EsitoAvvisoPagamentoDto;
import it.csi.epay.epaypaweb.dto.common.AvvisoPagamentoRequestDto;
import it.csi.epay.epaypaweb.exception.BusinessException;
import it.csi.epay.epaypaweb.presentation.annotation.Authorization;
import it.csi.epay.epaypaweb.presentation.util.ApplicationConfiguration;



@Namespace ( "/" )
@InterceptorRef ( "epaypawebStack" )
@Action ( "stampa-avviso-pagamento" )
@Results ( {
    //@formatter:off
    @Result ( name = INPUT, type = "redirect", location = "entry-visualizza-listadicarico.do",
                    params = { "callbackActionMessage", "${callbackActionMessage}", "callbackActionError", "${callbackActionError}" } ),
    @Result ( name = SUCCESS, type = "stream",
                    params = { "contentType", "application/pdf", "inputName", "inputStream", "contentDisposition", "attachment; filename=\"${filename}\"" } )
                //@formatter:on
} )
public class StampaAvvisoPagamentoAction extends EpaypawebBaseAction {

    static private final long serialVersionUID = 1L;

    static private final String CLASSNAME = StampaAvvisoPagamentoAction.class.getSimpleName ();

    private String iuv;

    private Long idPosizioneDebitoria;

    private String filename;

    private ByteArrayInputStream inputStream;

    private String pleaseWaitTokenValue;

    @EJB
    private GestioneFlussiBusiness gestioneFlussiBusiness;

    @Override
    @Authorization ( cdu = "PRN_AP" )
    public String execute () {
        String methodName = "execute";
        

        String result = null;
        try {
            log.info ( CLASSNAME + " " + methodName + " - START" );

            String url = ApplicationConfiguration.getApplicationConfiguration ().getStringProperty ( "epaybeapi.url" );
            String auth = ApplicationConfiguration.getApplicationConfiguration ().getStringProperty ( "epaybeapi.auth" );
            AvvisoPagamentoRequestDto avvisoPagamentoRequestDto = new AvvisoPagamentoRequestDto ( getIpAddress (), getIdUtente (), getCodiceFiscaleUtente (),
                getCodiceApplicazione (), idPosizioneDebitoria, url, auth );
            EsitoAvvisoPagamentoDto esito = gestioneFlussiBusiness.getAvvisoPagamento ( avvisoPagamentoRequestDto );
            int codEsito = Integer.parseInt ( esito.getCodEsito () );
            if ( codEsito < 100 ) {
                inputStream = new ByteArrayInputStream ( esito.getAvvisoPagamentoData () );
                filename = buildFilename ( iuv );
                result = SUCCESS;

            } else if ( codEsito < 200 ) {
                log.error ( "Errore applicativo durante la generazione dell'avviso di pagamento:" + esito.getCodEsito () + ", " + esito.getDesEsito () );
                setCallbackActionError ( getText ( "service.error", new String [] { esito.getCodEsito (), esito.getDesEsito () } ) );
                result = INPUT;
                
                

            } 
            else if (codEsito == 500 ){
            	 log.error ( "Errore nella stampa dell'avviso di pagamento:" + esito.getCodEsito () + ", " + esito.getDesEsito () );
                 setCallbackActionError ( getText ( "stampa.avviso.error", new String [] { iuv, esito.getDesEsito () } ) );
                 result = INPUT;
            	
            	
            }else {
                log.error ( "Errore di sistema durante generazione dell'avviso di pagamento:" + esito.getCodEsito () + ", " + esito.getDesEsito () );
                setCallbackActionError ( getText ( "system.error", new String [] { esito.getCodEsito (), esito.getDesEsito () } ) );
                result = INPUT;
            }

        } catch ( BusinessException e ) {
            log.error ( "Errore imprevisto", e );
            addActionError ( getText ( "system.error", new String [] { e.getClass ().getName (), e.getMessage () } ) );
            result = "system-error";

        } finally {
            setFileDownloadTokenCookie ( pleaseWaitTokenValue );
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }

        return result;
    }

    public String getIuv () {
        return iuv;
    }

    public void setIuv ( String iuv ) {
        this.iuv = iuv;
    }

    public Long getIdPosizioneDebitoria () {
        return idPosizioneDebitoria;
    }

    public void setIdPosizioneDebitoria ( Long idNotificaPagamento ) {
        this.idPosizioneDebitoria = idNotificaPagamento;
    }

    public String getFilename () {
        return filename;
    }

    public ByteArrayInputStream getInputStream () {
        return inputStream;
    }

    public String getPleaseWaitTokenValue () {
        return pleaseWaitTokenValue;
    }

    public void setPleaseWaitTokenValue ( String pleaseWaitTokenValue ) {
        this.pleaseWaitTokenValue = pleaseWaitTokenValue;
    }

    private void setFileDownloadTokenCookie ( String pleaseWaitTokenValue ) {
        HttpServletResponse response = ServletActionContext.getResponse ();
        response.addCookie ( new Cookie ( "pleaseWaitToken", pleaseWaitTokenValue ) );
    }

    public String buildFilename ( String iuv ) {
        return new StringBuilder ().append ( "avviso_pagamento_" ).append ( iuv ).append ( ".pdf" ).toString ();
    }

}
