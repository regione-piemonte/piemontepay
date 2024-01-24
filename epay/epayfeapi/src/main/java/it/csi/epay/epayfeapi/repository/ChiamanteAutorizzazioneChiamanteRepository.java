/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import it.csi.epay.epayfeapi.entity.EpayRChiamanteAutorizzazioneChiamante;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class ChiamanteAutorizzazioneChiamanteRepository implements PanacheRepository<EpayRChiamanteAutorizzazioneChiamante> {

	public long countByCodiceChiamanteAndCodiceAutorizzazioneChiamante ( String codiceChiamante, String codiceAutorizzazioneChiamante ) {
		return count ( "id.codiceChiamante = ?1 and id.codiceAutorizzazioneChiamante = ?2 ", codiceChiamante, codiceAutorizzazioneChiamante );
	}
}
