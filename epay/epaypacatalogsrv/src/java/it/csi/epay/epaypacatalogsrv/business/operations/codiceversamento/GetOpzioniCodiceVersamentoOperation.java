/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.codiceversamento;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.Operation;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.business.service.ProfilazioneService;
import it.csi.epay.epaypacatalogsrv.business.util.SecurityUtils;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.DecodificaCodiciVersamentoOutputDto;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.DecodificaOutputDto;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniCodiceVersamentoInput;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniCodiceVersamentoOutput;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento_;
import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.model.Ente_;
import it.csi.epay.epaypacatalogsrv.model.TematicaPpay;
import it.csi.epay.epaypacatalogsrv.model.TematicaPpay_;
import it.csi.epay.epaypacatalogsrv.model.VoceEntrata;
import it.csi.epay.epaypacatalogsrv.model.VoceEntrata_;
import it.csi.epay.epaypacatalogsrv.repository.CodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;
import it.csi.epay.epaypacatalogsrv.vo.security.PrincipalVO;


@Operation ( consumes = GetOpzioniCodiceVersamentoInput.class, produces = GetOpzioniCodiceVersamentoOutput.class )
@Component
public class GetOpzioniCodiceVersamentoOperation implements OperationHandler<GetOpzioniCodiceVersamentoInput, GetOpzioniCodiceVersamentoOutput> {

	@Autowired
	private CodiceVersamentoRepository repository;

	@Autowired
	private EnteRepository enteRepository;

	@Autowired
	private ProfilazioneService profilazioneService;

	@Override
	public void authorize ( GetOpzioniCodiceVersamentoInput input,
		OperationDispatchingContext<GetOpzioniCodiceVersamentoInput, GetOpzioniCodiceVersamentoOutput> context ) {
		SecurityUtils.assertUseCase ( Constants.USE_CASES.LOGIN );
		SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );
	}

	@Override
	public void validateInput ( GetOpzioniCodiceVersamentoInput input,
		OperationDispatchingContext<GetOpzioniCodiceVersamentoInput, GetOpzioniCodiceVersamentoOutput> context ) {
	}

	@Override
	@Transactional
	public GetOpzioniCodiceVersamentoOutput execute ( GetOpzioniCodiceVersamentoInput input,
		OperationDispatchingContext<GetOpzioniCodiceVersamentoInput, GetOpzioniCodiceVersamentoOutput> context ) {
		Ente enteCorrente = enteRepository.findOneByCodiceFiscale ( SecurityUtils.getCurrentCodiceFiscaleEnte () );

		GetOpzioniCodiceVersamentoOutput output = GetOpzioniCodiceVersamentoOutput.ok ( GetOpzioniCodiceVersamentoOutput.class );
		output.setOpzioni ( new ArrayList<> () );

		PrincipalVO principal = SecurityUtils.getPrincipal ();

		List<String> listaTematicheVisibili = profilazioneService.getCodiciTematicheConVisibilitaTotale ( principal );

		List<Long> listaIdCvVisibili = profilazioneService.getIdCodiciVersamentoConVisibilita ( principal );

		List<CodiceVersamento> candidates = repository.findAll(new Specification<CodiceVersamento>() {

			@Override
			public Predicate toPredicate(Root<CodiceVersamento> root, CriteriaQuery<?> cq, CriteriaBuilder builder) {
				Join<CodiceVersamento, Ente> joinEnte = root.join(CodiceVersamento_.ente, JoinType.LEFT);
				Join<CodiceVersamento, VoceEntrata> joinVoceEntrata = root.join(CodiceVersamento_.voceEntrata, JoinType.LEFT);
				Join<VoceEntrata, TematicaPpay> joinTematica = joinVoceEntrata.join(VoceEntrata_.tematica, JoinType.LEFT);

				root.fetch(CodiceVersamento_.tipoPagamento);
				root.fetch(CodiceVersamento_.codiceVersamentoPadre, JoinType.LEFT);
				//				root.fetch(CodiceVersamento_.statoAggiornamento);
				//				root.fetch(CodiceVersamento_.statoMultibeneficiario);
				root.fetch(CodiceVersamento_.modalitaIntegrazione, JoinType.LEFT); //-> con questa in join non da risultati
				Fetch<CodiceVersamento, VoceEntrata> fetchVE = root.fetch(CodiceVersamento_.voceEntrata);
				fetchVE.fetch(VoceEntrata_.tematica);
				fetchVE.fetch(VoceEntrata_.macrotipo);

				Predicate visibilityCondition = builder.disjunction();
				if (!listaIdCvVisibili.isEmpty()) {
					visibilityCondition = builder.or(
						visibilityCondition, 
						root.get(CodiceVersamento_.id).in(listaIdCvVisibili)	
									);
				}

				if (!listaTematicheVisibili.isEmpty()) {
					visibilityCondition = builder.or(
						visibilityCondition, 
						joinTematica.get(TematicaPpay_.codice).in(listaTematicheVisibili)	
									);
				}

				return builder.and(
					builder.equal(joinEnte.get(Ente_.id), enteCorrente.getId()),
					builder.or(
						builder.isNull(root.get(CodiceVersamento_.flagAnnullato)),
						builder.isFalse(root.get(CodiceVersamento_.flagAnnullato))
									),
					(
									Boolean.TRUE.equals(input.getSoloBase ()) ? 
													builder.isNull(root.get(CodiceVersamento_.codiceVersamentoPadre)) :
														builder.conjunction()
									),
					visibilityCondition
								);
			}

		}, new Sort ( CodiceVersamento_.id.getName() ));

		for ( CodiceVersamento entry: candidates ) {
			DecodificaCodiciVersamentoOutputDto decodifica = new DecodificaCodiciVersamentoOutputDto();
			decodifica.setId( entry.getId ().longValue ());
			decodifica.setCodice( entry.getCodice());
			decodifica.setDescrizione( entry.getDescrizione());
			decodifica.setFlagMbPrimario( entry.getFlagMbPrimario());
			decodifica.setFlagMbSecondario( entry.getFlagMbSecondario());
			decodifica.setTipoPagamento(entry.getTipoPagamento ()!= null? entry.getTipoPagamento ().getCodice ():null);
			if (entry.getCodiciVersamentoSecondariCollegati()!= null && entry.getCodiciVersamentoSecondariCollegati().size() >0) {

				CodiceVersamento codiceCollegato = entry.getCodiciVersamentoSecondariCollegati().get(0);

				decodifica.setCodiceVersamentoSecondario(codiceCollegato.getCodice());
				decodifica.setEnteSecondario(codiceCollegato.getEnte().getDenominazione());
				decodifica.setIdCodiceVersamentoSecondario ( codiceCollegato.getId () );
				decodifica.setDescrizioneCodiceVersamentoSecondario ( codiceCollegato.getDescrizione () );
				

			}

			output.getOpzioni ()
			.add ( decodifica );
		}

		output.getOpzioni ().sort ( new Comparator<DecodificaOutputDto> () {

			@Override
			public int compare ( DecodificaOutputDto o1, DecodificaOutputDto o2 ) {
				String v1 = o1.getCodice () + " - " + o1.getDescrizione ();
				String v2 = o2.getCodice () + " - " + o2.getDescrizione ();
				return v1.compareTo ( v2 );
			}

		} );

		return output;
	}

}
