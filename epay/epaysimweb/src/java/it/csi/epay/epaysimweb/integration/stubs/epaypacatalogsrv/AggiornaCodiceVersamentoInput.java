/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per aggiornaCodiceVersamentoInput complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="aggiornaCodiceVersamentoInput"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://interfacews.epaypacatalogsrv.epay.csi.it/}parentInput"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="bic" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceModalitaIntegrazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceTipoPagamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codiceVoceEntrata" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="flagInvioFlussi" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="iban" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aggiornaCodiceVersamentoInput", propOrder = {
    "bic",
    "codiceModalitaIntegrazione",
    "codiceTipoPagamento",
    "codiceVoceEntrata",
    "descrizione",
    "email",
    "flagInvioFlussi",
    "iban",
    "id"
})
public class AggiornaCodiceVersamentoInput
    extends ParentInput
{

    protected String bic;
    protected String codiceModalitaIntegrazione;
    protected String codiceTipoPagamento;
    protected String codiceVoceEntrata;
    protected String descrizione;
    protected String email;
    protected Boolean flagInvioFlussi;
    protected String iban;
    protected Long id;

    /**
     * Recupera il valore della proprieta bic.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBic() {
        return bic;
    }

    /**
     * Imposta il valore della proprieta bic.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBic(String value) {
        this.bic = value;
    }

    /**
     * Recupera il valore della proprieta codiceModalitaIntegrazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceModalitaIntegrazione() {
        return codiceModalitaIntegrazione;
    }

    /**
     * Imposta il valore della proprieta codiceModalitaIntegrazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceModalitaIntegrazione(String value) {
        this.codiceModalitaIntegrazione = value;
    }

    /**
     * Recupera il valore della proprieta codiceTipoPagamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceTipoPagamento() {
        return codiceTipoPagamento;
    }

    /**
     * Imposta il valore della proprieta codiceTipoPagamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceTipoPagamento(String value) {
        this.codiceTipoPagamento = value;
    }

    /**
     * Recupera il valore della proprieta codiceVoceEntrata.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceVoceEntrata() {
        return codiceVoceEntrata;
    }

    /**
     * Imposta il valore della proprieta codiceVoceEntrata.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceVoceEntrata(String value) {
        this.codiceVoceEntrata = value;
    }

    /**
     * Recupera il valore della proprieta descrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Imposta il valore della proprieta descrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizione(String value) {
        this.descrizione = value;
    }

    /**
     * Recupera il valore della proprieta email.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Imposta il valore della proprieta email.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Recupera il valore della proprieta flagInvioFlussi.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFlagInvioFlussi() {
        return flagInvioFlussi;
    }

    /**
     * Imposta il valore della proprieta flagInvioFlussi.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFlagInvioFlussi(Boolean value) {
        this.flagInvioFlussi = value;
    }

    /**
     * Recupera il valore della proprieta iban.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIban() {
        return iban;
    }

    /**
     * Imposta il valore della proprieta iban.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIban(String value) {
        this.iban = value;
    }

    /**
     * Recupera il valore della proprieta id.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Imposta il valore della proprieta id.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

}
