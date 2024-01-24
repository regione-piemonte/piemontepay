/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymdpservices.dto.epaymdpservices;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "esitoVerificaDatiPagamento", namespace = "http://serviziofruitore.interfacews.mdp.nodospc.csi.it/")
@XmlAccessorType(XmlAccessType.FIELD)
public class EsitoVerificaDatiPagamento {

    public String esito;
    public String timestamp;
    public String codiceErrore;
    public String messaggioErrore;
    public String importoDovuto;
    public String ibanAccredito;
    public String bicAccredito;
    public String credenzialiPagatore;
    public String causaleVersamento;
    public Date dataFineValiditaPagamento;

    public String descrizionePagamento;

    public Date getFineDataValiditaPagamento () {
        return dataFineValiditaPagamento;
    }

    public void setDataFineValiditaPagamento ( Date dataValiditaPagamento ) {
        this.dataFineValiditaPagamento = dataValiditaPagamento;
    }

    public String getDescrizionePagamento () {
        return descrizionePagamento;
    }

    public void setDescrizionePagamento ( String descrizionePagamento ) {
        this.descrizionePagamento = descrizionePagamento;
    }

    protected String mac;

    public String getEsito() {
        return esito;
    }
    public void setEsito(String esito) {
        this.esito = esito;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public String getCodiceErrore() {
        return codiceErrore;
    }
    public void setCodiceErrore(String codiceErrore) {
        this.codiceErrore = codiceErrore;
    }
    public String getMessaggioErrore() {
        return messaggioErrore;
    }
    public void setMessaggioErrore(String messaggioErrore) {
        this.messaggioErrore = messaggioErrore;
    }
    public String getImportoDovuto() {
        return importoDovuto;
    }
    public void setImportoDovuto(String importoDovuto) {
        this.importoDovuto = importoDovuto;
    }
    public String getIbanAccredito() {
        return ibanAccredito;
    }
    public void setIbanAccredito(String ibanAccredito) {
        this.ibanAccredito = ibanAccredito;
    }
    public String getBicAccredito() {
        return bicAccredito;
    }
    public void setBicAccredito(String bicAccredito) {
        this.bicAccredito = bicAccredito;
    }
    public String getCredenzialiPagatore() {
        return credenzialiPagatore;
    }
    public void setCredenzialiPagatore(String credenzialiPagatore) {
        this.credenzialiPagatore = credenzialiPagatore;
    }
    public String getCausaleVersamento() {
        return causaleVersamento;
    }
    public void setCausaleVersamento(String causaleVersamento) {
        this.causaleVersamento = causaleVersamento;
    }
    public String getMac() {
        return mac;
    }
    public void setMac(String mac) {
        this.mac = mac;
    }

}
