/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.manager;

import java.util.List;

import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsLock;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsLock;


public interface LockManager {

    public List<DTOOutputWsLock> lockFind ( DTOInputWsLock input );

    public Boolean lockBreak ( Long idLock );

}
