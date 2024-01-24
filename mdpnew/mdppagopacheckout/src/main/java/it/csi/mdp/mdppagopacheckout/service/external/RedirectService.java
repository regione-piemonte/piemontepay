/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.service.external;

import io.quarkus.logging.Log;
import it.csi.mdp.mdppagopacheckout.service.ApplicationCustomFieldService;
import it.csi.mdp.mdppagopacheckout.service.ConfigService;
import it.csi.mdp.mdppagopacheckout.service.TransazioneService;
import it.csi.mdp.mdppagopacheckout.util.MacUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

import static it.csi.mdp.mdppagopacheckout.util.Constants.MDP_RETURN_URL_CANCEL;
import static it.csi.mdp.mdppagopacheckout.util.Constants.MDP_RETURN_URL_ERROR;
import static it.csi.mdp.mdppagopacheckout.util.Constants.MDP_RETURN_URL_OK;
import static it.csi.mdp.mdppagopacheckout.util.Constants.PASSPHRASE;
import static it.csi.mdp.mdppagopacheckout.util.Constants.Q_PARAM;
import static it.csi.mdp.mdppagopacheckout.util.Constants.RETURN_CANCEL_URL_FIELDNAME;
import static it.csi.mdp.mdppagopacheckout.util.Constants.RETURN_ERROR_URL_FIELDNAME;
import static it.csi.mdp.mdppagopacheckout.util.Constants.RETURN_OK_URL_FIELDNAME;
import static it.csi.mdp.mdppagopacheckout.util.ResponseUtil.generateMacError;
import static it.csi.mdp.mdppagopacheckout.util.ResponseUtil.generateNotFoundUrl;


@ApplicationScoped
@Transactional
public class RedirectService {

	@Inject
	TransazioneService transazioneService;

	@Inject
	ApplicationCustomFieldService applicationCustomFieldService;

	@Inject
	ConfigService configService;

	public Response redirectByTransactionId ( String transactionId, String mac, String urlFromConfigTableKey )
					throws URISyntaxException, NoSuchAlgorithmException {
		Log.info ( "Called method redirectByTransactionId" );

		// looking for the transaction
		var transazione = transazioneService.getByTransactionId ( transactionId );
		if ( null == transazione ) {
			Log.errorf ( "Transaction not found: %s. 400 error without details for security reasons", transactionId );
			return Response.status ( Response.Status.BAD_REQUEST ).build ();
		}
		var applicationId = transazione.getApplicationId ();
		Log.infof ( "Application id found for %s: %s", transactionId, applicationId );

		var applicationCustomField = applicationCustomFieldService.getByApplicationIdAndFieldName ( applicationId, urlFromConfigTableKey );
		if ( null == applicationCustomField ) {
			return generateNotFoundUrl ( applicationId, urlFromConfigTableKey );
		}

		// mac check
		var url = getUrlFromConfig ( urlFromConfigTableKey );
		var passphrase = applicationCustomFieldService.getByApplicationIdAndFieldName ( applicationId, PASSPHRASE );
		var macCalculated = MacUtil.generateMacPagopa ( passphrase.getFieldvalue (), transactionId, url, passphrase.getFieldvalue () );
		if ( !macCalculated.equals ( mac ) ) {
			return generateMacError ( macCalculated, mac );
		}

		var urlDecrypted = applicationCustomFieldService.decrypt ( applicationCustomField );
		var uri = new URI ( String.format ( "%s%s%s", urlDecrypted, Q_PARAM, transactionId ) );
		Log.infof ( "uri path: %s", uri.getPath () );
		// inserire qui eventuali funzioni per statistiche sui pagamenti

		Log.info ( "End method manageCart" );
		return Response.status ( Response.Status.FOUND ).location ( uri ).build ();
	}

	private String getUrlFromConfig ( String urlFromConfigTableKey ) {
		String url = null;
		switch ( urlFromConfigTableKey ) {
		case RETURN_OK_URL_FIELDNAME:
			var okUrl = configService.getConfigByKey ( MDP_RETURN_URL_OK );
			if ( null == okUrl ) {
				Log.errorf ( "Key %s not found! for %s", MDP_RETURN_URL_OK, urlFromConfigTableKey );
				break;
			}
			url = okUrl.getValue ();
			break;
		case RETURN_CANCEL_URL_FIELDNAME:
			var cancelUrl = configService.getConfigByKey ( MDP_RETURN_URL_CANCEL );
			if ( null == cancelUrl ) {
				Log.errorf ( "Key %s not found! for %s", MDP_RETURN_URL_CANCEL, urlFromConfigTableKey );
				break;
			}
			url = cancelUrl.getValue ();
			break;
		case RETURN_ERROR_URL_FIELDNAME:
			var errorUrl = configService.getConfigByKey ( MDP_RETURN_URL_ERROR );
			if ( null == errorUrl ) {
				Log.errorf ( "Key %s not found! for %s", MDP_RETURN_URL_ERROR, urlFromConfigTableKey );
				break;
			}
			url = errorUrl.getValue ();
			break;
		}
		return url;
	}
}
