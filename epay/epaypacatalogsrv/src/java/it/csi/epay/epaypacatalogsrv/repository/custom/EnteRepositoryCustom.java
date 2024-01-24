/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository.custom;

import java.util.List;

import it.csi.epay.epaypacatalogsrv.dto.ente.GetEnteInput;
import it.csi.epay.epaypacatalogsrv.model.Ente;

public interface EnteRepositoryCustom {

    public List<Ente> search ( GetEnteInput input );
    
    
}
