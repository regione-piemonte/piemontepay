/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.service;

import java.util.List;

import it.csi.epay.epaypacatalogsrv.dto.CallerInputDto;
import it.csi.epay.epaypacatalogsrv.model.Ente;
import it.csi.epay.epaypacatalogsrv.model.Utente;
import it.csi.epay.epaypacatalogsrv.vo.security.PrincipalTematicaVO;
import it.csi.epay.epaypacatalogsrv.vo.security.PrincipalVO;

public interface ProfilazioneService {

	PrincipalVO getPrincipal(CallerInputDto caller);

    List<String> getCodiciTematicheConVisibilitaTotale ( PrincipalVO principal );

    List<String> getCodiciTematicheConVisibilitaParziale ( PrincipalVO principal );

    List<Long> getIdCodiciVersamentoConVisibilita ( PrincipalVO principal );

    List<PrincipalTematicaVO> getTematicheAssociate ( Utente dtoUtente, Ente dtoEnte );

    PrincipalVO createImpersonatedPrincipal ( String cf );

    void impersonateIfAnonymous ( String cf );

    void impersonate ( String cf );

	PrincipalVO getPrincipalV2(CallerInputDto caller);
}
