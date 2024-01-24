/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.dto;

import javax.ws.rs.core.Response;
import java.io.Serializable;


public class ConfigurazioneServiceElementsDTO implements Serializable {

	private static final long serialVersionUID = 5419732823923136467L;

	private Response response;

	private Integer elements;

	private boolean ok;

	private ConfigurazioneServiceElementsDTO () {
	}

	public static ConfigurazioneServiceElementsDTO OK ( Integer elements ) {
		ConfigurazioneServiceElementsDTO ok = new ConfigurazioneServiceElementsDTO ();
		ok.elements = elements;
		ok.ok = true;
		return ok;
	}

	public static ConfigurazioneServiceElementsDTO KO ( Response response ) {
		ConfigurazioneServiceElementsDTO ko = new ConfigurazioneServiceElementsDTO ();
		ko.response = response;
		ko.ok = false;
		return ko;
	}

	public Response getResponse () {
		return response;
	}

	public Integer getElements () {
		return elements;
	}

	public boolean isOk () {
		return ok;
	}
}
