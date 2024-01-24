/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.util;

import it.csi.epay.epaypacatalogsrv.model.Email;
import it.csi.epay.epaypacatalogsrv.vo.email.EmailVO;


public class InvioMailUtils {

    public static EmailVO toEmail ( Email emailEntity ) {
        EmailVO toEmail = new EmailVO ();

        toEmail.setBody ( emailEntity.getBody () );
        toEmail.setEmergencyRecipient ( "" );
        toEmail.setPassword ( "" );
        toEmail.setPort ( 25L );
        toEmail.setRecipient ( emailEntity.getRecipient () );
        toEmail.setRetryTimes ( emailEntity.getNumeroTentativi ().longValue () );
        toEmail.setRetryTimesMax ( 10L );
        toEmail.setRetryDelaySeconds ( 10L );
        toEmail.setSender ( "" );
        toEmail.setServer ( "" );
        toEmail.setSubject ( emailEntity.getSubject () );
        toEmail.setUser ( "" );
        toEmail.setId ( emailEntity.getId () );

        return toEmail;
    }

}
