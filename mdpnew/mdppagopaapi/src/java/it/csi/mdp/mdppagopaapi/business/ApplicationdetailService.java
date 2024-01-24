/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.business;

import it.csi.mdp.mdppagopaapi.integration.domain.Applicationdetail;


public interface ApplicationdetailService {


    public Applicationdetail findTopByApplicationid ( String applicationId );

}
