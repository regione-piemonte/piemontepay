/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayfeapi.service;

import static it.csi.epay.epayfeapi.util.Constants.REGISTRO_VERSAMENTO_ORIGINE_INSERIMENTO;
import static it.csi.epay.epayfeapi.util.DateUtil.currentTimestamp;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;

import io.quarkus.logging.Log;
import io.quarkus.panache.common.Sort;
import it.csi.epay.epayfeapi.dto.OrigineChiamata;
import it.csi.epay.epayfeapi.dto.PagamentoDTO;
import it.csi.epay.epayfeapi.dto.RegistroVersamentiDTO;
import it.csi.epay.epayfeapi.dto.StatoPagamento;
import it.csi.epay.epayfeapi.dto.TransazioneMdpDTO;
import it.csi.epay.epayfeapi.entity.EpayDOrigineChiamata;
import it.csi.epay.epayfeapi.entity.EpayDStatoPagamento;
import it.csi.epay.epayfeapi.entity.EpayTAnagrafica;
import it.csi.epay.epayfeapi.entity.EpayTPagamento;
import it.csi.epay.epayfeapi.entity.EpayTRegistroVersamenti;
import it.csi.epay.epayfeapi.entity.EpayTRegistroVersamentiReflection;
import it.csi.epay.epayfeapi.mapper.AnagraficaMapper;
import it.csi.epay.epayfeapi.mapper.RegistroVersamentiMapper;
import it.csi.epay.epayfeapi.mapper.TransazioneMdpMapper;
import it.csi.epay.epayfeapi.repository.RegistroVersamentiRepository;


@ApplicationScoped
@Transactional
public class RegistroVersamentiService {

	@Inject
	RegistroVersamentiRepository registroVersamentiRepository;

	@Inject
	AnagraficaService anagraficaService;

	@Inject
	StatoPagamentoService statoPagamentoService;

	@Inject
	PagamentoService pagamentoService;

	@Inject
	AnagraficaMapper anagraficaMapper;

	@Inject
	RegistroVersamentiMapper registroVersamentiMapper;

	@Inject
	TransazioneMdpMapper transazioneMdpMapper;

	public EpayTRegistroVersamentiReflection findMaxIdByIdPagamentoAndStatoPagamento ( Long idPagamento, Integer idStato ) {
		return registroVersamentiRepository.findMaxIdByIdPagamentoAndStatoPagamento ( idPagamento, idStato, Sort.descending ( "idRegistro" ) );
	}

	public void tracciaRegistroPagamento ( PagamentoDTO pagamento, StatoPagamento stato, TransazioneMdpDTO transazioneMdp, OrigineChiamata origineChiamata ) {
		RegistroVersamentiDTO registroVersamenti = new RegistroVersamentiDTO ();
		registroVersamenti.setIdPagamento ( pagamento.getIdPagamento () );
		registroVersamenti.setIdPagamentoSecondario ( null ); // sempre a null?
		registroVersamenti.setRisultato ( stato.getDescrizione () );
		if ( transazioneMdp != null ) {
			registroVersamenti.setIdTransazione ( transazioneMdp.getIdTransazione () );
		} else {
			Log.error ( "TransazioneMdp null, potrebbe causare dei problemi!" );
		}
		registroVersamenti
			.setIuv ( StringUtils.isNotEmpty ( pagamento.getIuvRegistroVersamenti () ) ? pagamento.getIuvRegistroVersamenti () : pagamento.getIuv () );
		registroVersamenti.setIdStato ( stato.getId () );
		registroVersamenti.setOrigineInserimento ( REGISTRO_VERSAMENTO_ORIGINE_INSERIMENTO );
		registroVersamenti.setDataOperazione ( currentTimestamp () );

		EpayTRegistroVersamenti entity = registroVersamentiMapper.toEntity ( registroVersamenti );
		if ( origineChiamata != null ) {
			// N.B.
			// impostato qui in modo esplicito senza sfruttare l'annotation, ora eliminata, in RegistroVersamentiMapper
			// @Mapping ( target = "epayDOrigineChiamata.id", source = "idOrigineChiamata")
			// perche se idOrigineChiamata risultasse null, si scassa da qualche parte con
			// "save the transient instance before flushing"
			//
			EpayDOrigineChiamata origineChiamataEntity = new EpayDOrigineChiamata ();
			origineChiamataEntity.setId ( origineChiamata.getId () );
			entity.setEpayDOrigineChiamata ( origineChiamataEntity );
		}
		if ( pagamento.getPagatore () != null ) {
			EpayTAnagrafica epayTAnagrafica = anagraficaMapper.toEntity ( pagamento.getPagatore () );
			if ( anagraficaService.isPersistent ( epayTAnagrafica ) ) {
				anagraficaService.save ( epayTAnagrafica );
			}
			//entity.setEpayTAnagrafica ( epayTAnagrafica ); SE LO SETTIAMO SI ROMPE TUTTO IL GITO DEL PAGAMENTO
		}
		if ( transazioneMdp != null ) {
			// N.B.
			// impostato qui in modo esplicito senza sfruttare l'annotation, ora eliminata, in RegistroVersamentiMapper
			// @Mapping ( target = "epayTTransazioneMdp.idTransazione", source = "idTransazione")
			// perche se idTransazione risultasse null, si scassa da qualche parte con
			// "save the transient instance before flushing"
			//
			entity.setEpayTTransazioneMdp ( transazioneMdpMapper.toEntity ( transazioneMdp ) );
		}
		EpayDStatoPagamento statoPagamento = statoPagamentoService.findById ( registroVersamenti.getIdStato () );
		entity.setEpayDStatoPagamento ( statoPagamento );
		EpayTPagamento epayTPagamento = pagamentoService.findById ( pagamento.getIdPagamento () );
		entity.setEpayTPagamento ( epayTPagamento );
		// save
		EpayTRegistroVersamenti result = save ( entity );
		//
		registroVersamenti.setIdRegistro ( result.getIdRegistro () );
		Log.debug ( "Inserita voce di tracciatura versamento : " + registroVersamenti );
	}

	private EpayTRegistroVersamenti save ( EpayTRegistroVersamenti epayTRegistroVersamenti ) {
		registroVersamentiRepository.persist ( epayTRegistroVersamenti );
		return epayTRegistroVersamenti;
	}

	public Long findMaxIdRegistro ( Long idPagamento ) {
		return registroVersamentiRepository.findMaxIdRegistro ( idPagamento );
	}
}
