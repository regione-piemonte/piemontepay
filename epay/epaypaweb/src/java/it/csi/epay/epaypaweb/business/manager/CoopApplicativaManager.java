/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */


package it.csi.epay.epaypaweb.business.manager;

import it.csi.epay.epaypaweb.exception.PersistenceException;
import it.csi.epay.epaypaweb.facade.cooppec.dto.AggiornaCodiceVersamentoRequest;
import it.csi.epay.epaypaweb.facade.cooppec.dto.AggiornaEnteRequest;
import it.csi.epay.epaypaweb.facade.cooppec.dto.AnnullaOperazioneRequest;
import it.csi.epay.epaypaweb.facade.cooppec.dto.ConfermaOperazioneRequest;
import it.csi.epay.epaypaweb.facade.cooppec.dto.ResponseType;

public interface CoopApplicativaManager {

    ResponseType aggiornaCodiceVersamento (AggiornaCodiceVersamentoRequest aggiornaCodiceVersamentoRequest ) throws PersistenceException;

    ResponseType aggiornaEnte ( AggiornaEnteRequest aggiornaEnteRequest ) throws PersistenceException;

    ResponseType annullaOperazione ( AnnullaOperazioneRequest annullaOperazioneRequest ) throws PersistenceException;

    ResponseType confermaOperazione ( ConfermaOperazioneRequest confermaOperazioneRequest ) throws PersistenceException;
}
