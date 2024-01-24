/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.util.spreadsheet;

import java.io.IOException;

import it.csi.epay.epaypaweb.dto.ElementoFlussoDto;
import it.csi.epay.epaypaweb.dto.FlussoCompletoDto;

public interface PageableSpreadsheetGenerator <T extends ElementoFlussoDto> {

	void flushData()  throws IOException;
	void addData(FlussoCompletoDto<T> flussoCompleto) throws IOException;
	void addData(FlussoCompletoDto<T> flussoCompleto, boolean forceDataFlush) throws IOException;
}
