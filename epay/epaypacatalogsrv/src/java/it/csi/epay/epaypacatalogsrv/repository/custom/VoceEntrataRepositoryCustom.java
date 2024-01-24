/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository.custom;

import java.util.List;

import it.csi.epay.epaypacatalogsrv.dto.voceentrata.RicercaVoceEntrataDisponibileInput;
import it.csi.epay.epaypacatalogsrv.dto.voceentrata.RicercaVoceEntrataInput;
import it.csi.epay.epaypacatalogsrv.model.VoceEntrata;

public interface VoceEntrataRepositoryCustom {

    public List<VoceEntrata> ricerca ( RicercaVoceEntrataInput input );

    public List<VoceEntrata> ricercaDisponibili ( RicercaVoceEntrataDisponibileInput input, Long idEnte );
}
