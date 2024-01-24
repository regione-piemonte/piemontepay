/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import it.csi.epay.epaypaweb.dto.CategoriaCduDto;
import it.csi.epay.epaypaweb.dto.CduDto;
import it.csi.epay.epaypaweb.dto.CodiceVersamentoDto;
import it.csi.epay.epaypaweb.dto.ElementoFlussoDto;
import it.csi.epay.epaypaweb.presentation.Constants;
import it.csi.epay.epaypaweb.presentation.dto.SessionContext;
import it.csi.epay.epaypaweb.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static it.csi.epay.epaypaweb.util.Util.APPLICATION_CODE;

public class EpaypawebBaseAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	protected static Logger log = LogManager.getLogger ( APPLICATION_CODE + ".presentation" );

	protected static SimpleDateFormat sdf = new SimpleDateFormat ( Util.DATE_FORMAT );

	protected boolean init;

	protected String callbackActionMessage;
	protected String callbackActionError;

	protected String esitoElaborazione;
	protected String messaggioEsitoElaborazione;

	public SessionContext getSessionContext() {
		ActionContext actionContext = ActionContext.getContext();

		return (SessionContext) actionContext.getSession ().get ( Constants.SESSION_CONTEXT_SESSION_KEY );
	}

	public void clearSessionContext() {
		ActionContext.getContext().getSession().remove(Constants.SESSION_CONTEXT_SESSION_KEY);
	}

	public boolean isCategoryEnabled(String codCategory) {
		SessionContext sessionContext = getSessionContext();

		if (sessionContext != null && sessionContext.getCategorieCdu() != null) {
			for ( CategoriaCduDto catCdu: sessionContext.getCategorieCdu () ) {
				if ( catCdu.getCod ().equals ( codCategory ) ) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean isUseCaseEnabled ( String codiceCdu ) {
		SessionContext sessionContext = getSessionContext ();

		if ( sessionContext != null && sessionContext.getListaCdu () != null ) {
			for ( CduDto cdu: sessionContext.getListaCdu () ) {
				if ( cdu.getCod ().equals ( codiceCdu ) ) {
					return true;
				}
			}
		}

		return false;
	}

	public Integer getIdCodVersamento(String codVersamento) {
		Integer idCodVersamento = null;
		SessionContext sessionContext = getSessionContext();
		Integer idEnte = sessionContext.getEnte().getId();
		for (CodiceVersamentoDto item : sessionContext.getCodiciVersamento()) {
			if (item.getCod().equals(codVersamento) && item.getEnteDto().getId().equals(idEnte)) {
				idCodVersamento = item.getId();
				break;
			}
		}
		return idCodVersamento;
	}

	public String getCodVersamento ( Integer idCodVersamento ) {
		String codVersamento = null;
		SessionContext sessionContext = getSessionContext ();
		for ( CodiceVersamentoDto item: sessionContext.getCodiciVersamento () ) {
			if ( item.getId ().equals ( idCodVersamento ) ) {
				codVersamento = item.getCod ();
				break;
			}
		}
		return codVersamento;
	}

	public CodiceVersamentoDto getCov ( Integer idCodVersamento ) {
		SessionContext sessionContext = getSessionContext ();
		for ( CodiceVersamentoDto item: sessionContext.getCodiciVersamento () ) {
			if ( item.getId ().equals ( idCodVersamento ) ) {
				return item;
			}
		}
		return null;
	}

	public String getCodVersamentoVisibile ( Integer idCodVersamento ) {
		String codVersamento = null;
		SessionContext sessionContext = getSessionContext ();
		for ( CodiceVersamentoDto item: sessionContext.getCodiciVersamentoVisibili () ) {
			if ( item.getId ().equals ( idCodVersamento ) ) {
				codVersamento = item.getCod ();
				break;
			}
		}
		return codVersamento;
	}

	public boolean isCodVersamentoVisibile ( Integer idCodVersamento ) {
		SessionContext sessionContext = getSessionContext ();
		for ( CodiceVersamentoDto item: sessionContext.getCodiciVersamentoVisibili () ) {
			if ( item.getId ().equals ( idCodVersamento ) ) {
				return true;
			}
		}
		return false;
	}

	public void rethrow(Exception e) throws Exception {
		if (e != null) {
			addActionError(e.getMessage() + (e.getCause() != null ? " - Causa: " + e.getCause().getMessage() : ""));
			e.printStackTrace();
			throw e;
		}
	}

	public boolean isInit() {
		return init;
	}

	public void setInit(boolean init) {
		this.init = init;
	}

	public String getCallbackActionMessage() {
		return callbackActionMessage;
	}

	public void setCallbackActionMessage(String callbackActionMessage) {
		this.callbackActionMessage = callbackActionMessage;
	}

	public String getCallbackActionError() {
		return callbackActionError;
	}

	public void setCallbackActionError(String callbackActionError) {
		this.callbackActionError = callbackActionError;
	}

	public void addActionMessageIfCallbackExists() {
		if (callbackActionMessage != null && callbackActionMessage.trim().length() > 0) {
			addActionMessage(callbackActionMessage);
		}
	}

	public void addActionErrorIfCallbackExists() {
		if (callbackActionError != null && callbackActionError.trim().length() > 0) {
			addActionError(callbackActionError);
		}
	}

	public String getEsitoElaborazione() {
		return esitoElaborazione;
	}

	public void setEsitoElaborazione(String esitoElaborazione) {
		this.esitoElaborazione = esitoElaborazione;
	}

	public String getMessaggioEsitoElaborazione() {
		return messaggioEsitoElaborazione;
	}

	public void setMessaggioEsitoElaborazione(String messaggioEsitoElaborazione) {
		this.messaggioEsitoElaborazione = messaggioEsitoElaborazione;
	}

	public <T extends ElementoFlussoDto> List<Long> collectIds(List<T> list) {
		List <Long> idList = new ArrayList<>();

		for (T elem : list) {
			idList.add(elem.getId());
		}

		return idList;
	}

	public String getCodiceFiscaleUtente() {
		return ((getSessionContext()!=null)&&(getSessionContext().getUtente()!=null))?getSessionContext().getUtente().getCod():null;
	}

	public Long getIdUtente() {
		return ((getSessionContext()!=null)&&(getSessionContext().getUtente()!=null)&&(getSessionContext().getUtente().getId()!=null))?getSessionContext().getUtente().getId():null;
	}

	public String getCodiceApplicazione() {
		return "";
	}

	public String getIpAddress() {
		String ipAddress = "UNK";
		HttpServletRequest request =ServletActionContext.getRequest();
		if (request != null) {
			ipAddress = request.getHeader("X-FORWARDED-FOR");
			if (ipAddress == null || "".equals(ipAddress)) {
				ipAddress = request.getRemoteAddr();
			}
		}
		return ipAddress;
	}
}
