/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager;

import java.util.Date;

import it.csi.epay.epaymodric.business.epaymodric.bo.Catalogo;
import it.csi.epay.epaymodric.interfacews.epaymodric.exception.RiconciliazioneException;


/**
 *
 */

public interface CatalogoManager {

    Catalogo recuperaDatiCatalogo ( String idEnte, String codiceVersamento, String datiSpecificiRiscossione, Integer annoEsercizio, Date dataPagamento );

	Catalogo recuperaRiferimentoContabilePerAnnoCorrenteAttivo(String idEnte, String codiceVersamento, Integer annoEsercizio, Date dataValidita, String datiSpecificiRiscossione,Date dataPagamento) throws RiconciliazioneException;

}
