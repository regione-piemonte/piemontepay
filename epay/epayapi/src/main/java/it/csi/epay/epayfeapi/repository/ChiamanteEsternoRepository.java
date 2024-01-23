/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import it.csi.epay.epayfeapi.entity.EpayDChiamanteEsterno;

import javax.enterprise.context.ApplicationScoped;
import java.util.Date;


@ApplicationScoped
public class ChiamanteEsternoRepository implements PanacheRepository<EpayDChiamanteEsterno> {

	public EpayDChiamanteEsterno findByCodiceChiamante ( String codiceChiamante ) {
		return find (
						"codiceChiamante = ?1 and dataInizioValidita <= ?2 and ((dataFineValidita > ?2) or dataFineValidita is null)",
						codiceChiamante, new Date () ).firstResult ();
	}
}
