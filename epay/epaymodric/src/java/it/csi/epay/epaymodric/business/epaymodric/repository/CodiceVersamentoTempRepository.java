/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.repository;

import java.util.List;

import it.csi.epay.epaymodric.business.epaymodric.model.CblTCodiceVersamentoTemp;
import it.csi.epay.epaymodric.business.epaymodric.repository.util.IRepository;


public interface CodiceVersamentoTempRepository extends IRepository<CblTCodiceVersamentoTemp, Long> {

    List<CblTCodiceVersamentoTemp> findAllByIdOperazione ( String idOperazione );

    CblTCodiceVersamentoTemp findOneByIdOperazione ( String idOperazione );
}
