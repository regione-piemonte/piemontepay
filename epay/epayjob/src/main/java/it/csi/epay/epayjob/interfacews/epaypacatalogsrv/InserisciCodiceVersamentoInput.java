/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayjob.interfacews.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for inserisciCodiceVersamentoInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="inserisciCodiceVersamentoInput">
 *   &lt;complexContent>
 *     &lt;extension base="{http://interfacews.epaypacatalogsrv.epay.csi.it/}parentInput">
 *       &lt;sequence>
 *         &lt;element name="bic" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceTipoPagamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceVoceEntrata" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flagInvioFlussi" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="iban" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idCodiceVersamentoPadre" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/> &lt;element name="flagInvioFlussi" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="flagMbSecondario" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="flagMbPrimario" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inserisciCodiceVersamentoInput", propOrder = {
    "bic",
    "codiceTipoPagamento",
    "codiceVoceEntrata",
    "descrizione",
    "email",
    "flagInvioFlussi",
    "iban",
    "idCodiceVersamentoPadre",
    "flagMbSecondario",
    "flagMbPrimario"
})
public class InserisciCodiceVersamentoInput
    extends ParentInput
{

    protected String bic;
    protected String codiceTipoPagamento;
    protected String codiceVoceEntrata;
    protected String descrizione;
    protected String email;
    protected Boolean flagInvioFlussi;
    protected String iban;
    protected Long idCodiceVersamentoPadre;
    protected Boolean flagMbSecondario;
    protected Boolean flagMbPrimario;

    /**
	 * @return the flagMbPrimario
	 */
	public Boolean getFlagMbPrimario() {
		return flagMbPrimario;
	}

	/**
	 * @param flagMbPrimario the flagMbPrimario to set
	 */
	public void setFlagMbPrimario(Boolean flagMbPrimario) {
		this.flagMbPrimario = flagMbPrimario;
	}

	/**
     * Gets the value of the bic property.
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
     * Sets the value of the bic property.
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
     * Gets the value of the codiceTipoPagamento property.
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
     * Sets the value of the codiceTipoPagamento property.
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
     * Gets the value of the codiceVoceEntrata property.
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
     * Sets the value of the codiceVoceEntrata property.
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
     * Gets the value of the descrizione property.
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
     * Sets the value of the descrizione property.
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
     * Gets the value of the email property.
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
     * Sets the value of the email property.
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
     * Gets the value of the flagInvioFlussi property.
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
     * Sets the value of the flagInvioFlussi property.
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
     * Gets the value of the iban property.
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
     * Sets the value of the iban property.
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
     * Gets the value of the idCodiceVersamentoPadre property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdCodiceVersamentoPadre() {
        return idCodiceVersamentoPadre;
    }

    /**
     * Sets the value of the idCodiceVersamentoPadre property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdCodiceVersamentoPadre(Long value) {
        this.idCodiceVersamentoPadre = value;
    }

    /**
     * Gets the value of the flagMbSecondario property.
     * 
	 *
	 * @return the flagMbSecondario
	 */
	public Boolean getFlagMbSecondario() {
		return flagMbSecondario;
	}

	
	/**
     * Sets the value of the flagMbSecondario property.
     * 
	 * @param flagMbSecondario the flagMbSecondario to set
	 */
	public void setFlagMbSecondario(Boolean flagMbSecondario) {
		this.flagMbSecondario = flagMbSecondario;
	}
    
    

}
