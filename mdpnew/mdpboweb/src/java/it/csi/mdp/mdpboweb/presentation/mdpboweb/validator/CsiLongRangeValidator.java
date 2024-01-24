/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.presentation.mdpboweb.validator;

import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.AbstractRangeValidator;

/**
 * Controlla il valore del campo di tipo Long sia contenuto nel range atteso
 *
 * @author GuiGen
 */
public class CsiLongRangeValidator extends AbstractRangeValidator {

	private Long max = null;
	private Long min = null;

	public void setMax(Long max) {
		this.max = max;
	}

	public Long getMax() {
		return max;
	}

	@SuppressWarnings("unchecked")
	public Comparable getMaxComparatorValue() {
		return max;
	}

	public void setMin(Long min) {
		this.min = min;
	}

	public Long getMin() {
		return min;
	}

	@SuppressWarnings("unchecked")
	public Comparable getMinComparatorValue() {
		return min;
	}

	@Override
	public void validate(Object object) throws ValidationException {
		String fieldName = getFieldName();
		Long value = (Long) this.getFieldValue(fieldName, object);
		if (getMin() > value || (getMax() != null && getMax() < value)) {
			addFieldError(fieldName, object);
		}
	}

}
