/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import it.csi.epay.epayfeapi.entity.EpayTRegistroVersamenti;
import it.csi.epay.epayfeapi.entity.EpayTRegistroVersamentiReflection;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class RegistroVersamentiRepository implements PanacheRepository<EpayTRegistroVersamenti> {

	public EpayTRegistroVersamentiReflection findMaxIdByIdPagamentoAndStatoPagamento ( Long idPagamento, Integer idStatoPagamento, Sort sort ) {
		return find ( "epayTPagamento.idPagamento = ?1 and epayDStatoPagamento.idStato = ?2 ", sort, idPagamento, idStatoPagamento ).project (
						EpayTRegistroVersamentiReflection.class ).firstResult ();
	}

	public Long findMaxIdRegistro ( Long idPagamento ) {
		EpayTRegistroVersamentiReflection epayTRegistroVersamentiReflection =
						this.findMaxIdByIdPagamentoAndStatoPagamento ( idPagamento, 4, Sort.descending ( "idRegistro" ) );
		if ( null == epayTRegistroVersamentiReflection ) {
			return null;
		} else {
			return epayTRegistroVersamentiReflection.getIdRegistro ();
		}
	}
}
