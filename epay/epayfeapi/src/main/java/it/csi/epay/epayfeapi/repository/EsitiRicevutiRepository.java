/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import it.csi.epay.epayfeapi.entity.EpayTEsitiRicevuti;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class EsitiRicevutiRepository implements PanacheRepository<EpayTEsitiRicevuti> {

	public EpayTEsitiRicevuti findByIdRegistro ( Long idRegistro ) {
		return find ( "epayTRegistroVersamenti.idRegistro = ?1 ", idRegistro ).firstResult ();
	}

	public EpayTEsitiRicevuti findByIdRegistroAndIdQuietanzaNotNull ( Long idRegistro ) {
		return find ( "epayTRegistroVersamenti.idRegistro = ?1 and epayTQuietanzaEsito is not null and epayTQuietanzaEsito.ricevutaPdf is not null ",
						idRegistro ).firstResult ();
	}

}
