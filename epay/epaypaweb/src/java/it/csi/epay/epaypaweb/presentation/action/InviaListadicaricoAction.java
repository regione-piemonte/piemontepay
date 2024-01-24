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
import it.csi.epay.epaypaweb.dto.EsitoDto;
import it.csi.epay.epaypaweb.dto.common.InviaListadicaricoRequestDto;
import it.csi.epay.epaypaweb.exception.BusinessException;
import it.csi.epay.epaypaweb.presentation.annotation.Authorization;
import it.csi.epay.epaypaweb.presentation.util.ApplicationConfiguration;

@Namespace("/")
@InterceptorRef("epaypawebStack")
@Action("invia-listadicarico-json")
@Results({
//@formatter:off
	@Result(name = SUCCESS, type = "json", params = { "root", "ajaxServiceResponse", "noCache", "true" })
//@formatter:on
})
public class InviaListadicaricoAction extends InviaFlussoAction {
	static private final long serialVersionUID = 1L;

	@EJB
	private GestioneFlussiBusiness gestioneFlussiBusiness;
	
	@Authorization(cdu = "INV_LDC")
	public String execute() {
		return commonExecute();
	}
	
	@Override
	protected EsitoDto inviaFlusso(Long idFlusso) throws BusinessException {
		String serviceEndpoint = ApplicationConfiguration.getApplicationConfiguration().getStringProperty("enti2epaywso.url");
		return gestioneFlussiBusiness.inviaListadicarico(new InviaListadicaricoRequestDto(getIpAddress(),getIdUtente(),getCodiceFiscaleUtente(),getCodiceApplicazione(), idFlusso, serviceEndpoint));
		//String codUtente = getSessionContext().getUtente().getCod();
		//return gestioneFlussiBusiness.inviaListadicarico(idFlusso, codUtente, serviceEndpoint);
	}

}
