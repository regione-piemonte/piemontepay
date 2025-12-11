/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayapi.api;

import it.csi.epay.epayapi.dto.security.Scopes;
import it.csi.epay.epayapi.dto.security.Secured;
import it.csi.epay.epayapi.util.Constants;
import it.csi.epay.epayservices.model.AccessoChiamanteEsternoSincronoInput;
import it.csi.epay.epayservices.model.AggiornaPosizioneDebitoriaChiamanteEsternoInput;
import it.csi.epay.epayservices.model.AggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsternoInput;
import it.csi.epay.epayservices.model.GetIuvChiamanteEsternoInput;
import it.csi.epay.epayservices.model.GetIuvChiamanteEsternoInputContainer;
import it.csi.epay.epayservices.model.GetIuvMultibeneficiarioChiamanteEsternoInput;
import it.csi.epay.epayservices.model.GetIuvMultibeneficiarioChiamanteEsternoInputContainer;
import it.csi.epay.epayservices.model.GetRTChiamanteEsternoInput;
import it.csi.epay.epayservices.model.PagamentoIuvChiamanteEsternoInput;
import it.csi.epay.epayservices.model.PagamentoMarcaDaBolloChiamanteEsternoInput;
import org.jboss.resteasy.spi.validation.ValidateRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


/**
 * Risorsa RestEasy per il reperimento di informazioni sullo stato dell'applicativo
 */
@Path ( "/" )
@Consumes ( Constants.DEFAULT_PROTOCOL )
@Produces ( Constants.DEFAULT_PROTOCOL )
@ValidateRequest
public interface ChiamataEsternaSincronaApi {

	/**
	 * Ottiene informazioni sullo stato dell'applicativo potrebbe esporre dati sensibili
	 *
	 * @return response JSON
	 */
	@Secured ( Scopes.CREAZIONE_IUV )
	@POST
	@Path ( "/accessoChiamanteEsternoSincrono" )
	Response accessoChiamanteEsternoSincrono ( AccessoChiamanteEsternoSincronoInput input );

	/**
	 * Ottiene lo IUV associato dal pagamento
	 *
	 * @return response JSON
	 */
	@Secured ( Scopes.CREAZIONE_IUV )
	@POST
	@Path ( "/getIUVChiamanteEsterno" )
	Response getIUVChiamanteEsterno ( GetIuvChiamanteEsternoInput input );

	/**
	 * Ottiene lo IUV associato dal pagamento multibeneficiario
	 *
	 * @return response JSON
	 */
	@Secured ( Scopes.CREAZIONE_IUV_MULTIBEN )
	@POST
	@Path ( "/getIUVMultibeneficiarioChiamanteEsterno" )
	Response getIUVMultibeneficiarioChiamanteEsterno ( GetIuvMultibeneficiarioChiamanteEsternoInput input );

	/**
	 * Ottiene lo IUV associato dal pagamento
	 *
	 * @return response JSON
	 */
	@Secured ( Scopes.CREAZIONE_IUV )
	@POST
	@Path ( "/aggiornaPosizioneDebitoriaChiamanteEsterno" )
	Response aggiornaPosizioneDebitoriaChiamanteEsterno ( AggiornaPosizioneDebitoriaChiamanteEsternoInput input );

	/**
	 * Ottiene la lista degli IUV associati a una lista di pagamenti di input.
	 *
	 * @return response JSON
	 */
	@Secured ( Scopes.CREAZIONE_IUV )
	@POST
	@Path ( "/getListIUVChiamanteEsterno" )
	Response getListIUVChiamanteEsterno ( GetIuvChiamanteEsternoInputContainer input );

	/**
	 * Ottiene la lista degli IUV multibeneficiario associati a una lista di pagamenti di input.
	 *
	 * @return response JSON
	 */
	@Secured ( Scopes.CREAZIONE_IUV_MULTIBEN )
	@POST
	@Path ( "/getListIUVMultibeneficiarioChiamanteEsterno" )
	Response getListIUVMultibeneficiarioChiamanteEsterno ( GetIuvMultibeneficiarioChiamanteEsternoInputContainer input );

	/**
	 * Ottiene l'url del WISP da cui il cittadino puo' effettuare il pagamento
	 * <p>
	 * PAGAIUV
	 *
	 * @return response JSON
	 */
	@Secured ( Scopes.CREAZIONE_IUV )
	@POST
	@Path ( "/pagamentoIUV" )
	Response pagamentoIUVChiamanteEsterno ( PagamentoIuvChiamanteEsternoInput input );

	/**
	 * Effettua il pagamento della marca da bollo
	 *
	 * @return response JSON
	 */
	@Secured ( Scopes.CREAZIONE_IUV )
	@POST
	@Path ( "/pagamentoMarcaBollo" )
	Response pagamentoMarcaBolloChiamanteEsterno ( PagamentoMarcaDaBolloChiamanteEsternoInput input );

	/**
	 * Ottiene la stampa Rt
	 *
	 * @return response JSON
	 */
	@Secured ( Scopes.STAMPA_RT_GE )
	@POST
	@Path ( "/getRT" )
	Response getRTChiamanteEsterno ( GetRTChiamanteEsternoInput input );

	/**
	 * Aggiorna la posizione debitoria per multibeneficiario
	 *
	 * @return response JSON
	 */
	@Secured ( Scopes.CREAZIONE_IUV_MULTIBEN )
	@POST
	@Path ( "/aggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsterno" )
	Response aggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsterno ( AggiornaPosizioneDebitoriaMultibeneficiarioChiamanteEsternoInput input );

}
