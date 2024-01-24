/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per eliminaCodiceVersamento complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="eliminaCodiceVersamento"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="eliminaCodiceVersamentoInput" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}eliminaCodiceVersamentoInput" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "eliminaCodiceVersamento", propOrder = {
    "eliminaCodiceVersamentoInput"
})
public class EliminaCodiceVersamento {

    protected EliminaCodiceVersamentoInput eliminaCodiceVersamentoInput;

    /**
     * Recupera il valore della propriet eliminaCodiceVersamentoInput.
     * 
     * @return
     *     possible object is
     *     {@link EliminaCodiceVersamentoInput }
     *     
     */
    public EliminaCodiceVersamentoInput getEliminaCodiceVersamentoInput() {
        return eliminaCodiceVersamentoInput;
    }

    /**
     * Imposta il valore della propriet eliminaCodiceVersamentoInput.
     * 
     * @param value
     *     allowed object is
     *     {@link EliminaCodiceVersamentoInput }
     *     
     */
    public void setEliminaCodiceVersamentoInput(EliminaCodiceVersamentoInput value) {
        this.eliminaCodiceVersamentoInput = value;
    }

}
