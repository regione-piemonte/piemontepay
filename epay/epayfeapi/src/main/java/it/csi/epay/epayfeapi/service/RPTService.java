/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service;

import it.csi.epay.epayfeapi.dto.PagamentoComponentiDTO;
import it.csi.epay.epayfeapi.dto.PagamentoDTO;
import it.csi.epay.epayfeapi.entity.EpayTPagamentoSecondario;
import it.csi.epay.epayfeapi.entity.EpayTPagamentoSecondarioComponenti;
import it.csi.epay.epayfeapi.soap.client.mdp.core.interfacecxf.DatiAccertamentoRPT;
import it.csi.epay.epayfeapi.soap.client.mdp.core.interfacecxf.DatiSingoloVersamentoRPT;
import it.csi.epay.epayfeapi.soap.client.mdp.core.interfacecxf.DatiVersamentoRPT;
import it.csi.epay.epayfeapi.soap.client.mdp.core.interfacecxf.ElencoRPT;
import it.csi.epay.epayfeapi.soap.client.mdp.core.interfacecxf.RPTData;
import it.csi.epay.epayfeapi.soap.client.mdp.core.interfacecxf.SoggettoPagatore;
import it.csi.epay.epayfeapi.soap.client.mdp.core.interfacecxf.Transazione;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import static it.csi.epay.epayfeapi.util.Constants.F;
import static it.csi.epay.epayfeapi.util.Constants.G;


@ApplicationScoped
@Transactional
public class RPTService {

	@Inject
	PagamentoSecondarioService pagamentoSecondarioService;

	@Inject
	PagamentoSecondarioComponentiService pagamentoSecondarioComponentiService;

	public ElencoRPT costruisciRPT ( PagamentoDTO pagamento, Transazione transazione, Boolean multibeneficiario ) {

		if ( multibeneficiario != null && multibeneficiario ) {
			return costruisciRPTMultibeneficiario ( pagamento, transazione );
		}

		SoggettoPagatore soggettoPagatore = new SoggettoPagatore ();
		soggettoPagatore.setIdentificativoUnivocoPagatore ( pagamento.getPagatore ().getCodiceFiscale () );
		if ( pagamento.getPagatore ().getFlagPersonaFisica () != null && pagamento.getPagatore ().getFlagPersonaFisica () ) {
			soggettoPagatore.setTipoIdentificativoUnivocoPagatore ( F );
			soggettoPagatore.setAnagraficaPagatore ( StringUtils.substring (
							StringUtils.join ( new String[] { pagamento.getPagatore ().getNome (), pagamento.getPagatore ().getCognome () }, " " ), 0, 70 ) );
		} else {
			soggettoPagatore.setTipoIdentificativoUnivocoPagatore ( G );
			soggettoPagatore.setAnagraficaPagatore ( StringUtils.substring ( pagamento.getPagatore ().getRagioneSociale (), 0, 70 ) );
		}
		if ( StringUtils.isNotBlank ( pagamento.getPagatore ().getEmail () ) ) {
			soggettoPagatore.setEMailPagatore ( pagamento.getPagatore ().getEmail () );
		}

		DatiVersamentoRPT datiVersamentoRpt = new DatiVersamentoRPT ();
		datiVersamentoRpt.setImportoTotaleDaVersare ( pagamento.getImporto () );
		datiVersamentoRpt.setIdentificativoUnivocoVersamento ( pagamento.getIuv () );

		if ( pagamento.getComponenti () == null || pagamento.getComponenti ().isEmpty () ) {
			String causaleVersamento = componiCausaleVersamento ( pagamento.getIuv (), pagamento.getImporto (),
							pagamento.getTipoPagamento ().getDescrizionePortale () );
			DatiSingoloVersamentoRPT datiSingoloVersamentoRPT = new DatiSingoloVersamentoRPT ();
			datiSingoloVersamentoRPT.setCausaleVersamento ( causaleVersamento );
			datiSingoloVersamentoRPT.setImportoSingoloVersamento ( pagamento.getImporto () );
			datiSingoloVersamentoRPT.setDatiSpecificiRiscossione ( pagamento.getTipoPagamento ().getDatiSpecificiRiscossione () );

			costruisciRPTHelper ( pagamento, datiVersamentoRpt, datiSingoloVersamentoRPT, pagamento.getAnnoAccertamento (),
							pagamento.getNumeroAccertamento () != null, pagamento.getNumeroAccertamento () );
		} else {
			for ( PagamentoComponentiDTO componente : pagamento.getComponenti () ) {
				String causaleVersamento
								= componiCausaleVersamento ( pagamento.getIuvRegistroVersamenti (), componente.getImporto (), componente.getCausale () );
				DatiSingoloVersamentoRPT datiSingoloVersamentoRPT = new DatiSingoloVersamentoRPT ();
				datiSingoloVersamentoRPT.setCausaleVersamento ( causaleVersamento );
				datiSingoloVersamentoRPT.setImportoSingoloVersamento ( componente.getImporto () );
				datiSingoloVersamentoRPT.setDatiSpecificiRiscossione ( componente.getDatiSpecificiRiscossione () );
				costruisciRPTHelper ( pagamento, datiVersamentoRpt, datiSingoloVersamentoRPT, componente.getAnnoAccertamento (),
								componente.getNumeroAccertamento () != null, Integer.valueOf ( componente.getNumeroAccertamento () ) );
			}
		}

		RPTData rptData = buildRPTData ( soggettoPagatore, datiVersamentoRpt, transazione.getApplicationId () );

		ElencoRPT elencoRPT = new ElencoRPT ();
		elencoRPT.getRptdata ().add ( rptData );

		return elencoRPT;
	}

