/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaybeapi.integration.ejb.mock.epayservices;

import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;
import org.springframework.stereotype.Service;

import it.csi.epay.epayservices.model.JobContextDto;
import it.csi.epay.epayservices.model.Mail;


@Service
public class MailFacadeMockImpl implements it.csi.epay.epayservices.interfaces.ejb.MailFacade{

	@Override
	public void inviaMail(Mail mail) throws MessagingException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		
	}

    @Override
    public void inviaMail ( JobContextDto arg0 ) {
        // TODO Auto-generated method stub
        
    }

}
