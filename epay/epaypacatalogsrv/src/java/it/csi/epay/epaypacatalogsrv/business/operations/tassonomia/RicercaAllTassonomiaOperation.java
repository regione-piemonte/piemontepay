/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.tassonomia;

import java.util.LinkedList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.Operation;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.RicercaTassonomiaAllInput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.RicercaTassonomiaOutput;
import it.csi.epay.epaypacatalogsrv.dto.tassonomia.TassonomiaOutputDto;
import it.csi.epay.epaypacatalogsrv.model.Tassonomia;
import it.csi.epay.epaypacatalogsrv.repository.TassonomiaRepository;


@Operation ( consumes = RicercaTassonomiaAllInput.class, produces = RicercaTassonomiaOutput.class )
@Component
public class RicercaAllTassonomiaOperation implements OperationHandler<RicercaTassonomiaAllInput, RicercaTassonomiaOutput> {

    @Autowired
    private TassonomiaRepository tassonomiaRepository;

    @Autowired
    private Mapper dozerBeanMapper;

    @Override
    public RicercaTassonomiaOutput execute ( RicercaTassonomiaAllInput input,
        OperationDispatchingContext<RicercaTassonomiaAllInput, RicercaTassonomiaOutput> context ) {

        List<Tassonomia> listEntity = tassonomiaRepository.findAll ();
        List<TassonomiaOutputDto> risultati = new LinkedList<> ();

        for ( Tassonomia entity: listEntity ) {
            risultati.add ( dozerBeanMapper.map ( entity, TassonomiaOutputDto.class ) );
        }

        return RicercaTassonomiaOutput.ok ( risultati );
    }

}
