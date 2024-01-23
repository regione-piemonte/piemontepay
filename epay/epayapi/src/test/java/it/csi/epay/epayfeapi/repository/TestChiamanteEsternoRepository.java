/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.repository;

import io.quarkus.arc.Priority;
import it.csi.epay.epayfeapi.entity.EpayDChiamanteEsterno;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.util.Date;


@Priority ( 1 )
@Alternative
@ApplicationScoped
public class TestChiamanteEsternoRepository extends ChiamanteEsternoRepository {

	@PostConstruct
	public void init () {
		EpayDChiamanteEsterno e1 = new EpayDChiamanteEsterno ();
		e1.setCodiceChiamante ( "codiceChiamante" );
		e1.setDescrizioneChiamante ( "descrizioneChiamante" );
		e1.setDataInizioValidita ( new Date () );
		e1.setDataFineValidita ( new Date () );
		e1.setPassphrase ( "123" );
		e1.setUrl ( "www.google.com" );
		EpayDChiamanteEsterno e2 = new EpayDChiamanteEsterno ();
		e2.setCodiceChiamante ( "codiceChiamante2" );
		e2.setDescrizioneChiamante ( "descrizioneChiamante2" );
		e2.setDataInizioValidita ( new Date () );
		e2.setDataFineValidita ( new Date () );
		e2.setPassphrase ( "1234" );
		e2.setUrl ( "www.google.com2" );
		persist ( e1, e2 );
	}
}
