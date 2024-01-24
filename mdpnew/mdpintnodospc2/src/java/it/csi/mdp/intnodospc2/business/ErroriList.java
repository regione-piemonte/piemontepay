/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.intnodospc2.business;

import java.util.ArrayList;

@SuppressWarnings("hiding")
public class ErroriList<String> extends ArrayList<String>{

	private static final long serialVersionUID = 1L;
	
	
	@Override
	public boolean add(String o) {
		if (o != null)
			return super.add(o);
		else
			return false;
	}

}
