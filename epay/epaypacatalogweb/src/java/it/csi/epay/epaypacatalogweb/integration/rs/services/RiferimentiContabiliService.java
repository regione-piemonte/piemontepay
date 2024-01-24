/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.integration.rs.services;

import it.csi.epay.epaypacatalogweb.integration.rs.VerificaNumeroRiferimentiContabiliInVitaPerCovInput;
import it.csi.epay.epaypacatalogweb.integration.rs.VerificaNumeroRiferimentiContabiliInVitaPerCovOutput;
import it.csi.epay.epaypacatalogweb.integration.rs.VerificaPresenzaRiferimentiContabiliInput;
import it.csi.epay.epaypacatalogweb.integration.rs.VerificaPresenzaRiferimentiContabiliOutput;

/**
 * Service per servizi di tassonomia
 */
public interface RiferimentiContabiliService {

	 public VerificaPresenzaRiferimentiContabiliOutput verificaPresenzaRiferimentiContabili( VerificaPresenzaRiferimentiContabiliInput input)  ;
	 
	 public VerificaNumeroRiferimentiContabiliInVitaPerCovOutput verificaNumeroRiferimentiContabiliInVitaPerCov( VerificaNumeroRiferimentiContabiliInVitaPerCovInput input)  ;

	
    

}
