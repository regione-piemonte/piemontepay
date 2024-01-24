/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.interfaces.ejb;

import java.io.UnsupportedEncodingException;

import javax.ejb.Remote;
import javax.mail.MessagingException;

import it.csi.epay.epayservices.model.JobContextDto;
import it.csi.epay.epayservices.model.Mail;

@Remote
public interface MailFacade {

    void inviaMail ( Mail mail ) throws MessagingException, UnsupportedEncodingException;

    void inviaMail ( JobContextDto context );
}
