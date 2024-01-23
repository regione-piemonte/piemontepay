/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
@SuppressWarnings ( "unused" )
public class EpayRChiamanteAutorizzazioneChiamantePK implements Serializable {

	private static final long serialVersionUID = -5939663828429996399L;

	@Column ( name = "codice_chiamante" )
	private String codiceChiamante;

	@Column ( name = "codice_autorizzazione_chiamante" )
	private String codiceAutorizzazioneChiamante;

	public String getCodiceChiamante () {
		return codiceChiamante;
	}

	public void setCodiceChiamante ( String codiceChiamante ) {
		this.codiceChiamante = codiceChiamante;
	}

	public String getCodiceAutorizzazioneChiamante () {
		return codiceAutorizzazioneChiamante;
	}

	public void setCodiceAutorizzazioneChiamante ( String codiceAutorizzazioneChiamante ) {
		this.codiceAutorizzazioneChiamante = codiceAutorizzazioneChiamante;
	}

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( codiceChiamante == null ) ? 0 : codiceChiamante.hashCode () );
		result = prime * result + ( ( codiceAutorizzazioneChiamante == null ) ? 0 : codiceAutorizzazioneChiamante.hashCode () );
		return result;
	}

	@Override
	public boolean equals ( Object obj ) {
		if ( this == obj ) {
			return true;
		}
		if ( obj == null ) {
			return false;
		}
		if ( getClass () != obj.getClass () ) {
			return false;
		}
		EpayRChiamanteAutorizzazioneChiamantePK other = (EpayRChiamanteAutorizzazioneChiamantePK) obj;
		if ( codiceChiamante == null ) {
			if ( other.codiceChiamante != null ) {
				return false;
			}
		} else if ( !codiceChiamante.equals ( other.codiceChiamante ) ) {
			return false;
		}
		if ( codiceAutorizzazioneChiamante == null ) {
			return other.codiceAutorizzazioneChiamante == null;
		} else {
			return codiceAutorizzazioneChiamante.equals ( other.codiceAutorizzazioneChiamante );
		}
	}

	@Override
	public String toString () {
		return "{ " +
			"codiceChiamante:" + codiceChiamante +
			", codiceAutorizzazioneChiamante:" + codiceAutorizzazioneChiamante +
			" }";
	}
}
