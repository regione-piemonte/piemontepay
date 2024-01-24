/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.mdpboweb.interceptor;
import java.util.Collections;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptorUtil;
import com.opensymphony.xwork2.util.TextParseUtil;

public abstract class MethodFilterInterceptor extends AbstractInterceptor {
	protected transient Log log = LogFactory.getLog(getClass());

	protected Set excludeMethods = Collections.EMPTY_SET;
	protected Set includeMethods = Collections.EMPTY_SET;

	public void setExcludeMethods(String excludeMethods) {
		this.excludeMethods = TextParseUtil
				.commaDelimitedStringToSet(excludeMethods);
	}

	public Set getExcludeMethodsSet() {
		return excludeMethods;
	}

	public void setIncludeMethods(String includeMethods) {
		this.includeMethods = TextParseUtil
				.commaDelimitedStringToSet(includeMethods);
	}

	public Set getIncludeMethodsSet() {
		return includeMethods;
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		if (applyInterceptor(invocation)) {
			return doIntercept(invocation);
		}
		return invocation.invoke();
	}

	protected boolean applyInterceptor(ActionInvocation invocation) {
		String method = invocation.getProxy().getMethod();
		if (method.contains("?")) {
			invocation.getProxy().setMethod(
					method.substring(0, method.indexOf("?")));
		}
		// ValidationInterceptor
		boolean applyMethod = MethodFilterInterceptorUtil.applyMethod(
				excludeMethods, includeMethods, method);
		if (log.isDebugEnabled()) {
			if (!applyMethod) {
				log.debug("Skipping Interceptor... Method [" + method
						+ "] found in exclude list.");
			}
		}
		return applyMethod;
	}

	/**
	 * Subclasses must override to implement the interceptor logic.
	 * 
	 * @param invocation the action invocation
	 * @return the result of invocation
	 * @throws Exception
	 */
	protected abstract String doIntercept(ActionInvocation invocation)
			throws Exception;

}
