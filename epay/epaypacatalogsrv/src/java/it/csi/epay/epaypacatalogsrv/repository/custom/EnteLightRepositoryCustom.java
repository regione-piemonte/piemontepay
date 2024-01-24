/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypacatalogsrv.repository.custom;

import java.util.List;

import it.csi.epay.epaypacatalogsrv.model.EnteLight;


public interface EnteLightRepositoryCustom {

    public List<EnteLight> findEntiWithRiferimentiContabiliInScadenza ( Long maxNumDay );


    public List<EnteLight> ricercaEntiConRiferimentoContabileSecondario ( String enteDaEscludere );

}
