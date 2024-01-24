/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaysimweb.integration.stubs.epaypacatalogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per getCodiceVersamentoOutput complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="getCodiceVersamentoOutput"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://interfacews.epaypacatalogsrv.epay.csi.it/}parentOutput"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="codiceVersamento" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}getCodiceVersamentoOutputDto" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
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
     * Recupera il valore della propriet codiceVersamento.
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
     * Imposta il valore della propriet codiceVersamento.
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
