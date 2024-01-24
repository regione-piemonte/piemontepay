/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per updateEsitoStatoElaborazioneFlusso complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="updateEsitoStatoElaborazioneFlusso"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="arg0" type="{http://epaysim.interfacews.epaysim.epay.csi.it/}updateEsitoStatoElaborazioneFlussoInputDTO" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateEsitoStatoElaborazioneFlusso", propOrder = {
    "arg0"
})
public class UpdateEsitoStatoElaborazioneFlusso {

    protected UpdateEsitoStatoElaborazioneFlussoInputDTO arg0;

    /**
     * Recupera il valore della propriet arg0.
     * 
     * @return
     *     possible object is
     *     {@link UpdateEsitoStatoElaborazioneFlussoInputDTO }
     *     
     */
    public UpdateEsitoStatoElaborazioneFlussoInputDTO getArg0() {
        return arg0;
    }

    /**
     * Imposta il valore della propriet arg0.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateEsitoStatoElaborazioneFlussoInputDTO }
     *     
     */
    public void setArg0(UpdateEsitoStatoElaborazioneFlussoInputDTO value) {
        this.arg0 = value;
    }

}
