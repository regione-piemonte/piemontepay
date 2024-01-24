/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypaweb.facade.sincronizzapagamentipec.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "pagamenti"
})
@XmlRootElement ( name = "SincronizzaPagamentiRequest" )
public class SincronizzaPagamentiRequest {

    @XmlElement ( name = "Pagamento" )
    protected List<SincronizzaPagamento> pagamenti;

    public List<SincronizzaPagamento> getPagamenti () {
        if ( pagamenti == null ) {
            pagamenti = new ArrayList<> ();
        }
        return pagamenti;
    }

}
