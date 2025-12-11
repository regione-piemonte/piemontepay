/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.entity.EpayDTipologiaPagamento;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class TipologiaPagamentoRepository implements PanacheRepository<EpayDTipologiaPagamento> {

	public EpayDTipologiaPagamento findByCodice ( String codice ) {
		var methodName = "[findByCodice] ";
		Log.infof ( "%scodice:%s", methodName, codice );

		var result = find ( "codice", codice ).firstResult ();

		Log.infof ( "%sresult:%s", methodName, result );
		return result;
	}
}
