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
 * <p>Classe Java per inserisciCodiceVersamentoOutput complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="inserisciCodiceVersamentoOutput"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://interfacews.epaypacatalogsrv.epay.csi.it/}parentOutput"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="messaggi" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}messageDto" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="risultatoInserimento" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}inserisciCodiceVersamentoOutputDto" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inserisciCodiceVersamentoOutput", propOrder = {
    "messaggi",
    "risultatoInserimento"
})
public class InserisciCodiceVersamentoOutput
    extends ParentOutput
{

    @XmlElement(nillable = true)
    protected List<MessageDto> messaggi;
    protected InserisciCodiceVersamentoOutputDto risultatoInserimento;

    /**
     * Gets the value of the messaggi property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the messaggi property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMessaggi().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MessageDto }
     * 
     * 
     */
    public List<MessageDto> getMessaggi() {
        if (messaggi == null) {
            messaggi = new ArrayList<MessageDto>();
        }
        return this.messaggi;
    }

    /**
     * Recupera il valore della propriet risultatoInserimento.
     * 
     * @return
     *     possible object is
     *     {@link InserisciCodiceVersamentoOutputDto }
     *     
     */
    public InserisciCodiceVersamentoOutputDto getRisultatoInserimento() {
        return risultatoInserimento;
    }

    /**
     * Imposta il valore della propriet risultatoInserimento.
     * 
     * @param value
     *     allowed object is
     *     {@link InserisciCodiceVersamentoOutputDto }
     *     
     */
    public void setRisultatoInserimento(InserisciCodiceVersamentoOutputDto value) {
        this.risultatoInserimento = value;
    }

}
