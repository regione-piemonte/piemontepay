package it.csi.epay.epayfeapi.rest.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.exception.CartServiceException;
import it.csi.epay.epayfeapi.model.mdppagopacheckout.Cart;
import it.csi.epay.epayfeapi.model.mdppagopacheckout.Error;
import it.csi.epay.epayfeapi.model.mdppagopacheckout.Transaction;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;


@ApplicationScoped
public class TransactionAdapterClient extends ApiClient {

	@ConfigProperty ( name = "mdppagopacheckoutService.authusr" )
	String usr;

	@ConfigProperty ( name = "mdppagopacheckoutService.authpwd" )
	String pwd;

	public Transaction getTransaction ( Cart request, String url ) throws IOException, CartServiceException {
		var methodName = "[getTransaction] ";
		Log.infof ( "%sBEGIN", methodName );
		Log.infof ( "%sparam request:%s", methodName, request );
		Log.infof ( "%sparam url:%s", methodName, url );

		Transaction response;

		// connect
		var secret = String.format ( "%s:%s", usr, pwd );
		var urlConnection = super.getResponse ( url, request, secret, "POST" );

		var statusCode = urlConnection.getResponseCode ();
		Log.infof ( "%sstatusCode:%d", methodName, statusCode );

		var mapper = new ObjectMapper ();
		switch ( statusCode ) {
		case 200:
		case 201:
			response = mapper.readValue ( urlConnection.getInputStream (), Transaction.class );
			break;
		default:
			var error = mapper.readValue ( urlConnection.getErrorStream (), Error.class );
			var message = getMessage ( url, statusCode, error );
			Log.errorf ( "%s%s", methodName, message );
			throw new CartServiceException ( message );
		}

		Log.infof ( "%sEND", methodName );
		return response;
	}

	private static String getMessage ( String url, int statusCode, Error error ) {
		String message;
		switch ( statusCode ) {
		case 400:
			message = "Bad Request";
			break;
		case 401:
			message = "Unauthorized";
			break;
		case 403:
			message = "Forbidden";
			break;
		case 404:
			message = "Not Found";
			break;
		case 500:
			message = "Internal error";
			break;
		default: // Not Handled, es. 204 ...
			message = "Not Handled error";
			break;
		}
		message = String.format ( "%d %s -- Check request: %s -- %s", statusCode, message, url, error.getDetail () );
		return message;
	}
}
