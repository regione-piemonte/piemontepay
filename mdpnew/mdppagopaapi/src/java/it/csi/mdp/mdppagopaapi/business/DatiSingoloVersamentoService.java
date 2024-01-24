/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.business;

import java.util.List;

import it.csi.mdp.mdppagopaapi.integration.domain.DatiSingoloVersamento;



public interface DatiSingoloVersamentoService {

    /**
     * Metodo per registrare un evento.
     *
     * @param datiSingoloVersamento
     * @return
     */
    DatiSingoloVersamento inserisciSingoloVersamento ( DatiSingoloVersamento datiSingoloVersamento );

    public List<DatiSingoloVersamento> findByMultiId ( int elementoMultiversamento );
    
    /**
     * Metodo per registrare un evento.
     *
     * @param ElementoMultiversamento
     * @return
     */
    DatiSingoloVersamento insert ( DatiSingoloVersamento datiSingoloVersamento );


}
