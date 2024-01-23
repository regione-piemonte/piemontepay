/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service;

import it.csi.epay.epayfeapi.dto.AnagraficaDTO;
import it.csi.epay.epayfeapi.dto.DestinatarioAvvisoPagamentoDTO;
import it.csi.epay.epayfeapi.dto.EnteCreditoreAvvisoPagamentoDTO;
import it.csi.epay.epayfeapi.dto.EnteDTO;
import it.csi.epay.epayfeapi.dto.InfoPagamentoAvvisoPagamentoDTO;
import it.csi.epay.epayfeapi.dto.PagamentoDTO;
import it.csi.epay.epayfeapi.entity.EpayTDatiAvvisoPagamento;
import it.csi.epay.epayfeapi.entity.EpayTPdfReport;
import it.csi.epay.epayfeapi.util.AvvisoPagamentoPdfGenerator;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;

import static it.csi.epay.epayfeapi.util.Constants.DELLA_TUA_BANCA;
import static it.csi.epay.epayfeapi.util.Constants.DEL_TUO_ENTE_CREDITORE;
import static it.csi.epay.epayfeapi.util.Constants.DI_POSTE_ITALIANE;
import static it.csi.epay.epayfeapi.util.Constants.UNA_UNICA_RATA;
import static it.csi.epay.epayfeapi.util.Constants.UNICA_RATA;
import static it.csi.epay.epayfeapi.util.Constants.URL_ENTE_CREDITORE;


@ApplicationScoped
@Transactional
public class CreaAvvisoPagamentoSpontaneoService {

	@Inject
	AvvisoPagamentoReportService avvisoPagamentoReportService;

	@Inject
	DatiAvvisoPagamentoService datiAvvisoPagamentoService;

	public byte [] buildAvvisoPagamentoSpontaneo ( PagamentoDTO pagamento ) throws IOException, JRException {
		EnteCreditoreAvvisoPagamentoDTO enteCreditore = buildEnteCreditoreAvvisoPagamentoDTO ( pagamento.getTipoPagamento ().getEpayTEnti (),
			pagamento.getTipoPagamento ().getFlagPresenzaBollettinoPostale (), pagamento.getTipoPagamento ().getIdTipoPagamento () );

		DestinatarioAvvisoPagamentoDTO destinatario = buildDestinatarioAvvisoPagamentoDTO ( pagamento.getPagatore () );

		InfoPagamentoAvvisoPagamentoDTO infoPagamento = buildInfoPagamentoAvvisoPagamentoDTO ( pagamento.getCausale (), pagamento.getDataScadenza (),
			pagamento.getImporto ().doubleValue (), pagamento.getIuv (), enteCreditore.getNumeroCCPostale (), enteCreditore.getAutorizzazione () );

		EpayTPdfReport epayTPdfReport = avvisoPagamentoReportService.getJasperReport ();

		AvvisoPagamentoPdfGenerator avvisoPagamentoPdfGenerator = new AvvisoPagamentoPdfGenerator ();

		return avvisoPagamentoPdfGenerator.creaPdf ( enteCreditore, destinatario, infoPagamento, epayTPdfReport );
	}

	private EnteCreditoreAvvisoPagamentoDTO buildEnteCreditoreAvvisoPagamentoDTO ( EnteDTO ente, Boolean bollettinoPostale, Long idTipoPagamento ) {
		EnteCreditoreAvvisoPagamentoDTO enteCreditore = new EnteCreditoreAvvisoPagamentoDTO ();
		if ( ente != null ) {
			enteCreditore.setCbill ( ente.getCodiceInterbancario () );
			enteCreditore.setEcLogo ( ente.getLogo () );
			enteCreditore.setEnteCreditore ( ente.getNome () );
			enteCreditore.setCfEnte ( ente.getCodiceFiscale () );
			if ( Boolean.TRUE.equals ( bollettinoPostale ) ) {
				EpayTDatiAvvisoPagamento datiAvvisoPagamento = datiAvvisoPagamentoService.findByIdTipoPagamento ( idTipoPagamento );
				if ( datiAvvisoPagamento != null ) {
					enteCreditore.setInfoEnte ( createInfoEnte ( datiAvvisoPagamento ) );
					enteCreditore.setAutorizzazione ( datiAvvisoPagamento.getAutorizzazioneDaPosteIt () );
					enteCreditore.setSettoreEnte ( datiAvvisoPagamento.getSettore () );
					enteCreditore.setNumeroCCPostale ( datiAvvisoPagamento.getNumeroCCPostale () );
					enteCreditore.setIntestatarioCCPostale ( datiAvvisoPagamento.getIntestatarioCCPostale () );
				}
			}
		}
		return enteCreditore;
	}

	private DestinatarioAvvisoPagamentoDTO buildDestinatarioAvvisoPagamentoDTO ( AnagraficaDTO anagrafica ) {
		if ( anagrafica == null ) {
			return null;
		}

		DestinatarioAvvisoPagamentoDTO destinatario = new DestinatarioAvvisoPagamentoDTO ();

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
		String autorizzazione ) {
		InfoPagamentoAvvisoPagamentoDTO infoPagamento = new InfoPagamentoAvvisoPagamentoDTO ();

		infoPagamento.setOggettoDelPagamento ( causale );
		infoPagamento.setData ( dataScadenza );
		infoPagamento.setImporto ( importo );
		infoPagamento.setCodiceAvviso ( "" );

		// TODO: Modificare in base alle rate
		infoPagamento.setPagamentoReteale ( null );
		infoPagamento.setRate ( UNA_UNICA_RATA );
		infoPagamento.setAllaRata ( UNICA_RATA );

		StringBuilder sb = new StringBuilder ();
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
		StringBuilder indirizzo = new StringBuilder ();
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

	private String createInfoEnte ( EpayTDatiAvvisoPagamento infoEnte ) {
		StringBuilder info = new StringBuilder ();
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
}
