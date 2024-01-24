/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the epay_t_tipo_pagamento database table.
 *
 */
@Entity
@Table ( name = "epay_t_tipo_pagamento" )
public class EpayTTipoPagamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator ( name = "EPAY_T_TIPO_PAGAMENTO_IDTIPOPAGAMENTO_GENERATOR", allocationSize = 1,
    sequenceName = "EPAY_T_TIPO_PAGAMENTO_ID_TIPO_PAGAMENTO_SEQ" )
    @GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "EPAY_T_TIPO_PAGAMENTO_IDTIPOPAGAMENTO_GENERATOR" )
    @Column ( name = "id_tipo_pagamento", unique = true, nullable = false )
    private Long idTipoPagamento;

    @Column ( name = "codice_versamento", nullable = false, length = 4 )
    private String codiceVersamento;

    @Column ( name = "compilazione_note", length = 2000 )
    private String compilazioneNote;

    @Column ( name = "contatore_compilazioni", nullable = false )
    private Long contatoreCompilazioni;

    @Column ( name = "contatore_pagamenti", nullable = false )
    private Long contatorePagamenti;

    @Column ( name = "contatore_selezioni", nullable = false )
    private Long contatoreSelezioni;

    @Column ( name = "dati_specifici_riscossione", length = 140 )
    private String datiSpecificiRiscossione;

    @Column ( name = "descrizione_portale", nullable = false, length = 140 )
    private String descrizionePortale;

    @Temporal ( TemporalType.DATE )
    @Column ( name = "fine_validita" )
    private Date fineValidita;

    @Column ( name = "flag_invio_pagamenti", nullable = false )
    private Boolean flagInvioPagamenti;

    @Column ( name = "id_applicazione", nullable = false, length = 30 )
    private String idApplicazione;

    @Temporal ( TemporalType.DATE )
    @Column ( name = "inizio_validita", nullable = false )
    private Date inizioValidita;

    @Column ( name = "pagamento_spontaneo", nullable = false )
    private Boolean pagamentoSpontaneo;

    @Column ( name = "bollettino_postale" )
    private Boolean flagPresenzaBollettinoPostale;

    //bi-directional many-to-one association to EpayTPagamento
    @OneToMany ( mappedBy = "epayTTipoPagamento" )
    private List<EpayTPagamento> epayTPagamentos;

    //bi-directional many-to-one association to EpayTEnti
    @ManyToOne
    @JoinColumn ( name = "id_ente", nullable = false )
    private EpayTEnti epayTEnti;

    @Column ( name = "numero_accertamento", nullable = true, length = 35 )
    private String numeroAccertamento;

    @Column ( name = "anno_accertamento", nullable = true )
    private Long annoAccertamento;

    @Column ( name = "chiave_intersistema", nullable = true, length = 100 )
    private String chiaveIntersistema;

    @Column ( name = "flag_invio_rt", nullable = false )
    private Boolean flagInvioRT;
    
    @Column ( name = "multibeneficiario" )
    private Boolean flagMultibeneficiario;
    
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
    

    @ManyToOne
    @JoinColumn ( name = "id_tipologia_pagamento", nullable = true )
    private EpayDTipologiaPagamento tipologiaPagamento;
    
    //bi-directional many-to-one association to EpayTPagamentoSecondario
    @OneToMany(mappedBy="epayTTipoPagamento")
    private List<EpayTPagamentoSecondario> epayTPagamentoSecondarios;
    
    @Column ( name = "url_attualizzazione_Pnd", nullable = true )
    private String urlAttualizzazionePnd;
    
    @Column(name="credenziali_Pnd", nullable=true)
    private byte[] credenzialiPnd;
    

    public EpayTTipoPagamento () {
    }
    
    
    public EpayTTipoPagamento ( Long idTipoPagamento, byte[] passphrase ) {
        this.idTipoPagamento = idTipoPagamento;
        this.passphrase = passphrase;
    }

    public EpayDTipologiaPagamento getTipologiaPagamento () {
        return tipologiaPagamento;
    }

    public void setTipologiaPagamento ( EpayDTipologiaPagamento tipologiaPagamento ) {
        this.tipologiaPagamento = tipologiaPagamento;
    }

    public String getNumeroAccertamento () {
        return numeroAccertamento;
    }

    public void setNumeroAccertamento ( String numeroAccertamento ) {
        this.numeroAccertamento = numeroAccertamento;
    }

    public Long getAnnoAccertamento () {
        return annoAccertamento;
    }

    public void setAnnoAccertamento ( Long annoAccertamento ) {
        this.annoAccertamento = annoAccertamento;
    }

    public String getChiaveIntersistema () {
        return chiaveIntersistema;
    }

    public void setChiaveIntersistema ( String chiaveIntersistema ) {
        this.chiaveIntersistema = chiaveIntersistema;
    }

    public Long getIdTipoPagamento () {
        return idTipoPagamento;
    }

    public void setIdTipoPagamento ( Long idTipoPagamento ) {
        this.idTipoPagamento = idTipoPagamento;
    }

    public String getCodiceVersamento () {
        return codiceVersamento;
    }

    public void setCodiceVersamento ( String codiceVersamento ) {
        this.codiceVersamento = codiceVersamento;
    }

    public String getCompilazioneNote () {
        return compilazioneNote;
    }

    public void setCompilazioneNote ( String compilazioneNote ) {
        this.compilazioneNote = compilazioneNote;
    }

    public Long getContatoreCompilazioni () {
        return contatoreCompilazioni;
    }

    public void setContatoreCompilazioni ( Long contatoreCompilazioni ) {
        this.contatoreCompilazioni = contatoreCompilazioni;
    }

    public Long getContatorePagamenti () {
        return contatorePagamenti;
    }

    public void setContatorePagamenti ( Long contatorePagamenti ) {
        this.contatorePagamenti = contatorePagamenti;
    }

    public Long getContatoreSelezioni () {
        return contatoreSelezioni;
    }

    public void setContatoreSelezioni ( Long contatoreSelezioni ) {
        this.contatoreSelezioni = contatoreSelezioni;
    }

    public String getDatiSpecificiRiscossione () {
        return datiSpecificiRiscossione;
    }

    public void setDatiSpecificiRiscossione ( String datiSpecificiRiscossione ) {
        this.datiSpecificiRiscossione = datiSpecificiRiscossione;
    }

    public String getDescrizionePortale () {
        return descrizionePortale;
    }

    public void setDescrizionePortale ( String descrizionePortale ) {
        this.descrizionePortale = descrizionePortale;
    }

    public Date getFineValidita () {
        return fineValidita;
    }

    public void setFineValidita ( Date fineValidita ) {
        this.fineValidita = fineValidita;
    }

    public Boolean getFlagInvioPagamenti () {
        return flagInvioPagamenti;
    }

    public void setFlagInvioPagamenti ( Boolean flagInvioPagamenti ) {
        this.flagInvioPagamenti = flagInvioPagamenti;
    }

    public String getIdApplicazione () {
        return idApplicazione;
    }

    public void setIdApplicazione ( String idApplicazione ) {
        this.idApplicazione = idApplicazione;
    }

    public Date getInizioValidita () {
        return inizioValidita;
    }

    public void setInizioValidita ( Date inizioValidita ) {
        this.inizioValidita = inizioValidita;
    }

    public Boolean getPagamentoSpontaneo () {
        return pagamentoSpontaneo;
    }

    public void setPagamentoSpontaneo ( Boolean pagamentoSpontaneo ) {
        this.pagamentoSpontaneo = pagamentoSpontaneo;
    }

    public Boolean getFlagInvioRT () {
        return flagInvioRT;
    }

    public void setFlagInvioRT ( Boolean flagInvioRT ) {
        this.flagInvioRT = flagInvioRT;
    }
    

	public Boolean getFlagMultibeneficiario() {
		return flagMultibeneficiario;
	}

	public void setFlagMultibeneficiario(Boolean flagMultibeneficiario) {
		this.flagMultibeneficiario = flagMultibeneficiario;
	}

	public List<EpayTPagamento> getEpayTPagamentos () {
        return epayTPagamentos;
    }

    public void setEpayTPagamentos ( List<EpayTPagamento> epayTPagamentos ) {
        this.epayTPagamentos = epayTPagamentos;
    }

    public EpayTPagamento addEpayTPagamento ( EpayTPagamento epayTPagamento ) {
        getEpayTPagamentos ().add ( epayTPagamento );
        epayTPagamento.setEpayTTipoPagamento ( this );

        return epayTPagamento;
    }

    public EpayTPagamento removeEpayTPagamento ( EpayTPagamento epayTPagamento ) {
        getEpayTPagamentos ().remove ( epayTPagamento );
        epayTPagamento.setEpayTTipoPagamento ( null );

        return epayTPagamento;
    }

    public EpayTEnti getEpayTEnti () {
        return epayTEnti;
    }

    public void setEpayTEnti ( EpayTEnti epayTEnti ) {
        this.epayTEnti = epayTEnti;
    }

    public Boolean getFlagPresenzaBollettinoPostale() {
        return flagPresenzaBollettinoPostale;
    }

    public void setFlagPresenzaBollettinoPostale(Boolean flagPresenzaBollettinoPostale) {
        this.flagPresenzaBollettinoPostale = flagPresenzaBollettinoPostale;
    }

    
    /**
     * @return the epayTPagamentoSecondarios
     */
    public List<EpayTPagamentoSecondario> getEpayTPagamentoSecondarios () {
        return epayTPagamentoSecondarios;
    }

    
    /**
     * @param epayTPagamentoSecondarios the epayTPagamentoSecondarios to set
     */
    public void setEpayTPagamentoSecondarios ( List<EpayTPagamentoSecondario> epayTPagamentoSecondarios ) {
        this.epayTPagamentoSecondarios = epayTPagamentoSecondarios;
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


    
    /**
     * @return the flagVisualizzaDaSportello
     */
    public Boolean getFlagVisualizzaDaSportello () {
        return flagVisualizzaDaSportello;
    }


    
    /**
     * @param flagVisualizzaDaSportello the flagVisualizzaDaSportello to set
     */
    public void setFlagVisualizzaDaSportello ( Boolean flagVisualizzaDaSportello ) {
        this.flagVisualizzaDaSportello = flagVisualizzaDaSportello;
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
    
    
    

}
