/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

public class StrutsOptionsSupport {
	private Integer id;
	private String cod;
	private String option;

	public StrutsOptionsSupport(int id, String option) {
		this(id, null, option);
	}

	public StrutsOptionsSupport(String cod, String option) {
		this(null, cod, option);
	}

	public StrutsOptionsSupport(Integer id, String cod, String option) {
		this.id = id;
		this.cod = cod;
		this.option = option;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}
}
