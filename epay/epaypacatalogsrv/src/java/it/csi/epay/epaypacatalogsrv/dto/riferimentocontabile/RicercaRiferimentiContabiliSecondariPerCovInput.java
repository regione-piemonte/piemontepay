/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile;

import it.csi.epay.epaypacatalogsrv.dto.ParentInput;


public class RicercaRiferimentiContabiliSecondariPerCovInput extends ParentInput {

    private static final long serialVersionUID = 1L;

    private Long idCodiceVersamento;


    public Long getIdCodiceVersamento () {
        return idCodiceVersamento;
    }

    public void setIdCodiceVersamento ( Long idCodiceVersamento ) {
        this.idCodiceVersamento = idCodiceVersamento;
    }

    
}
