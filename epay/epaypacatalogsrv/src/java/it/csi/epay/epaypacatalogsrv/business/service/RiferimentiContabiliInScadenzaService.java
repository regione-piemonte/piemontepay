/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.service;

import it.csi.epay.epaypacatalogsrv.dto.tassonomia.EntiRiferimentiContabiliInScadenzaOutput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.RiferimentiContabiliInScadenzaOutput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.RiferimentoContabileInScadenzaInput;


/**
 * Service per avere i riferimenti contabili in scadenza
 */
public interface RiferimentiContabiliInScadenzaService {

    EntiRiferimentiContabiliInScadenzaOutput getEntiRiferimentiContabiliInScadenza ();

    RiferimentiContabiliInScadenzaOutput getRiferimentiContabiliInScadenza ( RiferimentoContabileInScadenzaInput input );

}
