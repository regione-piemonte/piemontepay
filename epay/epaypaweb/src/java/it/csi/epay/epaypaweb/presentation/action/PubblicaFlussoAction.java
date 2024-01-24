/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import static com.opensymphony.xwork2.Action.SUCCESS;
import static it.csi.epay.epaypaweb.enumeration.StatoFlussoEnum.ERRORE_IN_FASE_DI_INVIO;
import static it.csi.epay.epaypaweb.enumeration.StatoFlussoEnum.IN_CORSO_DI_REDAZIONE;

import javax.ejb.EJB;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import it.csi.epay.epaypaweb.business.interf.GestioneFlussiBusiness;
import it.csi.epay.epaypaweb.dto.FlussoDto;
import it.csi.epay.epaypaweb.dto.FlussoLightDto;
import it.csi.epay.epaypaweb.dto.common.SalvaTestataFlussoPosizioniDebitorieRequestDto;
import it.csi.epay.epaypaweb.presentation.annotation.Authorization;
import it.csi.epay.epaypaweb.presentation.dto.AjaxServiceResponse;


@Namespace("/")
@InterceptorRef("epaypawebStack")
@Action("pubblica-flusso-json")
@Results({
//@formatter:off
	@Result(name = SUCCESS,	type = "json", params = { "root", "ajaxServiceResponse", "noCache", "true" })
//@formatter:on
})
public class PubblicaFlussoAction extends EpaypawebBaseAction {
	static private final long serialVersionUID = 1L;
	static private final String CLASSNAME = PubblicaFlussoAction.class.getSimpleName();

	private Long idFlusso;
	private String idMessaggio;
	private AjaxServiceResponse ajaxServiceResponse;

	@EJB
	private GestioneFlussiBusiness gestioneFlussiBusiness;

	@Authorization(cdu = "INS_LDC")
	@Authorization(cdu = "INS_AGPD")
	public String execute() {
		String methodName = "execute";
		

		ajaxServiceResponse = new AjaxServiceResponse();
		try {
			// riottiene dalla sessione alcuni dati necessari
			FlussoLightDto flussoSelezionato = gestioneFlussiBusiness.getFlussoLight(idFlusso);

			// determina lo stato del flusso
			FlussoDto testataFlusso = new FlussoDto(idFlusso);
			if (flussoSelezionato.getCodEsito() != null)
				testataFlusso.setStatoFlusso((Integer.parseInt(flussoSelezionato.getCodEsito()) > 99) ? ERRORE_IN_FASE_DI_INVIO : IN_CORSO_DI_REDAZIONE);
			else
				testataFlusso.setStatoFlusso(IN_CORSO_DI_REDAZIONE);

			
			SalvaTestataFlussoPosizioniDebitorieRequestDto salvaTestataFlussoPosizioniDebitorieRequestDto = new SalvaTestataFlussoPosizioniDebitorieRequestDto(getIpAddress(),getIdUtente(),getCodiceFiscaleUtente(),getCodiceApplicazione(),testataFlusso,null,null);
			// esce dallo stato di bozza salvando il nuovo stato
			String resultCode = (gestioneFlussiBusiness.salvaTestataFlussoPosizioniDebitorie(salvaTestataFlussoPosizioniDebitorieRequestDto)) ? "success" : "warning";
			//String resultCode = (gestioneFlussiBusiness.salvaTestataFlussoPosizioniDebitorie(testataFlusso, null, null)) ? "success" : "warning";
			ajaxServiceResponse.setResultCode(resultCode);
			ajaxServiceResponse.setMessage(getText("message." + resultCode + ".pubblica.flusso", new String[] { idMessaggio }));

		} catch (Exception e) {
			log.error("Errore imprevisto", e);
			ajaxServiceResponse.setResultCode("error");
			ajaxServiceResponse.setMessage(getText("message.error.pubblica.flusso", new String[] { idMessaggio, e.getMessage() }));

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

	public AjaxServiceResponse getAjaxServiceResponse() {
		return ajaxServiceResponse;
	}

}
