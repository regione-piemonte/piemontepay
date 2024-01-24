/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.mdpboweb.security;
import java.util.Map;
import it.csi.mdp.mdpboweb.business.*;

public interface UISecConstraint {

	public static final int ONOFF_CONSTRAINED_BEHAVIOR = 1;
	public static final int VISIB_CONSTRAINED_BEHAVIOR = 2;
	public boolean verifyConstraint(Map session, int checkedBehavior,
			SecurityHelper sh) throws BEException;
}
