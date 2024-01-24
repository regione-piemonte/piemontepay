/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogweb.business.manager;

import it.csi.epay.epaypacatalogweb.common.exceptions.OperationFailedException;
import it.csi.epay.epaypacatalogweb.model.gestioneente.EnteVO;
import it.csi.epay.epaypacatalogweb.model.gestioneente.ModificaEnteVO;
import it.csi.epay.epaypacatalogweb.model.gestioneente.RicercaEnteFiltroVO;
import it.csi.epay.epaypacatalogweb.model.gestioneente.RisultatoRicercaEnteVO;

import java.util.List;


public interface EnteManager {

	List<RisultatoRicercaEnteVO> ricercaEnti ( RicercaEnteFiltroVO input ) throws OperationFailedException;

	EnteVO getEnteById ( Long input ) throws OperationFailedException;

	void salvaEnte ( ModificaEnteVO input ) throws OperationFailedException;

	List<EnteVO> getEntiConRiferimentoContabileSecondario ();

}
