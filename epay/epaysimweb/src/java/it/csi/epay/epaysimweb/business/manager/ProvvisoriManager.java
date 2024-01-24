/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.business.manager;

import it.csi.epay.epaysimweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaysimweb.integration.generated.FlussoGiornaleDiCassa;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.DtoInputWsRicercaProvvisori;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.DtoOutputWsProvvisori;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.GiornaleDiCassaOutputDTO;
import it.csi.epay.epaysimweb.integration.stubs.epaysimulatordataws.InserisciProvvisorioOutputResponse;


public interface ProvvisoriManager {

    InserisciProvvisorioOutputResponse salvaFlussoProvvisori ( FlussoGiornaleDiCassa flussoInput, String utenteInsVar, String xmlFlusso )
                    throws OperationFailedException;

    GiornaleDiCassaOutputDTO ricercaGiornaleDiCassa ( FlussoGiornaleDiCassa flussoInput ) throws OperationFailedException;

    DtoOutputWsProvvisori ricercaProvvisori ( DtoInputWsRicercaProvvisori listaProvvisoriDaCercare ) throws OperationFailedException;
}
