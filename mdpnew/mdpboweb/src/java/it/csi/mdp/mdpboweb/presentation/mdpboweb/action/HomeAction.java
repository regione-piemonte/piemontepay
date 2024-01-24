/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.mdpboweb.action;

import java.util.*;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.validator.annotations.*;

import it.csi.mdp.mdpboweb.dto.*;

import it.csi.mdp.mdpboweb.business.*;

/**
 * HomeAction Action Class.
 *
 * @author GuiGen
 */
@Validation()
public class HomeAction extends BaseAction {

	/**
	 * classe model associata
	 */
	public Class modelClass() {

		return java.lang.Object.class;

	}

	/**
	 * Gestione della validazione
	 */
	public void validate() {
		/*PROTECTED REGION ID(R103397888) ENABLED START*/
		/* Inserire la validazione */
		/*PROTECTED REGION END*/
	}

	/**
	 *	Metodo per la rimozione dalla sessione degli application data a scope
	 *  SAME_PAGE. 
	 */
	public void clearPageScopedAppData(String targetContentPanelName) {
		// TODO: nel caso dell'on-init command non si ha ancora un 
		// "content panel corrente" -> NOP
	}

}
