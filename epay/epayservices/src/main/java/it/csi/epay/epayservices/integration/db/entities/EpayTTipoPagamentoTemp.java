/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the epay_t_tipo_pagamento database table.
 *
 */
@Entity
@Table ( name = "epay_t_tipo_pagamento_temp" )
public class EpayTTipoPagamentoTemp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator ( name = "EPAY_T_TIPO_PAGAMENTO_TEMP_IDTIPOPAGAMENTOTEMP_GENERATOR", allocationSize = 1,
    sequenceName = "EPAY_T_TIPO_PAGAMENTO_TEMP_ID_TIPO_PAGAMENTO_TEMP_SEQ" )
    @GeneratedValue ( strategy = GenerationType.SEQUENCE, generator = "EPAY_T_TIPO_PAGAMENTO_TEMP_IDTIPOPAGAMENTOTEMP_GENERATOR" )
    @Column ( name = "id_tipo_pagamento_temp", unique = true, nullable = false )
    private Long idTipoPagamentoTemp;

    @Column ( name = "id_operazione", nullable = false, length = 100 )
    private String idOperazione;

    @Column ( name = "tipo_operazione", nullable = false, length = 100 )
    private String tipoOperazione;

    @Column ( name = "chiave_intersistema", nullable = false, length = 100 )
    private String chiaveIntersistema;

    @ManyToOne
    @JoinColumn ( name = "id_tipo_pagamento", nullable = true )
    private EpayTTipoPagamento tipoPagamento;

    @ManyToOne
    @JoinColumn ( name = "id_ente", nullable = true )
    private EpayTEnti ente;

    @Column ( name = "codice_versamento", nullable = false, length = 4 )
    private String codiceVersamento;

    @Column ( name = "compilazione_note", length = 2000 )
    private String compilazioneNote;

    @Column ( name = "contatore_compilazioni", nullable = true )
    private Long contatoreCompilazioni;

    @Column ( name = "contatore_pagamenti", nullable = true )
    private Long contatorePagamenti;

    @Column ( name = "contatore_selezioni", nullable = true )
    private Long contatoreSelezioni;

    @Column ( name = "dati_specifici_riscossione", length = 140 )
    private String datiSpecificiRiscossione;

    @Column ( name = "descrizione_portale", nullable = true, length = 140 )
    private String descrizionePortale;

    @Temporal ( TemporalType.DATE )
    @Column ( name = "fine_validita" )
    private Date fineValidita;

    @Column ( name = "flag_invio_pagamenti", nullable = true )
    private Boolean flagInvioPagamenti;

    @Column ( name = "id_applicazione", nullable = true, length = 30 )
    private String idApplicazione;

    @Temporal ( TemporalType.DATE )
    @Column ( name = "inizio_validita", nullable = true )
    private Date inizioValidita;

    @Column ( name = "pagamento_spontaneo", nullable = true )
    private Boolean pagamentoSpontaneo;

    @Column ( name = "numero_accertamento", nullable = true, length = 35 )
    private String numeroAccertamento;

    @Column ( name = "anno_accertamento", nullable = true )
    private Long annoAccertamento;

    @ManyToOne
    @JoinColumn ( name = "id_tipologia_pagamento", nullable = true )
    private EpayDTipologiaPagamento tipologiaPagamento;

    @Column ( name = "bollettino_postale" )
    private Boolean flagPresenzaBollettinoPostale;

    @Column ( name = "flag_invio_rt", nullable = false )
    private Boolean flagInvioRT;
    
    @Column ( name = "multibeneficiario", nullable = true )
    private Boolean multibeneficiario;
    
    @Column ( name = "id_tipo_pagamento_secondario", nullable = true )
    private Long idTipoPagamentoSecondario;
    
    @Column ( name = "multibeneficiario_secondario", nullable = true )
    private Boolean multibeneficiarioSecondario ;
    
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
    @Column ( name = "url_attualizzazione_Pnd", nullable = true )
    private String urlAttualizzazionePnd;
    
    @Column(name="credenziali_Pnd", nullable=true)
    private byte[] credenzialiPnd;
    
    
    
    

    public EpayTTipoPagamentoTemp () {
    }

    public EpayDTipologiaPagamento getTipologiaPagamento () {
        return tipologiaPagamento;
    }

    public void setTipologiaPagamento ( EpayDTipologiaPagamento tipologiaPagamento ) {
        this.tipologiaPagamento = tipologiaPagamento;
    }

    public String getTipoOperazione () {
        return tipoOperazione;
    }

    public void setTipoOperazione ( String tipoOperazione ) {
        this.tipoOperazione = tipoOperazione;
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

    public Long getIdTipoPagamentoTemp () {
        return idTipoPagamentoTemp;
    }

    public void setIdTipoPagamentoTemp ( Long idTipoPagamentoTemp ) {
        this.idTipoPagamentoTemp = idTipoPagamentoTemp;
    }

    public String getIdOperazione () {
        return idOperazione;
    }

    public void setIdOperazione ( String idOperazione ) {
        this.idOperazione = idOperazione;
    }

    public String getChiaveIntersistema () {
        return chiaveIntersistema;
    }

    public void setChiaveIntersistema ( String chiaveIntersistema ) {
        this.chiaveIntersistema = chiaveIntersistema;
    }

    public EpayTTipoPagamento getTipoPagamento () {
        return tipoPagamento;
    }

    public void setTipoPagamento ( EpayTTipoPagamento tipoPagamento ) {
        this.tipoPagamento = tipoPagamento;
    }

    public EpayTEnti getEnte () {
        return ente;
    }

    public void setEnte ( EpayTEnti ente ) {
        this.ente = ente;
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


    public Boolean getFlagPresenzaBollettinoPostale () {
        return flagPresenzaBollettinoPostale;
    }


    public void setFlagPresenzaBollettinoPostale ( Boolean flagPresenzaBollettinoPostale ) {
        this.flagPresenzaBollettinoPostale = flagPresenzaBollettinoPostale;
    }


    public Boolean getFlagInvioRT () {
        return flagInvioRT;
    }

    public void setFlagInvioRT ( Boolean flagInvioRT ) {
        this.flagInvioRT = flagInvioRT;
    }

    
    public Boolean getMultibeneficiario () {
        return multibeneficiario;
    }

    
    public void setMultibeneficiario ( Boolean multibeneficiario ) {
        this.multibeneficiario = multibeneficiario;
    }

    
    public Long getIdTipoPagamentoSecondario () {
        return idTipoPagamentoSecondario;
    }

    
    public void setIdTipoPagamentoSecondario ( Long idTipoPagamentoSecondario ) {
        this.idTipoPagamentoSecondario = idTipoPagamentoSecondario;
    }

    
    public Boolean getMultibeneficiarioSecondario () {
        return multibeneficiarioSecondario;
    }

    
    public void setMultibeneficiarioSecondario ( Boolean multibeneficiarioSecondario ) {
        this.multibeneficiarioSecondario = multibeneficiarioSecondario;
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
     * @return the urlAttualizzazionePnd
     */
    public Boolean getFlagVisualizzaDaSportello () {
        return flagVisualizzaDaSportello;
    }
    
        
    public String getUrlAttualizzazionePnd () {
        return urlAttualizzazionePnd;
    }

    
    /**
     * @param flagVisualizzaDaSportello the flagVisualizzaDaSportello to set
     * @param urlAttualizzazionePnd the urlAttualizzazionePnd to set
     */
    public void setFlagVisualizzaDaSportello ( Boolean flagVisualizzaDaSportello ) {
        this.flagVisualizzaDaSportello = flagVisualizzaDaSportello;
    }
    
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
