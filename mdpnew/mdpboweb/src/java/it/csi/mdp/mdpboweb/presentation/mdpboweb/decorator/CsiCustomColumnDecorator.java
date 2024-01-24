/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.mdpboweb.decorator;

import java.text.NumberFormat;
import java.util.Locale;

import org.displaytag.decorator.ColumnDecorator;
import org.displaytag.exception.DecoratorException;

/**
 * Custom Column Decorator per DisplayTag: formatta i numeri.
 * 
 * @author [GUIGEN]
 */
public class CsiCustomColumnDecorator implements ColumnDecorator {

	/**
	 *
	 * @param columnValue
	 * @return
	 * @see org.displaytag.decorator.ColumnDecorator#decorate(java.lang.Object)
	 */
	public String decorate(Object columnValue) throws DecoratorException {
		if (columnValue != null) {
			if (columnValue.getClass() == Integer.class
					|| columnValue.getClass() == Long.class) {
				// tipo numerico intero
				return doFormatFromIntegerToString(columnValue);
			} else if (columnValue.getClass() == Float.class
					|| columnValue.getClass() == Double.class) {
				// tipo intero decimale
				return doFormatFromDecimalToString(columnValue);
			}
			// TODO: implementare la logica di decorazione per altri tipi (se necessario)

			// per tutti gli altri tipi ritorno la stringa
			return columnValue.toString();
		}
		return null;
	}

	/**
	 * 
	 * @param columnValue
	 * @return
	 */
	private String doFormatFromIntegerToString(Object columnValue) {
		NumberFormat df = NumberFormat.getNumberInstance(Locale.ITALY);
		return df.format(columnValue);
	}

	/**
	 * 
	 * @param columnValue
	 * @return
	 */
	private String doFormatFromDecimalToString(Object columnValue) {
		NumberFormat df = NumberFormat.getNumberInstance(Locale.ITALY);
		df.setMinimumFractionDigits(2);
		df.setMaximumFractionDigits(2);
		return df.format(columnValue);
	}

}
