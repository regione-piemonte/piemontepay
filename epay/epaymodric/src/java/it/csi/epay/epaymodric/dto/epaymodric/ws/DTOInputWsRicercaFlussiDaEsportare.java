/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.dto.epaymodric.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import it.csi.epay.epaymodric.interfacews.epaymodric.enums.TipoFileReportEnum;


// classe che contiene i dati di input da mandare al webservice

@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoInputWsRicercaFlussiDaEsportare" )
public class DTOInputWsRicercaFlussiDaEsportare extends DTOInputWsRicercaFlussoOrigine {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private TipoFileReportEnum tipoFileReport;
    /**
     * @return the tipoFileReport
     */
    public TipoFileReportEnum getTipoFileReport () {
        return tipoFileReport;
    }
    /**
     * @param tipoFileReport the tipoFileReport to set
     */
    public void setTipoFileReport ( TipoFileReportEnum tipoFileReport ) {
        this.tipoFileReport = tipoFileReport;
    }

}
