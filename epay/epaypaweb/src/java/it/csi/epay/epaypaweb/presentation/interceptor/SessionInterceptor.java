/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import it.csi.epay.epaypaweb.presentation.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static it.csi.epay.epaypaweb.util.Util.APPLICATION_CODE;

public class SessionInterceptor implements Interceptor {
	static private final long serialVersionUID = 1L;
	static private final String CLASSNAME = SessionInterceptor.class.getSimpleName();
	static private final Logger log = LogManager.getLogger(APPLICATION_CODE + ".presentation");

	@Override
	public void destroy() {
	}

	@Override
	public void init() {
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String methodName = "intercept";
//		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName, false);
//		lw.start();
		log.info ( CLASSNAME + " " + methodName + " - START" );
		String result;

		if (!"login".equals(invocation.getInvocationContext().getName())) {
			if (ActionContext.getContext().getSession().get(Constants.SESSION_CONTEXT_SESSION_KEY) == null)
				result = "invalid-session";
			else
				result = invocation.invoke();
		} else
			result = invocation.invoke();

//		lw.stop();
		log.info ( CLASSNAME + " " + methodName + " - STOP" );
		return result;
	}

}
