/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaymodricweb.integration.mapper;

import it.csi.epay.epaymodricweb.integration.stubs.epaymodricws.DtoOutputRicercaElaborazionePrecedente;
import it.csi.epay.epaymodricweb.model.elaborazione.ElaborazioneVO;

public class InserisciElaborazioneMapper extends ParentMapper {
	//usato in ricercaElaborazionePrecedente per mappare il risultato della ricerca
	public static ElaborazioneVO parseElaborazioneVO(DtoOutputRicercaElaborazionePrecedente dto) {
		ElaborazioneVO mapped = new ElaborazioneVO();
		mapped.setId(dto.getId());
		mapped.setIdEnte(dto.getIdEnte());
		mapped.setUtenteIns(dto.getUtenteIns());
		mapped.setDataElaborazione(parseDateFromXmlGregorianCalendar(dto.getDataElaborazione()));
		mapped.setDataFine(parseDateFromXmlGregorianCalendar(dto.getDataFine()));
		mapped.setDataInizio(parseDateFromXmlGregorianCalendar(dto.getDataInizio()));
		mapped.setListaFlussi(dto.getListaFlussi());
		mapped.setStatoElaborazione(dto.getStatoElaborazione());
		return mapped;

	}

}
