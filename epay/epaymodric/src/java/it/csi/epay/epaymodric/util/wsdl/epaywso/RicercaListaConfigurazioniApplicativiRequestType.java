/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.epaywso;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RicercaListaConfigurazioniApplicativiRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RicercaListaConfigurazioniApplicativiRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodiceFiscaleEnte" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CodiciVersamento" type="{http://www.csi.it/epay/epaywso/businesstypes}CodiceVersamentoTypeList"/>
 *         &lt;element name="TipoRichiesta" type="{http://www.csi.it/epay/epaywso/types}TipoRichiestaType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RicercaListaConfigurazioniApplicativiRequestType", propOrder = {
    "codiceFiscaleEnte",
    "codiciVersamento",
    "tipoRichiesta"
})
public class RicercaListaConfigurazioniApplicativiRequestType {

    @XmlElement(name = "CodiceFiscaleEnte", required = true)
    protected String codiceFiscaleEnte;
    @XmlElement(name = "CodiciVersamento", required = true)
    protected CodiceVersamentoTypeList codiciVersamento;
    @XmlElement(name = "TipoRichiesta", required = true)
    protected TipoRichiestaType tipoRichiesta;

    /**
     * Gets the value of the codiceFiscaleEnte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFiscaleEnte() {
        return codiceFiscaleEnte;
    }

    /**
     * Sets the value of the codiceFiscaleEnte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFiscaleEnte(String value) {
        this.codiceFiscaleEnte = value;
    }

    /**
     * Gets the value of the codiciVersamento property.
     * 
     * @return
     *     possible object is
     *     {@link CodiceVersamentoTypeList }
     *     
     */
    public CodiceVersamentoTypeList getCodiciVersamento() {
        return codiciVersamento;
    }

    /**
     * Sets the value of the codiciVersamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodiceVersamentoTypeList }
     *     
     */
    public void setCodiciVersamento(CodiceVersamentoTypeList value) {
        this.codiciVersamento = value;
    }

    /**
     * Gets the value of the tipoRichiesta property.
     * 
     * @return
     *     possible object is
     *     {@link TipoRichiestaType }
     *     
     */
    public TipoRichiestaType getTipoRichiesta() {
        return tipoRichiesta;
    }

    /**
     * Sets the value of the tipoRichiesta property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoRichiestaType }
     *     
     */
    public void setTipoRichiesta(TipoRichiestaType value) {
        this.tipoRichiesta = value;
    }

}
