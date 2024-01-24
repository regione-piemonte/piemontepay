/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.mdpboweb.security;

import java.util.Map;
import it.csi.mdp.mdpboweb.business.*;

public class DummyUISecConstraint extends AbstractUISecConstraint {

	private final boolean _fixedValue;

	public DummyUISecConstraint(String nomeContainer, String nomeWidget,
			int constrainedBehavior, boolean defaultState, boolean fixedValue) {
		super(nomeContainer, nomeWidget, constrainedBehavior, defaultState);
		_fixedValue = fixedValue;
	}

	@Override
	public boolean specificCheck(Map session, SecurityHelper sh)
			throws BEException {
		return _fixedValue;
	}

}
