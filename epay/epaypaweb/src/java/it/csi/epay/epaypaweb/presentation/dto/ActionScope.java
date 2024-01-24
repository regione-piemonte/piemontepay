/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.dto;

import java.util.HashMap;
import java.util.Map;

public class ActionScope {

	private Map<String, Object> scope;

	public ActionScope() {
		scope = new HashMap<String, Object>();
	}

	@SuppressWarnings("unchecked")
	public <T> T getValue(String key) {
		T integerValue = null;

		Object value = scope.get(key);
		if (value != null) {
			integerValue = (T) value;
		}
		return integerValue;
	}

	public <T> void putValue(String key, T value) {
		scope.put(key, value);
	}

	public void reset() {
		scope.clear();
	}

}
