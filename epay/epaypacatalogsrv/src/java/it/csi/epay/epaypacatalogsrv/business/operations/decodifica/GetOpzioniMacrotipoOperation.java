/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.decodifica;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.Operation;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.business.util.SecurityUtils;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.DecodificaOutputDto;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniInput;
import it.csi.epay.epaypacatalogsrv.dto.decodifica.GetOpzioniMacrotipoOutput;
import it.csi.epay.epaypacatalogsrv.model.MacrotipoPpay;
import it.csi.epay.epaypacatalogsrv.repository.MacrotipoPpayRepository;
import it.csi.epay.epaypacatalogsrv.vo.Constants;

@Operation(consumes = GetOpzioniInput.class, produces = GetOpzioniMacrotipoOutput.class)
@Component
public class GetOpzioniMacrotipoOperation implements OperationHandler<GetOpzioniInput, GetOpzioniMacrotipoOutput> {

    @Autowired
    private MacrotipoPpayRepository repository;

    @Override
    public void authorize ( GetOpzioniInput input, OperationDispatchingContext<GetOpzioniInput, GetOpzioniMacrotipoOutput> context ) {
        SecurityUtils.assertValidPrincipal();
        SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );
    }

    @Override
    @Transactional
    public GetOpzioniMacrotipoOutput execute ( GetOpzioniInput input, OperationDispatchingContext<GetOpzioniInput, GetOpzioniMacrotipoOutput> context ) {

        GetOpzioniMacrotipoOutput output = GetOpzioniMacrotipoOutput
                        .ok(GetOpzioniMacrotipoOutput.class);
        output.setOpzioni(new ArrayList<>());

        for (MacrotipoPpay entry : repository
                        //CSI_PAG_AM-5380 si ordina per codice desc
                        .findAll ( new Sort ( Direction.DESC, "codice" ) ) ) {
            output.getOpzioni()
            .add(new DecodificaOutputDto(entry.getId().longValue(), entry.getCodice(), entry.getDescrizione()));
        }
        return output;
    }

}
