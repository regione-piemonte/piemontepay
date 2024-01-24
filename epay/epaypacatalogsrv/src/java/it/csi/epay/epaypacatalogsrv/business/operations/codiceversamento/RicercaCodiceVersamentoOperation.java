/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.codiceversamento;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.Operation;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.business.service.ProfilazioneService;
import it.csi.epay.epaypacatalogsrv.business.util.EntityUtils;
import it.csi.epay.epaypacatalogsrv.business.util.SecurityUtils;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.RicercaCodiceVersamentoInput;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.RicercaCodiceVersamentoOutput;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.RicercaCodiceVersamentoOutputDto;
import it.csi.epay.epaypacatalogsrv.exception.BadRequestException;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento;
import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.model.TematicaPpay;
import it.csi.epay.epaypacatalogsrv.model.TematicheDaEscludere;
import it.csi.epay.epaypacatalogsrv.repository.CodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.TematicaPpayRepository;
import it.csi.epay.epaypacatalogsrv.repository.TematicheDaEscludereRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;
import it.csi.epay.epaypacatalogsrv.vo.security.PrincipalVO;


@Operation ( consumes = RicercaCodiceVersamentoInput.class, produces = RicercaCodiceVersamentoOutput.class )
@Component
public class RicercaCodiceVersamentoOperation implements OperationHandler<RicercaCodiceVersamentoInput, RicercaCodiceVersamentoOutput> {

	@Autowired
	private CodiceVersamentoRepository codiceVersamentoRepository;

	@Autowired
	private TematicheDaEscludereRepository tematicheDaEscludereRepository;

	@Autowired
	private TematicaPpayRepository tematicaRepository;

	@Autowired
	private EnteRepository enteRepository;

	@Autowired
	private Mapper dozerBeanMapper;

	@Autowired
	private ProfilazioneService profilazioneService;

