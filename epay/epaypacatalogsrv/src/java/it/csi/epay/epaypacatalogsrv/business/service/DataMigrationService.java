/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.service;

import it.csi.epay.epaypacatalogsrv.business.migrazione.MigrationContext;
import it.csi.epay.epaypacatalogsrv.dto.migrazione.EseguiMigrazioneInput;


/**
 *
 */

public interface DataMigrationService {

    MigrationContext runMigration ( EseguiMigrazioneInput input );

}
