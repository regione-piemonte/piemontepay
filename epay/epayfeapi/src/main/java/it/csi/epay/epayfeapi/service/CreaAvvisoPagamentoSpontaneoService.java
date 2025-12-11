/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service;

import static it.csi.epay.epayfeapi.util.Constants.CONFIG_ENDPOINT_SERVICE_DATI_AVVISO;
import static it.csi.epay.epayfeapi.util.Constants.DELLA_TUA_BANCA;
import static it.csi.epay.epayfeapi.util.Constants.DEL_TUO_ENTE_CREDITORE;
import static it.csi.epay.epayfeapi.util.Constants.DI_POSTE_ITALIANE;
import static it.csi.epay.epayfeapi.util.Constants.ERROR_GET_DATI_AVVISO;
import static it.csi.epay.epayfeapi.util.Constants.UNA_UNICA_RATA;
import static it.csi.epay.epayfeapi.util.Constants.UNICA_RATA;
import static it.csi.epay.epayfeapi.util.Constants.URL_ENTE_CREDITORE;

import java.util.Date;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;

import io.quarkus.logging.Log;
import it.csi.epay.epayfeapi.dto.AnagraficaDTO;
import it.csi.epay.epayfeapi.dto.DestinatarioAvvisoPagamentoDTO;
import it.csi.epay.epayfeapi.dto.EnteCreditoreAvvisoPagamentoDTO;
import it.csi.epay.epayfeapi.dto.EnteDTO;
import it.csi.epay.epayfeapi.dto.InfoPagamentoAvvisoPagamentoDTO;
import it.csi.epay.epayfeapi.dto.PagamentoDTO;
import it.csi.epay.epayfeapi.entity.EpayTEnti;
import it.csi.epay.epayfeapi.model.epaypacatalogsrv.DatiAvvisoOutput;
import it.csi.epay.epayfeapi.rest.client.DatiAvvisoAdapterClient;
import it.csi.epay.epayfeapi.util.AvvisoPagamentoPdfGenerator;


@ApplicationScoped
@Transactional
public class CreaAvvisoPagamentoSpontaneoService {

	@Inject
	AvvisoPagamentoReportService avvisoPagamentoReportService;

	@Inject
	DatiAvvisoPagamentoService datiAvvisoPagamentoService;
	
	@Inject
	ConfigurazioneService configurazioneService;
	
	@Inject
	DatiAvvisoAdapterClient datiAvvisoAdapterClient;

	public byte[] buildAvvisoPagamentoSpontaneo ( PagamentoDTO pagamento ) throws Exception {
		var enteCreditore = buildEnteCreditoreAvvisoPagamentoDTO ( pagamento.getTipoPagamento ().getEpayTEnti (),
						pagamento.getTipoPagamento ().getFlagPresenzaBollettinoPostale (), pagamento.getTipoPagamento ().getIdTipoPagamento () ,
						pagamento.getTipoPagamento ().getCodiceVersamento());

		var destinatario = buildDestinatarioAvvisoPagamentoDTO ( pagamento.getPagatore () );

		var infoPagamento = buildInfoPagamentoAvvisoPagamentoDTO ( pagamento.getCausale (), pagamento.getDataScadenza (),
						pagamento.getImporto ().doubleValue (), pagamento.getIuv (), enteCreditore.getNumeroCCPostale (), enteCreditore.getAutorizzazione () , pagamento.getCodiceAvviso());

		var epayTPdfReport = avvisoPagamentoReportService.getJasperReport ();

		return new AvvisoPagamentoPdfGenerator ().creaPdf ( enteCreditore, destinatario, infoPagamento, epayTPdfReport );
	}

