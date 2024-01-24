/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.decodifica;

import it.csi.epay.epaypacatalogsrv.dto.ParentInput;


public class GetOpzioniCodiceVersamentoInput extends ParentInput {

    private static final long serialVersionUID = 1L;

    private Boolean soloBase = false;

    public GetOpzioniCodiceVersamentoInput () {
        super ();
    }

    public Boolean getSoloBase () {
        return soloBase;
    }

    public void setSoloBase ( Boolean soloBase ) {
        this.soloBase = soloBase;
    }

}
