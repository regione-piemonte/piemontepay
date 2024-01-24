/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.model.report;

import java.io.Serializable;

public class RisultatiRicercaReportVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nominativoExport;
	private String tipoReport;
	private String statoReport;
	private String nomeFile;
	private String dataOraPrenotazione;
	private String dataOraUltimaModifica;
	private String dataOraInizioElaborazione;
	private String idFile;
	private String codiceStato;
	
	public RisultatiRicercaReportVO() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNominativoExport() {
		return nominativoExport;
	}

	public void setNominativoExport(String nominativoExport) {
		this.nominativoExport = nominativoExport;
	}

	public String getTipoReport() {
		return tipoReport;
	}

	public void setTipoReport(String tipoReport) {
		this.tipoReport = tipoReport;
	}

	public String getStatoReport() {
		return statoReport;
	}

	public void setStatoReport(String statoReport) {
		this.statoReport = statoReport;
	}

	public String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}

	public String getDataOraPrenotazione() {
		return dataOraPrenotazione;
	}

	public void setDataOraPrenotazione(String dataOraPrenotazione) {
		this.dataOraPrenotazione = dataOraPrenotazione;
	}

	public String getDataOraUltimaModifica() {
		return dataOraUltimaModifica;
	}

	public void setDataOraUltimaModifica(String dataOraUltimaModifica) {
		this.dataOraUltimaModifica = dataOraUltimaModifica;
	}

	public String getDataOraInizioElaborazione() {
		return dataOraInizioElaborazione;
	}

	public void setDataOraInizioElaborazione(String dataOraInizioElaborazione) {
		this.dataOraInizioElaborazione = dataOraInizioElaborazione;
	}

    /**
     * @return the idFile
     */
    public String getIdFile () {
        return idFile;
    }

    /**
     * @param idFile the idFile to set
     */
    public void setIdFile ( String idFile ) {
        this.idFile = idFile;
    }

    /**
     * @return the codiceStato
     */
    public String getCodiceStato () {
        return codiceStato;
    }

    /**
     * @param codiceStato the codiceStato to set
     */
    public void setCodiceStato ( String codiceStato ) {
        this.codiceStato = codiceStato;
    }

}
