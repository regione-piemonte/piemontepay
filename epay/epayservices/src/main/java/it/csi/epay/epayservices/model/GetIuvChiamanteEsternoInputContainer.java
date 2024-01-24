/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.util.List;


public final class GetIuvChiamanteEsternoInputContainer extends AccessoChiamanteEsternoSincronoSplitInput implements Serializable {

	private static final long serialVersionUID = -2340042605691964607L;

	private String codiceFiscaleEnte;

	private String tipoPagamento;

	private Integer numeroPosizioniDebitorie;

	private List<GetIuvChiamanteEsternoInput> elencoPosizioniDaInserire;

	public String getCodiceFiscaleEnte () {
		return codiceFiscaleEnte;
	}

	public void setCodiceFiscaleEnte ( String codiceFiscaleEnte ) {
		this.codiceFiscaleEnte = codiceFiscaleEnte;
	}

	public String getTipoPagamento () {
		return tipoPagamento;
	}

	public void setTipoPagamento ( String tipoPagamento ) {
		this.tipoPagamento = tipoPagamento;
	}

	public Integer getNumeroPosizioniDebitorie () {
		return numeroPosizioniDebitorie;
	}

	public void setNumeroPosizioniDebitorie ( Integer numeroPosizioniDebitorie ) {
		this.numeroPosizioniDebitorie = numeroPosizioniDebitorie;
	}

	public List<GetIuvChiamanteEsternoInput> getElencoPosizioniDaInserire () {
		return elencoPosizioniDaInserire;
	}

	public void setElencoPosizioniDaInserire ( List<GetIuvChiamanteEsternoInput> elencoPosizioniDaInserire ) {
		this.elencoPosizioniDaInserire = elencoPosizioniDaInserire;
	}

	@Override
	public String toString () {
		return "GetIuvChiamanteEsternoInputContainer [codiceFiscaleEnte=" + codiceFiscaleEnte + ", tipoPagamento=" + tipoPagamento
			+ ", numeroPosizioniDebitorie=" + numeroPosizioniDebitorie + ", elencoPosizioniDaInserire=" + elencoPosizioniDaInserire + "]";
	}

}
