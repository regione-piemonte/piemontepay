/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.dto;

import static it.csi.epay.epaywsosrv.util.Util.quote;

import java.io.Serializable;

public class RicercaStatoAggregatoDto implements Serializable {
	

	private static final long serialVersionUID = 1L;

	    private String idFlusso;
	    private String codiceFiscaleEnte;
	    private String idMessaggio;

		public String getIdFlusso() {
			return idFlusso;
		}

		public void setIdFlusso(String idFlusso) {
			this.idFlusso = idFlusso;
		}

		public String getCodiceFiscaleEnte() {
			return codiceFiscaleEnte;
		}

		public void setCodiceFiscaleEnte(String codiceFiscaleEnte) {
			this.codiceFiscaleEnte = codiceFiscaleEnte;
		}
		
		public String getIdMessaggio() {
			return idMessaggio;
		}

		public void setIdMessaggio(String idMessaggio) {
			this.idMessaggio = idMessaggio;
		}



	@Override
	public String toString() {
		final String COMMA = ", ";
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		s.append("idFlusso:").append(idFlusso).append(COMMA);
		s.append("codFiscaleEnte:").append(quote(codiceFiscaleEnte));
		s.append(" }");
		return s.toString();
	}

}
