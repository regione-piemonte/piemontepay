/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.business;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import it.csi.mdp.mdppagopaapi.integration.domain.Transazione;



public interface TransazioneService {

    /**
     * Metodo per registrare un evento.
     *
     * @param gde
     * @return
     */
    Transazione initTransazione ( Transazione t );

    BigInteger getSequenceNextVal ();
    
    Transazione aggiornaStatoTransazione ( Transazione t  , Long codStato );
    
    Transazione findById ( String idTransazione );
    




}
