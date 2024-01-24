/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.service;

import it.csi.mdp.mdppagopacheckout.entity.Gde;
import it.csi.mdp.mdppagopacheckout.repository.GdeRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static it.csi.mdp.mdppagopacheckout.util.Constants.COMPONENTE;
import static it.csi.mdp.mdppagopacheckout.util.Constants.EMPTY_STRING;
import static it.csi.mdp.mdppagopacheckout.util.Constants.INTERFACCIA;
import static it.csi.mdp.mdppagopacheckout.util.Constants.KO;
import static it.csi.mdp.mdppagopacheckout.util.Constants.NOT_AVAILABLE;
import static it.csi.mdp.mdppagopacheckout.util.Constants.SOTTOTIPO_EVENTO_PAGOPA_CHECKOUT;
import static it.csi.mdp.mdppagopacheckout.util.Constants.TIPO_EVENTO_PAGOPA_CHECKOUT;


@ApplicationScoped
@Transactional
@SuppressWarnings ( "unused" )
public class GdeService {

	@Inject
	GdeRepository gdeRepository;

	public void trackKO ( String iuv, @NotNull String applicationId ) {
		var today = new Date ( System.currentTimeMillis () );

		var gde = Gde.builder ()
						.withInsertDate ( today )
						.withDataoraevento ( today )
						.withIdentificativodominio ( NOT_AVAILABLE )
						.withIuv ( iuv )
						.withCodiceContesto ( NOT_AVAILABLE )
						.withIdPsp ( NOT_AVAILABLE )
						.withTipoversamento ( NOT_AVAILABLE )
						.withComponente ( COMPONENTE )
						.withCategoriaevento ( INTERFACCIA )
						.withTipoevento ( TIPO_EVENTO_PAGOPA_CHECKOUT )
						.withSottotipoevento ( SOTTOTIPO_EVENTO_PAGOPA_CHECKOUT )
						.withIdIntPsp ( NOT_AVAILABLE )
						.withEsito ( KO )
						.withApplicationId ( applicationId )
						.withTransactionid ( EMPTY_STRING )
						.build ();
		gdeRepository.persist ( gde );
	}
}
