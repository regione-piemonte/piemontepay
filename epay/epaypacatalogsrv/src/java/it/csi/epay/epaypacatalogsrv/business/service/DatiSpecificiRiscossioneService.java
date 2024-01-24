/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.service;

import it.csi.epay.epaypacatalogsrv.dto.tassonomia.DatiSpecificiRiscossioneInput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.DatiSpecificiRiscossioneOutput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.DatoSpecificoRiscossioneOutput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.DatoSpecificoRiscossioneInput;

public interface DatiSpecificiRiscossioneService {
	
	public DatoSpecificoRiscossioneOutput getDatoSpecificoRiscossione(DatoSpecificoRiscossioneInput input);
	
	public DatiSpecificiRiscossioneOutput getDatiSpecificiRiscossione(DatiSpecificiRiscossioneInput input);

}
