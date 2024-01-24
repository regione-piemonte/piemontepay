/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager;

import java.util.List;

import it.csi.epay.epaymodric.dto.epaymodric.base.DTOStatoElaborazione;


/**
 *
 */

public interface StatoElaborazioneManager {

    public List<DTOStatoElaborazione> recuperaStatiElaborazione ();

}
