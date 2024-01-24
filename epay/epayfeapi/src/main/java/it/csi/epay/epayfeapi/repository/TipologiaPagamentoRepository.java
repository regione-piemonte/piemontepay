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
		String methodName = "[findByCodice] ";
		Log.info ( methodName + "BEGIN" );
		Log.info ( methodName + "codice:" + codice );

		EpayDTipologiaPagamento result = find ( "codice", codice ).firstResult ();

		Log.info ( methodName + "result:" + result );
		Log.info ( methodName + "END" );
		return result;
	}
}
