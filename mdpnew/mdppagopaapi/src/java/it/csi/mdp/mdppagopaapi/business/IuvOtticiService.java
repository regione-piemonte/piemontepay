/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.business;

import it.csi.mdp.mdppagopaapi.integration.domain.IuvOttici;



public interface IuvOtticiService {

    /**
     * Metodo per ricercare il primo IUV nella tabella degli IUV ottici.
     *
     * @param stringa contenente lo iuv
     * @return lista di IUV ottici aventi come IUV_OTTICO lo IUV passato in ingresso.
     */
    public IuvOttici findTopByIuvOttico ( String iuv );
}
