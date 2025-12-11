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
import java.util.List;


@ApplicationScoped
public class RegistroVersamentiRepository implements PanacheRepository<EpayTRegistroVersamenti> {

	public EpayTRegistroVersamentiReflection findMaxIdByIdPagamentoAndStatoPagamento ( Long idPagamento, Integer idStatoPagamento, Sort sort ) {
		return find ( "epayTPagamento.idPagamento = ?1 and epayDStatoPagamento.idStato = ?2 ", sort, idPagamento, idStatoPagamento ).project (
						EpayTRegistroVersamentiReflection.class ).firstResult ();
	}

	public EpayTRegistroVersamentiReflection findMaxByIdPagamentoAndStatoPagamentoAndOrigine ( Long idPagamento, Integer idStatoPagamento, List<String>   origineInserimento,  Sort sort ) {
		return find ( "epayTPagamento.idPagamento = ?1 and epayDStatoPagamento.idStato = ?2 and origineInserimento in (?3)", sort, idPagamento, idStatoPagamento, origineInserimento ).project (
						EpayTRegistroVersamentiReflection.class ).firstResult ();
	}

	public Long findMaxIdRegistro ( Long idPagamento ) {
		var epayTRegistroVersamentiReflection = this.findMaxIdByIdPagamentoAndStatoPagamento ( idPagamento, 4, Sort.descending ( "idRegistro" ) );
		if ( null == epayTRegistroVersamentiReflection ) {
			return null;
		} else {
			return epayTRegistroVersamentiReflection.getIdRegistro ();
		}
	}

	public Long findMaxIdRegistroPagatoByIdPagamentoAndOrigine ( Long idPagamento , List<String> origineInserimento) {
		var epayTRegistroVersamentiReflection = this.findMaxByIdPagamentoAndStatoPagamentoAndOrigine ( idPagamento, 4,origineInserimento,  Sort.descending ( "idRegistro" ) );
		if ( null == epayTRegistroVersamentiReflection ) {
			return null;
		} else {
			return epayTRegistroVersamentiReflection.getIdRegistro ();
		}
	}

	public EpayTRegistroVersamenti ricercaUltimoByIdPagamentoIdTransazioneEIdStato ( Long idPagamento, Integer idStatoPagamento, String idTransazione ) {
		return find ( "epayTPagamento.idPagamento = ?1 and epayDStatoPagamento.idStato = ?2 and epayTTransazioneMdp.idTransazione = ?3 " +
										"and origineInserimento IN ('MDPSERVICES-riceviRT','MDPSERVICES-riceviEsito') order by idRegistro desc",
						idPagamento, idStatoPagamento, idTransazione
		).firstResult ();
	}
}
