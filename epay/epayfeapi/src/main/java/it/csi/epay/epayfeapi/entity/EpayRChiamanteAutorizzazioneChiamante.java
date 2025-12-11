/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table ( name = "epay_r_chiamante_autorizzazione_chiamante" )
@SuppressWarnings ( "unused" )
public class EpayRChiamanteAutorizzazioneChiamante implements Serializable {

	private static final long serialVersionUID = -450951795748806200L;

	@EmbeddedId
	private EpayRChiamanteAutorizzazioneChiamantePK id;

	public EpayRChiamanteAutorizzazioneChiamante () {
		id = new EpayRChiamanteAutorizzazioneChiamantePK ();
	}

	public EpayRChiamanteAutorizzazioneChiamantePK getId () {
		return id;
	}

	public void setId ( EpayRChiamanteAutorizzazioneChiamantePK id ) {
		this.id = id;
	}

	public String getCodiceChiamante () {
		return id != null ? id.getCodiceChiamante () : null;
	}

	public void setCodiceChiamante ( String codiceChiamante ) {
		id.setCodiceChiamante ( codiceChiamante );
	}

	public String getCodiceAutorizzazioneChiamante () {
		return id != null ? id.getCodiceAutorizzazioneChiamante () : null;
	}

	public void codiceAutorizzazioneChiamante ( String codiceAutorizzazioneChiamante ) {
		id.setCodiceAutorizzazioneChiamante ( codiceAutorizzazioneChiamante );
	}

	@Override
	public String toString () {
		return "{ id:" + id + " }";
	}
}
