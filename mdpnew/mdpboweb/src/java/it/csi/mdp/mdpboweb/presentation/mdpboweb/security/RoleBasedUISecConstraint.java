/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.mdpboweb.security;
import java.util.Map;
import it.csi.mdp.mdpboweb.business.*;

public class RoleBasedUISecConstraint extends AbstractUISecConstraint {

	private final String roleCode;
	private final String domainCode;

	public RoleBasedUISecConstraint(String nomeContainer, String nomeWidget,
			int constrainedBehavior, boolean defaultState, String roleCode,
			String domainCode) {
		super(nomeContainer, nomeWidget, constrainedBehavior, defaultState);
		this.roleCode = roleCode;
		this.domainCode = domainCode;
	}

	@Override
	public boolean specificCheck(Map session, SecurityHelper sh)
			throws BEException {
		return sh.verifyCurrentUserForRole(session, roleCode, domainCode);
	}

}
