/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.dto;

import java.sql.Timestamp;

@SuppressWarnings("serial")
public abstract class LoggingFlusso extends BaseDTO {
	
	private String idFlusso;
	private Timestamp dataOraInvio;
	private String istitutoMittente;
	private String errori;
	private String warning;
	private String esito;
	private String idMessaggio;
	
	public abstract String getTipoFlusso();
	
	public String getIdFlusso() {
		return idFlusso;
	}
	public void setIdFlusso(String idFlusso) {
		this.idFlusso = idFlusso;
	}
	public Timestamp getDataOraInvio() {
		return dataOraInvio;
	}
	public void setDataOraInvio(Timestamp dataOraInvio) {
		this.dataOraInvio = dataOraInvio;
	}
	public String getIstitutoMittente() {
		return istitutoMittente;
	}
	public void setIstitutoMittente(String istitutoMittente) {
		this.istitutoMittente = istitutoMittente;
	}
	public String getErrori() {
		return errori;
	}
	public void setErrori(String errori) {
		this.errori = errori;
	}
	public String getWarning() {
		return warning;
	}
	public void setWarning(String warning) {
		this.warning = warning;
	}

	public String getEsito() {
		return esito;
	}

	public void setEsito(String esito) {
		this.esito = esito;
	}

    
    /**
     * @return the idMessaggio
     */
    public String getIdMessaggio () {
        return idMessaggio;
    }

    
    /**
     * @param idMessaggio the idMessaggio to set
     */
    public void setIdMessaggio ( String idMessaggio ) {
        this.idMessaggio = idMessaggio;
    }
	
	
	
	

}
