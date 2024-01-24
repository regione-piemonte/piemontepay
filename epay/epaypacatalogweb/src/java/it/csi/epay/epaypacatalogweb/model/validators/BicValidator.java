/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.model.validators;

import org.apache.commons.lang3.StringUtils;


public class BicValidator {

    public boolean isValid ( String bic) {
        Boolean result = true;

        int AAAA = 0;
        int BB = 4;
        int CC = 6;
        int DDD = 8;

        if ( StringUtils.isEmpty ( bic ) )
            return false;
        if ( bic.length () != 11 )
            return false;;

        result &= StringUtils.isAlpha ( bic.substring ( AAAA, AAAA + 4 ) );
        result &= StringUtils.isAlpha ( bic.substring ( BB, BB + 2 ) );
        result &= StringUtils.isAlphanumeric ( bic.substring ( CC, CC + 2 ) );
        result &= StringUtils.isAlphanumeric ( bic.substring ( DDD, DDD + 3 ) );

        return result;
    }

}
