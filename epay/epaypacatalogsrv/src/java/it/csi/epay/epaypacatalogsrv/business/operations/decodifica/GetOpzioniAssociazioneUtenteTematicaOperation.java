/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.decodifica;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.Operation;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.business.util.SecurityUtils;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniAssociazioneUtenteTematicaCodiceVersamentoOutputDto;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniAssociazioneUtenteTematicaOutput;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniAssociazioneUtenteTematicaTematicaOutputDto;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniInput;
import it.csi.epay.epaypacatalogsrv.repository.CodiceVersamentoRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@Operation ( consumes = GetOpzioniInput.class, produces = GetOpzioniAssociazioneUtenteTematicaOutput.class )
@Component
public class GetOpzioniAssociazioneUtenteTematicaOperation implements OperationHandler<GetOpzioniInput, GetOpzioniAssociazioneUtenteTematicaOutput> {

    @Autowired
    private CodiceVersamentoRepository codiceVersamentoRepository;

    @Override
    public void authorize ( GetOpzioniInput input, OperationDispatchingContext<GetOpzioniInput, GetOpzioniAssociazioneUtenteTematicaOutput> context ) {
        SecurityUtils.assertUseCase ( Constants.USE_CASES.AUTORIZZA_CODICE_VERSAMENTO );
        SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );
    }

    @Override
    @Transactional
    public GetOpzioniAssociazioneUtenteTematicaOutput execute ( GetOpzioniInput input,
        OperationDispatchingContext<GetOpzioniInput, GetOpzioniAssociazioneUtenteTematicaOutput> context ) {

        Long idEnteCorrente = SecurityUtils.getCurrentIdEnte ();

        GetOpzioniAssociazioneUtenteTematicaOutput output = GetOpzioniAssociazioneUtenteTematicaOutput.ok ( GetOpzioniAssociazioneUtenteTematicaOutput.class );
        output.setTematiche ( new LinkedList<> () );

        List<Object []> listaCovTematiche = codiceVersamentoRepository.codiciVersamentoPerEnte ( idEnteCorrente );
        if ( !CollectionUtils.isEmpty ( listaCovTematiche ) ) {
            for ( Object [] t: listaCovTematiche ) {
                GetOpzioniAssociazioneUtenteTematicaTematicaOutputDto dtoTematica = null;
                if ( CollectionUtils.isEmpty ( output.getTematiche () )
                    || !output.getTematiche ().get ( output.getTematiche ().size () - 1 ).getId ().equals ( ( (Integer) t [0] ).longValue () ) ) {
                    dtoTematica = new GetOpzioniAssociazioneUtenteTematicaTematicaOutputDto ();
                    dtoTematica.setId ( ( (Integer) t [0] ).longValue () );
                    dtoTematica.setCodice ( (String) t [1] );
                    dtoTematica.setDescrizione ( (String) t [2] );
                    dtoTematica.setCodiciVersamento ( new LinkedList<> () );
                    output.getTematiche ().add ( dtoTematica );
                } else {
                    dtoTematica = output.getTematiche ().get ( output.getTematiche ().size () - 1 );
                }
                GetOpzioniAssociazioneUtenteTematicaCodiceVersamentoOutputDto dtoCodiceVersamento
                    = new GetOpzioniAssociazioneUtenteTematicaCodiceVersamentoOutputDto ();
                dtoCodiceVersamento.setId ( ( (BigInteger) t [3] ).longValue () );
                dtoCodiceVersamento.setCodice ( (String) t [4] );
                dtoCodiceVersamento.setDescrizione ( (String) t [5] );
                dtoTematica.getCodiciVersamento ().add ( dtoCodiceVersamento );
            }
        }
        return output;
    }

}
