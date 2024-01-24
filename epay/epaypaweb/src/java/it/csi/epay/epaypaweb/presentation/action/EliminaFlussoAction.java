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

import it.csi.epay.epaypaweb.business.interf.GestioneFlussiBusiness;
import it.csi.epay.epaypaweb.dto.common.EliminaFlussoRequestDto;
import it.csi.epay.epaypaweb.presentation.annotation.Authorization;
import it.csi.epay.epaypaweb.presentation.dto.AjaxServiceResponse;


@Namespace("/")
@InterceptorRef("epaypawebStack")
@Action("elimina-flusso-json")
@Results({
//@formatter:off
	@Result(name = SUCCESS,	type = "json", params = { "root", "ajaxServiceResponse", "noCache", "true" })
//@formatter:on
})
public class EliminaFlussoAction extends EpaypawebBaseAction {
	static private final long serialVersionUID = 1L;
	static private final String CLASSNAME = EliminaFlussoAction.class.getSimpleName();

	private Long idFlusso;
	private String tipoFlusso;
	private String idMessaggio;
	private AjaxServiceResponse ajaxServiceResponse;

	@EJB
	private GestioneFlussiBusiness gestioneFlussiBusiness;

	@Authorization(cdu = "ELM_LDC")
	@Authorization(cdu = "ELM_AGPD")
	public String execute() {
		String methodName = "execute";
		

		ajaxServiceResponse = new AjaxServiceResponse();
		try {
			String resultCode = (gestioneFlussiBusiness.eliminaFlusso(new  EliminaFlussoRequestDto(getIpAddress(),getIdUtente(),getCodiceFiscaleUtente(),getCodiceApplicazione(),idFlusso, tipoFlusso))) ? "success" : "warning";
			//String resultCode = (gestioneFlussiBusiness.eliminaFlusso(idFlusso)) ? "success" : "warning";
			ajaxServiceResponse.setResultCode(resultCode);
			ajaxServiceResponse.setMessage(getText("message." + resultCode + ".elimina.flusso", new String[] { idMessaggio }));

		} catch (Exception e) {
			log.error("Errore imprevisto", e);
			ajaxServiceResponse.setResultCode("error");
			ajaxServiceResponse.setMessage(getText("message.error.elimina.flusso", new String[] { idMessaggio, e.getMessage() }));

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return SUCCESS;
	}

	public void setIdFlusso(Long idFlusso) {
		this.idFlusso = idFlusso;
	}

	public void setIdMessaggio(String idMessaggio) {
		this.idMessaggio = idMessaggio;
	}
	
	

	/**
	 * @param tipoFlusso the tipoFlusso to set
	 */
	public void setTipoFlusso(String tipoFlusso) {
		this.tipoFlusso = tipoFlusso;
	}

	public AjaxServiceResponse getAjaxServiceResponse() {
		return ajaxServiceResponse;
	}

}
