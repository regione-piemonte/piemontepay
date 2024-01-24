/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogweb.business.manager;

import java.util.List;

import it.csi.epay.epaypacatalogweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaypacatalogweb.model.utente.AssociazioneUtenteTematicaVO;
import it.csi.epay.epaypacatalogweb.model.utente.ModificaAssociazioniUtenteTematicaVO;
import it.csi.epay.epaypacatalogweb.model.utente.UtenteVO;

/**
 *
 */

public interface AutorizzazioneCodiceVersamentoManager {

    List<AssociazioneUtenteTematicaVO> getTematichePossibili ();

    void popolaSelezioneTematichePerUtente ( UtenteVO utente, List<AssociazioneUtenteTematicaVO> alberoTematiche,
        ModificaAssociazioniUtenteTematicaVO inputUtente );

    void aggiornaSelezioneTematiche ( Long idUtente, ModificaAssociazioniUtenteTematicaVO inputUtente ) throws OperationFailedException;

}
