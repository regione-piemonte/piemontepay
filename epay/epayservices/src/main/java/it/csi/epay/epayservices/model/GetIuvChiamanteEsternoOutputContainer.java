/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.util.List;

/**
 *
 */

public class GetIuvChiamanteEsternoOutputContainer implements Serializable, OutputContainer {

	private static final long serialVersionUID = 4782593219891658921L;

	private String descrizioneEsito;

	private String codiceEsito;

	private List<GetIuvChiamanteEsternoOutput> elementiPosizioneDebitoria;


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

	public List<GetIuvChiamanteEsternoOutput> getElementiPosizioneDebitoria () {
		return elementiPosizioneDebitoria;
	}

	public void setElementiPosizioneDebitoria ( List<GetIuvChiamanteEsternoOutput> elementiPosizioneDebitoria ) {
		this.elementiPosizioneDebitoria = elementiPosizioneDebitoria;
	}

	@Override
	public String toString () {
		return "GetIuvChiamanteEsternoOutputContainer [descrizioneEsito=" + descrizioneEsito + ", codiceEsito=" + codiceEsito + ", elementiPosizioneDebitoria="
			+ elementiPosizioneDebitoria + "]";
	}


}
