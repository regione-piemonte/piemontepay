/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.model.cache;

import java.io.Serializable;
import java.util.Calendar;

public class CachedElement<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4310979175939438669L;
	private long creationTime;
	private T element;

	public CachedElement() {
		element = null;
		creationTime = -1;
	}

	public CachedElement(T elemnt) {
		this.element = elemnt;
		creationTime = getNow();
	}

	public T getElement() {
		return element;
	}

	public long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(long creationTime) {
		this.creationTime = creationTime;
	}

	public void setElement(T elemnt) {
		creationTime = getNow();
		this.element = elemnt;
	}

	public long getElapsed() {
		return getNow() - getCreationTime();
	}

	public static long getNow() {
		return Calendar.getInstance().getTimeInMillis();
	}

	public boolean isValid(long timeout) {
		boolean valid = element != null;
		valid = valid && (getElapsed() < timeout);
		return valid;
	}

}
