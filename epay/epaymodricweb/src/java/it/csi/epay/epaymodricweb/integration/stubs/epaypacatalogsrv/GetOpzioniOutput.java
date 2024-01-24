/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodricweb.integration.stubs.epaypacatalogsrv;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getOpzioniOutput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getOpzioniOutput">
 *   &lt;complexContent>
 *     &lt;extension base="{http://interfacews.epaypacatalogsrv.epay.csi.it/}parentOutput">
 *       &lt;sequence>
 *         &lt;element name="opzioni" type="{http://interfacews.epaypacatalogsrv.epay.csi.it/}decodificaOutputDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getOpzioniOutput", propOrder = {
    "opzioni"
})
@XmlSeeAlso({
    GetOpzioniTematicaCleanOutput.class,
    GetOpzioniModalitaIntegrazioneOutput.class,
    GetOpzioniTipoPagamentoOutput.class,
    GetOpzioniTipologiaAccertamentoOutput.class,
    GetOpzioniTipologiaDatoSpecificoRiscossioneOutput.class,
    GetOpzioniModalitaAcquisizioneProvvisoriOutput.class,
    GetOpzioniTematicaOutput.class,
    GetOpzioniPeriodicitaSchedulazioneRiconciliazioneOutput.class,
    GetOpzioniCodiceVersamentoOutput.class,
    GetOpzioniStatoAggiornamentoOutput.class,
    GetOpzioniStatoAggiornamentoEnteOutput.class,
    GetOpzioniMacrotipoOutput.class
})
public class GetOpzioniOutput
    extends ParentOutput
{

    @XmlElement(nillable = true)
    protected List<DecodificaOutputDto> opzioni;

    /**
     * Gets the value of the opzioni property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the opzioni property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOpzioni().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DecodificaOutputDto }
     * 
     * 
     */
    public List<DecodificaOutputDto> getOpzioni() {
        if (opzioni == null) {
            opzioni = new ArrayList<DecodificaOutputDto>();
        }
        return this.opzioni;
    }

}
