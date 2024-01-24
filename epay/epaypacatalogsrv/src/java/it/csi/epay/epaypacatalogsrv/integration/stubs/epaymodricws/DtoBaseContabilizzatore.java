/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.integration.stubs.epaymodricws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per dtoBaseContabilizzatore complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="dtoBaseContabilizzatore"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="risposta" type="{http://epaymodric.interfacews.epaymodric.epay.csi.it/}dtoBaseResponse" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoBaseContabilizzatore", propOrder = {
    "risposta"
})
@XmlSeeAlso({
    DtpOutputWsElaborazione.class
})
public class DtoBaseContabilizzatore {

    protected DtoBaseResponse risposta;

    /**
     * Recupera il valore della proprieta risposta.
     * 
     * @return
     *     possible object is
     *     {@link DtoBaseResponse }
     *     
     */
    public DtoBaseResponse getRisposta() {
        return risposta;
    }

    /**
     * Imposta il valore della proprieta risposta.
     * 
     * @param value
     *     allowed object is
     *     {@link DtoBaseResponse }
     *     
     */
    public void setRisposta(DtoBaseResponse value) {
        this.risposta = value;
    }

}
