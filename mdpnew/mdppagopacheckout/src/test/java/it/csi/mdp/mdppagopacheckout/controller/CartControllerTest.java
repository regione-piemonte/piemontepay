/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.controller;

import io.quarkus.logging.Log;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;

import static io.restassured.RestAssured.given;
import static it.csi.mdp.mdppagopacheckout.util.Constants.EMPTY_STRING;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.matchesPattern;


@QuarkusTest
@QuarkusTestResource ( H2DatabaseTestResource.class )
public class CartControllerTest extends BaseControllerTest {

	private final String USERNAME = "user1";

	private final String PASSWORD = "mypass1!";

	private final String BASEPATH = "/api/v1/";

	private final String PATH = BASEPATH + "transactions";

	private final String PAYLOAD_000 = "{\n" +
					"  \"emailNotice\": \"my_email@mail.it\",\n" +
					"  \"paymentNotices\": [\n" +
					"    {\n" +
					"      \"noticeNumber\": \"3" + IUV_001 + "\",\n" +
					"      \"amount\": 10000,\n" +
					"      \"description\": \"pagamento tassa A\",\n" +
					"      \"applicationId\": \"" + APPLICATION_ID_001 + "\"\n" +
					"    }\n" +
					"  ]\n" +
					"};";

	@SuppressWarnings ( "all" )
	private final String PAYLOAD_001 = "{\n" +
					"  \"emailNotice\": \"my_email@mail.it\",\n" +
					"  \"paymentNotices\": [\n" +
					"    {\n" +
					"      \"noticeNumber\": \"3" + IUV_002 + "\",\n" +
					"      \"amount\": 0.01,\n" +
					"      \"description\": \"pagamento tassa A\",\n" +
					"      \"applicationId\": \"" + APPLICATION_ID_002 + "\"\n" +
					"    }\n" +
					"  ]\n" +
					"};";

	@SuppressWarnings ( "all" )
	private final String PAYLOAD_002 = "{\n" +
					"  \"emailNotice\": \"my_email@mail.it\",\n" +
					"  \"paymentNotices\": [\n" +
					"    {\n" +
					"      \"noticeNumber\": \"3" + IUV_001 + "\",\n" +
					"      \"amount\": 0.00,\n" +
					"      \"description\": \"pagamento tassa A\",\n" +
					"      \"applicationId\": \"" + APPLICATION_ID_001 + "\"\n" +
					"    }\n" +
					"  ]\n" +
					"};";

	@Test
	public void h2initTest () {
		Log.info ( "H2 should be loaded" );
	}

	@Test
	@Transactional
	public void testTransactions201_ok_big_amount () {
		given ()
						.auth ().basic ( USERNAME, PASSWORD )
						.contentType ( ContentType.JSON )
						.accept ( ContentType.JSON )
						.body ( PAYLOAD_000 )
						.when ().post ( PATH )
						.then ()
						.statusCode ( 201 )
						.body ( matchesPattern ( "\\{\"idTransaction\":.*,\"paymentUrl\":.*" ) ); // Regular expression check
	}

	@Test
	@Transactional
	public void testTransactions201_ok_small_amount () {
		given ()
						.auth ().basic ( USERNAME, PASSWORD )
						.contentType ( ContentType.JSON )
						.accept ( ContentType.JSON )
						.body ( PAYLOAD_001 )
						.when ().post ( PATH )
						.then ()
						.statusCode ( 201 )
						.body ( matchesPattern ( "\\{\"idTransaction\":.*,\"paymentUrl\":.*" ) ); // Regular expression check
	}

	@Test
	public void testTransactions400_validation_error_no_payload () {
		given ()
						.auth ().basic ( USERNAME, PASSWORD )
						.contentType ( ContentType.JSON )
						.accept ( ContentType.JSON )
						.body ( EMPTY_STRING )
						.when ().post ( PATH )
						.then ()
						.statusCode ( 400 )
						.body ( is ( "{\"status\":\"400\",\"code\":\"VALIDATION_ERROR\",\"detail\":\"Errore per il valore: null: non deve essere null\"}" ) );
	}

	@Test
	public void testTransactions400_validation_error_amount_not_valid_zero () {
		given ()
						.auth ().basic ( USERNAME, PASSWORD )
						.contentType ( ContentType.JSON )
						.accept ( ContentType.JSON )
						.body ( PAYLOAD_002 )
						.when ().post ( PATH )
						.then ()
						.statusCode ( 400 );
	}

	@Test
	public void testTransactions401_unauthorized () {
		given ()
						.contentType ( ContentType.JSON )
						.accept ( ContentType.JSON )
						.body ( PAYLOAD_000 )
						.when ().post ( PATH )
						.then ()
						.statusCode ( 401 );
	}

	@Test
	public void testTransactions404_not_found () {
		given ()
						.auth ().basic ( USERNAME, PASSWORD )
						.contentType ( ContentType.JSON )
						.accept ( ContentType.JSON )
						.body ( PAYLOAD_000 )
						.when ().post ( BASEPATH + "cart" )
						.then ()
						.statusCode ( 404 );
	}
}
