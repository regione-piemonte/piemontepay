/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtoInputWsRicercaFlussiDaEsportare complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoInputWsRicercaFlussiDaEsportare">
 *   &lt;complexContent>
 *     &lt;extension base="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoInputWsRicercaFlussoOrigine">
 *       &lt;sequence>
 *         &lt;element name="tipoFileReport" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}tipoFileReportEnum" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoInputWsRicercaFlussiDaEsportare", propOrder = {
    "tipoFileReport"
})
public class DtoInputWsRicercaFlussiDaEsportare
    extends DtoInputWsRicercaFlussoOrigine
{

    protected TipoFileReportEnum tipoFileReport;

    /**
     * Gets the value of the tipoFileReport property.
     * 
     * @return
     *     possible object is
     *     {@link TipoFileReportEnum }
     *     
     */
    public TipoFileReportEnum getTipoFileReport() {
        return tipoFileReport;
    }

    /**
     * Sets the value of the tipoFileReport property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoFileReportEnum }
     *     
     */
    public void setTipoFileReport(TipoFileReportEnum value) {
        this.tipoFileReport = value;
    }

}
