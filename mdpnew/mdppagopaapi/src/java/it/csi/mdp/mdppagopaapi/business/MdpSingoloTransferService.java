/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdppagopaapi.business;

import it.csi.mdp.mdppagopaapi.integration.domain.MdpReceipt;
import it.csi.mdp.mdppagopaapi.integration.domain.MdpSingoloTransfer;

import java.util.List;



public interface MdpSingoloTransferService {

    /**
	 * Metodo per registrare un evento.
	 */
    void inserisciSingoloTransfer ( MdpSingoloTransfer datiSingoloTransfer );

    /**
     * Metodo per cercare per id.
     *
	 */
	List<MdpSingoloTransfer> findByMdpReceipt ( MdpReceipt mdpReceipt );

    /**
     * Metodo per registrare un evento.
     *
	 */
    MdpSingoloTransfer insert ( MdpSingoloTransfer mdpSingoloTransfer );

}
