/*
 * SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
 *
 * SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service;

import io.quarkiverse.cxf.annotation.CXFClient;
import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.entity.EpayTRt;
import it.csi.epay.epayfeapi.entity.EpayTRtPdf;
import it.csi.epay.epayfeapi.entity.EpayTTipoPagamento;
import it.csi.epay.epayfeapi.enumeration.StatoPagamento;
import it.csi.epay.epayfeapi.exception.MdpException;
import it.csi.epay.epayfeapi.repository.RegistroVersamentiRepository;
import it.csi.epay.epayfeapi.repository.RtRepository;
import it.csi.epay.epayfeapi.soap.client.mdpmultiiuv.IuvComplex;
import it.csi.epay.epayfeapi.soap.client.mdpmultiiuv.MdpMultiIuvWSInterface;
import it.csi.epay.epayfeapi.soap.client.mdpmultiiuv.MdpMultiIuvWSInterface_Service;
import it.csi.wso2.apiman.oauth2.helper.GenericWrapperFactoryBean;
import it.csi.wso2.apiman.oauth2.helper.OauthHelper;
import it.csi.wso2.apiman.oauth2.helper.TokenRetryManager;
import it.csi.wso2.apiman.oauth2.helper.extra.cxf.CxfImpl;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;


@ApplicationScoped
@Transactional
public class RtService {

	@Inject
	RtRepository rtRepository;

	@Inject
	RegistroVersamentiRepository registroVersamentiRepository;

	public EpayTRt findByIdRegistroAndCodEsitoPagamento ( Long idRegistro, Integer codEsitoPagamento ) {
		return rtRepository.findByIdRegistroAndCodEsitoPagamento ( idRegistro, codEsitoPagamento );
	}

	public EpayTRtPdf findPdfByIdRegistroAndCodEsitoPagamento ( Long idRegistro, Integer codEsitoPagamento ) {
		return rtRepository.findPdfByIdRegistroAndCodEsitoPagamento ( idRegistro, codEsitoPagamento );
	}

	public EpayTRt ricercaRt ( Long idPagamento, StatoPagamento statoPagamento, String idTransazione ) {
		var rVersamenti = registroVersamentiRepository.ricercaUltimoByIdPagamentoIdTransazioneEIdStato ( idPagamento, statoPagamento.getId (), idTransazione );
		return rtRepository.findByIdRegistro ( rVersamenti.getIdRegistro () );
	}

	@ApplicationScoped
	@Transactional
	public static class MultiIuv {

		@Inject
		@CXFClient ( "MdpMultiIuvsrvServiceWS" )
		@SuppressWarnings ( "all" )
		MdpMultiIuvWSInterface mdpMultiIuvWSInterface;

		@Inject
		ParametriService parametriService;

		private String passphrase;

		private static String getTimestamp () {
			var today = new Date ();
			var dateFormat = new SimpleDateFormat ( "ddMMyyyy-hh:mm:ss:SSS" );
			return dateFormat.format ( today );
		}

		private static String getMac ( final String applicationId, final String codiceVersamento, final String passphrase, final String timestamp ) {
			var sToDigest = passphrase + "%%%%" + codiceVersamento + applicationId + "%%%%" + timestamp + passphrase;
			var bMac = DigestUtils.sha256 ( sToDigest.getBytes () );
			return Base64.encodeBase64String ( bMac ).substring ( 0, 35 );
		}

		@PostConstruct
		public void initialization () throws MalformedURLException, ClassNotFoundException {

			var properties = new Properties ();
			//
			var listaParametri = parametriService.findByGruppo ( "epayservices" );
			for ( var parametro : listaParametri ) {
				properties.put ( parametro.getId ().getCodice (), parametro.getValore () );
			}
			//
			passphrase = parametriService.getParametro ( "mdp", "passphrase" ).getValore ();

			if ( properties.get ( "mdpMultiIuv.apiManager.flag" ).equals ( "true" ) ) {
				var urlService = new URL ( (String) properties.get ( "mdpMultiIuv.url.wsdl" ) );
				var service = new MdpMultiIuvWSInterface_Service ( urlService );
				var mdpMultiIuvWS = service.getMdpMultiIuvWSPort ();
				//
				var trm = new TokenRetryManager ();
				trm.setOauthHelper ( new OauthHelper (
								(String) properties.get ( "mdpMultiIuv.apiManager.oAuthUrl" ),
								(String) properties.get ( "mdpMultiIuv.apiManager.consumerKey" ),
								(String) properties.get ( "mdpMultiIuv.apiManager.consumerSecret" ) ) );
				trm.setWsProvider ( new CxfImpl () );
				//
				var gwfb = new GenericWrapperFactoryBean ();
				gwfb.setEndPoint ( (String) properties.get ( "mdpMultiIuv.apiManager.endPoint" ) );
				gwfb.setWrappedInterface ( MdpMultiIuvWSInterface.class );
				gwfb.setPort ( mdpMultiIuvWS );
				gwfb.setTokenRetryManager ( trm );

				mdpMultiIuvWSInterface = (MdpMultiIuvWSInterface) gwfb.create ();
			}
		}

		public List<IuvComplex> generateNewIuv ( EpayTTipoPagamento tipoPagamento, int numeroOccorrenze ) throws MdpException {
			var timestamp = getTimestamp ();
			var mac = getMac ( tipoPagamento.getIdApplicazione (), tipoPagamento.getCodiceVersamento (), passphrase, timestamp );

			Log.info ( "--------------------------------------------------------------------------------" );
			Log.info ( "Call mdpMultiIuvWSInterface.getIuvComplex" );
			Log.info ( "     Param IdApplicazione        : " + tipoPagamento.getIdApplicazione () );
			Log.info ( "     Param Codice Versamento     : " + tipoPagamento.getCodiceVersamento () );
			Log.info ( "     Param numero occorrenze iuv : " + numeroOccorrenze );
			Log.info ( "     Param timestamp             : " + timestamp );
			Log.info ( "     Param mac                   : " + mac );
			var response = mdpMultiIuvWSInterface.getIuvComplex ( tipoPagamento.getIdApplicazione (), tipoPagamento.getCodiceVersamento (), numeroOccorrenze,
							timestamp, mac );
			Log.info ( "Response mdpMultiIuvWSInterface.getIuvComplex :" );
			Log.info ( response.toString () );
			Log.info ( "--------------------------------------------------------------------------------" );

			return response.getIuvComplex ();
		}

	}
}
