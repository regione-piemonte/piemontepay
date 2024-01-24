/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.dto;

public class ApplicationEsito implements Comparable<ApplicationEsito> {
	private ConfigAppDto config;
	private EsitoInvioDto esito;
	
	public ConfigAppDto getConfig() {
		return config;
	}

	public void setConfig(ConfigAppDto config) {
		this.config = config;
	}

	public EsitoInvioDto getEsito() {
		return esito;
	}

	public void setEsito(EsitoInvioDto esito) {
		this.esito = esito;
	}

	@Override
	public int compareTo(ApplicationEsito other) {
		int result = 99;
		
		if (other == null) {
			result = 1;
		} else {
			if (esito == null || esito.getEsitoInvioRichiestaDto() == null || esito.getEsitoInvioRichiestaDto().getCod() == null) {
				result = -1;
			} 
			if (other.esito == null || other.esito.getEsitoInvioRichiestaDto() == null || other.esito.getEsitoInvioRichiestaDto().getCod() == null) {
				result = (result == -1) ? 0 : 1;
			}
			if (result == 99) {
				result = esito.getDataEsitoInvio().compareTo(other.esito.getDataEsitoInvio());
			}
		}
		
		return result;
	}
}
