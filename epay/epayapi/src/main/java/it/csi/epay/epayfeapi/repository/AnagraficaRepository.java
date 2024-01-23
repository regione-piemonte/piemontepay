/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import it.csi.epay.epayfeapi.entity.EpayTAnagrafica;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class AnagraficaRepository implements PanacheRepository<EpayTAnagrafica> {

}
