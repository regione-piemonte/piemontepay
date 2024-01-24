/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ricercaRiferimentoContabileOutput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ricercaRiferimentoContabileOutput">
 *   &lt;complexContent>
 *     &lt;extension base="{http://interfacews.epaypacatalogsrv.epay.csi.it/}parentOutput">
 *       &lt;sequence>
 *         &lt;element name="numeroRisultatiTotali" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="risultati" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}ricercaRiferimentoContabileGruppoCodiceVersamentoOutputDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaRiferimentoContabileOutput", propOrder = {
    "numeroRisultatiTotali",
    "risultati"
})
public class RicercaRiferimentoContabileOutput
    extends ParentOutput
{

    protected Integer numeroRisultatiTotali;
    @XmlElement(nillable = true)
    protected List<RicercaRiferimentoContabileGruppoCodiceVersamentoOutputDto> risultati;

    /**
     * Gets the value of the numeroRisultatiTotali property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumeroRisultatiTotali() {
        return numeroRisultatiTotali;
    }

    /**
     * Sets the value of the numeroRisultatiTotali property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumeroRisultatiTotali(Integer value) {
        this.numeroRisultatiTotali = value;
    }

    /**
     * Gets the value of the risultati property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the risultati property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRisultati().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RicercaRiferimentoContabileGruppoCodiceVersamentoOutputDto }
     * 
     * 
     */
    public List<RicercaRiferimentoContabileGruppoCodiceVersamentoOutputDto> getRisultati() {
        if (risultati == null) {
            risultati = new ArrayList<RicercaRiferimentoContabileGruppoCodiceVersamentoOutputDto>();
        }
        return this.risultati;
    }

}
