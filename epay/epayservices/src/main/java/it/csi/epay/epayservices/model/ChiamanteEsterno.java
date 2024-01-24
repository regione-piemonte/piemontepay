/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.util.Date;

public class ChiamanteEsterno implements Serializable {
	
	private static final long serialVersionUID = -6903007449346036409L;

	private String codiceChiamante;
	
	private String descrizioneChiamante;
	
	private Date dataInizioValidita;
	
	private Date dataFineValidita;

    private String url;
    
    private String passphrase;

	public String getCodiceChiamante() {
		return codiceChiamante;
	}

	public void setCodiceChiamante(String codiceChiamante) {
		this.codiceChiamante = codiceChiamante;
	}

	public String getDescrizioneChiamante() {
		return descrizioneChiamante;
	}

	public void setDescrizioneChiamante(String descrizioneChiamante) {
		this.descrizioneChiamante = descrizioneChiamante;
	}

	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}

	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public Date getDataFineValidita() {
		return dataFineValidita;
	}

	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

    public String getUrl () {
        return url;
    }

    public void setUrl ( String url ) {
        this.url = url;
    }

	public String getPassphrase() {
		return passphrase;
	}

	public void setPassphrase(String passphrase) {
		this.passphrase = passphrase;
	}

}
