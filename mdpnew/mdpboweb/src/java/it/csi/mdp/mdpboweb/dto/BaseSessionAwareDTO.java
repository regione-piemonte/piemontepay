/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto;

import java.util.Map;
import org.apache.log4j.*;
import it.csi.mdp.mdpboweb.util.*;

public abstract class BaseSessionAwareDTO implements java.io.Serializable {
	protected Map session;

	public void setSession(Map session) {
		this.session = session;
	}

	public Map getSession() {
		return this.session;
	}

	/**  */
	protected static final Logger log = Logger
			.getLogger(Constants.APPLICATION_CODE + ".dto");

	/**
	 * dump di debug dello stato interno del DTO
	 */
	public String dump() {
		StringBuffer sb = new StringBuffer();
		sb.append("" + getClass().getName() + "{\n");
		sb.append("\telenco fields:\n");
		java.beans.BeanInfo bi;
		try {
			bi = java.beans.Introspector.getBeanInfo(this.getClass());
			java.beans.PropertyDescriptor[] pds = bi.getPropertyDescriptors();
			for (int i = 0; i < pds.length; i++) {
				java.beans.PropertyDescriptor currPD = pds[i];
				java.lang.reflect.Method currReadMethod = currPD
						.getReadMethod();
				if (currReadMethod != null) {
					try {
						Object srcVal = currReadMethod.invoke(this,
								new Object[]{});
						sb.append("\t\t" + currPD.getName() + "=" + srcVal
								+ "\n");
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (java.lang.reflect.InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (java.beans.IntrospectionException e) {
			e.printStackTrace();
		}

		sb.append("\t\t");

		sb.append("\tsessione:\n");

		sb.append("}\n");
		return sb.toString();
	}
}
