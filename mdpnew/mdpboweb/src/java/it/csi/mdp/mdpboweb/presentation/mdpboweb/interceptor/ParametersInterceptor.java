/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.mdpboweb.interceptor;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.inject.Inject;
import com.opensymphony.xwork2.interceptor.NoParameters;
import com.opensymphony.xwork2.interceptor.ParameterNameAware;
import com.opensymphony.xwork2.util.LocalizedTextUtil;
import com.opensymphony.xwork2.util.OgnlContextState;
import com.opensymphony.xwork2.util.TextParseUtil;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * 
 * @author guigen
 * personalizzazione dell'interceptor com.opensymphony.xwork2.interceptor.ParametersInterceptor
 * per poter invocare un metodo e passare parametri allo strato struts ad es:
 * <s:submit name="widg_bDettaglio" id="widg_bDettaglio" method="handleBDettaglio_CLICKED?param1=uno&param2=due" />
 *
 */
public class ParametersInterceptor extends MethodFilterInterceptor {

	private static final Log LOG = LogFactory
			.getLog(ParametersInterceptor.class);

	boolean ordered = false;
	Set<Pattern> excludeParams = Collections.EMPTY_SET;
	Set<Pattern> acceptedParams = Collections.EMPTY_SET;

	private String acceptedParamNames = "[\\p{Graph}&&[^,#:=]]*";
	private Pattern acceptedPattern = Pattern.compile(acceptedParamNames);

	static boolean devMode = false;

	@Inject(value = "devMode", required = false)
	public static void setDevMode(String mode) {
		devMode = "true".equals(mode);
	}

	public void setAcceptedParamNames(String commaDelim) {
		Collection<String> acceptPatterns = asCollection(commaDelim);
		if (acceptPatterns != null) {
			acceptedParams = new HashSet<Pattern>();
			for (String pattern : acceptPatterns) {
				acceptedParams.add(Pattern.compile(pattern));
			}
		}
	}

	/**
	 * Compares based on number of '.' characters (fewer is higher)
	 */
	static final Comparator rbCollator = new Comparator() {
		public int compare(Object arg0, Object arg1) {
			String s1 = (String) arg0;
			String s2 = (String) arg1;
			int l1 = 0, l2 = 0;
			for (int i = s1.length() - 1; i >= 0; i--) {
				if (s1.charAt(i) == '.')
					l1++;
			}
			for (int i = s2.length() - 1; i >= 0; i--) {
				if (s2.charAt(i) == '.')
					l2++;
			}
			return l1 < l2 ? -1 : (l2 < l1 ? 1 : s1.compareTo(s2));
		}

		;
	};

	public String doIntercept(ActionInvocation invocation) throws Exception {
		Object action = invocation.getAction();
		if (!(action instanceof NoParameters)) {
			ActionContext ac = invocation.getInvocationContext();
			final Map parameters = ac.getParameters();

			if (LOG.isDebugEnabled()) {
				LOG.debug("Setting params " + getParameterLogMap(parameters));
			}

			if (parameters != null) {

				final Map newParameters = new HashMap();
				newParameters.putAll(parameters);
				for (Iterator iterator = parameters.keySet().iterator(); iterator
						.hasNext();) {
					String key = (String) iterator.next();
					Object param = parameters.get(key);
					normalizeParams(key, param, newParameters, invocation);
				}

				Map contextMap = ac.getContextMap();
				try {
					OgnlContextState.setCreatingNullObjects(contextMap, true);
					OgnlContextState.setDenyMethodExecution(contextMap, true);
					OgnlContextState.setReportingConversionErrors(contextMap,
							true);

					ValueStack stack = ac.getValueStack();
					setParameters(action, stack, newParameters);
				} finally {
					OgnlContextState.setCreatingNullObjects(contextMap, false);
					OgnlContextState.setDenyMethodExecution(contextMap, false);
					OgnlContextState.setReportingConversionErrors(contextMap,
							false);
				}
			}
		}
		return invocation.invoke();
	}

	private void normalizeParams(String key, Object param, Map parameters,
			ActionInvocation invocation) {

		if (key.contains("?")) {
			parameters.remove(key);
			String newKey = key.substring(0, key.indexOf("?"));
			parameters.put(newKey, param);
			addMethodParams(key.substring(key.indexOf("?") + 1, key.length()),
					parameters);
		}

	}

	private void addMethodParams(String substring, Map parameters) {
		StringTokenizer tok = new StringTokenizer(substring, "&");
		while (tok.hasMoreTokens()) {
			String tmp = tok.nextToken();
			parameters.put(tmp.substring(0, tmp.indexOf("=")),
					tmp.substring(tmp.indexOf("=") + 1, tmp.length()));
		}

	}

