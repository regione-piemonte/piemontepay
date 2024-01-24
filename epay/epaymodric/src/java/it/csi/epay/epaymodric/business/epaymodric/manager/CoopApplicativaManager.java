/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaymodric.business.epaymodric.manager;

import it.csi.epay.epaymodric.util.wsdl.coopapplicativapec.AggiornaCodiceVersamentoRequest;
import it.csi.epay.epaymodric.util.wsdl.coopapplicativapec.AggiornaEnteRequest;
import it.csi.epay.epaymodric.util.wsdl.coopapplicativapec.AggiornaRiferimentoContabileRequest;
import it.csi.epay.epaymodric.util.wsdl.coopapplicativapec.AnnullaOperazioneRequest;
import it.csi.epay.epaymodric.util.wsdl.coopapplicativapec.ConfermaOperazioneRequest;
import it.csi.epay.epaymodric.util.wsdl.coopapplicativapec.ResponseType;


public interface CoopApplicativaManager {

    ResponseType aggiornaCatalogo ( AggiornaRiferimentoContabileRequest aggiornaRiferimentoContabileRequest);

    ResponseType aggiornaCodiceVersamento (AggiornaCodiceVersamentoRequest aggiornaCodiceVersamentoRequest );

    ResponseType aggiornaEnte ( AggiornaEnteRequest aggiornaEnteRequest );

    ResponseType annullaOperazione ( AnnullaOperazioneRequest annullaOperazioneRequest );

    ResponseType confermaOperazione ( ConfermaOperazioneRequest confermaOperazioneRequest );
}
