/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.manager.utils;

import it.csi.epay.epaymodric.business.epaymodric.model.CblTLockApplicativo;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputWsLock;

public class LockUtility {

    public static DTOOutputWsLock toDto(CblTLockApplicativo lock) {
        
        DTOOutputWsLock dto = new DTOOutputWsLock ();
        
        dto.setIdEnte(lock.getCblTEnte ().getIdEnte ());
        dto.setDataFine(lock.getDataFine ());
        dto.setDataInizio(lock.getDataInizio ());
        dto.setId(lock.getId ());
        dto.setIdUtente(lock.getIdUtente ());
        
        return dto;
        
    }
    
}
