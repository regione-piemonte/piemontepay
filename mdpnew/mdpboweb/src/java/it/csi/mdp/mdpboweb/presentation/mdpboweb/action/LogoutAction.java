/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.mdpboweb.action;

import java.util.*;

import com.opensymphony.xwork2.ActionContext;

import it.csi.mdp.mdpboweb.dto.*;

/**
 * LogoutAction Action Class.
 *
 * @author GuiGen
 */
public class LogoutAction extends BaseAction {

	/**
	 * nessuna classe model associata
	 */
	public Class modelClass() {
		return null;
	}

	/**
	 * Mostra la pagina di conferma del logout
	 * @return SSO_LOGOUT.
	 */
	public String confirmLogout() throws Exception {
		return "confirmLogout";
	}

	/**
	 *
	 * @return SSO_LOGOUT.
	 */
	public String ssoLogout() throws Exception {
		log.debug("[LogoutAction::ssoLogout] START");
		invalidateLocalSession();
		log.debug("[LogoutAction::ssoLogout] END");
		return "SSO_LOGOUT";
	}

	/**
	 *
	 * @return LOCAL_LOGOUT.
	 */
	public String localLogout() throws Exception {
		log.debug("[LogoutAction::localLogout] START");
		invalidateLocalSession();
		log.debug("[LogoutAction::localLogout] END");
		return "LOCAL_LOGOUT";
	}

	/**
	 * Invalida la sessione corrente
	 */
	protected void invalidateLocalSession() {
		if (session instanceof org.apache.struts2.dispatcher.SessionMap) {

			try {
				((org.apache.struts2.dispatcher.SessionMap) session)
						.invalidate();
			} catch (IllegalStateException e) {
				log.error("[LogoutAction::invalidateLocalSession] ERROR Invalidating Struts2 Session");
				// in caso di errore invalido almeno l'attributo di iride
				session.remove(it.csi.mdp.mdpboweb.presentation.mdpboweb.filter.IrideIdAdapterFilter.IRIDE_ID_SESSIONATTR);
			}
		}
	}

	/**
	 *	Metodo per la rimozione dalla sessione degli application data a scope
	 *  SAME_PAGE. 
	 */
	public void clearPageScopedAppData(String targetContentPanelName) {
		//NOP
	}

}
