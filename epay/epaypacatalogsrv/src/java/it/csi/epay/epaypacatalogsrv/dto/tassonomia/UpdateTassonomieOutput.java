/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.dto.tassonomia;

import it.csi.epay.epaypacatalogsrv.dto.ParentOutput;


public class UpdateTassonomieOutput extends ParentOutput {

    private static final long serialVersionUID = -1975448613824581204L;

    public static UpdateTassonomieOutput ok () {
		return ok ( UpdateTassonomieOutput.class );
    }
}
