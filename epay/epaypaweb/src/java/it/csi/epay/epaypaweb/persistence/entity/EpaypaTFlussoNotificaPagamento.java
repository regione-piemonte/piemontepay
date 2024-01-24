/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.*;


@Entity ( name = "EpaypaTFlussoNotificaPagamento" )
public class EpaypaTFlussoNotificaPagamento implements Serializable {

    private static final long serialVersionUID = 1L;

    //------------------------------------
    //CAMPI TABELLA FLUSSO
    //------------------------------------
    
    @Id
    @Column(name="id_flusso")
    private Long idFlusso;

    //uni-directional many-to-one association to EpaypaDStatoFlusso
    @ManyToOne
    @JoinColumn(name="id_tipo_flusso")
    private EpaypaDTipoFlusso epaypaDTipoFlusso;

    //uni-directional many-to-one association to EpaypaDStatoFlusso
    @ManyToOne
    @JoinColumn(name="id_stato_flusso")
    private EpaypaDStatoFlusso epaypaDStatoFlusso;

    @Column(name="id_messaggio")
    private String idMessaggio;

    //uni-directional many-to-one association to EpaypaTCodiceVersamento
    @ManyToOne
    @JoinColumn(name="id_ente")
    private EpaypaTEnte epaypaTEnte;

    //uni-directional many-to-one association to EpaypaTCodiceVersamento
    @ManyToOne
    @JoinColumn(name="id_codice_versamento")
    private EpaypaTCodiceVersamento epaypaTCodiceVersamento;

    @Column(name="numero_elementi")
    private Integer numeroElementi;

    @Column(name="importo_totale")
    private BigDecimal importoTotale;

    @Column(name="pagamenti_spontanei")
    private Boolean pagamentiSpontanei;

    @Column(name="dt_inserimento")
    private Timestamp dtInserimento;

    @Column(name="dt_ultima_variazione")
    private Timestamp dtUltimaVariazione;

    @Column(name="utente_ultima_variazione")
    private String utenteUltimaVariazione;

    @Column(name="cod_esito")
    private String codEsito;

    @Column(name="dettaglio_esito")
    private String dettaglioEsito;

    //uni-directional many-to-one association to EpaypaTRendicontazione
    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_flusso", updatable=false, insertable=false, nullable=true)
    private EpaypaTRendicontazioneLight epaypaTRendicontazione;
    
    //------------------------------------
    //CAMPI TABELLA NOTIFICHE PAGAMENTO
    //------------------------------------

    private Long idNotificaPagamento;

    private Integer annoRiferimento;

    private String codiceAvviso;

    private String datiSpecificiRiscossione;

    private String desCausaleVersamento;

    private Timestamp dtEsitoPagamento;

    private Timestamp dtScadenza;

    private String idPosizioneDebitoria;

    private BigDecimal importoPagato;

    private String iuv;

    private String note;

    //bi-directional many-to-one association to EpaypaTFlusso
    @ManyToOne
    @JoinColumn ( name = "id_flusso", updatable = false, insertable = false, nullable = false )
    private EpaypaTFlusso epaypaTFlusso;

