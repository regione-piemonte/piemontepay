/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.repository;

import it.csi.epay.epaymodric.business.epaymodric.model.CblDTipoAcquisizione;
import it.csi.epay.epaymodric.business.epaymodric.repository.util.IRepository;


/**
 *
 */
public interface TipoAcquisizioneRepository extends IRepository<CblDTipoAcquisizione, Long> {

    CblDTipoAcquisizione findOneByCodice ( String codice );
}
