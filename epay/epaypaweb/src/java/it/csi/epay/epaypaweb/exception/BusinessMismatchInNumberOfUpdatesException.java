/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.exception;

import java.io.Serializable;

public class BusinessMismatchInNumberOfUpdatesException extends BusinessException implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;

	private int int1;
	private int int2;

	private long long1;
	private long long2;

	public BusinessMismatchInNumberOfUpdatesException(long id, int int1, int int2) {
		super();
		this.id = id;
		this.int1 = int1;
		this.int2 = int2;
	}

	public BusinessMismatchInNumberOfUpdatesException(long id, long long1, long long2) {
		super();
		this.id = id;
		this.long1 = long1;
		this.long2 = long2;
	}

	public long getId() {
		return id;
	}

	public int getInt1() {
		return int1;
	}

	public int getInt2() {
		return int2;
	}

	public long getLong1() {
		return long1;
	}

	public long getLong2() {
		return long2;
	}

}
