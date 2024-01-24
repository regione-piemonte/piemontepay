/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.security;

import it.csi.epay.epaymodric.dto.epaymodric.base.DTOCaller;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOInputBase;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOPrincipal;
import it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv.CallerInputDto;
import it.csi.epay.epaymodric.util.wsdl.epaypacatalogsrv.PrincipalInputDto;


public abstract class RequestParserHelper {

    public static CallerInputDto getCaller ( DTOInputBase input ) {
        if ( input != null && input.getCaller () != null ) {
            CallerInputDto outputCaller = new CallerInputDto ();
            DTOCaller inputCaller = input.getCaller ();

            outputCaller.setCodiceApplicativo ( inputCaller.getCodiceApplicativo () );
            outputCaller.setIp ( inputCaller.getIp () );

            DTOPrincipal inputPrincipal = inputCaller.getPrincipal ();

            if ( inputPrincipal != null ) {
                PrincipalInputDto outputPrincipal = new PrincipalInputDto ();
                outputCaller.setPrincipal ( outputPrincipal );
                outputPrincipal.setCodiceEnte ( inputPrincipal.getCodiceEnte () );
                outputPrincipal.setCodiceFiscale ( inputPrincipal.getCodiceFiscale () );
            }

            return outputCaller;
        } else {
            return null;
        }
    }

}
