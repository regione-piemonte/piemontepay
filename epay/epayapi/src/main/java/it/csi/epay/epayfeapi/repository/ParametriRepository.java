/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import it.csi.epay.epayfeapi.entity.EpayTParametri;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;


@ApplicationScoped
public class ParametriRepository implements PanacheRepository<EpayTParametri> {

	public List<EpayTParametri> findByGruppo ( String gruppo ) {
		return find ( "id.gruppo = ?1 ", gruppo ).list ();

	}

	public EpayTParametri getParametro ( String gruppo, String codice ) {
		return find ( "id.gruppo = ?1 and id.codice = ?2", gruppo, codice ).firstResult ();

	}

}
