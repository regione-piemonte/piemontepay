/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto.mapper;

import it.csi.epay.epaypaweb.dto.ElementoFlussoDto;
import it.csi.epay.epaypaweb.dto.FlussoDto;
import it.csi.epay.epaypaweb.enumeration.CampoFlussoEnum;

public interface TemplateMapper<T extends ElementoFlussoDto> {

	public Object getValue(FlussoDto head, T item, CampoFlussoEnum campoFlusso);

}
