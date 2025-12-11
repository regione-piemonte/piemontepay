/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayapi.api.v1;

import it.csi.epay.epayapi.dto.security.Scopes;
import it.csi.epay.epayapi.dto.security.Secured;
import it.csi.epay.epayapi.dto.v1.AggiornaPosizioneDebitoriaChiamanteEsternoInputDto;
import it.csi.epay.epayapi.dto.v1.AggiornaPosizioneDebitoriaMultiBeneficiarioChiamanteEsternoInputDto;
import it.csi.epay.epayapi.util.Constants;
import it.csi.epay.epayservices.model.GetIuvChiamanteEsternoInput;
import it.csi.epay.epayservices.model.GetRTChiamanteEsternoInput;
import it.csi.epay.epayservices.model.v1.AccessoChiamanteEsternoSincronoInput;
import it.csi.epay.epayservices.model.v1.GetIuvMultibeneficiarioChiamanteEsternoInput;
import it.csi.epay.epayservices.model.v1.PagamentoMarcaDaBolloChiamanteEsternoInput;
import org.jboss.resteasy.spi.validation.ValidateRequest;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


/**
 * Risorsa RestEasy per il reperimento di informazioni sullo stato dell'applicativo
 */
@Path ( "/v1" )
@Consumes ( Constants.DEFAULT_PROTOCOL )
@Produces ( Constants.DEFAULT_PROTOCOL )
@ValidateRequest
public interface ChiamataEsternaSincronaV1Api {

	/**
	 * Ottiene informazioni sullo stato dell'applicativo potrebbe esporre dati sensibili
	 *
	 * @return response JSON
	 */
	@Secured ( Scopes.CREAZIONE_IUV )
	@POST
	@Path ( "/organizations/{organization}/paymenttypes/{paymentType}/payment" )
	Response access (
					@PathParam ( "organization" ) @Pattern(regexp="[a-zA-Z0-9]+") @Size(min=11,max=16) String organization,
					@PathParam ( "paymentType" ) @Pattern(regexp="[a-zA-Z0-9][a-zA-Z0-9][a-zA-Z0-9][a-zA-Z0-9]")   String paymentType,
					AccessoChiamanteEsternoSincronoInput input );
	
	


	/**
	 * Ottiene lo IUV associato dal pagamento
	 *
	 * @return response JSON
	 */
	@Secured ( Scopes.CREAZIONE_IUV )
	@POST
	@Path ( "/organizations/{organization}/paymenttypes/{paymentType}/debtpositions" )
	Response getIUV (
					@PathParam ( "organization" )@Pattern(regexp="[a-zA-Z0-9]+") @Size(min=11,max=16) String organization,
					@PathParam ( "paymentType" )  @Pattern(regexp="[a-zA-Z0-9][a-zA-Z0-9][a-zA-Z0-9][a-zA-Z0-9]") String paymentType,
					GetIuvChiamanteEsternoInput input );

	/**
	 * Ottiene lo IUV associato dal pagamento multibeneficiario
	 *
	 * @return response JSON
	 */
	@Secured ( Scopes.CREAZIONE_IUV_MULTIBEN )
	@POST
	@Path ( "/organizations/{organization}/paymenttypes/{paymentType}/debtpositions/multipayees" )
	Response getIUVMultiPayee (
					@PathParam ( "organization" ) @Pattern(regexp="[a-zA-Z0-9]+") @Size(min=11,max=16) String organization,
					@PathParam ( "paymentType" ) @Pattern(regexp="[a-zA-Z0-9][a-zA-Z0-9][a-zA-Z0-9][a-zA-Z0-9]")  String paymentType,
					GetIuvMultibeneficiarioChiamanteEsternoInput input );

	/**
	 * Aggiorna una posizione debitoria
	 *
	 * @return response JSON
	 */
	@Secured ( Scopes.CREAZIONE_IUV )
	@PUT
	@Path ( "/organizations/{organization}/paymenttypes/{paymentType}/debtpositions/{iuv}" )
	Response updateDebtPosition (
					@PathParam ( "organization" ) @Pattern(regexp="[a-zA-Z0-9]+") @Size(min=11,max=16) String organization,
					@PathParam ( "paymentType" )  @Pattern(regexp="[a-zA-Z0-9][a-zA-Z0-9][a-zA-Z0-9][a-zA-Z0-9]") String paymentType,
					@PathParam ( "iuv" ) @Pattern(regexp="[0-9]+") @Size(min=17,max=35) String iuv,
					AggiornaPosizioneDebitoriaChiamanteEsternoInputDto input );

	/**
	 * Aggiorna una posizione debitoria multibeneficiario
	 *
	 * @return response JSON
	 */
	@Secured ( Scopes.CREAZIONE_IUV_MULTIBEN )
	@PUT
	@Path ( "/organizations/{organization}/paymenttypes/{paymentType}/debtpositions/{iuv}/multipayees" )
	Response updateDebtPositionMultypayee (
					@PathParam ( "organization" ) @Pattern(regexp="[a-zA-Z0-9]+") @Size(min=11,max=16) String organization,
					@PathParam ( "paymentType" )  @Pattern(regexp="[a-zA-Z0-9][a-zA-Z0-9][a-zA-Z0-9][a-zA-Z0-9]") String paymentType,
					@PathParam ( "iuv" ) @Pattern(regexp="[0-9]+") @Size(min=17,max=35)    String iuv,
					AggiornaPosizioneDebitoriaMultiBeneficiarioChiamanteEsternoInputDto input );

