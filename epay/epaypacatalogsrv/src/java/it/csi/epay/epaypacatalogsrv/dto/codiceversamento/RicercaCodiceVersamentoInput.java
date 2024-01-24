/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.codiceversamento;

import it.csi.epay.epaypacatalogsrv.dto.ParentInput;


public class RicercaCodiceVersamentoInput extends ParentInput {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String codice;

    private String descrizione;

    private String codiceMacrotipo;

    private String codiceTematica;

    private String codiceVoceEntrata;

    private String iban;

    private String codiceModalitaIntegrazione;

	private Boolean flagPresenzaBollettinoPostale;

	private Boolean ibanPostale;

	private Boolean ibanAppoggioPostale;
	
	private Boolean flagMbSecondario;
	
	private Boolean flagMbPrimario;

	private Boolean codiceMultibeneficiario;

    public String getCodiceModalitaIntegrazione () {
        return codiceModalitaIntegrazione;
    }

    public void setCodiceModalitaIntegrazione ( String codiceModalitaIntegrazione ) {
        this.codiceModalitaIntegrazione = codiceModalitaIntegrazione;
    }
    
    @Override
    public String toString () {
        return "RicercaCodiciVersamentoInput [descrizione=" + descrizione + ", codiceMacrotipo=" + codiceMacrotipo + ", codiceTematica=" + codiceTematica
            + ", codiceVoceEntrata=" + codiceVoceEntrata + ", iban=" + iban 
            + ", flagMbSecondario=" + flagMbSecondario 
             + ", flagMbPrimario=" + flagMbPrimario
            + "]";
    }

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getCodice () {
        return codice;
    }

    public void setCodice ( String codice ) {
        this.codice = codice;
    }

    public String getDescrizione () {
        return descrizione;
    }

    public void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }

    public String getCodiceMacrotipo () {
        return codiceMacrotipo;
    }

    public void setCodiceMacrotipo ( String codiceMacrotipo ) {
        this.codiceMacrotipo = codiceMacrotipo;
    }

    public String getCodiceTematica () {
        return codiceTematica;
    }

    public void setCodiceTematica ( String codiceTematica ) {
        this.codiceTematica = codiceTematica;
    }

    public String getCodiceVoceEntrata () {
        return codiceVoceEntrata;
    }

    public void setCodiceVoceEntrata ( String codiceVoceEntrata ) {
        this.codiceVoceEntrata = codiceVoceEntrata;
    }

    public String getIban () {
        return iban;
    }

    public void setIban ( String iban ) {
        this.iban = iban;
    }

    
	/**
	 * @return the ibanPostale
	 */
	public Boolean getIbanPostale() {
		return ibanPostale;
	}

	/**
	 * @param ibanPostale the ibanPostale to set
	 */
	public void setIbanPostale(Boolean ibanPostale) {
		this.ibanPostale = ibanPostale;
	}

	/**
	 * @return the ibanAppoggioPostale
	 */
	public Boolean getIbanAppoggioPostale() {
		return ibanAppoggioPostale;
	}

	/**
	 * @param ibanAppoggioPostale the ibanAppoggioPostale to set
	 */
	public void setIbanAppoggioPostale(Boolean ibanAppoggioPostale) {
		this.ibanAppoggioPostale = ibanAppoggioPostale;
	}

	public Boolean getFlagPresenzaBollettinoPostale() {
		return flagPresenzaBollettinoPostale;
	}

	public void setFlagPresenzaBollettinoPostale(Boolean flagPresenzaBollettinoPostale) {
		this.flagPresenzaBollettinoPostale = flagPresenzaBollettinoPostale;
	}

	public Boolean getFlagMbSecondario() {
		return flagMbSecondario;
	}

	public void setFlagMbSecondario(Boolean flagMbSecondario) {
		this.flagMbSecondario = flagMbSecondario;
	}

	public Boolean getFlagMbPrimario() {
		return flagMbPrimario;
	}

	public void setFlagMbPrimario(Boolean flagMbPrimario) {
		this.flagMbPrimario = flagMbPrimario;
	}

	public Boolean getCodiceMultibeneficiario() {
		return codiceMultibeneficiario;
	}

	public void setCodiceMultibeneficiario(Boolean codiceMultibeneficiario) {
		this.codiceMultibeneficiario = codiceMultibeneficiario;
	}
}
