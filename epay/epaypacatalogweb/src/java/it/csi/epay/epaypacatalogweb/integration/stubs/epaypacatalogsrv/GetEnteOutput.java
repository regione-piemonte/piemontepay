/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getEnteOutput complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getEnteOutput"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://interfacews.epaypacatalogsrv.epay.csi.it/}parentOutput"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ente" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getEnteOutputDto" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getEnteOutput", propOrder = {
    "ente"
})
public class GetEnteOutput
    extends ParentOutput
{

    protected GetEnteOutputDto ente;

    /**
     * Recupera il valore della propriet ente.
     * 
     * @return
     *     possible object is
     *     {@link GetEnteOutputDto }
     *     
     */
    public GetEnteOutputDto getEnte() {
        return ente;
    }

    /**
     * Imposta il valore della propriet ente.
     * 
     * @param value
     *     allowed object is
     *     {@link GetEnteOutputDto }
     *     
     */
    public void setEnte(GetEnteOutputDto value) {
        this.ente = value;
    }

}
