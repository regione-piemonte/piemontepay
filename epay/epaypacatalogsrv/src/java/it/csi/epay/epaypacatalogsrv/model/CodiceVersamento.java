/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the epaycat_t_codice_versamento database table.
 *
 */
@Entity
@Table ( name = "epaycat_t_codice_versamento" )
@NamedQuery ( name = "CodiceVersamento.findAll", query = "SELECT c FROM CodiceVersamento c" )
public class CodiceVersamento extends AbstractPropagableParentEntity implements Serializable {

    public List<CodiceVersamento> getCodiciVersamentoSecondariCollegati() {
		return codiciVersamentoSecondariCollegati;
	}

	public void setCodiciVersamentoSecondariCollegati(List<CodiceVersamento> codiciVersamentoSecondariCollegati) {
		this.codiciVersamentoSecondariCollegati = codiciVersamentoSecondariCollegati;
	}

	private static final long serialVersionUID = 1L;

    @Override
    public String getPrimaryKeyRepresentation () {
        return String.valueOf ( id );
    }

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Long id;

    private String bic;

    private String codice;

    private String descrizione;

    private String email;
    
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

    @Column ( name = "flag_invio_flussi" )
    private Boolean flagInvioFlussi;

    private String iban;

    @Column ( name = "flag_annullato" )
    private Boolean flagAnnullato;
    
    @Column ( name = "flag_mb_secondario" )
    private Boolean flagMbSecondario;
    
    @Column ( name = "flag_mb_primario" )
    private Boolean flagMbPrimario;
    
    @Column ( name = "flag_personalizzazione_cov", nullable = false )
    private Boolean flagPersonalizzazioneCov;
    
    @Column ( name = "flag_invio_notificatore", nullable = false )
    private Boolean flagInvioNotificatore;
    
    @Column(name="passphrase", nullable=false)
    private byte[] passphrase;
    
    @Column ( name = "descrizione_text_cov", nullable = true, length = 250 )
    private String descrizioneTextCov;
    
    @Column ( name = "flag_visualizza_da_sportello" )
    private Boolean flagVisualizzaDaSportello;
    
    

    @Column ( name = "data_inizio_validita" )
    private Date dataInizioValidita;

    @Column ( name = "data_fine_validita" )
    private Date dataFineValidita;
    
    

    //uni-directional many-to-one association to TipoPagamento
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn ( name = "codice_tipo_pagamento", referencedColumnName = "codice" )
    private TipoPagamento tipoPagamento;

    //bi-directional many-to-one association to CodiceVersamento
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn ( name = "id_padre" )
    private CodiceVersamento codiceVersamentoPadre;

    //bi-directional many-to-one association to CodiceVersamento
    @OneToMany ( mappedBy = "codiceVersamentoPadre" )
    private List<CodiceVersamento> codiciVersamentoCollegati;

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
    @JoinColumn ( name = "stato_mb_config", referencedColumnName = "codice" )
    private  StatoMultibeneficiario statoMultibeneficiario;
    
    @ManyToMany
    @JoinTable (
                    name = "epaycat_r_codice_versamento_mb",
                    joinColumns = { @JoinColumn ( name = "id_codice_versamento_primario", referencedColumnName = "id" ) },
                    inverseJoinColumns = { @JoinColumn ( name = "id_codice_versamento_secondario", referencedColumnName = "id" ) } )
    List <CodiceVersamento> codiciVersamentoSecondariCollegati = new LinkedList<CodiceVersamento> ();
    
    
    @Column ( name = "fattura" )
    private Boolean fattura;
    
    
    @Column(name="credenziali_Pnd", nullable=true)
    private byte[] credenzialiPnd;
    
    @Column ( name = "url_attualizzazione_Pnd", nullable = true, length = 200 )
    private String urlAttualizzazionePnd;
    
    
   

    public CodiceVersamento () {
    }

    public ModalitaIntegrazione getModalitaIntegrazione () {
        return modalitaIntegrazione;
    }

