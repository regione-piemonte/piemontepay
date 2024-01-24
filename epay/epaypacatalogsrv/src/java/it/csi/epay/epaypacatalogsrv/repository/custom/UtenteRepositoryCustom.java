/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository.custom;

import java.util.List;

import it.csi.epay.epaypacatalogsrv.dto.utente.RicercaUtenteInput;
import it.csi.epay.epaypacatalogsrv.model.Utente;

public interface UtenteRepositoryCustom {

    List<Utente> ricerca ( RicercaUtenteInput input, Long idEnte );

    List<Long> findIdEntiAssociatiByIdUtente ( Long idUtente );

    List<Long> findIdUtentiAssociatiByIdEnte ( Long idEnte );
}
