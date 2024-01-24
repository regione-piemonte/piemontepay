/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.service;

import it.csi.mdp.mdppagopacheckout.entity.IuvOttici;
import it.csi.mdp.mdppagopacheckout.repository.IuvOtticiRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;


@SuppressWarnings ( "unused" )
@ApplicationScoped
@Transactional
public class IuvOtticiService {

	@Inject
	IuvOtticiRepository iuvOtticiRepository;

	public List<IuvOttici> getByIUV ( String iuv ) {
		return iuvOtticiRepository.getByIUV ( iuv );
	}

	/*
	 * SE NON INIZIA PER 0 E PER 3 -> NON E' UNO IUV STACCATO DAL NOSTRO SISTEMA
	 * SE INIZIA CON 0, SUBSTRING DA 3 IN AVANTI
	 * SE INIZIA CON 3, SUBSTRING DA 1 IN AVANTI
	 * SE INIZIA CON ALTRO NUMERO, NESSUN SUBSTRING
	 */
	public String getIuvFromNoticeNumber ( String noticeNumber ) {
		var startingChar = noticeNumber.charAt ( 0 );
		switch ( startingChar ) {
		case '0':
			return noticeNumber.substring ( 3 );
		case '3':
			return noticeNumber.substring ( 1 );
		default:
			return noticeNumber;
		}
	}
}
