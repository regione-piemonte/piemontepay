/*
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 *
 * SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import it.csi.epay.epayfeapi.entity.EpayTRt;
import it.csi.epay.epayfeapi.entity.EpayTRtPdf;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class RtRepository implements PanacheRepository<EpayTRt> {

	public EpayTRt findByIdRegistroAndCodEsitoPagamento ( Long idRegistro, Integer codEsitoPagamento ) {
		return find ( "epayTRegistroVersamenti.idRegistro = ?1 and codEsitoPagamento = ?2", idRegistro, codEsitoPagamento ).firstResult ();
	}

	public EpayTRtPdf findPdfByIdRegistroAndCodEsitoPagamento ( Long idRegistro, Integer codEsitoPagamento ) {
		return find ( "epayTRegistroVersamenti.idRegistro = ?1 and codEsitoPagamento = ?2 and ricevutaPdf is not null", idRegistro,
						codEsitoPagamento ).project ( EpayTRtPdf.class ).firstResult ();
	}

	public EpayTRt findByIdRegistro ( Long idRegistro ) {
		return find ( "epayTRegistroVersamenti.idRegistro = ?1", idRegistro ).firstResult ();
	}
}
