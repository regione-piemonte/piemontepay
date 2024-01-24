/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.dto;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Hashtable;
import java.util.List;

public class DTOUtils {

	private static DTOUtils _instance;

	static {
		_instance = new DTOUtils();
	}

	public static DTOUtils getInstance() {
		return _instance;
	}
	public boolean deepEquals(Object o1, Object o2) {
		if ((o1 == null || o2 == null) && (o1 != null || o2 != null)) {
			return false;
		} else if (List.class.isAssignableFrom(o1.getClass())) {
			return deepEqualsArray(o1, o2);
		} else if (!(o1.getClass().equals(o2.getClass()))) {
			return false;
			// else: stesso tipo e nessuno dei due nullo
		} else if (o1 instanceof Integer || o1 instanceof Long
				|| o1 instanceof Float || o1 instanceof Double
				|| o1 instanceof String || o1 instanceof Byte
				|| o1 instanceof Boolean)
			return o1.equals(o2);
		else {
			// oggetti complessi
			return deepEqualsBean(o1, o2);
		}
	}
	private boolean deepEqualsBean(Object o1, Object o2) {
		BeanInfo bi;
		try {
			bi = Introspector.getBeanInfo(o1.getClass());
		} catch (IntrospectionException e) {
			throw new IllegalArgumentException("errore in lettura campi di "
					+ o1.getClass() + ":" + e);
		}
		PropertyDescriptor[] pds = bi.getPropertyDescriptors();
		for (int i = 0; i < pds.length; i++) {
			PropertyDescriptor currPD = pds[i];
			Method getter = currPD.getReadMethod();
			Method setter = currPD.getWriteMethod();
			if (getter != null && setter != null) {
				try {
					Object v1 = getter.invoke(o1, new Object[]{});
					Object v2 = getter.invoke(o2, new Object[]{});
					if (!deepEquals(v1, v2))
						return false; // basta uno diverso => false
				} catch (IllegalAccessException e) {
					throw new IllegalArgumentException(
							"errore in lettura campi di " + o1.getClass() + ":"
									+ e);
				} catch (InvocationTargetException e) {
					throw new IllegalArgumentException(
							"errore in lettura campi di " + o1.getClass() + ":"
									+ e);
				}
			}
		}
		return true;
	}
	private boolean deepEqualsArray(Object o1, Object o2) {
		List l1 = (List) o1;
		List l2 = (List) o2;
		if (l1.size() != l2.size())
			return false;
		else {
			for (int i = 0; i < l1.size(); i++) {
				Object curr1 = l1.get(i);
				Object curr2 = l2.get(i);
				if (!deepEquals(curr1, curr2))
					return false; // al primo diverso mi fermo
			}
			return true;
		}
	}
}
