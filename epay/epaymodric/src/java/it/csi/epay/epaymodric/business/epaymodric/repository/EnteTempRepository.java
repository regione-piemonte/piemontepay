/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.repository;

import it.csi.epay.epaymodric.business.epaymodric.model.CblTEnteTemp;
import it.csi.epay.epaymodric.business.epaymodric.repository.util.IRepository;


public interface EnteTempRepository extends IRepository<CblTEnteTemp, Long> {

    CblTEnteTemp findOneByIdOperazione ( String idOperazione );
}
