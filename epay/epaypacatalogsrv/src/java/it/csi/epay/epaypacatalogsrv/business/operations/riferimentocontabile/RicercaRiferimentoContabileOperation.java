/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.riferimentocontabile;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.Operation;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.business.operations.codiceversamento.RicercaCodiceVersamentoOperation;
import it.csi.epay.epaypacatalogsrv.business.service.ProfilazioneService;
import it.csi.epay.epaypacatalogsrv.business.util.EntityUtils;
import it.csi.epay.epaypacatalogsrv.business.util.SecurityUtils;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.RicercaCodiceVersamentoInput;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.RicercaCodiceVersamentoOutput;
import it.csi.epay.epaypacatalogsrv.dto.codiceversamento.RicercaCodiceVersamentoOutputDto;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.RicercaRiferimentoContabileGruppoCodiceVersamentoOutputDto;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.RicercaRiferimentoContabileInput;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.RicercaRiferimentoContabileOutput;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.RicercaRiferimentoContabileOutputDto;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.RicercaRiferimentoContabileStoricoOutputDto;
import it.csi.epay.epaypacatalogsrv.exception.BadRequestException;
import it.csi.epay.epaypacatalogsrv.model.CodiceVersamento;
import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.model.StoricoRiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.repository.CodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.repository.EnteRepository;
import it.csi.epay.epaypacatalogsrv.repository.RiferimentoContabileRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;
import it.csi.epay.epaypacatalogsrv.vo.security.PrincipalVO;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Operation ( consumes = RicercaRiferimentoContabileInput.class, produces = RicercaRiferimentoContabileOutput.class )
@Component
public class RicercaRiferimentoContabileOperation implements OperationHandler<RicercaRiferimentoContabileInput, RicercaRiferimentoContabileOutput> {

    @Autowired
    private RiferimentoContabileRepository riferimentoContabileRepository;

    @Autowired
    private CodiceVersamentoRepository codiceVersamentoRepository;

    @Autowired
    private EnteRepository enteRepository;

    @Autowired
    private Mapper dozerBeanMapper;

    @Autowired
    private ProfilazioneService profilazioneService;

    @Autowired(required = false)
    private RicercaCodiceVersamentoOperation ricercaCodiceVersamentoOperation;

