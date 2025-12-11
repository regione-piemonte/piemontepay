/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epayapi.business.impl;

import it.csi.epay.epayapi.business.EpayServicesClientService;
import it.csi.epay.epayapi.testbed.config.UnitTestDB;
import it.csi.epay.epayapi.testbed.model.ParentHttpCallTest;
import it.csi.epay.epayservices.model.*;
import it.csi.epay.epayservices.model.v1.GetDatiPagamentoChiamanteEsternoInput;
import it.csi.epay.epayservices.model.v1.GetDatiPagamentoChiamanteEsternoOutput;
import it.csi.epay.epayservices.model.v1.GetStatoPosizioneDebitoriaInput;
import it.csi.epay.epayservices.model.v1.GetStatoPosizioneDebitoriaOutput;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


/**
 *
 */
@RunWith ( SpringJUnit4ClassRunner.class )
@ContextConfiguration ( classes = { UnitTestDB.class } )
@Transactional
public class EpayServicesClientServiceImplTest extends ParentHttpCallTest {

	private final static BigDecimal importo = new BigDecimal ( "100.000" );

	private final static String codiceChiamante = "fnarouser";

	private final static String codiceFiscaleEnte = "80087670016";

	private final Timestamp today = new Timestamp ( 20160930 );

	@Autowired
	private EpayServicesClientService service;

	@Autowired
	private it.csi.epay.epayapi.business.v1.EpayServicesClientService epayServicesClientService;

	@Test
	public void shouldExist () {
		assertNotNull ( service );
	}

	/**
	 * Test di chiamata al servizio  con input null{@getIUVChiamanteEsterno}
	 */

	@Test
	public void getIUVChiamanteEsternoNullInputTest () {
		GetIuvChiamanteEsternoInput input = Mockito.mock ( GetIuvChiamanteEsternoInput.class );

		GetIuvChiamanteEsternoOutput out = service.getIUVChiamanteEsterno ( input );

		assertEquals ( "100", out.getCodiceEsito () );
	}

	/**
	 * Test di chiamata al servizio  con input validi{@getIUVChiamanteEsterno}
	 */
	@Test
	public void getIUVChiamanteEsternoValidInputTest () {

		GetIuvChiamanteEsternoInput input = generaInputMock ();
		GetIuvChiamanteEsternoOutput out = service.getIUVChiamanteEsterno ( input );

		assertEquals ( "000", out.getCodiceEsito () );
	}

	/**
	 * Test di chiamata al servizio  con input null {@getPagamentoIUVChiamanteEsterno}
	 */

	@Test
	public void getPagamentoIUVChiamanteEsternoNullInputTest () {

		PagamentoIuvChiamanteEsternoInput input = Mockito.mock ( PagamentoIuvChiamanteEsternoInput.class );
		PagamentoIuvChiamanteEsternoOutput out = service.getPagamentoIUVChiamanteEsterno ( input );

		assertEquals ( "100", out.getCodiceEsito () );
	}

	/**
	 * Test di chiamata al servizio con input null{@getIUVMultibeneficiarioChiamanteEsterno}
	 */

	@Test
	public void getIUVMultibeneficiarioChiamanteEsternoNullInputTest () {
		GetIuvMultibeneficiarioChiamanteEsternoInput input = Mockito.mock ( GetIuvMultibeneficiarioChiamanteEsternoInput.class );

		GetIuvMultibeneficiarioChiamanteEsternoOutput out = service.getIUVMultibeneficiarioChiamanteEsterno ( input );

		assertEquals ( "100", out.getCodiceEsito () );
	}

	/**
	 * Test di chiamata al servizio con input validi{@getIUVMultibeneficiarioChiamanteEsterno}
	 */
	@Test
	public void getIUVMultibeneficiarioChiamanteEsternoValidInputTest () {

		GetIuvMultibeneficiarioChiamanteEsternoInput input = generaMultibeneficiarioInputMock ();
		GetIuvMultibeneficiarioChiamanteEsternoOutput out = service.getIUVMultibeneficiarioChiamanteEsterno ( input );

		assertEquals ( "000", out.getCodiceEsito () );
	}

	/**
	 * Test di chiamata al servizio  con input validi {@getPagamentoIUVChiamanteEsterno}
	 */
	@Test
	public void getPagamentoIUVChiamanteEsternoValidInput () {

		PagamentoIuvChiamanteEsternoInput input = generaMockPagamentoInput ();
		PagamentoIuvChiamanteEsternoOutput out = service.getPagamentoIUVChiamanteEsterno ( input );

		assertEquals ( "000", out.getCodiceEsito () );

	}

