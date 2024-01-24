/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.business.operations.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.csi.epay.epaypacatalogsrv.business.dispatcher.Operation;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationDispatchingContext;
import it.csi.epay.epaypacatalogsrv.business.dispatcher.OperationHandler;
import it.csi.epay.epaypacatalogsrv.business.service.InvioMailService;
import it.csi.epay.epaypacatalogsrv.dto.email.ElaboraCodaEmailInput;
import it.csi.epay.epaypacatalogsrv.dto.email.ElaboraCodaEmailOutput;


@Operation ( consumes = ElaboraCodaEmailInput.class, produces = ElaboraCodaEmailOutput.class, skipAuthentication = true )
@Component
public class ElaboraCodaEmailOperation implements OperationHandler<ElaboraCodaEmailInput, ElaboraCodaEmailOutput> {

    @Autowired
    private InvioMailService invioMailService;

	@Override
	@Transactional
    public ElaboraCodaEmailOutput execute ( ElaboraCodaEmailInput input, OperationDispatchingContext<ElaboraCodaEmailInput, ElaboraCodaEmailOutput> context ) {

        // Map<EmailStatoEnum, Integer> risultati = invioMailService.elaboraCodaEmail ();

        invioMailService.elaboraCodaEmail ();

        ElaboraCodaEmailOutput output = ElaboraCodaEmailOutput.ok ();

        //        output.setDescrizioneEsito ( "Elaborazione della coda completata" );
        //
        //        if ( risultati != null && risultati.size () > 0 ) {
        //            for ( Entry<EmailStatoEnum, Integer> entry: risultati.entrySet () ) {
        //                output.setDescrizioneEsito ( output.getDescrizioneEsito () + "; " + entry.getValue () + " email in stato " + entry.getKey ().name () );
        //            }
        //        } else {
        //            output.setDescrizioneEsito ( output.getDescrizioneEsito () + "; nessuna email in coda" );
        //        }

        return output;
	}

}
