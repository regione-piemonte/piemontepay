/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import it.csi.epay.epaypaweb.presentation.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

import static it.csi.epay.epaypaweb.util.Util.APPLICATION_CODE;

public class FakeShibbolethInterceptor implements Interceptor {
	static private final long serialVersionUID = 1L;
	static private final String CLASSNAME = FakeShibbolethInterceptor.class.getSimpleName();
	static private final Logger log = LogManager.getLogger(APPLICATION_CODE + ".presentation");

	@Override
	public void destroy() {
	}

	@Override
	public void init() {
	}

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		String methodName = "intercept";
//		LogAndWatch lw = new LogAndWatch(log, CLASSNAME, methodName, false);
		log.info ( CLASSNAME + " " + methodName + " - START" );
//		lw.start();

		HttpServletRequest httpRequest = ServletActionContext.getRequest();
		String cf = httpRequest.getParameter("CF");

		log.debug("CF:" + httpRequest.getParameter("CF"));

		if (cf != null) {
			actionInvocation.getInvocationContext().put(Constants.USER_CODE_CONTEXT_KEY, cf);
			actionInvocation.getInvocationContext().put(Constants.IRIDE_AUTH_TOKEN_KEY, "AUTH_ID_1");
		}

//		lw.stop();
		log.info ( CLASSNAME + " " + methodName + " - STOP" );
		return actionInvocation.invoke();
	}

}
