/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import it.csi.mdp.mdppagopacheckout.entity.Applicationcustomfield;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class ApplicationCustomFieldRepository implements PanacheRepository<Applicationcustomfield> {

	public Applicationcustomfield getByApplicationIdAndFieldName ( String applicationId, String fieldName ) {
		return find ( "application.id = ?1 and gateway.flagNodo is true and fieldname = ?2", applicationId, fieldName ).firstResult ();
	}
}