	/**
	 * Cancella una posizionie debitoria
	 *
	 * @return response JSON
	 */
	@Secured ( Scopes.CREAZIONE_IUV )
	@DELETE
	@Path ( "/organizations/{organization}/paymenttypes/{paymentType}/debtpositions/{iuv}" )
	Response deleteDebtPosition (
					@PathParam ( "organization" ) @Pattern(regexp="[a-zA-Z0-9]+") @Size(min=11,max=16) String organization,
					@PathParam ( "paymentType" ) @Pattern(regexp="[a-zA-Z0-9][a-zA-Z0-9][a-zA-Z0-9][a-zA-Z0-9]")  String paymentType,
					@PathParam ( "iuv" ) @Pattern(regexp="[0-9]+") @Size(min=17,max=35)    String iuv );

	/**
	 * Ottiene l'url del WISP da cui il cittadino puo' effettuare il pagamento
	 * <p>
	 * PAGAIUV
	 *
	 * @return response JSON
	 */
	@Secured ( Scopes.CREAZIONE_IUV )
	@POST
	@Path ( "/organizations/{organization}/debtpositions/{iuv}/payment" )
	Response getIUVPayment (
					@PathParam ( "organization" ) @Pattern(regexp="[a-zA-Z0-9]+") @Size(min=11,max=16) String organization,
					@PathParam ( "iuv" ) @Pattern(regexp="[0-9]+") @Size(min=17,max=35)    String iuv
	);

	/**
	 * Effettua il pagamento della marca da bollo
	 *
	 * @return response JSON
	 */
	@Secured ( Scopes.CREAZIONE_IUV )
	@POST
	@Path ( "/organizations/{organization}/paymenttypes/{paymentType}/stamptaxpayment" )
	Response stampTaxPayment (
					@PathParam ( "organization" ) @Pattern(regexp="[a-zA-Z0-9]+") @Size(min=11,max=16) String organization,
					@PathParam ( "paymentType" )  @Pattern(regexp="[a-zA-Z0-9][a-zA-Z0-9][a-zA-Z0-9][a-zA-Z0-9]") String paymentType,
					PagamentoMarcaDaBolloChiamanteEsternoInput input
	);

	/**
	 * Ottiene la stampa Rt
	 *
	 * @return response JSON
	 */
	@Secured ( Scopes.STAMPA_RT_GE )
	@POST
	@Path ( "/rt" )
	Response getRT ( GetRTChiamanteEsternoInput input );

	/**
	 * Ritorna lo stato di una posizione debitoria
	 *
	 * @return response JSON
	 */
	@Secured ( Scopes.RICHIESTA_STATO_POS_DEB ) // deve avere valore uguale a quelli presenti nella epay_r_chiamanta_autorizzazione_chiamante
	@GET
	@Path ( "/organizations/{organization}/paymenttypes/{paymentType}/debtpositions/{iuv}/status" )
	Response getStatoPosizioneDebitoria (
					@PathParam ( "organization" ) @Pattern(regexp="[a-zA-Z0-9]+", message = "Il codice fiscale contiene caratteri non consentiti" ) @Size(min=11,max=16) String organization,
					@PathParam ( "paymentType" ) @Pattern(regexp="[a-zA-Z0-9][a-zA-Z0-9][a-zA-Z0-9][a-zA-Z0-9]")  String paymentType,
					@PathParam ( "iuv" )@Pattern(regexp="[0-9]+") @Size(min=17,max=35)    String iuv );

	/**
	 * Ritorna le informazioni su un pagamento
	 *
	 * @return response JSON
	 */
	@Secured ( Scopes.RICHIESTA_DATI_PAGAMENTO ) // deve avere valore uguale a quelli presenti nella epay_r_chiamante_autorizzazione_chiamante
	@GET
	@Path ( "/organizations/{organization}/paymenttypes/{paymentType}/debtpositions/{iuv}/paymentdata" )
	Response getDatiPagamento (
					@PathParam ( "organization" )@Pattern(regexp="[a-zA-Z0-9]+") @Size(min=11,max=16)  String organization,
					@PathParam ( "paymentType" )  @Pattern(regexp="[a-zA-Z0-9][a-zA-Z0-9][a-zA-Z0-9][a-zA-Z0-9]")  String paymentType,
					@PathParam ( "iuv" ) @Pattern(regexp="[0-9]+") @Size(min=17,max=35)    String iuv );

	@Secured ( Scopes.STAMPA_AVVISO_PAGAMENTO )
	@GET
	@Path ( "/organizations/{organization}/paymenttypes/{paymentType}/debtpositions/{iuv}/paymentnotice" )
	Response getStampaAvvisoPagamento (
					@PathParam ( "organization" )@Pattern(regexp="[a-zA-Z0-9]+") @Size(min=11,max=16) String organization,
					@PathParam ( "paymentType" )  @Pattern(regexp="[a-zA-Z0-9][a-zA-Z0-9][a-zA-Z0-9][a-zA-Z0-9]") String paymentType,
					@PathParam ( "iuv" ) @Pattern(regexp="[0-9]+") @Size(min=17,max=35)    String iuv );
	
	@Secured ( Scopes.STAMPA_XML_MB )
    @GET
    @Path ( "/organizations/{organization}/paymenttypes/{paymentType}/debtpositions/{iuv}/stampTaxAttachment" )
    Response getStampTaxAttachment (
                    @PathParam ( "organization" )@Pattern(regexp="[a-zA-Z0-9]+") @Size(min=11,max=16) String organization,
                    @PathParam ( "paymentType" )  @Pattern(regexp="[a-zA-Z0-9][a-zA-Z0-9][a-zA-Z0-9][a-zA-Z0-9]") String paymentType,
                    @PathParam ( "iuv" ) @Pattern(regexp="[a-zA-Z0-9]+") @Size(min=17,max=35)    String iuv );
	
	
}