    public void setModalitaIntegrazione ( ModalitaIntegrazione modalitaIntegrazione ) {
        this.modalitaIntegrazione = modalitaIntegrazione;
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

    public CodiceVersamento getCodiceVersamentoPadre () {
        return codiceVersamentoPadre;
    }

    public void setCodiceVersamentoPadre ( CodiceVersamento codiceVersamentoPadre ) {
        this.codiceVersamentoPadre = codiceVersamentoPadre;
    }

    public List<CodiceVersamento> getCodiciVersamentoCollegati () {
        return codiciVersamentoCollegati;
    }

    public void setCodiciVersamentoCollegati ( List<CodiceVersamento> codiciVersamentoCollegati ) {
        this.codiciVersamentoCollegati = codiciVersamentoCollegati;
    }

    public CodiceVersamento addCodiciVersamentoCollegati ( CodiceVersamento codiciVersamentoCollegati ) {
        getCodiciVersamentoCollegati ().add ( codiciVersamentoCollegati );
        codiciVersamentoCollegati.setCodiceVersamentoPadre ( this );

        return codiciVersamentoCollegati;
    }

    public CodiceVersamento removeCodiciVersamentoCollegati ( CodiceVersamento codiciVersamentoCollegati ) {
        getCodiciVersamentoCollegati ().remove ( codiciVersamentoCollegati );
        codiciVersamentoCollegati.setCodiceVersamentoPadre ( null );

        return codiciVersamentoCollegati;
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
    
    // RD10   aggiungere campi di appoggio
    
    /**
	 * @return the bicAppoggio
	 */
	public String getBicAppoggio() {
		return bicAppoggio;
	}

    
    public String getIbanAppoggio () {
        return ibanAppoggio;
    }

    
    public void setIbanAppoggio ( String ibanAppoggio ) {
        this.ibanAppoggio = ibanAppoggio;
    }

    
    public Boolean getFlagPresenzaBollettinoPostale () {
        return flagPresenzaBollettinoPostale;
    }

    
    public void setFlagPresenzaBollettinoPostale ( Boolean flagPresenzaBollettinoPostale ) {
        this.flagPresenzaBollettinoPostale = flagPresenzaBollettinoPostale;
    }

    
    public Boolean getIbanAppoggioPostale () {
        return ibanAppoggioPostale;
    }

    
    public void setIbanAppoggioPostale ( Boolean ibanAppoggioPostale ) {
        this.ibanAppoggioPostale = ibanAppoggioPostale;
    }

    
    public Boolean getIbanPostale () {
        return ibanPostale;
    }

    
    public void setIbanPostale ( Boolean ibanPostale ) {
        this.ibanPostale = ibanPostale;
    }

    
    public void setBicAppoggio ( String bicAppoggio ) {
        this.bicAppoggio = bicAppoggio;
    }

    
    public Boolean getFattura () {
        return fattura;
    }

    
    public void setFattura ( Boolean fattura ) {
        this.fattura = fattura;
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

	public Boolean getFlagMbPrimario() {
		return flagMbPrimario;
	}

	public void setFlagMbPrimario(Boolean flagMbPrimario) {
		this.flagMbPrimario = flagMbPrimario;
	}
	

	
    public Boolean getFlagPersonalizzazioneCov () {
        return flagPersonalizzazioneCov;
    }

    
    public void setFlagPersonalizzazioneCov ( Boolean flagPersonalizzazioneCov ) {
        this.flagPersonalizzazioneCov = flagPersonalizzazioneCov;
    }

    
    public Boolean getFlagInvioNotificatore () {
        return flagInvioNotificatore;
    }

    
    public void setFlagInvioNotificatore ( Boolean flagInvioNotificatore ) {
        this.flagInvioNotificatore = flagInvioNotificatore;
    }

    
    public byte [] getPassphrase () {
        return passphrase;
    }

    
    public void setPassphrase ( byte [] passphrase ) {
        this.passphrase = passphrase;
    }

    
    public String getDescrizioneTextCov () {
        return descrizioneTextCov;
    }

    
    public void setDescrizioneTextCov ( String descrizioneTextCov ) {
        this.descrizioneTextCov = descrizioneTextCov;
    }
    

    
    public Boolean getFlagVisualizzaDaSportello () {
        return flagVisualizzaDaSportello;
    }

    
    public void setFlagVisualizzaDaSportello ( Boolean flagVisualizzaDaSportello ) {
        this.flagVisualizzaDaSportello = flagVisualizzaDaSportello;
    }

   

    
    public Date getDataInizioValidita () {
        return dataInizioValidita;
    }

    
    public void setDataInizioValidita ( Date dataInizioValidita ) {
        this.dataInizioValidita = dataInizioValidita;
    }

    
    public Date getDataFineValidita () {
        return dataFineValidita;
    }

    
    public void setDataFineValidita ( Date dataFineValidita ) {
        this.dataFineValidita = dataFineValidita;
    }

    
    
    /**
     * @return the credenzialiPnd
     */
    public byte [] getCredenzialiPnd () {
        return credenzialiPnd;
    }

    
    /**
     * @param credenzialiPnd the credenzialiPnd to set
     */
    public void setCredenzialiPnd ( byte [] credenzialiPnd ) {
        this.credenzialiPnd = credenzialiPnd;
    }

    
    /**
     * @return the urlAttualizzazionePnd
     */
    public String getUrlAttualizzazionePnd () {
        return urlAttualizzazionePnd;
    }

    
    /**
     * @param urlAttualizzazionePnd the urlAttualizzazionePnd to set
     */
    public void setUrlAttualizzazionePnd ( String urlAttualizzazionePnd ) {
        this.urlAttualizzazionePnd = urlAttualizzazionePnd;
    }

    @Override
	public String toString() {
		return "CodiceVersamento [id=" + id + ", codice=" + codice + ", descrizione=" + descrizione + "]";
	}

	
	
	

	    
    

}
