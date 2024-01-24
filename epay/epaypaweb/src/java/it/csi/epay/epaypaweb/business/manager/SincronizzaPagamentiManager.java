/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypaweb.business.manager;

import it.csi.epay.epaypaweb.facade.sincronizzapagamentipec.dto.ResponseType;
import it.csi.epay.epaypaweb.facade.sincronizzapagamentipec.dto.SincronizzaPagamentiRequest;


public interface SincronizzaPagamentiManager {

    ResponseType sincronizzaPagamenti ( SincronizzaPagamentiRequest parameters );
}
