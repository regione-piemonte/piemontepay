/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.mdpboweb.security;

import java.util.Map;
import it.csi.mdp.mdpboweb.business.*;

public class ComplexUISecConstraint implements UISecConstraint {

	protected UISecConstraint[] constraints = null;

	public ComplexUISecConstraint(UISecConstraint[] constraints) {
		this.constraints = constraints;
	}

	/**
	 * Verifica se almeno uno dei constraints &egrave; soddisfatto.
	 */
	public boolean verifyConstraint(Map session, int checkedBehavior,
			SecurityHelper sh) throws BEException {
		if (constraints != null && constraints.length > 0) {
			boolean verified = false;
			for (int i = 0; i < constraints.length && !verified; i++) {
				UISecConstraint currCtr = constraints[i];
				verified |= currCtr.verifyConstraint(session, checkedBehavior,
						sh);
			}
			return verified;
		} else
			return true;
	}

}
