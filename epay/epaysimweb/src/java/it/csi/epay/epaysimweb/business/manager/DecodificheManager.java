/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaysimweb.business.manager;

import java.util.List;

import it.csi.epay.epaysimweb.model.GenericVO;


public interface DecodificheManager {

    List<GenericVO> getListaOpzioniModalitaAcquisizioneProvvisori ();

}
