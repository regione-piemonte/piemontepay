/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.mdpboweb.security;
import java.util.Map;
import it.csi.mdp.mdpboweb.business.*;

public abstract class AbstractUISecConstraint implements UISecConstraint {

	protected int constrainedBehavior = UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR;
	protected final boolean defaultState;
	protected final String nomeContainer;
	protected final String nomeWidget;

	public AbstractUISecConstraint(String nomeContainer, String nomeWidget,
			int constrainedBehavior, boolean defaultState) {
		if (nomeContainer == null || nomeWidget == null)
			throw new IllegalArgumentException(
					"Errore interno: nome container e nome widget obbligatori per constraint");
		if (constrainedBehavior != UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR
				&& constrainedBehavior != UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR)
			throw new IllegalArgumentException(
					"Errore interno: comportamento oggetto di constraint non gestibile:"
							+ constrainedBehavior);
		this.nomeContainer = nomeContainer;
		this.nomeWidget = nomeWidget;
		this.constrainedBehavior = constrainedBehavior;
		this.defaultState = defaultState;
	}
	/**
	 * Combina adeguatamente:
	 * <ul>
	 * <li>l'esito della verifica specifica
	 * <li>lo stato di default (relativo al comportamento oggetto di constraint - visibilit&agrave; o abilitazione)
	 * <li>lo stato corrente  (relativo al comportamento oggetto di constraint - visibilit&agrave; o abilitazione)
	 * </ul>
	 * In sintesi il comportamento oggetto di constraint sar&agrave; cos&igrave; valutato:
	 * <ul>
	 * <li>se lo stato corrente non &egrave; specificato (il widget non &egrave; stato impostato esplicitamente
	 *     ad invisibile o disabilitato) viene utilizzato come stato corrente lo stato di default
	 * <li>se lo stato corrente combinato (corrente effettivo + default) &egrave; OFF (invisibile o disabilitato)
	 *     lo stato finale o OFF
	 * <li>se lo stato corrente combinato (corrente effettivo + default) &egrave; ON, viene eseguito
	 *     il check specifico (che dipende dat tipo di constraint) e lo stato finale &egrave; 
	 *     ON (visibile o abilitato) se il check specifico &egrave; verificato, OFF altrimenti.
	 * </ul>
	 * @param session mantiene lo stato corrente del widget
	 * @return
	 */
	protected boolean combineAll(Map session, int checkedBehavior,
			SecurityHelper sh) throws BEException {
		if (checkedBehavior != this.constrainedBehavior)
			return false; // se il comportamento osservato e' un'altro passo oltre
		else {
			Boolean currentState = getCurrentState(session);
			if (currentState == null)
				currentState = Boolean.valueOf(defaultState);
			if (currentState.booleanValue() == false)
				return false;
			else
				return specificCheck(session, sh);
		}
	}

	/**
	 * Cerca in sessione lo stato corrente.
	 * @return null se lo stato (per il comportamento in osservazione) non &egrave; impostato oppure
	 * il valore effettivo se questo &egrave; impostato.
	 */
	protected Boolean getCurrentState(Map session) {
		String behaviorSuffix = "";
		switch (constrainedBehavior) {
			case UISecConstraint.ONOFF_CONSTRAINED_BEHAVIOR :
				behaviorSuffix = "_enabled";
				break;
			case UISecConstraint.VISIB_CONSTRAINED_BEHAVIOR :
				behaviorSuffix = "_visible";
				break;
			default :
				break;
		}
		Map cpData = (Map) session.get(nomeContainer);
		if (cpData != null) {
			Boolean behaviorFlag = (Boolean) cpData.get(nomeWidget
					+ behaviorSuffix);
			if (behaviorFlag != null) {
				return behaviorFlag.booleanValue();
			} else
				return null; // unspecified current state
		} else
			return null; // unspecified current state
	}

	public boolean verifyConstraint(Map session, int checkedBehavior,
			SecurityHelper sh) throws BEException {

		return combineAll(session, checkedBehavior, sh);
	}

	/**
	 * Questo metodo &egrave;ridefinito nelle varie tipologie di constraint e implementa
	 * i check specifici
	 * @param session
	 * @return
	 */
	public abstract boolean specificCheck(Map session, SecurityHelper sh)
			throws BEException;

}
