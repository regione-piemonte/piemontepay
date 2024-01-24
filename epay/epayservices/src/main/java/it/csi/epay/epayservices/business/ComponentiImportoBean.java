/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epayservices.business;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import it.csi.epay.epayservices.integration.db.manager.ComponentiImportoManager;
import it.csi.epay.epayservices.interfaces.ejb.ComponentiImportoFacade;
import it.csi.epay.epayservices.model.ComponentiImportoInput;
import it.csi.epay.epayservices.model.ComponentiImportoOutput;


@Stateless ( name = "ComponentiImportoFacade", mappedName = "ComponentiImporto" )
public class ComponentiImportoBean extends _BaseBean implements ComponentiImportoFacade {
	
	@EJB
	private ComponentiImportoManager componentiImportoManager;

	@Override
	public ComponentiImportoOutput estraiComponentiImporto(ComponentiImportoInput componentiImportoInput) {
		return componentiImportoManager.estraiComponentiImporto(componentiImportoInput);
	}

   

}
