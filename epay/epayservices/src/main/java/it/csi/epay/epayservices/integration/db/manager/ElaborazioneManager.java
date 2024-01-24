/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.integration.db.manager;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import it.csi.epay.epayservices.integration.db.entities.EpayTElaborazione;
import it.csi.epay.epayservices.integration.db.entities.EpayTQuietanzaDaElaborare;
import it.csi.epay.epayservices.integration.db.entities.EpayTQuietanzaEsito;
import it.csi.epay.epayservices.integration.db.entities.EpayTRt;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.ElaborazioneDto;
import it.csi.epay.epayservices.model.QuietanzaDaElaborareDto;
import it.csi.epay.epayservices.model.StatoArchiviazione;


@Stateless ( name = "ElaborazioneManager", mappedName = "ElaborazioneManager" )
public class ElaborazioneManager extends _Manager {

	@PersistenceContext
	private EntityManager entityManager;

	@EJB
	private RtManager rtManager;

	@EJB
	private StatoArchiviazioneManager statoArchiviazioneManager;

	/** verifica se il pagatore ha dato il consenso, in questo caso, all'archiviazione della quietanza */
	public boolean existsConsensoPagatore ( Long idEnte, String codiceFiscalePagatore ) {
		String methodName = "existsConsensoPagatore";
		log.info ( methodName, "BEGIN" );
		log.info ( methodName, "idEnte:" + idEnte );
		log.info ( methodName, "codiceFiscalePagatore:" + codiceFiscalePagatore );

		boolean exists = false;
		try {
			StatoArchiviazione statoArchiviazione = statoArchiviazioneManager.findByIdEnteAndCF ( idEnte, codiceFiscalePagatore );
			exists = statoArchiviazione.getFlagAbilitaArchiviazione ();

		} catch ( NoDataException e ) {
			log.warn ( methodName, "NESSUN STATO DI ARCHIVIAZIONE TROVATO "
							+ "PER idEnte:" + idEnte + " E codiceFiscalePagatore:" + codiceFiscalePagatore + ", "
							+ "ASSUME CHE NON SIA STATO DATO IL CONSENSO ALL'ARCHIVIAZIONE" );

		} finally {
			log.info ( methodName, "exists:" + exists );
			log.info ( methodName, "END" );
		}
		return exists;
	}

	/** verifica se esiste gia una quietanza con IUV e codice Ipa tra quelle da elaborare, in questo caso, da archiviare */
	public boolean existsQuietanza ( String iuv, String codiceIpa ) {
		String methodName = "existsQuietanza";
		log.info ( methodName, "BEGIN" );
		log.info ( methodName, "iuv:" + iuv );
		log.info ( methodName, "codiceIpa:" + codiceIpa );

		boolean exists = false;
		try {
			TypedQuery<EpayTQuietanzaDaElaborare> query
				= entityManager.createNamedQuery ( "EpayTQuietanzaDaElaborare.findByIuvAndCodiceIpa", EpayTQuietanzaDaElaborare.class );
			query.setParameter ( "iuv", iuv );
			query.setParameter ( "codiceIpa", codiceIpa );
			query.getSingleResult ();
			exists = true;

		} catch ( NoResultException nre ) {
			log.warn ( methodName, "NESSUNA QUIETANZA DA ELABORARE TROVATA PER iuv:" + iuv + " E codiceIpa:" + codiceIpa + ", ASSUME CHE NON CI SIA" );

		} finally {
			log.info ( methodName, "exists:" + exists );
			log.info ( methodName, "END" );
		}
		return exists;
	}

	/** inserisce la quietanza tra quelle da elaborare, in questo caso, da archiviare */
	public Long inserisciQuietanza ( QuietanzaDaElaborareDto quietanza ) {
		String methodName = "inserisciQuietanza";
		log.info ( methodName, "BEGIN" );
		log.info ( methodName, "quietanza:" + quietanza );

		Long idQuietanzaInserita = null;
		try {
			EpayTQuietanzaDaElaborare tQuietanzaDaElaborare = map ( quietanza, EpayTQuietanzaDaElaborare.class );
			tQuietanzaDaElaborare.setIdQuietanzaDaElaborare ( null );
			if ( quietanza.getIdQuietanzaEsito () != null ) {
				EpayTQuietanzaEsito tQuietanzaEsito = new EpayTQuietanzaEsito ();
				tQuietanzaEsito.setIdQuietanzaEsito ( quietanza.getIdQuietanzaEsito () );
				tQuietanzaDaElaborare.setEpayTQuietanzaEsito ( tQuietanzaEsito );
			}
			if ( quietanza.getIdRt () != null ) {
				EpayTRt tRt = new EpayTRt ();
				tRt.setIdRt ( quietanza.getIdRt () );
				tQuietanzaDaElaborare.setEpayTRt ( tRt );
			}
			entityManager.persist ( tQuietanzaDaElaborare );

			idQuietanzaInserita = tQuietanzaDaElaborare.getIdQuietanzaDaElaborare ();

		} catch ( Exception e ) {
			log.warn ( methodName, "INSERIMENTO QUIETANZA DA ARCHIVIAZIONE FALLITO" );
			throw e;

		} finally {
			log.info ( methodName, "idQuietanzaInserita:" + idQuietanzaInserita );
			log.info ( methodName, "END" );
		}
		return idQuietanzaInserita;
	}

	public ElaborazioneDto saveElaborazione ( ElaborazioneDto elaborazioneDto ) {
		EpayTElaborazione entity = toEntity ( elaborazioneDto );
		entityManager.merge ( entity );
		elaborazioneDto.setIdElaborazione ( entity.getIdElaborazione () );
		return elaborazioneDto;
	}

	public EpayTElaborazione toEntity ( ElaborazioneDto dto ) {
		EpayTElaborazione entity = new EpayTElaborazione ();
		entity.setIdElaborazione ( dto.getIdElaborazione () );
		entity.setDataAvvio ( dto.getDataAvvio () );
		entity.setDataTermine ( dto.getDataTermine () );
		entity.setDocumentiElaborati ( dto.getDocumentiElaborati () );
		entity.setDocumentiArchiviati ( dto.getDocumentiArchiviati () );
		entity.setDocumentiNonArchiviati ( dto.getDocumentiNonArchiviati () );
		return entity;
	}

	public ElaborazioneDto toDto ( EpayTElaborazione entity ) {
		ElaborazioneDto dto = new ElaborazioneDto ();
		dto.setIdElaborazione ( entity.getIdElaborazione () );
		dto.setDataAvvio ( entity.getDataAvvio () );
		dto.setDataTermine ( entity.getDataTermine () );
		dto.setDocumentiElaborati ( entity.getDocumentiElaborati () );
		dto.setDocumentiArchiviati ( entity.getDocumentiArchiviati () );
		dto.setDocumentiNonArchiviati ( entity.getDocumentiNonArchiviati () );
		return dto;
	}

	public ElaborazioneDto createOrUpdate ( ElaborazioneDto elaborazioneDto ) {
		EpayTElaborazione entity = toEntity ( elaborazioneDto );
		return toDto ( entityManager.merge ( entity ) );
	}
}
