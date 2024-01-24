/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class TracciabilitaChiamanteEsternoSincrono implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 736197800364600274L;

    private Long idChiamata;

    private ChiamanteEsterno chiamanteEsterno;

    private String iuv;

    private String idTransazione;

    private String codiceChiamante;

    private String password;

    private String codiceFiscaleEnte;

    private String causale;

    private String tipoPagamaneto;

    private BigDecimal importo;

    private String nome;

    private String cognome;

    private String ragioneSociale;

    private String eMail;

    private String codiceFiscalePartitaIVAPagatore;

    private String identificativoPagamento;

    private Date timestampChiamata;

    private String remoteHost;

    private String descrizioneChiamante;

    public void setIdTransazione ( String idTransazione ) {
        this.idTransazione = idTransazione;
    }

    public void setChiamanteEsterno ( ChiamanteEsterno chiamanteEsterno ) {
        this.chiamanteEsterno = chiamanteEsterno;
    }

    public Long getIdChiamata () {
        return idChiamata;
    }

    public void setIdChiamata ( Long idChiamata ) {
        this.idChiamata = idChiamata;
    }

    public String getCodiceChiamante () {
        return codiceChiamante;
    }

    public void setCodiceChiamante ( String codiceChiamante ) {
        this.codiceChiamante = codiceChiamante;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword ( String password ) {
        this.password = password;
    }

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

    public String getTipoPagamaneto () {
        return tipoPagamaneto;
    }

    public void setTipoPagamaneto ( String tipoPagamaneto ) {
        this.tipoPagamaneto = tipoPagamaneto;
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

    public String geteMail () {
        return eMail;
    }

    public void seteMail ( String eMail ) {
        this.eMail = eMail;
    }

    public String getCodiceFiscalePartitaIVAPagatore () {
        return codiceFiscalePartitaIVAPagatore;
    }

    public void setCodiceFiscalePartitaIVAPagatore ( String codiceFiscalePartitaIVAPagatore ) {
        this.codiceFiscalePartitaIVAPagatore = codiceFiscalePartitaIVAPagatore;
    }

    public String getIdentificativoPagamento () {
        return identificativoPagamento;
    }

    public void setIdentificativoPagamento ( String identificativoPagamento ) {
        this.identificativoPagamento = identificativoPagamento;
    }

    public Date getTimestampChiamata () {
        return timestampChiamata;
    }

    public void setTimestampChiamata ( Date timestampChiamata ) {
        this.timestampChiamata = timestampChiamata;
    }

    public String getRemoteHost () {
        return remoteHost;
    }

    public void setRemoteHost ( String remoteHost ) {
        this.remoteHost = remoteHost;
    }

    public String getDescrizioneChiamante () {
        return descrizioneChiamante;
    }

    public void setDescrizioneChiamante ( String descrizioneChiamante ) {
        this.descrizioneChiamante = descrizioneChiamante;
    }

    public String getIuv () {
        return iuv;
    }

    public void setIuv ( String iuv ) {
        this.iuv = iuv;
    }

    public ChiamanteEsterno getChiamanteEsterno () {
        return chiamanteEsterno;
    }

    public String getIdTransazione () {
        return idTransazione;
    }

}
