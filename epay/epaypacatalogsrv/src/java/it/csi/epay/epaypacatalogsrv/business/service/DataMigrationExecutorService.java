/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.service;

import it.csi.epay.epaypacatalogsrv.business.migrazione.MigrationContext;


/**
 *
 */

public interface DataMigrationExecutorService {

    MigrationContext eliminaOggettiMigrati ( MigrationContext context );

    MigrationContext eseguiMigrazione ( MigrationContext context );

    MigrationContext salvaLog ( MigrationContext context );

    MigrationContext eliminaLog ( MigrationContext context );
}
