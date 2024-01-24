/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto;

import static it.csi.epay.epaypaweb.util.Util.quote;

import java.io.Serializable;

public class StatoFlussoAggregatoDto implements Serializable {
	private static final long serialVersionUID = 1L;

	 protected String idFlusso;
	    protected String codFiscaleEnte;
	    protected String statoFlusso;
	    protected String datoAggiuntivoCodEsito;
	    protected String datoAggiuntivoDescEsito;
	    
	public StatoFlussoAggregatoDto(String idFlusso, String codFiscaleEnte) {
		this.idFlusso = idFlusso;
		this.codFiscaleEnte = codFiscaleEnte;
	}

    public StatoFlussoAggregatoDto ( String idFlusso, String codFiscaleEnte,
    		String statoFlusso, String datoAggiuntivoCodEsito,  String datoAggiuntivoDescEsito ) {
    	this.idFlusso = idFlusso;
		this.codFiscaleEnte = codFiscaleEnte;
		this.statoFlusso = statoFlusso;
		this.datoAggiuntivoCodEsito = datoAggiuntivoCodEsito;
		this.datoAggiuntivoDescEsito = datoAggiuntivoDescEsito;
    }

    

	public String getIdFlusso() {
		return idFlusso;
	}

	public void setIdFlusso(String idFlusso) {
		this.idFlusso = idFlusso;
	}

	public String getCodFiscaleEnte() {
		return codFiscaleEnte;
	}

	public void setCodFiscaleEnte(String codFiscaleEnte) {
		this.codFiscaleEnte = codFiscaleEnte;
	}

	public String getStatoFlusso() {
		return statoFlusso;
	}

	public void setStatoFlusso(String statoFlusso) {
		this.statoFlusso = statoFlusso;
	}

	public String getDatoAggiuntivoCodEsito() {
		return datoAggiuntivoCodEsito;
	}

	public void setDatoAggiuntivoCodEsito(String datoAggiuntivoCodEsito) {
		this.datoAggiuntivoCodEsito = datoAggiuntivoCodEsito;
	}

	public String getDatoAggiuntivoDescEsito() {
		return datoAggiuntivoDescEsito;
	}

	public void setDatoAggiuntivoDescEsito(String datoAggiuntivoDescEsito) {
		this.datoAggiuntivoDescEsito = datoAggiuntivoDescEsito;
	}

	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("id:").append(idFlusso).append(COMMA);
		s.append("codFiscale:").append(quote(codFiscaleEnte)).append(COMMA);
		s.append("statoFlusso:").append(quote(statoFlusso)).append(COMMA);
		s.append("datoAggiuntivoCodEsito:").append(quote(datoAggiuntivoCodEsito));
		s.append("datoAggiuntivoDescEsito:").append(quote(datoAggiuntivoDescEsito));
		s.append(" }");
		return s.toString();
	}

}
