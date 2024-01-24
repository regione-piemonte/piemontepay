/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getCodiceVersamentoOutput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getCodiceVersamentoOutput">
 *   &lt;complexContent>
 *     &lt;extension base="{http://interfacews.epaypacatalogsrv.epay.csi.it/}parentOutput">
 *       &lt;sequence>
 *         &lt;element name="codiceVersamento" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getCodiceVersamentoOutputDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getCodiceVersamentoOutput", propOrder = {
    "codiceVersamento"
})
public class GetCodiceVersamentoOutput
    extends ParentOutput
{

    protected GetCodiceVersamentoOutputDto codiceVersamento;

    /**
     * Gets the value of the codiceVersamento property.
     * 
     * @return
     *     possible object is
     *     {@link GetCodiceVersamentoOutputDto }
     *     
     */
    public GetCodiceVersamentoOutputDto getCodiceVersamento() {
        return codiceVersamento;
    }

    /**
     * Sets the value of the codiceVersamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetCodiceVersamentoOutputDto }
     *     
     */
    public void setCodiceVersamento(GetCodiceVersamentoOutputDto value) {
        this.codiceVersamento = value;
    }

}
