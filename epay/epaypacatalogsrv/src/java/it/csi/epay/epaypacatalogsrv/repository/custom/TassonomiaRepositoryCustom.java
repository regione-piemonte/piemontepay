/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository.custom;

import java.util.List;

import it.csi.epay.epaypacatalogsrv.dto.tassonomia.RicercaTassonomiaInput;
import it.csi.epay.epaypacatalogsrv.model.Tassonomia;

public interface TassonomiaRepositoryCustom {

    public List<Tassonomia> ricerca ( RicercaTassonomiaInput input, String codTipoEnteCreditore );
   
   
}
