/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdpnew.mdpcoopapplicativasrv.business.mdpcoopapplicativa.interfaces;

import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.AggiornaCodiceVersamentoRequest;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.AggiornaEnteRequest;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.AnnullaOperazioneRequest;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.ConfermaOperazioneRequest;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdpcoopapplicativa.coop.ws.ResponseType;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdprichiediapplicationid.coop.ws.RichiediApplicationIdRequest;
import it.csi.mdpnew.mdpcoopapplicativasrv.dto.mdprichiediapplicationid.coop.ws.RichiediApplicationIdResponse;
import it.csi.mdpnew.mdpcoopapplicativasrv.exception.mdpcoopapplicativa.coop.PersistenceException;

public interface MdpcoopapplicativaBusinessInterface {

    ResponseType aggiornaCodiceVersamento ( AggiornaCodiceVersamentoRequest aggiornaCodiceVersamentoRequest ) throws PersistenceException;

    ResponseType aggiornaEnte ( AggiornaEnteRequest aggiornaEnteRequest ) throws PersistenceException;

    ResponseType annullaOperazione ( AnnullaOperazioneRequest annullaOperazioneRequest ) throws PersistenceException;

    ResponseType confermaOperazione ( ConfermaOperazioneRequest confermaOperazioneRequest ) throws PersistenceException;
    
    RichiediApplicationIdResponse richiediApplicationId(RichiediApplicationIdRequest request);
    
}
