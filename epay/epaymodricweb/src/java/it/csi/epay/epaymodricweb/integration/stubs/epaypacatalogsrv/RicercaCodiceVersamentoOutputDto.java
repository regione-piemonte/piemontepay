/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ricercaCodiceVersamentoOutputDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ricercaCodiceVersamentoOutputDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bic" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceMacrotipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceTematica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceTipoPagamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiceVoceEntrata" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codiciVersamentoCollegati" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}ricercaCodiceVersamentoOutputDto" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descrizioneMacrotipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descrizioneTematica" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descrizioneTipoPagamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descrizioneVoceEntrata" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flagInvioFlussi" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="iban" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="idVoceEntrata" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="tematicaEsclusa" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="idPadre" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="flagMbSecondario" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="flagMbPrimario" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaCodiceVersamentoOutputDto", propOrder = {
    "bic",
    "codice",
    "codiceMacrotipo",
    "codiceTematica",
    "codiceTipoPagamento",
    "codiceVoceEntrata",
    "codiciVersamentoCollegati",
    "descrizione",
    "descrizioneMacrotipo",
    "descrizioneTematica",
    "descrizioneTipoPagamento",
    "descrizioneVoceEntrata",
    "email",
    "flagInvioFlussi",
    "iban",
    "id",
    "idVoceEntrata",
    "tematicaEsclusa",
    "idPadre",
    "flagMbSecondario",
    "flagMbPrimario"
})
public class RicercaCodiceVersamentoOutputDto {

    protected String bic;
    protected String codice;
    protected String codiceMacrotipo;
    protected String codiceTematica;
    protected String codiceTipoPagamento;
    protected String codiceVoceEntrata;
    @XmlElement(nillable = true)
    protected List<RicercaCodiceVersamentoOutputDto> codiciVersamentoCollegati;
    protected String descrizione;
    protected String descrizioneMacrotipo;
    protected String descrizioneTematica;
    protected String descrizioneTipoPagamento;
    protected String descrizioneVoceEntrata;
    protected String email;
    protected Boolean flagInvioFlussi;
    protected String iban;
    protected Long id;
    protected Long idVoceEntrata;
    protected Boolean tematicaEsclusa;
    protected Long idPadre;
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
     * Gets the value of the codice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodice() {
        return codice;
    }

    /**
     * Sets the value of the codice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodice(String value) {
        this.codice = value;
    }

    /**
     * Gets the value of the codiceMacrotipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceMacrotipo() {
        return codiceMacrotipo;
    }

    /**
     * Sets the value of the codiceMacrotipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceMacrotipo(String value) {
        this.codiceMacrotipo = value;
    }

    /**
     * Gets the value of the codiceTematica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceTematica() {
        return codiceTematica;
    }

    /**
     * Sets the value of the codiceTematica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceTematica(String value) {
        this.codiceTematica = value;
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
     * Gets the value of the codiciVersamentoCollegati property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the codiciVersamentoCollegati property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCodiciVersamentoCollegati().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RicercaCodiceVersamentoOutputDto }
     * 
     * 
     */
    public List<RicercaCodiceVersamentoOutputDto> getCodiciVersamentoCollegati() {
        if (codiciVersamentoCollegati == null) {
            codiciVersamentoCollegati = new ArrayList<RicercaCodiceVersamentoOutputDto>();
        }
        return this.codiciVersamentoCollegati;
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
     * Gets the value of the descrizioneMacrotipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneMacrotipo() {
        return descrizioneMacrotipo;
    }

    /**
     * Sets the value of the descrizioneMacrotipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneMacrotipo(String value) {
        this.descrizioneMacrotipo = value;
    }

    /**
     * Gets the value of the descrizioneTematica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneTematica() {
        return descrizioneTematica;
    }

    /**
     * Sets the value of the descrizioneTematica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneTematica(String value) {
        this.descrizioneTematica = value;
    }

    /**
     * Gets the value of the descrizioneTipoPagamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneTipoPagamento() {
        return descrizioneTipoPagamento;
    }

    /**
     * Sets the value of the descrizioneTipoPagamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneTipoPagamento(String value) {
        this.descrizioneTipoPagamento = value;
    }

    /**
     * Gets the value of the descrizioneVoceEntrata property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneVoceEntrata() {
        return descrizioneVoceEntrata;
    }

    /**
     * Sets the value of the descrizioneVoceEntrata property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneVoceEntrata(String value) {
        this.descrizioneVoceEntrata = value;
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
     * Gets the value of the id property.
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
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the idVoceEntrata property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdVoceEntrata() {
        return idVoceEntrata;
    }

    /**
     * Sets the value of the idVoceEntrata property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdVoceEntrata(Long value) {
        this.idVoceEntrata = value;
    }

    /**
     * Gets the value of the tematicaEsclusa property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTematicaEsclusa() {
        return tematicaEsclusa;
    }

    /**
     * Sets the value of the tematicaEsclusa property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTematicaEsclusa(Boolean value) {
        this.tematicaEsclusa = value;
    }

	public Long getIdPadre() {
		return idPadre;
	}

	public void setIdPadre(Long idPadre) {
		this.idPadre = idPadre;
	}

	/**
	 * @return the flagMbSecondario
	 */
	public Boolean getFlagMbSecondario() {
		return flagMbSecondario;
	}

	/**
	 * @param flagMbSecondario the flagMbSecondario to set
	 */
	public void setFlagMbSecondario(Boolean flagMbSecondario) {
		this.flagMbSecondario = flagMbSecondario;
	}
    
    

}
