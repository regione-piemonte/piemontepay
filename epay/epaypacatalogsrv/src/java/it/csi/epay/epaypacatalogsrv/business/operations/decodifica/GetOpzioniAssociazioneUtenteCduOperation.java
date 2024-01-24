/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.decodifica;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.Operation;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.business.util.SecurityUtils;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniAssociazioneUtenteCduCategoriaCduOutputDto;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniAssociazioneUtenteCduCduOutputDto;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniAssociazioneUtenteCduOutput;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniInput;
import it.csi.epay.epaypacatalogsrv.model.CategoriaCdu;
import it.csi.epay.epaypacatalogsrv.model.Cdu;
import it.csi.epay.epaypacatalogsrv.repository.CategoriaCduRepository;
import it.csi.epay.epaypacatalogsrv.repository.CduRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@Operation ( consumes = GetOpzioniInput.class, produces = GetOpzioniAssociazioneUtenteCduOutput.class )
@Component
public class GetOpzioniAssociazioneUtenteCduOperation implements OperationHandler<GetOpzioniInput, GetOpzioniAssociazioneUtenteCduOutput> {

    @Autowired
    private CategoriaCduRepository categoriaCduRepository;

    @Autowired
    private CduRepository cduRepository;

    @Override
    public void authorize ( GetOpzioniInput input, OperationDispatchingContext<GetOpzioniInput, GetOpzioniAssociazioneUtenteCduOutput> context ) {
        SecurityUtils.assertUseCase ( Constants.USE_CASES.AUTORIZZA_FUNZIONE );
        SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );
    }

    @Override
    @Transactional
    public GetOpzioniAssociazioneUtenteCduOutput execute ( GetOpzioniInput input,
        OperationDispatchingContext<GetOpzioniInput, GetOpzioniAssociazioneUtenteCduOutput> context ) {

        GetOpzioniAssociazioneUtenteCduOutput output = GetOpzioniAssociazioneUtenteCduOutput.ok ( GetOpzioniAssociazioneUtenteCduOutput.class );
        output.setCategorie ( new ArrayList<> () );

        List<CategoriaCdu> categorie = categoriaCduRepository.findAll ( new Sort ( "id" ) );

        for ( CategoriaCdu categoria: categorie ) {

            GetOpzioniAssociazioneUtenteCduCategoriaCduOutputDto dtoCategoria = new GetOpzioniAssociazioneUtenteCduCategoriaCduOutputDto ();

            dtoCategoria.setId ( categoria.getId ().longValue () );
            dtoCategoria.setCodice ( categoria.getCodice () );
            dtoCategoria.setDescrizione ( categoria.getDescrizione () );
            dtoCategoria.setCdu ( new ArrayList<> () );

            List<Cdu> codiciVersamento = cduRepository.findByCategoria ( categoria, new Sort ( "id" ) );
            for ( Cdu codiceVersamento: codiciVersamento ) {

                GetOpzioniAssociazioneUtenteCduCduOutputDto dtoCodiceVersamento
                    = new GetOpzioniAssociazioneUtenteCduCduOutputDto ();
                dtoCodiceVersamento.setId ( codiceVersamento.getId ().longValue () );
                dtoCodiceVersamento.setCodice ( codiceVersamento.getCodice () );
                dtoCodiceVersamento.setDescrizione ( codiceVersamento.getDescrizione () );
                dtoCategoria.getCdu ().add ( dtoCodiceVersamento );
            }

            output.getCategorie ().add ( dtoCategoria );
        }

        return output;
    }

}
