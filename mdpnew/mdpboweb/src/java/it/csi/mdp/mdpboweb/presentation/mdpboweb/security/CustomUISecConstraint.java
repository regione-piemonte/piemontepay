/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.mdpboweb.security;

import java.util.Map;
import it.csi.mdp.mdpboweb.business.*;

public class CustomUISecConstraint extends AbstractUISecConstraint {

	public CustomUISecConstraint(String nomeContainer, String nomeWidget,
			int constrainedBehavior, boolean defaultState) {
		super(nomeContainer, nomeWidget, constrainedBehavior, defaultState);
	}

	@Override
	public boolean specificCheck(Map session, SecurityHelper sh)
			throws BEException {
		// viene sempre ridefinito
		return true;
	}

}