	private GetIuvChiamanteEsternoInput generaInputMock () {

		AccessoChiamanteEsternoSincronoComponentePagamentoDSRInput componentiPagamento = new AccessoChiamanteEsternoSincronoComponentePagamentoDSRInput ();
		GetIuvChiamanteEsternoInput input = new GetIuvChiamanteEsternoInput ();
		impostaComponenti ( componentiPagamento );

		List<AccessoChiamanteEsternoSincronoComponentePagamentoDSRInput> compPagamento = new ArrayList<> ();
		compPagamento.add ( componentiPagamento );
		input.setCodiceChiamante ( codiceChiamante );
		input.setIpChiamante ( "TEST" );
		input.setCodiceFiscaleEnte ( codiceFiscaleEnte );
		input.setCausale ( "test" );
		input.setTipoPagamento ( "AAAF" );
		input.setImporto ( importo );
		input.setNome ( "tester" );
		input.setCognome ( "tester" );
		input.setRagioneSociale ( "test" );
		input.setCodiceFiscalePartitaIVAPagatore ( "test" );
		input.setEmail ( "test" );
		input.setComponentiPagamento ( compPagamento );

		return input;

	}

	private GetIuvMultibeneficiarioChiamanteEsternoInput generaMultibeneficiarioInputMock () {

		GetIuvMultibeneficiarioChiamanteEsternoInput input = new GetIuvMultibeneficiarioChiamanteEsternoInput ();
		AccessoChiamanteEsternoSincronoComponentePagamentoInput componentiPagamento = new AccessoChiamanteEsternoSincronoComponentePagamentoInput ();
		impostaComponenti ( componentiPagamento );
		AccessoChiamanteEsternoSincronoComponentePagamentoInput componentiPagamentoSecondario = new AccessoChiamanteEsternoSincronoComponentePagamentoInput ();
		impostaComponenti ( componentiPagamentoSecondario );

		List<AccessoChiamanteEsternoSincronoComponentePagamentoInput> compPagamento = new ArrayList<> ();
		compPagamento.add ( componentiPagamento );
		List<AccessoChiamanteEsternoSincronoComponentePagamentoInput> compPagamentoSecondario
						= new ArrayList<AccessoChiamanteEsternoSincronoComponentePagamentoInput> ();
		compPagamentoSecondario.add ( componentiPagamentoSecondario );
		input.setCodiceChiamante ( codiceChiamante );
		input.setIpChiamante ( "TEST" );
		input.setCodiceFiscaleEnte ( codiceFiscaleEnte );
		input.setCausale ( "test" );
		input.setTipoPagamento ( "AAAF" );
		input.setImportoPrincipale ( importo );
		input.setImportoSecondarioAltroEnte ( importo );
		input.setImportoTotale ( importo.add ( importo ) );
		input.setNome ( "tester" );
		input.setCognome ( "tester" );
		input.setRagioneSociale ( "test" );
		input.setCodiceFiscalePartitaIVAPagatore ( "test" );
		input.setEmail ( "test" );
		input.setComponentiPagamento ( compPagamento );
		input.setComponentiPagamentoSecondario ( compPagamentoSecondario );

		return input;

	}

	private void impostaComponenti ( AccessoChiamanteEsternoSincronoComponentePagamentoInput componentiPagamento ) {
		componentiPagamento.setProgressivo ( 1 );
		componentiPagamento.setImporto ( importo );
		componentiPagamento.setCausale ( "UnitTest" );
		componentiPagamento.setAnnoAccertamento ( 2016 );
		componentiPagamento.setNumeroAccertamento ( "0000" );
	}

	private PagamentoIuvChiamanteEsternoInput generaMockPagamentoInput () {

		PagamentoIuvChiamanteEsternoInput input = new PagamentoIuvChiamanteEsternoInput ();
		input.setCodiceChiamante ( codiceChiamante );
		input.setTimestampChiamata ( today );
		input.setIpChiamante ( "172.11.24.12" );
		input.setIuv ( "iuv" );
		input.setIdentificativoPagamento ( "0001" );
		input.setCodiceFiscale ( "80087670016" );
		return input;
	}