	private RPTData buildRPTData ( SoggettoPagatore soggettoPagatore, DatiVersamentoRPT datiVersamentoRpt, String applicationId ) {
		RPTData rptData = new RPTData ();
		rptData.setAutenticazioneSoggetto ( "N/A" );
		rptData.setSoggettoPagatore ( soggettoPagatore );
		rptData.setSoggettoVersante ( null );
		rptData.setDatiVersamento ( datiVersamentoRpt );
		rptData.setApplicationId ( applicationId );
		return rptData;
	}

	private ElencoRPT costruisciRPTMultibeneficiario ( PagamentoDTO pagamento, Transazione transazione ) {
		SoggettoPagatore soggettoPagatore = new SoggettoPagatore ();
		soggettoPagatore.setIdentificativoUnivocoPagatore ( pagamento.getPagatore ().getCodiceFiscale () );
		if ( pagamento.getPagatore ().getFlagPersonaFisica () != null && pagamento.getPagatore ().getFlagPersonaFisica () ) {
			soggettoPagatore.setTipoIdentificativoUnivocoPagatore ( F );
			soggettoPagatore.setAnagraficaPagatore (
							StringUtils.join ( new String[] { pagamento.getPagatore ().getNome (), pagamento.getPagatore ().getCognome () }, " " ) );
		} else {
			soggettoPagatore.setTipoIdentificativoUnivocoPagatore ( G );
			soggettoPagatore.setAnagraficaPagatore ( pagamento.getPagatore ().getRagioneSociale () );
		}
		if ( StringUtils.isNotBlank ( pagamento.getPagatore ().getEmail () ) ) {
			soggettoPagatore.setEMailPagatore ( pagamento.getPagatore ().getEmail () );
		}

		DatiVersamentoRPT datiVersamentoRpt = new DatiVersamentoRPT ();
		datiVersamentoRpt.setImportoTotaleDaVersare ( pagamento.getImportoPrincipale () );
		datiVersamentoRpt.setIdentificativoUnivocoVersamento ( pagamento.getIuvRegistroVersamenti () );

		for ( PagamentoComponentiDTO componente : pagamento.getComponenti () ) {
			buildDatiSingoloVersamento ( pagamento, componente.getImporto (), componente.getCausale (),
							componente.getDatiSpecificiRiscossione (),
							componente.getAnnoAccertamento (), componente.getNumeroAccertamento () );
		}

		RPTData rptData1 = buildRPTData ( soggettoPagatore, datiVersamentoRpt, transazione.getApplicationId () );

		ElencoRPT elencoRPT = new ElencoRPT ();
		elencoRPT.getRptdata ().add ( rptData1 );

		//recupero il pagamento secondario associato al principale
		DatiVersamentoRPT datiVersamentoRpt2 = new DatiVersamentoRPT ();
		EpayTPagamentoSecondario pagamentoSecondario = pagamentoSecondarioService.getPagamentoSecondarioByIdPagamentoPrincipale ( pagamento.getIdPagamento () );
		datiVersamentoRpt2.setImportoTotaleDaVersare ( pagamentoSecondario.getImportoComplessivo () );
		datiVersamentoRpt2.setIdentificativoUnivocoVersamento ( pagamento.getIuvRegistroVersamenti () );

		//recuperare record da epay_t_pagamento_secondario_componenti
		List<EpayTPagamentoSecondarioComponenti> pagamentoSecondarioComponenti
						= pagamentoSecondarioComponentiService.getPagamentoSecondarioComponentiById ( pagamentoSecondario.getIdPagamentoSecondario () );

		for ( EpayTPagamentoSecondarioComponenti componenteSecondaria : pagamentoSecondarioComponenti ) {
			datiVersamentoRpt2.getDatiSingoloVersamento ().add (
							buildDatiSingoloVersamento ( pagamento,
											componenteSecondaria.getImporto (),
											componenteSecondaria.getCausale (),
											componenteSecondaria.getDatiSpecificiRiscossione (),
											componenteSecondaria.getAnnoAccertamento (),
											componenteSecondaria.getNumeroAccertamento () ) );
		}

		RPTData rptData2 = buildRPTData ( soggettoPagatore, datiVersamentoRpt, pagamentoSecondario.getEpayTTipoPagamento ().getIdApplicazione () );

		elencoRPT.getRptdata ().add ( rptData2 );
		return elencoRPT;
	}

