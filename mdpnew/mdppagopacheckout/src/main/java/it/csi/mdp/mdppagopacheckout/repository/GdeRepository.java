/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import it.csi.mdp.mdppagopacheckout.entity.Gde;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class GdeRepository implements PanacheRepository<Gde> {

}
