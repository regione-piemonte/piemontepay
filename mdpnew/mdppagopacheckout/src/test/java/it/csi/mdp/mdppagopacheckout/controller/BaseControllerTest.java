/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopacheckout.controller;

import io.quarkus.logging.Log;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


@SuppressWarnings ( "ALL" )
@QuarkusTest
@QuarkusTestResource ( H2DatabaseTestResource.class )
public abstract class BaseControllerTest {

	public final String TRANSACTION_ID_001 = "TST000000001299626";

	public final String TRANSACTION_ID_002 = "TST000000001299627";

	public final String APPLICATION_ID_001 = "EPAY_80087670016_24";

	public final String APPLICATION_ID_002 = "EPAY_80087670016_25";

	public final String IUV_001 = "24231721863283967";

	public final String IUV_002 = "24231721863283968";

	public final String CONFIG_URL = "https://tst-mdp.piemontepay.it/mdppagopacheckout/";

	@PersistenceContext
	EntityManager entityManager;

	@BeforeEach
	@Transactional
	public void setupDatabase () {
		Log.info ( "Filling in memory database data..." );
		try {
			// application table
			var query =
							"INSERT INTO application (id, applicationname, referentecsi, cliente, progetto, note, esercemail, numeroverde, valido_dal, valido_al, redirect_newmdp) VALUES ('" + APPLICATION_ID_001 + "', 'EPAY Regione Piemonte 24', 'MARIA ROSARIA NAPOLETANO', 'Regione Piemonte', 'EPAY', null, 'assistenza.epay@csi.it', '               ', '2019-11-13 18:14:00.598000', null, 0);";
			entityManager.createNativeQuery ( query ).executeUpdate ();
			query =
							"INSERT INTO application (id, applicationname, referentecsi, cliente, progetto, note, esercemail, numeroverde, valido_dal, valido_al, redirect_newmdp) VALUES ('" + APPLICATION_ID_002 + "', 'EPAY Regione Piemonte 24', 'MARIA ROSARIA NAPOLETANO', 'Regione Piemonte', 'EPAY', null, 'assistenza.epay@csi.it', '               ', '2019-11-13 18:14:00.598000', null, 0);";
			entityManager.createNativeQuery ( query ).executeUpdate ();
			// enti table
			query =
							"INSERT INTO enti (ente_id, partita_iva, descrizione, attivo, codice_segregazione, flag_invio_flusso_base, flag_invio_flusso_esteso, progressivo_application_id) VALUES ('0003', '80087670016', 'Regione Piemonte', '1', '', true, true, 50);";
			entityManager.createNativeQuery ( query ).executeUpdate ();
			// iuv_ottici table
			query =
							"INSERT INTO iuv_ottici (id, data_creazione, iuv_ottico, iuv_standard, ente_id, cod_versamento, application_id, data_riconciliazione) VALUES (19654334, '2023-06-21 17:55:31.342462', '" + IUV_001 + "', '', '0003', 'DF50', '" + APPLICATION_ID_001 + "', null);";
			entityManager.createNativeQuery ( query ).executeUpdate ();
			query =
							"INSERT INTO iuv_ottici (id, data_creazione, iuv_ottico, iuv_standard, ente_id, cod_versamento, application_id, data_riconciliazione) VALUES (19654335, '2023-06-21 17:55:31.342463', '" + IUV_002 + "', '', '0003', 'DF50', '" + APPLICATION_ID_002 + "', null);";
			entityManager.createNativeQuery ( query ).executeUpdate ();
			// gateway table
			query =
							"INSERT INTO gateway (gateway_id, gateway_description, gateway_provider, valido_dal, valido_al, gatewayservicename, flag_nodo) VALUES ('E319020F-10E4-4D95-A338-B0AC49F3AD4A', 'Nodo dei Pagamenti SPC v.2', 'NodoSPC v.2', null, null, 'mdppaymentsrv/ejb/NodoSpcAdapter2', true);";
			entityManager.createNativeQuery ( query ).executeUpdate ();
			// applicationcustomfields table
			query =
							"INSERT INTO applicationcustomfields (keyid, applicationid, fieldname, fieldvalue, gateway_id, fielddescription) VALUES (1, '" + APPLICATION_ID_001 + "', 'returnOkUrl', 'r5o6GNTpHTvKZAQQmi5PjMD2hYyDBP4WL/O2flmHGrIUB7TGO+R50M3PLlhBLRpGsyBA+GiNKp1cU0H7UnhwTQ==', 'E319020F-10E4-4D95-A338-B0AC49F3AD4A', 'nodospc v2');";
			entityManager.createNativeQuery ( query ).executeUpdate ();
			query =
							"INSERT INTO applicationcustomfields (keyid, applicationid, fieldname, fieldvalue, gateway_id, fielddescription) VALUES (2, '" + APPLICATION_ID_001 + "', 'returnCancelUrl', 'r5o6GNTpHTvKZAQQmi5PjMD2hYyDBP4WL/O2flmHGrKBZVQ5i/Mb3XrVeYpio0iQuCJvnXCs4m5/ZgPanthkyg==', 'E319020F-10E4-4D95-A338-B0AC49F3AD4A', 'nodospc v2');";
			entityManager.createNativeQuery ( query ).executeUpdate ();
			query =
							"INSERT INTO applicationcustomfields (keyid, applicationid, fieldname, fieldvalue, gateway_id, fielddescription) VALUES (3, '" + APPLICATION_ID_001 + "', 'returnErrorUrl', 'r5o6GNTpHTvKZAQQmi5PjMD2hYyDBP4WL/O2flmHGrIarbZwYbPIkVrdibxJLLRL4hFfOevFx80xoRKtt8pQaQ==', 'E319020F-10E4-4D95-A338-B0AC49F3AD4A', 'nodospc v2');";
			entityManager.createNativeQuery ( query ).executeUpdate ();
			query =
							"INSERT INTO applicationcustomfields (keyid, applicationid, fieldname, fieldvalue, gateway_id, fielddescription) VALUES (4, '" + APPLICATION_ID_001 + "', 'contoPoste', '3QKY+NXaNvLw0ByiEOi28A==', 'E319020F-10E4-4D95-A338-B0AC49F3AD4A', 'nodospc v2');";
			entityManager.createNativeQuery ( query ).executeUpdate ();
			query =
							"INSERT INTO applicationcustomfields (keyid, applicationid, fieldname, fieldvalue, gateway_id, fielddescription) VALUES (5, '" + APPLICATION_ID_001 + "', 'identificativoDominio', 'rayt0CG0+mjtcxM3YKZ1fA==', 'E319020F-10E4-4D95-A338-B0AC49F3AD4A', 'nodospc v2')";
			entityManager.createNativeQuery ( query ).executeUpdate ();
			query =
							"INSERT INTO applicationcustomfields (keyid, applicationid, fieldname, fieldvalue, gateway_id, fielddescription) VALUES (6, '" + APPLICATION_ID_001 + "', 'codiceIdentificativoUnivocoBeneficiario', 'rayt0CG0+mjtcxM3YKZ1fA==', 'E319020F-10E4-4D95-A338-B0AC49F3AD4A', 'nodospc v2')";
			entityManager.createNativeQuery ( query ).executeUpdate ();
			query =
							"INSERT INTO applicationcustomfields (keyid, applicationid, fieldname, fieldvalue, gateway_id, fielddescription) VALUES (7, '" + APPLICATION_ID_001 + "', 'denominazioneBeneficiario', 'OR5ASTLevnjZ6spNSoQYAuIRXznrxcfNMaESrbfKUGk=', 'E319020F-10E4-4D95-A338-B0AC49F3AD4A', 'nodospc v2')";
			entityManager.createNativeQuery ( query ).executeUpdate ();
			query =
							"INSERT INTO applicationcustomfields (keyid, applicationid, fieldname, fieldvalue, gateway_id, fielddescription) VALUES (8, '" + APPLICATION_ID_002 + "', 'returnOkUrl', 'r5o6GNTpHTvKZAQQmi5PjMD2hYyDBP4WL/O2flmHGrIUB7TGO+R50M3PLlhBLRpGsyBA+GiNKp1cU0H7UnhwTQ==', 'E319020F-10E4-4D95-A338-B0AC49F3AD4A', 'nodospc v2');";
			entityManager.createNativeQuery ( query ).executeUpdate ();
			query =
							"INSERT INTO applicationcustomfields (keyid, applicationid, fieldname, fieldvalue, gateway_id, fielddescription) VALUES (9, '" + APPLICATION_ID_002 + "', 'returnCancelUrl', 'r5o6GNTpHTvKZAQQmi5PjMD2hYyDBP4WL/O2flmHGrKBZVQ5i/Mb3XrVeYpio0iQuCJvnXCs4m5/ZgPanthkyg==', 'E319020F-10E4-4D95-A338-B0AC49F3AD4A', 'nodospc v2');";
			entityManager.createNativeQuery ( query ).executeUpdate ();
			query =
							"INSERT INTO applicationcustomfields (keyid, applicationid, fieldname, fieldvalue, gateway_id, fielddescription) VALUES (10, '" + APPLICATION_ID_002 + "', 'returnErrorUrl', 'r5o6GNTpHTvKZAQQmi5PjMD2hYyDBP4WL/O2flmHGrIarbZwYbPIkVrdibxJLLRL4hFfOevFx80xoRKtt8pQaQ==', 'E319020F-10E4-4D95-A338-B0AC49F3AD4A', 'nodospc v2');";
			entityManager.createNativeQuery ( query ).executeUpdate ();
			query =
							"INSERT INTO applicationcustomfields (keyid, applicationid, fieldname, fieldvalue, gateway_id, fielddescription) VALUES (11, '" + APPLICATION_ID_002 + "', 'contoPoste', '3QKY+NXaNvLw0ByiEOi28A==', 'E319020F-10E4-4D95-A338-B0AC49F3AD4A', 'nodospc v2');";
			entityManager.createNativeQuery ( query ).executeUpdate ();
			query =
							"INSERT INTO applicationcustomfields (keyid, applicationid, fieldname, fieldvalue, gateway_id, fielddescription) VALUES (12, '" + APPLICATION_ID_002 + "', 'identificativoDominio', 'rayt0CG0+mjtcxM3YKZ1fA==', 'E319020F-10E4-4D95-A338-B0AC49F3AD4A', 'nodospc v2')";
			entityManager.createNativeQuery ( query ).executeUpdate ();
			query =
							"INSERT INTO applicationcustomfields (keyid, applicationid, fieldname, fieldvalue, gateway_id, fielddescription) VALUES (13, '" + APPLICATION_ID_002 + "', 'codiceIdentificativoUnivocoBeneficiario', 'rayt0CG0+mjtcxM3YKZ1fA==', 'E319020F-10E4-4D95-A338-B0AC49F3AD4A', 'nodospc v2')";
			entityManager.createNativeQuery ( query ).executeUpdate ();
			query =
							"INSERT INTO applicationcustomfields (keyid, applicationid, fieldname, fieldvalue, gateway_id, fielddescription) VALUES (14, '" + APPLICATION_ID_002 + "', 'denominazioneBeneficiario', 'OR5ASTLevnjZ6spNSoQYAuIRXznrxcfNMaESrbfKUGk=', 'E319020F-10E4-4D95-A338-B0AC49F3AD4A', 'nodospc v2')";
			entityManager.createNativeQuery ( query ).executeUpdate ();
			// stato_transazione table
			query = "INSERT INTO stato_transazione (descrizione, cod_stato, descrizioneestesa) VALUES ('Initialized', 1, null);";
			entityManager.createNativeQuery ( query ).executeUpdate ();
			// transazione table
			query = "CREATE sequence seq_transazione start with 21;";
			entityManager.createNativeQuery ( query ).executeUpdate ();
			query =
							"INSERT INTO transazione (transaction_id, application_id, language, ccy, buyer_email, basket_id, cod_stato, commissioni_applicate, init_date, start_date, finish_date, gateway_id, gatewaypaymodeid, amount, mscsorderid, merchant_id, pgresultcode, providertimestamp, authornumber, opernumber, rispcomp, errcode, buyername, buyercodfisc, oldstate, changestatedate, userhaschange, clientipaddress, incassokoerrormessage, intestatariocc, paymentid, payurl) VALUES ('" + TRANSACTION_ID_001 + "', '" + APPLICATION_ID_001 + "', 'ITA', null, null, null, 1, null, '2023-07-20 16:38:48.311000', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null)";
			entityManager.createNativeQuery ( query ).executeUpdate ();
			query =
							"INSERT INTO transazione (transaction_id, application_id, language, ccy, buyer_email, basket_id, cod_stato, commissioni_applicate, init_date, start_date, finish_date, gateway_id, gatewaypaymodeid, amount, mscsorderid, merchant_id, pgresultcode, providertimestamp, authornumber, opernumber, rispcomp, errcode, buyername, buyercodfisc, oldstate, changestatedate, userhaschange, clientipaddress, incassokoerrormessage, intestatariocc, paymentid, payurl) VALUES ('" + TRANSACTION_ID_002 + "', '" + APPLICATION_ID_002 + "', 'ITA', null, null, null, 1, null, '2023-07-20 16:38:48.311000', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null)";
			entityManager.createNativeQuery ( query ).executeUpdate ();
			// config table
			query = "DROP TABLE IF EXISTS config;";
			entityManager.createNativeQuery ( query ).executeUpdate ();
			query = "CREATE TABLE config (\"key\" VARCHAR(150) NOT NULL PRIMARY KEY, \"value\" VARCHAR(1000), descrizione VARCHAR(5000));";
			entityManager.createNativeQuery ( query ).executeUpdate ();
			query =
							"INSERT INTO config (\"key\", \"value\", descrizione) VALUES ('mdp.return.url.ok', '" + CONFIG_URL + "api/landingpages/v1/urlpaymentok', 'ok url di test');";
			entityManager.createNativeQuery ( query ).executeUpdate ();
			query =
							"INSERT INTO config (\"key\", \"value\", descrizione) VALUES ('mdp.return.url.cancel', '" + CONFIG_URL + "api/landingpages/v1/urlpaymentcancel', 'cancel url di test');";
			entityManager.createNativeQuery ( query ).executeUpdate ();
			query =
							"INSERT INTO config (\"key\", \"value\", descrizione) VALUES ('mdp.return.url.error', '" + CONFIG_URL + "api/landingpages/v1/urlpaymenterror', 'error url di test');";
			entityManager.createNativeQuery ( query ).executeUpdate ();
		} catch ( Exception e ) {
			Log.info ( "Filling in memory database data [FAILURE]" );
			Log.info ( "Error inside setupDatabase(), you can ignore it if you know why it's happening (e.i.: duplicate key on failure test is ok)" );
		}
		Log.info ( "Filling in memory database data [DONE]" );
	}
}
