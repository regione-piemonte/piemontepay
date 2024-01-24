/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypacatalogsrv.business.service;

import java.util.List;

import it.csi.epay.epaypacatalogsrv.dto.CallerInputDto;
import it.csi.epay.epaypacatalogsrv.vo.security.PrincipalVO;


public interface AuditService {

    void postAccesso ( CallerInputDto caller, PrincipalVO principal );

    void postAccessoNegato ( CallerInputDto caller, Exception e );

    void prePersist ( Object target );

    void preUpdate ( Object target );

    void preRemove ( Object target );

    void preExport ( String className,String description, List<String> list );

}
