/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.repository;

import it.csi.epay.epaymodric.business.epaymodric.model.CblDCategoriaIuv;
import it.csi.epay.epaymodric.business.epaymodric.repository.util.IRepository;


/**
 *
 */
public interface CategoriaIuvRepository extends IRepository<CblDCategoriaIuv, Long> {

    CblDCategoriaIuv findOneByCodice ( String codice );
}
