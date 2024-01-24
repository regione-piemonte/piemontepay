/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.email;

import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;

public class ElaboraCodaEmailOutput extends ParentOutput {

	private static final long serialVersionUID = 1L;

    public static ElaboraCodaEmailOutput ok () {
        ElaboraCodaEmailOutput output = ok ( ElaboraCodaEmailOutput.class );
        return output;
    }

}
