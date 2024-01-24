/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.autorizzazione;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.Operation;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.business.service.AuditService;
import it.csi.epay.epaypacatalogsrv.business.util.SecurityUtils;
import it.csi.epay.epaypacatalogsrv.dto.autorizzazione.AutorizzaEsportazioneDatiInput;
import it.csi.epay.epaypacatalogsrv.dto.autorizzazione.AutorizzaEsportazioneDatiOutput;
import it.csi.epay.epaypacatalogsrv.exception.BadRequestException;
import it.csi.epay.epaypacatalogsrv.vo.Constants;


@Operation ( consumes = AutorizzaEsportazioneDatiInput.class, produces = AutorizzaEsportazioneDatiOutput.class )
@Component
public class AutorizzaEsportazioneDatiOperation implements OperationHandler<AutorizzaEsportazioneDatiInput, AutorizzaEsportazioneDatiOutput> {

    @Autowired
    private AuditService auditService;

    @Override
    public void authorize ( AutorizzaEsportazioneDatiInput input,
        OperationDispatchingContext<AutorizzaEsportazioneDatiInput, AutorizzaEsportazioneDatiOutput> context ) {

        SecurityUtils.assertValidPrincipal ();
        SecurityUtils.assertScope ( Constants.SCOPES.CONFIGURATORE );
    }

    @Override
    public void validateInput ( AutorizzaEsportazioneDatiInput input,
        OperationDispatchingContext<AutorizzaEsportazioneDatiInput, AutorizzaEsportazioneDatiOutput> context ) {

        if ( StringUtils.isBlank ( input.getEntity () ) ) {
            throw new BadRequestException ( Constants.MESSAGES.FIELD_REQUIRED, "entity" );
        }

        if ( input.getIdList () == null ) {
            throw new BadRequestException ( Constants.MESSAGES.FIELD_REQUIRED, "id list" );
        }
    }

    @Override
    @Transactional
    public AutorizzaEsportazioneDatiOutput execute ( AutorizzaEsportazioneDatiInput input,
        OperationDispatchingContext<AutorizzaEsportazioneDatiInput, AutorizzaEsportazioneDatiOutput> context ) {

        auditService.preExport ( input.getEntity ()," (Download file)", input.getIdList () );

        AutorizzaEsportazioneDatiOutput output = AutorizzaEsportazioneDatiOutput.ok ( AutorizzaEsportazioneDatiOutput.class );

        return output;
    }

}
