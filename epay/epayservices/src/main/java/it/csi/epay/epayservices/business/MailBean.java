/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.business;

import java.io.UnsupportedEncodingException;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.mail.MessagingException;

import it.csi.epay.epayservices.integration.db.manager.MailManager;
import it.csi.epay.epayservices.interfaces.ejb.MailFacade;
import it.csi.epay.epayservices.model.JobContextDto;
import it.csi.epay.epayservices.model.Mail;

@Singleton(name = "MailFacade", mappedName = "Mail")
public class MailBean extends _BaseBean implements MailFacade {

    @EJB
    private MailManager mailManager;

    @Override
    public void inviaMail ( Mail mail ) throws MessagingException, UnsupportedEncodingException {
        mailManager.inviaMail ( mail );
    }

    @Override
    public void inviaMail ( JobContextDto context ) {
        mailManager.inviaMailAssistenza ( context );
    }

}
