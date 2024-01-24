/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per inserisciUtenteOutput complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="inserisciUtenteOutput"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://interfacews.epaypacatalogsrv.epay.csi.it/}parentOutput"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="risultatoInserimento" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}inserisciUtenteOutputDto" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inserisciUtenteOutput", propOrder = {
    "risultatoInserimento"
})
public class InserisciUtenteOutput
    extends ParentOutput
{

    protected InserisciUtenteOutputDto risultatoInserimento;

    /**
     * Recupera il valore della propriet risultatoInserimento.
     * 
     * @return
     *     possible object is
     *     {@link InserisciUtenteOutputDto }
     *     
     */
    public InserisciUtenteOutputDto getRisultatoInserimento() {
        return risultatoInserimento;
    }

    /**
     * Imposta il valore della propriet risultatoInserimento.
     * 
     * @param value
     *     allowed object is
     *     {@link InserisciUtenteOutputDto }
     *     
     */
    public void setRisultatoInserimento(InserisciUtenteOutputDto value) {
        this.risultatoInserimento = value;
    }

}
