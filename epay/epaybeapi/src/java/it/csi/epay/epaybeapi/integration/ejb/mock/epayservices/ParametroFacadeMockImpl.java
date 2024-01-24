/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.ejb.mock.epayservices;

import java.util.List;
import org.springframework.stereotype.Service;
import it.csi.epay.epayservices.interfaces.exception.NoDataException;
import it.csi.epay.epayservices.model.Parametro;


@Service
public class ParametroFacadeMockImpl implements it.csi.epay.epayservices.interfaces.ejb.ParametroFacade {

    @Override
    public Parametro ricerca ( String arg0, String arg1 ) throws NoDataException, IllegalArgumentException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Parametro> ricercaGruppo ( String arg0 ) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String test ( String arg0 ) {

        return " - value = (MODK) - " + arg0;
    }

}
