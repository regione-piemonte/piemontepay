/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.riferimentocontabile;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.Operation;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.business.enums.TipoAssociazioneMultibeneficiario;
import it.csi.epay.epaypacatalogsrv.business.util.EntityUtils;
import it.csi.epay.epaypacatalogsrv.business.util.SecurityUtils;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.GetRiferimentoContabileInput;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.GetRiferimentoContabileOutput;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.GetRiferimentoContabileOutputDto;
import it.csi.epay.epaypacatalogsrv.dto.riferimentocontabile.GetRiferimentoContabileStoricoOutputDto;
import it.csi.epay.epaypacatalogsrv.exception.BadRequestException;
import it.csi.epay.epaypacatalogsrv.exception.NotFoundException;
import it.csi.epay.epaypacatalogsrv.model.RiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.model.StoricoRiferimentoContabile;
import it.csi.epay.epaypacatalogsrv.repository.RiferimentoContabileRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Operation ( consumes = GetRiferimentoContabileInput.class, produces = GetRiferimentoContabileOutput.class )
@Component
public class GetRiferimentoContabileOperation implements OperationHandler<GetRiferimentoContabileInput, GetRiferimentoContabileOutput> {

    @Autowired
    private RiferimentoContabileRepository repository;

    @Autowired
    private Mapper dozerBeanMapper;

    @Override
    public void authorize ( GetRiferimentoContabileInput input,
        OperationDispatchingContext<GetRiferimentoContabileInput, GetRiferimentoContabileOutput> context ) {

        SecurityUtils.assertUseCase ( Constants.USE_CASES.RICERCA_RIFERIMENTO_CONTABILE );
        SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );
    }

    @Override
    public void validateInput ( GetRiferimentoContabileInput input,
        OperationDispatchingContext<GetRiferimentoContabileInput, GetRiferimentoContabileOutput> context ) {

        if ( input.getId () == null ) {
            throw new BadRequestException ( Constants.MESSAGES.FIELD_REQUIRED, "ID" );
        }
        if ( input.getId () < 1L ) {
            throw new BadRequestException ( Constants.MESSAGES.INVALID_FIELD, "ID" );
        }
    }

    @Override
    @Transactional
    public GetRiferimentoContabileOutput execute ( GetRiferimentoContabileInput input,
        OperationDispatchingContext<GetRiferimentoContabileInput, GetRiferimentoContabileOutput> context ) {

        RiferimentoContabile raw = repository.findOne ( input.getId () );
        if ( raw == null ) {
            throw new NotFoundException ( Constants.MESSAGES.CODICE_VERSAMENTO_NOT_FOUND );
        }

        // l'utente deve avere visibilita' del codice
        SecurityUtils.assertVisibilitaSuCodiceVersamento ( raw.getCodiceVersamento () );

        raw = filtraPerEliminareStoriciNonRipristinabili ( raw );

        GetRiferimentoContabileOutputDto mapped = this.map ( raw );
        return GetRiferimentoContabileOutput.ok ( mapped );
    }

    private GetRiferimentoContabileOutputDto map ( RiferimentoContabile input ) {

        GetRiferimentoContabileOutputDto output = dozerBeanMapper.map ( input, GetRiferimentoContabileOutputDto.class );

        output.setFlagElementiMultibeneficiario ( Boolean.TRUE.equals ( input.getFlagMbPrimario () ) || Boolean.TRUE.equals ( input.getFlagMbSecondario () ) );
        if ( Boolean.TRUE.equals ( output.getFlagElementiMultibeneficiario () ) ) {
            if ( Boolean.TRUE.equals ( input.getFlagMbPrimario () ) ) {
                output.setMbModalita ( TipoAssociazioneMultibeneficiario.PRIMARIO.getCode ());
                if ( !CollectionUtils.isEmpty ( input.getRiferimentiContabiliSecondariCollegati () ) ) {
                    output
                    .setMbEnteSecondario (
                        input.getRiferimentiContabiliSecondariCollegati ().get ( 0 ).getCodiceVersamento ().getEnte ().getDenominazione () );
                    output.setMbCodiceVersamentoAssociato ( input.getRiferimentiContabiliSecondariCollegati ().get ( 0 ).getCodiceVersamento ().getCodice ()+
                        " - "+input.getRiferimentiContabiliSecondariCollegati ().get ( 0 ).getCodiceVersamento ().getDescrizione () );
                    String cva = input.getDescrizioneDatoSpecificoRiscossione ();
                    cva += input.getAnnoEsercizio () + "; Validita' dal ";
                    cva += input.getDataInizioValidita () + " al ";
                    cva += input.getDataFineValidita ();
                    output.setMbRiferimentoContabileAssociato ( cva );
                }
            } else {
                output.setMbModalita ( TipoAssociazioneMultibeneficiario.SECONDARIO.getCode ());
            }
        }

        if ( output.getStorico () != null ) {
            output.getStorico ().sort ( new Comparator<GetRiferimentoContabileStoricoOutputDto> () {

                @Override
                public int compare ( GetRiferimentoContabileStoricoOutputDto o1, GetRiferimentoContabileStoricoOutputDto o2 ) {
                    return o1.getId ().compareTo ( o2.getId () );
                }

            } );
        }

        return output;
    }

    private RiferimentoContabile filtraPerEliminareStoriciNonRipristinabili ( RiferimentoContabile input ) {
        if ( input != null && input.getStorico () != null && !input.getStorico ().isEmpty () ) {
            List<StoricoRiferimentoContabile> storicoFiltrato = new ArrayList<> ();
            for ( StoricoRiferimentoContabile voceStorico: input.getStorico () ) {
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
            input.setStorico ( storicoFiltrato );
        }
        return input;
    }

}
