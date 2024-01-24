/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.integration.rs.services;

import it.csi.epay.epaypacatalogweb.integration.rs.ElencoTassonomiaPerCodiceTipoEnteCreditoreInput;
import it.csi.epay.epaypacatalogweb.integration.rs.ElencoTassonomiaPerCodiceTipoEnteCreditoreOutput;
import it.csi.epay.epaypacatalogweb.integration.rs.RicercaTassonomiaInput;
import it.csi.epay.epaypacatalogweb.integration.rs.RicercaTassonomiaOutput;

/**
 * Service per servizi di tassonomia
 */
public interface TassonomiaService {

	ElencoTassonomiaPerCodiceTipoEnteCreditoreOutput getElencoTassonomiaPerCodiceTipoEnteCreditore (ElencoTassonomiaPerCodiceTipoEnteCreditoreInput input);
	
	RicercaTassonomiaOutput ricercaTassonomia (RicercaTassonomiaInput input);
	

	
    

}
