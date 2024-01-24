/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.integration.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.csi.mdp.mdppagopaapi.integration.domain.Transazione;


/**
 * Spring data Jpa repository for "TransazioneRepository" <br>
 *
 * @author Silvia.Balsamini
 */
@SuppressWarnings ( "unused" )
@Repository
public interface TransazioneRepository extends IRepository<Transazione, String> {

    @Query ( value = "select nextval('seq_transazione')", nativeQuery = true )
    public BigInteger getSequenceNextVal ();
}
