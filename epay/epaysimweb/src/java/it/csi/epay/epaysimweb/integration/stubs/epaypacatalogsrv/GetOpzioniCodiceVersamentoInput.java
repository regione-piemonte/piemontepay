/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getOpzioniCodiceVersamentoInput complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getOpzioniCodiceVersamentoInput"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://interfacews.epaypacatalogsrv.epay.csi.it/}parentInput"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="soloBase" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getOpzioniCodiceVersamentoInput", propOrder = {
    "soloBase"
})
public class GetOpzioniCodiceVersamentoInput
    extends ParentInput
{

    protected Boolean soloBase;

    /**
     * Recupera il valore della propriet soloBase.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSoloBase() {
        return soloBase;
    }

    /**
     * Imposta il valore della propriet soloBase.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSoloBase(Boolean value) {
        this.soloBase = value;
    }

}
