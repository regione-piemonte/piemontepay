/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository.custom;

import java.util.List;

import it.csi.epay.epaypacatalogsrv.model.CodiceVersamentoLight;

public interface CodiceVersamentoLightRepositoryCustom {
	
	List<CodiceVersamentoLight> ricercaCovSecondariPerEnte( Long idEnte);

   
}
