/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.riferimentocontabile;

import java.util.List;

import it.csi.epay.epaypacatalogweb.model.GenericVO;


public class RisultatoRicercaRiferimentoContabileRaggruppatoCodiceVersamentoVO extends GenericVO {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String descrizione;

    private String codiceTematica;

    private String descrizioneTematica;

    private String codiceMacrotipo;

    private String descrizioneMacrotipo;

    private String codiceVoceEntrata;

    private Long idVoceEntrata;

    private String descrizioneVoceEntrata;

    private String codiceTipoPagamento;

    private String descrizioneTipoPagamento;

    private String iban;

    private String bic;

    private String email;

    private Boolean flagInvioFlussi;

    private List<RisultatoRicercaRiferimentoContabileRaggruppatoCodiceVersamentoVO> codiciVersamentoCollegati;

    private List<RisultatoRicercaRiferimentoContabileVO> riferimentiContabili;

    private Integer numeroRiferimentiTotali;

    private Integer numeroRiferimentiInVita;

    private Integer numeroRiferimentiNonInVita;

    public List<RisultatoRicercaRiferimentoContabileRaggruppatoCodiceVersamentoVO> getCodiciVersamentoCollegati () {
        return codiciVersamentoCollegati;
    }

    public void setCodiciVersamentoCollegati ( List<RisultatoRicercaRiferimentoContabileRaggruppatoCodiceVersamentoVO> codiciVersamentoCollegati ) {
        this.codiciVersamentoCollegati = codiciVersamentoCollegati;
    }

    public List<RisultatoRicercaRiferimentoContabileVO> getRiferimentiContabili () {
        return riferimentiContabili;
    }

    public void setRiferimentiContabili ( List<RisultatoRicercaRiferimentoContabileVO> riferimentiContabili ) {
        this.riferimentiContabili = riferimentiContabili;
    }

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

    @Override
    public Long getId () {
        return id;
    }

    @Override
    public void setId ( Long id ) {
        this.id = id;
    }

    @Override
    public String getDescrizione () {
        return descrizione;
    }

    @Override
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

    public String getCodiceVoceEntrata () {
        return codiceVoceEntrata;
    }

    public void setCodiceVoceEntrata ( String codiceVoceEntrata ) {
        this.codiceVoceEntrata = codiceVoceEntrata;
    }

    public Long getIdVoceEntrata () {
        return idVoceEntrata;
    }

    public void setIdVoceEntrata ( Long idVoceEntrata ) {
        this.idVoceEntrata = idVoceEntrata;
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

    public String getDescrizioneTipoPagamento () {
        return descrizioneTipoPagamento;
    }

    public void setDescrizioneTipoPagamento ( String descrizioneTipoPagamento ) {
        this.descrizioneTipoPagamento = descrizioneTipoPagamento;
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

}
