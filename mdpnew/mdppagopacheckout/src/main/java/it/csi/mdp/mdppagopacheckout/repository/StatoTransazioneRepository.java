/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import it.csi.mdp.mdppagopacheckout.entity.StatoTransazione;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class StatoTransazioneRepository implements PanacheRepository<StatoTransazione> {

}
