/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.mdpboweb.security;
import java.util.Map;
import it.csi.mdp.mdpboweb.business.*;

public class ActorBasedUISecConstraint extends AbstractUISecConstraint {

	private final String actorCode;

	public ActorBasedUISecConstraint(String nomeContainer, String nomeWidget,
			int constrainedBehavior, boolean defaultState, String actorCode) {
		super(nomeContainer, nomeWidget, constrainedBehavior, defaultState);
		this.actorCode = actorCode;
	}

	@Override
	public boolean specificCheck(Map session, SecurityHelper sh)
			throws BEException {
		return sh.verifyCurrentUserForActor(session, actorCode);
	}

}
