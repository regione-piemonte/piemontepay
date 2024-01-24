/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.presentation.action;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;

import it.csi.epay.epaypaweb.dto.TipoFormatoFileDto;
import it.csi.epay.epaypaweb.enumeration.TipoFormatoFileEnum;

public class InserisciFlussoDaFileStep1Action extends EpaypawebBaseAction {
	static private final long serialVersionUID = 1L;

	protected TipoFormatoFileEnum tipoFormato;

	public TipoFormatoFileEnum getTipoFormato() {
		return tipoFormato;
	}

	@RequiredFieldValidator(key = "campo.obbligatorio.tipo.formato")
	public void setTipoFormato(TipoFormatoFileEnum tipoFormato) {
		this.tipoFormato = tipoFormato;
	}

	public String getDescrizioneTipoFormatoFile() {
		String descrizione = "";
		for (TipoFormatoFileDto dto : getSessionContext().getFormatiFile()) {
			if (dto.getTipoFormatoFile() == tipoFormato) {
				descrizione = dto.getDescrizione();
				break;
			}
		}
		return descrizione;
	}

}
