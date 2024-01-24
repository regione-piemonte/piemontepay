/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodric.business.epaymodric.manager;

import java.util.List;

import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOInputWsRicercaElaborazionePrecedente;
import it.csi.epay.epaymodric.dto.epaymodric.ws.DTOOutputRicercaElaborazionePrecedente;

/**
 * 
 * 
 * @author Nouroudine Gueye
 *
 */
public interface RicercaElaborazionePrecedenteManager {

	public List<DTOOutputRicercaElaborazionePrecedente> ricercaElaborazionePrecedente(DTOInputWsRicercaElaborazionePrecedente input);

}