	/**
	 * Test di chiamata al servizio   con input validi {@getRTChiamanteEsterno}
	 */
	@Test
	public void getRTChiamanteEsternoValidInput () {

		GetRTChiamanteEsternoInput input = generaMockGetRTInput ();
		GetRTChiamanteEsternoOutput out = service.getRTChiamanteEsterno ( input );

		assertEquals ( "000", out.getCodiceEsito () );

	}

	/**
	 * Test di chiamata al servizio  con input null {@getRTChiamanteEsterno}
	 */

	@Test
	public void getRTChiamanteEsternoNullInputTest () {
		GetRTChiamanteEsternoInput input = Mockito.mock ( GetRTChiamanteEsternoInput.class );
		GetRTChiamanteEsternoOutput out = service.getRTChiamanteEsterno ( input );
		assertEquals ( "100", out.getCodiceEsito () );
	}

	private GetRTChiamanteEsternoInput generaMockGetRTInput () {
		GetRTChiamanteEsternoInput input = new GetRTChiamanteEsternoInput ();
		input.setCodiceChiamante ( codiceChiamante );
		input.setTimestampChiamata ( today );
		input.setIpChiamante ( "172.11.24.12" );
		input.setIdentificativoPagamento ( "0001" );
		input.setIuv ( "RF20170180005B00100000001" );
		input.setCodiceFiscale ( "mndlnd65l45l219v" );
		input.setFormatoRT ( "XML" );
		input.setCodiceFiscaleEnte ( "00429440068" );
		return input;
	}

	@Test
	public void getStatoPosizioneDebitoriaChiamanteEsternoTest () {
		GetStatoPosizioneDebitoriaInput input = generateMockGetStatoPosizioneDebitoriaChiamanteEsternoInput ();
		GetStatoPosizioneDebitoriaOutput output = epayServicesClientService.getStatoPosizioneDebitoriaChiamanteEsterno ( input );
		assertEquals ( "000", output.getCodiceEsito () );
	}

	@Test
	public void getStatoPosizioneDebitoriaChiamanteEsternoNullTest () {
		GetStatoPosizioneDebitoriaInput input = Mockito.mock ( GetStatoPosizioneDebitoriaInput.class );
		GetStatoPosizioneDebitoriaOutput output = epayServicesClientService.getStatoPosizioneDebitoriaChiamanteEsterno ( input );
		assertEquals ( "100", output.getCodiceEsito () );
	}

	private GetStatoPosizioneDebitoriaInput generateMockGetStatoPosizioneDebitoriaChiamanteEsternoInput () {
		GetStatoPosizioneDebitoriaInput input = new GetStatoPosizioneDebitoriaInput ("", "");
		input.setIpChiamante ( "127.0.0.1" );
		input.setTimestampChiamata ( today );
		input.setCodiceChiamante ( "epayapi1" );
		input.setIdentificativoPagamento ( "494b1b6c-e5bd-4067-b6c3-57fb469bbc27_99" );
		input.setCodiceFiscaleEnte ( "80087670016" );
		return input;
	}

	@Test
	public void getDatiPagamentoChiamanteEsternoTest () {
		GetDatiPagamentoChiamanteEsternoInput input = generateMockGetDatiPagamentoChiamanteEsternoInput ();
		GetDatiPagamentoChiamanteEsternoOutput output = epayServicesClientService.getDatiPagamentoChiamanteEsterno ( input );
		assertEquals ( "000", output.getCodiceEsito () );
	}

	@Test
	public void getDatiPagamentoChiamanteEsternoNullTest () {
		GetDatiPagamentoChiamanteEsternoInput input = Mockito.mock ( GetDatiPagamentoChiamanteEsternoInput.class );
		GetDatiPagamentoChiamanteEsternoOutput output = epayServicesClientService.getDatiPagamentoChiamanteEsterno ( input );
		assertEquals ( "100", output.getCodiceEsito () );
	}

	private GetDatiPagamentoChiamanteEsternoInput generateMockGetDatiPagamentoChiamanteEsternoInput () {
		GetDatiPagamentoChiamanteEsternoInput input = new GetDatiPagamentoChiamanteEsternoInput ("", "");
		input.setIpChiamante ( "127.0.0.1" );
		input.setTimestampChiamata ( today );
		input.setCodiceChiamante ( "epayapi1" );
		input.setIdentificativoPagamento ( "494b1b6c-e5bd-4067-b6c3-57fb469bbc27_99" );
		input.setCodiceFiscaleEnte ( "80087670016" );
		return input;
	}
}
