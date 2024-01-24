/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.proto;

import java.security.InvalidParameterException;
import java.util.Date;


public interface TimeBoundedValidity {

    Date getDataInizioValidita ();

    Date getDataFineValidita ();

    default boolean inValidInterval () {
        return inValidInterval ( new Date () );
    }

    default boolean inValidInterval ( Date reference ) {
        if ( reference == null ) {
            throw new InvalidParameterException ();
        }

        Date from = getDataInizioValidita ();

        if ( from == null ) {
            return false;
        }

        if ( from.after ( reference ) ) {
            return false;
        }

        Date to = getDataFineValidita ();

        if ( to == null ) {
            return true;
        }

        if ( to.after ( reference ) ) {
            return true;
        }

        return false;
    }

}
