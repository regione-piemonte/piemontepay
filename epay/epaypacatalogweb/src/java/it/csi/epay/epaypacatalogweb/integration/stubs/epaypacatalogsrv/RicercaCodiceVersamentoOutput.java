/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ricercaCodiceVersamentoOutput complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ricercaCodiceVersamentoOutput"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://interfacews.epaypacatalogsrv.epay.csi.it/}parentOutput"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="numeroRisultatiTotali" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="risultati" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}ricercaCodiceVersamentoOutputDto" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ricercaCodiceVersamentoOutput", propOrder = {
    "numeroRisultatiTotali",
    "risultati"
})
public class RicercaCodiceVersamentoOutput
    extends ParentOutput
{

    protected Integer numeroRisultatiTotali;
    @XmlElement(nillable = true)
    protected List<RicercaCodiceVersamentoOutputDto> risultati;

    /**
     * Recupera il valore della propriet numeroRisultatiTotali.
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
     * Imposta il valore della propriet numeroRisultatiTotali.
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
     * {@link RicercaCodiceVersamentoOutputDto }
     * 
     * 
     */
    public List<RicercaCodiceVersamentoOutputDto> getRisultati() {
        if (risultati == null) {
            risultati = new ArrayList<RicercaCodiceVersamentoOutputDto>();
        }
        return this.risultati;
    }

}