	private EnteCreditoreAvvisoPagamentoDTO buildEnteCreditoreAvvisoPagamentoDTO ( EnteDTO ente, Boolean bollettinoPostale, 
			Long idTipoPagamento,  String codiceVersamento) throws Exception {
		var enteCreditore = new EnteCreditoreAvvisoPagamentoDTO ();
		if ( ente != null ) {
			enteCreditore.setCbill ( ente.getCodiceInterbancario () );
			enteCreditore.setEcLogo ( ente.getLogo () );
			enteCreditore.setEnteCreditore ( ente.getNome () );
			enteCreditore.setCfEnte ( ente.getCodiceFiscale () );
//			
			
//			getDatiAvvisoOutput
//			if ( Boolean.TRUE.equals ( bollettinoPostale ) ) {
//				var datiAvvisoPagamento = datiAvvisoPagamentoService.findByIdTipoPagamento ( idTipoPagamento );
				var datiAvviso = getDatiAvvisoOutput( codiceVersamento ,ente.getCodiceFiscale (),   configurazioneService, datiAvvisoAdapterClient);
				if ( datiAvviso != null &&
                		(!StringUtils.isEmpty(datiAvviso.getIbanAppoggio())|| 
                		!StringUtils.isEmpty(datiAvviso.getIbanTesoreria())) ) {
					enteCreditore.setInfoEnte ( createInfoEnte ( datiAvviso ) );
					enteCreditore.setAutorizzazione ( datiAvviso.getNumeroAutorizzazione() );
					enteCreditore.setSettoreEnte ( datiAvviso.getSettore () );
					enteCreditore.setNumeroCCPostale (!StringUtils.isEmpty(datiAvviso.getIbanAppoggio())? datiAvviso.getIbanAppoggio()
                    		: datiAvviso.getIbanTesoreria());
					enteCreditore.setIntestatarioCCPostale ( datiAvviso.getIntestatarioPostale () );
				}
//			}
		}
		return enteCreditore;
	}

	private DestinatarioAvvisoPagamentoDTO buildDestinatarioAvvisoPagamentoDTO ( AnagraficaDTO anagrafica ) {
		if ( anagrafica == null ) {
			return null;
		}

		var destinatario = new DestinatarioAvvisoPagamentoDTO ();

		if ( StringUtils.isNotBlank ( anagrafica.getRagioneSociale () ) ) {
			destinatario.setAnagraficaDestinatario ( "<b>" + anagrafica.getRagioneSociale () + "</b>" );
			destinatario.setDatiDestinatario ( anagrafica.getRagioneSociale () );
		} else {
			destinatario.setAnagraficaDestinatario ( "<b>" + anagrafica.getNome () + " " + anagrafica.getCognome () + "</b>" );
			destinatario.setDatiDestinatario ( anagrafica.getNome () + " " + anagrafica.getCognome () );
		}

		destinatario.setIdUnivocoDestinatario ( anagrafica.getCodiceFiscale () );
		destinatario.setIndirizzoDestinatario ( createIndirizzoDebitore ( anagrafica ) );

		return destinatario;
	}

	private InfoPagamentoAvvisoPagamentoDTO buildInfoPagamentoAvvisoPagamentoDTO ( String causale, Date dataScadenza, Double importo, String iuv,
					String numeroContoCorrentePostale,
					String autorizzazione , String codiceAvviso) {
		var infoPagamento = new InfoPagamentoAvvisoPagamentoDTO ();

		infoPagamento.setOggettoDelPagamento ( causale );
		infoPagamento.setData ( dataScadenza );
		infoPagamento.setImporto ( importo );
		infoPagamento.setCodiceAvviso ( codiceAvviso );

		// TODO: Modificare in base alle rate
		infoPagamento.setPagamentoReteale ( null );
		infoPagamento.setRate ( UNA_UNICA_RATA );
		infoPagamento.setAllaRata ( UNICA_RATA );

		var sb = new StringBuilder ();
		// in futuro, quando si avra' l'opzione relativa al modello 1 o 2, si
		// settera'. per ora
		infoPagamento.setModalitaPagamentoEnteCreditore ( DEL_TUO_ENTE_CREDITORE );
		infoPagamento.setUrlPagamentoEnteCreditore ( URL_ENTE_CREDITORE );
		if ( StringUtils.isNotBlank ( numeroContoCorrentePostale )
						&& StringUtils.isNotBlank ( autorizzazione ) )
			sb.append ( DI_POSTE_ITALIANE );
		sb.append ( DELLA_TUA_BANCA );
		infoPagamento.setModalitaPagamento ( sb.toString () );
		infoPagamento.setIuv ( iuv );

		return infoPagamento;
	}

