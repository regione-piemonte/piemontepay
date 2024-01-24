/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


/**
 * Richiesta di pagamento marca da bollo.
 */

public class PagamentoMarcaDaBolloChiamanteEsternoInput extends AccessoChiamanteEsternoSincronoSplitInput implements Serializable {

    private static final long serialVersionUID = -5415280858827186363L;

    private String codiceFiscaleEnte;

    private String causale;

    private String tipoPagamento;

    private BigDecimal importo;

    private String nome;

    private String cognome;

    private String ragioneSociale;

    private String email;

    private String codiceFiscalePartitaIVAPagatore;

    private String hashDocumento;

    private Boolean flagSoloMarca;

    private String provincia;

    private String tipoBollo;

    private BigDecimal importoBollo;

    private Integer quantita;

    private String iuvIstanzaAssociata;

    private List<AccessoChiamanteEsternoSincronoComponentePagamentoDSRInput> componentiPagamento;

    public String getCodiceFiscaleEnte () {
        return codiceFiscaleEnte;
    }

    public void setCodiceFiscaleEnte ( String codiceFiscaleEnte ) {
        this.codiceFiscaleEnte = codiceFiscaleEnte;
    }

    public String getCausale () {
        return causale;
    }

    public void setCausale ( String causale ) {
        this.causale = causale;
    }

    public String getTipoPagamento () {
        return tipoPagamento;
    }

    public void setTipoPagamento ( String tipoPagamento ) {
        this.tipoPagamento = tipoPagamento;
    }

    public BigDecimal getImporto () {
        return importo;
    }

    public void setImporto ( BigDecimal importo ) {
        this.importo = importo;
    }

    public String getNome () {
        return nome;
    }

    public void setNome ( String nome ) {
        this.nome = nome;
    }

    public String getCognome () {
        return cognome;
    }

    public void setCognome ( String cognome ) {
        this.cognome = cognome;
    }

    public String getRagioneSociale () {
        return ragioneSociale;
    }

    public void setRagioneSociale ( String ragioneSociale ) {
        this.ragioneSociale = ragioneSociale;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail ( String email ) {
        this.email = email;
    }

    public String getCodiceFiscalePartitaIVAPagatore () {
        return codiceFiscalePartitaIVAPagatore;
    }

    public void setCodiceFiscalePartitaIVAPagatore ( String codiceFiscalePartitaIVAPagatore ) {
        this.codiceFiscalePartitaIVAPagatore = codiceFiscalePartitaIVAPagatore;
    }

    public List<AccessoChiamanteEsternoSincronoComponentePagamentoDSRInput> getComponentiPagamento () {
        return componentiPagamento;
    }

    public void setComponentiPagamento ( List<AccessoChiamanteEsternoSincronoComponentePagamentoDSRInput> componentiPagamento ) {
        this.componentiPagamento = componentiPagamento;
    }

    public String getHashDocumento () {
        return hashDocumento;
    }

    public void setHashDocumento ( String hashDocumento ) {
        this.hashDocumento = hashDocumento;
    }

    public Boolean getFlagSoloMarca () {
        return flagSoloMarca;
    }

    public void setFlagSoloMarca ( Boolean flagSoloMarca ) {
        this.flagSoloMarca = flagSoloMarca;
    }

    public String getProvincia () {
        return provincia;
    }

    public void setProvincia ( String provincia ) {
        this.provincia = provincia;
    }

    public String getTipoBollo () {
        return tipoBollo;
    }

    public void setTipoBollo ( String tipoBollo ) {
        this.tipoBollo = tipoBollo;
    }

    public BigDecimal getImportoBollo () {
        return importoBollo;
    }

    public void setImportoBollo ( BigDecimal importoBollo ) {
        this.importoBollo = importoBollo;
    }

    public Integer getQuantita () {
        return quantita;
    }

    public void setQuantita ( Integer quantita ) {
        this.quantita = quantita;
    }

    public String getIuvIstanzaAssociata () {
        return iuvIstanzaAssociata;
    }

    public void setIuvIstanzaAssociata ( String iuvIstanzaAssociata ) {
        this.iuvIstanzaAssociata = iuvIstanzaAssociata;
    }

    @Override
    public String toString () {
        return "GetIuvMarcaDaBolloChiamanteEsternoInput ["
            + " codiceFiscaleEnte=" + StringUtils.trimToEmpty ( getCodiceFiscaleEnte () )
            + " causale=" + StringUtils.trimToEmpty ( getCausale () )
            + "tipoPagamento=" + StringUtils.trimToEmpty ( getTipoPagamento () )
            + ( getImporto () != null ? "importo=" + getImporto () + ", " : "" )
            + "nome=" + StringUtils.trimToEmpty ( getNome () )
            + "cognome=" + StringUtils.trimToEmpty ( getCognome () )
            + "ragioneSociale=" + StringUtils.trimToEmpty ( getRagioneSociale () )
            + "email=" + StringUtils.trimToEmpty ( getEmail () )
            + "codiceFiscalePartitaIVAPagatore=" + StringUtils.trimToEmpty ( getCodiceFiscalePartitaIVAPagatore () )
            + "hashDocumento=" + StringUtils.trimToEmpty ( getHashDocumento () )
            + "flagSoloMarca=" + getFlagSoloMarca ()
            + "provincia=" + StringUtils.trimToEmpty ( getProvincia () )
            + "tipoBollo=" + StringUtils.trimToEmpty ( getTipoBollo () )
            + "importoBollo=" + ( getImportoBollo () != null ? getImportoBollo () : "" )
            + "quantita=" + ( getQuantita () != null ? getQuantita () : "" )
            + "iuvIstanzaAssociata=" + StringUtils.trimToEmpty ( getIuvIstanzaAssociata () )
            + ( getComponentiPagamento () != null ? "componentiPagamento=" + getComponentiPagamento () : "" ) + "]";
    }

}