    //bi-directional one-to-one association to EpaypaTTransazionePsp
    @OneToOne ( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @PrimaryKeyJoinColumn
    @JoinColumn ( name = "id_notifica_pagamento", updatable = false, insertable = false, nullable = false )
    private EpaypaTTransazionePsp epaypaTTransazionePsp;

    //uni-directional many-to-one association to EpaypaTSoggetto
    @ManyToOne ( cascade = CascadeType.ALL )
    @JoinColumn ( name = "id_soggetto_debitore" )
    private EpaypaTSoggetto epaypaTSoggettoDebitore;

    //uni-directional many-to-one association to EpaypaTSoggetto
    @ManyToOne ( cascade = CascadeType.ALL )
    @JoinColumn ( name = "id_soggetto_versante" )
    private EpaypaTSoggetto epaypaTSoggettoVersante;

    //------------------------------------
    //CAMPI TABELLA SOGGETTO
    //------------------------------------
    
    @Column(name="id_soggetto")
    private Long idSoggetto;
    
    private String cap;
    
    private String civico;
    
    @Column(name="cognome_rag_soc")
    private String cognomeRagSoc;
    
    private String email;
    
    @Column(name="id_univoco_fiscale")
    private String idUnivocoFiscale;
    
    private String indirizzo;
    
    private String localita;
    
    private String nazione;
    
    private String nome;
    
    private String provincia;
    
    @Column(name="tipologia_soggetto")
    private String tipologiaSoggetto;

    //------------------------------------
    
    public Long getIdFlusso () {
        return idFlusso;
    }

    
    public void setIdFlusso ( Long idFlusso ) {
        this.idFlusso = idFlusso;
    }

    
    public EpaypaDTipoFlusso getEpaypaDTipoFlusso () {
        return epaypaDTipoFlusso;
    }

    
    public void setEpaypaDTipoFlusso ( EpaypaDTipoFlusso epaypaDTipoFlusso ) {
        this.epaypaDTipoFlusso = epaypaDTipoFlusso;
    }

    
    public EpaypaDStatoFlusso getEpaypaDStatoFlusso () {
        return epaypaDStatoFlusso;
    }

    
    public void setEpaypaDStatoFlusso ( EpaypaDStatoFlusso epaypaDStatoFlusso ) {
        this.epaypaDStatoFlusso = epaypaDStatoFlusso;
    }

    
    public String getIdMessaggio () {
        return idMessaggio;
    }

    
    public void setIdMessaggio ( String idMessaggio ) {
        this.idMessaggio = idMessaggio;
    }

    
    public EpaypaTEnte getEpaypaTEnte () {
        return epaypaTEnte;
    }

    
    public void setEpaypaTEnte ( EpaypaTEnte epaypaTEnte ) {
        this.epaypaTEnte = epaypaTEnte;
    }

    
    public EpaypaTCodiceVersamento getEpaypaTCodiceVersamento () {
        return epaypaTCodiceVersamento;
    }

    
    public void setEpaypaTCodiceVersamento ( EpaypaTCodiceVersamento epaypaTCodiceVersamento ) {
        this.epaypaTCodiceVersamento = epaypaTCodiceVersamento;
    }

    
    public Integer getNumeroElementi () {
        return numeroElementi;
    }

    
    public void setNumeroElementi ( Integer numeroElementi ) {
        this.numeroElementi = numeroElementi;
    }

    
    public BigDecimal getImportoTotale () {
        return importoTotale;
    }

    
    public void setImportoTotale ( BigDecimal importoTotale ) {
        this.importoTotale = importoTotale;
    }

    
    public Boolean getPagamentiSpontanei () {
        return pagamentiSpontanei;
    }

    
    public void setPagamentiSpontanei ( Boolean pagamentiSpontanei ) {
        this.pagamentiSpontanei = pagamentiSpontanei;
    }

    
    public Timestamp getDtInserimento () {
        return dtInserimento;
    }

    
    public void setDtInserimento ( Timestamp dtInserimento ) {
        this.dtInserimento = dtInserimento;
    }

    
    public Timestamp getDtUltimaVariazione () {
        return dtUltimaVariazione;
    }

    
    public void setDtUltimaVariazione ( Timestamp dtUltimaVariazione ) {
        this.dtUltimaVariazione = dtUltimaVariazione;
    }

    
    public String getUtenteUltimaVariazione () {
        return utenteUltimaVariazione;
    }

    
    public void setUtenteUltimaVariazione ( String utenteUltimaVariazione ) {
        this.utenteUltimaVariazione = utenteUltimaVariazione;
    }

    
    public String getCodEsito () {
        return codEsito;
    }

    
    public void setCodEsito ( String codEsito ) {
        this.codEsito = codEsito;
    }

    
    public String getDettaglioEsito () {
        return dettaglioEsito;
    }

    
    public void setDettaglioEsito ( String dettaglioEsito ) {
        this.dettaglioEsito = dettaglioEsito;
    }

    
    public EpaypaTRendicontazioneLight getEpaypaTRendicontazione () {
        return epaypaTRendicontazione;
    }

    
    public void setEpaypaTRendicontazione ( EpaypaTRendicontazioneLight epaypaTRendicontazione ) {
        this.epaypaTRendicontazione = epaypaTRendicontazione;
    }

    
    public Long getIdNotificaPagamento () {
        return idNotificaPagamento;
    }

    
    public void setIdNotificaPagamento ( Long idNotificaPagamento ) {
        this.idNotificaPagamento = idNotificaPagamento;
    }

    
    public Integer getAnnoRiferimento () {
        return annoRiferimento;
    }

    
    public void setAnnoRiferimento ( Integer annoRiferimento ) {
        this.annoRiferimento = annoRiferimento;
    }

    
    public String getCodiceAvviso () {
        return codiceAvviso;
    }

    
    public void setCodiceAvviso ( String codiceAvviso ) {
        this.codiceAvviso = codiceAvviso;
    }

    
    public String getDatiSpecificiRiscossione () {
        return datiSpecificiRiscossione;
    }

    
    public void setDatiSpecificiRiscossione ( String datiSpecificiRiscossione ) {
        this.datiSpecificiRiscossione = datiSpecificiRiscossione;
    }

    
    public String getDesCausaleVersamento () {
        return desCausaleVersamento;
    }

    
    public void setDesCausaleVersamento ( String desCausaleVersamento ) {
        this.desCausaleVersamento = desCausaleVersamento;
    }

    
    public Timestamp getDtEsitoPagamento () {
        return dtEsitoPagamento;
    }

    
    public void setDtEsitoPagamento ( Timestamp dtEsitoPagamento ) {
        this.dtEsitoPagamento = dtEsitoPagamento;
    }

    
    public Timestamp getDtScadenza () {
        return dtScadenza;
    }

    
    public void setDtScadenza ( Timestamp dtScadenza ) {
        this.dtScadenza = dtScadenza;
    }

    
    public String getIdPosizioneDebitoria () {
        return idPosizioneDebitoria;
    }

    
    public void setIdPosizioneDebitoria ( String idPosizioneDebitoria ) {
        this.idPosizioneDebitoria = idPosizioneDebitoria;
    }

    
    public BigDecimal getImportoPagato () {
        return importoPagato;
    }

    
    public void setImportoPagato ( BigDecimal importoPagato ) {
        this.importoPagato = importoPagato;
    }

    
    public String getIuv () {
        return iuv;
    }

    
    public void setIuv ( String iuv ) {
        this.iuv = iuv;
    }

    
    public String getNote () {
        return note;
    }

    
    public void setNote ( String note ) {
        this.note = note;
    }

    
    public EpaypaTFlusso getEpaypaTFlusso () {
        return epaypaTFlusso;
    }

    
    public void setEpaypaTFlusso ( EpaypaTFlusso epaypaTFlusso ) {
        this.epaypaTFlusso = epaypaTFlusso;
    }

    
    public EpaypaTTransazionePsp getEpaypaTTransazionePsp () {
        return epaypaTTransazionePsp;
    }

    
    public void setEpaypaTTransazionePsp ( EpaypaTTransazionePsp epaypaTTransazionePsp ) {
        this.epaypaTTransazionePsp = epaypaTTransazionePsp;
    }

    
    public EpaypaTSoggetto getEpaypaTSoggettoDebitore () {
        return epaypaTSoggettoDebitore;
    }

    
    public void setEpaypaTSoggettoDebitore ( EpaypaTSoggetto epaypaTSoggettoDebitore ) {
        this.epaypaTSoggettoDebitore = epaypaTSoggettoDebitore;
    }

    
    public EpaypaTSoggetto getEpaypaTSoggettoVersante () {
        return epaypaTSoggettoVersante;
    }

    
    public void setEpaypaTSoggettoVersante ( EpaypaTSoggetto epaypaTSoggettoVersante ) {
        this.epaypaTSoggettoVersante = epaypaTSoggettoVersante;
    }

    
    public Long getIdSoggetto () {
        return idSoggetto;
    }

    
    public void setIdSoggetto ( Long idSoggetto ) {
        this.idSoggetto = idSoggetto;
    }

    
    public String getCap () {
        return cap;
    }

    
    public void setCap ( String cap ) {
        this.cap = cap;
    }

    
    public String getCivico () {
        return civico;
    }

    
    public void setCivico ( String civico ) {
        this.civico = civico;
    }

    
    public String getCognomeRagSoc () {
        return cognomeRagSoc;
    }

    
    public void setCognomeRagSoc ( String cognomeRagSoc ) {
        this.cognomeRagSoc = cognomeRagSoc;
    }

    
    public String getEmail () {
        return email;
    }

    
    public void setEmail ( String email ) {
        this.email = email;
    }

    
    public String getIdUnivocoFiscale () {
        return idUnivocoFiscale;
    }

    
    public void setIdUnivocoFiscale ( String idUnivocoFiscale ) {
        this.idUnivocoFiscale = idUnivocoFiscale;
    }

    
    public String getIndirizzo () {
        return indirizzo;
    }

    
    public void setIndirizzo ( String indirizzo ) {
        this.indirizzo = indirizzo;
    }

    
    public String getLocalita () {
        return localita;
    }

    
    public void setLocalita ( String localita ) {
        this.localita = localita;
    }

    
    public String getNazione () {
        return nazione;
    }

    
    public void setNazione ( String nazione ) {
        this.nazione = nazione;
    }

    
    public String getNome () {
        return nome;
    }

    
    public void setNome ( String nome ) {
        this.nome = nome;
    }

    
    public String getProvincia () {
        return provincia;
    }

    
    public void setProvincia ( String provincia ) {
        this.provincia = provincia;
    }

    
    public String getTipologiaSoggetto () {
        return tipologiaSoggetto;
    }

    
    public void setTipologiaSoggetto ( String tipologiaSoggetto ) {
        this.tipologiaSoggetto = tipologiaSoggetto;
    }
    
}
