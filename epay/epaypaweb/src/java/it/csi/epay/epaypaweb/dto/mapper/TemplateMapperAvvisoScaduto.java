/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.dto.mapper;

import it.csi.epay.epaypaweb.dto.AvvisoScadutoDto;
import it.csi.epay.epaypaweb.dto.FlussoDto;
import it.csi.epay.epaypaweb.enumeration.CampoFlussoEnum;

public class TemplateMapperAvvisoScaduto implements TemplateMapper<AvvisoScadutoDto> {

	//@formatter:off
	@Override
	public Object getValue(FlussoDto head, AvvisoScadutoDto item, CampoFlussoEnum campoFlusso) {
		if (item != null && campoFlusso != null) {
			switch (campoFlusso) {
				case AVSC_IUV: return item.getIUV();
				case AVSC_IMPORTO: return item.getImporto();
				case AVSC_DT_SCADENZA: return item.getDataScadenza();
				default: return null;
			}
		} else
			return null;
	}
	//@formatter:on
}
