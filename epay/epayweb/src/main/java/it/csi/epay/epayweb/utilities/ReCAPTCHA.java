/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayweb.utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ReCAPTCHA {

    // private final String secret = "secret=6Lec_AgUAAAAAKM56OBY09FONCcGRxoKLH6YWUhY";
    private final String secret = "secret=";

    // private final String urlVerifyCaptcha = "https://www.google.com/recaptcha/api/siteverify";
	private final String USER_AGENT = "Mozilla/5.0";

    @Value ( "${ReCAPTCHA.verify:false}" )
    private boolean flagReCAPTCHA;

    @Value ( "${ReCAPTCHA.proxy:true}" )
    private boolean flagReCAPTCHAProxy;

    @Value ( "${ReCAPTCHA.secret}" )
    private String reCaptchaSecret;

    @Value ( "${ReCAPTCHA.endpoint}" )
    private String reCaptchaEndpoint;

    protected LogUtil log = new LogUtil ( this.getClass () );

	public boolean verify(final String response) {

        String methodName = "verify";
        log.infoStart ( methodName );

        boolean result = false;
        String endpoint;
        boolean useProxy;

        if ( !result ) {
            endpoint = reCaptchaEndpoint.replace ( "http://", "https://" );
            useProxy = true && flagReCAPTCHAProxy;
            log.debug ( methodName, "Avvio verifica con endpoint " + endpoint + " con proxy " + useProxy );
            try {
                if ( this._verify ( response, endpoint, useProxy ) ) {
                    log.debug ( methodName, "Verifica riuscita" );
                    result = true;
                } else {
                    log.debug ( methodName, "Verifica fallita" );
                }
            } catch ( Exception e ) {
                log.warn ( methodName, "Errore durante la verifica", e );
            }
        }

        if ( !result ) {
            endpoint = reCaptchaEndpoint.replace ( "https://", "http://" );
            useProxy = true && flagReCAPTCHAProxy;
            log.debug ( methodName, "Avvio verifica con endpoint " + endpoint + " con proxy " + useProxy );
            try {
                if ( this._verify ( response, endpoint, useProxy ) ) {
                    log.debug ( methodName, "Verifica riuscita" );
                    result = true;
                } else {
                    log.debug ( methodName, "Verifica fallita" );
                }
            } catch ( Exception e ) {
                log.warn ( methodName, "Errore durante la verifica", e );
            }
        }

        if ( !result ) {
            endpoint = reCaptchaEndpoint.replace ( "https://", "http://" );
            useProxy = false;
            log.debug ( methodName, "Avvio verifica con endpoint " + endpoint + " con proxy " + useProxy );
            try {
                if ( this._verify ( response, endpoint, useProxy ) ) {
                    log.debug ( methodName, "Verifica riuscita" );
                    result = true;
                } else {
                    log.debug ( methodName, "Verifica fallita" );
                }
            } catch ( Exception e ) {
                log.warn ( methodName, "Errore durante la verifica", e );
            }
        }

        if ( !result ) {
            endpoint = reCaptchaEndpoint.replace ( "http://", "https://" );
            useProxy = false;
            log.debug ( methodName, "Avvio verifica con endpoint " + endpoint + " con proxy " + useProxy );
            try {
                if ( this._verify ( response, endpoint, useProxy ) ) {
                    log.debug ( methodName, "Verifica riuscita" );
                    result = true;
                } else {
                    log.debug ( methodName, "Verifica fallita" );
                }
            } catch ( Exception e ) {
                log.warn ( methodName, "Errore durante la verifica", e );
            }
        }

        log.debug ( methodName, "Risultato verifica = " + result );
        log.infoEnd ( methodName );
        return result;
    }

    private boolean _verify ( final String response, String endpoint, boolean useProxy ) {
        String methodName = "verify";

        log.debug ( methodName, "ReCAPTCHA.verify = " + flagReCAPTCHA );
        log.debug ( methodName, "ReCAPTCHA.secret = " + reCaptchaSecret );
        log.debug ( methodName, "ReCAPTCHA.endpoint = " + endpoint );
        log.debug ( methodName, "use proxy = " + useProxy );
        log.debug ( methodName, "input response = " + response );

		if (!flagReCAPTCHA) {
            log.debug ( methodName, "Verifica del CAPTCHA non abilitata. Verifica RIUSCITA" );
			return true;
		}

        if ( response == null || response.trim ().isEmpty () ) {
            log.debug ( methodName, "Risposta di verifica del CAPTCHA non presente. Verifica FALLITA" );
            return false;
        }

		try {
            URL url = new URL ( endpoint );
            String urlParameters = secret + reCaptchaSecret + "&response=" + response;

            HttpURLConnection httpsCon = null;

			if (useProxy) {
                log.debug ( methodName, "Verifica del CAPTCHA indirizzata su proxy.csi.it:3128" );

				System.setProperty("http.proxySet", "true");
                System.setProperty ( "http.proxyHost", "proxy-srv.csi.it" );
				System.setProperty("http.proxyPort", "3128");

				System.setProperty("https.proxySet", "true");
                System.setProperty ( "https.proxyHost", "proxy-srv.csi.it" );
				System.setProperty("https.proxyPort", "3128");
			}

            httpsCon = (HttpURLConnection) url.openConnection ();

			httpsCon.setRequestMethod("POST");
			httpsCon.setRequestProperty("User-Agent", USER_AGENT);
			httpsCon.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			httpsCon.setDoInput(true);
			httpsCon.setDoOutput(true);

			OutputStreamWriter wr = new OutputStreamWriter(httpsCon.getOutputStream());
			wr.write(urlParameters);
			wr.flush();
			wr.close();

			int responseCode = httpsCon.getResponseCode();
            log.debug ( methodName, "Response code verifica API : " + responseCode );

            BufferedReader in;
            boolean isError = ( responseCode >= 400 );

            if ( isError ) {
                in = new BufferedReader ( new InputStreamReader ( httpsCon.getErrorStream () ) );
            } else {
                in = new BufferedReader ( new InputStreamReader ( httpsCon.getInputStream () ) );
            }

            String httpResult = "";
            String line;
            while ( ( line = in.readLine () ) != null ) {
                httpResult += line + "\n";
            }

            log.debug ( methodName, "Risultato verifica API : \n" + httpResult );

            if ( isError ) {
                log.warn ( methodName, "Codice di risultato " + responseCode + ". Verifica FALLITA" );
                return false;

            } else {

                JsonObject jso = new Gson ().fromJson ( httpResult, JsonObject.class );
                boolean result = jso.get ( "success" ).getAsBoolean ();

                if ( result ) {
                    log.debug ( methodName, "Verifica RIUSCITA" );
                } else {
                    log.warn ( methodName, "Verifica FALLITA" );
                }
                return result;
            }

		} catch (Exception e) {
            log.error ( methodName, "Errore nella verifica del captcha tramite API. Verifica FALLITA", e );
			return false;
		}
	}

}