	private String createIndirizzoDebitore ( AnagraficaDTO soggettoDebitore ) {
		var indirizzo = new StringBuilder ();
		if ( StringUtils.isNotBlank ( soggettoDebitore.getIndirizzo () ) ) {
			indirizzo.append ( soggettoDebitore.getIndirizzo () );
			if ( StringUtils.isNotBlank ( soggettoDebitore.getCivico () ) ) {
				indirizzo.append ( ", " ).append ( soggettoDebitore.getCivico () ).append ( "<br/>" );
			} else {
				indirizzo.append ( "<br/>" );
			}
			if ( StringUtils.isNotBlank ( soggettoDebitore.getCap () ) ) {
				indirizzo.append ( soggettoDebitore.getCap () ).append ( ", " );
			}
			if ( StringUtils.isNotBlank ( soggettoDebitore.getLocalita () ) ) {
				indirizzo.append ( soggettoDebitore.getLocalita () );
				if ( StringUtils.isNotBlank ( soggettoDebitore.getProvincia () ) ) {
					indirizzo.append ( " (" ).append ( soggettoDebitore.getProvincia () ).append ( ") " );
				}
			}
			if ( StringUtils.isNotBlank ( soggettoDebitore.getNazione () ) ) {
				indirizzo.append ( soggettoDebitore.getNazione () );
			}
		}
		return indirizzo.toString ();
	}

	private String createInfoEnte ( DatiAvvisoOutput infoEnte ) {
		var info = new StringBuilder ();
		if ( StringUtils.isNotBlank ( infoEnte.getIndirizzo () ) ) {
			info.append ( infoEnte.getIndirizzo () );
			if ( StringUtils.isNotBlank ( infoEnte.getCap () ) ) {
				info.append ( ", " ).append ( infoEnte.getCap () );
			}
			if ( StringUtils.isNotBlank ( infoEnte.getLocalita () ) ) {
				info.append ( ( " - " ) ).append ( infoEnte.getLocalita () );
				if ( StringUtils.isNotBlank ( infoEnte.getSiglaProvincia () ) ) {
					info.append ( " (" ).append ( infoEnte.getSiglaProvincia () ).append ( ") " );
				}
			}
			if ( StringUtils.isNotBlank ( infoEnte.getEmail () ) ) {
				info.append ( "<br/>" ).append ( infoEnte.getEmail () );
			}
			if ( StringUtils.isNotBlank ( infoEnte.getInfoEnte () ) ) {
				info.append ( "<br/>" ).append ( infoEnte.getInfoEnte () );
			}
		}
		return info.toString ();
	}
	
	public static DatiAvvisoOutput getDatiAvvisoOutput ( String codiceVersamento, String organizationFiscalCode,
			ConfigurazioneService configurazioneService, DatiAvvisoAdapterClient datiAvvisoAdapterClient ) throws Exception {
		var ente = new EpayTEnti ();
		ente.setIdEnte ( 0L ); // fatto per prendere sempre il record di default per tutti gli enti
		var config = configurazioneService.findByCodiceAndCodiceAndEnte ( CONFIG_ENDPOINT_SERVICE_DATI_AVVISO, ente );
		if ( null == config ) {
			Log.error ( ERROR_GET_DATI_AVVISO );
			throw new Exception ( ERROR_GET_DATI_AVVISO );
		}
		var url = config.getValore ();
		
		return datiAvvisoAdapterClient.getDatiAvviso(organizationFiscalCode, codiceVersamento, url);
}
}