	@Override
	public void authorize ( RicercaCodiceVersamentoInput input,
		OperationDispatchingContext<RicercaCodiceVersamentoInput, RicercaCodiceVersamentoOutput> context ) {

		SecurityUtils.assertUseCase ( Constants.USE_CASES.RICERCA_CODICE_VERSAMENTO );
		SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );
	}

	@Override
	public void validateInput ( RicercaCodiceVersamentoInput input,
		OperationDispatchingContext<RicercaCodiceVersamentoInput, RicercaCodiceVersamentoOutput> context ) {
		if ( EntityUtils.isEmpty ( input ) ) {
			throw new BadRequestException ();
		}
	}

	@Override
	@Transactional
	public RicercaCodiceVersamentoOutput execute ( RicercaCodiceVersamentoInput input,
		OperationDispatchingContext<RicercaCodiceVersamentoInput, RicercaCodiceVersamentoOutput> context ) {
		Long idEnte = SecurityUtils.getCurrentIdEnte ();

		// ricerca i codici versamento
		List<CodiceVersamento> records = codiceVersamentoRepository.ricerca ( input, idEnte );

		ArrayList<RicercaCodiceVersamentoOutputDto> risultati = new ArrayList<> ();

		for ( CodiceVersamento record: records ) {
			risultati.add ( map ( record ) );
		}

		// filtra i codici versamento per ritornare solo quelli autorizzati
		risultati = filtraPerVisibilita ( risultati );

		risultati = filtraPerFlagMultibeneficiario ( risultati, Boolean.TRUE.equals ( input.getCodiceMultibeneficiario () ) );

		settaTematicaEsclusa(risultati);

		// sort output
		risultati.sort ( new Comparator<RicercaCodiceVersamentoOutputDto> () {

			@Override
			public int compare ( RicercaCodiceVersamentoOutputDto o1, RicercaCodiceVersamentoOutputDto o2 ) {
				return o1.getCodice ().compareTo ( o2.getCodice () );
			}
		} );

		List<String> codiciFigli= new LinkedList<String>();

		for ( RicercaCodiceVersamentoOutputDto risultato: risultati ) {

			if ( risultato.getCodiciVersamentoCollegati () != null ) {
				risultato.getCodiciVersamentoCollegati ().sort ( new Comparator<RicercaCodiceVersamentoOutputDto> () {

					@Override
					public int compare ( RicercaCodiceVersamentoOutputDto o1, RicercaCodiceVersamentoOutputDto o2 ) {
						return o1.getCodice ().compareTo ( o2.getCodice () );
					}
				} );
				for (RicercaCodiceVersamentoOutputDto figlio:risultato.getCodiciVersamentoCollegati ())
				{
					codiciFigli.add(figlio.getCodice());
				}

			}

		}


		List<RicercaCodiceVersamentoOutputDto>  padri= new LinkedList<RicercaCodiceVersamentoOutputDto>();
		for ( RicercaCodiceVersamentoOutputDto risultato: risultati ) {

			if (!codiciFigli.contains(risultato.getCodice()))
			{
				padri.add(risultato);
			}

		}


		// build output
		return RicercaCodiceVersamentoOutput.ok ( padri );
	}

	private void settaTematicaEsclusa ( ArrayList<RicercaCodiceVersamentoOutputDto> input ) {
		if ( input == null || input.size () < 1 ) {
			return;
		}

		List<TematicheDaEscludere> daEscludere = tematicheDaEscludereRepository.findAll ();

		for ( RicercaCodiceVersamentoOutputDto voce: input ) {
			voce.setTematicaEsclusa ( isTematicaEsclusa ( daEscludere, voce ));
			for(RicercaCodiceVersamentoOutputDto codiciCollegati:voce.getCodiciVersamentoCollegati ()) {
				codiciCollegati.setTematicaEsclusa ( isTematicaEsclusa ( daEscludere, codiciCollegati) );
			}
		}
	}

	private boolean isTematicaEsclusa(List<TematicheDaEscludere> daEscludere,RicercaCodiceVersamentoOutputDto voce) {
		for(TematicheDaEscludere item:daEscludere) {
			TematicaPpay tematica = tematicaRepository.findOneByCodice ( voce.getCodiceTematica () );
			if((tematica != null ) && (tematica.getId ().compareTo ( item.getIdTematica () ) == 0)) {
				return true;
			}
		}
		return false;
	}

	private ArrayList<RicercaCodiceVersamentoOutputDto> filtraPerFlagMultibeneficiario ( ArrayList<RicercaCodiceVersamentoOutputDto> input, boolean isMulti ) {
		if ( input == null || input.isEmpty () ) {
			return input;
		}
		ArrayList<RicercaCodiceVersamentoOutputDto> output = new ArrayList<> ();
		for ( RicercaCodiceVersamentoOutputDto i: input ) {
			boolean flagMbPrimario = Boolean.TRUE.equals ( i.getFlagMbPrimario () );
			boolean flagMbSecondario = Boolean.TRUE.equals ( i.getFlagMbSecondario () );
			if ( isMulti ) {
				//deselezionato non lo mostriamo
				//selezionato mostriamo anche
				//if ( flagMbPrimario || flagMbSecondario ) {
					output.add ( i );
				//}
			} else {
				if ( !flagMbPrimario && !flagMbSecondario ) {
				    if (!CollectionUtils.isEmpty ( i.getCodiciVersamentoCollegati () ))
				    {
				        List<RicercaCodiceVersamentoOutputDto> covCollegati= new LinkedList<RicercaCodiceVersamentoOutputDto> ();
				        for (RicercaCodiceVersamentoOutputDto collegato: i.getCodiciVersamentoCollegati ())
				        {
				            if (!Boolean.TRUE.equals ( collegato.getFlagMbPrimario () )
				                            && !Boolean.TRUE.equals ( collegato.getFlagMbSecondario () ))
				            {
				                covCollegati.add ( collegato );
				            }
				        }
				        
				        i.setCodiciVersamentoCollegati ( covCollegati );
				        
				    }
				    
					output.add ( i );
				}
			}
		}
		return output;
	}

	private ArrayList<RicercaCodiceVersamentoOutputDto> filtraPerVisibilita ( ArrayList<RicercaCodiceVersamentoOutputDto> input ) {
		if ( input == null || input.size () < 1 ) {
			return input;
		}

		ArrayList<RicercaCodiceVersamentoOutputDto> output = new ArrayList<> ();

		PrincipalVO principal = SecurityUtils.getPrincipal ();

		List<String> listaTematicheVisibili = profilazioneService.getCodiciTematicheConVisibilitaTotale ( principal );
		List<Long> listaIdCvVisibili = null;

		for ( RicercaCodiceVersamentoOutputDto voce: input ) {

			boolean addVoce = false;
			boolean addTuttiCollegati = false;

			if ( listaTematicheVisibili.contains ( voce.getCodiceTematica () ) ) {
				addVoce = true;
				addTuttiCollegati = true;
			} else {
				if ( listaIdCvVisibili == null ) {
					listaIdCvVisibili = profilazioneService.getIdCodiciVersamentoConVisibilita ( principal );
				}

				if ( listaIdCvVisibili.contains ( voce.getId () ) ) {
					addVoce = true;
				}
			}

			if ( addVoce ) {
				output.add ( voce );

				if ( !addTuttiCollegati && voce.getCodiciVersamentoCollegati () != null && voce.getCodiciVersamentoCollegati ().size () > 0 ) {
					ArrayList<RicercaCodiceVersamentoOutputDto> outputCollegati = new ArrayList<> ();

					for ( RicercaCodiceVersamentoOutputDto voceCollegata: voce.getCodiciVersamentoCollegati () ) {

						if ( listaIdCvVisibili == null ) {
							listaIdCvVisibili = profilazioneService.getIdCodiciVersamentoConVisibilita ( principal );
						}

						if ( listaIdCvVisibili.contains ( voceCollegata.getId () ) ) {
							outputCollegati.add ( voceCollegata );
						}
					}

					voce.setCodiciVersamentoCollegati ( outputCollegati );
				}
			}
		}

		return output;
	}

	private RicercaCodiceVersamentoOutputDto map ( CodiceVersamento input ) {

		if ( input.getCodiciVersamentoCollegati () != null ) {
			List<CodiceVersamento> filtrati = new ArrayList<> ();
			for ( CodiceVersamento cv: input.getCodiciVersamentoCollegati () ) {
				if ( !EntityUtils.isTrue ( cv.getFlagAnnullato () ) ) {
					filtrati.add ( cv );
				}
			}
			input.setCodiciVersamentoCollegati ( filtrati );
		}

		RicercaCodiceVersamentoOutputDto output = dozerBeanMapper.map ( input, RicercaCodiceVersamentoOutputDto.class );

		return output;
	}
}
