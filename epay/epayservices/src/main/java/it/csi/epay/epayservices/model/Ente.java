/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.math.BigDecimal;


public class Ente implements Serializable {

	private static final long serialVersionUID = -7841755359116294830L;

	private Long idEnte;

	private byte[] logo;

	private String nome;

	private String codiceFiscale;

	private BigDecimal codiceGs1Gln;

	private String orari;

	private Boolean flagInvioPagamenti;

	private String codiceInterbancario;

	private Boolean flagRiconciliazioneVersamenti;

	private Boolean flagAdesioneCittaFacile;

	private byte[] passphrase;

	private String codiceIstat;
    
    public Boolean getFlagRiconciliazioneVersamenti () {
        return flagRiconciliazioneVersamenti;
    }
    private String templateEmailId;
    
    private String urlDominio;
    
    private String codiceIpa;


	public void setFlagRiconciliazioneVersamenti ( Boolean flagRiconciliazioneVersamenti ) {
		this.flagRiconciliazioneVersamenti = flagRiconciliazioneVersamenti;
	}

	public Long getIdEnte () {
		return idEnte;
	}

	public void setIdEnte ( Long idEnte ) {
		this.idEnte = idEnte;
	}

	public byte[] getLogo () {
		return logo;
	}

	public void setLogo ( byte[] logo ) {
		this.logo = logo;
	}

	public String getNome () {
		return nome;
	}

	public void setNome ( String nome ) {
		this.nome = nome;
	}

	public String getCodiceFiscale () {
		return codiceFiscale;
	}

	public void setCodiceFiscale ( String codiceFiscale ) {
		this.codiceFiscale = codiceFiscale;
	}

	public BigDecimal getCodiceGs1Gln () {
		return codiceGs1Gln;
	}

	public void setCodiceGs1Gln ( BigDecimal codiceGs1Gln ) {
		this.codiceGs1Gln = codiceGs1Gln;
	}

	public String getOrari () {
		return orari;
	}

	public void setOrari ( String orari ) {
		this.orari = orari;
	}

	/**
	 * @return the flagInvioPagamenti
	 */
	public Boolean getFlagInvioPagamenti () {
		return flagInvioPagamenti;
	}

	/**
	 * @param flagInvioPagamenti the flagInvioPagamenti to set
	 */
	public void setFlagInvioPagamenti ( Boolean flagInvioPagamenti ) {
		this.flagInvioPagamenti = flagInvioPagamenti;
	}

	public String getCodiceInterbancario () {
		return codiceInterbancario;
	}

	public void setCodiceInterbancario ( String codiceInterbancario ) {
		this.codiceInterbancario = codiceInterbancario;
	}

	public byte[] getPassphrase () {
		return passphrase;
	}

	public void setPassphrase ( byte[] passphrase ) {
		this.passphrase = passphrase;
	}

	public Boolean getFlagAdesioneCittaFacile () {
		return flagAdesioneCittaFacile;
	}

	public void setFlagAdesioneCittaFacile ( Boolean flagAdesioneCittaFacile ) {
		this.flagAdesioneCittaFacile = flagAdesioneCittaFacile;
	}

	public String getCodiceIstat () {
		return codiceIstat;
	}

	public void setCodiceIstat ( String codiceIstat ) {
		this.codiceIstat = codiceIstat;
	}

    
    public String getTemplateEmailId () {
        return templateEmailId;
    }

    
    public void setTemplateEmailId ( String templateEmailId ) {
        this.templateEmailId = templateEmailId;
    }

    
    public String getUrlDominio () {
        return urlDominio;
    }

    
    public void setUrlDominio ( String urlDominio ) {
        this.urlDominio = urlDominio;
    }

    
    public String getCodiceIpa () {
        return codiceIpa;
    }

    
    public void setCodiceIpa ( String codiceIpa ) {
        this.codiceIpa = codiceIpa;
    }
	
	
}
	
