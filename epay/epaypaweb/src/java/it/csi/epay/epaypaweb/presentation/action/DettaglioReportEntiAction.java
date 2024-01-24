/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import static com.opensymphony.xwork2.Action.SUCCESS;

import javax.ejb.EJB;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import it.csi.epay.epaypaweb.business.interf.ReportEntiBusiness;
import it.csi.epay.epaypaweb.dto.DettaglioPagamentiDto;
import it.csi.epay.epaypaweb.dto.common.RicercaDettaglioReportEntiDto;
import it.csi.epay.epaypaweb.dto.common.RicercaReportEntiDto;
import it.csi.epay.epaypaweb.presentation.annotation.Authorization;


@Namespace("/")
@InterceptorRef("epaypawebStack")
@Results({
//@formatter:off
	@Result(name = SUCCESS, location = "pages/dettaglio-report-enti.jsp")
//@formatter:on
})
public class DettaglioReportEntiAction extends EpaypawebBaseAction {
	static private final long serialVersionUID = 1L;
	static private final String CLASSNAME = DettaglioReportEntiAction.class.getSimpleName();

    private String iuv;

    private DettaglioPagamentiDto pagamentoSelezionato;

	@EJB
    private ReportEntiBusiness gestioneFlussiBusiness;

    @Action("entry-dettaglio-report-enti")
    @Authorization(cdu = "RIC_ENTI")
    public String entryDettaglioPagamento() {
        return entryDettaglioPagamentoLocal(true);
    }

	public String entryDettaglioPagamentoLocal(boolean firstCall) {
		String methodName = "entryDettaglioPagamentoLocal";
		
		log.info(methodName + " iuv: "+ iuv);
		String result = null;
		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );
			
			  RicercaDettaglioReportEntiDto ricercaDettaglioReportEntiDto = new RicercaDettaglioReportEntiDto(getIpAddress(),getIdUtente(),getCodiceFiscaleUtente(),getCodiceApplicazione(),
					  iuv);
			
			pagamentoSelezionato = gestioneFlussiBusiness.getDettaglio(ricercaDettaglioReportEntiDto);
			result = SUCCESS;

		} catch (Throwable e) {
			log.error("Errore imprevisto", e);
			addActionError(getText("system.error", new String[] { e.getClass().getName(), e.getMessage() }));
			result = "system-error";

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return result;
	}

	public String getIuv() {
		return iuv;
	}

	public void setIuv(String iuv) {
		this.iuv = iuv;
	}

    public ReportEntiBusiness getGestioneFlussiBusiness () {
		return gestioneFlussiBusiness;
	}

    public void setGestioneFlussiBusiness ( ReportEntiBusiness gestioneFlussiBusiness ) {
		this.gestioneFlussiBusiness = gestioneFlussiBusiness;
	}

    public DettaglioPagamentiDto getPagamentoSelezionato () {
		return pagamentoSelezionato;
	}

}
