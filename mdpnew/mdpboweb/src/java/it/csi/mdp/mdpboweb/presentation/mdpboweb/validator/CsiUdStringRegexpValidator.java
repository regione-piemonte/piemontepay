/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.mdpboweb.validator;

import java.lang.reflect.Method;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;

import com.opensymphony.xwork2.validator.*;
import com.opensymphony.xwork2.validator.validators.*;

import org.apache.log4j.Logger;

import it.csi.mdp.mdpboweb.util.*;

/**
 * CsiUdStringRegexpValidator Simple Type User Defined Validator Class.
 *
 * @author GuiGen
 */
public class CsiUdStringRegexpValidator extends RegexFieldValidator {

	protected static final Logger log = Logger
			.getLogger(Constants.APPLICATION_CODE + ".presentation.validator");

	@Override
	public void validate(Object object) throws ValidationException {
		// nome del campo da validare
		String fieldName = getFieldName();

		// valore del campo (questo e' il SimpleType User Defined)
		Object fieldObj = (Object) this.getFieldValue(fieldName, object);
		if (fieldObj == null) {
			return;
		}

		// reperisco il valore del SimpleType User Defined chiamando il metodo getValue()
		// se non c'e' nessun valore non faccio nessuna validazione
		// se e' obbligatorio un valore, allora deve essere aggiunto sul campo un required validator
		Object obj;
		try {
			Method readMethod = findReadMethod("value", fieldObj.getClass());
			obj = readMethod.invoke(fieldObj, new Object[]{});
			if (obj == null) {
				return;
			}
		} catch (Exception e) {
			log.error(
					"[CsiUdStringRegexpValidator::validate] Errore nel reperimento o invocazione del metodo getValue, classe:"
							+ object.getClass().getName() + ": " + e, e);
			throw new ValidationException(
					"Errore nel reperimento o invocazione del metodo getValue, classe:"
							+ fieldObj.getClass().getName() + ": " + e);
		}

		// da qui in avanti c'e' la logica del metodo originale RegexFieldValidator.validate()
		// XW-375 - must be a string
		if (!(obj instanceof String)) {
			return;
		}

		// string must not be empty
		String str = ((String) obj).trim();
		if (str.length() == 0) {
			return;
		}

		// match against expression
		java.util.regex.Pattern pattern;
		if (isCaseSensitive()) {
			pattern = java.util.regex.Pattern.compile(getExpression());
		} else {
			pattern = java.util.regex.Pattern.compile(getExpression(),
					java.util.regex.Pattern.CASE_INSENSITIVE);
		}

		String compare = (String) obj;
		if (isTrimed()) {
			compare = compare.trim();
		}
		java.util.regex.Matcher matcher = pattern.matcher(compare);

		if (!matcher.matches()) {
			addFieldError(fieldName, object);
		}

	}

	////////////////////////////////////////////////////////////////////////////////////////
	// PROPRIETA' E METODI DEL VALIDATORE ORIGINALE SOVRASCRITTI O COPIATI
	////////////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////////////////
	// INTROSPECTION METHODS
	////////////////////////////////////////////////////////////////////////////////////////

	private Class objClass;

	private Class getObjClass() {
		return objClass;
	}

	private void setObjClass(Class cl) {
		objClass = cl;
	}

	private Method findReadMethod(String name, Class cl)
			throws IntrospectionException {
		name = (name.startsWith("get") || name.startsWith("set") ? name
				.substring(3) : name.startsWith("is")
				? name.substring(2)
				: name);
		BeanInfo bi = java.beans.Introspector.getBeanInfo(cl);
		PropertyDescriptor[] pds = bi.getPropertyDescriptors();
		for (int i = 0; i < pds.length; i++) {
			PropertyDescriptor currPd = pds[i];
			if (currPd.getName().equalsIgnoreCase(name))
				return currPd.getReadMethod();
		}
		return null;
	}

	private Method findWriteMethod(String name, Class cl)
			throws IntrospectionException {
		name = (name.startsWith("get") || name.startsWith("set") ? name
				.substring(3) : name.startsWith("is")
				? name.substring(2)
				: name);
		BeanInfo bi = java.beans.Introspector.getBeanInfo(cl);
		PropertyDescriptor[] pds = bi.getPropertyDescriptors();
		for (int i = 0; i < pds.length; i++) {
			PropertyDescriptor currPd = pds[i];
			if (currPd.getName().equalsIgnoreCase(name))
				return currPd.getWriteMethod();
		}
		return null;
	}

}
