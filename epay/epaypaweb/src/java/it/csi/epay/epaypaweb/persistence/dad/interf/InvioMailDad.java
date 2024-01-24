/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.persistence.dad.interf;

import it.csi.epay.epaypaweb.dto.InvioMailDto;
import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.persistence.dad.EPaypaDadBase;

//@formatter:off
/** cuscinetto intermedio tra business e dao, contiene semplice logica di accesso ai dati */
public interface InvioMailDad extends EPaypaDadBase {

	public InvioMailDto findProssimoInvioMail(int seconds) throws PersistenceException;
	public Long insertInvioMail(InvioMailDto invioMailDto) throws PersistenceException;
	public boolean updateInvioMail(InvioMailDto invioMail) throws PersistenceException;
	public void deleteInvioMail(Long idInvioMail) throws PersistenceException;

}
//@formatter:on
