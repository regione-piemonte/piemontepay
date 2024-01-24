/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.business.manager;

import java.util.List;

import it.csi.epay.epaypacatalogweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaypacatalogweb.model.utente.UtenteVO;
import it.csi.epay.epaypacatalogweb.vo.FunzioneVO;

/**
 *
 */

public interface AutorizzazioneFunzioneManager {


    List<FunzioneVO> getCduAssegnati ( UtenteVO userVO, List<FunzioneVO> tutteLeFunzioni );

    List<FunzioneVO> getCduNonAssegnati ( UtenteVO userVO, List<FunzioneVO> tutteLeFunzioni );

    void salvaAssociazioneCdu ( Long idUtente, String [] ids ) throws OperationFailedException;

    List<FunzioneVO> getCduPossibili ();

}
