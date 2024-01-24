/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import it.csi.epay.epayfeapi.entity.EpayTPagamentoSecondarioComponenti;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;


@ApplicationScoped
public class PagamentoSecondarioComponentiRepository implements PanacheRepository<EpayTPagamentoSecondarioComponenti> {

	public List<EpayTPagamentoSecondarioComponenti> getPagamentoSecondarioComponentiById ( Long idPagamentoSecondario ) {
		return find ( "epayTPagamentoSecondario.idPagamentoSecondario", idPagamentoSecondario ).list ();
	}
}
