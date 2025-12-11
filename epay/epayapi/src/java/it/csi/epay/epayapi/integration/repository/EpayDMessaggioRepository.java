/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.integration.repository;

import org.springframework.stereotype.Repository;

import it.csi.epay.epayapi.integration.domain.EpayDMessaggio;


/**
 * Spring data Jpa repository for "EpayDMessaggio" <br>
 *
 * @author fabio.fenoglio
 */
@SuppressWarnings ( "unused" )
@Repository
public interface EpayDMessaggioRepository extends IRepository<EpayDMessaggio, String> {
}
