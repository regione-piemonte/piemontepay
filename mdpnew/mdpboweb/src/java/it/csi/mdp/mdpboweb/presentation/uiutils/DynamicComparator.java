/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.uiutils;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @generated
 */
@SuppressWarnings("unchecked")
public final class DynamicComparator implements Comparator {
	// Class Members
	public static final int EQUAL = 0;
	public static final int LESS_THAN = -1;
	public static final int GREATER_THAN = 1;

	@SuppressWarnings("unused")
	private Collection collection;
	private String methodName;
	private boolean sortAsc;

	private DynamicComparator(Collection collection, String methodName,
			boolean sortAsc) {
		this.collection = collection;
		this.methodName = methodName;
		this.sortAsc = sortAsc;
	}

	// Sort invocation starts here
	public static void sort(List list, String methodName, boolean sortAsc) {
		Collections
				.sort(list, new DynamicComparator(list, methodName, sortAsc));
	}

	// Compare for Collections.sort here

	public int compare(Object o1, Object o2) {
		int rv = 0;

		try {
			// Determine the return type
			Method method = Utility.convertiMetodo(methodName, o1); // o1.getClass().getMethod(methodName, null);
			Class c = method.getReturnType();
			//System.out.println("return type is " + c);

			// Invoke method to gather two comparable objects
			java.lang.Object result1 = method.invoke(o1, (Object[]) null);
			java.lang.Object result2 = method.invoke(o2, (Object[]) null);
			//***controllo se mi sta arrivando una data in formato String
			if (result1 instanceof String
					&& (Utility.convertToDate((String) result1) instanceof Date)) {
				result1 = Utility.convertToDate((String) result1);
				result2 = Utility.convertToDate((String) result2);
			}

			if (c.isAssignableFrom(Class.forName("java.util.Comparator"))) {
				//System.out.println(c + " implements java.util.Comparator");
				java.util.Comparator c1 = (java.util.Comparator) result1;
				java.util.Comparator c2 = (java.util.Comparator) result2;
				rv = c1.compare(c1, c2);
			} else if (Class.forName("java.lang.Comparable")
					.isAssignableFrom(c)) {
				//System.out.println(c + "implements java.lang.Comparable");
				java.lang.Comparable c1 = (java.lang.Comparable) result1;
				java.lang.Comparable c2 = (java.lang.Comparable) result2;
				rv = c1.compareTo(c2);
			} else if (c.isPrimitive()) {
				//System.out.println("c.isPrimitive");
				long f1 = ((Number) result1).longValue();
				long f2 = ((Number) result2).longValue();
				if (f1 == f2)
					rv = EQUAL;
				else if (f1 < f2)
					rv = LESS_THAN;
				else if (f1 > f2)
					rv = GREATER_THAN;
			} else {
				throw new DynamicComparatorException(
						"DynamicComparator does not currently support ''"
								+ c.getName() + "''!");
			}
		} catch (Exception nsme) {
			throw new DynamicComparatorException(
					"Attenzione gli unici tipi primitivi supportati dal comparator sono i long e gli int",
					nsme.getCause());
		}
		return rv * getSortOrder();
	}

	/**
	 * Not used for sorting. Only here to meet the requirements of the
	 * Comparator interface.
	 * 
	 * @param o
	 *            The object for comparison
	 * @return boolean
	 */
	public boolean equals(Object o) {
		return true;
	}

	// Sort order getter
	private int getSortOrder() {
		return sortAsc ? 1 : -1;
	}
}
