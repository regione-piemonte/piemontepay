/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile;

import it.csi.epay.epaypacatalogsrv.dto.ParentInput;

import java.util.Date;


public class AggiornaRiferimentoContabileInput extends ParentInput {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer annoAccertamento;

	private Date dataFineValidita;

    //    private String codiceTipologiaDatoSpecificoRiscossione;
    private String datoSpecificoRiscossione;
    private String descrizioneDatoSpecificoRiscossione;

    private String livelloPdc;

    private Integer numeroAccertamento;

    private Integer numeroArticolo;

    private String numeroCapitolo;

    private String titolo;

    private String categoria;

    private String tipologia;

	private String codiceTributo;

    @Override
    public String toString () {
        return "AggiornaRiferimentoContabileInput [id=" + id + ", annoAccertamento=" + annoAccertamento
            + ", dataFineValidita=" + dataFineValidita + ", livelloPdc=" + livelloPdc + ", numeroAccertamento=" + numeroAccertamento + ", numeroArticolo="
            + numeroArticolo + ", numeroCapitolo=" + numeroCapitolo + ", titolo=" + titolo + ", categoria=" + categoria
            + ", tipologia=" + tipologia + "]";
    }

    public Date getDataFineValidita () {
        return dataFineValidita;
    }

    public void setDataFineValidita ( Date dataFineValidita ) {
        this.dataFineValidita = dataFineValidita;
    }

    public String getCategoria () {
        return categoria;
    }

    public void setCategoria ( String categoria ) {
        this.categoria = categoria;
    }

    public String getTipologia () {
        return tipologia;
    }

    public void setTipologia ( String tipologia ) {
        this.tipologia = tipologia;
    }

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public Integer getAnnoAccertamento () {
        return annoAccertamento;
    }

    public void setAnnoAccertamento ( Integer annoAccertamento ) {
        this.annoAccertamento = annoAccertamento;
    }


    public String getDatoSpecificoRiscossione () {
        return datoSpecificoRiscossione;
    }

    public void setDatoSpecificoRiscossione ( String datoSpecificoRiscossione ) {
        this.datoSpecificoRiscossione = datoSpecificoRiscossione;
    }

    public String getDescrizioneDatoSpecificoRiscossione () {
        return descrizioneDatoSpecificoRiscossione;
    }

    public void setDescrizioneDatoSpecificoRiscossione ( String descrizioneDatoSpecificoRiscossione ) {
        this.descrizioneDatoSpecificoRiscossione = descrizioneDatoSpecificoRiscossione;
    }

    public String getLivelloPdc () {
        return livelloPdc;
    }

    public void setLivelloPdc ( String livelloPdc ) {
        this.livelloPdc = livelloPdc;
    }

    public Integer getNumeroAccertamento () {
        return numeroAccertamento;
    }

    public void setNumeroAccertamento ( Integer numeroAccertamento ) {
        this.numeroAccertamento = numeroAccertamento;
    }

    public Integer getNumeroArticolo () {
        return numeroArticolo;
    }

    public void setNumeroArticolo ( Integer numeroArticolo ) {
        this.numeroArticolo = numeroArticolo;
    }

    public String getNumeroCapitolo () {
        return numeroCapitolo;
    }

    public void setNumeroCapitolo ( String numeroCapitolo ) {
        this.numeroCapitolo = numeroCapitolo;
    }

    public String getTitolo () {
        return titolo;
    }

    public void setTitolo ( String titolo ) {
        this.titolo = titolo;
    }

	public String getCodiceTributo () {
		return codiceTributo;
	}

	public void setCodiceTributo ( String codiceTributo ) {
		this.codiceTributo = codiceTributo;
	}
}
