/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.mdpboweb.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

/**
 * TableSelectionInterceptor Interceptor Class.
 *
 * @author GuiGen
 */
public class TableSelectionInterceptor implements Interceptor {

	/** Auto-generated serialization id */
	private static final long serialVersionUID = 7706482638806990958L;

	private String uncheckedValue = null;

	public void destroy() {
	}

	public void init() {
	}

	public String intercept(ActionInvocation ai) throws Exception {
		Map parameters = ai.getInvocationContext().getParameters();
		Map<String, String> newParams = new HashMap<String, String>();
		Set<String> keys = parameters.keySet();
		for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
			String key = iterator.next();

			if (key.startsWith("__tableselectionempty_")) {
				String name = key.substring("__tableselectionempty_".length());

				iterator.remove();

				// is this checkbox checked/submitted?
				if (!parameters.containsKey(name)) {
					// if not, let's be sure to default the value to false
					newParams.put(name, uncheckedValue);
				}
			}
		}

		parameters.putAll(newParams);

		return ai.invoke();
	}

	/**
	 * Overrides the default value for an unchecked checkbox
	 *
	 * @param uncheckedValue The uncheckedValue to set
	 */
	public void setUncheckedValue(String uncheckedValue) {
		this.uncheckedValue = uncheckedValue;
	}

}
