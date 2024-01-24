/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.util.xsd.trasmissioneflussi;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per TipoRichiestaType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="TipoRichiestaType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="INSERISCI_LISTA_DI_CARICO"/>
 *     &lt;enumeration value="AGGIORNA_POSIZIONI_DEBITORIE"/>
 *     &lt;enumeration value="TRASMETTI_NOTIFICHE_PAGAMENTO"/>
 *     &lt;enumeration value="TRASMETTI_AVVISI_SCADUTI"/>
 *     &lt;enumeration value="TRASMETTI_FLUSSO_RENDICONTAZIONE"/>
 *     &lt;enumeration value="TRASMETTI_FLUSSO_RENDICONTAZIONE_ESTESO"/>
 *     &lt;enumeration value="TRASMETTI_FLUSSO_RENDICONTAZIONE_COMPLETO"/>
 *     &lt;enumeration value="TRASMETTI_RICHIESTE_DI_REVOCA"/>
 *     &lt;enumeration value="TRASMETTI_RT"/>
 *     &lt;enumeration value="TRASMETTI_RT_ESTESA"/>
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "TipoRichiestaType")
@XmlEnum
public enum TipoRichiestaType {

    INSERISCI_LISTA_DI_CARICO,
    AGGIORNA_POSIZIONI_DEBITORIE,
    TRASMETTI_NOTIFICHE_PAGAMENTO,
    TRASMETTI_AVVISI_SCADUTI,
    TRASMETTI_FLUSSO_RENDICONTAZIONE,
    TRASMETTI_FLUSSO_RENDICONTAZIONE_ESTESO,
    TRASMETTI_FLUSSO_RENDICONTAZIONE_COMPLETO,
    TRASMETTI_RICHIESTE_DI_REVOCA,
    TRASMETTI_RT,
    TRASMETTI_RT_ESTESA;

    public String value() {
        return name();
    }

    public static TipoRichiestaType fromValue(String v) {
        return valueOf(v);
    }

}
