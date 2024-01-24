/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import it.csi.epay.epaypaweb.dto.EsitoDto;
import it.csi.epay.epaypaweb.exception.BusinessException;
import it.csi.epay.epaypaweb.presentation.dto.AjaxServiceResponse;



public abstract class InviaFlussoAction extends EpaypawebBaseAction {

	private static final long serialVersionUID = 1L;

	private static final String CLASSNAME = InviaFlussoAction.class.getSimpleName ();

	private Long idFlusso;
	private String idMessaggio;
	private AjaxServiceResponse ajaxServiceResponse;

	protected String commonExecute() {
		String methodName = "commonExecute";
		

		ajaxServiceResponse = new AjaxServiceResponse();

		try {
			log.info ( CLASSNAME + " " + methodName + " - START" );

			EsitoDto esito = inviaFlusso(idFlusso);

			if (esito.getCodEsito().equals("000")) {
				ajaxServiceResponse.setResultCode("success");
				ajaxServiceResponse.setMessage(getText("message.success.invio.flusso", new String[] { idMessaggio }));

			} else {
				String resultCode;
				if (Integer.valueOf(esito.getCodEsito()) < 100)
					resultCode = "warning";
				else if (Integer.valueOf(esito.getCodEsito()) < 200)
					resultCode = "error";
				else
					resultCode = "danger";

				ajaxServiceResponse.setResultCode(resultCode);
				ajaxServiceResponse.setMessage(getText("message." + resultCode + ".invio.flusso", new String[] { idMessaggio, esito.getCodEsito(), esito.getDesEsito() }));
			}

		} catch (BusinessException e) {
			log.error("Errore imprevisto", e);
			ajaxServiceResponse.setResultCode("danger");
			ajaxServiceResponse.setMessage(getText("message.danger.invio.flusso", new String[] { idMessaggio, "---", e.getMessage() }));

		} finally {
			log.info ( CLASSNAME + " " + methodName + " - STOP" );
		}

		return SUCCESS;
	}

	abstract protected EsitoDto inviaFlusso(Long idFlusso) throws BusinessException;

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
