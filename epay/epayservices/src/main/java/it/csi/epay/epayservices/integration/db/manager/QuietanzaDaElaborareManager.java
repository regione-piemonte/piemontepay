/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.manager;

import it.csi.epay.epayservices.integration.db.entities.EpayTElaborazione;
import it.csi.epay.epayservices.integration.db.entities.EpayTQuietanzaDaElaborare;
import it.csi.epay.epayservices.model.ElaborazioneDto;
import it.csi.epay.epayservices.model.QuietanzaDaElaborareDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;


@Stateless ( name = "QuietanzaDaElaborareManager", mappedName = "QuietanzaDaElaborareManager" )
public class QuietanzaDaElaborareManager extends _Manager {

	@PersistenceContext
	private EntityManager entityManager;

	@EJB
	private QuietanzaEsitoManager quietanzaEsitoManager;

	@EJB
	private RtManager rtManager;

	@EJB
	private ElaborazioneManager elaborazioneManager;

	public List<QuietanzaDaElaborareDto> getListaQuietanzeDaElaborare ( int nrTentativiGiornalieri, int nrTotaleGiorniTentativi ) {
		try {
			TypedQuery<EpayTQuietanzaDaElaborare> query =
							entityManager.createNamedQuery ( "EpayTQuietanzaDaElaborare.getListaQuietanzeDaElaborare", EpayTQuietanzaDaElaborare.class );
			List<EpayTQuietanzaDaElaborare> epayTQuietanzaDaElaborareList = query.getResultList ();
			List<QuietanzaDaElaborareDto> quietanzaDaElaborareList = new ArrayList<> ();
			for ( EpayTQuietanzaDaElaborare q : epayTQuietanzaDaElaborareList ) {
				quietanzaDaElaborareList.add ( toDto ( q ) );
			}
			return quietanzaDaElaborareList;
		} catch ( NoResultException e ) {
			return null;
		}
	}

	private QuietanzaDaElaborareDto toDto ( EpayTQuietanzaDaElaborare entity ) {
		QuietanzaDaElaborareDto dto = new QuietanzaDaElaborareDto ();
		dto.setIdQuietanzaDaElaborare ( entity.getIdQuietanzaDaElaborare () );
		dto.setFruitoreEsterno ( entity.getFruitoreEsterno () );
		dto.setTipoElaborazione ( entity.getTipoElaborazione () );
		dto.setDataPrimaChiamata ( entity.getDataPrimaChiamata () );
		dto.setDataInserimento ( entity.getDataInserimento () );
		dto.setIdentificativoChiamata ( entity.getIdentificativoChiamata () );
		dto.setCodEsito ( entity.getCodEsito () );
		dto.setInviare ( entity.getInviare () );
		dto.setNrInvii ( entity.getNrInvii () );
		dto.setInvioFallitoGiornaliero ( entity.getInvioFallitoGiornaliero () );
		dto.setNrGiorni ( entity.getNrGiorni () );
		dto.setRicevutaPdf ( entity.getRicevutaPdf () );
		if ( null != entity.getEpayTQuietanzaEsito () ) {
			dto.setIdQuietanzaEsito ( entity.getEpayTQuietanzaEsito ().getIdQuietanzaEsito () );
		}
		if ( null != entity.getEpayTRt () ) {
			dto.setIdRt ( entity.getEpayTRt ().getIdRt () );
		}
		dto.setIuv ( entity.getIuv () );
		dto.setTerminiScaduti ( entity.getTerminiScaduti () );
		dto.setCausalePagamento ( entity.getCausalePagamento () );
		dto.setDataInizioPagamento ( entity.getDataInizioPagamento () );
		dto.setDataFinePagamento ( entity.getDataFinePagamento () );
		dto.setCfCittadino ( entity.getCfCittadino () );
		dto.setCodiceIpa ( entity.getCodiceIpa () );
		return dto;
	}

	public EpayTQuietanzaDaElaborare toEntity ( QuietanzaDaElaborareDto dto ) {
		EpayTQuietanzaDaElaborare entity = new EpayTQuietanzaDaElaborare ();
		entity.setIdQuietanzaDaElaborare ( dto.getIdQuietanzaDaElaborare () );
		entity.setFruitoreEsterno ( dto.getFruitoreEsterno () );
		entity.setTipoElaborazione ( dto.getTipoElaborazione () );
		entity.setDataPrimaChiamata ( dto.getDataPrimaChiamata () );
		entity.setDataInserimento ( dto.getDataInserimento () );
		entity.setIdentificativoChiamata ( dto.getIdentificativoChiamata () );
		entity.setCodEsito ( dto.getCodEsito () );
		entity.setInviare ( dto.getInviare () );
		entity.setNrInvii ( dto.getNrInvii () );
		entity.setInvioFallitoGiornaliero ( dto.getInvioFallitoGiornaliero () );
		entity.setNrGiorni ( dto.getNrGiorni () );
		entity.setRicevutaPdf ( dto.getRicevutaPdf () );
		if ( null != dto.getIdQuietanzaEsito () ) {
			entity.setEpayTQuietanzaEsito ( quietanzaEsitoManager.findById ( dto.getIdQuietanzaEsito () ) );
		}
		if ( null != dto.getIdRt () ) {
			entity.setEpayTRt ( rtManager.findById ( dto.getIdRt () ) );
		}
		entity.setIuv ( dto.getIuv () );
		entity.setTerminiScaduti ( dto.getTerminiScaduti () );
		entity.setCausalePagamento ( dto.getCausalePagamento () );
		entity.setDataInizioPagamento ( dto.getDataInizioPagamento () );
		entity.setDataFinePagamento ( dto.getDataFinePagamento () );
		entity.setCfCittadino ( dto.getCfCittadino () );
		entity.setCodiceIpa ( dto.getCodiceIpa () );
		return entity;
	}

	public ElaborazioneDto aggiornaQuietanzaDaElaborare ( QuietanzaDaElaborareDto q, ElaborazioneDto elaborazioneDto ) {
		EpayTQuietanzaDaElaborare epayTQuietanzaDaElaborare = toEntity ( q );
		EpayTElaborazione epayTElaborazione = elaborazioneManager.toEntity ( elaborazioneDto );
		List<EpayTElaborazione> list = new ArrayList<> ();
		list.add ( epayTElaborazione );
		epayTQuietanzaDaElaborare.setEpayTElaboraziones ( list );
		EpayTQuietanzaDaElaborare mergeResult = entityManager.merge ( epayTQuietanzaDaElaborare );
		return elaborazioneManager.toDto ( epayTElaborazione );
	}
}
