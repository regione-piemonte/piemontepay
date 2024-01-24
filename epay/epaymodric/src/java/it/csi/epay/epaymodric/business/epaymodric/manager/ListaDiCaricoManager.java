/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager;

import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsSalvaListaDiCarico;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsEsito;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.EpaymodricException;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.UnrecoverableException;

/**
 *
 */

public interface ListaDiCaricoManager {

    public DTOOutputWsEsito salvaListaDiCarico ( DTOInputWsSalvaListaDiCarico input ) throws EpaymodricException, Exception, UnrecoverableException;

    public void invalidaListaDiCarico ( Long id );

}
