/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.business.interf;

import java.util.List;

import javax.ejb.Local;

import it.csi.epay.epaypaweb.dto.ReportBaseDto;
import it.csi.epay.epaypaweb.exception.BusinessException;

@Local
public interface ReportCreatorBusiness <T extends ReportBaseDto>{
	
	List<String> generaReport(T reportDto, int pageSize) throws BusinessException;

}
