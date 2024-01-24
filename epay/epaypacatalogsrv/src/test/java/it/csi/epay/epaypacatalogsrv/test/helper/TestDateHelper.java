/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.test.helper;

import java.security.InvalidParameterException;
import java.util.Calendar;
import java.util.Date;


public abstract class TestDateHelper {

    public static Date plusDays ( Date input, int days ) {
        if ( input == null ) {
            throw new InvalidParameterException ();
        }

        Calendar cal = Calendar.getInstance ();
        cal.setTime ( input );
        cal.add ( Calendar.DATE, days );

        return cal.getTime ();
    }

    public static Date minusDays ( Date input, int days ) {
        return plusDays ( input, -days );
    }
}
