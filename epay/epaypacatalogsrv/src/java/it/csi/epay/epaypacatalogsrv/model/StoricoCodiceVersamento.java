/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the epaycat_t_codice_versamento database table.
 *
 */
@Entity
@Table ( name = "epaycat_t_storico_codice_versamento" )
@NamedQuery ( name = "StoricoCodiceVersamento.findAll", query = "SELECT c FROM StoricoCodiceVersamento c" )
public class StoricoCodiceVersamento extends AbstractPropagableParentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String getPrimaryKeyRepresentation () {
        return String.valueOf ( id );
    }

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn ( name = "id_codice_versamento" )
    private CodiceVersamento codiceVersamento;

    private String bic;

    private String codice;

    private String descrizione;

    private String email;

    @Column ( name = "flag_invio_flussi" )
    private Boolean flagInvioFlussi;

    private String iban;

    @Column ( name = "flag_annullato" )
    private Boolean flagAnnullato;
    
    @Column ( name = "flag_mb_secondario" )
    private Boolean flagMbSecondario;
    
    @Column ( name = "flag_mb_primario" )
    private Boolean flagMbPrimario;


    public Boolean getFlagMbPrimario() {
		return flagMbPrimario;
	}

	public void setFlagMbPrimario(Boolean flagMbPrimario) {
		this.flagMbPrimario = flagMbPrimario;
	}

	//uni-directional many-to-one association to TipoPagamento
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn ( name = "codice_tipo_pagamento", referencedColumnName = "codice" )
    private TipoPagamento tipoPagamento;

    //uni-directional many-to-one association to Ente
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn ( name = "id_ente" )
    private Ente ente;

    //uni-directional many-to-one association to VoceEntrata
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn ( name = "codice_voce_entrata", referencedColumnName = "codice" )
    private VoceEntrata voceEntrata;
    
    //uni-directional many-to-one association to StatoMultibeneficiario
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn ( name = "stato_mb", referencedColumnName = "codice" )
    private  StatoMultibeneficiario statoMultibeneficiario;

    @Column ( name = "bic_appoggio" )
    private String bicAppoggio;

    @Column ( name = "iban_appoggio" )
    private String ibanAppoggio;

    @Column ( name = "bollettino_postale" )
    private Boolean flagPresenzaBollettinoPostale;

    @Column ( name = "iban_appoggio_postale" )
    private Boolean ibanAppoggioPostale;

    @Column ( name = "iban_postale" )
    private Boolean ibanPostale;

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn ( name = "codice_modalita_integrazione", referencedColumnName = "codice" )
    private ModalitaIntegrazione modalitaIntegrazione;

    
    public ModalitaIntegrazione getModalitaIntegrazione () {
        return modalitaIntegrazione;
    }

    public void setModalitaIntegrazione ( ModalitaIntegrazione modalitaIntegrazione ) {
        this.modalitaIntegrazione = modalitaIntegrazione;
    }
    
    
    public StoricoCodiceVersamento () {
    }

    public Boolean getFlagAnnullato () {
        return flagAnnullato;
    }

    public void setFlagAnnullato ( Boolean flagAnnullato ) {
        this.flagAnnullato = flagAnnullato;
    }

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public String getBic () {
        return bic;
    }

    public void setBic ( String bic ) {
        this.bic = bic;
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

    public String getEmail () {
        return email;
    }

    public void setEmail ( String email ) {
        this.email = email;
    }

    public Boolean getFlagInvioFlussi () {
        return flagInvioFlussi;
    }

    public void setFlagInvioFlussi ( Boolean flagInvioFlussi ) {
        this.flagInvioFlussi = flagInvioFlussi;
    }

    public String getIban () {
        return iban;
    }

    public void setIban ( String iban ) {
        this.iban = iban;
    }

    public TipoPagamento getTipoPagamento () {
        return tipoPagamento;
    }

    public void setTipoPagamento ( TipoPagamento tipoPagamento ) {
        this.tipoPagamento = tipoPagamento;
    }

    public Ente getEnte () {
        return ente;
    }

    public void setEnte ( Ente ente ) {
        this.ente = ente;
    }

    public VoceEntrata getVoceEntrata () {
        return voceEntrata;
    }

    public void setVoceEntrata ( VoceEntrata voceEntrata ) {
        this.voceEntrata = voceEntrata;
    }

    public CodiceVersamento getCodiceVersamento () {
        return codiceVersamento;
    }

    public void setCodiceVersamento ( CodiceVersamento codiceVersamento ) {
        this.codiceVersamento = codiceVersamento;
    }

    /**
     * @return the bicAppoggio
     */
    public String getBicAppoggio () {
        return bicAppoggio;
    }

    /**
     * @param bicAppoggio the bicAppoggio to set
     */
    public void setBicAppoggio ( String bicAppoggio ) {
        this.bicAppoggio = bicAppoggio;
    }

    /**
     * @return the ibanAppoggio
     */
    public String getIbanAppoggio () {
        return ibanAppoggio;
    }

    /**
     * @param ibanAppoggio the ibanAppoggio to set
     */
    public void setIbanAppoggio ( String ibanAppoggio ) {
        this.ibanAppoggio = ibanAppoggio;
    }

    /**
     * @return the flagPresenzaBollettinoPostale
     */
    public Boolean getFlagPresenzaBollettinoPostale () {
        return flagPresenzaBollettinoPostale;
    }

    /**
     * @param flagPresenzaBollettinoPostale the flagPresenzaBollettinoPostale to set
     */
    public void setFlagPresenzaBollettinoPostale ( Boolean flagPresenzaBollettinoPostale ) {
        this.flagPresenzaBollettinoPostale = flagPresenzaBollettinoPostale;
    }

    /**
     * @return the ibanAppoggioPostale
     */
    public Boolean getIbanAppoggioPostale () {
        return ibanAppoggioPostale;
    }

    /**
     * @param ibanAppoggioPostale the ibanAppoggioPostale to set
     */
    public void setIbanAppoggioPostale ( Boolean ibanAppoggioPostale ) {
        this.ibanAppoggioPostale = ibanAppoggioPostale;
    }

    /**
     * @return the ibanPostale
     */
    public Boolean getIbanPostale () {
        return ibanPostale;
    }

    /**
     * @param ibanPostale the ibanPostale to set
     */
    public void setIbanPostale ( Boolean ibanPostale ) {
        this.ibanPostale = ibanPostale;
    }

	public Boolean getFlagMbSecondario() {
		return flagMbSecondario;
	}

	public void setFlagMbSecondario(Boolean flagMbSecondario) {
		this.flagMbSecondario = flagMbSecondario;
	}

	public StatoMultibeneficiario getStatoMultibeneficiario() {
		return statoMultibeneficiario;
	}

	public void setStatoMultibeneficiario(StatoMultibeneficiario statoMultibeneficiario) {
		this.statoMultibeneficiario = statoMultibeneficiario;
	}

	@Override
	public String toString() {
		return "StoricoCodiceVersamento [id=" + id + "]";
	}
	

}