    @Override
    public void authorize ( RicercaRiferimentoContabileInput input,
        OperationDispatchingContext<RicercaRiferimentoContabileInput, RicercaRiferimentoContabileOutput> context ) {

        SecurityUtils.assertUseCase ( Constants.USE_CASES.RICERCA_RIFERIMENTO_CONTABILE );
        SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );
    }

    @Override
    public void validateInput ( RicercaRiferimentoContabileInput input,
        OperationDispatchingContext<RicercaRiferimentoContabileInput, RicercaRiferimentoContabileOutput> context ) {
        if ( EntityUtils.isEmpty ( input ) ) {
            throw new BadRequestException ();
        }
    }

    private List<RicercaCodiceVersamentoOutputDto> eseguiRicercaCodiciVersamento ( RicercaRiferimentoContabileInput input ) {

        RicercaCodiceVersamentoInput inputRicercaCodiciVersamento = new RicercaCodiceVersamentoInput ();
        inputRicercaCodiciVersamento.setCaller ( input.getCaller () );
        inputRicercaCodiciVersamento.setCodiceMacrotipo ( input.getCodiceMacrotipo () );
        inputRicercaCodiciVersamento.setCodiceTematica ( input.getCodiceTematica () );
        inputRicercaCodiciVersamento.setCodiceVoceEntrata ( input.getCodiceVoceEntrata () );
        inputRicercaCodiciVersamento.setCodiceMultibeneficiario ( Boolean.TRUE );

        if ( input.getIdCodiceVersamento () != null ) {
            CodiceVersamento cv = codiceVersamentoRepository.findOne ( input.getIdCodiceVersamento () );
            String codiceBase = cv.getCodice ().substring ( 0, 3 ) + "0";
            inputRicercaCodiciVersamento.setCodice ( codiceBase );
        }

        RicercaCodiceVersamentoOutput risultatoRicercaCV = ricercaCodiceVersamentoOperation
            .execute ( inputRicercaCodiciVersamento, null );

        if ( input.getIdCodiceVersamento () != null ) {
            List<RicercaCodiceVersamentoOutputDto> outputFiltrato = new ArrayList<> ();
            for ( RicercaCodiceVersamentoOutputDto o: risultatoRicercaCV.getRisultati () ) {
                if ( o.getId ().equals ( input.getIdCodiceVersamento () ) ) {
                    outputFiltrato.add ( o );
                    o.getCodiciVersamentoCollegati ().clear ();
                    return outputFiltrato;
                }
                if ( o.getCodiciVersamentoCollegati () != null ) {
                    for ( RicercaCodiceVersamentoOutputDto oc: o.getCodiciVersamentoCollegati () ) {
                        if ( oc.getId ().equals ( input.getIdCodiceVersamento () ) ) {
                            outputFiltrato.add ( oc );
                            return outputFiltrato;
                        }
                    }
                }
            }
        }

        return risultatoRicercaCV.getRisultati ();
    }

    @Override
    @Transactional
    public RicercaRiferimentoContabileOutput execute ( RicercaRiferimentoContabileInput input,
        OperationDispatchingContext<RicercaRiferimentoContabileInput, RicercaRiferimentoContabileOutput> context ) {
        Ente ente = enteRepository.findOneByCodiceFiscale ( SecurityUtils.getCurrentCodiceFiscaleEnte () );

        Boolean soloRiferimentiInVita = input.getSoloRiferimentiInVita ();

        // forzo la ricerca di tutti perche' devo calcolare il conteggio totale
        input.setSoloRiferimentiInVita ( false );

        List<RicercaCodiceVersamentoOutputDto> codiciVersamento = eseguiRicercaCodiciVersamento ( input );

        List<RiferimentoContabile> records = riferimentoContabileRepository.ricerca ( input, ente.getId () );

        records = filtraPerEliminareStoriciNonRipristinabili ( records );

        ArrayList<RicercaRiferimentoContabileOutputDto> risultati = new ArrayList<> ();

        for ( RiferimentoContabile record: records ) {
            risultati.add ( map ( record ) );
        }

        risultati = filtraPerVisibilita ( risultati );

        if ( risultati != null ) {
            risultati.sort ( new Comparator<RicercaRiferimentoContabileOutputDto> () {

                @Override
                public int compare ( RicercaRiferimentoContabileOutputDto o1, RicercaRiferimentoContabileOutputDto o2 ) {
                    String v1 = o1.getCodiceCodiceVersamento () + "-" + o1.getDescrizioneCodiceVersamento () + "-"
                        + o1.getCodiceTipologiaDatoSpecificoRiscossione () + "-" + o1.getDatoSpecificoRiscossione () + "-"
                        + o1.getDescrizioneDatoSpecificoRiscossione () + "-" + o1.getId ();
                    String v2 = o2.getCodiceCodiceVersamento () + "-" + o2.getDescrizioneCodiceVersamento () + "-"
                        + o2.getCodiceTipologiaDatoSpecificoRiscossione () + "-" + o2.getDatoSpecificoRiscossione () + "-"
                        + o2.getDescrizioneDatoSpecificoRiscossione () + "-" + o2.getId ();
                    return v1.compareTo ( v2 );
                }

            } );
        }

        // build output
        return costruisciOutput ( codiciVersamento, risultati, soloRiferimentiInVita );
    }

    private List<RiferimentoContabile> filtraPerEliminareStoriciNonRipristinabili ( List<RiferimentoContabile> input ) {
        if ( input != null ) {

            for ( RiferimentoContabile o: input ) {
                if ( o.getStorico () != null && !o.getStorico ().isEmpty () ) {
                    List<StoricoRiferimentoContabile> storicoFiltrato = new ArrayList<> ();
                    for ( StoricoRiferimentoContabile voceStorico: o.getStorico () ) {
                        if ( EntityUtils.isTrue ( voceStorico.getFlagPosizionePrecedente () ) ) {
                            storicoFiltrato.add ( voceStorico );

                            if ( voceStorico.getStorico () != null && !voceStorico.getStorico ().isEmpty () ) {
                                List<StoricoRiferimentoContabile> storicoStoricoFiltrato = new ArrayList<> ();
                                for ( StoricoRiferimentoContabile voceStoricoStorico: voceStorico.getStorico () ) {
                                    if ( EntityUtils.isTrue ( voceStoricoStorico.getFlagPosizionePrecedente () ) ) {
                                        storicoStoricoFiltrato.add ( voceStoricoStorico );
                                    }
                                }
                                voceStorico.setStorico ( storicoStoricoFiltrato );
                            }
                        }
                    }
                    o.setStorico ( storicoFiltrato );
                }
            }
        }
        return input;
    }

    private RicercaRiferimentoContabileOutput costruisciOutput ( List<RicercaCodiceVersamentoOutputDto> codiciVersamento,
        List<RicercaRiferimentoContabileOutputDto> riferimentiContabili, Boolean soloRiferimentiInVita ) {

        List<RicercaRiferimentoContabileGruppoCodiceVersamentoOutputDto> built = new ArrayList<> ();

        for ( RicercaCodiceVersamentoOutputDto cv: codiciVersamento ) {
            RicercaRiferimentoContabileGruppoCodiceVersamentoOutputDto outputCV = new RicercaRiferimentoContabileGruppoCodiceVersamentoOutputDto ();
            outputCV.setNumeroRiferimentiInVita ( 0 );
            outputCV.setNumeroRiferimentiNonInVita ( 0 );
            outputCV.setNumeroRiferimentiTotali ( 0 );

            EntityUtils.copyProperties ( outputCV, cv );
            outputCV.setRiferimentiContabili ( new ArrayList<> () );

            for ( RicercaRiferimentoContabileOutputDto rc: riferimentiContabili ) {
                if ( rc.getIdCodiceVersamento ().equals ( cv.getId () ) ) {
                    outputCV.setNumeroRiferimentiTotali ( outputCV.getNumeroRiferimentiTotali () + 1 );
                    if ( EntityUtils.isRiferimentoInVita ( rc ) ) {
                        outputCV.setNumeroRiferimentiInVita ( outputCV.getNumeroRiferimentiInVita () + 1 );
                        outputCV.getRiferimentiContabili ().add ( rc );
                    } else {
                        outputCV.setNumeroRiferimentiNonInVita ( outputCV.getNumeroRiferimentiNonInVita () + 1 );
                        if ( Boolean.FALSE.equals ( soloRiferimentiInVita ) ) {
                            outputCV.getRiferimentiContabili ().add ( rc );
                        }
                    }

                    if ( rc.getStorico () != null ) {
                        for ( RicercaRiferimentoContabileStoricoOutputDto voceStorico: rc.getStorico () ) {
                            outputCV.setNumeroRiferimentiTotali ( outputCV.getNumeroRiferimentiTotali () + 1 );
                            if ( EntityUtils.isRiferimentoInVita ( voceStorico ) ) {
                                outputCV.setNumeroRiferimentiInVita ( outputCV.getNumeroRiferimentiInVita () + 1 );
                            } else {
                                outputCV.setNumeroRiferimentiNonInVita ( outputCV.getNumeroRiferimentiNonInVita () + 1 );
                            }
                        }
                    }
                }
            }

            outputCV.setCodiciVersamentoCollegati ( new ArrayList<> () );

            if ( cv.getCodiciVersamentoCollegati () != null && cv.getCodiciVersamentoCollegati ().size () > 0 ) {

                for ( RicercaCodiceVersamentoOutputDto cvc: cv.getCodiciVersamentoCollegati () ) {
                    RicercaRiferimentoContabileGruppoCodiceVersamentoOutputDto outputCVC = new RicercaRiferimentoContabileGruppoCodiceVersamentoOutputDto ();
                    outputCVC.setNumeroRiferimentiInVita ( 0 );
                    outputCVC.setNumeroRiferimentiNonInVita ( 0 );
                    outputCVC.setNumeroRiferimentiTotali ( 0 );

                    EntityUtils.copyProperties ( outputCVC, cvc );
                    outputCVC.setRiferimentiContabili ( new ArrayList<> () );

                    for ( RicercaRiferimentoContabileOutputDto rc: riferimentiContabili ) {
                        if ( rc.getIdCodiceVersamento ().equals ( cvc.getId () ) ) {
                            outputCVC.setNumeroRiferimentiTotali ( outputCVC.getNumeroRiferimentiTotali () + 1 );
                            if ( EntityUtils.isRiferimentoInVita ( rc ) ) {
                                outputCVC.setNumeroRiferimentiInVita ( outputCVC.getNumeroRiferimentiInVita () + 1 );
                                outputCVC.getRiferimentiContabili ().add ( rc );
                            } else {
                                outputCVC.setNumeroRiferimentiNonInVita ( outputCVC.getNumeroRiferimentiNonInVita () + 1 );
                                if ( !soloRiferimentiInVita ) {
                                    outputCVC.getRiferimentiContabili ().add ( rc );
                                }
                            }

                            if ( rc.getStorico () != null ) {
                                for ( RicercaRiferimentoContabileStoricoOutputDto voceStorico: rc.getStorico () ) {
                                    outputCVC.setNumeroRiferimentiTotali ( outputCVC.getNumeroRiferimentiTotali () + 1 );
                                    if ( EntityUtils.isRiferimentoInVita ( voceStorico ) ) {
                                        outputCVC.setNumeroRiferimentiInVita ( outputCVC.getNumeroRiferimentiInVita () + 1 );
                                    } else {
                                        outputCVC.setNumeroRiferimentiNonInVita ( outputCVC.getNumeroRiferimentiNonInVita () + 1 );
                                    }
                                }
                            }
                        }
                    }

                    outputCVC.setCodiciVersamentoCollegati ( new ArrayList<> () );

                    outputCV.getCodiciVersamentoCollegati ().add ( outputCVC );
                }

            }

            built.add ( outputCV );
        }

        if ( built != null ) {
            built.sort ( new Comparator<RicercaRiferimentoContabileGruppoCodiceVersamentoOutputDto> () {

                @Override
                public int compare ( RicercaRiferimentoContabileGruppoCodiceVersamentoOutputDto o1,
                    RicercaRiferimentoContabileGruppoCodiceVersamentoOutputDto o2 ) {
                    String v1 = o1.getCodice () + "-" + o1.getDescrizione ();
                    String v2 = o2.getCodice () + "-" + o2.getDescrizione ();
                    return v1.compareTo ( v2 );
                }

            } );
        }

        return RicercaRiferimentoContabileOutput.ok ( built );
    }

    private ArrayList<RicercaRiferimentoContabileOutputDto> filtraPerVisibilita ( ArrayList<RicercaRiferimentoContabileOutputDto> input ) {
        if ( input == null || input.size () < 1 ) {
            return input;
        }

        ArrayList<RicercaRiferimentoContabileOutputDto> output = new ArrayList<> ();

        PrincipalVO principal = SecurityUtils.getPrincipal ();

        List<String> listaTematicheVisibili = profilazioneService.getCodiciTematicheConVisibilitaTotale ( principal );

        List<Long> listaIdCvVisibili = null;

        for ( RicercaRiferimentoContabileOutputDto voce: input ) {

            if ( listaTematicheVisibili.contains ( voce.getCodiceTematica () ) ) {
                output.add ( voce );
            } else {
                if ( listaIdCvVisibili == null ) {
                    listaIdCvVisibili = profilazioneService.getIdCodiciVersamentoConVisibilita ( principal );
                }
                if ( listaIdCvVisibili.contains ( voce.getIdCodiceVersamento () ) ) {
                    output.add ( voce );
                }
            }
        }

        return output;
    }

    private RicercaRiferimentoContabileOutputDto map ( RiferimentoContabile input ) {

        RicercaRiferimentoContabileOutputDto mapped = dozerBeanMapper.map ( input, RicercaRiferimentoContabileOutputDto.class );

        if ( mapped.getStorico () != null ) {
            mapped.getStorico ().sort ( new Comparator<RicercaRiferimentoContabileStoricoOutputDto> () {
                @Override
                public int compare ( RicercaRiferimentoContabileStoricoOutputDto o1, RicercaRiferimentoContabileStoricoOutputDto o2 ) {
                    return o1.getId ().compareTo ( o2.getId () );
                }
            } );
        }

        return mapped;

    }
}
