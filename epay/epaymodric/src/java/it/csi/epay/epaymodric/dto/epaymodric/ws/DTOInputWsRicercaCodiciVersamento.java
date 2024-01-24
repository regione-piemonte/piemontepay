/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.ws;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import it.csi.epay.epaymodric.dto.epaymodric.base.DTOInputBase;


@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoInputWsRicercaCodiciVersamento" )
public class DTOInputWsRicercaCodiciVersamento extends DTOInputBase {

    /**
     *
     */
    private static final long serialVersionUID = -2092980565865023818L;

    private List<String> codiciVersamento;

    /**
     * @return the codiciVersamento
     */
    public List<String> getCodiciVersamento () {
        return codiciVersamento;
    }

    /**
     * @param codiciVersamento the codiciVersamento to set
     */
    public void setCodiciVersamento ( List<String> codiciVersamento ) {
        this.codiciVersamento = codiciVersamento;
    }

}
