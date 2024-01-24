/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the epaycat_d_mappatura_riferimento_contabile_epaypa database table.
 * 
 */
@Entity
@Table ( name = "epaycat_d_mappatura_riferimento_contabile_epaypa" )
@NamedQuery ( name = "MappaturaRiferimentoContabileEpaypa.findAll", query = "SELECT m FROM MappaturaRiferimentoContabileEpaypa m" )
public class MappaturaRiferimentoContabileEpaypa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    @Column ( name = "anno_accertamento" )
    private Integer annoAccertamento;

    @Column ( name = "anno_esercizio" )
    private Integer annoEsercizio;

    private String categoria;

    @Column ( name = "codice_tipologia_dato_specifico_riscossione" )
    private String codiceTipologiaDatoSpecificoRiscossione;

    @Column ( name = "data_inizio_validita" )
    private Timestamp dataInizioValidita;

    @Column ( name = "data_fine_validita" )
    private Timestamp dataFineValidita;

    @Column ( name = "dato_specifico_riscossione" )
    private String datoSpecificoRiscossione;

    private String descrizione;

    @Column ( name = "descrizione_dato_specifico_riscossione" )
    private String descrizioneDatoSpecificoRiscossione;

    @Column ( name = "id_codice_versamento" )
    private Integer idCodiceVersamento;

    @Column ( name = "livello_pdc" )
    private String livelloPdc;

    @Column ( name = "numero_accertamento" )
    private Integer numeroAccertamento;

    @Column ( name = "numero_articolo" )
    private Integer numeroArticolo;

    @Column ( name = "capitolo" )
    private String numeroCapitolo;

    private String tipologia;

    private String titolo;

    public MappaturaRiferimentoContabileEpaypa () {
    }

    public Integer getId () {
        return this.id;
    }

    public void setId ( Integer id ) {
        this.id = id;
    }

    public Integer getAnnoAccertamento () {
        return this.annoAccertamento;
    }

    public void setAnnoAccertamento ( Integer annoAccertamento ) {
        this.annoAccertamento = annoAccertamento;
    }

    public Integer getAnnoEsercizio () {
        return this.annoEsercizio;
    }

    public void setAnnoEsercizio ( Integer annoEsercizio ) {
        this.annoEsercizio = annoEsercizio;
    }

    public String getCategoria () {
        return this.categoria;
    }

    public void setCategoria ( String categoria ) {
        this.categoria = categoria;
    }

    public String getCodiceTipologiaDatoSpecificoRiscossione () {
        return this.codiceTipologiaDatoSpecificoRiscossione;
    }

    public void setCodiceTipologiaDatoSpecificoRiscossione ( String codiceTipologiaDatoSpecificoRiscossione ) {
        this.codiceTipologiaDatoSpecificoRiscossione = codiceTipologiaDatoSpecificoRiscossione;
    }

    public Timestamp getDataInizioValidita () {
        return this.dataInizioValidita;
    }

    public void setDataInizioValidita ( Timestamp dataInizioValidita ) {
        this.dataInizioValidita = dataInizioValidita;
    }

    public Timestamp getDataFineValidita () {
        return dataFineValidita;
    }

    public void setDataFineValidita ( Timestamp dataFineValidita ) {
        this.dataFineValidita = dataFineValidita;
    }

    public String getDatoSpecificoRiscossione () {
        return this.datoSpecificoRiscossione;
    }

    public void setDatoSpecificoRiscossione ( String datoSpecificoRiscossione ) {
        this.datoSpecificoRiscossione = datoSpecificoRiscossione;
    }

    public String getDescrizione () {
        return this.descrizione;
    }

    public void setDescrizione ( String descrizione ) {
        this.descrizione = descrizione;
    }

    public String getDescrizioneDatoSpecificoRiscossione () {
        return this.descrizioneDatoSpecificoRiscossione;
    }

    public void setDescrizioneDatoSpecificoRiscossione ( String descrizioneDatoSpecificoRiscossione ) {
        this.descrizioneDatoSpecificoRiscossione = descrizioneDatoSpecificoRiscossione;
    }

    public Integer getIdCodiceVersamento () {
        return this.idCodiceVersamento;
    }

    public void setIdCodiceVersamento ( Integer idCodiceVersamento ) {
        this.idCodiceVersamento = idCodiceVersamento;
    }

    public String getLivelloPdc () {
        return this.livelloPdc;
    }

    public void setLivelloPdc ( String livelloPdc ) {
        this.livelloPdc = livelloPdc;
    }

    public Integer getNumeroAccertamento () {
        return this.numeroAccertamento;
    }

    public void setNumeroAccertamento ( Integer numeroAccertamento ) {
        this.numeroAccertamento = numeroAccertamento;
    }

    public Integer getNumeroArticolo () {
        return this.numeroArticolo;
    }

    public void setNumeroArticolo ( Integer numeroArticolo ) {
        this.numeroArticolo = numeroArticolo;
    }

    public String getNumeroCapitolo () {
        return this.numeroCapitolo;
    }

    public void setNumeroCapitolo ( String numeroCapitolo ) {
        this.numeroCapitolo = numeroCapitolo;
    }

    public String getTipologia () {
        return this.tipologia;
    }

    public void setTipologia ( String tipologia ) {
        this.tipologia = tipologia;
    }

    public String getTitolo () {
        return this.titolo;
    }

    public void setTitolo ( String titolo ) {
        this.titolo = titolo;
    }

	@Override
	public String toString() {
		return "MappaturaRiferimentoContabileEpaypa [id=" + id + ", annoAccertamento=" + annoAccertamento
				+ ", annoEsercizio=" + annoEsercizio + ", descrizione=" + descrizione + "]";
	}

}
