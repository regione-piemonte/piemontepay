/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings ( "unused" )
public class RicercaRiferimentoContabileGruppoCodiceVersamentoOutputDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String codice;

    private String descrizione;

    private String codiceTematica;

    private String descrizioneTematica;

    private String codiceMacrotipo;

    private String descrizioneMacrotipo;

    private String descrizioneTipoPagamento;

    private Long idVoceEntrata;

    private String codiceVoceEntrata;

    private String descrizioneVoceEntrata;

    private String codiceTipoPagamento;

    private String iban;

    private String bic;

    private Boolean flagInvioFlussi;

    private String email;

    private String codiceStatoAggiornamento;

    private String descrizioneStatoAggiornamento;

    private String descrizioneErroreAggiornamento;

    private List<RicercaRiferimentoContabileGruppoCodiceVersamentoOutputDto> codiciVersamentoCollegati;

    private List<RicercaRiferimentoContabileOutputDto> riferimentiContabili;

    private Integer numeroRiferimentiTotali;

    private Integer numeroRiferimentiInVita;

    private Integer numeroRiferimentiNonInVita;

	private String codiceTributo;

    public Integer getNumeroRiferimentiTotali () {
        return numeroRiferimentiTotali;
    }

    public void setNumeroRiferimentiTotali ( Integer numeroRiferimentiTotali ) {
        this.numeroRiferimentiTotali = numeroRiferimentiTotali;
    }

    public Integer getNumeroRiferimentiInVita () {
        return numeroRiferimentiInVita;
    }

    public void setNumeroRiferimentiInVita ( Integer numeroRiferimentiInVita ) {
        this.numeroRiferimentiInVita = numeroRiferimentiInVita;
    }

    public Integer getNumeroRiferimentiNonInVita () {
        return numeroRiferimentiNonInVita;
    }

    public void setNumeroRiferimentiNonInVita ( Integer numeroRiferimentiNonInVita ) {
        this.numeroRiferimentiNonInVita = numeroRiferimentiNonInVita;
    }

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
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

    public String getCodiceTematica () {
        return codiceTematica;
    }

    public void setCodiceTematica ( String codiceTematica ) {
        this.codiceTematica = codiceTematica;
    }

    public String getDescrizioneTematica () {
        return descrizioneTematica;
    }

    public void setDescrizioneTematica ( String descrizioneTematica ) {
        this.descrizioneTematica = descrizioneTematica;
    }

    public String getCodiceMacrotipo () {
        return codiceMacrotipo;
    }

    public void setCodiceMacrotipo ( String codiceMacrotipo ) {
        this.codiceMacrotipo = codiceMacrotipo;
    }

    public String getDescrizioneMacrotipo () {
        return descrizioneMacrotipo;
    }

    public void setDescrizioneMacrotipo ( String descrizioneMacrotipo ) {
        this.descrizioneMacrotipo = descrizioneMacrotipo;
    }

    public String getDescrizioneTipoPagamento () {
        return descrizioneTipoPagamento;
    }

    public void setDescrizioneTipoPagamento ( String descrizioneTipoPagamento ) {
        this.descrizioneTipoPagamento = descrizioneTipoPagamento;
    }

    public Long getIdVoceEntrata () {
        return idVoceEntrata;
    }

    public void setIdVoceEntrata ( Long idVoceEntrata ) {
        this.idVoceEntrata = idVoceEntrata;
    }

    public String getCodiceVoceEntrata () {
        return codiceVoceEntrata;
    }

    public void setCodiceVoceEntrata ( String codiceVoceEntrata ) {
        this.codiceVoceEntrata = codiceVoceEntrata;
    }

    public String getDescrizioneVoceEntrata () {
        return descrizioneVoceEntrata;
    }

    public void setDescrizioneVoceEntrata ( String descrizioneVoceEntrata ) {
        this.descrizioneVoceEntrata = descrizioneVoceEntrata;
    }

    public String getCodiceTipoPagamento () {
        return codiceTipoPagamento;
    }

    public void setCodiceTipoPagamento ( String codiceTipoPagamento ) {
        this.codiceTipoPagamento = codiceTipoPagamento;
    }

    public String getIban () {
        return iban;
    }

    public void setIban ( String iban ) {
        this.iban = iban;
    }

    public String getBic () {
        return bic;
    }

    public void setBic ( String bic ) {
        this.bic = bic;
    }

    public Boolean getFlagInvioFlussi () {
        return flagInvioFlussi;
    }

    public void setFlagInvioFlussi ( Boolean flagInvioFlussi ) {
        this.flagInvioFlussi = flagInvioFlussi;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail ( String email ) {
        this.email = email;
    }

    public String getCodiceStatoAggiornamento () {
        return codiceStatoAggiornamento;
    }

    public void setCodiceStatoAggiornamento ( String codiceStatoAggiornamento ) {
        this.codiceStatoAggiornamento = codiceStatoAggiornamento;
    }

    public String getDescrizioneStatoAggiornamento () {
        return descrizioneStatoAggiornamento;
    }

    public void setDescrizioneStatoAggiornamento ( String descrizioneStatoAggiornamento ) {
        this.descrizioneStatoAggiornamento = descrizioneStatoAggiornamento;
    }

    public String getDescrizioneErroreAggiornamento () {
        return descrizioneErroreAggiornamento;
    }

    public void setDescrizioneErroreAggiornamento ( String descrizioneErroreAggiornamento ) {
        this.descrizioneErroreAggiornamento = descrizioneErroreAggiornamento;
    }

    public List<RicercaRiferimentoContabileGruppoCodiceVersamentoOutputDto> getCodiciVersamentoCollegati () {
        return codiciVersamentoCollegati;
    }

    public void setCodiciVersamentoCollegati ( List<RicercaRiferimentoContabileGruppoCodiceVersamentoOutputDto> codiciVersamentoCollegati ) {
        this.codiciVersamentoCollegati = codiciVersamentoCollegati;
    }

    public List<RicercaRiferimentoContabileOutputDto> getRiferimentiContabili () {
        return riferimentiContabili;
    }

    public void setRiferimentiContabili ( List<RicercaRiferimentoContabileOutputDto> riferimentiContabili ) {
        this.riferimentiContabili = riferimentiContabili;
    }

	public String getCodiceTributo () {
		return codiceTributo;
	}

	public void setCodiceTributo ( String codiceTributo ) {
		this.codiceTributo = codiceTributo;
	}

	@Override public String toString () {
		return "RicercaRiferimentoContabileGruppoCodiceVersamentoOutputDto{" +
						"id=" + id +
						", codice='" + codice + '\'' +
						", descrizione='" + descrizione + '\'' +
						", codiceTematica='" + codiceTematica + '\'' +
						", descrizioneTematica='" + descrizioneTematica + '\'' +
						", codiceMacrotipo='" + codiceMacrotipo + '\'' +
						", descrizioneMacrotipo='" + descrizioneMacrotipo + '\'' +
						", descrizioneTipoPagamento='" + descrizioneTipoPagamento + '\'' +
						", idVoceEntrata=" + idVoceEntrata +
						", codiceVoceEntrata='" + codiceVoceEntrata + '\'' +
						", descrizioneVoceEntrata='" + descrizioneVoceEntrata + '\'' +
						", codiceTipoPagamento='" + codiceTipoPagamento + '\'' +
						", iban='" + iban + '\'' +
						", bic='" + bic + '\'' +
						", flagInvioFlussi=" + flagInvioFlussi +
						", email='" + email + '\'' +
						", codiceStatoAggiornamento='" + codiceStatoAggiornamento + '\'' +
						", descrizioneStatoAggiornamento='" + descrizioneStatoAggiornamento + '\'' +
						", descrizioneErroreAggiornamento='" + descrizioneErroreAggiornamento + '\'' +
						", codiciVersamentoCollegati=" + codiciVersamentoCollegati +
						", riferimentiContabili=" + riferimentiContabili +
						", numeroRiferimentiTotali=" + numeroRiferimentiTotali +
						", numeroRiferimentiInVita=" + numeroRiferimentiInVita +
						", numeroRiferimentiNonInVita=" + numeroRiferimentiNonInVita +
						", codiceTributo='" + codiceTributo + '\'' +
						'}';
	}
}
