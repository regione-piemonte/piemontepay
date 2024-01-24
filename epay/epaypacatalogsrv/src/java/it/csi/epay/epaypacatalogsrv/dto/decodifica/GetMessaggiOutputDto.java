/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.decodifica;

import java.io.Serializable;

public class GetMessaggiOutputDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String codice;
	private String codiceLingua;
	private String codiceApplicativo;
	private String codiceBreve;
	
	private String messaggio;

	public GetMessaggiOutputDto() {
	}

	public GetMessaggiOutputDto(Long id, String codice, String codiceLingua, String codiceApplicativo,
			String codiceBreve, String messaggio) {
		super();
		this.id = id;
		this.codice = codice;
		this.codiceLingua = codiceLingua;
		this.codiceApplicativo = codiceApplicativo;
		this.codiceBreve = codiceBreve;
		this.messaggio = messaggio;
	}

	public String getCodiceApplicativo() {
		return codiceApplicativo;
	}

	public void setCodiceApplicativo(String codiceApplicativo) {
		this.codiceApplicativo = codiceApplicativo;
	}

	public String getCodiceBreve() {
		return codiceBreve;
	}

	public void setCodiceBreve(String codiceBreve) {
		this.codiceBreve = codiceBreve;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getCodiceLingua() {
		return codiceLingua;
	}

	public void setCodiceLingua(String codiceLingua) {
		this.codiceLingua = codiceLingua;
	}

	public String getMessaggio() {
		return messaggio;
	}

	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}

}
