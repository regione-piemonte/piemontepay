/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.manager;

import it.csi.epay.epayservices.integration.db.entities.EpayTChiamataEsternaNonValida;
import it.csi.epay.epayservices.model.v1.TracciabilitaChiamataEsternaNonValida;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless ( name = "ChiamataEsternaNonValidaManager", mappedName = "ChiamataEsternaNonValidaManager" )
public class ChiamataEsternaNonValidaManager extends _Manager {

	@PersistenceContext
	private EntityManager entityManager;

	public Long inserisci ( TracciabilitaChiamataEsternaNonValida tracciabilitaChiamataEsternaNonValida ) {
		EpayTChiamataEsternaNonValida epayTChiamataEsternaNonValida = new EpayTChiamataEsternaNonValida ();
		epayTChiamataEsternaNonValida.setCodiceChiamante ( tracciabilitaChiamataEsternaNonValida.getCodiceChiamante () );
		epayTChiamataEsternaNonValida.setCodiceFiscale ( tracciabilitaChiamataEsternaNonValida.getCodiceFiscale () );
		epayTChiamataEsternaNonValida.setTimestampChiamata ( tracciabilitaChiamataEsternaNonValida.getTimestampChiamata () );
		epayTChiamataEsternaNonValida.setRemoteHost ( tracciabilitaChiamataEsternaNonValida.getRemoteHost () );
		epayTChiamataEsternaNonValida.setDescrizioneErrore ( tracciabilitaChiamataEsternaNonValida.getDescrizioneErrore () );
		epayTChiamataEsternaNonValida.setIdentificativoPagamento ( tracciabilitaChiamataEsternaNonValida.getIdentificativoPagamento () );
		epayTChiamataEsternaNonValida.setIuv ( tracciabilitaChiamataEsternaNonValida.getIuv () );
		entityManager.persist ( epayTChiamataEsternaNonValida );
		entityManager.flush ();
		return epayTChiamataEsternaNonValida.getIdChiamata ();
	}
}
