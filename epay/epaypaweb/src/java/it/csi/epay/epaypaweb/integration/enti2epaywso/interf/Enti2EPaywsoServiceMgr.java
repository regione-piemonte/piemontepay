/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaypaweb.integration.enti2epaywso.interf;

import it.csi.epay.epaypaweb.dto.EsitoDto;
import it.csi.epay.epaypaweb.dto.FlussoCompletoDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaDaAggiornareDto;
import it.csi.epay.epaypaweb.dto.PosizioneDebitoriaDto;
import it.csi.epay.epaypaweb.exception.IntegrationException;

public interface Enti2EPaywsoServiceMgr {

	public EsitoDto inserisciListaCarico(FlussoCompletoDto<PosizioneDebitoriaDto> flusso) throws IntegrationException;

	public EsitoDto aggiornaPosizioniDebitorie(FlussoCompletoDto<PosizioneDebitoriaDaAggiornareDto> flusso) throws IntegrationException;

}