	private DatiSingoloVersamentoRPT buildDatiSingoloVersamento ( PagamentoDTO pagamento, BigDecimal importo, String causale,
					String datiSpecificiRiscossione, Integer annoAccertamento, String numeroAccertamento ) {
		String causaleVersamento
						= componiCausaleVersamento ( /*[BUG: poteva risultare null e questo generava NullPointeException] pagamento.getIuvRegistroVersamenti () *//*[FIX:]*/ pagamento.getIuv(), importo, causale );
		DatiSingoloVersamentoRPT datiSingoloVersamentoRPT = new DatiSingoloVersamentoRPT ();
		datiSingoloVersamentoRPT.setCausaleVersamento ( causaleVersamento );
		datiSingoloVersamentoRPT.setImportoSingoloVersamento ( importo );
		datiSingoloVersamentoRPT.setDatiSpecificiRiscossione ( datiSpecificiRiscossione );
		DatiAccertamentoRPT datiAccertamentoRPT = new DatiAccertamentoRPT ();
		datiAccertamentoRPT.setAnnoAccertamento ( annoAccertamento );
		try {
			datiAccertamentoRPT
							.setNumeroAccertamento (
											numeroAccertamento != null ? Integer.valueOf ( numeroAccertamento ) : null );
		} catch ( NumberFormatException nfe ) {
			throw new RuntimeException ( "Numero pagamento " + pagamento.getIdPagamento () + " ha il formato del numero accertamento errato" );
		}
		datiSingoloVersamentoRPT.setDatiAccertamento ( datiAccertamentoRPT );

		return datiSingoloVersamentoRPT;
	}

	private void costruisciRPTHelper ( PagamentoDTO pagamento, DatiVersamentoRPT datiVersamentoRpt, DatiSingoloVersamentoRPT datiSingoloVersamentoRPT,
					Integer annoAccertamento, boolean b, Integer integer ) {
		DatiAccertamentoRPT datiAccertamentoRPT = new DatiAccertamentoRPT ();
		datiAccertamentoRPT.setAnnoAccertamento ( annoAccertamento );
		try {
			datiAccertamentoRPT.setNumeroAccertamento ( b ? integer : null );
		} catch ( NumberFormatException nfe ) {
			throw new RuntimeException ( "Numero pagamento " + pagamento.getIdPagamento () + " ha il formato del numero accertamento errato" );
		}
		datiSingoloVersamentoRPT.setDatiAccertamento ( datiAccertamentoRPT );

		datiVersamentoRpt.getDatiSingoloVersamento ().add ( datiSingoloVersamentoRPT );
	}

	private String componiCausaleVersamento ( String iuv, BigDecimal importo, String descrizione ) {
		StringBuilder composizioneCausale = new StringBuilder ( iuv.length () == 25 ? "/RFS/" : "/RFB/" );
		composizioneCausale.append ( iuv );
		composizioneCausale.append ( "/" );
		composizioneCausale.append ( importo.toString () );
		if ( StringUtils.isNotBlank ( descrizione ) ) {
			composizioneCausale.append ( "/TXT/" ).append ( descrizione );
		}
		return composizioneCausale.substring ( 0, Math.min ( composizioneCausale.length (), 140 ) );
	}
}
