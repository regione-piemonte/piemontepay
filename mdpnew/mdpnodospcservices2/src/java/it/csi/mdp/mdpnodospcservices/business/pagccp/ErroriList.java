/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpnodospcservices.business.pagccp;

import java.util.ArrayList;

@SuppressWarnings("hiding")
public class ErroriList<String> extends ArrayList<String>{

	private static final long serialVersionUID = 7393674209351245890L;

	@Override
	public boolean add(String o) {
		if (o != null)
			return super.add(o);
		else
			return false;
	}

}
