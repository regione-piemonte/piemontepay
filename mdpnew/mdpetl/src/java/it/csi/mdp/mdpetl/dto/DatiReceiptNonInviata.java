/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpetl.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class DatiReceiptNonInviata extends BaseDTO  {

	private static final long serialVersionUID = -85768446336181040L;
	
	private Integer id;
	private String receiptId;
	private String creditorReferenceId;
    private String noticeNumber;
    private boolean iuvSconosciuto;
    private String applicationId;
    private boolean pagamentoInviato;
    private BigDecimal paymentAmount;
    private String transactionId;
    private byte[] xmlReceip;
    private Timestamp paymentDateTime;
    private String paymentMethod;
    private Timestamp transfertDate;
    private Integer idElementoMultiversamento;
    private Timestamp dataOraInsert;
    private Timestamp dataOraUpdate;
    private Timestamp dataOraRicezione;
    private Timestamp dataInvioFruitore;
    private Timestamp dataOraInvioFallito;
    private Timestamp dataOraRetry;
    private Integer feePsp;
    private String idPsp;
    private String pspCompanyName;
    private Timestamp applicationDate;
    private String idPa;
    private String idBrokerPa;
    private String idStation;
    private String statoInvioFruitore;
    private String msgInvioFallito;

    private double importo;
    
    
    
	private CodaInvioReceipt coda;



    
    public Integer getId () {
        return id;
    }
    
    public void setId ( Integer id ) {
        this.id = id;
    }

    
    public String getReceiptId () {
        return receiptId;
    }

    
    public void setReceiptId ( String receiptId ) {
        this.receiptId = receiptId;
    }

    
    public String getCreditorReferenceId () {
        return creditorReferenceId;
    }

    
    public void setCreditorReferenceId ( String creditorReferenceId ) {
        this.creditorReferenceId = creditorReferenceId;
    }

    
    public String getNoticeNumber () {
        return noticeNumber;
    }

    
    public void setNoticeNumber ( String noticeNumber ) {
        this.noticeNumber = noticeNumber;
    }

    
    public boolean isIuvSconosciuto () {
        return iuvSconosciuto;
    }

    
    public void setIuvSconosciuto ( boolean iuvSconosciuto ) {
        this.iuvSconosciuto = iuvSconosciuto;
    }

    
    public String getApplicationId () {
        return applicationId;
    }

    
    public void setApplicationId ( String applicationId ) {
        this.applicationId = applicationId;
    }

    
    public boolean isPagamentoInviato () {
        return pagamentoInviato;
    }
    
    public void setPagamentoInviato ( boolean pagamentoInviato ) {
        this.pagamentoInviato = pagamentoInviato;
    }

    
   

    
    public String getTransactionId () {
        return transactionId;
    }

    
    public void setTransactionId ( String transactionId ) {
        this.transactionId = transactionId;
    }

    
    public byte [] getXmlReceip () {
        return xmlReceip;
    }

    
    public void setXmlReceip ( byte [] xmlReceip ) {
        this.xmlReceip = xmlReceip;
    }

    
    public Timestamp getPaymentDateTime () {
        return paymentDateTime;
    }

    
    public void setPaymentDateTime ( Timestamp paymentDateTime ) {
        this.paymentDateTime = paymentDateTime;
    }

    
    public String getPaymentMethod () {
        return paymentMethod;
    }

    
    public void setPaymentMethod ( String paymentMethod ) {
        this.paymentMethod = paymentMethod;
    }

    
    public Timestamp getTransfertDate () {
        return transfertDate;
    }

    
    public void setTransfertDate ( Timestamp transfertDate ) {
        this.transfertDate = transfertDate;
    }

    
    public Integer getIdElementoMultiversamento () {
        return idElementoMultiversamento;
    }

    
    public void setIdElementoMultiversamento ( Integer idElementoMultiversamento ) {
        this.idElementoMultiversamento = idElementoMultiversamento;
    }

    
    public Timestamp getDataOraInsert () {
        return dataOraInsert;
    }

    
    public void setDataOraInsert ( Timestamp dataOraInsert ) {
        this.dataOraInsert = dataOraInsert;
    }

    
    public Timestamp getDataOraUpdate () {
        return dataOraUpdate;
    }

    
    public void setDataOraUpdate ( Timestamp dataOraUpdate ) {
        this.dataOraUpdate = dataOraUpdate;
    }

    
    public Timestamp getDataOraRicezione () {
        return dataOraRicezione;
    }

    
    public void setDataOraRicezione ( Timestamp dataOraRicezione ) {
        this.dataOraRicezione = dataOraRicezione;
    }

    
    public Timestamp getDataInvioFruitore () {
        return dataInvioFruitore;
    }

    
    public void setDataInvioFruitore ( Timestamp dataInvioFruitore ) {
        this.dataInvioFruitore = dataInvioFruitore;
    }

    
    public Timestamp getDataOraInvioFallito () {
        return dataOraInvioFallito;
    }

    
    public void setDataOraInvioFallito ( Timestamp dataOraInvioFallito ) {
        this.dataOraInvioFallito = dataOraInvioFallito;
    }

    
    public Timestamp getDataOraRetry () {
        return dataOraRetry;
    }

    
    public void setDataOraRetry ( Timestamp dataOraRetry ) {
        this.dataOraRetry = dataOraRetry;
    }

    
    public Integer getFeePsp () {
        return feePsp;
    }

    
    public void setFeePsp ( Integer feePsp ) {
        this.feePsp = feePsp;
    }

    
    public String getIdPsp () {
        return idPsp;
    }

    
    public void setIdPsp ( String idPsp ) {
        this.idPsp = idPsp;
    }



    
    public String getPspCompanyName () {
        return pspCompanyName;
    }

    
    public void setPspCompanyName ( String pspCompanyName ) {
        this.pspCompanyName = pspCompanyName;
    }

    
    public Timestamp getApplicationDate () {
        return applicationDate;
    }

    
    public void setApplicationDate ( Timestamp applicationDate ) {
        this.applicationDate = applicationDate;
    }

    
    public String getIdPa () {
        return idPa;
    }

    
    public void setIdPa ( String idPa ) {
        this.idPa = idPa;
    }

    
    public String getIdBrokerPa () {
        return idBrokerPa;
    }

    
    public void setIdBrokerPa ( String idBrokerPa ) {
        this.idBrokerPa = idBrokerPa;
    }

    
    public String getIdStation () {
        return idStation;
    }

    
    public void setIdStation ( String idStation ) {
        this.idStation = idStation;
    }

    
    public String getStatoInvioFruitore () {
        return statoInvioFruitore;
    }

    
    public void setStatoInvioFruitore ( String statoInvioFruitore ) {
        this.statoInvioFruitore = statoInvioFruitore;
    }

    
    public String getMsgInvioFallito () {
        return msgInvioFallito;
    }

    
    public void setMsgInvioFallito ( String msgInvioFallito ) {
        this.msgInvioFallito = msgInvioFallito;
    }
    
    
    public double getImporto () {
        return importo;
    }

    
    public void setImporto ( double importo ) {
        this.importo = importo;
    }


    public CodaInvioReceipt getCoda () {
        return coda;
    }

    
    public void setCoda ( CodaInvioReceipt coda ) {
        this.coda = coda;
    }

    
    public BigDecimal getPaymentAmount () {
        return paymentAmount;
    }

    
    public void setPaymentAmount ( BigDecimal paymentAmount ) {
        this.paymentAmount = paymentAmount;
    }
    
    
	
}