	protected void setParameters(Object action, ValueStack stack,
			final Map parameters) {
		ParameterNameAware parameterNameAware = (action instanceof ParameterNameAware)
				? (ParameterNameAware) action
				: null;

		Map params = null;
		if (ordered) {
			params = new TreeMap(getOrderedComparator());
			params.putAll(parameters);
		} else {
			params = new TreeMap(parameters);
		}

		for (Iterator iterator = params.entrySet().iterator(); iterator
				.hasNext();) {
			Map.Entry entry = (Map.Entry) iterator.next();
			String name = entry.getKey().toString();

			boolean acceptableName = acceptableName(name)
					&& (parameterNameAware == null || parameterNameAware
							.acceptableParameterName(name));

			if (acceptableName) {
				Object value = entry.getValue();
				try {
					stack.setValue(name, value);
				} catch (RuntimeException e) {
					if (devMode) {
						String developerNotification = LocalizedTextUtil
								.findText(ParametersInterceptor.class,
										"devmode.notification", ActionContext
												.getContext().getLocale(),
										"Developer Notification:\n{0}",
										new Object[]{e.getMessage()});
						LOG.error(developerNotification);
						if (action instanceof ValidationAware) {
							((ValidationAware) action)
									.addActionMessage(developerNotification);
						}
					} else {
						LOG.error("ParametersInterceptor - [setParameters]: Unexpected Exception caught setting '"
								+ name
								+ "' on '"
								+ action.getClass()
								+ ": "
								+ e.getMessage());
					}
				}
			}
		}
	}

	/**
	 * Gets an instance of the comparator to use for the ordered sorting.  Override this
	 * method to customize the ordering of the parameters as they are set to the
	 * action.
	 *
	 * @return A comparator to sort the parameters
	 */
	protected Comparator getOrderedComparator() {
		return rbCollator;
	}

	private String getParameterLogMap(Map parameters) {
		if (parameters == null) {
			return "NONE";
		}

		StringBuffer logEntry = new StringBuffer();
		for (Iterator paramIter = parameters.entrySet().iterator(); paramIter
				.hasNext();) {
			Map.Entry entry = (Map.Entry) paramIter.next();
			logEntry.append(String.valueOf(entry.getKey()));
			logEntry.append(" => ");
			if (entry.getValue() instanceof Object[]) {
				Object[] valueArray = (Object[]) entry.getValue();
				logEntry.append("[ ");
				for (int indexA = 0; indexA < (valueArray.length - 1); indexA++) {
					Object valueAtIndex = valueArray[indexA];
					logEntry.append(valueAtIndex);
					logEntry.append(String.valueOf(valueAtIndex));
					logEntry.append(", ");
				}
				logEntry.append(String
						.valueOf(valueArray[valueArray.length - 1]));
				logEntry.append(" ] ");
			} else {
				logEntry.append(String.valueOf(entry.getValue()));
			}
		}

		return logEntry.toString();
	}

	protected boolean acceptableName(String name) {
		if (isAccepted(name) && !isExcluded(name)) {
			return true;
		}
		return false;
	}

	protected boolean isAccepted(String paramName) {
		if (!this.acceptedParams.isEmpty()) {
			for (Pattern pattern : acceptedParams) {
				Matcher matcher = pattern.matcher(paramName);
				if (!matcher.matches()) {
					return false;
				}
			}
		}
		return acceptedPattern.matcher(paramName).matches();
	}

	protected boolean isExcluded(String paramName) {
		if (!this.excludeParams.isEmpty()) {
			for (Pattern pattern : excludeParams) {
				Matcher matcher = pattern.matcher(paramName);
				if (matcher.matches()) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Whether to order the parameters or not
	 *
	 * @return True to order
	 */
	public boolean isOrdered() {
		return ordered;
	}

	/**
	 * Set whether to order the parameters by object depth or not
	 *
	 * @param ordered True to order them
	 */
	public void setOrdered(boolean ordered) {
		this.ordered = ordered;
	}

	/**
	 * Gets a set of regular expressions of parameters to remove
	 * from the parameter map
	 *
	 * @return A set of compiled regular expression patterns
	 */
	protected Set getExcludeParamsSet() {
		return excludeParams;
	}

	/**
	 * Sets a comma-delimited list of regular expressions to match
	 * parameters that should be removed from the parameter map.
	 *
	 * @param commaDelim A comma-delimited list of regular expressions
	 */
	public void setExcludeParams(String commaDelim) {
		Collection<String> excludePatterns = asCollection(commaDelim);
		if (excludePatterns != null) {
			excludeParams = new HashSet<Pattern>();
			for (String pattern : excludePatterns) {
				excludeParams.add(Pattern.compile(pattern));
			}
		}
	}

	/**
	 * Return a collection from the comma delimited String.
	 *
	 * @param commaDelim
	 * @return A collection from the comma delimited String.
	 */
	private Collection asCollection(String commaDelim) {
		if (commaDelim == null || commaDelim.trim().length() == 0) {
			return null;
		}
		return TextParseUtil.commaDelimitedStringToSet(commaDelim);
	}
}
