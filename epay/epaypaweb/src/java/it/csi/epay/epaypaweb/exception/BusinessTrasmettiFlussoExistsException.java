/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.exception;

import it.csi.epay.epaypaweb.enumeration.TipoFlussoEnum;

public class BusinessTrasmettiFlussoExistsException extends BusinessException {
	private static final long serialVersionUID = 1L;

	private String idMessaggio;
	private String codFiscaleEnte;
	private String codVersamento;
	private TipoFlussoEnum tipoFlusso;

	public BusinessTrasmettiFlussoExistsException(String idMessaggio, String codFiscaleEnte, String codVersamento, TipoFlussoEnum tipoFlusso) {
		super();
		this.idMessaggio = idMessaggio;
		this.codFiscaleEnte = codFiscaleEnte;
		this.codVersamento = codVersamento;
		this.tipoFlusso = tipoFlusso;
	}

	public String getIdMessaggio() {
		return idMessaggio;
	}

	public String getCodFiscaleEnte() {
		return codFiscaleEnte;
	}

	public String getCodVersamento() {
		return codVersamento;
	}

	public TipoFlussoEnum getTipoFlusso() {
		return tipoFlusso;
	}

}
