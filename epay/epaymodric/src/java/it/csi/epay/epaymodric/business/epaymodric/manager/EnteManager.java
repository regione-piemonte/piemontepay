/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager;

import java.util.List;

import it.csi.epay.epaymodric.business.epaymodric.bo.Ente;
import it.csi.epay.epaymodric.dto.epaymodric.base.DTOEnte;

public interface EnteManager {

    public Ente leggiPerIdEnte ( String idEnte );

    public Ente leggiPerCodiceFiscaleEnte ( String codiceFiscale );

    public List<DTOEnte> elencaEnti ();
}
