/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager.utils;

import it.csi.epay.epaymodric.business.epaymodric.bo.Email;
import it.csi.epay.epaymodric.business.epaymodric.model.CblTEmail;


/**
 *
 * @author vsgro
 */
public class InvioMailUtility {

    public static Email toEmail ( CblTEmail cblTEmail ) {
        Email toEmail = new Email ();

        toEmail.setBody ( cblTEmail.getBody () );
        toEmail.setEmergencyRecipient ( "" );
        toEmail.setPassword ( "" );
        toEmail.setPort ( 25L );
        toEmail.setRecipient ( cblTEmail.getRecipient () );
        toEmail.setRetryTimes ( cblTEmail.getRetry () );
        toEmail.setRetryTimesMax ( 10L );
        toEmail.setRetryDelaySeconds ( 10L );
        toEmail.setSender ( "" );
        toEmail.setServer ( "" );
        toEmail.setSubject ( cblTEmail.getSubject () );
        toEmail.setUser ( "" );
        toEmail.setId ( cblTEmail.getId () );

        return toEmail;
    }

}
