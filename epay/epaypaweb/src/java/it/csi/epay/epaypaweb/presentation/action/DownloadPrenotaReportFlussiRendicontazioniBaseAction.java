/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

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

import it.csi.epay.epaypaweb.business.interf.ReportEntiBusiness;
import it.csi.epay.epaypaweb.dto.FileReportDto;
import it.csi.epay.epaypaweb.dto.ReportFilterDto;
import it.csi.epay.epaypaweb.dto.common.DownloadPrenotazioneReportEntiRequestDto;
import it.csi.epay.epaypaweb.presentation.annotation.Authorization;


@Namespace ( "/" )
@InterceptorRef ( "epaypawebStack" )

@Results ( {
    //@formatter:off
	@Result(name = "REFRESH", type = "chain", location = "entry-prenota-ricerca-report-flussi-rendicontazione-base"),
			@Result ( name = SUCCESS, type = "stream", params = { "contentType", "${tipoFormato.contentType}", "inputName", "inputStream", "contentDisposition",
        "attachment; filename=\"${filename}\"" } )
                //@formatter:on
} )
public class DownloadPrenotaReportFlussiRendicontazioniBaseAction extends BaseRicercaFlussiRendicontazioniAction {

	private static final long serialVersionUID = 1L;
	
	private static final String CLASSNAME = DownloadPrenotaReportFlussiRendicontazioniBaseAction.class.getSimpleName();
	
	protected String filename;
	
	protected transient ByteArrayInputStream inputStream;
	
	protected String pleaseWaitTokenValue;
	
    @EJB
    private ReportEntiBusiness reportEntiBusiness;
	
	@Action ( "scarica-flussi-base" )
    @Authorization ( cdu = "RIC_FR" )
    public String scaricaPagamenti () {
		
		String methodName = "scaricaPagamenti";
        
        try {
        	log.info ( CLASSNAME + " " + methodName + " - START" );
        	ReportFilterDto reportFilterDto = getReportFilterDto();
        	
        	if ( null != getIdFile () ) {
        		
        		DownloadPrenotazioneReportEntiRequestDto downloadPrenotazioneReportEntiRequestDto = 
        	       		 new DownloadPrenotazioneReportEntiRequestDto(getIpAddress(),getIdUtente(),getCodiceFiscaleUtente(),getCodiceApplicazione(),
        	       				reportFilterDto.getIdEnte (), getIdFile());

                FileReportDto file = reportEntiBusiness.downloadFileReportRicerca (downloadPrenotazioneReportEntiRequestDto);
                filename = file.getNomeFile ();
                inputStream = new ByteArrayInputStream ( file.getContenutoFile () );
            } else {
                esitoElaborazione = "warning";
                messaggioEsitoElaborazione = "Identificativo file non presente.";
            }
        } catch ( Throwable e ) {
            log.error ( "Errore imprevisto", e );
            esitoElaborazione = "error";
            messaggioEsitoElaborazione = "Errore in fase di recupero del file con identificativo: " + getIdFile ();
            return "REFRESH";
        } finally {
            setFileDownloadTokenCookie ( pleaseWaitTokenValue );
            log.info ( CLASSNAME + " " + methodName + " - STOP" );
        }
        return SUCCESS;
	}
	
	 protected void setFileDownloadTokenCookie ( String pleaseWaitTokenValue ) {
	        HttpServletResponse response = ServletActionContext.getResponse ();
	        response.addCookie ( new Cookie ( "pleaseWaitToken", pleaseWaitTokenValue ) );
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getPleaseWaitTokenValue() {
		return pleaseWaitTokenValue;
	}

	public void setPleaseWaitTokenValue(String pleaseWaitTokenValue) {
		this.pleaseWaitTokenValue = pleaseWaitTokenValue;
	}

}
