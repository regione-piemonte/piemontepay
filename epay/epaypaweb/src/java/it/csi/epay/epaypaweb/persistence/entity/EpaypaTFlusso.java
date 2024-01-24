/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


/**
 * The persistent class for the epaypa_t_flusso database table.
 *
 */
@Entity
@Table(name="epaypa_t_flusso")
@NamedQuery(name="EpaypaTFlusso.findAll", query="SELECT e FROM EpaypaTFlusso e")
public class EpaypaTFlusso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    @Column ( name = "id_flusso" )
    private Long idFlusso;

    @Column ( name = "cod_esito" )
    private String codEsito;

    @Column ( name = "dettaglio_esito" )
    private String dettaglioEsito;

    @Column ( name = "dt_inoltro" )
    private Timestamp dtInoltro;

    @Column ( name = "dt_inserimento" )
    private Timestamp dtInserimento;

    @Column ( name = "dt_ultima_variazione" )
    private Timestamp dtUltimaVariazione;

    @Column ( name = "id_messaggio" )
    private String idMessaggio;

    @Column ( name = "importo_totale" )
    private BigDecimal importoTotale;

    @Column ( name = "numero_elementi" )
    private Integer numeroElementi;

    @Column ( name = "pagamenti_spontanei" )
    private Boolean pagamentiSpontanei;

    @Column ( name = "utente_inserimento" )
    private String utenteInserimento;

    @Column ( name = "utente_ultima_variazione" )
    private String utenteUltimaVariazione;

    //uni-directional many-to-one association to EpaypaDStatoFlusso
    @ManyToOne
    @JoinColumn ( name = "id_stato_flusso" )
    private EpaypaDStatoFlusso epaypaDStatoFlusso;

    //uni-directional many-to-one association to EpaypaDTipoFlusso
    @ManyToOne
    @JoinColumn ( name = "id_tipo_flusso" )
    private EpaypaDTipoFlusso epaypaDTipoFlusso;

    //uni-directional many-to-one association to EpaypaTCodiceVersamento
    @ManyToOne
    @JoinColumn ( name = "id_codice_versamento" )
    private EpaypaTCodiceVersamento epaypaTCodiceVersamento;

    //uni-directional many-to-one association to EpaypaTEnte
    @ManyToOne
    @JoinColumn ( name = "id_ente" )
    private EpaypaTEnte epaypaTEnte;

    //bi-directional many-to-one association to EpaypaTNotificaPagamento
    @OneToMany ( cascade = CascadeType.ALL )
    @JoinColumn ( name = "id_flusso", updatable = false, insertable = false, nullable = false )
    private List<EpaypaTNotificaPagamento> epaypaTNotificaPagamentos;

    //bi-directional many-to-one association to EpaypaTPosizioneDebitoria
    @OneToMany ( cascade = CascadeType.ALL )
    @JoinColumn ( name = "id_flusso", updatable = false, insertable = false, nullable = false )
    private List<EpaypaTPosizioneDebitoria> epaypaTPosizioneDebitorias;

    //bi-directional many-to-one association to EpaypaTAggPosizioneDebitoria
    @OneToMany ( cascade = CascadeType.ALL )
    @JoinColumn ( name = "id_flusso", updatable = false, insertable = false, nullable = false )
    private List<EpaypaTAggPosizioneDebitoria> epaypaTAggPosizioneDebitorias;

    //bi-directional many-to-one association to EpaypaTAvvisoScaduto
    @OneToMany ( cascade = CascadeType.ALL )
    @JoinColumn ( name = "id_flusso", updatable = false, insertable = false, nullable = false )
    private List<EpaypaTAvvisoScaduto> epaypaTAvvisoScadutos;

    //bi-directional one-to-one association to EpaypaTTransazionePsp
    @OneToOne ( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @PrimaryKeyJoinColumn
    @JoinColumn ( name = "id_flusso", updatable = false, insertable = false, nullable = false )
    private EpaypaTRendicontazione epaypaTRendicontazione;

    public EpaypaTFlusso () {
		//empty constructor
    }

    public Long getIdFlusso () {
        return this.idFlusso;
    }

    public void setIdFlusso ( Long idFlusso ) {
        this.idFlusso = idFlusso;
    }

    public String getCodEsito () {
        return this.codEsito;
    }

    public void setCodEsito ( String codEsito ) {
        this.codEsito = codEsito;
    }

    public String getDettaglioEsito () {
        return this.dettaglioEsito;
    }

    public void setDettaglioEsito ( String dettaglioEsito ) {
        this.dettaglioEsito = dettaglioEsito;
    }

    public Timestamp getDtInoltro () {
        return this.dtInoltro;
    }

    public void setDtInoltro ( Timestamp dtInoltro ) {
        this.dtInoltro = dtInoltro;
    }

    public Timestamp getDtInserimento () {
        return this.dtInserimento;
    }

    public void setDtInserimento ( Timestamp dtInserimento ) {
        this.dtInserimento = dtInserimento;
    }

    public Timestamp getDtUltimaVariazione () {
        return this.dtUltimaVariazione;
    }

    public void setDtUltimaVariazione ( Timestamp dtUltimaVariazione ) {
        this.dtUltimaVariazione = dtUltimaVariazione;
    }

    public String getIdMessaggio () {
        return this.idMessaggio;
    }

    public void setIdMessaggio ( String idMessaggio ) {
        this.idMessaggio = idMessaggio;
    }

    public BigDecimal getImportoTotale () {
        return this.importoTotale;
    }

    public void setImportoTotale ( BigDecimal importoTotale ) {
        this.importoTotale = importoTotale;
    }

    public Integer getNumeroElementi () {
        return this.numeroElementi;
    }

    public void setNumeroElementi ( Integer numeroElementi ) {
        this.numeroElementi = numeroElementi;
    }

    public Boolean getPagamentiSpontanei () {
        return this.pagamentiSpontanei;
    }

    public void setPagamentiSpontanei ( Boolean pagamentiSpontanei ) {
        this.pagamentiSpontanei = pagamentiSpontanei;
    }

    public String getUtenteInserimento () {
        return this.utenteInserimento;
    }

    public void setUtenteInserimento ( String utenteInserimento ) {
        this.utenteInserimento = utenteInserimento;
    }

    public String getUtenteUltimaVariazione () {
        return this.utenteUltimaVariazione;
    }

    public void setUtenteUltimaVariazione ( String utenteUltimaVariazione ) {
        this.utenteUltimaVariazione = utenteUltimaVariazione;
    }

    public EpaypaDStatoFlusso getEpaypaDStatoFlusso () {
        return this.epaypaDStatoFlusso;
    }

    public void setEpaypaDStatoFlusso ( EpaypaDStatoFlusso epaypaDStatoFlusso ) {
        this.epaypaDStatoFlusso = epaypaDStatoFlusso;
    }

    public EpaypaDTipoFlusso getEpaypaDTipoFlusso () {
        return this.epaypaDTipoFlusso;
    }

    public void setEpaypaDTipoFlusso ( EpaypaDTipoFlusso epaypaDTipoFlusso ) {
        this.epaypaDTipoFlusso = epaypaDTipoFlusso;
    }

    public EpaypaTCodiceVersamento getEpaypaTCodiceVersamento () {
        return this.epaypaTCodiceVersamento;
    }

    public void setEpaypaTCodiceVersamento ( EpaypaTCodiceVersamento epaypaTCodiceVersamento ) {
        this.epaypaTCodiceVersamento = epaypaTCodiceVersamento;
    }

    public EpaypaTEnte getEpaypaTEnte () {
        return this.epaypaTEnte;
    }

    public void setEpaypaTEnte ( EpaypaTEnte epaypaTEnte ) {
        this.epaypaTEnte = epaypaTEnte;
    }

    public List<EpaypaTNotificaPagamento> getEpaypaTNotificaPagamentos () {
        return this.epaypaTNotificaPagamentos;
    }

    public void setEpaypaTNotificaPagamentos ( List<EpaypaTNotificaPagamento> epaypaTNotificaPagamentos ) {
        this.epaypaTNotificaPagamentos = epaypaTNotificaPagamentos;
    }

    public EpaypaTNotificaPagamento addEpaypaTNotificaPagamento ( EpaypaTNotificaPagamento epaypaTNotificaPagamento ) {
        getEpaypaTNotificaPagamentos ().add ( epaypaTNotificaPagamento );
        epaypaTNotificaPagamento.setEpaypaTFlusso ( this );

        return epaypaTNotificaPagamento;
    }

    public EpaypaTNotificaPagamento removeEpaypaTNotificaPagamento ( EpaypaTNotificaPagamento epaypaTNotificaPagamento ) {
        getEpaypaTNotificaPagamentos ().remove ( epaypaTNotificaPagamento );
        epaypaTNotificaPagamento.setEpaypaTFlusso ( null );

        return epaypaTNotificaPagamento;
    }

    public List<EpaypaTPosizioneDebitoria> getEpaypaTPosizioneDebitorias () {
        return this.epaypaTPosizioneDebitorias;
    }

    public void setEpaypaTPosizioneDebitorias ( List<EpaypaTPosizioneDebitoria> epaypaTPosizioneDebitorias ) {
        this.epaypaTPosizioneDebitorias = epaypaTPosizioneDebitorias;
    }

    public EpaypaTPosizioneDebitoria addEpaypaTPosizioneDebitoria ( EpaypaTPosizioneDebitoria epaypaTPosizioneDebitoria ) {
        getEpaypaTPosizioneDebitorias ().add ( epaypaTPosizioneDebitoria );
        epaypaTPosizioneDebitoria.setEpaypaTFlusso ( this );

        return epaypaTPosizioneDebitoria;
    }

    public EpaypaTPosizioneDebitoria removeEpaypaTPosizioneDebitoria ( EpaypaTPosizioneDebitoria epaypaTPosizioneDebitoria ) {
        getEpaypaTPosizioneDebitorias ().remove ( epaypaTPosizioneDebitoria );
        epaypaTPosizioneDebitoria.setEpaypaTFlusso ( null );

        return epaypaTPosizioneDebitoria;
    }

    public List<EpaypaTAggPosizioneDebitoria> getEpaypaTAggPosizioneDebitorias () {
        return this.epaypaTAggPosizioneDebitorias;
    }

    public void setEpaypaTAggPosizioneDebitorias ( List<EpaypaTAggPosizioneDebitoria> epaypaTAggPosizioneDebitorias ) {
        this.epaypaTAggPosizioneDebitorias = epaypaTAggPosizioneDebitorias;
    }

    public EpaypaTAggPosizioneDebitoria addEpaypaTAggPosizioneDebitoria ( EpaypaTAggPosizioneDebitoria epaypaTAggPosizioneDebitoria ) {
        getEpaypaTAggPosizioneDebitorias ().add ( epaypaTAggPosizioneDebitoria );
        epaypaTAggPosizioneDebitoria.setEpaypaTFlusso ( this );

        return epaypaTAggPosizioneDebitoria;
    }

    public EpaypaTAggPosizioneDebitoria removeEpaypaTAggPosizioneDebitoria ( EpaypaTAggPosizioneDebitoria epaypaTAggPosizioneDebitoria ) {
        getEpaypaTAggPosizioneDebitorias ().remove ( epaypaTAggPosizioneDebitoria );
        epaypaTAggPosizioneDebitoria.setEpaypaTFlusso ( null );

        return epaypaTAggPosizioneDebitoria;
    }

    public List<EpaypaTAvvisoScaduto> getEpaypaTAvvisoScadutos () {
        return this.epaypaTAvvisoScadutos;
    }

    public void setEpaypaTAvvisoScadutos ( List<EpaypaTAvvisoScaduto> epaypaTAvvisoScadutos ) {
        this.epaypaTAvvisoScadutos = epaypaTAvvisoScadutos;
    }

    public EpaypaTAvvisoScaduto addEpaypaTAvvisoScaduto ( EpaypaTAvvisoScaduto epaypaTAvvisoScaduto ) {
        getEpaypaTAvvisoScadutos ().add ( epaypaTAvvisoScaduto );
        epaypaTAvvisoScaduto.setEpaypaTFlusso ( this );

        return epaypaTAvvisoScaduto;
    }

    public EpaypaTAvvisoScaduto removeEpaypaTAvvisoScaduto ( EpaypaTAvvisoScaduto epaypaTAvvisoScaduto ) {
        getEpaypaTAvvisoScadutos ().remove ( epaypaTAvvisoScaduto );
        epaypaTAvvisoScaduto.setEpaypaTFlusso ( null );

        return epaypaTAvvisoScaduto;
    }

    public EpaypaTRendicontazione getEpaypaTRendicontazione () {
        return this.epaypaTRendicontazione;
    }

    public void setEpaypaTRendicontazione ( EpaypaTRendicontazione epaypaTRendicontazione ) {
        this.epaypaTRendicontazione = epaypaTRendicontazione;
    }

}
