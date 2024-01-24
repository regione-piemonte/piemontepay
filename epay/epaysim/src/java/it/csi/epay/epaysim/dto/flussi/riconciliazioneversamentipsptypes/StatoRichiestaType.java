/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

//
// Questo file  stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andr persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.10.25 alle 05:45:36 PM CEST 
//


package it.csi.epay.epaysim.dto.flussi.riconciliazioneversamentipsptypes;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per StatoRichiestaType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="StatoRichiestaType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="IN_CORSO_DI_ACQUISIZIONE"/&gt;
 *     &lt;enumeration value="ERRORE_IN_FASE_DI_ACQUISIZIONE"/&gt;
 *     &lt;enumeration value="DA_ELABORARE"/&gt;
 *     &lt;enumeration value="ERRORE_IN_FASE_DI_ELABORAZIONE"/&gt;
 *     &lt;enumeration value="ELABORATA"/&gt;
 *     &lt;enumeration value="IN_CORSO_DI_ELABORAZIONE"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "StatoRichiestaType")
@XmlEnum
public enum StatoRichiestaType {

    IN_CORSO_DI_ACQUISIZIONE,
    ERRORE_IN_FASE_DI_ACQUISIZIONE,
    DA_ELABORARE,
    ERRORE_IN_FASE_DI_ELABORAZIONE,
    ELABORATA,
    IN_CORSO_DI_ELABORAZIONE;

    public String value() {
        return name();
    }

    public static StatoRichiestaType fromValue(String v) {
        return valueOf(v);
    }

}
