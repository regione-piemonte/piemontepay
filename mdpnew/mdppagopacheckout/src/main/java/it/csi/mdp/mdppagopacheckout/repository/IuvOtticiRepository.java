/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import it.csi.mdp.mdppagopacheckout.entity.IuvOttici;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;


@ApplicationScoped
public class IuvOtticiRepository implements PanacheRepository<IuvOttici> {

	public List<IuvOttici> getByIUV ( String iuv ) {
		return find ( "iuvOttico = ?1", iuv ).list ();
	}
}
