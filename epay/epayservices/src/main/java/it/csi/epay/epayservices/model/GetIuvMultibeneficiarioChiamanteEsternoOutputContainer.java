/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.util.List;


public class GetIuvMultibeneficiarioChiamanteEsternoOutputContainer implements Serializable, OutputContainer {

	private static final long serialVersionUID = -4591634294527208320L;

	private String descrizioneEsito;

	private String codiceEsito;

	private List<GetIuvMultibeneficiarioChiamanteEsternoOutput> elementiPosizioneDebitoria;

	public String getDescrizioneEsito () {
		return descrizioneEsito;
	}

	@Override
	public void setDescrizioneEsito ( String descrizioneEsito ) {
		this.descrizioneEsito = descrizioneEsito;
	}

	public String getCodiceEsito () {
		return codiceEsito;
	}

	@Override
	public void setCodiceEsito ( String codiceEsito ) {
		this.codiceEsito = codiceEsito;
	}

	public List<GetIuvMultibeneficiarioChiamanteEsternoOutput> getElementiPosizioneDebitoria () {
		return elementiPosizioneDebitoria;
	}

	public void setElementiPosizioneDebitoria ( List<GetIuvMultibeneficiarioChiamanteEsternoOutput> elementiPosizioneDebitoria ) {
		this.elementiPosizioneDebitoria = elementiPosizioneDebitoria;
	}

	@Override
	public String toString () {
		return "GetIuvMultibeneficiarioChiamanteEsternoOutputContainer [descrizioneEsito=" + descrizioneEsito + ", codiceEsito=" + codiceEsito
			+ ", elementiPosizioneDebitoria=" + elementiPosizioneDebitoria + "]";
	}

}
