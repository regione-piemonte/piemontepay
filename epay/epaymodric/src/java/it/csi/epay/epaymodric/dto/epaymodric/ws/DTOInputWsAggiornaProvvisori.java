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
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOProvvisorio;


@XmlAccessorType ( XmlAccessType.PROPERTY )
@XmlType ( name = "dtoInputWsAggiornaProvvisori" )
public class DTOInputWsAggiornaProvvisori extends DTOInputBase {

    private static final long serialVersionUID = 2158554345569096657L;

    private List<DTOProvvisorio> dtoProvvisorioList;

    private String identificativoEnte;

    private Boolean nonBatch;

    public DTOInputWsAggiornaProvvisori () {
    }

    public List<DTOProvvisorio> getDtoProvvisorioList () {
        return dtoProvvisorioList;
    }

    public void setDtoProvvisorioList ( List<DTOProvvisorio> dtoProvvisorioList ) {
        this.dtoProvvisorioList = dtoProvvisorioList;
    }

    public String getIdentificativoEnte () {
        return identificativoEnte;
    }

    public void setIdentificativoEnte ( String identificativoEnte ) {
        this.identificativoEnte = identificativoEnte;
    }

    public Boolean getNonBatch () {
        return nonBatch;
    }

    public void setNonBatch ( Boolean nonBatch ) {
        this.nonBatch = nonBatch;
    }

}
