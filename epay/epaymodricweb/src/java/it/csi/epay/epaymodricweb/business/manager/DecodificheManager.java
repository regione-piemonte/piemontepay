/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.business.manager;

import java.util.List;

import it.csi.epay.epaymodricweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaymodricweb.model.GenericVO;

public interface DecodificheManager {

    List<GenericVO> getListaOpzioniStatoFlusso() throws OperationFailedException;

	List<GenericVO> getListaOpzioniCodiciVersamento () throws OperationFailedException;

}
