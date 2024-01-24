/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.Interceptor;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import static it.csi.epay.epaypaweb.util.Util.APPLICATION_CODE;

public class ExceptionInterceptor implements Interceptor {

	private static final long serialVersionUID = 1L;

	static private final Logger log = LogManager.getLogger(APPLICATION_CODE + ".presentation");

	@Override
	public void destroy() {
	}

	@Override
	public void init() {
	}

	@Override
	public String intercept(ActionInvocation actionInvocation) {
		try {
			return actionInvocation.invoke();
		} catch (Throwable t) {
			log.error("ExceptionInterceptor - Unhandled exception:", t);
			((ActionSupport) actionInvocation.getAction()).addActionError(
				((ActionSupport) actionInvocation.getAction()).getText("system.error", new String[] {
				t.getClass().getName(),
				t.getMessage()
			}));
		}
		return "system-error";
	}

}
