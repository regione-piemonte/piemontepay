/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.mdpboweb.security;

import java.util.Map;
import it.csi.mdp.mdpboweb.business.*;

public class UCBasedUISecConstraint extends AbstractUISecConstraint {

	private final String useCaseCode;

	public UCBasedUISecConstraint(String nomeContainer, String nomeWidget,
			int constrainedBehavior, boolean defaultState, String useCaseCode) {
		super(nomeContainer, nomeWidget, constrainedBehavior, defaultState);
		this.useCaseCode = useCaseCode;
	}

	@Override
	public boolean specificCheck(Map session, SecurityHelper sh)
			throws BEException {
		return sh.verifyCurrentUserForUC(session, useCaseCode);
	}

}
