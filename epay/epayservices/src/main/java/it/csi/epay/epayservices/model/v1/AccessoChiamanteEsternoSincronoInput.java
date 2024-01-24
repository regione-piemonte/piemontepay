/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.model.v1;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


public class AccessoChiamanteEsternoSincronoInput implements Serializable {

    private static final long serialVersionUID = -6903007449346036409L;

    private Timestamp timestampChiamata;

    private String ipChiamante;

    private String codiceChiamante;

    private String password;

    private String codiceFiscaleEnte;

    private String causale;

    private String tipoPagamento;

    private BigDecimal importo;

    private String nome;

    private String cognome;

    private String ragioneSociale;

    private String email;

    private String codiceFiscalePartitaIVAPagatore;

    private String identificativoPagamento;

    private String notePerIlPagatore;

    private Date dataScadenza;

    private Date dataInizioValidita;

    private Date dataFineValidita;

    private List<AccessoChiamanteEsternoSincronoComponentePagamentoDSRInput> componentiPagamento;

    public Timestamp getTimestampChiamata () {
        return timestampChiamata;
    }

    public void setTimestampChiamata ( Timestamp timestampChiamata ) {
        this.timestampChiamata = timestampChiamata;
    }

    public String getIpChiamante () {
        return ipChiamante;
    }

    public void setIpChiamante ( String ipChiamante ) {
        this.ipChiamante = ipChiamante;
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

    public String getIdentificativoPagamento () {
        return identificativoPagamento;
    }

    public void setIdentificativoPagamento ( String identificativoPagamento ) {
        this.identificativoPagamento = identificativoPagamento;
    }

    public List<AccessoChiamanteEsternoSincronoComponentePagamentoDSRInput> getComponentiPagamento () {
        return componentiPagamento;
    }

    public void setComponentiPagamento ( List<AccessoChiamanteEsternoSincronoComponentePagamentoDSRInput> componentiPagamento ) {
        this.componentiPagamento = componentiPagamento;
    }

    /**
     * @return the notePerIlPagatore
     */
    public String getNotePerIlPagatore () {
        return notePerIlPagatore;
    }

    /**
     * @return the dataScadenza
     */
    public Date getDataScadenza () {
        return dataScadenza;
    }

    /**
     * @return the dataInizioValidita
     */
    public Date getDataInizioValidita () {
        return dataInizioValidita;
    }

    /**
     * @return the dataFineValidita
     */
    public Date getDataFineValidita () {
        return dataFineValidita;
    }

    /**
     * @param notePerIlPagatore the notePerIlPagatore to set
     */
    public void setNotePerIlPagatore ( String notePerIlPagatore ) {
        this.notePerIlPagatore = notePerIlPagatore;
    }

    /**
     * @param dataScadenza the dataScadenza to set
     */
    public void setDataScadenza ( Date dataScadenza ) {
        this.dataScadenza = dataScadenza;
    }

    /**
     * @param dataInizioValidita the dataInizioValidita to set
     */
    public void setDataInizioValidita ( Date dataInizioValidita ) {
        this.dataInizioValidita = dataInizioValidita;
    }

    /**
     * @param dataFineValidita the dataFineValidita to set
     */
    public void setDataFineValidita ( Date dataFineValidita ) {
        this.dataFineValidita = dataFineValidita;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString () {
        StringBuilder builder = new StringBuilder ();
        builder.append ( "AccessoChiamanteEsternoSincronoInput [timestampChiamata=" );
        builder.append ( timestampChiamata );
        builder.append ( ", ipChiamante=" );
        builder.append ( ipChiamante );
        builder.append ( ", codiceChiamante=" );
        builder.append ( codiceChiamante );
        builder.append ( ", codiceFiscaleEnte=" );
        builder.append ( codiceFiscaleEnte );
        builder.append ( ", causale=" );
        builder.append ( causale );
        builder.append ( ", tipoPagamento=" );
        builder.append ( tipoPagamento );
        builder.append ( ", importo=" );
        builder.append ( importo );
        builder.append ( ", nome=" );
        builder.append ( nome );
        builder.append ( ", cognome=" );
        builder.append ( cognome );
        builder.append ( ", ragioneSociale=" );
        builder.append ( ragioneSociale );
        builder.append ( ", email=" );
        builder.append ( email );
        builder.append ( ", codiceFiscalePartitaIVAPagatore=" );
        builder.append ( codiceFiscalePartitaIVAPagatore );
        builder.append ( ", identificativoPagamento=" );
        builder.append ( identificativoPagamento );
        builder.append ( ", notePerIlPagatore=" );
        builder.append ( notePerIlPagatore );
        builder.append ( ", dataScadenza=" );
        builder.append ( dataScadenza );
        builder.append ( ", dataInizioValidita=" );
        builder.append ( dataInizioValidita );
        builder.append ( ", dataFineValidita=" );
        builder.append ( dataFineValidita );
        builder.append ( ", componentiPagamento=" );
        builder.append ( componentiPagamento );
        builder.append ( "]" );
        return builder.toString ();
    }

}
