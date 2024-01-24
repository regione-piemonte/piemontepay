/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.mdpboweb.interceptor;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Iterator;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

public class MethodProtectionInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = -6680894220590585506L;

	public static final String INVALID_TOKEN_CODE = "invalid.token";

	/**
	 * @see com.opensymphony.xwork2.interceptor.MethodFilterInterceptor#doIntercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Intercepting invocation to check for valid transaction token.");
		}

		Map session = ActionContext.getContext().getSession();

		synchronized (session) {
			// verifica se c'e' un lock in corso
			String declaredProtection = getDeclaredProtection(invocation);
			if (mustReject(invocation, session, declaredProtection))
				return handleReject(invocation, session);
			else {
				// insert lock
				String lockMarker = insertLock(invocation, session,
						declaredProtection);
				String _res = null;
				try {
					_res = handleAllow(invocation);
				} catch (Exception e) {
					removeLock(lockMarker, session);
					throw new Exception(
							"Errore occorso nell'esecuzione del metodo: " + e);
				}
				// remove lock
				removeLock(lockMarker, session);
				return _res;
			}
		}
	}

	final static String LOCK_ALL_MARKER = "__lockAllMethods";
	final static String LOCK_SAME_MARKER_PREFIX = "__lockSameMethods_";

	protected String methodLockMarker(ActionInvocation invocation) {
		return LOCK_SAME_MARKER_PREFIX + invocation.getProxy().getActionName()
				+ "." + invocation.getProxy().getMethod();
	}

	/**
	 * 
	 * @param invocation
	 * @return null se il metodo della action non e' annotato, il tipo di annotazione
	 * altrimenti
	 */
	protected String getDeclaredProtection(ActionInvocation invocation) {
		String lockType = null;
		String methodName = invocation.getProxy().getMethod();
		Method m = null;
		try {
			m = invocation.getAction().getClass()
					.getDeclaredMethod(methodName, new Class[]{});
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		if (m != null) {
			MethodProtection prot = m.getAnnotation(MethodProtection.class);
			if (prot == null)
				lockType = MethodProtection.ALLOW_ALL;
			else
				lockType = prot.level();
		}
		return lockType;
	}

	protected String insertLock(ActionInvocation invocation, Map session,
			String declaredProtection) {
		if (MethodProtection.REJECT_ALL.equals(declaredProtection)) {
			session.put(LOCK_ALL_MARKER, LOCK_ALL_MARKER);
			return LOCK_ALL_MARKER;
		} else if (MethodProtection.REJECT_SAME.equals(declaredProtection)) {
			String marker = methodLockMarker(invocation);
			session.put(marker, marker);
			return marker;
		} else {
			// no lock
			return null;
		}
	}

	protected void removeLock(String lockMarker, Map session) {
		if (lockMarker != null)
			session.remove(lockMarker);
	}

	/**
	 * cancella tutti i lock (globale e locali)
	 * @param session
	 */
	protected void removeAllLocks(Map session) {
		session.remove(LOCK_ALL_MARKER);
		Iterator<String> keys = session.keySet().iterator();
		while (keys.hasNext()) {
			String currKey = keys.next();
			if (currKey.startsWith(LOCK_SAME_MARKER_PREFIX))
				session.remove(currKey);
		}
	}

	/**
	 * 
	 * @param invocation
	 * @return true se c'e' un lock in corso che riguarda l'invocazione corrente, ovvero:
	 * se il lock e' un lockAll oppure se il lock e' un lockSame e il metodo lockato coincide
	 * con quello corrente. 
	 */
	protected boolean mustReject(ActionInvocation invocation, Map session,
			String declaredProtection) {
		if (declaredProtection == null)
			return false; // annotazione assente: non rientra nel lock
		String lockAll = (String) session.get(LOCK_ALL_MARKER);
		if (lockAll != null)
			return true;
		String lockSame = (String) session.get(methodLockMarker(invocation));
		if (lockSame != null)
			return true;
		// else
		return false;
	}

	protected String handleReject(ActionInvocation invocation, Map session)
			throws Exception {
		Object action = invocation.getAction();
		String errorMessage = LocalizedTextUtil
				.findText(
						this.getClass(),
						"struts.messages.invalid.token",
						invocation.getInvocationContext().getLocale(),
						"The form has already been processed or no token was supplied, please try again.",
						new Object[0]);

		if (action instanceof ValidationAware) {
			((ValidationAware) action).addActionError(errorMessage);
		} else {
			log.warn(errorMessage);
		}
		removeAllLocks(session);
		return INVALID_TOKEN_CODE;
	}

	/**
	 * Called when we must allow method execution. 
	 *
	 * @param invocation the action invocation
	 * @throws Exception when any unexpected error occurs.
	 */
	protected String handleAllow(ActionInvocation invocation) throws Exception {
		return invocation.invoke();
	}
}
