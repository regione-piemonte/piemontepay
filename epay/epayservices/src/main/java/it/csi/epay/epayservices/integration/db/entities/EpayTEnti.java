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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the epay_t_enti database table.
 *
 */
@Entity
@Table ( name = "epay_t_enti" )

@NamedQueries ( {
    @NamedQuery ( name = "EpayTEnti.findAll", query = "SELECT e FROM EpayTEnti e" )
} )
public class EpayTEnti implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator ( name = "EPAY_T_ENTI_IDENTE_GENERATOR", allocationSize = 1, sequenceName = "EPAY_T_ENTI_ID_ENTE_SEQ" )
    @GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "EPAY_T_ENTI_IDENTE_GENERATOR" )
    @Column ( name = "id_ente", unique = true, nullable = false )
    private Long idEnte;

    @Column ( name = "codice_fiscale", nullable = false, length = 16 )
    private String codiceFiscale;

    @Column ( name = "codice_gs1_gln", nullable = false, precision = 13 )
    private BigDecimal codiceGs1Gln;

    @Column ( name = "flag_invio_pagamenti", nullable = false )
    private Boolean flagInvioPagamenti;

    @Column ( name = "flag_intermediato" )
    private Boolean flagIntermediato;

	@Column ( name = "flag_adesione_cittafacile" )
	private Boolean flagAdesioneCittaFacile;

    public Boolean getFlagIntermediato () {
        return flagIntermediato;
    }

    public void setFlagIntermediato ( Boolean flagIntermediato ) {
        this.flagIntermediato = flagIntermediato;
    }

    @Column ( nullable = false )
    private byte [] logo;

    @Column ( nullable = false, length = 250 )
    private String nome;

    @Column ( length = 2000 )
    private String orari;

    @Column ( name = "codice_interbancario", length = 5 )
    private String codiceInterbancario;

    @Column(name="flag_invio_notificatore", nullable=false)
    private Boolean flagInvioNotificatore;

    @Column(name="passphrase", nullable=false)
    private byte[] passphrase;

    //bi-directional many-to-one association to EpayTTipoPagamento
    @OneToMany ( mappedBy = "epayTEnti" )
    private List<EpayTTipoPagamento> epayTTipoPagamentos;

	@Column ( name = "flag_riconciliazione_versamenti" )
	private Boolean flagRiconciliazioneVersamenti;

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

    public EpayTEnti () {
    }


    public EpayTEnti ( Long idEnte, byte[] passphrase ) {
        this.idEnte = idEnte;
        this.passphrase = passphrase;
    }

    public EpayTEnti ( Long idEnte, String codiceFiscale, BigDecimal codiceGs1Gln, String codiceInterbancario, Boolean flagInvioPagamenti, String nome,
        String orari ) {
        this.idEnte = idEnte;
        this.codiceFiscale = codiceFiscale;
        this.codiceGs1Gln = codiceGs1Gln;
        this.codiceInterbancario = codiceInterbancario;
        this.flagInvioPagamenti = flagInvioPagamenti;
        this.nome = nome;
        this.orari = orari;
        logo = null;
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


    public Boolean getFlagInvioNotificatore() {
        return flagInvioNotificatore;
    }

    public void setFlagInvioNotificatore(Boolean flagInvioNotificatore) {
        this.flagInvioNotificatore = flagInvioNotificatore;
    }

    public byte[] getPassphrase() {
        return passphrase;
    }

    public void setPassphrase(byte[] passphrase) {
        this.passphrase = passphrase;
    }

    public String getCodiceInterbancario () {
        return codiceInterbancario;
    }

    public void setCodiceInterbancario ( String codiceInterbancario ) {
        this.codiceInterbancario = codiceInterbancario;
    }

    public List<EpayTTipoPagamento> getEpayTTipoPagamentos () {
        return epayTTipoPagamentos;
    }

    public void setEpayTTipoPagamentos ( List<EpayTTipoPagamento> epayTTipoPagamentos ) {
        this.epayTTipoPagamentos = epayTTipoPagamentos;
    }

    public EpayTTipoPagamento addEpayTTipoPagamento ( EpayTTipoPagamento epayTTipoPagamento ) {
        getEpayTTipoPagamentos ().add ( epayTTipoPagamento );
        epayTTipoPagamento.setEpayTEnti ( this );

        return epayTTipoPagamento;
    }

    public EpayTTipoPagamento removeEpayTTipoPagamento ( EpayTTipoPagamento epayTTipoPagamento ) {
        getEpayTTipoPagamentos ().remove ( epayTTipoPagamento );
        epayTTipoPagamento.setEpayTEnti ( null );

        return epayTTipoPagamento;
    }

	public Boolean getFlagAdesioneCittaFacile () {
		return flagAdesioneCittaFacile;
	}

	public void setFlagAdesioneCittaFacile ( Boolean flagAdesioneCittaFacile ) {
		this.flagAdesioneCittaFacile = flagAdesioneCittaFacile;
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
