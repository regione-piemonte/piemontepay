/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.mdpcore;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per DatiVersamentoRPT complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DatiVersamentoRPT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="importoTotaleDaVersare" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="identificativoUnivocoVersamento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ibanAddebito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bicAddebito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="firmaRicevuta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="datiSingoloVersamento" type="{http://interfacecxf.core.mdp.csi.it/}DatiSingoloVersamentoRPT" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatiVersamentoRPT", propOrder = {
    "importoTotaleDaVersare",
    "identificativoUnivocoVersamento",
    "ibanAddebito",
    "bicAddebito",
    "firmaRicevuta",
    "datiSingoloVersamento"
})
public class DatiVersamentoRPT {

    @XmlElement(required = true)
    protected BigDecimal importoTotaleDaVersare;
    @XmlElement(required = true)
    protected String identificativoUnivocoVersamento;
    protected String ibanAddebito;
    protected String bicAddebito;
    @XmlElement(required = true)
    protected String firmaRicevuta;
    @XmlElement(required = true)
    protected List<DatiSingoloVersamentoRPT> datiSingoloVersamento;

    /**
     * Recupera il valore della proprieta' importoTotaleDaVersare.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImportoTotaleDaVersare() {
        return importoTotaleDaVersare;
    }

    /**
     * Imposta il valore della proprieta' importoTotaleDaVersare.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImportoTotaleDaVersare(BigDecimal value) {
        this.importoTotaleDaVersare = value;
    }

    /**
     * Recupera il valore della proprieta' identificativoUnivocoVersamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoUnivocoVersamento() {
        return identificativoUnivocoVersamento;
    }

    /**
     * Imposta il valore della proprieta' identificativoUnivocoVersamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoUnivocoVersamento(String value) {
        this.identificativoUnivocoVersamento = value;
    }

    /**
     * Recupera il valore della proprieta' ibanAddebito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIbanAddebito() {
        return ibanAddebito;
    }

    /**
     * Imposta il valore della proprieta' ibanAddebito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIbanAddebito(String value) {
        this.ibanAddebito = value;
    }

    /**
     * Recupera il valore della proprieta' bicAddebito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBicAddebito() {
        return bicAddebito;
    }

    /**
     * Imposta il valore della proprieta' bicAddebito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBicAddebito(String value) {
        this.bicAddebito = value;
    }

    /**
     * Recupera il valore della proprieta' firmaRicevuta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirmaRicevuta() {
        return firmaRicevuta;
    }

    /**
     * Imposta il valore della proprieta' firmaRicevuta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirmaRicevuta(String value) {
        this.firmaRicevuta = value;
    }

    /**
     * Gets the value of the datiSingoloVersamento property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the datiSingoloVersamento property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDatiSingoloVersamento().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DatiSingoloVersamentoRPT }
     * 
     * 
     */
    public List<DatiSingoloVersamentoRPT> getDatiSingoloVersamento() {
        if (datiSingoloVersamento == null) {
            datiSingoloVersamento = new ArrayList<DatiSingoloVersamentoRPT>();
        }
        return this.datiSingoloVersamento;
    }

}
