/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * The persistent class for the epay_t_enti database table.
 *
 */
@Entity
@Table ( name = "epay_t_enti_temp" )
public class EpayTEntiTemp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="EPAY_T_ENTI_TEMP_ID_GENERATOR", sequenceName="epay.EPAY_T_ENTI_TEMP_ID_ENTE_SEQ" ,schema="epay", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EPAY_T_ENTI_TEMP_ID_GENERATOR")
    @Column ( name = "id_ente" )
    private Long idEnte;

    @Column ( name = "id_operazione", nullable = false, length = 100 )
    private String idOperazione;

    @Column ( name = "codice_fiscale", nullable = false, length = 16 )
    private String codiceFiscale;

    @Column ( name = "codice_gs1_gln", precision = 13 )
    private BigDecimal codiceGs1Gln;

    @Column ( name = "flag_invio_pagamenti" )
    private Boolean flagInvioPagamenti;

    @Column ()
    private byte [] logo;

    @Column ( nullable = false, length = 250 )
    private String nome;

    @Column ( length = 2000 )
    private String orari;

    @Column ( name = "codice_interbancario", length = 5 )
    private String codiceInterbancario;

    @Column ( name = "flag_riconciliazione_versamenti" )
    private Boolean flagRiconciliazioneVersamenti;

	@Column ( name = "flag_adesione_cittafacile" )
	private Boolean flagAdesioneCittaFacile;

	@Column ( name = "codice_istat" )
	private String codiceIstat;

    @Column ( name = "template_email_id", length = 50 )
    private String templateEmailId;
    
    @Column ( name = "url_dominio", length = 50 )
    private String urlDominio;
    
    @Column ( name = "codice_ipa", length = 10 )
    private String codiceIpa;


    public Boolean getFlagRiconciliazioneVersamenti () {
        return flagRiconciliazioneVersamenti;
    }

    public void setFlagRiconciliazioneVersamenti ( Boolean flagRiconciliazioneVersamenti ) {
        this.flagRiconciliazioneVersamenti = flagRiconciliazioneVersamenti;
    }

	public Boolean getFlagAdesioneCittaFacile () {
		return flagAdesioneCittaFacile;
	}

	public void setFlagAdesioneCittaFacile ( Boolean value ) {
		this.flagAdesioneCittaFacile = value;
	}

    public String getIdOperazione () {
        return idOperazione;
    }

    public void setIdOperazione ( String idOperazione ) {
        this.idOperazione = idOperazione;
    }

    public EpayTEntiTemp () {
    }

    public Long getIdEnte () {
        return idEnte;
    }


    public void setIdEnte ( Long idEnte ) {
        this.idEnte = idEnte;
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

    public Boolean getFlagInvioPagamenti () {
        return flagInvioPagamenti;
    }

    public void setFlagInvioPagamenti ( Boolean flagInvioPagamenti ) {
        this.flagInvioPagamenti = flagInvioPagamenti;
    }

    public byte [] getLogo () {
        return logo;
    }

    public void setLogo ( byte [] logo ) {
        this.logo = logo;
    }

    public String getNome () {
        return nome;
    }

    public void setNome ( String nome ) {
        this.nome = nome;
    }

    public String getOrari () {
        return orari;
    }

    public void setOrari ( String orari ) {
        this.orari = orari;
    }

    public String getCodiceInterbancario () {
        return codiceInterbancario;
    }

    public void setCodiceInterbancario ( String codiceInterbancario ) {
        this.codiceInterbancario = codiceInterbancario;
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
