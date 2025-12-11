/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.entity;

import javax.persistence.Column;
import java.io.Serializable;


@SuppressWarnings ( "unused" )
public class EpayRTipoPagamentoCollegatoKey implements Serializable {

	private static final long serialVersionUID = -8610096928339825730L;

	@Column ( name = "id_tipo_pagamento_principale", nullable = false )
	private Long idTipoPagamentoPrincipale;

	@Column ( name = "id_tipo_pagamento_secondario", nullable = false )
	private Long idTipoPagamentoSecondario;

	public EpayRTipoPagamentoCollegatoKey () {
		super ();
	}

	public EpayRTipoPagamentoCollegatoKey ( Long idTipoPagamentoPrincipale, Long idTipoPagamentoSecondario ) {
		super ();
		this.idTipoPagamentoPrincipale = idTipoPagamentoPrincipale;
		this.idTipoPagamentoSecondario = idTipoPagamentoSecondario;
	}

	public Long getIdTipoPagamentoPrincipale () {
		return idTipoPagamentoPrincipale;
	}

	public void setIdTipoPagamentoPrincipale ( Long idTipoPagamentoPrincipale ) {
		this.idTipoPagamentoPrincipale = idTipoPagamentoPrincipale;
	}

	public Long getIdTipoPagamentoSecondario () {
		return idTipoPagamentoSecondario;
	}

	public void setIdTipoPagamentoSecondario ( Long idTipoPagamentoSecondario ) {
		this.idTipoPagamentoSecondario = idTipoPagamentoSecondario;
	}

	public boolean equals ( Object obj ) {
		if ( this == obj ) {
			return true;
		}
		if ( obj == null ) {
			return false;
		}
		if ( this.getClass () != obj.getClass () ) {
			return false;
		}
		var other = (EpayRTipoPagamentoCollegatoKey) obj;
		if ( idTipoPagamentoPrincipale == null ) {
			if ( other.idTipoPagamentoPrincipale != null ) {
				return false;
			}
		} else if ( !idTipoPagamentoPrincipale.equals ( other.idTipoPagamentoPrincipale ) ) {
			return false;
		}
		if ( idTipoPagamentoSecondario == null ) {
			return other.idTipoPagamentoSecondario == null;
		} else {
			return idTipoPagamentoSecondario.equals ( other.idTipoPagamentoSecondario );
		}
	}

	@Override
	public int hashCode () {
		final var prime = 31;
		var result = 1;
		result = prime * result + ( ( idTipoPagamentoPrincipale == null ) ? 0 : idTipoPagamentoPrincipale.hashCode () );
		result = prime * result + ( ( idTipoPagamentoSecondario == null ) ? 0 : idTipoPagamentoSecondario.hashCode () );
		return result;
	}

	@Override
	public String toString () {
		return "{ " +
						"idTipoPagamentoPrincipale:" + idTipoPagamentoPrincipale +
						", idTipoPagamentoSecondario:" + idTipoPagamentoSecondario +
						" }";
	}
}
