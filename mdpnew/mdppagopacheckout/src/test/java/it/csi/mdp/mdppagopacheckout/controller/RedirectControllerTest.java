/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.controller;

import io.quarkus.logging.Log;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;
import java.net.UnknownHostException;

import static io.restassured.RestAssured.given;
import static it.csi.mdp.mdppagopacheckout.util.Constants.Q_PARAM;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


@QuarkusTest
@QuarkusTestResource ( H2DatabaseTestResource.class )
public class RedirectControllerTest extends BaseControllerTest {

	private final String BASEPATH = "/api/landingpages/v1/";

	private final String URL_OK = BASEPATH + "urlpaymentok";

	private final String URL_ERROR = BASEPATH + "urlpaymenterror";

	private final String URL_CANCEL = BASEPATH + "urlpaymentcancel";

	private final String COMUNE = "www.comune.di.prova.it";

	@Test
	public void h2initTest () {
		Log.info ( "H2 should be loaded" );
	}

	@Test
	@Transactional
	public void testOkUrl_no_transactionId_ko () {
		given ()
						.when ().get ( URL_OK )
						.then ()
						.statusCode ( 400 );
	}

	@Test
	@Transactional
	public void testOkUrl_transactionId_hack_ko () {
		given ()
						.when ().get ( URL_OK + Q_PARAM + "<script>var</script>" )
						.then ()
						.statusCode ( 400 );
	}

	@Test
	@Transactional
	public void testOkUrl_transactionId_not_found_ko () {
		given ()
						.when ().get ( URL_OK + Q_PARAM + "TST000000001299625" )
						.then ()
						.statusCode ( 400 );
	}

	@Test
	@Transactional
	public void testOkUrl_ok () {
		try {
			given ()
							.when ().get ( URL_OK + Q_PARAM + TRANSACTION_ID_001 )
							.then ()
							.statusCode ( 302 ) // 302 indicates a redirect response
							.header ( "Location", "EXPECTED_REDIRECT_URL" );
			Log.info ( "Test passed for the expected redirect." );
			assertTrue ( true );
		} catch ( Exception e ) {
			if ( e instanceof UnknownHostException ) {
				if ( e.getMessage ().contains ( COMUNE ) ) {
					Log.info ( "ok" );
				} else {
					fail ( "Comune non gestito" );
				}
			} else {
				fail ( "Eccezione non gestita" );
			}
		}
	}

	@Test
	@Transactional
	public void testCancelUrl_no_transactionId_ko () {
		given ()
						.when ().get ( URL_CANCEL )
						.then ()
						.statusCode ( 400 );
	}

	@Test
	@Transactional
	public void testCancelUrl_transactionId_hack_ko () {
		given ()
						.when ().get ( URL_CANCEL + Q_PARAM + "<script>var</script>" )
						.then ()
						.statusCode ( 400 );
	}

	@Test
	@Transactional
	public void testCancelUrl_transactionId_not_found_ko () {
		given ()
						.when ().get ( URL_CANCEL + Q_PARAM + "TST000000001299625" )
						.then ()
						.statusCode ( 400 );
	}

	@Test
	@Transactional
	public void testCancelUrl_ok () {
		try {
			given ()
							.when ().get ( URL_CANCEL + Q_PARAM + TRANSACTION_ID_001 )
							.then ()
							.statusCode ( 302 ) // 302 indicates a redirect response
							.header ( "Location", "https://www.comune.di.prova.it/pagopa/cancel.html" + Q_PARAM + TRANSACTION_ID_001 );
			Log.info ( "Test passed for the expected redirect." );
			assertTrue ( true );
		} catch ( Exception e ) {
			if ( e instanceof UnknownHostException ) {
				if ( e.getMessage ().contains ( COMUNE ) ) {
					Log.info ( "ok" );
				} else {
					fail ( "Comune non gestito" );
				}
			} else {
				fail ( "Eccezione non gestita" );
			}
		}
	}

	@Test
	@Transactional
	public void testErrorUrl_no_transactionId_ko () {
		given ()
						.when ().get ( URL_ERROR )
						.then ()
						.statusCode ( 400 );
	}

	@Test
	@Transactional
	public void testErrorUrl_transactionId_hack_ko () {
		given ()
						.when ().get ( URL_ERROR + Q_PARAM + "<script>var</script>" )
						.then ()
						.statusCode ( 400 );
	}

	@Test
	@Transactional
	public void testErrorUrl_transactionId_not_found_ko () {
		given ()
						.when ().get ( URL_ERROR + Q_PARAM + "TST000000001299625" )
						.then ()
						.statusCode ( 400 );
	}

	@Test
	@Transactional
	public void testErrorUrl_ok () {
		try {
			given ()
							.when ().get ( URL_ERROR + Q_PARAM + TRANSACTION_ID_001 )
							.then ()
							.statusCode ( 302 ) // 302 indicates a redirect response
							.header ( "Location", "EXPECTED_REDIRECT_URL" );
			Log.info ( "Test passed for the expected redirect." );
			assertTrue ( true );
		} catch ( Exception e ) {
			if ( e instanceof UnknownHostException ) {
				if ( e.getMessage ().contains ( COMUNE ) ) {
					Log.info ( "ok" );
				} else {
					fail ( "Comune non gestito" );
				}
			} else {
				fail ( "Eccezione non gestita" );
			}
		}
	}
}
