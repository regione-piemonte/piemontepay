/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getUtenteOutput complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getUtenteOutput"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://interfacews.epaypacatalogsrv.epay.csi.it/}parentOutput"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="utente" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getUtenteOutputDto" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getUtenteOutput", propOrder = {
    "utente"
})
public class GetUtenteOutput
    extends ParentOutput
{

    protected GetUtenteOutputDto utente;

    /**
     * Recupera il valore della propriet utente.
     * 
     * @return
     *     possible object is
     *     {@link GetUtenteOutputDto }
     *     
     */
    public GetUtenteOutputDto getUtente() {
        return utente;
    }

    /**
     * Imposta il valore della propriet utente.
     * 
     * @param value
     *     allowed object is
     *     {@link GetUtenteOutputDto }
     *     
     */
    public void setUtente(GetUtenteOutputDto value) {
        this.utente = value;
    }

}
