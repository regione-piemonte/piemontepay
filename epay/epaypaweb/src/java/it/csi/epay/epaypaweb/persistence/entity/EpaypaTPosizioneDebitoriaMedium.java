/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * The persistent class for the epaypa_t_posizione_debitoria database table.
 *
 */
@Entity
@Table ( name = "epaypa_t_posizione_debitoria" )
public class EpaypaTPosizioneDebitoriaMedium implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column ( name = "id_posizione_debitoria" )
    private Long idPosizioneDebitoria;

    @Column ( name = "anno_riferimento" )
    private Integer annoRiferimento;

    @Column ( name = "cod_esito" )
    private String codEsito;

    @Column ( name = "codice_avviso" )
    private String codiceAvviso;

    @Column ( name = "des_causale_versamento" )
    private String desCausaleVersamento;

    @Column ( name = "des_rata" )
    private String desRata;

    @Column ( name = "dettaglio_esito" )
    private String dettaglioEsito;

    @Column ( name = "dt_fine_validita" )
    private Timestamp dtFineValidita;

    @Column ( name = "dt_inizio_validita" )
    private Timestamp dtInizioValidita;

    @Column ( name = "dt_scadenza" )
    private Timestamp dtScadenza;

    @Column ( name = "id_posizione_debitoria_est" )
    private String idPosizioneDebitoriaEst;

    @Column ( name = "importo_totale" )
    private BigDecimal importoTotale;

    @Column ( name = "iuv" )
    private String iuv;

    @Column ( name = "note_per_pagatore" )
    private String notePerPagatore;

    //uni-directional many-to-one association to EpaypaTSoggetto
    @ManyToOne
    @JoinColumn ( name = "id_soggetto_debitore" )
    private EpaypaTSoggetto epaypaTSoggettoDebitore;

    //bi-directional many-to-one association to EpaypaTFlusso
    @ManyToOne
    @JoinColumn ( name = "id_flusso", updatable = false, insertable = false, nullable = false )
    private EpaypaTFlusso epaypaTFlusso;

    @OneToOne ( fetch = FetchType.LAZY, optional = true )
    @JoinColumn ( name = "iuv", referencedColumnName = "iuv", insertable = false, updatable = false )
    private EpaypaTPagamenti pagamento;

    //uni-directional many-to-many association to EpaypaTComponenteImporto
    @ManyToMany ( fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    @JoinTable (
                    name = "epaypa_r_pos_deb_comp_imp", joinColumns = {
                        @JoinColumn ( name = "id_posizione_debitoria" )
                    },
                    inverseJoinColumns = {
                        @JoinColumn ( name = "id_componente" )
                    } )
    private Set<EpaypaTComponenteImporto> epaypaTComponenteImportos;
    
    //uni-directional many-to-many association to EpaypaTRiferimentoPagamento
    @ManyToMany ( fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    @JoinTable (
                    name = "epaypa_r_pos_deb_rif_pagamento", joinColumns = {
                        @JoinColumn ( name = "id_posizione_debitoria" )
                    },
                    inverseJoinColumns = {
                        @JoinColumn ( name = "id_riferimento" )
                    } )
    private Set<EpaypaTRiferimentoPagamento> epaypaTRiferimentoPagamentos;

    public EpaypaTPosizioneDebitoriaMedium () {
    }

    public Set<EpaypaTComponenteImporto> getEpaypaTComponenteImportos () {
        return epaypaTComponenteImportos;
    }

    public void setEpaypaTComponenteImportos ( Set<EpaypaTComponenteImporto> epaypaTComponenteImportos ) {
        this.epaypaTComponenteImportos = epaypaTComponenteImportos;
    }

    public EpaypaTPagamenti getPagamento () {
        return pagamento;
    }

    public void setPagamento ( EpaypaTPagamenti pagamento ) {
        this.pagamento = pagamento;
    }

    public EpaypaTFlusso getEpaypaTFlusso () {
        return epaypaTFlusso;
    }

    public void setEpaypaTFlusso ( EpaypaTFlusso epaypaTFlusso ) {
        this.epaypaTFlusso = epaypaTFlusso;
    }

    public Long getIdPosizioneDebitoria () {
        return idPosizioneDebitoria;
    }

    public void setIdPosizioneDebitoria ( Long idPosizioneDebitoria ) {
        this.idPosizioneDebitoria = idPosizioneDebitoria;
    }

    public String getIuv () {
        return iuv;
    }

    public void setIuv ( String iuv ) {
        this.iuv = iuv;
    }

    public BigDecimal getImportoTotale () {
        return importoTotale;
    }

    public void setImportoTotale ( BigDecimal importoTotale ) {
        this.importoTotale = importoTotale;
    }

    public String getDesCausaleVersamento () {
        return desCausaleVersamento;
    }

    public void setDesCausaleVersamento ( String desCausaleVersamento ) {
        this.desCausaleVersamento = desCausaleVersamento;
    }

    public Timestamp getDtScadenza () {
        return dtScadenza;
    }

    public void setDtScadenza ( Timestamp dtScadenza ) {
        this.dtScadenza = dtScadenza;
    }

    public EpaypaTSoggetto getEpaypaTSoggettoDebitore () {
        return epaypaTSoggettoDebitore;
    }

    public void setEpaypaTSoggettoDebitore ( EpaypaTSoggetto epaypaTSoggettoDebitore ) {
        this.epaypaTSoggettoDebitore = epaypaTSoggettoDebitore;
    }

    public Integer getAnnoRiferimento () {
        return annoRiferimento;
    }

    public void setAnnoRiferimento ( Integer annoRiferimento ) {
        this.annoRiferimento = annoRiferimento;
    }

    public String getCodEsito () {
        return codEsito;
    }

    public void setCodEsito ( String codEsito ) {
        this.codEsito = codEsito;
    }

    public String getCodiceAvviso () {
        return codiceAvviso;
    }

    public void setCodiceAvviso ( String codiceAvviso ) {
        this.codiceAvviso = codiceAvviso;
    }

    public String getDesRata () {
        return desRata;
    }

    public void setDesRata ( String desRata ) {
        this.desRata = desRata;
    }

    public String getDettaglioEsito () {
        return dettaglioEsito;
    }

    public void setDettaglioEsito ( String dettaglioEsito ) {
        this.dettaglioEsito = dettaglioEsito;
    }

    public Timestamp getDtFineValidita () {
        return dtFineValidita;
    }

    public void setDtFineValidita ( Timestamp dtFineValidita ) {
        this.dtFineValidita = dtFineValidita;
    }

    public Timestamp getDtInizioValidita () {
        return dtInizioValidita;
    }

    public void setDtInizioValidita ( Timestamp dtInizioValidita ) {
        this.dtInizioValidita = dtInizioValidita;
    }

    public String getIdPosizioneDebitoriaEst () {
        return idPosizioneDebitoriaEst;
    }

    public void setIdPosizioneDebitoriaEst ( String idPosizioneDebitoriaEst ) {
        this.idPosizioneDebitoriaEst = idPosizioneDebitoriaEst;
    }

    public String getNotePerPagatore () {
        return notePerPagatore;
    }

    public void setNotePerPagatore ( String notePerPagatore ) {
        this.notePerPagatore = notePerPagatore;
    }
    
    public Set<EpaypaTRiferimentoPagamento> getEpaypaTRiferimentoPagamentos () {
        return this.epaypaTRiferimentoPagamentos;
    }

    public void setEpaypaTRiferimentoPagamentos ( Set<EpaypaTRiferimentoPagamento> epaypaTRiferimentoPagamentos ) {
        this.epaypaTRiferimentoPagamentos = epaypaTRiferimentoPagamentos;
    }

}
