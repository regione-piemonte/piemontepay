/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.repository;

import java.util.List;

import it.csi.epay.epaymodric.business.epaymodric.model.CblTCatalogoTemp;
import it.csi.epay.epaymodric.business.epaymodric.repository.util.IRepository;


public interface CatalogoTempRepository extends IRepository<CblTCatalogoTemp, Long> {

    CblTCatalogoTemp findOneByIdOperazione ( String idOperazione );

    List<CblTCatalogoTemp> findAllByIdOperazione ( String idOperazione );
}
