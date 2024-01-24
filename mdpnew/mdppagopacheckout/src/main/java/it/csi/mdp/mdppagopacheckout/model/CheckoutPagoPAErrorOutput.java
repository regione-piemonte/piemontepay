/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.model;

import java.io.Serializable;


@SuppressWarnings ( "unused" )
public class CheckoutPagoPAErrorOutput extends CheckoutPagoPAOutput implements Serializable {

	private static final long serialVersionUID = -3645450612901266602L;

	private String type;

	private String title;

	private Integer status;

	private String detail;

	public String getType () {
		return type;
	}

	public void setType ( String type ) {
		this.type = type;
	}

	public String getTitle () {
		return title;
	}

	public void setTitle ( String title ) {
		this.title = title;
	}

	public Integer getStatus () {
		return status;
	}

	public void setStatus ( Integer status ) {
		this.status = status;
	}

	public String getDetail () {
		return detail;
	}

	public void setDetail ( String detail ) {
		this.detail = detail;
	}

	@Override
	public String toString () {
		return "CheckoutPagoPAErrorOutput{" +
						"type='" + type + '\'' +
						", title='" + title + '\'' +
						", status=" + status +
						", detail='" + detail + '\'' +
						'}';
	}
}
